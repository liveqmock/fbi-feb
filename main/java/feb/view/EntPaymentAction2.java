package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T549;
import gateway.sbs.txn.model.msg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对公支付查询、确认、撤销
 */
@ManagedBean
@ViewScoped
public class EntPaymentAction2 implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;
    private List<SelectItem> entPaymentStsItems;      // 对公支付状态
    private Map<String, String> entPaymentPrcsts;     // 对公支付交易状态
    private Mn039 mn039;
    private List<T549.Bean> dataList;
    private T549.Bean[] selectedRecords;
    private T549 t549 = new T549();

    @PostConstruct
    public void init() {
        entPaymentStsItems = skylineService.getEnuSelectItemList("CTF-PRCSTS", false, false);
        entPaymentPrcsts = skylineService.getEnuSelectMap("CTF-PRCSTS");
        mn039 = new Mn039(skylineService.getSysdate8(), "1", "1");

    }

    public String onMultiQuery() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("n039", mn039);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if (!"T549".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T549".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t549 = (T549) form.getFormBody();
                        t549.setCurcnt(Integer.parseInt(t549.getCurcnt()) + "");
                        t549.setTotcnt(Integer.parseInt(t549.getTotcnt()) + "");
//                        BigDecimal bd100 = new BigDecimal("100");
//                        DecimalFormat df = new DecimalFormat("###,###,##0.00");
                        dataList = t549.getBeanList();
                       /* for (T536.Bean record : t536.getBeanList()) {
                            record.setTXNAMT(df.format(new BigDecimal(record.getTXNAMT()).divide(bd100)));
                            dataList.add(record);
                        }*/
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
//                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("没有查询到数据。");
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

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
            for (T549.Bean bean : selectedRecords) {
                List<SOFForm> formList = dataExchangeService.callSbsTxn("n038", new Mn038(bean.getFBTIDX()));
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
            MessageUtil.addInfo("完成确认笔数： " + cnt);
            onMultiQuery();
        } catch (Exception e) {
            logger.error("对公支付确认失败", e);
            MessageUtil.addError("对公支付确认异常.");
        }
        return null;
    }

    // ------------------------------------


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public SkylineService getSkylineService() {
        return skylineService;
    }

    public void setSkylineService(SkylineService skylineService) {
        this.skylineService = skylineService;
    }

    public List<SelectItem> getEntPaymentStsItems() {
        return entPaymentStsItems;
    }

    public void setEntPaymentStsItems(List<SelectItem> entPaymentStsItems) {
        this.entPaymentStsItems = entPaymentStsItems;
    }

    public Map<String, String> getEntPaymentPrcsts() {
        return entPaymentPrcsts;
    }

    public void setEntPaymentPrcsts(Map<String, String> entPaymentPrcsts) {
        this.entPaymentPrcsts = entPaymentPrcsts;
    }

    public Mn039 getMn039() {
        return mn039;
    }

    public void setMn039(Mn039 mn039) {
        this.mn039 = mn039;
    }

    public List<T549.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T549.Bean> dataList) {
        this.dataList = dataList;
    }

    public T549.Bean[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(T549.Bean[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public T549 getT549() {
        return t549;
    }

    public void setT549(T549 t549) {
        this.t549 = t549;
    }
}
