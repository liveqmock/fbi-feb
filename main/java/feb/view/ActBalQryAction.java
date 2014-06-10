package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T923;
import gateway.sbs.txn.model.form.ac.T931;
import gateway.sbs.txn.model.msg.M8123;
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
 * Date: 2014/6/10
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ActBalQryAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ActapcAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private T931 t931 = new T931();
    private M8123 m8123 = new M8123();
    private List<T931.Bean> dataList;

    public String onBalQry() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8123", m8123).get(0);
            String formcode = form.getFormHeader().getFormCode();
            dataList = new ArrayList<>();
            if ("T931".equals(formcode)) {
                t931 = (T931) form.getFormBody();
                dataList.addAll(t931.getBeanList());
                int m = Integer.parseInt(t931.getTotcnt()) / Integer.parseInt(t931.getCurcnt());
                int n = Integer.parseInt(t931.getTotcnt()) % Integer.parseInt(t931.getCurcnt());
                if (m > 0 && n > 0) {
                    String tmp = "";
                    for (int i = 1; i <= m; i++) {
                        try {
                            tmp = i * Integer.parseInt(t931.getCurcnt()) + 1 + "";
                            m8123.setBEGNUM(tmp);
                            SOFForm form2 = dataExchangeService.callSbsTxn("8123", m8123).get(0);
                            String formcode2 = form.getFormHeader().getFormCode();
                            if ("T931".equals(formcode)) {
                                t931 = (T931) form2.getFormBody();
                                dataList.addAll(t931.getBeanList());
                            } else {
                                logger.error(formcode2);
                                MessageUtil.addErrorWithClientID("msgs", formcode2);
                            }
                        } catch (NumberFormatException e) {
                            logger.error("²éÑ¯Ê§°Ü", e);
                            MessageUtil.addError("²éÑ¯Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
                        }

                    }
                }
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }

        } catch (Exception e) {
            logger.error("²éÑ¯Ê§°Ü", e);
            MessageUtil.addError("²éÑ¯Ê§°Ü." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // = = = = = = = = = = = = = = = =  = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public T931 getT931() {
        return t931;
    }

    public void setT931(T931 t931) {
        this.t931 = t931;
    }

    public M8123 getM8123() {
        return m8123;
    }

    public void setM8123(M8123 m8123) {
        this.m8123 = m8123;
    }

    public List<T931.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T931.Bean> dataList) {
        this.dataList = dataList;
    }
}
