package feb.view;

import feb.service.DataExchangeService;
import feb.service.RosPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T133;
import gateway.sbs.txn.model.form.T554;
import gateway.sbs.txn.model.msg.Ma276;
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

    private String actty1;
    private String iptac1;
    private String dramd1;
    private String txndat;
    private String dueflg;
    private String actty2;
    private String iptac2;
    private String nbkfl2;
    private T133 rso = new T133();
    private boolean printable = false;

    //----------------
    private Ma276 ma276 = new Ma276();
    private T554 t554;
    private List<T554.Bean> dataList = new ArrayList<>();
    //----------------


    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        actty1 = "07";
        dramd1 = "0";
        dueflg = "0";
        actty2 = "01";
        nbkfl2 = "0";
    }

    //定期查询
    public String onAllQuery() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a276", ma276);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T554".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T554 t554 = (T554) form.getFormBody();
                        dataList = t554.getBeanList();
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
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

    public Ma276 getMa276() {
        return ma276;
    }

    public void setMa276(Ma276 ma276) {
        this.ma276 = ma276;
    }

    public T554 getT554() {
        return t554;
    }

    public void setT554(T554 t554) {
        this.t554 = t554;
    }

    public List<T554.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T554.Bean> dataList) {
        this.dataList = dataList;
    }
}
