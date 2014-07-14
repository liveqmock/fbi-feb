package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.msg.Mn090;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/7/14   IET-n090-内部总分账户余额清算
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class InnerActClearAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(InnerActClearAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private Mn090 mn090 = new Mn090();
    private boolean disable = false;

    public void onDeal() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("n090", mn090).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equals(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("交易失败", e);
            pub.platform.MessageUtil.addError("交易失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    // = = = = = = = = = =  = get set = = =  = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public Mn090 getMn090() {
        return mn090;
    }

    public void setMn090(Mn090 mn090) {
        this.mn090 = mn090;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}