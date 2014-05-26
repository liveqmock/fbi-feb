package feb.view;


import feb.print.model.Vchset;
import feb.service.DataExchangeService;
import feb.service.TemVchPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T898;
import gateway.sbs.txn.model.msg.M8420;
import gateway.sbs.txn.model.msg.M85a2;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.DateUtil;
import pub.tools.MessageUtil;
import pub.tools.SystemDate;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-26        重新答应的交易没有用8420，而是用的传票查询（85a2）的交易做的
 * Time: 上午8:44
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class RePrintVchAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RePrintVchAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{temVchPrintService}")
    private TemVchPrintService temVchPrintService;


    private String vchset = "";
    private String txntim;                //时间
    private String sysdat;                //日期
    private String tlrnum;                //柜员

    private boolean isPrint ;
    private T898 t898 = new T898();
    private M8420 m8420 = new M8420();
    M85a2 m85a2 = new M85a2();
    private List<T898.Bean> dataList = new ArrayList<>();
    private List<T898.Bean> allList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        tlrnum = SkylineService.getOperId();//============>得到当前柜员号
        sysdat =new SimpleDateFormat("yyyy/MM/dd").format(new SystemDate().getSysdate2());//sbs时间
    }

    public String onVchQry() {
        try {
            M85a2 m85a2 = new M85a2(vchset);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("85a2", m85a2);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                allList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T898".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t898 = (T898) form.getFormBody();
                        allList.addAll(t898.getBeanList());
                        for (T898.Bean bean : allList) {
                            if (!bean.getRECSTS().equals("I")) {
                                bean.setTMPAMT(new BigDecimal(bean.getTXNAMT()));//金额格式化
                                dataList.add(bean);
                                isPrint = true;
                            }
                        }
                        //tlrnum = t898.getFormBodyHeader().getTLRNUM();
                        vchset = t898.getFormBodyHeader().getVCHSET();
                        //totnum = t898.getFormBodyHeader().getTOTNUM();//总笔数
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addWarnWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            //MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
   /* public String onVchQry() {
        try {
            m8420 = new M8420(vchset);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8420", m8420);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                allList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T845".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t845 = (T845) form.getFormBody();
                        allList.addAll(t845.getBeanList());
                        for (T845.Bean bean : allList) {
                            if (!bean.getRECSTS().equals("I")) {
                                dataList.add(bean);
                            }
                        }
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addWarnWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
        }
        return null;
    }*/

    public void onPrint() {
        try {
            List<Vchset> vchs = new ArrayList<>();
            int printCnt = 0;
            for (T898.Bean bean : dataList) {
                if (!StringUtils.isEmpty(bean.getACTNUM()) || !StringUtils.isEmpty(bean.getTXNAMT())) {
                    printCnt++;
                    Vchset vch = new Vchset();
                    BeanHelper.copyFields(bean, vch);
                    DecimalFormat df = new DecimalFormat("###,###,##0.00");
                    vch.setTXNAMT(df.format(new BigDecimal(bean.getTXNAMT())));
                    vchs.add(vch);
                }
            }
            txntim = DateUtil.getCurrentTime();//系统时间
            temVchPrintService.printVch( vchset, sysdat, txntim,tlrnum,vchs);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    //- - - - - - - - - - - - --  -- - - - - - - - - -
    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public TemVchPrintService getTemVchPrintService() {
        return temVchPrintService;
    }

    public void setTemVchPrintService(TemVchPrintService temVchPrintService) {
        this.temVchPrintService = temVchPrintService;
    }

    public List<T898.Bean> getAllList() {
        return allList;
    }

    public void setAllList(List<T898.Bean> allList) {
        this.allList = allList;
    }

    public M85a2 getM85a2() {
        return m85a2;
    }

    public void setM85a2(M85a2 m85a2) {
        this.m85a2 = m85a2;
    }

    public String getVchset() {
        return vchset;
    }

    public void setVchset(String vchset) {
        this.vchset = vchset;
    }

    public boolean isPrint() {
        return isPrint;
    }

    public void setPrint(boolean print) {
        isPrint = print;
    }

    public T898 getT898() {
        return t898;
    }

    public void setT898(T898 t898) {
        this.t898 = t898;
    }

    public List<T898.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T898.Bean> dataList) {
        this.dataList = dataList;
    }
}
