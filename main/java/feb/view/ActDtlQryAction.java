package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T926;
import gateway.sbs.txn.model.msg.M8854;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
public class ActDtlQryAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActDtlQryAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String floflg = "";       //后续标识
    private String totcnt = "";       //总记录数
    private String curcnt = "";       //本包内记录数
    private String dbtcnt = "";       //借方总笔数
    private String dbtamt = "";       //借方总金额
    private String crtcnt = "";       //贷方总笔数
    private String crtamt = "";       //贷方总金额
    private T926 t926 = new T926();
    private List<T926.Bean> dataList;
    private M8854 m8854 = new M8854();

    public String ondtlQry() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8854", m8854).get(0);
            String formcode = form.getFormHeader().getFormCode();
            dataList = new ArrayList<>();
            if ("T926".equals(formcode)) {
                t926 = (T926) form.getFormBody();
                dataList.addAll(t926.getBeanList());
                floflg = t926.getFormBodyHeader().getFLOFLG();
                totcnt = t926.getFormBodyHeader().getTOTCNT();
                curcnt = t926.getFormBodyHeader().getCURCNT();
                dbtcnt = t926.getFormBodyHeader().getDBTCNT();
                dbtamt = new DecimalFormat("###,###,##0.00").format(t926.getFormBodyHeader().getDBTAMT());
                crtcnt = t926.getFormBodyHeader().getTOTCNT();
                crtamt = new DecimalFormat("###,###,##0.00").format(t926.getFormBodyHeader().getCRTAMT());
                if ("1".equals(floflg)) {
                    int m = Integer.parseInt(totcnt) / Integer.parseInt(curcnt);
                    int n = Integer.parseInt(totcnt) % Integer.parseInt(curcnt);
                    if (m > 0 && n > 0) {
                        String tmp = "";
                        for (int i = 1; i <= m; i++) {
                            try {
                                tmp = i * Integer.parseInt(curcnt) + 1 + "";
                                m8854.setBEGNUM(tmp);
                                SOFForm form2 = dataExchangeService.callSbsTxn("8854", m8854).get(0);
                                String formcode2 = form.getFormHeader().getFormCode();
                                if ("T926".equals(formcode)) {
                                    t926 = (T926) form2.getFormBody();
                                    dataList.addAll(t926.getBeanList());
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

    public String getDbtcnt() {
        return dbtcnt;
    }

    public void setDbtcnt(String dbtcnt) {
        this.dbtcnt = dbtcnt;
    }



    public String getCrtcnt() {
        return crtcnt;
    }

    public void setCrtcnt(String crtcnt) {
        this.crtcnt = crtcnt;
    }

    public String getDbtamt() {
        return dbtamt;
    }

    public void setDbtamt(String dbtamt) {
        this.dbtamt = dbtamt;
    }

    public String getCrtamt() {
        return crtamt;
    }

    public void setCrtamt(String crtamt) {
        this.crtamt = crtamt;
    }

    public T926 getT926() {
        return t926;
    }

    public void setT926(T926 t926) {
        this.t926 = t926;
    }

    public List<T926.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T926.Bean> dataList) {
        this.dataList = dataList;
    }

    public M8854 getM8854() {
        return m8854;
    }

    public void setM8854(M8854 m8854) {
        this.m8854 = m8854;
    }
}
