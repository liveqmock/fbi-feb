package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T132;
import gateway.sbs.txn.model.msg.M0003;
import gateway.sbs.txn.model.msg.Ma270;
import gateway.sbs.txn.model.msg.Ma271;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定期存款
 */
@ManagedBean
@ViewScoped
public class TimeDepositAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(TimeDepositAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private Ma271 ma271;
    private T132 qryT132;
    private boolean printable = false;
    private BigDecimal bdTxnamt;   // 交易金额

    @PostConstruct
    public void init() {
        ma271 = new Ma271();
        ma271.setVALDAT(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        qryT132 = new T132();
    }

    public String onQry() {
        try {
            Ma270 ma270 = new Ma270();
//            ma271.setTXNAMT(bdTxnamt.toString());
            BeanHelper.copyFields(ma271, ma270);
            SOFForm form = dataExchangeService.callSbsTxn("a270", ma270).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T132".equalsIgnoreCase(formcode)) {
                qryT132 = (T132) form.getFormBody();
                ma271.setACTNM2(qryT132.getACTNAM());
                ma271.setPASTYP(qryT132.getPASTYP());
                ma271.setPASSNO(qryT132.getPASSNO());
                ma271.setCORADD(qryT132.getCORADD());
                ma271.setTELNUM(qryT132.getTELNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("定期存款开户查询失败", e);
            MessageUtil.addError("定期存款开户查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onCreate() {

        printable = true;
        return null;
    }

    public void onPrint() {

    }

    // ------------------------------------------------------

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public Ma271 getMa271() {
        return ma271;
    }

    public void setMa271(Ma271 ma271) {
        this.ma271 = ma271;
    }

    public BigDecimal getBdTxnamt() {
        return bdTxnamt;
    }

    public void setBdTxnamt(BigDecimal bdTxnamt) {
        this.bdTxnamt = bdTxnamt;
    }

    public T132 getQryT132() {
        return qryT132;
    }

    public void setQryT132(T132 qryT132) {
        this.qryT132 = qryT132;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
    }
}
