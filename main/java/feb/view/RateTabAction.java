package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T806;
import gateway.sbs.txn.model.form.ac.T807;
import gateway.sbs.txn.model.msg.M9807;
import gateway.sbs.txn.model.msg.M9a06;
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
 * Time: …œŒÁ10:18
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class RateTabAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RateTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String action;
    private String frtcde;
    private String curcde;
    private String effdat;

    private T806 t806 = new T806();
    private T807 t807 = new T807();
    private M9807 m9807 = new M9807();
    private M9a06 m9a06 = new M9a06();
    private List<T807.Bean> dataList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        frtcde = params.get("frtcde");
        curcde = params.get("curcde");
        effdat = params.get("effdat");
        action = params.get("action");
        if (action != null) {
            onDetailQry();
        } else {
            //onAllQry();
        }
    }

    public String onAllQry() {
        try {
            m9807.setCURCDE(curcde);
            m9807.setEFFDAT(effdat);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9807", m9807);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T807".equals(form.getFormHeader().getFormCode())) {
                        t807 = (T807) form.getFormBody();
                        dataList.addAll(t807.getBeanList());
                    }else if ("W012".equals(form.getFormHeader().getFormCode())){

                    }else {
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
        try{
            m9a06.setCURCDE(curcde);
            m9a06.setFRTCDE(frtcde);
            m9a06.setEFFDAT(effdat);
            SOFForm form = dataExchangeService.callSbsTxn("9a06",m9a06).get(0);
            if ("T806".equals(form.getFormHeader().getFormCode())){
                t806 = (T806)form.getFormBody();
            }else {
                logger.error(form.getFormHeader().getFormCode());
                MessageUtil.addErrorWithClientID("msgs",form.getFormHeader().getFormCode());
            }
        }catch (Exception e){
            logger.error("≤È—Ø ß∞‹", e);
            MessageUtil.addError("≤È—Ø ß∞‹." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClick(){
        return "ratetabBean";
    }

    public String onBack(){
        return "ratetabQry?faces-redirect=true";
    }

    //= = = = = = = = = = = = = = = = == = = = =  =

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

    public String getCurcde() {
        return curcde;
    }

    public void setCurcde(String curcde) {
        this.curcde = curcde;
    }

    public String getEffdat() {
        return effdat;
    }

    public void setEffdat(String effdat) {
        this.effdat = effdat;
    }

    public String getFrtcde() {
        return frtcde;
    }

    public void setFrtcde(String frtcde) {
        this.frtcde = frtcde;
    }

    public T806 getT806() {
        return t806;
    }

    public void setT806(T806 t806) {
        this.t806 = t806;
    }

    public T807 getT807() {
        return t807;
    }

    public void setT807(T807 t807) {
        this.t807 = t807;
    }

    public M9807 getM9807() {
        return m9807;
    }

    public void setM9807(M9807 m9807) {
        this.m9807 = m9807;
    }

    public M9a06 getM9a06() {
        return m9a06;
    }

    public void setM9a06(M9a06 m9a06) {
        this.m9a06 = m9a06;
    }

    public List<T807.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T807.Bean> dataList) {
        this.dataList = dataList;
    }
}
