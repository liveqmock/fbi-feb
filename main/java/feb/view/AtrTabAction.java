package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T818;
import gateway.sbs.txn.model.form.T824;
import gateway.sbs.txn.model.form.T865;
import gateway.sbs.txn.model.form.T871;
import gateway.sbs.txn.model.msg.M9a18;
import gateway.sbs.txn.model.msg.M9a24;
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
public class AtrTabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(AtrTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String atrcde;
    private String action;

    private T824 t824 = new T824();
    private T871 t871 = new T871();
    private List<T824.Bean> dataList = new ArrayList<>();
    private M9a24 m9a24 = new M9a24();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        atrcde = params.get("atrcde");
        if (action != null) {
            onDetailQry();
        } else {
            onAllQry();
        }
    }

    public String onAllQry() {
        try {
            m9a24.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a24", m9a24);
            if (!formList.isEmpty() && formList != null) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T824".equals(form.getFormHeader().getFormCode())) {
                        t824 = (T824) form.getFormBody();
                        dataList.addAll(t824.getBeanList());
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
            m9a24.setFUNCDE("0");
            m9a24.setATRCDE(atrcde);
            SOFForm form = dataExchangeService.callSbsTxn("9a24", m9a24).get(0);
            if ("T871".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                t871 = (T871) form.getFormBody();
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
        return "atrtabBean";
    }

    public String onBack() {
        return "atrtabQry?faces-redirect=true";
    }


    //= = = = = = = = = = = = = = = = = = = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getAtrcde() {
        return atrcde;
    }

    public void setAtrcde(String atrcde) {
        this.atrcde = atrcde;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T824 getT824() {
        return t824;
    }

    public void setT824(T824 t824) {
        this.t824 = t824;
    }

    public T871 getT871() {
        return t871;
    }

    public void setT871(T871 t871) {
        this.t871 = t871;
    }

    public List<T824.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T824.Bean> dataList) {
        this.dataList = dataList;
    }

    public M9a24 getM9a24() {
        return m9a24;
    }

    public void setM9a24(M9a24 m9a24) {
        this.m9a24 = m9a24;
    }
}
