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
 * Date: 14-4-8
 * Time: ÉÏÎç9:27
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ReverseDepositAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RePrintVchAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private Ma000 ma000 ;
    private String rvskey;
    private String rvstcd;
    private String actty1;
    private String iptac1;
    private String actty2;
    private String iptac2;
    private String txnamt;
    private String remark;

    public String onReverse() {
        try {
            Ma000 ma000 = new Ma000(rvskey, rvstcd,actty1, iptac1,
                    actty2,iptac2,txnamt,remark);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a000", ma000);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if (!"W001".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        logger.info(form.getFormHeader().getFormCode());
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

    public String getRvskey() {
        return rvskey;
    }

    public void setRvskey(String rvskey) {
        this.rvskey = rvskey;
    }

    public String getRvstcd() {
        return rvstcd;
    }

    public void setRvstcd(String rvstcd) {
        this.rvstcd = rvstcd;
    }

    public String getActty1() {
        return actty1;
    }

    public void setActty1(String actty1) {
        this.actty1 = actty1;
    }

    public String getIptac1() {
        return iptac1;
    }

    public void setIptac1(String iptac1) {
        this.iptac1 = iptac1;
    }

    public String getActty2() {
        return actty2;
    }

    public void setActty2(String actty2) {
        this.actty2 = actty2;
    }

    public String getIptac2() {
        return iptac2;
    }

    public void setIptac2(String iptac2) {
        this.iptac2 = iptac2;
    }

    public String getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(String txnamt) {
        this.txnamt = txnamt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
