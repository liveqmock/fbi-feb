package inr.action;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T462;
import gateway.sbs.txn.model.msg.Maa05;
import gateway.sbs.txn.model.msg.Maa06;
import gateway.sbs.txn.model.msg.Maa41;
import inr.bean.PrintBean;
import inr.service.CommonService;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import pub.platform.MessageUtil;
import pub.tools.SystemDate;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class CommonAction implements Serializable {

    private static Logger logger = Logger.getLogger(CommonAction.class);

    @ManagedProperty(value = "#{commonService}")
    private CommonService commonService;

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private PrintBean printBean = new PrintBean();
    private String dysdate;
    private List<PrintBean> vochers;
    private PrintBean selectedVocher;

    private Maa05 maa05 = new Maa05();
    private Maa06 maa06 = new Maa06();
    private String fbtidx="";  //流水号
    private T462 t462;
    private List<T462.Bean> t426List = new ArrayList<>();
    private List<SelectItem> entInnerPapItems;      // 内转查询状态
    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;
    private Maa41 maa41;
    private List<PrintBean> dataList;
    private PrintBean[] selectedRecords;
    SystemDate systemDate = new SystemDate();
    private String bizdate = new SimpleDateFormat("yyyyMMdd").format(systemDate.getSysdate2());

    @PostConstruct
    public void init() {
        entInnerPapItems = skylineService.getEnuSelectItemList("CTF-PAPSTS", false, false);
    }
    /**
     * 导入到本地数据库
     */
    public void importToDB() {
        try {
            int num = commonService.importToLocalDB(bizdate);
            MessageUtil.addInfo("导入" + num + "条数据!");
        } catch (Exception e) {
            MessageUtil.addError("数据库连接失败!");
            logger.error(new Date().toString() + " 数据库连接失败!");
        }
    }

    /**
     * 统计数据
     */
    public void stasticData() {
        try {
            dataList = commonService.getPrintBeans(bizdate);
        } catch (DataAccessException e) {
            MessageUtil.addError("数据库连接失败!");
            logger.error(new Date().toString() + " 数据库连接失败!");
        }
    }

    /**
     * 作废
     */
    public void printVoucher(PrintBean bean) {
        try {
            bean.setTrflag(1);        //printdata中的字段是w06中的作废数据标志
            commonService.delVocherInfo(bean);
            dataList.remove(bean);
            MessageUtil.addInfo("执行成功!");
        } catch (DataAccessException e) {
            MessageUtil.addError("sql执行失败!");
            logger.error(new Date().toString() + " sql执行失败!");
        }
    }

    /**
     * 记账
     */
    public String onAllConfirm() {
        selectedRecords = dataList.toArray(selectedRecords);
        onMultiConfirm();
        return null;
    }

    public String onMultiConfirm() {
        if (selectedRecords == null || selectedRecords.length == 0) {
            MessageUtil.addWarn("至少选择一笔数据记录！");
            return null;
        }
        try {
            // 确认
            int cnt = 0;
            for (PrintBean bean : selectedRecords) {
                String amt = bean.getDebitAmt().toString();
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
                ///String bizdate = sdf.format(systemDate); //格式化业务日期
                maa41 = new Maa41("8010" + bean.getAcntCodeOne(), "8010" + bean.getAcntCodeTwo(), amt);
                maa41.setTXNDAT(bizdate);
                maa41.setREMARK(bean.getSerialnum1());
                maa41.setPASSNO("BI1" + bean.getSerialnum1());//票据编号
                List<SOFForm> formList = dataExchangeService.callSbsTxn("aa41", maa41);
                if (formList != null && !formList.isEmpty()) {
                    String formcode = formList.get(0).getFormHeader().getFormCode();
                    if ("T531".equals(formcode)) {
                        commonService.addVocherInfo(bean);
                        dataList.remove(bean);
                        cnt++;
                    } else {
                        MessageUtil.addErrorWithClientID("msgs", formcode);
                        break;
                    }
                } else {
                    MessageUtil.addError("SBS系统响应超时。");
                }
            }
            MessageUtil.addInfo("完成内转笔数： " + cnt);
        } catch (Exception e) {
            logger.error("批量内转失败", e);
            MessageUtil.addError("批量内转异常.");
        }
        return null;
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            PrintBean bean = (PrintBean) event.getObject();
            commonService.updateVochdata(bean);
            MessageUtil.addInfo("执行成功!");
        } catch (DataAccessException e) {
            MessageUtil.addError("sql执行失败!");
            logger.error(new Date().toString() + " sql执行失败!");
        }
    }

    /**
     * 记账查看页面信息检索
     */
    public void obtainVocherInfos() {
        try {
            vochers = commonService.obtainVocherInfos();
        } catch (DataAccessException e) {
            MessageUtil.addError("sql执行失败!");
            logger.error(new Date().toString() + " sql执行失败!");
        }
    }

    //内装记账撤销查询
    public String onQryInnvch(){
        try {
            if (!"".equals(maa06.getIPTAC1())){
                maa06.setIPTAC1("8010" + maa06.getIPTAC1());
            }
            if (!"".equals(maa06.getIPTAC2())){
                maa06.setIPTAC2("8010" + maa06.getIPTAC2());
            }
            maa06.setTXNDAT(bizdate);
            SOFForm form = dataExchangeService.callSbsTxn("aa06", maa06).get(0);
            String formcode = form.getFormHeader().getFormCode();
            t426List = new ArrayList<>();
            if ("T462".equals(formcode)) {
                t462 = (T462) form.getFormBody();
                t426List.addAll(t462.getBeanList());
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
    //撤销记账
    public String onRevokeVch(T462.Bean bean){
        try {
            maa05.setFBTIDX(bean.getFBTIDX());
            SOFForm form = dataExchangeService.callSbsTxn("aa05", maa05).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equals(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                t426List.remove(bean);
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("撤销失败", e);
            MessageUtil.addError("撤销失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
    // = = = = = = = = = = = = = get set = = = = = =  = = = = =  =

    public String getDysdate() {
        return dysdate;
    }

    public void setDysdate(String dysdate) {
        this.dysdate = dysdate;
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public PrintBean getPrintBean() {
        return printBean;
    }

    public void setPrintBean(PrintBean printBean) {
        this.printBean = printBean;
    }

    public List<PrintBean> getVochers() {
        return vochers;
    }

    public void setVochers(List<PrintBean> vochers) {
        this.vochers = vochers;
    }

    public PrintBean getSelectedVocher() {
        return selectedVocher;
    }

    public void setSelectedVocher(PrintBean selectedVocher) {
        this.selectedVocher = selectedVocher;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public Maa41 getMaa41() {
        return maa41;
    }

    public void setMaa41(Maa41 maa41) {
        this.maa41 = maa41;
    }

    public List<PrintBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<PrintBean> dataList) {
        this.dataList = dataList;
    }

    public PrintBean[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(PrintBean[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public void setBizdate(String bizdate) {
        this.bizdate = bizdate;
    }

    public String getBizdate() {
        return bizdate;
    }

    public Maa06 getMaa06() {
        return maa06;
    }

    public void setMaa06(Maa06 maa06) {
        this.maa06 = maa06;
    }

    public List<T462.Bean> getT426List() {
        return t426List;
    }

    public void setT426List(List<T462.Bean> t426List) {
        this.t426List = t426List;
    }

    public String getFbtidx() {
        return fbtidx;
    }

    public void setFbtidx(String fbtidx) {
        this.fbtidx = fbtidx;
    }

    public List<SelectItem> getEntInnerPapItems() {
        return entInnerPapItems;
    }

    public void setEntInnerPapItems(List<SelectItem> entInnerPapItems) {
        this.entInnerPapItems = entInnerPapItems;
    }

    public SkylineService getSkylineService() {
        return skylineService;
    }

    public void setSkylineService(SkylineService skylineService) {
        this.skylineService = skylineService;
    }

    public Maa05 getMaa05() {
        return maa05;
    }

    public void setMaa05(Maa05 maa05) {
        this.maa05 = maa05;
    }

    public T462 getT462() {
        return t462;
    }

    public void setT462(T462 t462) {
        this.t462 = t462;
    }
}


