package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T801;
import gateway.sbs.txn.model.form.T804;
import gateway.sbs.txn.model.form.T850;
import gateway.sbs.txn.model.msg.M9804;
import gateway.sbs.txn.model.msg.M9a01;
import org.apache.commons.lang.StringUtils;
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
 * Date: 14-5-13
 * Time: œ¬ŒÁ3:59
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class CurTabAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(CurTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String action;
    private String curcde;
    private T850 t850 = new T850();
    private M9a01 m9a01 = new M9a01();
    private List<T801.Bean> dataList = new ArrayList<>();

    @PostConstruct
    public void init() {
        //FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        curcde = StringUtils.isEmpty(params.get("curcde")) ? "" : params.get("curcde");
        action = params.get("action");
        if (action != null) {
            onDetailQry();
        } else {
            onAllQry();
        }
        /*if (action != null) {
            if (action.equals("detail")) {
                onDetailQry();
            } else if (action.equals("query")) {
                onAllQry();
            }
        } else {
            if (context.getRenderResponse()) {
                onAllQry();
            }
        }*/
    }

    public String onAllQry() {
        try {
            m9a01.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a01", m9a01);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T801".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T801 t801 = (T801) form.getFormBody();
                        dataList.addAll(t801.getBeanList());
                    } else if ("W012".equalsIgnoreCase(form.getFormHeader().getFormCode())) {

                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
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
            m9a01.setFUNCDE("0");
            m9a01.setCURCDE(curcde);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a01", m9a01);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T850".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t850 = (T850) form.getFormBody();
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
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
        return "curtabBean";
    }

    public String onBack() {
        return "curtabQry?faces-redirect=true";
        //return "curtabQry?faces-redirect=true&action=query";
    }

    //= = = = =  = = = = = =  = = =  = = = = = = = = = = = =


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

    public T850 getT850() {
        return t850;
    }

    public void setT850(T850 t850) {
        this.t850 = t850;
    }

    public M9a01 getM9a01() {
        return m9a01;
    }

    public void setM9a01(M9a01 m9a01) {
        this.m9a01 = m9a01;
    }

    public List<T801.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T801.Bean> dataList) {
        this.dataList = dataList;
    }

    public String getCurcde() {
        return curcde;
    }

    public void setCurcde(String curcde) {
        this.curcde = curcde;
    }
}
