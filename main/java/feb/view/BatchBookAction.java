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
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ecs.html.Big;
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
import java.lang.reflect.InvocationTargetException;
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
 * Time: 下午12:50
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

    private String vchset;//传票套号
    private String vchsetTmp ="0000";//传票套号
    private String setseq;//套内序号
    private String tlrnum;//柜员号
    private String totnum;//总笔数
    private String sysdat;//日期
    private String txntim;//时间
    //--------------------------------------
    private String actnum;//ACTNUM账号
    private String txnamt;//TXNAMT金额
    private String rvslbl;//RVSLBL冲正标志
    private String valdat;//VALDAT起息日
    private String anacde;//ANACDE统计码
    private String furinf;//FURINF摘要

    //--------------------------------------
    private M8401 m8401 = new M8401();
    private M8409 m8409 = new M8409();
    private T898 t898 = new T898();
    private List<T898.Bean> selectedRecords;
    private M8402 m8402 = new M8402();
    private M85a2 m85a2 = new M85a2();
    private List<T898.Bean> dataList = new ArrayList<>();
    private List<T898.Bean> allList = new ArrayList<>();
    private BigDecimal totalDebitAmt;    //借方
    private BigDecimal totalCreditAmt;   //贷方
    private BigDecimal totalAmt;         //轧差
    private boolean printable = false;
    SystemDate systemDate = new SystemDate();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        vchset = StringUtils.isEmpty(params.get("vchset")) ? "0000" : params.get("vchset");
        setseq = params.get("setseq");

        tlrnum = SkylineService.getOperId();//============>得到当前柜员号
        sysdat =new SimpleDateFormat("yyyy/MM/dd").format(systemDate.getSysdate2());//sbs时间
        onBatchQry("0000");  // 初始化查询
        initAddBat();
    }

    /*
     * String date = new SimpleDateFormat("yyyyMMdd").format(new Date())
     */
    //录入初始化
    public void initAddBat() {
        m8401.setPRDCDE("VCH1");
        m8401.setORGID3("010");
        m8401.setTLRNUM(tlrnum);
        m8401.setVCHSET(vchset);
        m8401.setRVSLBL("12");
        //m8401.setOPNDA2(new SimpleDateFormat("yyyyMMdd").format(new Date()));//当前日期
        m8401.setOPNDA2(systemDate.getSysdate1());//sbs日期
    }

    //-------------------event判断----------------------------
    public String actEvent() {
        if (m8401.getACTNUM().length() == 14 && m8401.getACTNUM().matches("[0-9]+")) {
            return null;
        } else {
            logger.info("请正确输入14位账号");
            MessageUtil.addWarn("请正确输入14位账号");
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
            MessageUtil.addWarn("金额不合法，只包含-+与数字 ");
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
            txntim = DateUtil.getCurrentTime();//系统时间
            temVchPrintService.printVch( vchset, sysdat, txntim,tlrnum,vchs);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    //套票查询
    public String onBatchQry(String tmp) {
        try {
            m85a2.setVCHSET(tmp);
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
                        //tlrnum = t898.getFormBodyHeader().getTLRNUM();//柜员号
                        vchset = t898.getFormBodyHeader().getVCHSET();
                        totnum = t898.getFormBodyHeader().getTOTNUM();//总笔数
                        flushTotalData();
                    } else if ("M922".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        MessageUtil.addWarn("您无权限操作该交易 !");
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                //MessageUtil.addWarn("没有查询到数据。");
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //传票录入
    public void onCreateNewRecord() {
        int tmp = Integer.parseInt(totnum) + 1;
        String str = tmp + "";
        try {
            m8401.setSETSEQ(str);
            m8401.setVCHSET(vchset);
            m8401.setTXNAMT(txnamt);
            /*
            * 不通过ajax提交 自行对摘要字段转码，get得到utf-8，new Str转换成java运行编码utf-16
            */
            //m8401.setFURINF(new String(m8401.getFURINF().getBytes("ISO-8859-1"),"UTF-8"));
            SOFForm form = dataExchangeService.callSbsTxn("8401", m8401).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                //MessageUtil.addInfo("传票录入成功：");
                onBatchQry(vchset);
                initAddBat();
                flushTotalData();
            } else if ("M402".equalsIgnoreCase(formcode)){
                MessageUtil.addWarnWithClientID("msgs", formcode);
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401传票录入失败", e);
            MessageUtil.addError("8401传票录入失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    //计算轧差
    public void flushTotalData() {
        BigDecimal amt ;
        totalDebitAmt = new BigDecimal("0");
        totalCreditAmt = new BigDecimal("0");
        totalAmt = new BigDecimal("0");
        for (T898.Bean t898s : dataList) {
            amt = new BigDecimal(t898s.getTXNAMT());
            if (amt.compareTo(new BigDecimal("0"))>0) {
                if ("22".equals(t898s.getRVSLBL())){
                    totalCreditAmt = totalCreditAmt.subtract(amt);
                }else {
                    totalCreditAmt = totalCreditAmt.add(amt);
                }

            } else {
                if ("22".equals(t898s.getRVSLBL())){
                    totalDebitAmt = totalDebitAmt.add(amt);
                }else {
                    totalDebitAmt = totalDebitAmt.subtract(amt);
                }

            }
        }
        totalAmt = totalCreditAmt.subtract(totalDebitAmt);
    }

    //套平
    public String onBalanceAct() {
        try {
            String str = totalAmt + "";
            m8402 = new M8402(vchset, str);
            m8402.setTLRNUM(tlrnum);
            SOFForm form = dataExchangeService.callSbsTxn("8402", m8402).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode) || "M124".equalsIgnoreCase(formcode)) {
                onPrint();
                //onBatchQry();//如果添加打印按钮，可以在此处使用onModifyRecord()打印之后再查询最新
                //initAddBat();
                MessageUtil.addInfo("传票套平成功：");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401传票套平失败", e);
            MessageUtil.addError("8401传票套平失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }


    //套删除
    public String onDeleteVchset() {
        try {
            M8409 m8409 = new M8409(vchset);
            m8409.setFUNCDE("0");    //0套删除 1 单笔删除
            SOFForm form = dataExchangeService.callSbsTxn("8409", m8409).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                onBatchQry("0000");
                MessageUtil.addInfo("套票删除成功：");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("套票删除失败", e);
            MessageUtil.addError("套票删除失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }

        return null;
    }

    public String onClick() {
        return "batchBookBean";
    }

    public String onBack() {
        return "batchBookMng";
    }

    public void onModifyRecord(T898.Bean bean){//套票单笔修改 ==>单笔删除再添加
        try {
            m8409.setVCHSET(vchset);
            m8409.setSETSEQ(bean.getSETSEQ());
            m8409.setFUNCDE("1");    //0套删除 1 单笔删除
            SOFForm form = dataExchangeService.callSbsTxn("8409", m8409).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                BeanUtils.copyProperties(m8401,bean);
                txnamt = bean.getTXNAMT();
            } else {
                logger.error("传票单笔修改删除失败！");
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //套号修改
    public String onBoolVchset() {
        if (this.dataList.size() > 0) {//存在问题：传票修改时套号变
            MessageUtil.addError("存在未套平传票...");
        } else {
            onBatchQry(vchsetTmp);
        }
        return null;
    }


    //单笔删除
    public String onDeleteRecord(T898.Bean bean) {
        setseq = bean.getSETSEQ();
        try {
            M8409 m8409 = new M8409(vchset, setseq);
            m8409.setFUNCDE("1");    //0套删除 1 单笔删除
            SOFForm form = dataExchangeService.callSbsTxn("8409", m8409).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfo("套票单笔删除成功.");
                dataList.remove(bean);
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("套票单笔删除失败", e);
            MessageUtil.addError("套票单笔删除失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        flushTotalData();
        return null;
    }
    //---------------------------多笔删除-----------------------------------------------------
//    public String onAllConfirm() {全部删除
//        selectedRecords = dataList.toArray(selectedRecords);
//        onMultiConfirm();
//        return null;
//    }

    public String onMultiConfirm() {
        if (selectedRecords == null || selectedRecords.size() == 0) {
            MessageUtil.addWarn("至少选择一笔数据记录！");
            return null;
        }
        try {
            // 确认
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
                    MessageUtil.addError("SBS系统响应超时。");
                }
            }
            MessageUtil.addInfo("删除笔数： " + cnt);
            dataList.removeAll(selectedRecords);
        } catch (Exception e) {
            logger.error("多笔删除失败", e);
            MessageUtil.addError("多笔删除异常.");
        }
        flushTotalData();
        return null;
    }
    /*
      ----------------------得到当前用户名（柜员号）---------------------------------
      public static OperatorManager getOperatorManager() {
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) extContext.getSession(true);
            OperatorManager om = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
            if (om == null) {
                throw new RuntimeException("用户未登录！");
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

    public String getVchsetTmp() {
        return vchsetTmp;
    }

    public void setVchsetTmp(String vchsetTmp) {
        this.vchsetTmp = vchsetTmp;
    }

    public M8409 getM8409() {
        return m8409;
    }

    public void setM8409(M8409 m8409) {
        this.m8409 = m8409;
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

    public BigDecimal getTotalDebitAmt() {
        return totalDebitAmt;
    }

    public void setTotalDebitAmt(BigDecimal totalDebitAmt) {
        this.totalDebitAmt = totalDebitAmt;
    }

    public BigDecimal getTotalCreditAmt() {
        return totalCreditAmt;
    }

    public void setTotalCreditAmt(BigDecimal totalCreditAmt) {
        this.totalCreditAmt = totalCreditAmt;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
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

    public List<T898.Bean> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<T898.Bean> selectedRecords) {
        this.selectedRecords = selectedRecords;
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


