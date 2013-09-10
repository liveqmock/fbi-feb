package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T804;
import gateway.sbs.txn.model.form.T805;
import gateway.sbs.txn.model.form.T813;
import gateway.sbs.txn.model.form.T861;
import gateway.sbs.txn.model.msg.M9804;
import gateway.sbs.txn.model.msg.M9861;
import gateway.sbs.txn.model.msg.M9813;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.security.OperatorManager;
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
 * 总账码
 */
@ManagedBean
@ViewScoped
public class ActglcAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActirtAction.class);

    private String action;
    private String curcde;
    private String extdat;
    private String irtcde;
    private String irtdate;
    private String glcode;
    private String glcnam;

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
//    private List<T805.Bean> dataList = new ArrayList<>();
    private List<T813.Bean> dataList = new ArrayList<>();
    private T861 irt = new T861();
    private boolean updateable = false;
    private boolean deleteable = false;
    private boolean readonly = false;
    private List<M9861> addirtList = new ArrayList<>();
    private M9861 addirt;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        curcde = StringUtils.isEmpty(params.get("curcde")) ? "001" : params.get("curcde");
        extdat = params.get("extdat");
        irtcde = params.get("irtcde");
        action = params.get("action");
        glcode = params.get("");
        glcnam = params.get("");
        irtdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (!StringUtils.isEmpty(irtcde)) {
            M9813 m9813 = new M9813(glcode, glcnam);
            SOFForm form = dataExchangeService.callSbsTxn("9813", m9813).get(0);
            if (!"T861".equals(form.getFormHeader().getFormCode())) {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            } else {
                irt = (T861) form.getFormBody();
            }
        } else {
            // 添加利率 初始化 addirt
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
        addirt = new M9861();
        addirt.setGLCCAT("001");
        addirt.setEFFDAT(irtdate);
        addirt.setGLCTYP("0.00");
        addirt.setGLCCCY("0.00");
        addirt.setGLCBAL("0");
        addirt.setGLCOCC("0");
        addirt.setGLCINT("0");
        addirt.setGLCOPN("0");
        addirt.setGLCRVS("0");
        addirt.setGLCBEL("1");

    }

    public String onAllQuery() {
        try {
            M9813 m9813 = new M9813(curcde, irtdate);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9813", m9813);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if (!"T813".equals(form.getFormHeader().getFormCode()) &&
                            !"W012".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T813".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T813 t813 = (T813) form.getFormBody();
                        dataList.addAll(t813.getBeanList());
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
        return "actplcBean";
    }

    public String onUpdate() {
        try {
            String formcode = txn9861ForUD();
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
            String formcode = txn9861ForUD();
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

    public String onAdd() {
        try {
//            addirt.setMODFLG("1");
            addirt.setFUNCDE("4");
            SOFForm form = dataExchangeService.callSbsTxn("9861", addirt).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W005".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                addirtList.add(addirt);
                initAddirt();
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("利率增加失败", e);
            MessageUtil.addError("利率增加失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // 利率修改和删除
    private String txn9861ForUD() throws IllegalAccessException {
        M9861 m9861 = new M9861(irt.getGLCODE(),irt.getGLCNAM());
        BeanHelper.copyFields(irt, m9861);
//        m9861.setMODFLG("1");
        if ("update".equals(action)) m9861.setFUNCDE("2");
        else if ("delete".equals(action)) m9861.setFUNCDE("3");
        else m9861.setFUNCDE("4");
        SOFForm form = dataExchangeService.callSbsTxn("9861", m9861).get(0);
        return form.getFormHeader().getFormCode();
//          M9813 m9813 = new M9813(irt.getGLCODE(),irt.getGLCNAM());
//          SOFForm form = dataExchangeService.callSbsTxn("9813", m9813).get(0);
//          return form.getFormHeader().getFormCode();
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<T813.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T813.Bean> dataList) {
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

    public List<M9861> getAddirtList() {
        return addirtList;
    }

    public void setAddirtList(List<M9861> addirtList) {
        this.addirtList = addirtList;
    }

    public M9861 getAddirt() {
        return addirt;
    }

    public void setAddirt(M9861 addirt) {
        this.addirt = addirt;
    }

    public String getGlcode() {
        return glcode;
    }
}
