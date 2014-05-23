package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T883;
import gateway.sbs.txn.model.form.ac.T884;
import gateway.sbs.txn.model.msg.M9a29;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;
import pub.tools.BeanHelper;

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
 * Time: ÉÏÎç9:52
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class TaxrTabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(TaxrTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService ;

    private String taxtno;
    private String taxcde;
    private String action;
    private boolean updateable = false;
    private boolean deleteable = false;

    private T884 t884 = new T884();
    private T883 t883 = new T883();
    private List<T884.Bean> dataList = new ArrayList<>();
    private M9a29 m9a29 = new M9a29();

    @PostConstruct
    public void init(){
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        taxtno = params.get("taxtno");
        taxcde = params.get("taxcde");
        if (action != null) {
            onDetailQry();
            if ("update".equals(action)) {
                updateable = true;
            }
            if ("delete".equals(action)) {
                deleteable = true;
            }
        } else {
            onAllQry();
        }
    }

    public String onAllQry(){
        try{
            m9a29.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a29",m9a29);
            if (!formList.isEmpty()&&formList!=null){
                dataList = new ArrayList<>();
                for (SOFForm form : formList){
                    if ("T884".equals(form.getFormHeader().getFormCode())){
                        t884 = (T884)form.getFormBody();
                        dataList.addAll(t884.getBeanList());
                    }else if ("W012".equals(form.getFormHeader().getFormCode())){

                    }else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs",form.getFormHeader().getFormCode());
                    }
                }
            }
        }catch (Exception e){
            logger.error("²éÑ¯Ê§°Ü", e);
            MessageUtil.addError("²éÑ¯Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onDetailQry(){
        try {
            m9a29.setFUNCDE("0");
            m9a29.setTAXTNO(taxtno);
            m9a29.setTAXCDE(taxcde);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a29", m9a29);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T883".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t883 = (T883) form.getFormBody();
                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("²éÑ¯Ê§°Ü", e);
            MessageUtil.addError("²éÑ¯Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onDetailMng(String funcde){
        try {
            m9a29.setFUNCDE(funcde);
            m9a29.setTAXTNO(taxtno);
            m9a29.setTAXCDE(taxcde);
            BeanHelper.copyFields(t883,m9a29);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a29", m9a29);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("W001".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    } else if ("W004".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("²Ù×÷Ê§°Ü", e);
            MessageUtil.addError("²Ù×÷Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onAdd(){
        try {
            m9a29.setFUNCDE("4");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a29", m9a29);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("W005".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }  else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Ìí¼ÓÊ§°Ü", e);
            MessageUtil.addError("Ìí¼ÓÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }


    public String  onClick(){
        return "taxrtabBean";
    }

    public String onBack(){
        if ("detail".equals(action)) {
            return "taxrtabQry?faces-redirect=true";
        }
        return "taxrtabMng?faces-redirect=true";
    }


    //= = = = = = = = = = = = = = = = = = = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getTaxtno() {
        return taxtno;
    }

    public void setTaxtno(String taxtno) {
        this.taxtno = taxtno;
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

    public T884 getT884() {
        return t884;
    }

    public void setT884(T884 t884) {
        this.t884 = t884;
    }

    public T883 getT883() {
        return t883;
    }

    public void setT883(T883 t883) {
        this.t883 = t883;
    }

    public List<T884.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T884.Bean> dataList) {
        this.dataList = dataList;
    }

    public M9a29 getM9a29() {
        return m9a29;
    }

    public void setM9a29(M9a29 m9a29) {
        this.m9a29 = m9a29;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }

    public boolean isDeleteable() {
        return deleteable;
    }

    public void setDeleteable(boolean deleteable) {
        this.deleteable = deleteable;
    }

}
