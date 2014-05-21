package feb.view;

import feb.print.model.Vch;
import feb.service.DataExchangeService;
import feb.service.PdfPrintService;
import feb.service.RosPrintService;
import feb.service.TemInvPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T016;
import gateway.sbs.txn.model.form.re.T088;
import gateway.sbs.txn.model.form.re.T104;
import gateway.sbs.txn.model.form.re.T130;
import gateway.sbs.txn.model.msg.Ma100;
import gateway.sbs.txn.model.msg.Ma101;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
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
 *
 */
@ManagedBean
@ViewScoped
public class PartdraAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(PartdraAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{rosPrintService}")
    private RosPrintService rosPrintService;

    /*@ManagedProperty(value = "#{pdfPrintService}")
    private PdfPrintService pdfPrintService;*/
    @ManagedProperty(value = "#{temInvPrintService}")
    private TemInvPrintService temInvPrintService;

    private T088 dra = new T088();
    private T104 Vrso;
    private T104 t104 = new T104();
    private T016 t016;
    private T130 t130;
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
    private String actty2;
    private String iptac2;
    private String anacde;
    private String auttlr;
    private String autpwd;

    private List<Ma100> hstList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        actty1 = "07";
        dueflg = "0";
        vchtyp = "73";
        vchnum = "05";
        anacde = "1111";
        actty2 = "01";
    }

    public String onAllQuery() {
        try {
            Ma101 ma101 = new Ma101(actty1, iptac1, dueflg, dramd1, dptprd, valdat, txnamt, vchtyp, vchnum, actty2, iptac2, anacde);
            List<SOFForm> formList = dataExchangeService.callSbsTxn(auttlr, autpwd, "a101", ma101);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T088".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T088 t088 = (T088) form.getFormBody();
                        dra = t088;
                        printable = true;
                    } else if ("T016".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T016 t016 = (T016) form.getFormBody();
                        this.t016 = t016;
                        printable = true;
                    } else if ("T104".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T104 t104 = (T104) form.getFormBody();
                        this.Vrso = t104;
                        printable = true;
                    } else if ("T130".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T130 t130 = (T130) form.getFormBody();
                        this.t130 = t130;
                        printable = true;
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
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
    public void onPrintOpenAct() {
        try {
            List<Vch> vchs = new ArrayList<>();
            int printCnt = 0;
            for (T016.Bean bean : t016.getBeanList()) {
                if (!StringUtils.isEmpty(bean.getDEBACT()) || !StringUtils.isEmpty(bean.getDEBAMT())) {
                    printCnt++;
                    logger.info(t016.getVCHSET() + " :  " + bean.getDEBACT() + bean.getDEBAMT() + bean.getCREACT() + bean.getCREAMT());
                    Vch vch = new Vch();
                    BeanHelper.copyFields(bean, vch);
                    vchs.add(vch);
                }
            }

            temInvPrintService.printVch(
                    "联机传票/复核（授权）清单", t016.getTRNCDE(),t016.getTLRNUM(), t016.getTRNTIM(),
                    t016.getVCHSET(),t016.getTRNDAT(), vchs,"单位定期存款证实书",t104.getCUSIDT(),t104.getOURREF(),t104.getTXNDAT(),
                    t104.getORGNAM(),t104.getACTNAM(),t104.getBOKNUM(),t104.getVALDAT(),t104.getEXPDAT(),
                    t104.getINTCUR(),t104.getTXNAMT(),t104.getDPTTYP(),t104.getDPTPRD(),t104.getINTRAT());
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    /*public void onPrintOpenAct() {
        try {

            if (t016 != null && Vrso != null) {
                List<Vch> vchs = new ArrayList<>();
                int printCnt = 0;
                for (T016.Bean bean : t016.getBeanList()) {
                    if (!StringUtils.isEmpty(bean.getDEBACT()) || !StringUtils.isEmpty(bean.getDEBAMT())) {
                        printCnt++;
                        logger.info(t016.getVCHSET() + " :  " + bean.getDEBACT() + bean.getDEBAMT() + bean.getCREACT() + bean.getCREAMT());
                        Vch vch = new Vch();
                        BeanHelper.copyFields(bean, vch);
                        vchs.add(vch);
                    }
                }
                for (; printCnt < 11; printCnt++) {
                    vchs.add(new Vch());
                }
                rosPrintService.printCP_ZSS("联机传票/复核（授权）清单", t016, vchs, "存款证实书", Vrso);
            }
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }*/

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public RosPrintService getRosPrintService() {
        return rosPrintService;
    }

    public void setRosPrintService(RosPrintService rosPrintService) {
        this.rosPrintService = rosPrintService;
    }

    public TemInvPrintService getTemInvPrintService() {
        return temInvPrintService;
    }

    public void setTemInvPrintService(TemInvPrintService temInvPrintService) {
        this.temInvPrintService = temInvPrintService;
    }

    public T104 getT104() {
        return t104;
    }

    public void setT104(T104 t104) {
        this.t104 = t104;
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

    public T016 getT016() {
        return t016;
    }

    public void setT016(T016 t016) {
        this.t016 = t016;
    }

    public T130 getT130() {
        return t130;
    }

    public void setT130(T130 t130) {
        this.t130 = t130;
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
