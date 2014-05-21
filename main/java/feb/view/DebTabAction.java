package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T816;
import gateway.sbs.txn.model.form.ac.T882;
import gateway.sbs.txn.model.msg.M9816;
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
public class DebTabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(DebTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String alcode;
    private String glcode;
    private String action;
    private boolean updateable = false;
    private boolean deleteable = false;

    private T816 t816 = new T816();
    private T882 t882 = new T882();
    private List<T816.Bean> dataList = new ArrayList<>();
    private M9816 m9816 = new M9816();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        alcode = params.get("alcode");
        glcode = params.get("glcode");
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

    public String onAllQry() {
        try {
            m9816.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9816", m9816);
            if (!formList.isEmpty() && formList != null) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T816".equals(form.getFormHeader().getFormCode())) {
                        t816 = (T816) form.getFormBody();
                        dataList.addAll(t816.getBeanList());
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

    public String onDetailQry() {
        try {
            m9816.setFUNCDE("0");
            m9816.setALCODE(alcode);
            m9816.setGLCODE(glcode);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9816", m9816);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T882".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t882 = (T882) form.getFormBody();
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

    public String onDetailMng(String funcde) {
        try {
            m9816.setFUNCDE(funcde);
            m9816.setALCODE(alcode);
            m9816.setGLCODE(glcode);
            BeanHelper.copyFields(t882, m9816);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9816", m9816);
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

    public String onAdd() {
        try {
            m9816.setFUNCDE("4");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9816", m9816);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("W005".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
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
        return "debtabBean";
    }

    public String onBack() {
        if ("detail".equals(action)) {
            return "debtabQry?faces-redirect=true";
        }
        return "debtabMng?faces-redirect=true";
    }


    //= = = = = = = = = = = = = = = = = = = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getAlcode() {
        return alcode;
    }

    public void setAlcode(String alcode) {
        this.alcode = alcode;
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

    public T816 getT816() {
        return t816;
    }

    public void setT816(T816 t816) {
        this.t816 = t816;
    }

    public T882 getT882() {
        return t882;
    }

    public void setT882(T882 t882) {
        this.t882 = t882;
    }

    public List<T816.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T816.Bean> dataList) {
        this.dataList = dataList;
    }

    public M9816 getM9816() {
        return m9816;
    }

    public void setM9816(M9816 m9816) {
        this.m9816 = m9816;
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
