package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T824;
import gateway.sbs.txn.model.form.re.T250;
import gateway.sbs.txn.model.msg.Ma841;
import gateway.sbs.txn.model.msg.Ma842;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/7/24       总分账号对照查询
 * Time: 9:32
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class DptCmpQryAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(DptCmpQryAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String dpttyp = "24";  //操作类别
    private Ma841 ma841 = new Ma841();
    private Ma842 ma842 = new Ma842();
    private T250 t250 = new T250();
    private List<T250.Bean> dataList;

    @PostConstruct
    public void init(){
        ma841.setDPTTYP(dpttyp);
        ma842.setACTTY1(dpttyp);

    }
    public void onDptQry() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("a841", ma841).get(0);
            String formcode = form.getFormHeader().getFormCode();
            dataList = new ArrayList<>();
            if ("T250".equals(formcode)) {
                t250 = (T250) form.getFormBody();
                dataList.addAll(t250.getBeanList());
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("总分账号对照查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }

    }

    public void onDptAdd() {
        try {
            ma842.setPASTYP("A");
            SOFForm form = dataExchangeService.callSbsTxn("a842", ma842).get(0);
            String formcode = form.getFormHeader().getFormCode();
            dataList = new ArrayList<>();
            if ("W005".equals(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("总分账号对照增加失败", e);
            MessageUtil.addError("总分账号对照增加失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    public void onDptDel(T250.Bean bean) {
        try {
            ma842.setPASTYP("D");
            ma842.setIPTAC1(bean.getCODNUM());
            ma842.setACTNM1(bean.getCODVAL());
            ma842.setACTNM2(bean.getCODVL1());
            ma842.setREASON(bean.getCODVL2());
            ma842.setREMARK(bean.getCODNAM());
            SOFForm form = dataExchangeService.callSbsTxn("a842", ma842).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W004".equals(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                dataList.remove(bean);
            }else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("总分账号对照删除失败", e);
            MessageUtil.addError("总分账号对照删除失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            T250.Bean bean = (T250.Bean) event.getObject();
            ma842.setPASTYP("U");
            ma842.setIPTAC1(bean.getCODNUM());
            ma842.setACTNM1(bean.getCODVAL());
            ma842.setACTNM2(bean.getCODVL1());
            ma842.setREASON(bean.getCODVL2());
            ma842.setREMARK(bean.getCODNAM());
            SOFForm form = dataExchangeService.callSbsTxn("a842", ma842).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equals(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
            }else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("总分账号对照修改失败", e);
            MessageUtil.addError("总分账号对照修改失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    // = = = = = = = = = = = = = = = = = = = = = = = =  =

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public Ma842 getMa842() {
        return ma842;
    }

    public void setMa842(Ma842 ma842) {
        this.ma842 = ma842;
    }

    public Ma841 getMa841() {
        return ma841;
    }

    public void setMa841(Ma841 ma841) {
        this.ma841 = ma841;
    }

    public T250 getT250() {
        return t250;
    }

    public void setT250(T250 t250) {
        this.t250 = t250;
    }

    public List<T250.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T250.Bean> dataList) {
        this.dataList = dataList;
    }
}
