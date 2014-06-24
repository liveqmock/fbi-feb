package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T861;
import gateway.sbs.txn.model.form.ac.T813;
import gateway.sbs.txn.model.msg.M9813;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ÀûÂÊÂë
 */
@ManagedBean
@ViewScoped
public class ActglcAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActglcAction.class);

    private String action = "";
    private String glcode = "";

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private List<T813.Bean> dataList = new ArrayList<>();
    private T861 irt = new T861();
    private T813 t813 = new T813();

    private boolean updateable = false;
    private boolean deleteable = false;
    private boolean readonly = false;
    private List<M9813> addirtList = new ArrayList<>();
    private M9813 addirt = new M9813();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        glcode = params.get("glcode");
        action = params.get("action");
        if (action != null) {
            onDetailQry();
            if ("update".equals(action)) {
                updateable = true;
            }
            if ("delete".equals(action)) {
                deleteable = true;
            }
        } else {
            onAllQuery();
        }
    }

    private void initAddirt() {
        addirt = new M9813();
    }

    public String onDetailQry() {
        try {
            addirt.setFUNCDE("0");
            addirt.setGLCODE(glcode);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9813", addirt);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T861".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        irt = (T861)form.getFormBody();
                    } else if ("W012".equals(form.getFormHeader().getFormCode())) {

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

    public String onAllQuery() {
        try {
            addirt.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9813", addirt);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T813".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t813 = (T813) form.getFormBody();
                        dataList.addAll(t813.getBeanList());
                    } else if ("W012".equals(form.getFormHeader().getFormCode())) {
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

    public String onClick() {
        return "actglcBean";
    }

    public String onUpdate() {
        try {
            String formcode = txn9813ForUD();
            if ("W001".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                updateable = false;
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("ÐÞ¸ÄÊ§°Ü", e);
            MessageUtil.addError("ÐÞ¸ÄÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onDelete() {
        try {
            String formcode = txn9813ForUD();
            if ("T813".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                deleteable = false;
            } else {
                MessageUtil.addInfoWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("É¾³ýÊ§°Ü", e);
            MessageUtil.addError("É¾³ýÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onAdd() {
        addirt.setFUNCDE("4");
        try {
            SOFForm form = dataExchangeService.callSbsTxn("9813", addirt).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if (!"T861".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                addirtList.add(addirt);
                initAddirt();
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("ÀûÂÊÔö¼ÓÊ§°Ü", e);
            MessageUtil.addError("ÀûÂÊÔö¼ÓÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // ÀûÂÊÐÞ¸ÄºÍÉ¾³ý
    private String txn9813ForUD() throws IllegalAccessException {
//        M9813 m9813 = new M9813(irt.getGLCODE(), irt.getGLCNAM(), irt.getEFFDAT(), null);
        M9813 m9813 = new M9813(glcode);

        BeanHelper.copyFields(irt, m9813);
        if ("update".equals(action)) m9813.setFUNCDE("2");
        else if ("delete".equals(action)) m9813.setFUNCDE("3");
        else m9813.setFUNCDE("3");
        SOFForm form = dataExchangeService.callSbsTxn("9813", m9813).get(0);
        return form.getFormHeader().getFormCode();
    }

    public String onBack() {
        if ("detail".equals(action)) {
            return "actglcQry?faces-redirect=true";
        }
        return "actglcMng?faces-redirect=true";
    }


    // - - - - - - - -  - - - - - - - - - - - -

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

    public T861 getIrt() {
        return irt;
    }

    public void setIrt(T861 irt) {
        this.irt = irt;
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

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public List<M9813> getAddirtList() {
        return addirtList;
    }

    public void setAddirtList(List<M9813> addirtList) {
        this.addirtList = addirtList;
    }

    public M9813 getAddirt() {
        return addirt;
    }

    public void setAddirt(M9813 addirt) {
        this.addirt = addirt;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public List<T813.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T813.Bean> dataList) {
        this.dataList = dataList;
    }

    public T813 getT813() {
        return t813;
    }

    public void setT813(T813 t813) {
        this.t813 = t813;
    }

}
