package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T951;
import gateway.sbs.txn.model.msg.M8951;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Date: 14-4-30
 * Time: 上午9:18
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class OutSysConnAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(OutSysConnAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String funcde = "";
    private String anacde = "";
    private M8951 m8951 = new M8951();
    private T951 t951 = new T951();
    private T951.Bean[] selectedRecords;
    private List<T951.Bean> dataList = new ArrayList<>();


    @PostConstruct
    public void onConnQry() {
        try {
            m8951 = new M8951("0", "0");
            SOFForm form = dataExchangeService.callSbsTxn("8951", m8951).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T951".equalsIgnoreCase(formcode)) {
                t951 = (T951) form.getFormBody();
                dataList = t951.getBeanList();
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("交易失败", e);
            MessageUtil.addError("接口状态查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    /**
     * 单笔操作
     */
    public String onOpenConn(String funcde) {
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            anacde = StringUtils.isEmpty(params.get("anacde")) ? "" : params.get("anacde");
            m8951 = new M8951(funcde, "1", anacde);
            SOFForm form = dataExchangeService.callSbsTxn("8951", m8951).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T951".equalsIgnoreCase(formcode)) {
                t951 = (T951) form.getFormBody();
                dataList = t951.getBeanList();
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("交易失败", e);
            MessageUtil.addError("接口操作失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    /**
     * 多笔操作
     */
    public String onAllOpnfirm(String funcde) {
        try {
            m8951.setFUNCDE(funcde);
            m8951.setRECTYP("0");
            SOFForm form = dataExchangeService.callSbsTxn("8951", m8951).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T951".equalsIgnoreCase(formcode)) {
                t951 = (T951) form.getFormBody();
                dataList = t951.getBeanList();
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("批量操作失败", e);
            MessageUtil.addError("批量操作异常.");
        }
        return null;
    }

    //= = = = = == = = = = = = = = = = = = = = = = = = = = = = = =

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        OutSysConnAction.logger = logger;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getFuncde() {
        return funcde;
    }

    public void setFuncde(String funcde) {
        this.funcde = funcde;
    }


    public String getAnacde() {
        return anacde;
    }

    public void setAnacde(String anacde) {
        this.anacde = anacde;
    }

    public M8951 getM8951() {
        return m8951;
    }

    public void setM8951(M8951 m8951) {
        this.m8951 = m8951;
    }

    public T951 getT951() {
        return t951;
    }

    public void setT951(T951 t951) {
        this.t951 = t951;
    }

    public T951.Bean[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(T951.Bean[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public List<T951.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T951.Bean> dataList) {
        this.dataList = dataList;
    }
}
