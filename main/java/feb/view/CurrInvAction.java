package feb.view;

import com.itextpdf.text.DocumentException;
import feb.service.DataExchangeService;
import feb.service.InvPrintService;
import feb.service.TemPrintService;
import feb.service.VchPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T114;
import gateway.sbs.txn.model.msg.M8114;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;
import pub.tools.BeanHelper;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-18
 * Time: 下午12:56
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class CurrInvAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(CurrInvAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    /*@ManagedProperty(value = "#{invPrintService}")
    private InvPrintService invPrintService;*/

    @ManagedProperty(value = "#{temPrintService}")
    private TemPrintService temPrintService;

    private String orgid3 = "010";  //            账户机构号
    private String actnum = "";  //            账号
    private String auttlr = "";
    private String autpwd = "";
    private String clsdat = "";
    private String lintdt = "";
    private  M8114 m8114 = new M8114();
    private T114 t114 ;
    private boolean isPrintable = false;               // 是否可打印凭证

    public String onQryIvn() {
        try {
            m8114 = new M8114(orgid3,actnum);
            List<SOFForm> forms = dataExchangeService.callSbsTxn(auttlr, autpwd,"8114", m8114);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T114".equalsIgnoreCase(formcode)) {
                    t114 = (T114) form.getFormBody();
                    BeanHelper.copyFields(t114, m8114);
                    isPrintable = true;
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("查询清单失败", e);
            MessageUtil.addError("查询清单失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
    public void onPrintInv() {
        try {
            clsdat = new SimpleDateFormat("yyyy/MM/dd").format(new SimpleDateFormat("yyyyMMdd").parse(t114.getCLSDAT()));
            lintdt = new SimpleDateFormat("yyyy/MM/dd").format(new SimpleDateFormat("yyyyMMdd").parse(t114.getLINTDT()));
            temPrintService.printCurrInv(t114.getORGIDT(),t114.getACTNUM(),t114.getACTNAM(),
                    t114.getAVABAL(),clsdat,lintdt,t114.getCRACCM1(),t114.getCRATSF1(),
                    t114.getCACINT1(),t114.getCRACCM2(),t114.getCRATSF2(),t114.getCACINT2(),t114.getCRACCM3(),
                    t114.getCRATSF3(),t114.getCACINT3(),t114.getCACINT(),t114.getAMOUNT());
        } catch (Exception e) {
            logger.error("打印失败", e);
            pub.tools.MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    /*public void onPrintInv() {
        try {
            clsdat = new SimpleDateFormat("yyyy/MM/dd").format(new SimpleDateFormat("yyyyMMdd").parse(t114.getCLSDAT()));
            lintdt = new SimpleDateFormat("yyyy/MM/dd").format(new SimpleDateFormat("yyyyMMdd").parse(t114.getLINTDT()));
            invPrintService.printCurrInv(" 活期存款清单",t114.getORGIDT(),t114.getACTNUM(),t114.getACTNAM(),
                    t114.getAVABAL(),clsdat,lintdt,t114.getCRACCM1(),t114.getCRATSF1(),
                    t114.getCACINT1(),t114.getCRACCM2(),t114.getCRATSF2(),t114.getCACINT2(),t114.getCRACCM3(),
                    t114.getCRATSF3(),t114.getCACINT3(),t114.getCACINT(),t114.getAMOUNT());
        } catch (Exception e) {
            logger.error("打印失败", e);
            pub.tools.MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }*/

    // - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - -
    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    /*public InvPrintService getInvPrintService() {
        return invPrintService;
    }

    public void setInvPrintService(InvPrintService invPrintService) {
        this.invPrintService = invPrintService;
    }*/

    public String getOrgid3() {
        return orgid3;
    }

    public void setOrgid3(String orgid3) {
        this.orgid3 = orgid3;
    }

    public String getActnum() {
        return actnum;
    }

    public void setActnum(String actnum) {
        this.actnum = actnum;
    }

    public M8114 getM8114() {
        return m8114;
    }

    public void setM8114(M8114 m8114) {
        this.m8114 = m8114;
    }

    public T114 getT114() {
        return t114;
    }

    public void setT114(T114 t114) {
        this.t114 = t114;
    }

    public boolean isPrintable() {
        return isPrintable;
    }

    public void setPrintable(boolean printable) {
        isPrintable = printable;
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

    public String getLintdt() {
        return lintdt;
    }

    public void setLintdt(String lintdt) {
        this.lintdt = lintdt;
    }

    public String getClsdat() {
        return clsdat;
    }

    public void setClsdat(String clsdat) {
        this.clsdat = clsdat;
    }

    public TemPrintService getTemPrintService() {
        return temPrintService;
    }

    public void setTemPrintService(TemPrintService temPrintService) {
        this.temPrintService = temPrintService;
    }
}
