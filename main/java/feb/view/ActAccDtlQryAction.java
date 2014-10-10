package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T923;
import gateway.sbs.txn.model.form.ac.T926;
import gateway.sbs.txn.model.msg.M8853;
import gateway.sbs.txn.model.msg.M8854;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/9
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ActAccDtlQryAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActAccDtlQryAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String floflg = "";       //后续标识
    private String totcnt = "";       //总记录数
    private String curcnt = "";       //本包内记录数
    private T923 t923 = new T923();
    private List<T923.Bean> dataList;
    private M8853 m8853 = new M8853();

    public String ondtlQry() {
        try {
            m8853.setACTNUM("8010"+m8853.getACTNUM());
            SOFForm form = dataExchangeService.callSbsTxn("8853", m8853).get(0);
            String formcode = form.getFormHeader().getFormCode();
            dataList = new ArrayList<>();
            if ("T923".equals(formcode)) {
                t923 = (T923) form.getFormBody();
                dataList.addAll(t923.getBeanList());
                floflg = t923.getFormBodyHeader().getFLOFLG();
                totcnt = t923.getFormBodyHeader().getTOTCNT();
                curcnt = t923.getFormBodyHeader().getCURCNT();
                if ("1".equals(floflg)) {
                    int m = Integer.parseInt(totcnt) / Integer.parseInt(curcnt);
                    int n = Integer.parseInt(totcnt) % Integer.parseInt(curcnt);
                    if (m > 0 && n > 0) {
                        String tmp = "";
                        for (int i = 1; i <= m; i++) {
                            try {
                                tmp = i * Integer.parseInt(curcnt) + 1 + "";
                                m8853.setBEGNUM(tmp);
                                m8853.setACTNUM("8010"+m8853.getACTNUM());
                                SOFForm form2 = dataExchangeService.callSbsTxn("8853", m8853).get(0);
                                String formcode2 = form.getFormHeader().getFormCode();
                                if ("T923".equals(formcode)) {
                                    t923 = (T923) form2.getFormBody();
                                    dataList.addAll(t923.getBeanList());
                                } else {
                                    logger.error(formcode2);
                                    MessageUtil.addErrorWithClientID("msgs", formcode2);
                                }
                            } catch (NumberFormatException e) {
                                logger.error("查询失败", e);
                                MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
                            }

                        }
                    }
                }
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }

        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //= = = = = = = = = = = = =  = = =  = = = = = =  = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getFloflg() {
        return floflg;
    }

    public void setFloflg(String floflg) {
        this.floflg = floflg;
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

    public T923 getT923() {
        return t923;
    }

    public void setT923(T923 t923) {
        this.t923 = t923;
    }

    public List<T923.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T923.Bean> dataList) {
        this.dataList = dataList;
    }

    public M8853 getM8853() {
        return m8853;
    }

    public void setM8853(M8853 m8853) {
        this.m8853 = m8853;
    }
}
