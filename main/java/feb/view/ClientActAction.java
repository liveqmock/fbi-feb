package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T101;
import gateway.sbs.txn.model.msg.M8101;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * 客户账户
 */
@ManagedBean
@ViewScoped
public class ClientActAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private M8101 clientAct = new M8101();
    private T101 rtnInfo;

    public String onCreate() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8101", clientAct).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnInfo = (T101) form.getFormBody();
                // TODO 打印
                MessageUtil.addInfo("客户账户建立成功，账号：" + rtnInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8101客户开户失败", e);
            MessageUtil.addError("8101客户开户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // ------------------------------

    public M8101 getClientAct() {
        return clientAct;
    }

    public void setClientAct(M8101 clientAct) {
        this.clientAct = clientAct;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public T101 getRtnInfo() {
        return rtnInfo;
    }

    public void setRtnInfo(T101 rtnInfo) {
        this.rtnInfo = rtnInfo;
    }
}
