package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.*;
import gateway.sbs.txn.model.msg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
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

    private String cusidt;
    private String cusnam;
    private M8002 m8002 = new M8002();          //查询客户请求报文
    private M8004 m8004 = new M8004();          //修改客户
    private M8001 m8001 = new M8001();          //对公账户开户
    private T001 t001 ;                          //创建账户响应报文 与关闭账户查询公用
    private T004 t004 = new T004();             //对公客户单笔查询
    private boolean closeable = false;         // 是否可关户
    private boolean updateable = false;        // 是否可修改
    private List<T003.Bean> dataList = new ArrayList<>();

    public String onCreate() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8001", m8001);
            System.out.println(forms.size());
            /*String formcode = form.getFormHeader().getFormCode();
            if ("T001".equalsIgnoreCase(formcode)) {
                t001 = (T001) form.getFormBody();
                // TODO 打印
                MessageUtil.addInfo("客户信息建立成功，名称：" + m8001.getCUSNAM());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }*/
        } catch (Exception e) {
            logger.error("8001客户信息建立失败", e);
            MessageUtil.addError("8001客户信息建立失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onQryCus() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8002", m8002);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T004".equalsIgnoreCase(formcode)) {
                    t004 = (T004) form.getFormBody();
                    BeanHelper.copyFields(t004, m8004);
                    updateable = true;
                    closeable = true;
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

    //浏览查询
    public String onAllQuery() {
        try {
            m8002 = new M8002(cusidt);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8002", m8002);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if (!"T003".equals(form.getFormHeader().getFormCode()) ) {
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                        return null;
                    } else if ("T003".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T003 t003 = (T003) form.getFormBody();
                        dataList.addAll(t003.getBeanList());
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
//                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
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

    public String onClose() {
        try {
            M8003 m8003 = new M8003(m8002.getCUSIDT());
            SOFForm form = dataExchangeService.callSbsTxn("8003", m8003).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T001".equalsIgnoreCase(formcode)) {
                t001 = (T001) form.getFormBody();
                closeable = false;
                // TODO 打印
                MessageUtil.addInfo("客户账户关闭成功，账号：" + t001.getCUSIDT());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8103关闭账户失败", e);
            MessageUtil.addError("8103关闭账户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;

    }

    public String onUpdate() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8004", m8004);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T004".equalsIgnoreCase(formcode)) {
                    t004 = (T004) form.getFormBody();
                    updateable = false;
                    MessageUtil.addInfo("客户账户修改成功，账号：" +
                            t004.getCUSIDT() + t004.getCUSNAM());
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("8004账户修改交易失败", e);
            MessageUtil.addError("8004账户修改交易失败." + (e.getMessage() == null ? "" : e.getMessage()));
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


    public M8002 getM8002() {
        return m8002;
    }

    public void setM8002(M8002 m8002) {
        this.m8002 = m8002;
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

    public M8001 getM8001() {
        return m8001;
    }

    public void setM8001(M8001 m8001) {
        this.m8001 = m8001;
    }

    public T001 getT001() {
        return t001;
    }

    public void setT001(T001 t001) {
        this.t001 = t001;
    }

    public String getCusidt() {
        return cusidt;
    }

    public void setCusidt(String cusidt) {
        this.cusidt = cusidt;
    }

    public M8004 getM8004() {
        return m8004;
    }

    public void setM8004(M8004 m8004) {
        this.m8004 = m8004;
    }

    public T004 getT004() {
        return t004;
    }

    public void setT004(T004 t004) {
        this.t004 = t004;
    }

    public List<T003.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T003.Bean> dataList) {
        this.dataList = dataList;
    }

    public String getCusnam() {
        return cusnam;
    }

    public void setCusnam(String cusnam) {
        this.cusnam = cusnam;
    }

}
