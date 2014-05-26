package feb.view;

import feb.service.DataExchangeService;
import feb.service.RosPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T554;
import gateway.sbs.txn.model.form.re.T555;
import gateway.sbs.txn.model.msg.Ma113;
import gateway.sbs.txn.model.msg.Ma276;
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
 * 定期查询  通知存款查询
 */
@ManagedBean
@ViewScoped
public class ActrsoAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ActrsoAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{rosPrintService}")
    private RosPrintService rosPrintService;

    private T554 t554 = new T554();
    private T555 t555 = new T555();
    private Ma276 ma276 = new Ma276();
    private Ma113 ma113 = new Ma113();
    private List<T554.Bean> dataList = new ArrayList<>();
    private List<T555.Bean> rosList = new ArrayList<>();

    //定期查询
    public String onAllQuery() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a276", ma276);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T554".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T554 t554 = (T554) form.getFormBody();
                        dataList = t554.getBeanList();
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //通知存款查询
    public String onRosQuery() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("a113", ma113);
            if (formList != null && !formList.isEmpty()) {
                for (SOFForm form : formList) {
                    if ("T555".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T555 t555 = (T555) form.getFormBody();
                        rosList = t555.getBeanList();
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //= = = = = = = = = = = = = = = = = = = = = = = = = = = =

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public RosPrintService getRosPrintService() {
        return rosPrintService;
    }

    public void setRosPrintService(RosPrintService rosPrintService) {
        this.rosPrintService = rosPrintService;
    }

    public Ma276 getMa276() {
        return ma276;
    }

    public void setMa276(Ma276 ma276) {
        this.ma276 = ma276;
    }

    public T554 getT554() {
        return t554;
    }

    public void setT554(T554 t554) {
        this.t554 = t554;
    }

    public List<T554.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T554.Bean> dataList) {
        this.dataList = dataList;
    }

    public T555 getT555() {
        return t555;
    }

    public void setT555(T555 t555) {
        this.t555 = t555;
    }

    public Ma113 getMa113() {
        return ma113;
    }

    public void setMa113(Ma113 ma113) {
        this.ma113 = ma113;
    }

    public List<T555.Bean> getRosList() {
        return rosList;
    }

    public void setRosList(List<T555.Bean> rosList) {
        this.rosList = rosList;
    }
}
