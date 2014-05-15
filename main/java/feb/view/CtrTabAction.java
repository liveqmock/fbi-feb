package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T816;
import gateway.sbs.txn.model.form.T818;
import gateway.sbs.txn.model.form.T865;
import gateway.sbs.txn.model.form.T882;
import gateway.sbs.txn.model.msg.M9816;
import gateway.sbs.txn.model.msg.M9a18;
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
 * Date: 14-5-15
 * Time: …œŒÁ9:52
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class CtrTabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(CtrTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String ctrcde;
    private String action;

    private T818 t818 = new T818();
    private T865 t865 = new T865();
    private List<T818.Bean> dataList = new ArrayList<>();
    private M9a18 m9a18 = new M9a18();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        ctrcde = params.get("ctrcde");
        if (action != null) {
            onDetailQry();
        } else {
            onAllQry();
        }
    }

    public String onAllQry() {
        try {
            m9a18.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a18", m9a18);
            if (!formList.isEmpty() && formList != null) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T818".equals(form.getFormHeader().getFormCode())) {
                        t818 = (T818) form.getFormBody();
                        dataList.addAll(t818.getBeanList());
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
            m9a18.setFUNCDE("0");
            m9a18.setCTRCDE(ctrcde);
            SOFForm form = dataExchangeService.callSbsTxn("9a18", m9a18).get(0);
            if ("T865".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                t865 = (T865) form.getFormBody();
            } else {
                logger.error(form.getFormHeader().getFormCode());
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            }
        } catch (Exception e) {
            logger.error("≤È—Ø ß∞‹", e);
            MessageUtil.addError("≤È—Ø ß∞‹." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClick() {
        return "ctrtabBean";
    }

    public String onBack() {
        return "ctrtabQry?faces-redirect=true";
    }


    //= = = = = = = = = = = = = = = = = = = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getCtrcde() {
        return ctrcde;
    }

    public void setCtrcde(String ctrcde) {
        this.ctrcde = ctrcde;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T818 getT818() {
        return t818;
    }

    public void setT818(T818 t818) {
        this.t818 = t818;
    }

    public T865 getT865() {
        return t865;
    }

    public void setT865(T865 t865) {
        this.t865 = t865;
    }

    public List<T818.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T818.Bean> dataList) {
        this.dataList = dataList;
    }

    public M9a18 getM9a18() {
        return m9a18;
    }

    public void setM9a18(M9a18 m9a18) {
        this.m9a18 = m9a18;
    }
}
