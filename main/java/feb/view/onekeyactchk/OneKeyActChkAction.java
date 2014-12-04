package feb.view.onekeyactchk;

import feb.view.onekeyactchk.httpclient.T1001Request;
import feb.view.onekeyactchk.httpclient.T1001Response;
import feb.view.onekeyactchk.httpclient.T1002Request;
import feb.view.onekeyactchk.httpclient.T1002Response;
import feb.view.onekeyactchk.wsclient.spc1.SBSSysServiceServiceLocator;
import feb.view.onekeyactchk.wsclient.spc1.SBSSysServiceSoapBindingStub;
import feb.view.onekeyactchk.wsclient.spc1.ScfDzInfoVO;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;
import pub.platform.dao.PTENUDETAIL;
import pub.platform.form.config.SystemAttributeNames;
import pub.platform.security.OperatorManager;
import skyline.utils.SmsHelper;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * SBS һ�����˴���
 * User: zhanrui
 * Date: 2014-11-27
 * Time: ����3:40
 */
@ManagedBean
//@ViewScoped
@SessionScoped
public class OneKeyActChkAction implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(OneKeyActChkAction.class);
    private static final long serialVersionUID = 1366227629931959859L;

    private String txnDate;
    private boolean pollStop = false;

    private List<PeripheralAppInfo> apps = new ArrayList<PeripheralAppInfo>();

    private PeripheralAppInfo[] selectedRecords;
    private TxnStatus txnStatus = TxnStatus.INIT;
    private String enuId = "ONEKEY_ACCT_CHK";

    private static final Executor executor = Executors.newCachedThreadPool();


    @PostConstruct
    public void postConstruct() {
        this.txnDate = getOperatorManager().getSysBusinessDate();
        List<PTENUDETAIL> ptenudetails = new PTENUDETAIL().findByWhere(" where enutype ='" + enuId + "' order by dispno");

        for (PTENUDETAIL enu : ptenudetails) {
            PeripheralAppInfo appInfo = new PeripheralAppInfo();
            appInfo.setAppId("" + enu.getDispno());
            appInfo.setAppName(enu.getEnuitemlabel());
            appInfo.setAppChnCode(enu.getEnuitemvalue());
            appInfo.setUrl(enu.getEnuitemexpand());
            appInfo.setStatus(TxnStatus.INIT.getCode());
            appInfo.setInformTime("");
            appInfo.setResultQryTime("");
            appInfo.setSmsDesc(enu.getEnuitemdesc());
            apps.add(appInfo);
        }
    }

    public void onPoll() {
        boolean hasUnderway = false;
        for (final PeripheralAppInfo app : selectedRecords) {
            TxnStatus status = TxnStatus.valueOfAlias(app.getStatus());
            if (TxnStatus.ACCT_UNDERWAY == status
                    || TxnStatus.INFORM_SUCC == status
                    || TxnStatus.INFORM_STARTED == status
                    || TxnStatus.ACCT_RESULT_QRY_STARTED == status) {
                hasUnderway = true;
            }
        }
        if (!hasUnderway) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(15 * 1000);
                    } catch (InterruptedException e) {
                        //
                    }
                    pollStop = true;
                }
            };
            new Thread(task).start();
        }
    }

    public String onStartAcctChk() {
        this.txnDate = getOperatorManager().getSysBusinessDate();
        if (selectedRecords.length == 0) {
            MessageUtil.addError("��ѡ������˵�ϵͳ...");
            return null;
        }

        this.pollStop = false;
        try {
            for (final PeripheralAppInfo app : selectedRecords) {
                if (StringUtils.isEmpty(app.getUrl())) {
                    app.setRtnMsg("��ϵͳ�Ķ��˷���δ����");
                    //����֪ͨ
                    if (!StringUtils.isEmpty(app.getSmsDesc())) {
                        SmsHelper.asyncSendSms(app.getSmsDesc(), app.getAppName() + "��ʼ����:" + txnDate);
                    }
                    continue;
                }

                //����Ƿ����ڶ����� �� ��ƽ��
                TxnStatus status = TxnStatus.valueOfAlias(app.getStatus());
                switch (status) {
                    case ACCT_UNDERWAY:
                    case ACCT_SUCC_BANLANCE:
                        continue;
                }

                //��ʼ��״̬ ׼��������˴�������
                app.setStatus(TxnStatus.INFORM_STARTED.getCode());
                app.setInformTime("");
                app.setResultQryTime("");

                //������˴�������
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            app.setRtnCode("");
                            app.setRtnMsg("");
                            processInformTxn(app);
                            TxnStatus status = TxnStatus.valueOfAlias(app.getStatus());
                            switch (status) {
                                case INFORM_FAIL:
                                    return;
                            }

                            boolean loop;
                            do {
                                app.setRtnCode("");
                                app.setRtnMsg("");
                                app.setStatus(TxnStatus.ACCT_RESULT_QRY_STARTED.getCode());
                                processResultQryTxn(app);
                                status = TxnStatus.valueOfAlias(app.getStatus());
                                switch (status) {
                                    case ACCT_UNDERWAY:
                                        loop = true;
                                        Thread.sleep(5 * 1000);
                                        break;
                                    case ACCT_SUCC_BANLANCE:
                                        if (!StringUtils.isEmpty(app.getSmsDesc()))
                                            SmsHelper.asyncSendSms(app.getSmsDesc(), app.getAppName() + "���˽��:ƽ��" + txnDate);
                                        loop = false;
                                        break;
                                    case ACCT_SUCC_NOTBANLANCE:
                                        if (!StringUtils.isEmpty(app.getSmsDesc()))
                                            SmsHelper.asyncSendSms(app.getSmsDesc(), app.getAppName() + "���˽��:��ƽ" + txnDate);
                                        loop = false;
                                        break;
                                    case ACCT_FAIL_EXCEPTION:
                                        if (!StringUtils.isEmpty(app.getSmsDesc()))
                                            SmsHelper.asyncSendSms(app.getSmsDesc(), app.getAppName() + "�����쳣" + txnDate);
                                        loop = false;
                                        break;
                                    default:
                                        loop = false;
                                }
                            } while (loop);
                        } catch (Exception e) {
                            app.setRtnCode("ERR3");
                            app.setRtnMsg("һ�����˴���" + e.getMessage());
                            logger.error("һ�����˳���.", e);
                        }
                    }
                };
                executor.execute(task);
            }
        } catch (Exception ex) {
            logger.error("���˵ķ����״������", ex);
            MessageUtil.addError("���˵ķ����״������" + ex.getMessage());
        }
        return null;
    }

    public String onQryResult() {
        this.txnDate = getOperatorManager().getSysBusinessDate();
        if (selectedRecords.length == 0) {
            MessageUtil.addError("��ѡ������˵�ϵͳ...");
            return null;
        }

        this.pollStop = false;
        try {
            for (final PeripheralAppInfo app : selectedRecords) {
                if (StringUtils.isEmpty(app.getUrl())) {
                    app.setRtnMsg("��ϵͳ�Ķ��˷���δ����");
                    continue;
                }

                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boolean loop;
                            do {
                                app.setStatus(TxnStatus.ACCT_RESULT_QRY_STARTED.getCode());
                                processResultQryTxn(app);
                                TxnStatus status = TxnStatus.valueOfAlias(app.getStatus());
                                switch (status) {
                                    case ACCT_UNDERWAY:
                                        loop = true;
                                        Thread.sleep(5 * 1000);
                                        break;
                                    case ACCT_SUCC_BANLANCE:
                                        if (!StringUtils.isEmpty(app.getSmsDesc()))
                                            SmsHelper.asyncSendSms(app.getSmsDesc(), app.getAppName() + "���˽��:ƽ��" + txnDate);
                                        loop = false;
                                        break;
                                    case ACCT_SUCC_NOTBANLANCE:
                                        if (!StringUtils.isEmpty(app.getSmsDesc()))
                                            SmsHelper.asyncSendSms(app.getSmsDesc(), app.getAppName() + "���˽��:��ƽ" + txnDate);
                                        loop = false;
                                        break;
                                    case ACCT_FAIL_EXCEPTION:
                                        if (!StringUtils.isEmpty(app.getSmsDesc()))
                                            SmsHelper.asyncSendSms(app.getSmsDesc(), app.getAppName() + "�����쳣" + txnDate);
                                        loop = false;
                                        break;
                                    default:
                                        loop = false;
                                }
                            } while (loop);
                        } catch (Exception e) {
                            app.setRtnCode("ERR4");
                            app.setRtnMsg("�����ѯ����" + e.getMessage());
                            logger.error("һ�����˳���.", e);
                        }
                    }
                };
                executor.execute(task);
            }
        } catch (Exception ex) {
            logger.error("�����ѯ�������", ex);
            MessageUtil.addError("�����ѯ�������" + ex.getMessage());
        }
        return null;
    }

    public String onResetStatus() {
        if (selectedRecords.length == 0) {
            MessageUtil.addError("��ѡ���账���ϵͳ...");
            return null;
        }
        try {
            for (PeripheralAppInfo app : selectedRecords) {
                app.setStatus(TxnStatus.INIT.getCode());
                app.setRtnCode("");
                app.setRtnMsg("");
                app.setInformTime("");
                app.setResultQryTime("");
                List<PTENUDETAIL> ptenudetails = new PTENUDETAIL().findByWhere(" where enutype ='" + enuId + "' order by dispno");

                for (PTENUDETAIL enu : ptenudetails) {
                    if (enu.getEnuitemvalue().equals(app.getAppChnCode())) {
                        app.setUrl(enu.getEnuitemexpand());
                        app.setAppName(enu.getEnuitemlabel());
                        app.setSmsDesc(enu.getEnuitemdesc());
                    }
                }
                logger.info("һ����������״̬:" + app.getAppName() + "  URL=" + app.getUrl());
            }
        } catch (Exception ex) {
            logger.error("״̬���ô������", ex);
            MessageUtil.addError("״̬���ô������" + ex.getMessage());
        }
        this.pollStop = false;
        return null;
    }


    private void processInformTxn(PeripheralAppInfo app) {
        //����֪ͨ
        if (!StringUtils.isEmpty(app.getSmsDesc())) {
            SmsHelper.asyncSendSms(app.getSmsDesc(), app.getAppName() + "��ʼ����:" + txnDate);
        }
        try {
            if ("SPC1".equals(app.getAppChnCode())) {
                processInformTxn_webservice(app);
            } else {
                processInformTxn_Http(app);
            }
        } catch (Exception e) {
            app.setStatus(TxnStatus.INFORM_STARTED.getCode());
            app.setRtnCode("ERR1");
            app.setRtnMsg("������˴���:" + e.getMessage());
            logger.error("������˴���", e);
        }
    }

    private void processInformTxn_Http(PeripheralAppInfo app) {
        T1001Request request = new T1001Request();
        request.getINFO().setTXNCODE("1001");
        request.getINFO().setVERSION("01");
        request.getINFO().setREQSN(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));

        request.getBODY().setTXNDATE(txnDate);
        request.getBODY().setTXNTIME(new SimpleDateFormat("HHmmss").format(new Date()));

        request.getBODY().setCHNCODE(app.getAppChnCode());
        request.getBODY().setACTION("1");
        request.getBODY().setREMARK("start txn : 1001");

        String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" + request.toXml(request);
        logger.info(app.getAppName() + "һ������T1001������" + reqXml);

        if (StringUtils.isEmpty(app.getUrl())) {
            throw new RuntimeException("ϵͳ����δ�������URL");
        }
        String respXml = doPost(app.getUrl(), reqXml, "GBK");
        logger.info(app.getAppName() + "һ������T1001��Ӧ����" + respXml);

        T1001Response response = new T1001Response();
        response = (T1001Response) response.toBean(respXml);

        String rtncode = response.getINFO().getRTNCODE();
        if ("0000".equals(rtncode)) {
            app.setStatus(TxnStatus.INFORM_SUCC.getCode());
        } else {
            app.setStatus(TxnStatus.INFORM_FAIL.getCode());
        }
        app.setRtnCode(rtncode);
        app.setRtnMsg(response.getINFO().getRTNMSG());
        app.setInformTime(new DateTime().toString("HH:mm:ss"));
    }

    private void processInformTxn_webservice(PeripheralAppInfo app) {
        if (StringUtils.isEmpty(app.getUrl())) {
            throw new RuntimeException("ϵͳ����δ�������URL");
        }

        URL wsdlUrl = null;
        SBSSysServiceSoapBindingStub service = null;
        ScfDzInfoVO vo = new ScfDzInfoVO();
        vo.setTxnCode("1001");
        vo.setVersion("01");
        vo.setReqSn(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        vo.setTxnDate(txnDate);
        vo.setTxnTime(new SimpleDateFormat("HHmmss").format(new Date()));
        vo.setAction("1");
        vo.setChnCode("SPC1");

        logger.info(app.getAppName() + "һ������T1001������" + vo);

        ScfDzInfoVO respVo = null;
        try {
            wsdlUrl = new URL(app.getUrl());
            service = (SBSSysServiceSoapBindingStub) new SBSSysServiceServiceLocator().getSBSSysService(wsdlUrl);
            respVo = service.acceptB2BDzInfo(vo);
            logger.info(app.getAppName() + "һ������T1001��Ӧ����" + respVo);
        } catch (Exception e) {
            throw new RuntimeException("WebService�������", e);
        }

        String rtncode = respVo.getRtnCode();
        if ("0000".equals(rtncode)) {
            app.setStatus(TxnStatus.INFORM_SUCC.getCode());
        } else {
            app.setStatus(TxnStatus.INFORM_FAIL.getCode());
        }
        app.setRtnCode(rtncode);
        app.setRtnMsg(respVo.getRtnMsg());
        app.setInformTime(new DateTime().toString("HH:mm:ss"));
    }

    //---
    private void processResultQryTxn(PeripheralAppInfo app) {
        try {
            if ("SPC1".equals(app.getAppChnCode())) {
                processResultQryTxn_webservice(app);
            } else {
                processResultQryTxn_http(app);
            }
        } catch (Exception e) {
            app.setStatus(TxnStatus.ACCT_FAIL_EXCEPTION.getCode());
            app.setRtnCode("ERR2");
            app.setRtnMsg("��ѯ���˽������:" + e.getMessage());
            logger.error("��ѯ���˽������", e);
        }
    }

    private void processResultQryTxn_http(PeripheralAppInfo app) {
        T1002Request request = new T1002Request();
        request.getINFO().setTXNCODE("1002");
        request.getINFO().setVERSION("01");
        request.getINFO().setREQSN(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));

        request.getBODY().setCHANNEL(app.getAppChnCode());

        String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" + request.toXml(request);
        logger.info(app.getAppName() + "һ������T1002������" + reqXml);
        String respXml = doPost(app.getUrl(), reqXml, "GBK");
        logger.info(app.getAppName() + "һ������T1002��Ӧ����" + respXml);

        T1002Response response = new T1002Response();
        response = (T1002Response) response.toBean(respXml);

        String rtncode = response.getINFO().getRTNCODE();
        if ("0000".equals(rtncode)) {
            app.setStatus(TxnStatus.ACCT_SUCC_BANLANCE.getCode());
        } else if ("1000".equals(rtncode)) {
            app.setStatus(TxnStatus.ACCT_SUCC_NOTBANLANCE.getCode());
        } else if ("0001".equals(rtncode)) {
            app.setStatus(TxnStatus.ACCT_UNDERWAY.getCode());
        } else {
            app.setStatus(TxnStatus.ACCT_FAIL_EXCEPTION.getCode());
        }
        app.setRtnCode(rtncode);
        app.setRtnMsg(response.getINFO().getRTNMSG());
        app.setResultQryTime(new DateTime().toString("HH:mm:ss"));
    }

    private void processResultQryTxn_webservice(PeripheralAppInfo app) {
        if (StringUtils.isEmpty(app.getUrl())) {
            throw new RuntimeException("ϵͳ����δ�������URL");
        }

        URL wsdlUrl = null;
        SBSSysServiceSoapBindingStub service = null;
        ScfDzInfoVO vo = new ScfDzInfoVO();
        vo.setTxnCode("1002");
        vo.setVersion("01");
        vo.setReqSn(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        vo.setTxnDate(txnDate);
        vo.setTxnTime(new SimpleDateFormat("HHmmss").format(new Date()));
        vo.setAction("1");
        vo.setChannel("SPC1");
        logger.info(app.getAppName() + "һ������T1002������" + vo);

        ScfDzInfoVO respVo = null;
        try {
            wsdlUrl = new URL(app.getUrl());
            service = (SBSSysServiceSoapBindingStub) new SBSSysServiceServiceLocator().getSBSSysService(wsdlUrl);
            respVo = service.getScfDzResult(vo);
            logger.info(app.getAppName() + "һ������T1002��Ӧ����" + respVo);
        } catch (Exception e) {
            throw new RuntimeException("WebService�������", e);
        }

        String rtncode = respVo.getRtnCode();
        if ("0000".equals(rtncode)) {
            app.setStatus(TxnStatus.ACCT_SUCC_BANLANCE.getCode());
        } else if ("1000".equals(rtncode)) {
            app.setStatus(TxnStatus.ACCT_SUCC_NOTBANLANCE.getCode());
        } else if ("0001".equals(rtncode)) {
            app.setStatus(TxnStatus.ACCT_UNDERWAY.getCode());
        } else {
            app.setStatus(TxnStatus.ACCT_FAIL_EXCEPTION.getCode());
        }

        app.setRtnCode(rtncode);
        app.setRtnMsg(respVo.getRtnMsg());
        app.setResultQryTime(new DateTime().toString("HH:mm:ss"));
    }

    //=====
    private String doPost(String serverUrl, String datagram, String charsetName) {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            //����ʱ
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000 * 30);
            //��ȡ��ʱ
            httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 1000 * 180);

            HttpPost httppost = new HttpPost(serverUrl);
            httppost.getURI();
            StringEntity xmlSE = new StringEntity(datagram, charsetName);
            httppost.setEntity(xmlSE);

            HttpResponse httpResponse = httpclient.execute(httppost);

            //HttpStatus.SC_OK)��ʾ���ӳɹ�
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(httpResponse.getEntity(), charsetName);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException("Http ͨѶ����", e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    private OperatorManager getOperatorManager() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        OperatorManager om = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
        if (om == null) {
            throw new RuntimeException("�û�δ��¼��");
        }
        return om;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }


    public List<PeripheralAppInfo> getApps() {
        return apps;
    }

    public void setApps(List<PeripheralAppInfo> apps) {
        this.apps = apps;
    }


    public PeripheralAppInfo[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(PeripheralAppInfo[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public TxnStatus getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(TxnStatus txnStatus) {
        this.txnStatus = txnStatus;
    }

    public boolean isPollStop() {
        return pollStop;
    }

    public void setPollStop(boolean pollStop) {
        this.pollStop = pollStop;
    }
}
