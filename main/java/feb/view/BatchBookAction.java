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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-29
 * Time: ����12:50
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class BatchBookAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(BatchBookAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String vchset;//��Ʊ�׺�
    private String setseq;//�������
    private String tlrnum;//��Ա��
    private String totnum;//�ܱ���

    private String actnum;         // �˺�
    private String prdcde;          //��Ʒ��
    private String txnamt;          //���
    private String rvslbl;          //������־
    private String opnda2;          //��������
    private String erytyp;          //��Ʊ���뷽ʽ
    private String erydat;           //��������
    private M8401 m8401 = new M8401();
    private T898 t898 = new T898();
    private T898.Bean[] selectedRecords;
    private M8402 m8402 = new M8402();
    //private M8409 m8409 = new M8409();
    private List<T898.Bean> dataList = new ArrayList<>();
    private List<T898.Bean> allList = new ArrayList<>();
    private float totalDebitAmt;    //�跽
    private float totalCreditAmt;   //����
    private float totalAmt;         //����

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        vchset = StringUtils.isEmpty(params.get("vchset")) ? "0000" : params.get("vchset");
        setseq = params.get("setseq");
        if (!StringUtils.isEmpty(vchset)) {
            M85a2 m85a2 = new M85a2(vchset);
            SOFForm form = dataExchangeService.callSbsTxn("85a2", m85a2).get(0);
            if ("T898".equals(form.getFormHeader().getFormCode())) {
                t898 = (T898) form.getFormBody();
            } else {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            }
            onBatchQry();  // ��ʼ����ѯ
            for (T898.Bean bean : allList) {
                if (bean.getSETSEQ().equals(setseq)) {
                    actnum = bean.getACTNUM();
                    prdcde = bean.getPRDCDE();
                    txnamt = bean.getTXNAMT();
                    rvslbl = bean.getRVSLBL();
                    erydat = bean.getERYDAT();
                    erytyp = bean.getERYTYP();
                    opnda2 = bean.getVALDAT();
                }
            }
            //initAddBat();
        }
    }

    //¼���ʼ��
    public void initAddBat() {
        M8401 m8401 = new M8401();
        m8401.setTLRNUM(tlrnum);
        m8401.setVCHSET(vchset);
        m8401.setPRDCDE("VCH1");
        m8401.setORGID3("010");
        m8401.setFUNCDE("1");
        m8401.setERYTYP("0");
    }

    //��Ʊ��ѯ
    public String onBatchQry() {
        try {
            M85a2 m85a2 = new M85a2("0000");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("85a2", m85a2);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                allList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T898".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t898 = (T898) form.getFormBody();
                        allList.addAll(t898.getBeanList());
                        for (T898.Bean bean : allList) {
                            if (!bean.getRECSTS().equals("I")) {
                                dataList.add(bean);
                            }
                        }
                        tlrnum = t898.getFormBodyHeader().getTLRNUM();
                        vchset = t898.getFormBodyHeader().getVCHSET();
                        totnum = t898.getFormBodyHeader().getTOTNUM();
                        flushTotalData();
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        // MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("û�в�ѯ�����ݡ�");
            }
        } catch (Exception e) {
            logger.error("��ѯʧ��", e);
            MessageUtil.addError("��ѯʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //��Ʊ¼��
    public String onCreateNewRecord() {
        try {
            m8401.setVCHSET(vchset);
            SOFForm form = dataExchangeService.callSbsTxn("8401", m8401).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {

                //MessageUtil.addInfo("��Ʊ¼��ɹ���");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401��Ʊ¼��ʧ��", e);
            MessageUtil.addError("8401��Ʊ¼��ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        onBatchQry();
        initAddBat();
        flushTotalData();
        return null;
    }

    //��������
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

    //��ƽ
    public String onBalanceAct() {
        try {
            String str = totalAmt + "";
            m8402 = new M8402(vchset, str);
            m8402.setTLRNUM(tlrnum);
            SOFForm form = dataExchangeService.callSbsTxn("8402", m8402).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {

                MessageUtil.addInfo("��Ʊ��ƽ�ɹ���");
            } else {

                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401��Ʊ��ƽʧ��", e);
            MessageUtil.addError("8401��Ʊ��ƽʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        onBatchQry();
        initAddBat();
        return null;
    }

    //��ɾ��
    public String onDeleteVchset() {
        try {
            M8409 m8409 = new M8409(vchset);
            m8409.setFUNCDE("0");    //0��ɾ�� 1 ����ɾ��
            SOFForm form = dataExchangeService.callSbsTxn("8409", m8409).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfo("��Ʊ ɾ���ɹ���");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("��Ʊɾ��ʧ��", e);
            MessageUtil.addError("��Ʊɾ��ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        onBatchQry();
        return null;
    }

    public String onClick() {
        return "batchBookBean";
    }

    public String onBack() {
        return "batchBookMng";
    }

    //������ϸ�޸�
    public String onEditRecord() {
        try {

            m8401.setVCHSET(vchset);
            m8401.setSETSEQ(setseq);
            SOFForm form = dataExchangeService.callSbsTxn("8401", m8401).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                onDeleteRecord();
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401��Ʊ�޸�ʧ��", e);
            MessageUtil.addError("8401��Ʊ�޸�ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //�׺��޸�
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
                        totnum = t898.getFormBodyHeader().getTOTNUM();
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        // MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }

            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("û�в�ѯ�����ݡ�");
            }

        } catch (Exception e) {
            logger.error("��ѯʧ��", e);
            MessageUtil.addError("��ѯʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //����ɾ��
    public String onDeleteRecord() {
        Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        setseq = param.get("setseq");
        try {
            M8409 m8409 = new M8409(vchset, setseq);
            m8409.setFUNCDE("1");    //0��ɾ�� 1 ����ɾ��
            SOFForm form = dataExchangeService.callSbsTxn("8409", m8409).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfo("��Ʊ����ɾ���ɹ���");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("��Ʊ����ɾ��ʧ��", e);
            MessageUtil.addError("��Ʊ����ɾ��ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        onBatchQry();
        flushTotalData();
        return null;
    }
    //---------------------------���ɾ��-----------------------------------------------------
//    public String onAllConfirm() {
//        selectedRecords = dataList.toArray(selectedRecords);
//        onMultiConfirm();
//        return null;
//    }

    public String onMultiConfirm() {
        if (selectedRecords == null || selectedRecords.length == 0) {
            MessageUtil.addWarn("����ѡ��һ�����ݼ�¼��");
            return null;
        }
        try {
            // ȷ��
            int cnt = 0;
            for (T898.Bean bean : selectedRecords) {
                M8409 m8409 = new M8409(vchset,bean.getSETSEQ());
                m8409.setFUNCDE("1");
                List<SOFForm> formList = dataExchangeService.callSbsTxn("8409",m8409);
                if (formList != null && !formList.isEmpty()) {
                    String formcode = formList.get(0).getFormHeader().getFormCode();
                    if (!"W001".equals(formcode)) {
                        MessageUtil.addErrorWithClientID("msgs", formcode);
                        break;
                    } else cnt++;
                } else {
                    MessageUtil.addError("SBSϵͳ��Ӧ��ʱ��");
                }
            }
            MessageUtil.addInfo("���ȷ�ϱ����� " + cnt);
            onBatchQry();
        } catch (Exception e) {
            logger.error("���ɾ��ʧ��", e);
            MessageUtil.addError("���ɾ���쳣.");
        }
        return null;
    }

    //=================================================================================
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

    public void setSelectedRecords(T898.Bean[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public T898.Bean[] getSelectedRecords() {
        return selectedRecords;
    }

    public String getTotnum() {
        return totnum;
    }

    public void setTotnum(String totnum) {
        this.totnum = totnum;
    }

    public String getPrdcde() {
        return prdcde;
    }

    public void setPrdcde(String prdcde) {
        this.prdcde = prdcde;
    }

    public String getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(String txnamt) {
        this.txnamt = txnamt;
    }

    public String getRvslbl() {
        return rvslbl;
    }

    public void setRvslbl(String rvslbl) {
        this.rvslbl = rvslbl;
    }

    public String getActnum() {
        return actnum;
    }

    public void setActnum(String actnum) {
        this.actnum = actnum;
    }

    public String getOpnda2() {
        return opnda2;
    }

    public void setOpnda2(String opnda2) {
        this.opnda2 = opnda2;
    }

    public String getErytyp() {
        return erytyp;
    }

    public void setErytyp(String erytyp) {
        this.erytyp = erytyp;
    }

    public String getErydat() {
        return erydat;
    }

    public void setErydat(String erydat) {
        this.erydat = erydat;
    }
}


