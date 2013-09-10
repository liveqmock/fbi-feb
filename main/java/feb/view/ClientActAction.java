package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T101;
import gateway.sbs.txn.model.form.T108;
import gateway.sbs.txn.model.form.T109;
import gateway.sbs.txn.model.msg.*;
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
 * �ͻ��˻�
 */
@ManagedBean
@ViewScoped
public class ClientActAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private M8101 clientAct = new M8101();     // ����
    private M8109 closeAct = new M8109();      // �ػ���ѯ
    private T101 rtnActInfo;                   // ���ػ�������Ϣ
    private T109 closeActInfo;                 // �ػ���ѯ������Ϣ
    private T109 updateActRtnInfo;             // �޸��˻�������Ϣ
    private T108 actInfo;                      // ��ѯ�����˻���Ϣ
    private M8104 internalAct = new M8104();   // �ڲ��˻�
    private M8108 updateQryAct = new M8108();  // �˻���ѯ
    private boolean updateable = false;        // �Ƿ���޸�
    private M8102 updateAct = new M8102();     // Ҫ�޸ĵ��˻�
    private boolean closeable = false;         // �Ƿ�ɹػ�
    private String actnum;

    public String onCreate() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8101", clientAct).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                // TODO ��ӡ
                MessageUtil.addInfo("�ͻ��˻������ɹ����˺ţ�" + rtnActInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8101�ͻ�����ʧ��", e);
            MessageUtil.addError("8101�ͻ�����ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onCreateInternalAct() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8104", internalAct).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                // TODO ��ӡ
                MessageUtil.addInfo("�ڲ��˻������ɹ����˺ţ�" + rtnActInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8104�ڲ��˻�����ʧ��", e);
            MessageUtil.addError("8104�ڲ��˻�����ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onQryCloseAct() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8109", closeAct);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T109".equalsIgnoreCase(formcode)) {
                    closeActInfo = (T109) form.getFormBody();
                    closeable = true;
                    actnum = closeActInfo.getCUSIDT() + closeActInfo.getAPCODE() + closeActInfo.getCURCDE();
                } else if ("W313".equalsIgnoreCase(formcode)) {
                    MessageUtil.addWarnWithClientID("msgs", form.getFormHeader().getFormCode());
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("8109����ʧ��", e);
            MessageUtil.addError("8109����ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClose() {
        try {
            M8103 m8103 = new M8103(closeAct.getACTNUM());
            SOFForm form = dataExchangeService.callSbsTxn("8103", m8103).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                closeable = false;
                // TODO ��ӡ
                MessageUtil.addInfo("�ͻ��˻��رճɹ����˺ţ�" + rtnActInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8103�ر��˻�ʧ��", e);
            MessageUtil.addError("8103�ر��˻�ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onCloseInternalAct() {
        try {
            M8106 m8106 = new M8106();
            m8106.setACTNUM(closeAct.getACTNUM());
            SOFForm form = dataExchangeService.callSbsTxn("8106", m8106).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                rtnActInfo = (T101) form.getFormBody();
                closeable = false;
                // TODO ��ӡ
                MessageUtil.addInfo("�˻��رճɹ����˺ţ�" + rtnActInfo.getACTNUM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8106�ر��ڲ��˻�ʧ��", e);
            MessageUtil.addError("8106�ر��ڲ��˻�ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onQryUpdateAct() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8108", updateQryAct);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T108".equalsIgnoreCase(formcode)) {
                    actInfo = (T108) form.getFormBody();
                   // BeanHelper.copyFields(actInfo, updateAct);
                   // updateable = true;
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("8108��ѯ�˻�ʧ��", e);
            MessageUtil.addError("8108��ѯ�˻�ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onUpdate() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8102", updateAct);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T109".equalsIgnoreCase(formcode)) {
                    updateActRtnInfo = (T109) form.getFormBody();
                    updateable = false;
                    MessageUtil.addInfo("�ͻ��˻��޸ĳɹ����˺ţ�" +
                            updateActRtnInfo.getCUSIDT() + updateActRtnInfo.getAPCODE() + updateActRtnInfo.getCURCDE());
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("8102�˻��޸Ľ���ʧ��", e);
            MessageUtil.addError("8102�˻��޸Ľ���ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // ------------------------------

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public M8101 getClientAct() {
        return clientAct;
    }

    public void setClientAct(M8101 clientAct) {
        this.clientAct = clientAct;
    }

    public M8109 getCloseAct() {
        return closeAct;
    }

    public void setCloseAct(M8109 closeAct) {
        this.closeAct = closeAct;
    }

    public T101 getRtnActInfo() {
        return rtnActInfo;
    }

    public void setRtnActInfo(T101 rtnActInfo) {
        this.rtnActInfo = rtnActInfo;
    }

    public T109 getCloseActInfo() {
        return closeActInfo;
    }

    public void setCloseActInfo(T109 closeActInfo) {
        this.closeActInfo = closeActInfo;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    public String getActnum() {
        return actnum;
    }

    public void setActnum(String actnum) {
        this.actnum = actnum;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }

    public M8108 getUpdateQryAct() {
        return updateQryAct;
    }

    public void setUpdateQryAct(M8108 updateQryAct) {
        this.updateQryAct = updateQryAct;
    }

    public M8102 getUpdateAct() {
        return updateAct;
    }

    public void setUpdateAct(M8102 updateAct) {
        this.updateAct = updateAct;
    }

    public T109 getUpdateActRtnInfo() {
        return updateActRtnInfo;
    }

    public void setUpdateActRtnInfo(T109 updateActRtnInfo) {
        this.updateActRtnInfo = updateActRtnInfo;
    }

    public T108 getActInfo() {
        return actInfo;
    }

    public void setActInfo(T108 actInfo) {
        this.actInfo = actInfo;
    }

    public M8104 getInternalAct() {
        return internalAct;
    }

    public void setInternalAct(M8104 internalAct) {
        this.internalAct = internalAct;
    }
}
