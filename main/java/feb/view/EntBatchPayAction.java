package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T536;
import gateway.sbs.txn.model.form.T547;
import gateway.sbs.txn.model.msg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import pub.tools.MessageUtil;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 批量代发查询、确认、撤销
 */
@ManagedBean
@ViewScoped
public class EntBatchPayAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;
    private List<SelectItem> entPaymentStsItems;      // 批量代发状态
    private Map<String, String> entPaymentPrcsts;     // 批量代发交易状态
    private Mn046 mn046;
    private List<T536.Bean> dataList;
    private T536.Bean[] selectedRecords;
    private T547 t547 = new T547();
    private T536 t536 = new T536();
    private String orddat;
    private String action;
    private String prcsts;
    private String srcpage;

    @PostConstruct
    public void init() {
        entPaymentStsItems = skylineService.getEnuSelectItemList("CTF-PRCSTS", false, false);
        entPaymentPrcsts = skylineService.getEnuSelectMap("CTF-PRCSTS");
        mn046 = new Mn046(skylineService.getSysdate8(), "1", "1");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        prcsts = params.get("prcsts");
        orddat = params.get("orddat");
        srcpage = params.get("srcpage");
        if (!StringUtils.isEmpty(action)) {
            if ("query".equals(action)) {
                String fbtidx = params.get("fbtidx");
                List<SOFForm> formList = dataExchangeService.callSbsTxn("n047", new Mn047(fbtidx, orddat));
                if (formList != null && !formList.isEmpty()) {
                    SOFForm form = formList.get(0);
                    if (!"T547".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    } else {
                        t547 = (T547) form.getFormBody();
                    }
                }
            } else if ("queryAll".equals(action)) {
                mn046 = new Mn046(orddat, prcsts, "1");
                onMultiQuery();
            }
        } else {
            // do nothing
        }
    }

    public String onMultiQuery() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("n046", mn046);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if (!"T536".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T536".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t536 = (T536) form.getFormBody();
                        t536.setCurcnt(Integer.parseInt(t536.getCurcnt()) + "");
                        t536.setTotcnt(Integer.parseInt(t536.getTotcnt()) + "");
                        dataList = t536.getBeanList();
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
            for (T536.Bean bean : selectedRecords) {
                List<SOFForm> formList = dataExchangeService.callSbsTxn("n048", new Mn048(bean.getFBTIDX()));
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
            logger.error("批量代发确认失败", e);
            MessageUtil.addError("批量代发确认异常.");
        }
        return null;
    }

    public String onAllCancel() {
        selectedRecords = dataList.toArray(selectedRecords);
        onMultiCancel();
        return null;
    }

    public String onMultiCancel() {
        if (selectedRecords == null || selectedRecords.length == 0) {
            MessageUtil.addWarn("至少选择一笔数据记录！");
            return null;
        }
        try {
            // 确认
            int cnt = 0;
            for (T536.Bean bean : selectedRecords) {
                List<SOFForm> formList = dataExchangeService.callSbsTxn("n045", new Mn045(bean.getFBTIDX()));
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
            MessageUtil.addInfo("完成撤销笔数： " + cnt);
            onMultiQuery();
        } catch (Exception e) {
            logger.error("批量代发撤销失败", e);
            MessageUtil.addError("批量代发撤销异常.");
        }
        return null;
    }

    public String onSingleQuery() {
        return "entBatchPayBean";
    }

    public String onBack() {
        return srcpage + "?faces-redirect=true&action=queryAll&orddat=" + orddat + "&prcsts=" + prcsts;
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

    public Mn046 getMn046() {
        return mn046;
    }

    public void setMn046(Mn046 mn046) {
        this.mn046 = mn046;
    }

    public List<T536.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T536.Bean> dataList) {
        this.dataList = dataList;
    }


    public T547 getT547() {
        return t547;
    }

    public void setT547(T547 t547) {
        this.t547 = t547;
    }

    public T536 getT536() {
        return t536;
    }

    public void setT536(T536 t536) {
        this.t536 = t536;
    }

    public String getOrddat() {
        return orddat;
    }

    public void setOrddat(String orddat) {
        this.orddat = orddat;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPrcsts() {
        return prcsts;
    }

    public void setPrcsts(String prcsts) {
        this.prcsts = prcsts;
    }

    public T536.Bean[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(T536.Bean[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public String getSrcpage() {
        return srcpage;
    }

    public void setSrcpage(String srcpage) {
        this.srcpage = srcpage;
    }
}
