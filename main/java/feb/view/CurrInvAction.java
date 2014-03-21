package feb.view;

import com.itextpdf.text.DocumentException;
import feb.service.DataExchangeService;
import feb.service.InvPrintService;
import feb.service.VchPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T114;
import gateway.sbs.txn.model.msg.M8114;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;
import pub.tools.BeanHelper;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-18
 * Time: ����12:56
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class CurrInvAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(CurrInvAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{invPrintService}")
    private InvPrintService invPrintService;

    private String orgid3 = "";  //            �˻�������
    private String actnum = "";  //            �˺�
    private String auttlr = "";
    private String autpwd = "";
    private  M8114 m8114 = new M8114();
    private T114 t114 ;
    private boolean isPrintable = false;               // �Ƿ�ɴ�ӡƾ֤

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
            logger.error("��ѯ�嵥ʧ��", e);
            MessageUtil.addError("��ѯ�嵥ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
    public void onPrintInv() {
        /*try {
            invPrintService.printCurrInv(" ���ڴ���嵥","2","3","4",
                    "5","6","7","8","9",
                    "10","11","12","13","14",
                    "15","16","17","18");
        } catch (Exception e) {
            logger.error("��ӡʧ��", e);
            pub.tools.MessageUtil.addError("��ӡʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }*/
        try {
            invPrintService.printCurrInv(" ���ڴ���嵥",t114.getORGIDT(),t114.getACTNUM(),t114.getACTNAM(),
                    t114.getAVABAL(),t114.getCLSDAT(),t114.getLINTDT(),t114.getCRACCM1(),t114.getCRATSF1(),
                    t114.getCACINT1(),t114.getCRACCM2(),t114.getCRATSF2(),t114.getCACINT2(),t114.getCRACCM3(),
                    t114.getCRATSF3(),t114.getCACINT3(),t114.getCACINT(),t114.getAMOUNT());
        } catch (Exception e) {
            logger.error("��ӡʧ��", e);
            pub.tools.MessageUtil.addError("��ӡʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    // - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - -
    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public InvPrintService getInvPrintService() {
        return invPrintService;
    }

    public void setInvPrintService(InvPrintService invPrintService) {
        this.invPrintService = invPrintService;
    }

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

}