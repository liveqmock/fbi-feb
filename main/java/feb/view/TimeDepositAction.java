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
 * 定期存款
 */
@ManagedBean
@ViewScoped
public class TimeDepositAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(TimeDepositAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    public String onQry() {
        try {

        } catch (Exception e) {
            logger.error("定期存款开户查询失败", e);
            MessageUtil.addError("定期存款开户查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // ------------------------------------------------------

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }
}
