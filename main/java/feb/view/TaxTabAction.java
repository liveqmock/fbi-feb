package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T885;
import gateway.sbs.txn.model.form.T886;
import gateway.sbs.txn.model.msg.M9a30;
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
public class TaxTabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(TaxTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService ;

    private String taxcde;
    private String action;

    private T886 t886 = new T886();
    private T885 t885 = new T885();
    private List<T886.Bean> dataList = new ArrayList<>();
    private M9a30 m9a30 = new M9a30();

    @PostConstruct
    public void init(){
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        taxcde = params.get("taxcde");
        if (action != null) {
            if ("detail".equals(action)){
                onDetailQry();
            }
        } else {
            onAllQry();
        }
    }

    public String onAllQry(){
        try{
            m9a30.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a30",m9a30);
            if (!formList.isEmpty()&&formList!=null){
                dataList = new ArrayList<>();
                for (SOFForm form : formList){
                    if ("T886".equals(form.getFormHeader().getFormCode())){
                        t886 = (T886)form.getFormBody();
                        dataList.addAll(t886.getBeanList());
                    }else if ("W012".equals(form.getFormHeader().getFormCode())){

                    }else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs",form.getFormHeader().getFormCode());
                    }
                }
            }
        }catch (Exception e){
            logger.error("≤È—Ø ß∞‹", e);
            MessageUtil.addError("≤È—Ø ß∞‹." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onDetailQry(){
        try {
            m9a30.setFUNCDE("0");
            m9a30.setTAXCDE(taxcde);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a30", m9a30);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T885".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t885 = (T885) form.getFormBody();
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

    public String  onClick(){
        return "taxtabBean";
    }

    public String onBack(){
        return "taxtabQry?faces-redirect=true";
    }


    //= = = = = = = = = = = = = = = = = = = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }


    public String getTaxcde() {
        return taxcde;
    }

    public void setTaxcde(String taxcde) {
        this.taxcde = taxcde;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T886 getT886() {
        return t886;
    }

    public void setT886(T886 t886) {
        this.t886 = t886;
    }

    public T885 getT885() {
        return t885;
    }

    public void setT885(T885 t885) {
        this.t885 = t885;
    }

    public List<T886.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T886.Bean> dataList) {
        this.dataList = dataList;
    }

    public M9a30 getM9a30() {
        return m9a30;
    }

    public void setM9a30(M9a30 m9a30) {
        this.m9a30 = m9a30;
    }
}
