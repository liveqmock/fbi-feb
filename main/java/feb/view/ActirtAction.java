package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.core.domain.SOFFormBean;
import gateway.sbs.txn.model.form.T804;
import gateway.sbs.txn.model.form.T805;
import gateway.sbs.txn.model.msg.M9804;
import gateway.sbs.txn.model.msg.M9805;
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
 * 利率码
 */
@ManagedBean
@ViewScoped
public class ActirtAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActirtAction.class);

    private String action;
    private String curcde;
    private String extdat;
    private String irtcde;
    private String irtdate;

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private List<T805> dataList;
    private T804 irt;
    private boolean updateable = false;
    private boolean deleteable = false;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        curcde = StringUtils.isEmpty(params.get("curcde")) ? "001" : params.get("curcde");
        extdat = params.get("extdat");
        irtcde = params.get("irtcde");
        action = params.get("action");
        irtdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (!StringUtils.isEmpty(irtcde)) {
            M9804 m9804 = new M9804(curcde, irtcde, extdat, "0");
            SOFForm form = dataExchangeService.callSbsTxn("9804", m9804).get(0);
            if (!"T804".equals(form.getFormHeader().getFormCode())) {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            } else {
                irt = (T804) form.getFormData().getBeans().get(0);
            }
        }
        if ("update".equals(action)) updateable = true;
        if ("delete".equals(action)) deleteable = true;
        if ("query".equals(action)) onAllQuery();
    }

    public String onAllQuery() {
        try {
            M9805 m9805 = new M9805(curcde, irtdate);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9805", m9805);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if (!"T805".equals(form.getFormHeader().getFormCode()) &&
                            !"W012".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T805".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        for (SOFFormBean formBean : form.getFormData().getBeans()) {
                            dataList.add((T805) formBean);
                        }
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
//                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("没有查询到数据。");
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClick() {
        return "actirtBean";
    }

    public String onUpdate() {
        try {
            String formcode = txn9804();
            if ("W001".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                updateable = false;
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("修改失败", e);
            MessageUtil.addError("修改失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onDelete() {
        try {
            String formcode = txn9804();
            if ("W004".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                deleteable = false;
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("删除失败", e);
            MessageUtil.addError("删除失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // 利率修改和删除
    private String txn9804() throws IllegalAccessException {
        M9804 m9804 = new M9804(irt.getCURCDE(), irt.getIRTKD1() + irt.getIRTKD2(), irt.getEFFDAT(), "2");
        BeanHelper.copyFields(irt, m9804);
        m9804.setMODFLG("1");
        if ("update".equals(action)) m9804.setFUNCDE("2");
        if ("delete".equals(action)) m9804.setFUNCDE("3");
        if ("insert".equals(action)) m9804.setFUNCDE("4");
        SOFForm form = dataExchangeService.callSbsTxn("9804", m9804).get(0);
        return form.getFormHeader().getFormCode();
    }

    public String onBack() {
        return "actirtMng?faces-redirect=true&action=query";
    }
    // --------------------


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getCurcde() {
        return curcde;
    }

    public void setCurcde(String curcde) {
        this.curcde = curcde;
    }

    public String getIrtdate() {
        return irtdate;
    }

    public void setIrtdate(String irtdate) {
        this.irtdate = irtdate;
    }

    public List<T805> getDataList() {
        return dataList;
    }

    public void setDataList(List<T805> dataList) {
        this.dataList = dataList;
    }

    public String getExtdat() {
        return extdat;
    }

    public void setExtdat(String extdat) {
        this.extdat = extdat;
    }

    public String getIrtcde() {
        return irtcde;
    }

    public void setIrtcde(String irtcde) {
        this.irtcde = irtcde;
    }

    public T804 getIrt() {
        return irt;
    }

    public void setIrt(T804 irt) {
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
}
