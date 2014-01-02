package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T088;
import gateway.sbs.txn.model.form.T104;
import gateway.sbs.txn.model.msg.Ma100;
import gateway.sbs.txn.model.msg.Ma101;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;
import feb.print.model.VchPartRso;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import gateway.sbs.txn.model.form.T399;

/**
 *
 */
@ManagedBean
@ViewScoped
public class PartdraAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(PartdraAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private T088 dra = new T088();
    private T104 Vrso = new T104();
    private boolean printable = false;

    private String actty1;
    private String iptac1;
    private String dueflg;
    private String dramd1;
    private String dptprd;
    private String valdat;
    private String txnamt;
    private String vchtyp;
    private String vchnum;
    private String anacde;
    private String auttlr;
    private String autpwd;


    private List<Ma100> hstList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        actty1 = "07";
        iptac1 = "010600012700000033";
        dueflg = "0";
        vchtyp = "74";
        vchnum = "00000303";
        anacde = "1111";
    }
    public String onAllQuery() {
        try {
//            if (allone.contentEquals("0")){
            Ma100 ma100 = new Ma100(actty1,iptac1,dueflg,dramd1,dptprd,valdat,txnamt,vchtyp,vchnum,anacde);
            List<SOFForm> formList = dataExchangeService.callSbsTxn(auttlr,autpwd,"a100", ma100);
                 if (formList != null && !formList.isEmpty()) {
                    for (SOFForm form : formList) {
                        if ("T088".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                            T088 t088 = (T088) form.getFormBody();
                            dra = t088;
                            printable = true ;
                        }
                        else {
                            logger.info(form.getFormHeader().getFormCode());

                            MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
//                            MessageUtil.addInfoWithClientID("msgs", "查询成功");
                        }
                    }
                    }
                if (dra == null) {
                    MessageUtil.addWarn("没有查询到数据。");
                }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }

        return null;
    }
    public void onPrint() {
        Ma101 ma101 = new Ma101(actty1,iptac1,dueflg,dramd1,dptprd,valdat,txnamt,vchtyp,vchnum,anacde);
        VchPartRso PrintRso = new VchPartRso();
        List<SOFForm> formList = dataExchangeService.callSbsTxn(auttlr,autpwd,"a101", ma101);
        if (formList != null && !formList.isEmpty()) {
            for (SOFForm form : formList) {
                if ("T104".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                    T104 t104 = (T104) form.getFormBody();
                    Vrso = t104;
                }
                else {
                    logger.info(form.getFormHeader().getFormCode());

                    MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
//                            MessageUtil.addInfoWithClientID("msgs", "查询成功");
                }
            }
        }
        if (PrintRso.getACTNAM().isEmpty()) {
            MessageUtil.addWarn("打印失败。");
        }
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
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
    public String getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(String txnamt) {
        this.txnamt = txnamt;
    }

    public T088 getDra() {
        return dra;
    }

    public void setDra(T088 dra) {
        this.dra = dra;
    }

    public String getDueflg() {
        return dueflg;
    }

    public void setDueflg(String dueflg) {
        this.dueflg = dueflg;
    }

    public String getDptprd() {
        return dptprd;
    }

    public void setDptprd(String dptprd) {
        this.dptprd = dptprd;
    }

    public String getValdat() {
        return valdat;
    }

    public void setValdat(String valdat) {
        this.valdat = valdat;
    }

    public String getVchtyp() {
        return vchtyp;
    }

    public void setVchtyp(String vchtyp) {
        this.vchtyp = vchtyp;
    }

    public String getVchnum() {
        return vchnum;
    }

    public void setVchnum(String vchnum) {
        this.vchnum = vchnum;
    }

    public String getAnacde() {
        return anacde;
    }

    public void setAnacde(String anacde) {
        this.anacde = anacde;
    }

    public List<Ma100> getHstList() {
        return hstList;
    }

    public void setHstList(List<Ma100> hstList) {
        this.hstList = hstList;
    }

    public T104 getVrso() {
        return Vrso;
    }

    public void setVrso(T104 vrso) {
        Vrso = vrso;
    }

    public String getAuttlr() {
        return auttlr;
    }

    public void setAuttlr(String auttlr) {
        this.auttlr = auttlr;
    }

    public String getAutpwd() {
        return autpwd;
    }

    public void setAutpwd(String autpwd) {
        this.autpwd = autpwd;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
    }
}
