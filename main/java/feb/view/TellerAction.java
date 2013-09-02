package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.msg.M0003;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * 柜员管理
 */
@ManagedBean
@ViewScoped
public class TellerAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(TellerAction.class);

    private String signoutTlr;    // 要强制签退的柜员
    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    public String onForceSignout() {
        try {
            M0003 m0003 = new M0003(signoutTlr);
            SOFForm form = dataExchangeService.callSbsTxn("0003", m0003).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("0003强制签退柜员失败", e);
            MessageUtil.addError("0003强制签退柜员失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // ------------------------------


    public String getSignoutTlr() {
        return signoutTlr;
    }

    public void setSignoutTlr(String signoutTlr) {
        this.signoutTlr = signoutTlr;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }
}
