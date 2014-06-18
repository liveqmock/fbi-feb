package feb.view;

import feb.print.model.Vch;
import feb.service.DataExchangeService;
import feb.service.TemSqrPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T016;
import gateway.sbs.txn.model.form.re.T091;
import gateway.sbs.txn.model.form.re.T106;
import gateway.sbs.txn.model.form.re.T130;
import gateway.sbs.txn.model.msg.Ma130;
import gateway.sbs.txn.model.msg.Ma131;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  通知存款结清
 */
@ManagedBean
@ViewScoped
public class RsodraAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RsodraAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{temSqrPrintService}")
    private TemSqrPrintService temSqrPrintService;

    private T091 dra = new T091();
    private T130 t130 = new T130();
    private T016 t016 = new T016();
    private Ma130 ma130 = new Ma130();
    private Ma131 ma131 = new Ma131();

    private String auttlr;                     // 授权主管柜员号
    private String autpwd;                     // 授权主管密码
    private boolean printable = false;


    @PostConstruct
    public void init() {
        ma130.setACTTY1("07");
        ma130.setACTTY2("01");
        ma130.setTXNDAT(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        ma131.setACTTY1("07");
        ma131.setACTTY2("01");
        ma131.setTXNDAT(new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    public String onAllQuery() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a130", ma130);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T091".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        dra = (T091) form.getFormBody();
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

    public String onDeal() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn(auttlr, autpwd, "a131", ma131);
            if (formList != null && !formList.isEmpty()){
                for (SOFForm form : formList){
                    if ("T091".equals(form.getFormHeader().getFormCode())) {
                        dra = (T091) form.getFormBody();
                    } else if ("T016".equals(form.getFormHeader().getFormCode())){
                        t016 = (T016) form.getFormBody();
                    }else if ("T130".equals(form.getFormHeader().getFormCode())){
                        t130 = (T130) form.getFormBody();
                    }else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("结清失败", e);
            MessageUtil.addError("结清失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }


    public void onPrintOpenAct() {
        try {
            List<Vch> vchs = new ArrayList<>();
            int printCnt = 0;
            for (T016.Bean bean : t016.getBeanList()) {
                if (!StringUtils.isEmpty(bean.getDEBACT()) || !StringUtils.isEmpty(bean.getDEBAMT())) {
                    printCnt++;
                    logger.info(t016.getVCHSET() + " :  " + bean.getDEBACT() + bean.getDEBAMT() + bean.getCREACT() + bean.getCREAMT());
                    Vch vch = new Vch();
                    BeanHelper.copyFields(bean, vch);
                    vchs.add(vch);
                }
            }

            temSqrPrintService.printVch(
                    "联机传票/复核（授权）清单", t016.getTRNCDE(),t016.getTLRNUM(), t016.getTRNTIM(),
                    t016.getVCHSET(),t016.getTRNDAT(), vchs,"利息单",t130);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    //= = = =  = = = = = =  = = = =  = = = =  = = = = = = = = =

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public TemSqrPrintService getTemSqrPrintService() {
        return temSqrPrintService;
    }

    public void setTemSqrPrintService(TemSqrPrintService temSqrPrintService) {
        this.temSqrPrintService = temSqrPrintService;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
    }

    public T130 getT130() {
        return t130;
    }

    public void setT130(T130 t130) {
        this.t130 = t130;
    }

    public T016 getT016() {
        return t016;
    }

    public void setT016(T016 t016) {
        this.t016 = t016;
    }

    public Ma130 getMa130() {
        return ma130;
    }

    public void setMa130(Ma130 ma130) {
        this.ma130 = ma130;
    }

    public Ma131 getMa131() {
        return ma131;
    }

    public void setMa131(Ma131 ma131) {
        this.ma131 = ma131;
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

    public T091 getDra() {
        return dra;
    }

    public void setDra(T091 dra) {
        this.dra = dra;
    }

}
