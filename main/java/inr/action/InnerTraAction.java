package inr.action;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.msg.Maa41;
import inr.bean.PrintBean;
import inr.service.CommonService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import pub.platform.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/8/15
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class InnerTraAction implements Serializable {
    private static Logger logger = Logger.getLogger(InnerTraAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{commonService}")
    private CommonService commonService;

    Maa41 maa41;
    private List<PrintBean> dataList;
    private PrintBean[] selectedRecords;

    @PostConstruct
    public void  init(){
        obtainVocherInfos();
    }

    /**
     * 记账页面信息检索
     */
    public void obtainVocherInfos() {
        try {
            dataList = commonService.obtainVocherInfos();
            /*for (PrintBean bean : vochers){
            }*/
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
                String bizdate = sdf.format(bean.getBizDate()); //格式化业务日期
                maa41 = new Maa41("8010"+bean.getAcntCodeOne(),"8010"+bean.getAcntCodeTwo(),amt);
                maa41.setTXNDAT(bizdate);
                maa41.setPASSNO(bizdate+bean.getIdOne());//当前日期+print表的idone
                List<SOFForm> formList = dataExchangeService.callSbsTxn("aa41", maa41);
                if (formList != null && !formList.isEmpty()) {
                    String formcode = formList.get(0).getFormHeader().getFormCode();
                    if ("T531".equals(formcode)) {
                        commonService.updatePrintdata(bean);   //更新printdata表
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


    /**
     * 添加前台页面显示内容
     *
     * @param msgStr
     */
    public void addMessage(FacesMessage.Severity severity, String msgStr) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msgStr, msgStr));
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
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
}
