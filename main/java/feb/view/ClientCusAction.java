package feb.view;

import feb.service.DataExchangeService;

import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T001;
import gateway.sbs.txn.model.form.T002;
import gateway.sbs.txn.model.form.T805;
import gateway.sbs.txn.model.msg.M8001;
import gateway.sbs.txn.model.msg.M8002;
import gateway.sbs.txn.model.msg.M9805;
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
 * Date: 13-9-5
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ClientCusAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ClientCusAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private M8001 clientCus = new M8001();     // 创建客户
    private M8002 quryCus = new M8002();      // 查询
    private T001 rtnCusInfo;                  // 开关户返回信息
    private T002 quryCusInfo;                 // 查询返回信息
    private List<T002.Bean> cusList =  new ArrayList();
    private boolean closeable = false;         // 是否可关户
    private String cusnum;

    public String onCreate() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8001", clientCus).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T001".equalsIgnoreCase(formcode)) {
                rtnCusInfo = (T001) form.getFormBody();
                // TODO 打印
                MessageUtil.addInfo("客户建立成功");
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8001客户失败", e);
            MessageUtil.addError("8001客户创建失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
    public String onAllQuery() {
        try {
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8002",quryCus);
            if (formList != null && !formList.isEmpty()) {
                cusList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if (!"T002".equals(form.getFormHeader().getFormCode()) &&
                            !"W012".equals(form.getFormHeader().getFormCode())) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T002".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T002 t002 = (T002) form.getFormBody();
                        cusList.addAll(t002.getBeanList());
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                       MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (cusList == null || cusList.isEmpty()) {
                MessageUtil.addWarn("没有查询到数据。");
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
    public String action(){
        return "entcusBean";
    }
 //===========================================

    public T001 getRtnCusInfo() {
        return rtnCusInfo;
    }

    public void setRtnCusInfo(T001 rtnCusInfo) {
        this.rtnCusInfo = rtnCusInfo;
    }

    public List<T002.Bean> getCusList() {
        return cusList;
    }

    public void setCusList(List<T002.Bean> cusList) {
        this.cusList = cusList;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public M8001 getClientCus() {
        return clientCus;
    }

    public void setClientCus(M8001 clientCus) {
        this.clientCus = clientCus;
    }

    public M8002 getQuryCus() {
        return quryCus;
    }

    public void setQuryCus(M8002 quryCus) {
        this.quryCus = quryCus;
    }

    public T001 getRtnActInfo() {
        return rtnCusInfo;
    }

    public void setRtnActInfo(T001 rtnActInfo) {
        this.rtnCusInfo = rtnActInfo;
    }

    public T002 getQuryCusInfo() {
        return quryCusInfo;
    }

    public void setQuryCusInfo(T002 quryCusInfo) {
        this.quryCusInfo = quryCusInfo;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    public String getCusnum() {
        return cusnum;
    }

    public void setCusnum(String cusnum) {
        this.cusnum = cusnum;
    }
}
