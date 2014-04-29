package feb.view;

import feb.service.DataExchangeService;
import feb.service.RosPrintService;
import feb.service.TemRosPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T220;
import gateway.sbs.txn.model.msg.Ma121;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 */
@ManagedBean
@ViewScoped
public class RsondpAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RsondpAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{temRosPrintService}")
    private TemRosPrintService temRosPrintService;
    /*@ManagedProperty(value = "#{rosPrintService}")
    private RosPrintService rosPrintService;*/

    private T220 ndp = new T220();

    private String auttlr;                     // 授权主管柜员号
    private String autpwd;                     // 授权主管密码


    private String actty2;
    private String iptac2;
    private String dramd2;

    private boolean printable = false;


    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        actty2 = "07";
        dramd2 = "0";

    }

    public String onAllQuery() {
        try {
//            if (allone.contentEquals("0")){
            Ma121 ma121 = new Ma121(actty2, iptac2, dramd2);
            List<SOFForm> formList = dataExchangeService.callSbsTxn(auttlr, autpwd, "a121", ma121);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T220".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T220 t220 = (T220) form.getFormBody();
                        ndp = t220;
                    } else {
                        logger.info(form.getFormHeader().getFormCode());

                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
//                            MessageUtil.addInfoWithClientID("msgs", "查询成功");
                    }
                }
            }
            if (ndp == null) {
                MessageUtil.addWarn("没有查询到数据。");
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public void onPrintOpenAct() {
        try {
            String txnamt = StringMathFormat.strFormat2(ndp.getTXNAMT().toString());
            temRosPrintService.printRosdn("七天撤销存款通知单",ndp.getTXNCDE(),ndp.getTELLER(),ndp.getTXNDAT(),
                    ndp.getIPTAC(),ndp.getADVDAT(),ndp.getACTNAM(),ndp.getINTCUR(),txnamt,
                    ndp.getADVNUM(),ndp.getREMARK());
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
   /* public void onPrintOpenAct() {
        try {
            if (ndp != null) {
                rosPrintService.printRevoNote("撤销通知单", ndp);
            }
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }*/


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

    public String getDramd2() {
        return dramd2;
    }

    public void setDramd2(String dramd2) {
        this.dramd2 = dramd2;
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

    public T220 getNdp() {
        return ndp;
    }

    public void setNdp(T220 ndp) {
        this.ndp = ndp;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
    }
}
