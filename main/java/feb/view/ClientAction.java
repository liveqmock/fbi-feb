package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.*;
import gateway.sbs.txn.model.msg.M8001;
import gateway.sbs.txn.model.msg.M8002;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-12
 * Time: 下午10:16
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ClientAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private T005 cusInfo;                        //个人客户单笔查询
    private M8002 updateQryCus = new M8002();
    private M8001 m8001 = new M8001();          //对公账户开户
    private T001 t001;
    private boolean closeable = false;         // 是否可关户
    private boolean updateable = false;        // 是否可修改

    public String onCreate() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8001", m8001).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T001".equalsIgnoreCase(formcode)) {
                t001 = (T001) form.getFormBody();
                // TODO 打印
                MessageUtil.addInfo("客户账户建立成功，账号：" + m8001.getCUSIDT());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8001客户开户失败", e);
            MessageUtil.addError("8001客户开户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onQryUpdateCus() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8002", updateQryCus);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T005".equalsIgnoreCase(formcode)) {
                    cusInfo = (T005) form.getFormBody();
                    BeanHelper.copyFields(cusInfo, updateQryCus);
                    updateable = true;
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("查询客户失败", e);
            MessageUtil.addError("查询客户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //----------------------------------------------------------------

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public T005 getCusInfo() {
        return cusInfo;
    }

    public void setCusInfo(T005 cusInfo) {
        this.cusInfo = cusInfo;
    }

    public M8002 getUpdateQryCus() {
        return updateQryCus;
    }

    public void setUpdateQryCus(M8002 updateQryCus) {
        this.updateQryCus = updateQryCus;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }
}
