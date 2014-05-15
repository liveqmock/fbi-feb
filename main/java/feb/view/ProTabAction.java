package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T815;
import gateway.sbs.txn.model.form.T863;
import gateway.sbs.txn.model.msg.M9815;
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
public class ProTabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ProTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService ;

    private String plcode;
    private String glcode;
    private String action;

    private T815 t815 = new T815();
    private T863 t863 = new T863();
    private List<T815.Bean> dataList = new ArrayList<>();
    private M9815 m9815 = new M9815();

    @PostConstruct
    public void init(){
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        plcode = params.get("plcode");
        glcode = params.get("glcode");
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
            m9815.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9815",m9815);
            if (!formList.isEmpty()&&formList!=null){
                dataList = new ArrayList<>();
                for (SOFForm form : formList){
                    if ("T815".equals(form.getFormHeader().getFormCode())){
                        t815 = (T815)form.getFormBody();
                        dataList.addAll(t815.getBeanList());
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
            m9815.setFUNCDE("0");
            m9815.setPLCODE(plcode);
            m9815.setGLCODE(glcode);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9815", m9815);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T863".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t863 = (T863) form.getFormBody();
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
        return "protabBean";
    }

    public String onBack(){
        return "protabQry?faces-redirect=true";
    }


    //= = = = = = = = = = = = = = = = = = = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getPlcode() {
        return plcode;
    }

    public void setPlcode(String plcode) {
        this.plcode = plcode;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T815 getT815() {
        return t815;
    }

    public void setT815(T815 t815) {
        this.t815 = t815;
    }

    public T863 getT863() {
        return t863;
    }

    public void setT863(T863 t863) {
        this.t863 = t863;
    }

    public List<T815.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T815.Bean> dataList) {
        this.dataList = dataList;
    }

    public M9815 getM9815() {
        return m9815;
    }

    public void setM9815(M9815 m9815) {
        this.m9815 = m9815;
    }
}
