package feb.view;

import feb.service.DataExchangeService;
import feb.service.PdfPrintService;
import feb.service.TemPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.*;
import gateway.sbs.txn.model.msg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.MessageUtil;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * 客户账户
 */
@ManagedBean
@ViewScoped
public class ClientActAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{pdfPrintService}")
    private PdfPrintService pdfPrintService;

    @ManagedProperty(value = "#{temPrintService}")
    private TemPrintService temPrintService;

    private M8101 clientAct = new M8101();     // 开户
    private M8109 closeAct = new M8109();      // 关户查询
    private T101 rtnActInfo;                   // 开关户返回信息
    private T109 closeActInfo = new T109();    // 关户查询返回信息
    private T109 updateActRtnInfo;             // 修改账户返回信息
    private T108 actInfo = new T108();         // 查询返回账户信息
    private M8104 internalAct = new M8104();   // 内部账户
    private M8108 updateQryAct = new M8108();  // 账户查询
    private boolean updateable = false;        // 是否可修改
    private M8102 updateAct = new M8102();     // 要修改的账户
    private boolean closeable = false;         // 是否可关户
    private String actnum;
    private String auttlr;                     // 授权主管柜员号
    private String autpwd;                     // 授权主管密码
    private boolean isPrintable;               // 是否可打印凭证
    private String tellerid;

    @PostConstruct
    public void init() {
        tellerid = SkylineService.getOperId();
    }

    public String onCreate() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8101", clientAct).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                MessageUtil.addInfo("客户账户建立成功，账号：" + rtnActInfo.getACTNUM());
                isPrintable = true;
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8101客户开户失败", e);
            MessageUtil.addError("8101客户开户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // 打印开户确认书
   /* public void onPrintOpenAct() {
        try {
            pdfPrintService.printVch4OpenClsAct(
                    "       开户确认书", rtnActInfo.getORGIDT(), rtnActInfo.getDEPNUM(), rtnActInfo.getACTNUM(),
                    rtnActInfo.getCUSNAM(), rtnActInfo.getOPNDAT(), "", tellerid);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }*/
    // 打印开户确认书
    public void onPrintOpenAct() {
        try {
            temPrintService.printOpnAct(
                    rtnActInfo.getORGIDT(), rtnActInfo.getDEPNUM(), rtnActInfo.getACTNUM(),
                    rtnActInfo.getCUSNAM(), rtnActInfo.getOPNDAT(), tellerid);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    public void onPrintCloseAct() {
        try {
            temPrintService.printClsAct(
                    rtnActInfo.getORGIDT(), rtnActInfo.getDEPNUM(), rtnActInfo.getACTNUM(),
                    rtnActInfo.getCUSNAM(), rtnActInfo.getOPNDAT(), rtnActInfo.getCLSDAT(), tellerid);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    /*public void onPrintCloseAct() {
        try {
            pdfPrintService.printVch4OpenClsAct(
                    "       销户确认书", rtnActInfo.getORGIDT(), rtnActInfo.getDEPNUM(), rtnActInfo.getACTNUM(),
                    rtnActInfo.getCUSNAM(), rtnActInfo.getOPNDAT(), rtnActInfo.getCLSDAT(), tellerid);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }*/

    public String onCreateInternalAct() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8104", internalAct).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                isPrintable = true;
                MessageUtil.addInfo("内部账户建立成功，账号：" + rtnActInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8104内部账户开户失败", e);
            MessageUtil.addError("8104内部账户开户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onQryCloseAct() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn(auttlr, autpwd, "8109", closeAct);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T109".equalsIgnoreCase(formcode)) {
                    closeActInfo = (T109) form.getFormBody();
                    closeable = true;
                    actnum = closeActInfo.getCUSIDT() + closeActInfo.getAPCODE() + closeActInfo.getCURCDE();
                } else if ("W313".equalsIgnoreCase(formcode)) {
                    MessageUtil.addWarnWithClientID("msgs", form.getFormHeader().getFormCode());
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("8109交易失败", e);
            MessageUtil.addError("8109交易失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClose() {
        try {
            M8103 m8103 = new M8103(closeAct.getACTNUM());
            SOFForm form = dataExchangeService.callSbsTxn(auttlr, autpwd, "8103", m8103).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                closeable = false;
                isPrintable = true;
                MessageUtil.addInfo("客户账户关闭成功，账号：" + rtnActInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8103关闭账户失败", e);
            MessageUtil.addError("8103关闭账户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onCloseInternalAct() {
        try {
            M8106 m8106 = new M8106();
            m8106.setACTNUM(closeAct.getACTNUM());
            SOFForm form = dataExchangeService.callSbsTxn(auttlr, autpwd, "8106", m8106).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                closeable = false;
                isPrintable = true;
                MessageUtil.addInfo("账户关闭成功，账号：" + rtnActInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8106关闭内部账户失败", e);
            MessageUtil.addError("8106关闭内部账户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onQryUpdateAct() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8108", updateQryAct);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T108".equalsIgnoreCase(formcode)) {
                    actInfo = (T108) form.getFormBody();
                    BeanHelper.copyFields(actInfo, updateAct);
                    updateable = true;
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("8108查询账户失败", e);
            MessageUtil.addError("8108查询账户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onUpdate() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn(auttlr, autpwd, "8102", updateAct);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T109".equalsIgnoreCase(formcode)) {
                    updateActRtnInfo = (T109) form.getFormBody();
                    updateable = false;
                    MessageUtil.addInfo("客户账户修改成功，账号：" +
                            updateActRtnInfo.getCUSIDT() + updateActRtnInfo.getAPCODE() + updateActRtnInfo.getCURCDE());
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("8102账户修改交易失败", e);
            MessageUtil.addError("8102账户修改交易失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onQryCustom() {
        try {
            String cusidt = clientAct.getACTNUM().substring(0, 7);
            M8002 m8002 = new M8002();
            m8002.setCUSIDT(cusidt);
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8002", m8002);
            SOFForm form = forms.get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T004".equalsIgnoreCase(formcode)) {
                T004 t004 = (T004) form.getFormBody();
                clientAct.setACTNAM(t004.getCUSNAM());
                clientAct.setSTMADD(t004.getCORADD());
                clientAct.setSTMZIP(t004.getZIPCDE());
                clientAct.setINTTRA("8010"+clientAct.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            }
        } catch (Exception e) {
            logger.error("联动查询客户信息失败", e);
            MessageUtil.addError("联动查询客户信息失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onQryApc() {
        try {
            String apcode = internalAct.getACTNUM().substring(7, 11);
            M9814 m9814 = new M9814();
            m9814.setAPCODE(apcode);
            m9814.setFUNCDE("0");
            List<SOFForm> forms = dataExchangeService.callSbsTxn("9814", m9814);
            SOFForm form = forms.get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T862".equalsIgnoreCase(formcode)) {
                T862 t862 = (T862) form.getFormBody();
                internalAct.setACTNAM(t862.getAPCNAM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            }
        } catch (Exception e) {
            logger.error("联动查询核算码信息失败", e);
            MessageUtil.addError("联动查询核算码信息失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public void  handleCom(){
        try{
            String acttyp = clientAct.getACTTYP();
            if (acttyp.equals("2")){
                clientAct.setINTFLG("0");
            }
        }catch (Exception e){
            logger.error("联动设置账页信息失败", e);
            MessageUtil.addError("联动设置账页信息失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }

    }

    // ------------------------------

    public PdfPrintService getPdfPrintService() {
        return pdfPrintService;
    }

    public void setPdfPrintService(PdfPrintService pdfPrintService) {
        this.pdfPrintService = pdfPrintService;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public M8101 getClientAct() {
        return clientAct;
    }

    public void setClientAct(M8101 clientAct) {
        this.clientAct = clientAct;
    }

    public M8109 getCloseAct() {
        return closeAct;
    }

    public void setCloseAct(M8109 closeAct) {
        this.closeAct = closeAct;
    }

    public T101 getRtnActInfo() {
        return rtnActInfo;
    }

    public void setRtnActInfo(T101 rtnActInfo) {
        this.rtnActInfo = rtnActInfo;
    }

    public T109 getCloseActInfo() {
        return closeActInfo;
    }

    public void setCloseActInfo(T109 closeActInfo) {
        this.closeActInfo = closeActInfo;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    public String getActnum() {
        return actnum;
    }

    public void setActnum(String actnum) {
        this.actnum = actnum;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }

    public M8108 getUpdateQryAct() {
        return updateQryAct;
    }

    public void setUpdateQryAct(M8108 updateQryAct) {
        this.updateQryAct = updateQryAct;
    }

    public M8102 getUpdateAct() {
        return updateAct;
    }

    public void setUpdateAct(M8102 updateAct) {
        this.updateAct = updateAct;
    }

    public T109 getUpdateActRtnInfo() {
        return updateActRtnInfo;
    }

    public void setUpdateActRtnInfo(T109 updateActRtnInfo) {
        this.updateActRtnInfo = updateActRtnInfo;
    }

    public T108 getActInfo() {
        return actInfo;
    }

    public void setActInfo(T108 actInfo) {
        this.actInfo = actInfo;
    }

    public M8104 getInternalAct() {
        return internalAct;
    }

    public void setInternalAct(M8104 internalAct) {
        this.internalAct = internalAct;
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
        return isPrintable;
    }

    public void setPrintable(boolean printable) {
        isPrintable = printable;
    }

    public String getTellerid() {
        return tellerid;
    }

    public void setTellerid(String tellerid) {
        this.tellerid = tellerid;
    }

    public TemPrintService getTemPrintService() {
        return temPrintService;
    }

    public void setTemPrintService(TemPrintService temPrintService) {
        this.temPrintService = temPrintService;
    }
}
