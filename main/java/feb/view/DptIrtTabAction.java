package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T227;
import gateway.sbs.txn.model.msg.Ma831;
import gateway.sbs.txn.model.msg.Ma832;
import gateway.sbs.txn.model.msg.Ma834;
import gateway.sbs.txn.model.msg.Ma835;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;
import pub.tools.BeanHelper;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/12         存款种类与利率码对应表维护-多笔查询
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class DptIrtTabAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(DptIrtTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private T227 t227 = new T227();
    private Ma835 ma835 = new Ma835();
    private Ma831 ma831 = new Ma831();
    private Ma832 ma832 = new Ma832();
    private Ma834 ma834 = new Ma834();
    private List<T227.Bean> dataList ;

    private T227.Bean beanSelected;

    @PostConstruct
    public void init(){
        onDptirtQry();
    }


    public String onDptirtQry(){
        try {
            SOFForm form = dataExchangeService.callSbsTxn("a835",ma835).get(0);
            String formcode = form.getFormHeader().getFormCode();
            dataList = new ArrayList<>();
            if ("T227".equals(formcode)){
                t227 = (T227)form.getFormBody();
                dataList.addAll(t227.getBeanList());
                for (int j = 0; j < dataList.size(); j++) {
                    dataList.get(j).setPkid(UUID.randomUUID().toString());
                }
            }else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onAdd(){
        try {
            SOFForm form = dataExchangeService.callSbsTxn("a831",ma831).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equals(formcode)){
                logger.info(formcode);
                pub.tools.MessageUtil.addInfoWithClientID("msgs", formcode);
            }else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("添加失败", e);
            MessageUtil.addError("添加失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
    public String onDelete(T227.Bean bean){
        try {
            BeanHelper.copyFields(bean,ma832);
            SOFForm form = dataExchangeService.callSbsTxn("a832",ma832).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W004".equals(formcode)){
                logger.info(formcode);
                pub.tools.MessageUtil.addInfoWithClientID("msgs",formcode);
                dataList.remove(bean);
            }else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("删除失败", e);
            MessageUtil.addError("删除失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return  null;
    }

    public String onUpdate(){
        try {
            int position = dataList.indexOf(beanSelected);
            dataList.remove(beanSelected);
            SOFForm form = dataExchangeService.callSbsTxn("a834",ma834).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equals(formcode)){
                BeanUtils.copyProperties(beanSelected, ma834);
                dataList.add(position, beanSelected);
                logger.info(formcode);
                pub.tools.MessageUtil.addInfoWithClientID("msgs", formcode);
            }else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("修改失败", e);
            MessageUtil.addError("修改失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public void onRowSelect(SelectEvent event) {
        beanSelected = (T227.Bean) event.getObject();
        try {
            BeanUtils.copyProperties(ma834, beanSelected);
        } catch (Exception e) {
            logger.error("类复制出错", e);
            MessageUtil.addError("类复制出错." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    // = = = = = = = = = = = = = = = = = = = = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public T227 getT227() {
        return t227;
    }

    public void setT227(T227 t227) {
        this.t227 = t227;
    }

    public Ma835 getMa835() {
        return ma835;
    }

    public void setMa835(Ma835 ma835) {
        this.ma835 = ma835;
    }

    public Ma832 getMa832() {
        return ma832;
    }

    public void setMa832(Ma832 ma832) {
        this.ma832 = ma832;
    }

    public Ma834 getMa834() {
        return ma834;
    }

    public void setMa834(Ma834 ma834) {
        this.ma834 = ma834;
    }

    public T227.Bean getBeanSelected() {
        return beanSelected;
    }

    public void setBeanSelected(T227.Bean beanSelected) {
        this.beanSelected = beanSelected;
    }

    public List<T227.Bean> getDataList() {
        return dataList;
    }

    public Ma831 getMa831() {
        return ma831;
    }

    public void setMa831(Ma831 ma831) {
        this.ma831 = ma831;
    }

    public void setDataList(List<T227.Bean> dataList) {
        this.dataList = dataList;
    }
}
