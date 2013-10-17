package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T121;
import gateway.sbs.txn.model.form.T007;
import gateway.sbs.txn.model.form.Th804;
//import gateway.sbs.txn.model.form.T861;
import gateway.sbs.txn.model.msg.Mh805;
import gateway.sbs.txn.model.msg.Mh803;
import gateway.sbs.txn.model.msg.Mh804;
import gateway.sbs.txn.model.msg.Mh801;
import gateway.sbs.txn.model.msg.Mh802;
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
public class ActidmAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActidmAction.class);

    private String action;
    private String vchtyp ="";

    private String irtdate;

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private List<T121.Bean> dataList = new ArrayList<>();
    private T121 irt = new T121();
    private T007 idm = new T007();
    private Th804 idmer = new Th804();


    private boolean updateable = false;
    private boolean deleteable = false;
    private boolean readonly = false;
    private List<Mh801> addirtList = new ArrayList<>();
    private Mh801 addirt = new Mh801();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        vchtyp = params.get("vchtyp");
        action = params.get("action");
        if (!StringUtils.isEmpty(vchtyp)) {
            Mh805 mh805 = new Mh805();
            SOFForm form = dataExchangeService.callSbsTxn("h805", mh805).get(0);
            if (!"T007".equals(form.getFormHeader().getFormCode())) {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            } else {
                irt = (T121) form.getFormBody();
//                irt.getBeanList().addAll(DdataList);
            }
        } else {
            // 添加利率 初始化 addirt
//            initAddirt();

        }
        if ("update".equals(action)) {
            updateable = true;
            readonly = true;
        }
        if ("delete".equals(action)) {
            deleteable = true;
            readonly = true;
        }
        if ("query".equals(action)) onAllT121Query();
    }

    private void initAddirt() {
        addirt = new Mh801();
    }
public String onAllQuery() {
    try {
        double amt = 0;
        Mh803 mh803 = new Mh803(vchtyp);
        List<SOFForm> formList = dataExchangeService.callSbsTxn("h803", mh803);
        if (formList != null && !formList.isEmpty()) {
            dataList = new ArrayList<>();
            for (SOFForm form : formList) {
                if ("T007".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                    idm = (T007) form.getFormBody();
//                    amt = Float.parseFloat(idm.getVCHAMT());

                }
                else {
                    logger.info(form.getFormHeader().getFormCode());
                    // MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        }

        if (idm.getSCTMAK() == null|| idm.getSCTMAK().isEmpty() ) {
            MessageUtil.addWarn("没有查询到数据。");
        }
    } catch (Exception e) {
        logger.error("查询失败", e);
        MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
    }
    return null;
}
    public String onAllT121Query() {
        try {
            Mh805 mh805 = new Mh805();
            List<SOFForm> formList = dataExchangeService.callSbsTxn("h805", mh805);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T121".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T121 t121 = (T121) form.getFormBody();
                        dataList.addAll(t121.getBeanList());
                    }
                    else {
                        logger.info(form.getFormHeader().getFormCode());
                        // MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
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
        return "actidmBean";
    }

    public String onUpdate() {
        try {
            String formcode = txnh804ForUD();
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
    private String txnh804ForUD() throws IllegalAccessException {
        Mh804 mh804 = new Mh804();
//        BeanHelper.copyFields(idmer, mh804);
        SOFForm form = dataExchangeService.callSbsTxn("h804", mh804).get(0);
        return form.getFormHeader().getFormCode();
    }
    private String txnh802ForUD() throws IllegalAccessException {

        Mh802 mh802 = new Mh802();
        SOFForm form = dataExchangeService.callSbsTxn("h802", mh802).get(0);
        return form.getFormHeader().getFormCode();
    }

    public String onDelete() {
        try {
            String formcode = txnh802ForUD();
            if ("w004".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                deleteable = false;
            } else {
                MessageUtil.addInfoWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("删除失败", e);
            MessageUtil.addError("删除失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onAdd() {

        try {
            SOFForm form = dataExchangeService.callSbsTxn("h801", addirt).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
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




    public String onBack() {
        return "actidmMng?faces-redirect=true&action=query";
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




    public T121 getIrt() {
        return irt;
    }

    public void setIrt(T121 irt) {
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

    public List<Mh801> getAddirtList() {
        return addirtList;
    }

    public void setAddirtList(List<Mh801> addirtList) {
        this.addirtList = addirtList;
    }

    public List<T121.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T121.Bean> dataList) {
        this.dataList = dataList;
    }

    public Mh801 getAddirt() {
        return addirt;
    }

    public void setAddirt(Mh801 addirt) {
        this.addirt = addirt;
    }

    public String getVchtyp() {
        return vchtyp;
    }

    public void setVchtyp(String vchtyp) {
        this.vchtyp = vchtyp;
    }

    public T007 getIdm() {
        return idm;
    }

    public void setIdm(T007 idm) {
        this.idm = idm;
    }

/*public List<T861.Bean> getDdataList() {
        return DdataList;
    }

    public void setDdataList(List<T861.Bean> ddataList) {
        DdataList = ddataList;
    }*/



//    public T861 getT861() {
//        return t861;
//    }
//
//    public void setT861(T861 t861) {
//        this.t861 = t861;
//    }

}
