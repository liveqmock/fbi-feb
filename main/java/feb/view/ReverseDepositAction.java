package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.msg.Ma000;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-4-8    ³åÕý
 * Time: ÉÏÎç9:27
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ReverseDepositAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RePrintVchAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private Ma000 ma000 = new Ma000();

    public String onReverse() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a000", ma000);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if (form.getFormHeader().getFormCode().startsWith("T")) {
                        MessageUtil.addInfoWithClientID("msgs", "W001");
                    }else {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        logger.error(form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("³åÕýÊ§°Ü", e);
            MessageUtil.addError("³åÕýÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public Ma000 getMa000() {
        return ma000;
    }

    public void setMa000(Ma000 ma000) {
        this.ma000 = ma000;
    }

}
