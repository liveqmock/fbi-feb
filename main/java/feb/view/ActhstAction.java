package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T851;
import gateway.sbs.txn.model.msg.M8823;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
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
 * 利率码
 */
@ManagedBean
@ViewScoped
public class ActhstAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ActhstAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private T851 hst = new T851();
//    private M8823 hstDay = new M8823();
    private String totcnt;
    private String curcnt;

    private String cusidt;
    private String apcode;
    private String curcde;
    private String eryda1;
    private String eryda2;
    private String secamt;
    private String ovelim;
    private String tlenum;
    private String vchset;
    private String funcde;
    private String regadd;
    private String begnum;

    private List<M8823> hstList = new ArrayList<>();
    private List<T851.Bean> dataList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        cusidt = "";
        regadd = "";
        begnum = "";


    }
    public String onAllQuery() {
        try {
//            if (allone.contentEquals("0")){
            M8823 m8823 = new M8823(cusidt,apcode,curcde,eryda1,eryda2,secamt,ovelim,tlenum,vchset,funcde,regadd,begnum);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8823", m8823);

                 if (formList != null && !formList.isEmpty()) {
                        dataList = new ArrayList<>();

                    for (SOFForm form : formList) {

                        if ("T851".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                            T851 t851 = (T851) form.getFormBody();
                            totcnt = t851.getFormBodyHeader().getTOTCNT();
                            curcnt = t851.getFormBodyHeader().getCURCNT();
                            dataList.addAll(t851.getBeanList());
                        }
                        else {
                            logger.info(form.getFormHeader().getFormCode());

                            MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
//                            MessageUtil.addInfoWithClientID("msgs", "查询成功");
                        }
                    }
                    }
//                if (cimqry == null) {
//                    MessageUtil.addWarn("没有查询到数据。");
//                }
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

    public String getCusidt() {
        return cusidt;
    }

    public void setCusidt(String cusidt) {
        this.cusidt = cusidt;
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

    public String getEryda1() {
        return eryda1;
    }

    public void setEryda1(String eryda1) {
        this.eryda1 = eryda1;
    }

    public String getEryda2() {
        return eryda2;
    }

    public void setEryda2(String eryda2) {
        this.eryda2 = eryda2;
    }

    public String getSecamt() {
        return secamt;
    }

    public void setSecamt(String secamt) {
        this.secamt = secamt;
    }

    public String getOvelim() {
        return ovelim;
    }

    public void setOvelim(String ovelim) {
        this.ovelim = ovelim;
    }

    public String getTlenum() {
        return tlenum;
    }

    public void setTlenum(String tlenum) {
        this.tlenum = tlenum;
    }

    public String getVchset() {
        return vchset;
    }

    public void setVchset(String vchset) {
        this.vchset = vchset;
    }

    public String getFuncde() {
        return funcde;
    }

    public void setFuncde(String funcde) {
        this.funcde = funcde;
    }

    public String getRegadd() {
        return regadd;
    }

    public void setRegadd(String regadd) {
        this.regadd = regadd;
    }

    public String getBegnum() {
        return begnum;
    }

    public void setBegnum(String begnum) {
        this.begnum = begnum;
    }

    public T851 getHst() {
        return hst;
    }

    public void setHst(T851 hst) {
        this.hst = hst;
    }
    public List<M8823> getHstList() {
        return hstList;
    }

    public void setHstList(List<M8823> hstList) {
        this.hstList = hstList;
    }

    public List<T851.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T851.Bean> dataList) {
        this.dataList = dataList;
    }

    public String getTotcnt() {
        return totcnt;
    }

    public void setTotcnt(String totcnt) {
        this.totcnt = totcnt;
    }

    public String getCurcnt() {
        return curcnt;
    }

    public void setCurcnt(String curcnt) {
        this.curcnt = curcnt;
    }
}
