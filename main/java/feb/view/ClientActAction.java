package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T101;
import gateway.sbs.txn.model.form.T109;
import gateway.sbs.txn.model.msg.M8101;
import gateway.sbs.txn.model.msg.M8103;
import gateway.sbs.txn.model.msg.M8109;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * 客户账户
 */
@ManagedBean
@ViewScoped
public class ClientActAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private M8101 clientAct = new M8101();     // 开户
    private M8109 closeAct = new M8109();      // 关户查询
    private T101 rtnActInfo;                      // 开关户返回信息
    private T109 closeActInfo;                 // 关户查询返回信息
    private boolean closeable = false;         // 是否可关户
    private String actnum;

    public String onCreate() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8101", clientAct).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                // TODO 打印
                MessageUtil.addInfo("客户账户建立成功，账号：" + rtnActInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8101客户开户失败", e);
            MessageUtil.addError("8101客户开户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onQryCloseAct() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8109", closeAct);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T109".equalsIgnoreCase(formcode)) {
                    closeActInfo = (T109) form.getFormBody();
                    closeable = true;
                    actnum = closeActInfo.getCUSIDT() + closeActInfo.getAPCODE() + closeActInfo.getCURCDE();
                } else if ("W313".equalsIgnoreCase(formcode)) {
                    MessageUtil.addWarnWithClientID("msgs", form.getFormHeader().getFormCode());
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("8109交易失败", e);
            MessageUtil.addError("8109交易失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClose() {
        try {
            M8103 m8103 = new M8103(closeAct.getACTNUM());
            SOFForm form = dataExchangeService.callSbsTxn("8103", m8103).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                closeable = false;
                // TODO 打印
                MessageUtil.addInfo("客户账户关闭成功，账号：" + rtnActInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8103关闭账户失败", e);
            MessageUtil.addError("8103关闭账户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
    // ------------------------------

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public M8101 getClientAct() {
        return clientAct;
    }

    public void setClientAct(M8101 clientAct) {
        this.clientAct = clientAct;
    }

    public M8109 getCloseAct() {
        return closeAct;
    }

    public void setCloseAct(M8109 closeAct) {
        this.closeAct = closeAct;
    }

    public T101 getRtnActInfo() {
        return rtnActInfo;
    }

    public void setRtnActInfo(T101 rtnActInfo) {
        this.rtnActInfo = rtnActInfo;
    }

    public T109 getCloseActInfo() {
        return closeActInfo;
    }

    public void setCloseActInfo(T109 closeActInfo) {
        this.closeActInfo = closeActInfo;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    public String getActnum() {
        return actnum;
    }

    public void setActnum(String actnum) {
        this.actnum = actnum;
    }
}
