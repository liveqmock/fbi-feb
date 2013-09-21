package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T804;
import gateway.sbs.txn.model.form.T805;
import gateway.sbs.txn.model.form.T814;
import gateway.sbs.txn.model.form.T815;
import gateway.sbs.txn.model.msg.M9804;
import gateway.sbs.txn.model.msg.M9805;
import gateway.sbs.txn.model.msg.M9814;
import gateway.sbs.txn.model.msg.M9815;
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
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-3
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ActapcAction  implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ActapcAction.class);

    private String action;
    private String glcode;
    private String apcode;
    private String apcnam;
    private String apctyp;
    private boolean updateable = false;
    private boolean deleteable = false;
    private boolean readonly = false;

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private T814 apc = new T814();
    private M9814 addapc ;
    private List<T815.Bean> dataList = new ArrayList<>();
    private List<M9814> addapcList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        glcode = params.get("glcode");
        apcode = params.get("apcode");
        apcnam = params.get("apcnam");
        apctyp = params.get("apctyp");
        action = params.get("action");
        if (!StringUtils.isEmpty(glcode)) {
            M9814 m9814 = new M9814(glcode, apcode, apcnam, "0");
            SOFForm form = dataExchangeService.callSbsTxn("9814", m9814).get(0);
            if (!"T814".equals(form.getFormHeader().getFormCode())) {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            } else {
                apc = (T814) form.getFormBody();
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
        addapc = new M9814();
        addapc.setApctyp("0");

    }

    public String onAllQuery() {
        try {
            M9815 m9815 = new M9815(glcode, apcode);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9815", m9815);
            System.out.print(formList.size());
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                       //System.out.print("form长度："+form.length);
                    if (!"T815".equals(form.getFormHeader().getFormCode()) &&
                            !"W014".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T815".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T815 t815 = (T815) form.getFormBody();
                        dataList.addAll(t815.getBeanList());
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("没有查询到数据。");
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "null" : e.getMessage()));
        }
        return null;
    }

    public String onClick() {
        return "actapcBean";
    }

    public String onAdd() {
        try {
            addapc.setMODFLG("1");
            addapc.setFUNCDE("4");
            SOFForm form = dataExchangeService.callSbsTxn("9814", addapc ).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W005".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
                addapcList.add(addapc);
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("利率增加失败", e);
            MessageUtil.addError("利率增加失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
    public String onDelete() {
        try {
            String formcode = txn9814ForUD();
            if ("W014".equalsIgnoreCase(formcode)) {
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

    public String onUpdate() {
        try {
            String formcode = txn9814ForUD();
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

    private String txn9814ForUD() throws IllegalAccessException {
        M9814 m9814 = new M9814(apc.getGlcode(), apc.getApcode() + apc.getApcnam(), apc.getApctyp(), null);
        BeanHelper.copyFields(apc, m9814);
        m9814.setMODFLG("1");
        if ("update".equals(action)) m9814.setFUNCDE("2");
        else if ("delete".equals(action)) m9814.setFUNCDE("3");
        else m9814.setFUNCDE("4");
        SOFForm form = dataExchangeService.callSbsTxn("9814", m9814).get(0);
        return form.getFormHeader().getFormCode();
    }

//===============================================================================

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getApcnam() {
        return apcnam;
    }

    public void setApcnam(String apcnam) {
        this.apcnam = apcnam;
    }

    public String getApctyp() {
        return apctyp;
    }

    public void setApctyp(String apctyp) {
        this.apctyp = apctyp;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }
    public List<T815.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T815.Bean> dataList) {
        this.dataList = dataList;
    }

    public String onBack() {
        return "actapcMng?faces-redirect=true&action=query";
    }

    public static Logger getLogger() {
        return logger;
    }

    public T814 getApc() {
        return apc;
    }

    public void setApc(T814 apc) {
        this.apc = apc;
    }

    public M9814 getAddapc() {
        return addapc;
    }

    public void setAddapc(M9814 addapc) {
        this.addapc = addapc;
    }

    public List<M9814> getAddapcList() {
        return addapcList;
    }

    public void setAddapcList(List<M9814> addapcList) {
        this.addapcList = addapcList;
    }


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

}
