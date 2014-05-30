package feb.view;

import feb.service.DataExchangeService;
import feb.service.TemRosPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T220;
import gateway.sbs.txn.model.msg.Ma111;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.utils.StringMathFormat;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 */
@ManagedBean
@ViewScoped
public class RsondnAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RsondnAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{temRosPrintService}")
    private TemRosPrintService temRosPrintService;

    private T220 t220 = new T220();
    private Ma111 ma111 = new Ma111();

    private String auttlr;                     // 授权主管柜员号
    private String autpwd;                     // 授权主管密码

    private boolean printable = false;


    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

    }

    public String onDraNot() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn(auttlr, autpwd, "a111", ma111);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T220".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t220 = (T220) form.getFormBody();
                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public void onPrintOpenAct() {
        try {
            String txnamt = StringMathFormat.strFormat2(t220.getTXNAMT().toString());
            temRosPrintService.printRosdn("七天通知存款通知单",t220.getTXNCDE(),t220.getTELLER(),t220.getTXNDAT(),
                    t220.getIPTAC(),t220.getADVDAT(),t220.getACTNAM(),t220.getINTCUR(),txnamt,
                    t220.getADVNUM(),t220.getREMARK());
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public TemRosPrintService getTemRosPrintService() {
        return temRosPrintService;
    }

    public void setTemRosPrintService(TemRosPrintService temRosPrintService) {
        this.temRosPrintService = temRosPrintService;
    }

    public T220 getT220() {
        return t220;
    }

    public void setT220(T220 t220) {
        this.t220 = t220;
    }

    public String getAuttlr() {
        return auttlr;
    }

    public void setAuttlr(String auttlr) {
        this.auttlr = auttlr;
    }

    public String getAutpwd() {
        return autpwd;
    }

    public void setAutpwd(String autpwd) {
        this.autpwd = autpwd;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
    }

    public Ma111 getMa111() {
        return ma111;
    }

    public void setMa111(Ma111 ma111) {
        this.ma111 = ma111;
    }
}
