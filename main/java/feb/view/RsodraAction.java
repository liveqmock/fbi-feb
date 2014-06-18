package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T091;
import gateway.sbs.txn.model.form.re.T399;
import gateway.sbs.txn.model.msg.Ma130;
import gateway.sbs.txn.model.msg.Ma131;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  通知存款结清
 */
@ManagedBean
@ViewScoped
public class RsodraAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RsodraAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private T091 dra = new T091();
    private Ma130 ma130 = new Ma130();
    private Ma131 ma131 = new Ma131();

    private String auttlr;                     // 授权主管柜员号
    private String autpwd;                     // 授权主管密码


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

                    }else if ("T130".equals(form.getFormHeader().getFormCode())){

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

    //= = = =  = = = = = =  = = = =  = = = =  = = = = = = = = =

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
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
