package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T091;
import gateway.sbs.txn.model.msg.Ma130;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@ManagedBean
@ViewScoped
public class RsodraAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RsodraAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private T091 dra = new T091();

    private String auttlr;                     // 授权主管柜员号
    private String autpwd;                     // 授权主管密码


    private String actty1;
    private String iptac1;
    private String dramd1;
    private String txndat;
    private String advnum;
    private String txnamt;
    private String actty2;
    private String iptac2;

    private List<Ma130> hstList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        actty1 = "07";
        iptac1 = "010600012000002235";
        dramd1 = "0";
        actty2 = "01";
        iptac2 = "801000000432012001";

    }
    public String onAllQuery() {
        try {
//            if (allone.contentEquals("0")){
            Ma130 ma130 = new Ma130(actty1,iptac1,dramd1,txndat,advnum,txnamt,actty2,iptac2);
            List<SOFForm> formList = dataExchangeService.callSbsTxn(auttlr, autpwd,"a130", ma130);
                 if (formList != null && !formList.isEmpty()) {
                    for (SOFForm form : formList) {
                        if ("T091".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                            T091 t091 = (T091) form.getFormBody();
                            dra = t091;
                        }
                        else {
                            logger.info(form.getFormHeader().getFormCode());

                            MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
//                            MessageUtil.addInfoWithClientID("msgs", "查询成功");
                        }
                    }
                    }
                if (dra == null) {
                    MessageUtil.addWarn("没有查询到数据。");
                }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }

        return null;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
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

    public String getDramd1() {
        return dramd1;
    }

    public void setDramd1(String dramd1) {
        this.dramd1 = dramd1;
    }

    public String getTxndat() {
        return txndat;
    }

    public void setTxndat(String txndat) {
        this.txndat = txndat;
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

    public String getAdvnum() {
        return advnum;
    }

    public void setAdvnum(String advnum) {
        this.advnum = advnum;
    }

    public String getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(String txnamt) {
        this.txnamt = txnamt;
    }

    public List<Ma130> getHstList() {
        return hstList;
    }

    public void setHstList(List<Ma130> hstList) {
        this.hstList = hstList;
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
