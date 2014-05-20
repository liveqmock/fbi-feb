package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.sc.T908;
import gateway.sbs.txn.model.form.ac.T909;
import gateway.sbs.txn.model.msg.M8848;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;

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
 * Date: 14-5-14
 * Time: œ¬ŒÁ3:24
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class BusTabAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(BusTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String tddtdc;
    private String action;

    private T909 t909 = new T909();
    private T908 t908 = new T908();
    private List<T909.Bean> dataList = new ArrayList<>();
    private M8848 m8848 = new M8848();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        tddtdc = params.get("tddtdc");
        action = params.get("action");
        if (action != null) {
            if ("detail".equals(action)) {
                onDetailQry();
            }
        } else {
            onAllQry();
        }
    }

    public String onAllQry() {
        try {
            m8848.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8848", m8848);
            if (!formList.equals(null) && formList != null) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T909".equals(form.getFormHeader().getFormCode())) {
                        t909 = (T909) form.getFormBody();
                        dataList.addAll(t909.getBeanList());
                    } else if ("W012".equals(form.getFormHeader().getFormCode())) {

                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());

                    }
                }
            }
        } catch (Exception e) {
            logger.error("≤È—Ø ß∞‹", e);
            MessageUtil.addError("≤È—Ø ß∞‹." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onDetailQry() {
        try {
            m8848.setPASTYP(tddtdc.substring(0, 1));
            m8848.setINPFLG(tddtdc.substring(1, 3));
            m8848.setSBKNUM(tddtdc.substring(3, 5));
            m8848.setFUNCDE("0");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8848", m8848);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T908".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t908 = (T908) form.getFormBody();
                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        pub.tools.MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("≤È—Ø ß∞‹", e);
            pub.tools.MessageUtil.addError("≤È—Ø ß∞‹." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClick() {
        return "bustabBean";
    }

    public String onBack() {
        return "bustabQry?faces-redirect=true";
        //return "curtabQry?faces-redirect=true&action=query";
    }


    // = = = = = = = = = = =  = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T909 getT909() {
        return t909;
    }

    public void setT909(T909 t909) {
        this.t909 = t909;
    }

    public List<T909.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T909.Bean> dataList) {
        this.dataList = dataList;
    }

    public M8848 getM8848() {
        return m8848;
    }

    public void setM8848(M8848 m8848) {
        this.m8848 = m8848;
    }

    public String getTddtdc() {
        return tddtdc;
    }

    public void setTddtdc(String tddtdc) {
        this.tddtdc = tddtdc;
    }

    public T908 getT908() {
        return t908;
    }

    public void setT908(T908 t908) {
        this.t908 = t908;
    }
}
