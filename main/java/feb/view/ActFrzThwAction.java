package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T225;
import gateway.sbs.txn.model.msg.M7280;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;
import pub.tools.SystemDate;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/9
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ActFrzThwAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ActFrzThwAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String auttlr;
    private String autpwd;
    private T225 t225 = new T225();
    private M7280 m7280 = new M7280();

    @PostConstruct
    public void init(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        SystemDate systemDate = new SystemDate();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(systemDate.getSysdate2());
        rightNow.add(Calendar.MONTH, 6);
        Date dt2 = rightNow.getTime();
        String str2 = sdf.format(dt2);
        m7280.setFRZDAT(systemDate.getSysdate1());
        m7280.setFRZEDT(str2);
    }
    public String onFrzThw() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn(auttlr,autpwd,"7280", m7280).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T225".equals(formcode)) {
                t225 = (T225) form.getFormBody();
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("冻结及解冻失败", e);
            MessageUtil.addError("冻结及解冻失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //= = = = = = = = = = = = = = = = = = = = =

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

    public T225 getT225() {
        return t225;
    }

    public void setT225(T225 t225) {
        this.t225 = t225;
    }

    public M7280 getM7280() {
        return m7280;
    }

    public void setM7280(M7280 m7280) {
        this.m7280 = m7280;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }
}
