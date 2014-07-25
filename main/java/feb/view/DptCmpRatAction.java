package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T250;
import gateway.sbs.txn.model.msg.Ma841;
import gateway.sbs.txn.model.msg.Ma842;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
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
public class DptCmpRatAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(DptCmpRatAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String dpttyp = "26";  //操作类别
    private String codnum;  //分公司账号
    private String codval;  //总公司账号
    private String codvl1;  //上划下拨方式
    private String codvl2;  //日中保留余额
    private String codnam;  //备注
    private String action;
    private boolean updateable = false;
    private boolean deleteable = false;
    private Ma841 ma841 = new Ma841();
    private Ma842 ma842 = new Ma842();
    private T250 t250 = new T250();
    private List<T250.Bean> dataList;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        codnum = params.get("codnum");
        codval = params.get("codval");
        codvl1 = params.get("codvl1");
        codvl2 = params.get("codvl2");
        codnam = params.get("codnam");
        ma841.setDPTTYP("26");
        if (action != null) {
            if ("update".equals(action)) {
                onCopyBean();
                updateable = true;
            }
            if ("delete".equals(action)) {
                onCopyBean();
                deleteable = true;
            }
            if ("query".equals(action)) {
                ma841.setDPTTYP(dpttyp);
                onDptQry();
            }
        }
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


    public void onDptRatAdd() {
        try {
            ma842.setPASTYP("A");
            ma842.setACTTY1("26");
            ma842.setACTNM2("1");
            SOFForm form = dataExchangeService.callSbsTxn("a842", ma842).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W005".equals(formcode)) {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("总分账号对照增加失败", e);
            MessageUtil.addError("总分账号对照增加失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    public void onDptMng(String pastyp) {
        try {
            ma842.setPASTYP(pastyp);
            ma842.setACTTY1(dpttyp);
            SOFForm form = dataExchangeService.callSbsTxn("a842", ma842).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equals(formcode)||"W004".equals(formcode)) {
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

    public void onCopyBean() {
        ma842.setIPTAC1(codnum);
        ma842.setACTNM1(codval);
        ma842.setACTNM2(codvl1);
        ma842.setREASON(codvl2);
        ma842.setREMARK(codnam);
    }

    public String onClick() {
        return "dptCmpRatBean";
    }

    public String onBack() {
        return "dptCmpRatMng?faces-redirect=true&&action=query&&dpttyp=" + dpttyp;
    }
    // = = = = = = = = = = = = = = = = = = = = = = = =  =

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getCodnum() {
        return codnum;
    }

    public void setCodnum(String codnum) {
        this.codnum = codnum;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }

    public boolean isDeleteable() {
        return deleteable;
    }

    public void setDeleteable(boolean deleteable) {
        this.deleteable = deleteable;
    }

    public String getDpttyp() {
        return dpttyp;
    }

    public void setDpttyp(String dpttyp) {
        this.dpttyp = dpttyp;
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
