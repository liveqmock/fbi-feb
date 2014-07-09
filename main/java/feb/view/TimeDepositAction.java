package feb.view;

import feb.print.model.Vch;
import feb.service.DataExchangeService;
import feb.service.TemInvPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T016;
import gateway.sbs.txn.model.form.re.T104;
import gateway.sbs.txn.model.form.ac.T108;
import gateway.sbs.txn.model.form.re.T132;
import gateway.sbs.txn.model.msg.M8108;
import gateway.sbs.txn.model.msg.Ma270;
import gateway.sbs.txn.model.msg.Ma271;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定期存款
 */
@ManagedBean
@ViewScoped
public class TimeDepositAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(TimeDepositAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{temInvPrintService}")
    private TemInvPrintService temInvPrintService;
    private Ma271 ma271;
    private T132 qryT132;
    private T016 t016;
    private T104 t104;
    private T108 t108;
    private boolean printable = false;

    private String auttlr;                     // 授权主管柜员号
    private String autpwd;                     // 授权主管密码

    @PostConstruct
    public void init() {
        ma271 = new Ma271();
        ma271.setVALDAT(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        qryT132 = new T132();
    }

    public void onQry() {
        try {
            Ma270 ma270 = new Ma270();
            BeanHelper.copyFields(ma271, ma270);
            SOFForm form = dataExchangeService.callSbsTxn("a270", ma270).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T132".equalsIgnoreCase(formcode)) {
                qryT132 = (T132) form.getFormBody();
                ma271.setACTNM2(qryT132.getACTNAM());
                ma271.setPASTYP(qryT132.getPASTYP());
                ma271.setPASSNO(qryT132.getPASSNO());
                ma271.setCORADD(qryT132.getCORADD());
                ma271.setTELNUM(qryT132.getTELNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("定期存款开户查询失败", e);
            MessageUtil.addError("定期存款开户查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    public void onQryAct() {
        try {
            M8108 m8108 = new M8108();
            //BeanHelper.copyFields(ma271, m8108);
            m8108.setACTNUM(ma271.getIPTAC1().substring(4,18));//包含头不包含尾
            SOFForm form = dataExchangeService.callSbsTxn("8108", m8108).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T108".equalsIgnoreCase(formcode)) {
                t108 = (T108) form.getFormBody();
                ma271.setIPTAC2(t108.getACTNAM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("转账账户不存在", e);
            MessageUtil.addError("转账账户不存在." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    public String onCreate() {
        try {
            ma271.setACTTY1("01");
            if (!StringUtils.isEmpty(ma271.getIPTAC1()) && !ma271.getIPTAC1().startsWith("8010")) {
                ma271.setIPTAC1("8010" + ma271.getIPTAC1());
            }
            List<SOFForm> forms = dataExchangeService.callSbsTxn(auttlr, autpwd, "a271", ma271);

            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if (!"T132".equals(formcode) && !"T016".equals(formcode) && !"T104".equals(formcode)&&!"M115".equals(formcode)) {
                    MessageUtil.addErrorWithClientID("msgs", formcode);
                } else {
                    if ("T016".equals(formcode)) {
                        t016 = (T016) form.getFormBody();
                        printable = true;
                    }
                    if ("T104".equals(formcode)) {
                        t104 = (T104) form.getFormBody();
                        printable = true;
                    }if ("M115".equals(formcode)) {
                        MessageUtil.addErrorWithClientID("msgs", formcode);
                    }
                }
            }
            if (printable) {
                MessageUtil.addInfoWithClientID("msgs", "W001");
            }
        } catch (Exception e) {
            logger.error("定期存款开户查询失败", e);
            MessageUtil.addError("定期存款开户查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public void onPrint() {
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
            String tmp ="";
            DecimalFormat df = new DecimalFormat("###,###,##0.00");
            if(t104.getTXNAMT()!=null){
                tmp = df.format(new BigDecimal(t104.getTXNAMT()));
            }
            temInvPrintService.printVch(
                    "联机传票/复核（授权）清单", t016.getTRNCDE(),t016.getTLRNUM(), t016.getTRNTIM(),
                    t016.getVCHSET(),t016.getTRNDAT(), vchs,"单位定期存款证实书",t104.getCUSIDT(),t104.getOURREF(),t104.getTXNDAT(),
                    t104.getORGNAM(),t104.getACTNAM(),t104.getBOKNUM(),t104.getVALDAT(),t104.getEXPDAT(),
                    t104.getINTCUR(),tmp,t104.getDPTTYP(),t104.getDPTPRD(),t104.getINTRAT());
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }


    // ------------------------------------------------------

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public TemInvPrintService getTemInvPrintService() {
        return temInvPrintService;
    }

    public void setTemInvPrintService(TemInvPrintService temInvPrintService) {
        this.temInvPrintService = temInvPrintService;
    }

    public Ma271 getMa271() {
        return ma271;
    }

    public void setMa271(Ma271 ma271) {
        this.ma271 = ma271;
    }


    public T132 getQryT132() {
        return qryT132;
    }

    public void setQryT132(T132 qryT132) {
        this.qryT132 = qryT132;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
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
}
