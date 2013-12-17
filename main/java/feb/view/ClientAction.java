package feb.view;

import feb.print.model.Vchset;
import feb.service.DataExchangeService;
import feb.service.VchPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.*;
import gateway.sbs.txn.model.msg.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.BeanHelper;
import pub.tools.MessageUtil;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-12
 * Time: ����10:16
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ClientAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{vchPrintService}")
    private VchPrintService vchPrintService;

    private String inpflg = "";
    private String cusidt = "";
    private String cusnam = "";
    private String pastyp = "";
    private String passno = "";
    private String cusidx = "";
    private String legbdy = "";
    private String relcus = "";
    private String action = "";
    private M8002 m8002 = new M8002();          //��ѯ�ͻ�������
    private M8004 m8004 = new M8004();          //�޸Ŀͻ�
    private M8001 m8001 = new M8001();          //�Թ��˻�����
    private T001 t001;                          //�����˻���Ӧ���� ��ر��˻���ѯ����
    private T224 t224;
    private T003 t003 = new T003();
    private T004 t004 = new T004();             //�Թ��ͻ����ʲ�ѯ
    private boolean closeable = false;         // �Ƿ�ɹػ�
    private boolean updateable = false;        // �Ƿ���޸�
    private List<T003.Bean> dataList = new ArrayList<>();
    private List<T003.Bean> tmpList = new ArrayList<>();
    private String tellerid;                    //��Ա��
    private String srcpage;
    private boolean isPrintable = false;               // �Ƿ�ɴ�ӡƾ֤

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        cusidt = StringUtils.isEmpty(params.get("cusidt")) ? "" : params.get("cusidt");
        cusnam = StringUtils.isEmpty(params.get("cusnam")) ? "" : params.get("cusnam");
        srcpage = params.get("srcpage");
        action = StringUtils.isEmpty(params.get("action")) ? "" : params.get("action");
        tellerid = SkylineService.getOperId();
        if ("detail".equals(action)) onQryCus();
        if ("query".equals(action)) onAllQuery();
    }

    public String onCreate() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8001", m8001);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if (forms.size() == 2) {
                    if ("T001".equalsIgnoreCase(formcode)) {
                        t001 = (T001) form.getFormBody();
                        MessageUtil.addInfo("�ͻ���Ϣ�����ɹ������ƣ�" + m8001.getCUSNAM());
                    }
                } else if ("T001".equalsIgnoreCase(formcode)) {
                    t001 = (T001) form.getFormBody();
                    m8001.setFUNCDE("Y");
                    MessageUtil.addWarn("�ÿͻ��Ѵ���,������������뵥��ȷ�ϰ�ť��");
                } else {
                    MessageUtil.addErrorWithClientID("msgs", formcode);
                }
            }
        } catch (Exception e) {
            logger.error("8001�ͻ���Ϣ����ʧ��", e);
            MessageUtil.addError("8001�ͻ���Ϣ����ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // ��ӡȷ����
    public void onPrintOpenAct() {
        try {
            vchPrintService.printVchpenAct(
                    "     �ͻ�����ȷ����", t001.getORGIDT(), t001.getDEPNUM(), t001.getCUSIDT(),
                    t001.getCUSNAM(), t001.getOPNDAT(), "", tellerid);
        } catch (Exception e) {
            logger.error("��ӡʧ��", e);
            MessageUtil.addError("��ӡʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    public void onPrintCloseAct() {
        try {
            vchPrintService.printVchClsAct(
                    "     �ͻ��ر�ȷ����", t001.getORGIDT(), t001.getDEPNUM(), t001.getCUSIDT(),
                    t001.getCUSNAM(), t001.getOPNDAT(),t001.getCLSDAT(), tellerid);
        } catch (Exception e) {
            logger.error("��ӡʧ��", e);
            MessageUtil.addError("��ӡʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }

    public String onQryCus() {
        try {
            m8002 = new M8002(cusidt);
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
            logger.error("��ѯ�ͻ�ʧ��", e);
            MessageUtil.addError("��ѯ�ͻ�ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //�����ѯ
    public String onAllQuery() {
        try {
            m8002 = new M8002(cusidt, cusnam, pastyp, passno, cusidx, legbdy, relcus);
            if (!cusnam.equals("") && passno.equals("") && pastyp.equals("") &&
                    cusidx.equals("") && legbdy.equals("") && relcus.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("6");
            } else if (!pastyp.equals("") && !passno.equals("") && cusnam.equals("") &&
                    cusidx.equals("") && legbdy.equals("") && relcus.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("2");
            } else if (!cusidx.equals("") && cusnam.equals("") && passno.equals("") &&
                    pastyp.equals("") && legbdy.equals("") && relcus.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("3");
            } else if (!legbdy.equals("") && cusnam.equals("") && passno.equals("") &&
                    pastyp.equals("") && cusidx.equals("") && relcus.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("4");
            } else if (!relcus.equals("") && cusnam.equals("") && passno.equals("") &&
                    pastyp.equals("") && cusidx.equals("") && legbdy.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("5");
            } else if (!cusidt.equals("") && relcus.equals("") && cusnam.equals("") && passno.equals("") &&
                    pastyp.equals("") && cusidx.equals("") && legbdy.equals("")) {
                m8002.setINPFLG("1");
            } else {
                MessageUtil.addWarn("û��������ϣ����������");
                return null;
            }
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8002", m8002);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                List list = new ArrayList();
                for (SOFForm form : formList) {
                    if ("T003".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T003 t003 = (T003) form.getFormBody();
                        dataList.addAll(t003.getBeanList());
                    } else if ("T004".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T004 t004 = (T004) form.getFormBody();
                        list.add(t004);
                        dataList.addAll(list);
                    } else if ("W012".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        //logger.info("��ѯ���");
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("û�в�ѯ�����ݡ�");
            }
        } catch (Exception e) {
            logger.error("��ѯʧ��", e);
            MessageUtil.addError("��ѯʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClick() {
        return "clientBean";
    }

    public String onBack() {
        return srcpage + "?faces-redirect=true&action=query&cusidt=" + cusidt;
        //return "clientAllQry?faces-redirect=true&action=query&pastyp=" + t004.getPASTYP()+"&passno=" +t004.getPASSNO();
    }

    public String onClose() {
        try {
            M8003 m8003 = new M8003(m8002.getCUSIDT());
            SOFForm form = dataExchangeService.callSbsTxn("8003", m8003).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T001".equalsIgnoreCase(formcode)) {
                t001 = (T001) form.getFormBody();
                closeable = false;
                isPrintable = true;
                // TODO ��ӡ
                MessageUtil.addInfo("�ͻ��˻��رճɹ����ͻ��ţ�" + t001.getCUSIDT());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("�رտͻ�ʧ��", e);
            MessageUtil.addError("�رտͻ�ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
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
                    MessageUtil.addInfo("�ͻ���Ϣ�޸ĳɹ����ͻ��ţ�" + t004.getCUSIDT());
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("�ͻ���Ϣ�޸�ʧ��", e);
            MessageUtil.addError("�ͻ���Ϣ�޸�ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
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

    public VchPrintService getVchPrintService() {
        return vchPrintService;
    }

    public void setVchPrintService(VchPrintService vchPrintService) {
        this.vchPrintService = vchPrintService;
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

    /*public boolean isPrintable() {
        return isPrintable;
    }

    public void setPrintable(boolean printable) {
        isPrintable = printable;
    }*/

    public T003 getT003() {
        return t003;
    }

    public void setT003(T003 t003) {
        this.t003 = t003;
    }

    public String getPastyp() {
        return pastyp;
    }

    public void setPastyp(String pastyp) {
        this.pastyp = pastyp;
    }

    public String getPassno() {
        return passno;
    }

    public void setPassno(String passno) {
        this.passno = passno;
    }

    public String getCusidx() {
        return cusidx;
    }

    public void setCusidx(String cusidx) {
        this.cusidx = cusidx;
    }

    public String getLegbdy() {
        return legbdy;
    }

    public void setLegbdy(String legbdy) {
        this.legbdy = legbdy;
    }

    public String getRelcus() {
        return relcus;
    }

    public void setRelcus(String relcus) {
        this.relcus = relcus;
    }

    public String getInpflg() {
        return inpflg;
    }

    public void setInpflg(String inpflg) {
        this.inpflg = inpflg;
    }

    public T224 getT224() {
        return t224;
    }

    public void setT224(T224 t224) {
        this.t224 = t224;
    }

    public boolean isPrintable() {
        return isPrintable;
    }

    public void setPrintable(boolean printable) {
        isPrintable = printable;
    }

    public String getSrcpage() {
        return srcpage;
    }

    public void setSrcpage(String srcpage) {
        this.srcpage = srcpage;
    }
}
