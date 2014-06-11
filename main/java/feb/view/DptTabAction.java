package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T228;
import gateway.sbs.txn.model.form.re.T229;
import gateway.sbs.txn.model.msg.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/10
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class DptTabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(DebTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String dpttyp;
    private String chfmak;
    private String action;
    private boolean updateable = false;
    private boolean deleteable = false;
    private T229 t229 = new T229();
    private T228 t228 = new T228();
    private Ma825 ma825 = new Ma825();
    private Ma822 ma822 = new Ma822();
    private Ma823 ma823 = new Ma823();
    private Ma821 ma821 = new Ma821();
    private Ma824 ma824 = new Ma824();
    private List<T229.Bean> dataList;

    private T229.Bean beanSelected;

    @PostConstruct
    public void init() {
        onAllQry();
    }

    public String onAllQry() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a825", ma825);
            if (!formList.isEmpty() && formList != null) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T229".equals(form.getFormHeader().getFormCode())) {
                        t229 = (T229) form.getFormBody();
                        dataList.addAll(t229.getBeanList());

                        for (int j = 0; j < dataList.size(); j++) {
                            dataList.get(j).setPkid(UUID.randomUUID().toString());
                        }
                    } else if ("W001".equals(form.getFormHeader().getFormCode())) {

                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("²éÑ¯Ê§°Ü", e);
            MessageUtil.addError("²éÑ¯Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    /*public String onDetailQry() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a823", ma823);
            if (!formList.isEmpty() && formList != null) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T228".equals(form.getFormHeader().getFormCode())) {
                        t228 = (T228) form.getFormBody();
                    } else if ("W001".equals(form.getFormHeader().getFormCode())) {

                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("²éÑ¯Ê§°Ü", e);
            MessageUtil.addError("²éÑ¯Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }*/

    public String onUpdate() {
        try {
            //BeanHelper.copyFields(t228, ma824);
            int position = dataList.indexOf(beanSelected);
            dataList.remove(beanSelected);
            SOFForm form = dataExchangeService.callSbsTxn("a824", ma824).get(0);
            if ("W001".equals(form.getFormHeader().getFormCode())) {
                BeanUtils.copyProperties(beanSelected, ma824);
                dataList.add(position, beanSelected);
                logger.info(form.getFormHeader().getFormCode());
                MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
            } else {
                logger.error(form.getFormHeader().getFormCode());
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            }
        } catch (Exception e) {
            logger.error("ÐÞ¸ÄÊ§°Ü", e);
            MessageUtil.addError("ÐÞ¸ÄÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }


    public String onDelete() {
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            dpttyp = StringUtils.isEmpty(params.get("dpttyp")) ? "" : params.get("dpttyp");
            chfmak = StringUtils.isEmpty(params.get("chfmak")) ? "" : params.get("chfmak");
            ma822.setDPTTYP(dpttyp);
            ma822.setCHFMAK(chfmak);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a822", ma822);
            if (!formList.isEmpty() && formList != null) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("W004".equals(form.getFormHeader().getFormCode())) {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("É¾³ýÊ§°Ü", e);
            MessageUtil.addError("É¾³ýÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onAdd() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a821", ma821);
            if (!formList.isEmpty() && formList != null) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("W005".equals(form.getFormHeader().getFormCode())) {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Ìí¼ÓÊ§°Ü", e);
            MessageUtil.addError("Ìí¼ÓÊ§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public void onRowSelect(SelectEvent event) {
        beanSelected = (T229.Bean) event.getObject();
        try {
            BeanUtils.copyProperties(ma824, beanSelected);
        } catch (Exception e) {
            logger.error("Àà¸´ÖÆ³ö´í", e);
            MessageUtil.addError("Àà¸´ÖÆ³ö´í." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    //= = = = = = = = = = = = = = = = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getDpttyp() {
        return dpttyp;
    }

    public void setDpttyp(String dpttyp) {
        this.dpttyp = dpttyp;
    }

    public String getChfmak() {
        return chfmak;
    }

    public void setChfmak(String chfmak) {
        this.chfmak = chfmak;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }

    public boolean isDeleteable() {
        return deleteable;
    }

    public void setDeleteable(boolean deleteable) {
        this.deleteable = deleteable;
    }

    public T229 getT229() {
        return t229;
    }

    public void setT229(T229 t229) {
        this.t229 = t229;
    }

    public T228 getT228() {
        return t228;
    }

    public void setT228(T228 t228) {
        this.t228 = t228;
    }

    public Ma823 getMa823() {
        return ma823;
    }

    public void setMa823(Ma823 ma823) {
        this.ma823 = ma823;
    }

    public Ma822 getMa822() {
        return ma822;
    }

    public void setMa822(Ma822 ma822) {
        this.ma822 = ma822;
    }

    public Ma821 getMa821() {
        return ma821;
    }

    public void setMa821(Ma821 ma821) {
        this.ma821 = ma821;
    }

    public Ma824 getMa824() {
        return ma824;
    }

    public void setMa824(Ma824 ma824) {
        this.ma824 = ma824;
    }

    public Ma825 getMa825() {
        return ma825;
    }

    public void setMa825(Ma825 ma825) {
        this.ma825 = ma825;
    }

    public List<T229.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T229.Bean> dataList) {
        this.dataList = dataList;
    }

    public T229.Bean getBeanSelected() {
        return beanSelected;
    }

    public void setBeanSelected(T229.Bean beanSelected) {
        this.beanSelected = beanSelected;
    }
}
