package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T536;
import gateway.sbs.txn.model.form.T539;
import gateway.sbs.txn.model.msg.Mn065;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 对公支付手续费查询
 */
@ManagedBean
@ViewScoped
public class EntPayChargeAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;
    private List<SelectItem> chargeFuncdeItems;      // 查询类别
    private List<T539.Bean> dataList;
    private Mn065 mn065;
    private T539 t539;

    @PostConstruct
    public void init() {
        t539 = new T539();
        mn065 = new Mn065(skylineService.getSysdate6(), "1", "1");
        chargeFuncdeItems = skylineService.getEnuSelectItemList("CTF-FUNCDE", false, false);
    }

    public String onQuery() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("n065", mn065);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if (!"T539".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T539".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t539 = (T539) form.getFormBody();
                        t539.setCurcnt(Integer.parseInt(t539.getCurcnt()) + "");
                        t539.setTotcnt(Integer.parseInt(t539.getTotcnt()) + "");
//                        dataList = t539.getBeanList();
                        DecimalFormat df = new DecimalFormat("###,###,##0.00");
                        for (T539.Bean record : t539.getBeanList()) {
                            record.setFEEAMT(df.format(new BigDecimal(record.getFEEAMT())));
                            record.setTXNCNT(Integer.parseInt(record.getTXNCNT()) + "");
                            dataList.add(record);
                        }
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


    // --------------------------------------------------

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

    public List<SelectItem> getChargeFuncdeItems() {
        return chargeFuncdeItems;
    }

    public void setChargeFuncdeItems(List<SelectItem> chargeFuncdeItems) {
        this.chargeFuncdeItems = chargeFuncdeItems;
    }

    public List<T539.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T539.Bean> dataList) {
        this.dataList = dataList;
    }

    public Mn065 getMn065() {
        return mn065;
    }

    public void setMn065(Mn065 mn065) {
        this.mn065 = mn065;
    }

    public T539 getT539() {
        return t539;
    }

    public void setT539(T539 t539) {
        this.t539 = t539;
    }
}
