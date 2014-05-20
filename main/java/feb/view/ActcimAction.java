package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T208;

import gateway.sbs.txn.model.form.re.T302;
import gateway.sbs.txn.model.msg.Mh820;
import gateway.sbs.txn.model.msg.Mh830;
import gateway.sbs.txn.model.form.re.T009;
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

@ManagedBean
@ViewScoped
public class ActcimAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ActcimAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private T208 t208 = new T208();
    private T009 cimqry = new T009();
    private Mh830 addcim = new Mh830();
    private Mh820 mh820;
    private String vchtyp;
    private String allone;
    private String orgidt;
    private String depnum;
    private String txntlr;

    private String ioflag;
    private String begnum;
    private String endnum;
    private String vchcnt;
    private List<Mh830> addcimList = new ArrayList<>();
    private List<T208> t208List = new ArrayList<>();
    private List<T302.Bean> dataList = new ArrayList<>();
    private List<T009.Bean> dataListPz = new ArrayList<>();
//    private List<T009.Bean> dataListOne = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        orgidt = "010";
        depnum = "60";


    }

    public String onAllQuery() {
        try {
//            if (allone.contentEquals("0")){
            Mh830 mh830 = new Mh830(vchtyp, allone, orgidt, depnum, txntlr);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("h830", mh830);
            List<SOFForm> formDemo = null;

            if (allone.equals("1") && vchtyp.equals("")) {
                MessageUtil.addError("单种查询必须输入凭证种类");
            } else {
                if (formList != null && !formList.isEmpty()) {
                    dataListPz = new ArrayList<>();
                    for (SOFForm form : formList) {

                        if ("T009".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                            T009 t009 = (T009) form.getFormBody();
                            dataListPz.addAll(t009.getBeanList());


                        } else {
                            logger.info(form.getFormHeader().getFormCode());

                            MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
//                            MessageUtil.addInfoWithClientID("msgs", "查询成功");
                        }
                    }
                }
                if (cimqry == null) {
                    MessageUtil.addWarn("没有查询到数据。");
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }

        return null;
    }

    public String cntNum() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        begnum = params.get("form:begnum");
        vchcnt = ((Integer.parseInt(endnum) - Integer.parseInt(begnum) + 1) + "");
        return vchcnt;
    }

    public String onDeal() {
        try {
            mh820 = new Mh820(vchtyp, ioflag, begnum, endnum, vchcnt);
            mh820.setVCHCNT((Integer.parseInt(endnum) - Integer.parseInt(begnum) + 1) + "");
            //BeanHelper.copyFields(cim, mh820);
            SOFForm form = dataExchangeService.callSbsTxn("h820", mh820).get(0);
            if ("T208".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                T208 t208 = (T208) form.getFormBody();
                t208List.add(t208);
            } else {
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            }
        } catch (Exception e) {
            logger.error("交易失败", e);
            MessageUtil.addError("交易失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // --------------------


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }


//    public List<T813.Bean> getDataList() {
//        return dataList;
//    }
//
//    public void setDataList(List<T813.Bean> dataList) {
//        this.dataList = dataList;
//    }


    public T009 getCimqry() {
        return cimqry;
    }

    public void setCimqry(T009 cimqry) {
        this.cimqry = cimqry;
    }

    public Mh830 getAddcim() {
        return addcim;
    }

    public void setAddcim(Mh830 addcim) {
        this.addcim = addcim;
    }

    public String getTxntlr() {
        return txntlr;
    }

    public void setTxntlr(String txntlr) {
        this.txntlr = txntlr;
    }

    public String getVchtyp() {
        return vchtyp;
    }

    public void setVchtyp(String vchtyp) {
        this.vchtyp = vchtyp;
    }

    public String getAllone() {
        return allone;
    }

    public void setAllone(String allone) {
        this.allone = allone;
    }

    public String getOrgidt() {
        return orgidt;
    }

    public void setOrgidt(String orgidt) {
        this.orgidt = orgidt;
    }

    public String getDepnum() {
        return depnum;
    }

    public void setDepnum(String depnum) {
        this.depnum = depnum;
    }

    public List<Mh830> getAddcimList() {
        return addcimList;
    }

    public void setAddcimList(List<Mh830> addcimList) {
        this.addcimList = addcimList;
    }

    public List<T302.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T302.Bean> dataList) {
        this.dataList = dataList;
    }

    public String getIoflag() {
        return ioflag;
    }

    public void setIoflag(String ioflag) {
        this.ioflag = ioflag;
    }

    public String getBegnum() {
        return begnum;
    }

    public void setBegnum(String begnum) {
        this.begnum = begnum;
    }

    public String getEndnum() {
        return endnum;
    }

    public void setEndnum(String endnum) {
        this.endnum = endnum;
    }

    public String getVchcnt() {
        return vchcnt;
    }

    public void setVchcnt(String vchcnt) {
        this.vchcnt = vchcnt;
    }

    public List<T009.Bean> getDataListPz() {
        return dataListPz;
    }

    public void setDataListPz(List<T009.Bean> dataListPz) {
        this.dataListPz = dataListPz;
    }

    public List<T208> getT208List() {
        return t208List;
    }

    public void setT208List(List<T208> t208List) {
        this.t208List = t208List;
    }

    public Mh820 getMh820() {
        return mh820;
    }

    public void setMh820(Mh820 mh820) {
        this.mh820 = mh820;
    }

    public T208 getT208() {
        return t208;
    }

    public void setT208(T208 t208) {
        this.t208 = t208;
    }
}
