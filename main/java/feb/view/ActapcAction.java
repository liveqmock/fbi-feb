package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T404;
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
    private String apctyp;
    private String action;

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private List<T862.Bean> dataList = new ArrayList<>();
    private T404 apc = new T404();
    private boolean updateable = false;
    private boolean deleteable = false;
    private boolean readonly = false;
    private List<M9814> addapcList = new ArrayList<>();
    private M9814 addapc ;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        apcode = params.get("apcode");
        apctyp = params.get("apctyp");
        action = params.get("action");
        if (!StringUtils.isEmpty(apcode)) {
            M9814 m9814 = new M9814(glcode, apcode);
            SOFForm form = dataExchangeService.callSbsTxn("9814", m9814).get(0);
            if (!"T862".equals(form.getFormHeader().getFormCode())) {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            } else {
                apc = (T404) form.getFormBody();
            }
        } else {
            // Ìí¼Óapc ³õÊ¼»¯ addapc
            initAddapc();
        }
        if ("update".equals(action)) {
            updateable = true;
            readonly = true;
        }
        if ("delete".equals(action)) {
            deleteable = true;
            readonly = true;
        }
        if ("detail".equals(action)) {
            deleteable = true;
            readonly = true;
        }
        if ("query".equals(action)) onAllQuery();
    }

    private void initAddapc() {
        addapc = new M9814();
        //addapc.setAPCODE("1119");
        //addapc.setAPCNAM("WW");
        addapc.setAPCTYP("0");
        addapc.setGLCODE(glcode);
        addapc.setCLRFLG("1");

    }
    public String onAllQuery() {
        try {
            //addapc.setFUNCDE("2");
            M9814 m9814 = new M9814(glcode, apcode);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9814", m9814);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if (!"T862".equals(form.getFormHeader().getFormCode()) &&
                            !"W001".equals(form.getFormHeader().getFormCode())) {
                       MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T862".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T862 t862 = (T862) form.getFormBody();
                        dataList.addAll(t862.getBeanList());
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
//                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
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
            if ("T862".equalsIgnoreCase(formcode)) {
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
            String formcode = txn9814ForUD();
            if ("W004".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
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
    // ºËËãÂëÐÞ¸ÄºÍÉ¾³ý
    private String txn9814ForUD() throws IllegalAccessException {
        M9814 m9814 = new M9814(apc.getGLCODE(), apc.getAPCODE());
        BeanHelper.copyFields(apc, m9814);
        m9814.setMODFLG("1");
        if ("update".equals(action)) m9814.setFUNCDE("2");
        else if ("delete".equals(action)) m9814.setFUNCDE("3");
        else m9814.setFUNCDE("4");
        SOFForm form = dataExchangeService.callSbsTxn("9814", m9814).get(0);
        return form.getFormHeader().getFormCode();
    }
    public String onClick() {
        return "actapcBean";
    }
    public String onBack() {
        return "actapcMng?faces-redirect=true&action=query";
    }

//---------------------------------------------------------------------------
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

    public String getApctyp() {
        return apctyp;
    }

    public void setApctyp(String apctyp) {
        this.apctyp = apctyp;
    }

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

    public List<T862.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T862.Bean> dataList) {
        this.dataList = dataList;
    }

    public T404 getApc() {
        return apc;
    }

    public void setApc(T404 apc) {
        this.apc = apc;
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

}
