package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T898;
import gateway.sbs.txn.model.msg.M8401;
import gateway.sbs.txn.model.msg.M8402;
import gateway.sbs.txn.model.msg.M8409;
import gateway.sbs.txn.model.msg.M85a2;
import org.apache.commons.lang.StringUtils;
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
 * Date: 13-9-29
 * Time: ÏÂÎç12:50
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class BatchBookAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(BatchBookAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String vchset;//´«Æ±Ì×ºÅ
    private String setseq;//Ì×ÄÚÐòºÅ
    private String tlrnum;//¹ñÔ±ºÅ
    private M8401 m8401 = new M8401();
    private T898 t898 = new T898();
    private T898[] selectedRecords;
    private M8402 m8402 = new M8402();
    //private M8409 m8409 = new M8409();
    private List<T898.Bean> dataList = new ArrayList<>();
    private float totalDebitAmt;    //½è·½
    private float totalCreditAmt;   //´û·½
    private float totalAmt;         //Ôþ²î

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        vchset = StringUtils.isEmpty(params.get("vchset")) ? "0000" : params.get("vchset");
        setseq = params.get("setseq");
        onBatchQry();
        //initAddBat();
    }

    //Â¼Èë³õÊ¼»¯
    public void initAddBat() {
        M8401 m8401 = new M8401();
        m8401.setTLRNUM(tlrnum);
        m8401.setVCHSET(vchset);
        m8401.setPRDCDE("VCH1");
        m8401.setORGID3("010");
        m8401.setFUNCDE("1");
        m8401.setERYTYP("0");
    }

    //Ì×Æ±²éÑ¯
    public String onBatchQry() {
        try {
            M85a2 m85a2 = new M85a2("0000");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("85a2", m85a2);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T898".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t898 = (T898) form.getFormBody();
                        dataList.addAll(t898.getBeanList());
                        tlrnum = t898.getFormBodyHeader().getTLRNUM();
                        vchset = t898.getFormBodyHeader().getVCHSET();
                        flushTotalData();
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        // MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("Ã»ÓÐ²éÑ¯µ½Êý¾Ý¡£");
            }
        } catch (Exception e) {
            logger.error("²éÑ¯Ê§°Ü", e);
            MessageUtil.addError("²éÑ¯Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //´«Æ±Â¼Èë
    public String onCreateNewRecord() {
        try {
            m8401.setVCHSET(vchset);
            SOFForm form = dataExchangeService.callSbsTxn("8401", m8401).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {

                //MessageUtil.addInfo("´«Æ±Â¼Èë³É¹¦£º");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401´«Æ±Â¼ÈëÊ§°Ü", e);
            MessageUtil.addError("8401´«Æ±Â¼ÈëÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        onBatchQry();
        initAddBat();
        flushTotalData();
        return null;
    }

    //¼ÆËãÔþ²î
    public void flushTotalData() {
        float amt = 0f;
        totalDebitAmt = 0f;
        totalCreditAmt = 0f;
        totalAmt = 0f;
        for (T898.Bean t898s : dataList) {
            amt = Float.parseFloat(t898s.getTXNAMT());
            if (amt > 0) {
                totalDebitAmt = totalDebitAmt + amt;
            }
            if (amt < 0) {
                totalCreditAmt = totalCreditAmt + (-amt);
            }
        }
        totalAmt = totalCreditAmt - totalDebitAmt;
    }

    //Ì×Æ½
    public String onBalanceAct() {
        try {
            String str = totalAmt + "";
            m8402 = new M8402(vchset, str);
            m8402.setTLRNUM(tlrnum);
            SOFForm form = dataExchangeService.callSbsTxn("8402", m8402).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfo("´«Æ±Ì×Æ½³É¹¦£º");

            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401´«Æ±Ì×Æ½Ê§°Ü", e);
            MessageUtil.addError("8401´«Æ±Ì×Æ½Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        onBatchQry();
        initAddBat();
        return null;
    }

    //Ì×É¾³ý
    public String onDeleteVchset() {
        try {
            M8409 m8409 = new M8409(vchset);
            m8409.setFUNCDE("0");    //0Ì×É¾³ý 1 µ¥±ÊÉ¾³ý
            SOFForm form = dataExchangeService.callSbsTxn("8409", m8409).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfo("Ì×Æ± É¾³ý³É¹¦£º");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("Ì×Æ±É¾³ýÊ§°Ü", e);
            MessageUtil.addError("Ì×Æ±É¾³ýÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        onBatchQry();
        return null;
    }

    //µ¥±ÊÉ¾³ý
    public void onDeleteRecord() {
        try {
            M8409 m8409 = new M8409(vchset, setseq);
            m8409.setFUNCDE("1");    //0Ì×É¾³ý 1 µ¥±ÊÉ¾³ý
            SOFForm form = dataExchangeService.callSbsTxn("8409", m8409).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfo("Ì×Æ±µ¥±ÊÉ¾³ý³É¹¦£º");
                flushTotalData();
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("Ì×Æ±µ¥±ÊÉ¾³ýÊ§°Ü", e);
            MessageUtil.addError("Ì×Æ±µ¥±ÊÉ¾³ýÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    //µ¥±ÊÏêÏ¸ÐÞ¸Ä
    public String onEditRecord() {
        return null;
    }

    //Ì×ºÅÐÞ¸Ä
    public String onModifyVchset() {
        try {
            M85a2 m85a2 = new M85a2(vchset);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("85a2", m85a2);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T898".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t898 = (T898) form.getFormBody();
                        dataList.addAll(t898.getBeanList());
                        tlrnum = t898.getFormBodyHeader().getTLRNUM();
                        vchset = t898.getFormBodyHeader().getVCHSET();
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        // MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }

            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("Ã»ÓÐ²éÑ¯µ½Êý¾Ý¡£");
            }

        } catch (Exception e) {
            logger.error("²éÑ¯Ê§°Ü", e);
            MessageUtil.addError("²éÑ¯Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //=============================================================================

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getVchset() {
        return vchset;
    }

    public void setVchset(String vchset) {
        this.vchset = vchset;
    }

    public List<T898.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T898.Bean> dataList) {
        this.dataList = dataList;
    }

    public T898 getT898() {
        return t898;
    }

    public void setT898(T898 t898) {
        this.t898 = t898;
    }

    public M8401 getM8401() {
        return m8401;
    }

    public void setM8401(M8401 m8401) {
        this.m8401 = m8401;
    }

    public M8402 getM8402() {
        return m8402;
    }

    public void setM8402(M8402 m8402) {
        this.m8402 = m8402;
    }

    public float getTotalDebitAmt() {
        return totalDebitAmt;
    }

    public void setTotalDebitAmt(float totalDebitAmt) {
        this.totalDebitAmt = totalDebitAmt;
    }

    public float getTotalCreditAmt() {
        return totalCreditAmt;
    }

    public void setTotalCreditAmt(float totalCreditAmt) {
        this.totalCreditAmt = totalCreditAmt;
    }

    public float getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(float totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getSetseq() {
        return setseq;
    }

    public void setSetseq(String setseq) {
        this.setseq = setseq;
    }

    public String getTlrnum() {
        return tlrnum;
    }

    public void setTlrnum(String tlrnum) {
        this.tlrnum = tlrnum;
    }

    public T898[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(T898[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }
}


