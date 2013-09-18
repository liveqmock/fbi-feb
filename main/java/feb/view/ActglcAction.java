package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T861;
import gateway.sbs.txn.model.form.T813;
import gateway.sbs.txn.model.msg.M9861;
import gateway.sbs.txn.model.msg.M9813;
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
 * ������
 */
@ManagedBean
@ViewScoped
public class ActglcAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActirtAction.class);

    private String action;
    private String glcode;
    private String extdat;
    private String glcnam;
    private String irtdate;

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
//    private List<T813.Bean> dataList = new ArrayList<>();
    private List<T861.Bean> DdataList = new ArrayList<>();
    private T861 irt = new T861();

    private boolean updateable = false;
    private boolean deleteable = false;
    private boolean readonly = false;
    private List<M9813> addirtList = new ArrayList<>();
    private M9813 addirt = new M9813();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        extdat = params.get("extdat");
        glcnam = params.get("glcnam");
        action = params.get("action");
        irtdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (!StringUtils.isEmpty(glcode)) {
            M9813 m9813 = new M9813(glcode);
            SOFForm form = dataExchangeService.callSbsTxn("9813", m9813).get(0);
            if (!"T861".equals(form.getFormHeader().getFormCode())) {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            } else {
                irt = (T861) form.getFormBody();

            }
        } else {
            // ������� ��ʼ�� addirt
            initAddirt();

        }
        if ("update".equals(action)) {
            updateable = true;
            readonly = true;
        }
        if ("delete".equals(action)) {
            deleteable = true;
            readonly = true;
        }
        if ("query".equals(action)) onAllQuery();
    }

    private void initAddirt() {
        addirt = new M9813();


    }

    public String onAllQuery() {
        try {
            M9813 m9813 = new M9813(glcode);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9813", m9813);
            if (formList != null && !formList.isEmpty()) {
//                dataList = new ArrayList<>();
                DdataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if (!"T861".equals(form.getFormHeader().getFormCode()) && !"W001".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T861".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T861 t861 = (T861) form.getFormBody();
                        DdataList.addAll(t861.getBeanList());
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        // MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
           }
            if (DdataList == null || DdataList.isEmpty()) {
                MessageUtil.addWarn("û�в�ѯ�����ݡ�");
            }
        } catch (Exception e) {
            logger.error("��ѯʧ��", e);
            MessageUtil.addError("��ѯʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
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
            logger.error("�޸�ʧ��", e);
            MessageUtil.addError("�޸�ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onDelete() {
        try {
            String formcode = txn9813ForUD();
            if ("W004".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                deleteable = false;
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("ɾ��ʧ��", e);
            MessageUtil.addError("ɾ��ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onAdd() {
        addirt.setFUNCDE("4");
        try {
            SOFForm form = dataExchangeService.callSbsTxn("9813", addirt).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if (!"W005".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                addirtList.add(addirt);
                initAddirt();
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("��������ʧ��", e);
            MessageUtil.addError("��������ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // �����޸ĺ�ɾ��
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
        return "actglcMng?faces-redirect=true&action=query";
    }
    // --------------------


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }


    public String getIrtdate() {
        return irtdate;
    }

    public void setIrtdate(String irtdate) {
        this.irtdate = irtdate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

//    public List<T813.Bean> getDataList() {
//        return dataList;
//    }
//
//    public void setDataList(List<T813.Bean> dataList) {
//        this.dataList = dataList;
//    }

    public String getExtdat() {
        return extdat;
    }

    public void setExtdat(String extdat) {
        this.extdat = extdat;
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

    public String getGlcnam() {
        return glcnam;
    }

    public void setGlcnam(String glcnam) {
        this.glcnam = glcnam;
    }

    public List<T861.Bean> getDdataList() {
        return DdataList;
    }

    public void setDdataList(List<T861.Bean> ddataList) {
        DdataList = ddataList;
    }


}
