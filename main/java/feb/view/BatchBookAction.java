package feb.view;

import feb.print.model.Vchset;
import feb.service.DataExchangeService;
import feb.service.TemVchPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T898;
import gateway.sbs.txn.model.msg.M8401;
import gateway.sbs.txn.model.msg.M8402;
import gateway.sbs.txn.model.msg.M8409;
import gateway.sbs.txn.model.msg.M85a2;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.DateUtil;
import pub.tools.MessageUtil;
import pub.tools.SystemDate;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @ManagedProperty(value = "#{temVchPrintService}")
    private TemVchPrintService temVchPrintService;

    private String vchset;//��Ʊ�׺�
    private String setseq;//�������
    private String tlrnum;//��Ա��
    private String totnum;//�ܱ���
    private String sysdat;//����
    private String txntim;//ʱ��
    //--------------------------------------
    private String actnum;//ACTNUM�˺�
    private String txnamt;//TXNAMT���
    private String rvslbl;//RVSLBL������־
    private String valdat;//VALDAT��Ϣ��
    private String anacde;//ANACDEͳ����
    private String furinf;//FURINFժҪ

    //--------------------------------------
    private M8401 m8401 = new M8401();
    private T898 t898 = new T898();
    private T898.Bean[] selectedRecords;
    private M8402 m8402 = new M8402();
    private List<T898.Bean> dataList = new ArrayList<>();
    private List<T898.Bean> allList = new ArrayList<>();
    private double totalDebitAmt;    //�跽
    private double totalCreditAmt;   //����
    private double totalAmt;         //����
    private boolean printable = false;
    SystemDate systemDate = new SystemDate();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        vchset = StringUtils.isEmpty(params.get("vchset")) ? "0000" : params.get("vchset");
        setseq = params.get("setseq");

        tlrnum = SkylineService.getOperId();//============>�õ���ǰ��Ա��
        sysdat =new SimpleDateFormat("yyyy/MM/dd").format(systemDate.getSysdate2());//sbsʱ��
        onBatchQry();  // ��ʼ����ѯ
        initAddBat();
    }

    /*
     * String date = new SimpleDateFormat("yyyyMMdd").format(new Date())
     */
    //¼���ʼ��
    public void initAddBat() {
        m8401.setPRDCDE("VCH1");
        m8401.setORGID3("010");
        m8401.setTLRNUM(tlrnum);
        m8401.setVCHSET(vchset);
        m8401.setRVSLBL("12");
        //m8401.setOPNDA2(new SimpleDateFormat("yyyyMMdd").format(new Date()));//��ǰ����
        m8401.setOPNDA2(systemDate.getSysdate1());//sbs����
    }

    //-------------------event�ж�----------------------------
    public String actEvent() {
        if (m8401.getACTNUM().length() == 14 && m8401.getACTNUM().matches("[0-9]+")) {
            return null;
        } else {
            logger.info("����ȷ����14λ�˺�");
            MessageUtil.addWarn("����ȷ����14λ�˺�");
        }
        return null;
    }

    public String txnEvent() {
        if (m8401.getTXNAMT().matches("^[-+]?([0-9]+)")) {
            txnamt = m8401.getTXNAMT();
            BigDecimal bd100 = new BigDecimal("100");
            DecimalFormat df = new DecimalFormat("###,###,##0.00");
            m8401.setTXNAMT(df.format(new BigDecimal(m8401.getTXNAMT()).divide(bd100)));
            return null;
        } else {
            MessageUtil.addWarn("���Ϸ���ֻ����-+������ ");
        }
        return null;
    }

    public void onPrint() {
        try {
            List<Vchset> vchs = new ArrayList<>();
            int printCnt = 0;
            for (T898.Bean bean : dataList) {
                if (!StringUtils.isEmpty(bean.getACTNUM()) || !StringUtils.isEmpty(bean.getTXNAMT())) {
                    printCnt++;
                    Vchset vch = new Vchset();
                    BeanHelper.copyFields(bean, vch);
                    DecimalFormat df = new DecimalFormat("###,###,##0.00");
                    vch.setTXNAMT(df.format(new BigDecimal(bean.getTXNAMT())));
                    vchs.add(vch);
                }
            }
            txntim = DateUtil.getCurrentTime();//ϵͳʱ��
            temVchPrintService.printVch( vchset, sysdat, txntim,tlrnum,vchs);
        } catch (Exception e) {
            logger.error("��ӡʧ��", e);
            MessageUtil.addError("��ӡʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
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
                        if (!allList.get(0).getSETSEQ().equals("")) {
                            for (T898.Bean bean : allList) {
                                if (!bean.getRECSTS().equals("I")) {
                                    bean.setTMPAMT(new BigDecimal(bean.getTXNAMT()));
                                    /*DecimalFormat df = new DecimalFormat("###,###,##0.00");
                                    bean.setTXNAMT(df.format(new BigDecimal(bean.getTXNAMT())));*/
                                    dataList.add(bean);
                                }
                            }
                        }
                        //tlrnum = t898.getFormBodyHeader().getTLRNUM();//��Ա��
                        vchset = t898.getFormBodyHeader().getVCHSET();
                        totnum = t898.getFormBodyHeader().getTOTNUM();//�ܱ���
                        flushTotalData();
                    } else if ("M922".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        MessageUtil.addWarn("����Ȩ�޲����ý��� !");
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                //MessageUtil.addWarn("û�в�ѯ�����ݡ�");
            }
        } catch (Exception e) {
            logger.error("��ѯʧ��", e);
            MessageUtil.addError("��ѯʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //��Ʊ¼��
    public String onCreateNewRecord() {
        int tmp = Integer.parseInt(totnum) + 1;
        String str = tmp + "";
        try {
            m8401.setSETSEQ(str);
            m8401.setVCHSET(vchset);
            m8401.setTXNAMT(txnamt);
            /*
            * ��ͨ��ajax�ύ ���ж�ժҪ�ֶ�ת�룬get�õ�utf-8��new Strת����java���б���utf-16
            */
            m8401.setFURINF(new String(m8401.getFURINF().getBytes("ISO-8859-1"),"UTF-8"));

            SOFForm form = dataExchangeService.callSbsTxn("8401", m8401).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                //MessageUtil.addInfo("��Ʊ¼��ɹ���");
                onModifyVchset();
                initAddBat();
                flushTotalData();
            } else if ("M402".equalsIgnoreCase(formcode)){
                MessageUtil.addWarnWithClientID("msgs", formcode);
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401��Ʊ¼��ʧ��", e);
            MessageUtil.addError("8401��Ʊ¼��ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //��������
    public void flushTotalData() {
        double amt = 0.0;
        totalDebitAmt = 0.0;
        totalCreditAmt = 0.0;
        totalAmt = 0.0;
        for (T898.Bean t898s : dataList) {
            amt = Double.parseDouble(t898s.getTXNAMT());
            if (amt > 0) {
                totalCreditAmt += amt;
            } else {
                totalDebitAmt += (-amt);
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
            if ("W001".equalsIgnoreCase(formcode) || "M124".equalsIgnoreCase(formcode)) {
                onPrint();
                onBatchQry();//�����Ӵ�ӡ��ť�������ڴ˴�ʹ��onModifyRecord()��ӡ֮���ٲ�ѯ����
                initAddBat();
                MessageUtil.addInfo("��Ʊ��ƽ�ɹ���");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401��Ʊ��ƽʧ��", e);
            MessageUtil.addError("8401��Ʊ��ƽʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
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
                onBatchQry();
                MessageUtil.addInfo("��Ʊɾ���ɹ���");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("��Ʊɾ��ʧ��", e);
            MessageUtil.addError("��Ʊɾ��ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }

        return null;
    }

    public String onClick() {
        return "batchBookBean";
    }

    public String onBack() {
        return "batchBookMng";
    }

    public String onModifyRecord() { //��Ʊ�����޸� =������ɾ�������
        Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        setseq = param.get("setseq");
        txnamt = param.get("txnamt");
        M8409 m8409 = new M8409(vchset, setseq);
        m8409.setFUNCDE("1");    //0��ɾ�� 1 ����ɾ��
        SOFForm form = dataExchangeService.callSbsTxn("8409", m8409).get(0);
        String formcode = form.getFormHeader().getFormCode();
        if ("W001".equalsIgnoreCase(formcode)) {
            //onModifyVchset();
            //flushTotalData();
        } else {
            MessageUtil.addErrorWithClientID("msgs", formcode);
        }
        double d = Double.parseDouble(txnamt) * 100;
        String str = "";
        if (d > 2147483646 || d < -2147483645) {
            str = d + "";  //ǰ̨���ÿ�ѧ��������ʾ2.147483647E9
        } else {
            int i = (int) (d);
            str = i + "";
        }
        // - - - - - - - - - - - - - - - - - -
        m8401.setACTNUM(param.get("actnum"));
        m8401.setTXNAMT(str);
        m8401.setRVSLBL(param.get("rvslbl"));
        m8401.setOPNDA2(param.get("valdat"));
        m8401.setANACDE(param.get("anacde"));
        m8401.setFURINF(param.get("furinf"));
        // - - - - - - - - - - - - - - - - - -
        return null;
    }

    //�׺��޸�
    public String onBoolVchset() {//�������⣺��Ʊ�޸�ʱ�׺ű�
        if (this.dataList.size() > 0) {
            MessageUtil.addError("����δ��ƽ��Ʊ...");
        } else {
            onModifyVchset();
        }
        return null;
    }

    public String onModifyVchset() {
        try {
            M85a2 m85a2 = new M85a2(vchset);
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
                                bean.setTMPAMT(new BigDecimal(bean.getTXNAMT()));//����ʽ��
                                dataList.add(bean);
                            }
                        }
                        tlrnum = t898.getFormBodyHeader().getTLRNUM();
                        vchset = t898.getFormBodyHeader().getVCHSET();
                        totnum = t898.getFormBodyHeader().getTOTNUM();//�ܱ���
                        flushTotalData();
                    } else {
                        if ("M319".equals(form.getFormHeader().getFormCode())) {
                            onBatchQry();
                        }
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addWarnWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("��ѯʧ��", e);
            //MessageUtil.addError("��ѯʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
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
        onModifyVchset();
        flushTotalData();
        return null;
    }
    //---------------------------���ɾ��-----------------------------------------------------
//    public String onAllConfirm() {ȫ��ɾ��
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
                M8409 m8409 = new M8409(vchset, bean.getSETSEQ());
                m8409.setFUNCDE("1");
                List<SOFForm> formList = dataExchangeService.callSbsTxn("8409", m8409);
                m8409.setFUNCDE("1");
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
            MessageUtil.addInfo("ɾ�������� " + cnt);

        } catch (Exception e) {
            logger.error("���ɾ��ʧ��", e);
            MessageUtil.addError("���ɾ���쳣.");
        }
        onModifyVchset();
        flushTotalData();
        return null;
    }
    /*
      ----------------------�õ���ǰ�û�������Ա�ţ�---------------------------------
      public static OperatorManager getOperatorManager() {
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) extContext.getSession(true);
            OperatorManager om = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
            if (om == null) {
                throw new RuntimeException("�û�δ��¼��");
            }
            System.out.println("===============================>"+ om.getOperatorName());
            return om;
        }
    */

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

    public double getTotalDebitAmt() {
        return totalDebitAmt;
    }

    public void setTotalDebitAmt(double totalDebitAmt) {
        this.totalDebitAmt = totalDebitAmt;
    }

    public double getTotalCreditAmt() {
        return totalCreditAmt;
    }

    public void setTotalCreditAmt(double totalCreditAmt) {
        this.totalCreditAmt = totalCreditAmt;
    }

    public double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(double totalAmt) {
        this.totalAmt = totalAmt;
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

    public String getActnum() {
        return actnum;
    }

    public void setActnum(String actnum) {
        this.actnum = actnum;
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

    public String getValdat() {
        return valdat;
    }

    public void setValdat(String valdat) {
        this.valdat = valdat;
    }

    public String getAnacde() {
        return anacde;
    }

    public void setAnacde(String anacde) {
        this.anacde = anacde;
    }

    public String getFurinf() {
        return furinf;
    }

    public void setFurinf(String furinf) {
        this.furinf = furinf;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
    }

    public TemVchPrintService getTemVchPrintService() {
        return temVchPrintService;
    }

    public void setTemVchPrintService(TemVchPrintService temVchPrintService) {
        this.temVchPrintService = temVchPrintService;
    }
}


