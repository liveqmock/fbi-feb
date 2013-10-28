package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T404;
import gateway.sbs.txn.model.form.T814;
import gateway.sbs.txn.model.form.T862;
import gateway.sbs.txn.model.msg.M9804;
import gateway.sbs.txn.model.msg.M9814;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-12
 * Time: ÏÂÎç8:34
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ActapcAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ActirtAction.class);

    private String glcode;
    private String apcode;
    private String action;
    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private List<T814.Bean> dataList = new ArrayList<>();
    private T404 t404 = new T404();
    private T814 t814 = new T814();
    private T862 t862 = new T862();
    private boolean updateable = false;
    private boolean deleteable = false;
    private boolean readonly = false;
    private List<M9814> addapcList = new ArrayList<>();
    private M9814 addapc;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        glcode = StringUtils.isEmpty(params.get("glcode")) ? "" : params.get("glcode");
        apcode = params.get("apcode");
        action = params.get("action");
        if (!StringUtils.isEmpty(apcode)) {
            M9814 m9814 = new M9814(glcode, apcode);
            SOFForm form = dataExchangeService.callSbsTxn("9814", m9814).get(0);
            if (!"T862".equals(form.getFormHeader().getFormCode())) {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            } else {
                t862 = (T862) form.getFormBody();
            }
        } else {
            // Ìí¼Ó ³õÊ¼»¯ addapc
            initAddapc();
            initQry();
        }
        if ("update".equals(action)) {
            updateable = true;
            readonly = true;
        }
        if ("detail".equals(action)) {
            updateable = false;
            readonly = true;
        }
        if ("delete".equals(action)) {
            deleteable = true;
            readonly = true;
        }
        if ("query".equals(action)) {
            initQry();
        }
    }

    private void initAddapc() {
        //t862.setAPCODE("");
        addapc = new M9814();
        addapc.setAPCTYP("0");
        addapc.setCLRFLG("1");
    }

    public String initQry() {
        try {
            apcode = (apcode == null ? "" : apcode);
            M9814 m9814 = new M9814(glcode, apcode);
            m9814.setFUNCDE("1");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9814", m9814);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T862".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t862 = (T862) form.getFormBody();
                    } else if ("T814".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T814 t814 = (T814) form.getFormBody();
                        dataList.addAll(t814.getBeanList());
                    } else if (form.getFormHeader().getFormCode().contains("W012")) {
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addWarnWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            m9814.setBEGNUM("000451");
            List<SOFForm> formList2 = dataExchangeService.callSbsTxn("9814", m9814);
            for (SOFForm form : formList2) {
                if (form.getFormBody() != null) {
                    T814 t814 = (T814) form.getFormBody();
                    dataList.addAll(t814.getBeanList());
                    if (form.getFormHeader().getFormCode().contains("W012")) {
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
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
            addapc = new M9814(glcode, apcode);
            dataList = new ArrayList<>();
            List list = new ArrayList();
            SOFForm form = dataExchangeService.callSbsTxn("9814", addapc).get(0);
            if (!"T862".equals(form.getFormHeader().getFormCode())) {
                MessageUtil.addWarnWithClientID("msgs", form.getFormHeader().getFormCode());
            } else {
                t862 = (T862) form.getFormBody();
                list.add(t862);
                dataList.addAll(list);
            }
            if (dataList == null || dataList.isEmpty() && t862 == null) {
                MessageUtil.addWarn("Ã»ÓÐ²éÑ¯µ½Êý¾Ý¡£");
            }
        } catch (Exception e) {
            logger.error("²éÑ¯Ê§°Ü", e);
            MessageUtil.addError("²éÑ¯Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onAdd() {
        try {
            addapc.setMODFLG("1");
            addapc.setFUNCDE("4");
            SOFForm form = dataExchangeService.callSbsTxn("9814", addapc).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T404".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                addapcList.add(addapc);
                initAddapc();
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("ºËËãÂëÔö¼ÓÊ§°Ü", e);
            MessageUtil.addError("ºËËãÂëÔö¼ÓÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onUpdate() {
        try {
            String formcode = txn9814ForUD();
            if ("T404".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode + "ÐÞ¸Ä³É¹¦");
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
            String formcode = txn9814ForUD();
            if ("T404".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode + "É¾³ý³É¹¦");
                deleteable = false;
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("É¾³ýÊ§°Ü", e);
            MessageUtil.addError("É¾³ýÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // ÀûÂÊÐÞ¸ÄºÍÉ¾³ý
    private String txn9814ForUD() throws IllegalAccessException {
        M9814 m9814 = new M9814(glcode, apcode);
        BeanHelper.copyFields(t404, m9814);
        m9814.setMODFLG("1");
        if ("update".equals(action)) {
            m9814.setFUNCDE("2");
            m9814.setEBKCDE(apcode);
        } else if ("delete".equals(action)) {
            m9814.setFUNCDE("3");
        } else m9814.setFUNCDE("4");
        SOFForm form = dataExchangeService.callSbsTxn("9814", m9814).get(0);
        return form.getFormHeader().getFormCode();
    }

    public String onClick() {
        return "actapcBean";
    }
    public String onDelClick() {
        return "actapcDelMng";
    }

    public String onUpClick() {
        try {
            addapc = new M9814("0220", "0222");
            addapc.setFUNCDE("2");
            SOFForm form = dataExchangeService.callSbsTxn("9814", addapc).get(0);
            if ("T404".equals(form.getFormHeader().getFormCode())) {
                t404 = (T404) form.getFormBody();
            } else {
                MessageUtil.addWarnWithClientID("msgs", form.getFormHeader().getFormCode());
            }
        } catch (Exception e) {
            logger.error("ÐÞ¸ÄÊ§°Ü", e);
            MessageUtil.addError("ÐÞ¸ÄÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return "actBean";
    }

    public String onBack() {
        if ("detail".equals(action)) {
            return "actapcQry?faces-redirect=true&action=query";
        }
        return "actapcMng?faces-redirect=true&action=query";
    }

//---------------------------------------------------------------------------

    public T814 getT814() {
        return t814;
    }

    public void setT814(T814 t814) {
        this.t814 = t814;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getApcode() {
        return apcode;
    }

    public void setApcode(String apcode) {
        this.apcode = apcode;
    }
/*
    public String getApcnam() {
        return apcnam;
    }

    public void setApcnam(String apcnam) {
        this.apcnam = apcnam;
    }*/

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public List<T814.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T814.Bean> dataList) {
        this.dataList = dataList;
    }

    public T404 getT404() {
        return t404;
    }

    public void setT404(T404 t404) {
        this.t404 = t404;
    }

    public List<M9814> getAddapcList() {
        return addapcList;
    }

    public void setAddapcList(List<M9814> addapcList) {
        this.addapcList = addapcList;
    }

    public M9814 getAddapc() {
        return addapc;
    }

    public void setAddapc(M9814 addapc) {
        this.addapc = addapc;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
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

    public T862 getT862() {
        return t862;
    }

    public void setT862(T862 t862) {
        this.t862 = t862;
    }

}
