package feb.view;


import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T529;
import gateway.sbs.txn.model.form.T552;
import gateway.sbs.txn.model.msg.M5832;
import gateway.sbs.txn.model.msg.M5834;
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
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-14
 * Time: œ¬ŒÁ2:36
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class PatTabAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(PatTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String action;
    private String secccy;
    private String effdat;
    private String curcde;

    private M5832 m5832 = new M5832();
    private M5834 m5834 = new M5834();
    private T552 t552 = new T552();
    private List<T552.Bean> dataList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        secccy = params.get("secccy");
        effdat = params.get("effdat");
        curcde = params.get("curcde");
        if (action != null) {
            onDetailQry();
        }
    }

    public String onAllQry() {
        try {
            m5832.setSECCCY(secccy);
            m5832.setEFFDAT(effdat);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("5832", m5832);
            dataList = new ArrayList<>();
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T552".equals(form.getFormHeader().getFormCode())) {
                        t552 = (T552) form.getFormBody();
                        dataList.addAll(t552.getBeanList());
                    } else if ("W012".equals(form.getFormHeader().getFormCode())) {

                    } else if ("T529".equals(form.getFormHeader().getFormCode())) {
                        T529 t529 = (T529) form.getFormBody();
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
            m5834.setSECCCY(secccy);
            m5834.setEFFDAT(effdat);
            m5834.setCURCDE(curcde);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("5834", m5834);
            dataList = new ArrayList<>();
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    String formcode = form.getFormHeader().getFormCode();
                    String formsys = form.getFormHeader().getFormSys();
                    if ("T552".equals(form.getFormHeader().getFormCode())) {
                        t552 = (T552) form.getFormBody();
                        dataList.addAll(t552.getBeanList());
                    } else if ("W012".equals(form.getFormHeader().getFormCode())) {

                    } else if ("T529".equals(form.getFormHeader().getFormCode())) {
                        T529 t529 = (T529) form.getFormBody();
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

    public String onClick() {
        return "pattabBean";
    }

    public String onBack() {
        return "pattabQry?faces-redirect=true";
    }
    //= = = = = = = = = = = = = = = = = = = = = = = = =


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

    public String getSecccy() {
        return secccy;
    }

    public void setSecccy(String secccy) {
        this.secccy = secccy;
    }

    public M5832 getM5832() {
        return m5832;
    }

    public void setM5832(M5832 m5832) {
        this.m5832 = m5832;
    }

    public T552 getT552() {
        return t552;
    }

    public void setT552(T552 t552) {
        this.t552 = t552;
    }

    public List<T552.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T552.Bean> dataList) {
        this.dataList = dataList;
    }

    public String getEffdat() {
        return effdat;
    }

    public void setEffdat(String effdat) {
        this.effdat = effdat;
    }

    public String getCurcde() {
        return curcde;
    }

    public void setCurcde(String curcde) {
        this.curcde = curcde;
    }

    public M5834 getM5834() {
        return m5834;
    }

    public void setM5834(M5834 m5834) {
        this.m5834 = m5834;
    }
}
