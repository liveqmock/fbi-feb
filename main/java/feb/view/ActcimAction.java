package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T208;

import gateway.sbs.txn.model.form.T302;
import gateway.sbs.txn.model.msg.Mh820;
import gateway.sbs.txn.model.msg.Mh830;
import gateway.sbs.txn.model.form.T009;
import gateway.sbs.txn.model.form.T302;
import org.apache.commons.lang.StringUtils;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 利率码
 */
@ManagedBean
@ViewScoped
public class ActcimAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ActcimAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
//    private T208 cim = new T208();
    private T009 cimqry = new T009();
    private Mh830 addcim = new Mh830();
    private Mh820 cim = new Mh820();
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
    private List<T302.Bean> dataList = new ArrayList<>();
//    private List<T009.Bean> dataListOne = new ArrayList<>();
//    private List<?> data = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        orgidt = "010";
        depnum = "60";

    }
    public String onAllQuery() {
        try {
            if (allone.contentEquals("1")){
            Mh830 mh830 = new Mh830(vchtyp,allone,orgidt,depnum,txntlr);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("h830", mh830);
            if (formList != null && !formList.isEmpty()) {
//                dataListOne = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T009".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        cimqry = (T009) form.getFormBody();
//                        T009 t009 = (T009) form.getFormBody();
//                        dataListOne.addAll(t009.getBeanList());
//                        data = new ArrayList<>(dataListOne);

                    }
                    else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (cimqry == null) {
                MessageUtil.addWarn("没有查询到数据。");
            }
            }
            else {
                   onAllT813Query();
            }
           /* if (DdataList == null || DdataList.isEmpty()) {
                MessageUtil.addWarn("没有查询到数据。");
            }*/
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }

        return null;
    }
    public String onAllT813Query() {
        try {

            Mh830 mh830= new Mh830(vchtyp,allone,orgidt,depnum,txntlr);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("h830", mh830);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T302".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T302 t302 = (T302) form.getFormBody();

                        dataList.addAll(t302.getBeanList());

//                        data = new ArrayList<>(dataList);
                    }
                    else {
                        logger.info(form.getFormHeader().getFormCode());
                        // MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
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


    public String onDeal() {
        try {
            String formcode = txnh820ForUD();
            if ("T208".equalsIgnoreCase(formcode)) {
                MessageUtil.addInfoWithClientID("msgs", formcode);
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("交易失败", e);
            MessageUtil.addError("交易失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // 利率修改和删除
    private String txnh820ForUD() throws IllegalAccessException {

        Mh820 mh820 = new Mh820();

        int a = Integer.parseInt(cim.getBEGNUM());
        int b = Integer.parseInt(cim.getENDNUM());
        int c = b - a + 1;
        cim.setVCHCNT(Integer.toString(c));
        BeanHelper.copyFields(cim, mh820);
        SOFForm form = dataExchangeService.callSbsTxn("h820", mh820).get(0);
        return form.getFormHeader().getFormCode();
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

//    public T208 getCim() {
//        return cim;
//    }
//
//    public void setCim(T208 cim) {
//        this.cim = cim;
//    }

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

    public Mh820 getCim() {
        return cim;
    }

    public void setCim(Mh820 cim) {
        this.cim = cim;
    }

}
