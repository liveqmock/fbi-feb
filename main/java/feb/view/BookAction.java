package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T841;
import gateway.sbs.txn.model.msg.M8834;
import org.apache.commons.lang.StringUtils;
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
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-5
 * Time: 上午9:16
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class BookAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(BookAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String vchset = "";
    private String tlrnum = "";
    private String apcode = "";
    private String curcde = "";
    private String txnamt = "";
    private String cusidt = "";

    private List<T841.Bean> dataList = new ArrayList<>();
    private M8834 m8834;


    public String onVchsetQry() {
        try {
            m8834 = new M8834(curcde,apcode,tlrnum,vchset,cusidt,txnamt);
            m8834.setFUNCDE("6");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8834", m8834);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T841".equals(form.getFormHeader().getFormCode())) {
                        T841 t841 = (T841) form.getFormBody();
                        dataList.addAll(t841.getBeanList());
                    }else if ("W012".equals(form.getFormHeader().getFormCode())){

                    }else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("没有查询到数据。");
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

//================================================
    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getVchset() {
        return vchset;
    }

    public void setVchset(String vchset) {
        this.vchset = vchset;
    }

    public String getTlrnum() {
        return tlrnum;
    }

    public void setTlrnum(String tlrnum) {
        this.tlrnum = tlrnum;
    }

    public String getApcode() {
        return apcode;
    }

    public void setApcode(String apcode) {
        this.apcode = apcode;
    }

    public String getCurcde() {
        return curcde;
    }

    public void setCurcde(String curcde) {
        this.curcde = curcde;
    }

    public String getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(String txnamt) {
        this.txnamt = txnamt;
    }

    public String getCusidt() {
        return cusidt;
    }

    public void setCusidt(String cusidt) {
        this.cusidt = cusidt;
    }

    public List<T841.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T841.Bean> dataList) {
        this.dataList = dataList;
    }

    public M8834 getM8834() {
        return m8834;
    }

    public void setM8834(M8834 m8834) {
        this.m8834 = m8834;
    }
}
