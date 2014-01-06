package feb.view;

import feb.service.DataExchangeService;
import feb.service.RosPrintService;
import feb.service.VchPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T133;
import gateway.sbs.txn.model.msg.Ma280;
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
 * 利率码
 */
@ManagedBean
@ViewScoped
public class ActrsoAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ActrsoAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{rosPrintService}")
    private RosPrintService rosPrintService;

    private T133 rso = new T133();
    private String actty1;
    private String iptac1;
    private String dramd1;
    private String txndat;
    private String dueflg;
    private String actty2;
    private String iptac2;
    private String nbkfl2;
    private boolean printable = false;

    private List<Ma280> hstList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        actty1 = "07";
//        iptac1 = "010600012000002235";
        dramd1 = "0";
        dueflg = "0";
        actty2 = "01";
//        iptac2 = "801000000432012001";
        nbkfl2 = "0";
    }
    public String onAllQuery() {
        try {
//            if (allone.contentEquals("0")){
            Ma280 ma280 = new Ma280(actty1,iptac1,dramd1,txndat,dueflg,actty2,iptac2,nbkfl2);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a280", ma280);

                 if (formList != null && !formList.isEmpty()) {
                    for (SOFForm form : formList) {
                        if ("T133".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                            T133 t133 = (T133) form.getFormBody();
                            rso = t133;
                            printable = true;
                        }
                        else {
                            logger.info(form.getFormHeader().getFormCode());

                            MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
//                            MessageUtil.addInfoWithClientID("msgs", "查询成功");
                        }
                    }
                    }
                if (rso == null) {
                    MessageUtil.addWarn("没有查询到数据。");
                }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }

        return null;
    }
    public void onPrintOpenAct() {
        try {
            rosPrintService.printVchpenAct(
                    "     客户建立确认书", "1", "2", "3",
                    "4", "5", "6", "sys1");
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public T133 getRso() {
        return rso;
    }

    public void setRso(T133 rso) {
        this.rso = rso;
    }

    public String getActty1() {
        return actty1;
    }

    public void setActty1(String actty1) {
        this.actty1 = actty1;
    }

    public String getIptac1() {
        return iptac1;
    }

    public void setIptac1(String iptac1) {
        this.iptac1 = iptac1;
    }

    public String getDramd1() {
        return dramd1;
    }

    public void setDramd1(String dramd1) {
        this.dramd1 = dramd1;
    }

    public String getTxndat() {
        return txndat;
    }

    public void setTxndat(String txndat) {
        this.txndat = txndat;
    }

    public String getDueflg() {
        return dueflg;
    }

    public void setDueflg(String dueflg) {
        this.dueflg = dueflg;
    }

    public String getActty2() {
        return actty2;
    }

    public void setActty2(String actty2) {
        this.actty2 = actty2;
    }

    public String getIptac2() {
        return iptac2;
    }

    public void setIptac2(String iptac2) {
        this.iptac2 = iptac2;
    }

    public String getNbkfl2() {
        return nbkfl2;
    }

    public void setNbkfl2(String nbkfl2) {
        this.nbkfl2 = nbkfl2;
    }

    public List<Ma280> getHstList() {
        return hstList;
    }

    public void setHstList(List<Ma280> hstList) {
        this.hstList = hstList;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
    }

    public RosPrintService getRosPrintService() {
        return rosPrintService;
    }

    public void setRosPrintService(RosPrintService rosPrintService) {
        this.rosPrintService = rosPrintService;
    }
}
