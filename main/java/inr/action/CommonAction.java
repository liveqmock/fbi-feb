package inr.action;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.msg.Maa41;
import inr.bean.PrintBean;
import inr.service.CommonService;
import org.apache.log4j.Logger;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import pub.platform.MessageUtil;
import pub.tools.SystemDate;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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

    Maa41 maa41;
    private List<PrintBean> dataList;
    private PrintBean[] selectedRecords;
    SystemDate systemDate = new SystemDate();
    private String bizdate = new SimpleDateFormat("yyyyMMdd").format(systemDate.getSysdate2());

    /**
     * 导入到本地数据库
     */
    public void importToDB() {
        try {
            int num = commonService.importToLocalDB();
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
            bean.setTrflag(1);
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
                maa41.setPASSNO("feb"+bizdate + bean.getIdOne());//当前日期+print表的idone
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
}


