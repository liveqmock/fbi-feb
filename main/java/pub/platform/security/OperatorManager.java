package pub.platform.security;

//import cbs.common.IbatisFactory;
//import cbs.repository.platform.dao.ScttlrMapper;
//import cbs.repository.platform.model.Scttlr;
//import cbs.repository.platform.model.ScttlrExample;

import feb.service.DataExchangeService;
import gateway.sbs.core.CtgManager;
import gateway.sbs.core.SBSRequest;
import gateway.sbs.core.SBSResponse;
import gateway.sbs.txn.model.msg.M0001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.advance.utils.PropertyManager;
import pub.platform.db.ConnectionManager;
import pub.platform.db.DatabaseConnection;
import pub.platform.system.manage.dao.PtDeptBean;
import pub.platform.system.manage.dao.PtOperBean;
import pub.platform.utils.BusinessDate;
import pub.platform.utils.ImgSign;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Title: OperatorManager.java
 * </p>
 * <p>
 * Description: This class includes the basic behaviors of operator.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author WangHaiLei
 * @version 1.6 $ UpdateDate: Y-M-D-H-M: 2003-12-02-09-50 2004-03-01-20-35 $
 */
public class OperatorManager implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(OperatorManager.class);
    /**
     * operatorid�Ǵ�login(operatorid, password)�еõ��ġ�
     */
    private String fimgSign = "";

    private String operatorname = null;

    private String operatorid = null;

    //��ǰȨ���µĲ˵���������Ӳ˵���
    private String xmlString = null;

    //��ǰȨ���µ�ȫ���˵�
    private String jsonString = null;

    /*
    20100820 zhanrui
    ��ǰȨ���µİ���targetmachine����Ĳ˵���
    Ŀǰֻ��Ϊ������
    1��default����Ҫ��ҵ��˵���targetmachine Ϊ�ջ� Ϊdefault��
    2��system����Ҫ��ϵͳ������ز˵�
     */
    private Map jsonMap = new HashMap();

    private Resources resources;

    private String[] roles = new String[]{};

    private MenuBean mb;

    private List jsplist = null;

    private PtOperBean operator;
    //private Scttlr scttlr;

    private String remoteAddr = null;

    private String remoteHost = null;

    private String loginTime = BusinessDate.getTodaytime();

    private boolean isLogin = false;

    private String filePath = "";

    private String safySign = "";

    public OperatorManager() {

        // //����ͼƬ��ʾ
        createImgSign();
    }

    /**
     * @return
     */
    public String getXmlString() {
        return (this.xmlString);
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * ����һ��Operator Object�����Ojbect�а����ò���Ա�Ļ�����Ϣ��������operid, email, enabled, sex,
     * status, opername��
     *
     * @return Operator Ojbect.
     */
    public PtOperBean getOperator() {
        return operator;
    }

    public String filePath() {
        return filePath;
    }

    /**
     * �õ���ǰ����Ա��operatorname��
     *
     * @return
     */
    public String getOperatorName() {
        return operator.getOpername();
    }

    /**
     * @return
     */
    public String getOperatorId() {
        return operatorid;
    }

    /**
     * ����Աǩ������֤operid+passwd�Ƿ���ȷ ǩ���ɹ��� 1.isLogin=true 2.ȡ�øò���Ա��ص����н�ɫ 3.��ʼ����Դ�б�
     * 4.ȡ�ò���Ա�Ĳ˵�
     *
     * @param operid
     * @param password
     * @return boolean
     * @roseuid 3F80B6360281
     */
    public boolean login(String operid, String password) {

        if (!sbsLogin(operid, password)) return false;
        ConnectionManager cm = ConnectionManager.getInstance();
        DatabaseConnection dc = cm.get();
//        SqlSession session = IbatisFactory.ORACLE.getInstance().openSession();
        try {
            String loginWhere = "where operid='" + operid
                    + "' and operpasswd ='" + password + "'and operenabled='1'";
            //zhan 20100415 for UAAP
            /*
            if (operid == null) {
                isLogin = false;
                return false;
            }
            String loginWhere = "where operid='" + operid
                    + "' and operenabled='1'";
            */
            this.operatorid = operid;
            operator = new PtOperBean();
            operator = (PtOperBean) operator.findFirstByWhere(loginWhere);
            if (operator == null) {
                isLogin = false;
                return false;
            }

            /*
            //zhanrui 20101117
            ScttlrMapper mapper = session.getMapper(ScttlrMapper.class);
            ScttlrExample example = new ScttlrExample();
            //TODO ������
            example.createCriteria().andOrgidtEqualTo(operator.getDeptid()).andTlrnumEqualTo(operid);

            try {
                scttlr = mapper.selectByExample(example).get(0);
            } catch (Exception e) {
                logger.info("δ��SCTTLR�����ҵ���Ӧ��¼��");
                return false;
            }
            */

            String sss = "��¼ʱ�� :" + loginTime + " IP: " + remoteAddr
                    + " �������� : " + remoteHost;

            operator.setFillstr600(sss);

            PtDeptBean ptpdet = new PtDeptBean();

            operator.setPtDeptBean((PtDeptBean) ptpdet
                    .findFirstByWhere("where deptid='" + operator.getDeptid()
                            + "'"));

            this.operatorname = operator.getOpername();
            isLogin = true;
            // ȡ�øò���Ա�����н�ɫ��dep
            // PtOperRoleBean porb = new PtOperRoleBean();
            // List porbs = porb.findByWhere("where operid='"+operid+"'");
            // roles = new String[porbs.size()];
            // for ( int i = 0 ; i < porbs.size() ; i++ ) {
            // roles[i] = ((PtOperRoleBean)porbs.get(i)).getRoleid();
            // }
            // ��ʼ����Դ�б���
            resources = new Resources(operid);
            // ��ʼ���˵���
            try {
                mb = new MenuBean();

                //this.xmlString = mb.generateStream(operid);
                //this.jsonString = mb.generateJsonStream(operid);
                this.jsonMap.put("default", mb.generateJsonStream(operid, "default"));
                this.jsonMap.put("system", mb.generateJsonStream(operid, "system"));

            } catch (Exception ex3) {
                ex3.printStackTrace();
                System.err.println("Wrong when getting menus of operator: [ "
                        + ex3 + "]");
            }

            // ��ʼ��jsp��Դ
            // PtMenuBean pmb = new PtMenuBean();
            // List pmbs = pmb.findByWhere("where menuid in (select resname from
            // ptresource where restype='2' and resid in (select resid from
            // ptroleres where roleid in (select roleid from ptoperrole where
            // operid='"+operid+"')))");
            // jsplist = new ArrayList();
            // for ( int i = 0 ; i < pmbs.size() ; i++ ) {
            // pmb = (PtMenuBean)pmbs.get(i);
            // jsplist.add(pmb.getMenuaction());
            // }
            return isLogin;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cm.release();
        }

    }

    public boolean sbsLogin(String tellerId, String tellerPwd) {
        M0001 m0001 = new M0001(tellerId, tellerPwd);
        List<String> paramList = new ArrayList<>();
        paramList.add(m0001.getTLRNUM());
        paramList.add(m0001.getTLRPWD());
        paramList.add(m0001.getDEVBLN());
        CtgManager ctgManager = new CtgManager();
        SBSRequest sbsRequest = new SBSRequest(tellerId, tellerId, "0001", paramList);
        SBSResponse sbsResponse = new SBSResponse();
        ctgManager.processSingleResponsePkg(sbsRequest, sbsResponse);
        return sbsResponse.getFormCodes().contains("T901");
    }

    public boolean sbsLogout(String tellerId, String termId) {
        List<String> paramList = new ArrayList<>();
        CtgManager ctgManager = new CtgManager();
        SBSRequest sbsRequest = new SBSRequest(tellerId, termId, "0002", paramList);
        SBSResponse sbsResponse = new SBSResponse();
        ctgManager.processSingleResponsePkg(sbsRequest, sbsResponse);
        return sbsResponse.getFormCodes().contains("W001");
    }

    /**
     * �����¼��� �����pwd
     *
     * @param operid
     * @return
     */
    public boolean login4sso(String operid) {

        ConnectionManager cm = ConnectionManager.getInstance();
        DatabaseConnection dc = cm.get();
        try {
            String loginWhere = "where operid='" + operid
                    + "' and operenabled='1'";
            this.operatorid = operid;
            operator = new PtOperBean();
            operator = (PtOperBean) operator.findFirstByWhere(loginWhere);
            if (operator == null) {
                isLogin = false;
                return false;
            }

            String sss = "��¼ʱ�� :" + loginTime + " IP: " + remoteAddr
                    + " �������� : " + remoteHost;

            operator.setFillstr600(sss);

            PtDeptBean ptpdet = new PtDeptBean();

            operator.setPtDeptBean((PtDeptBean) ptpdet
                    .findFirstByWhere("where deptid='" + operator.getDeptid()
                            + "'"));

            this.operatorname = operator.getOpername();
            isLogin = true;

            // ��ʼ����Դ�б���
            resources = new Resources(operid);
            // ��ʼ���˵���
            try {
                mb = new MenuBean();

                //this.xmlString = mb.generateStream(operid);
                //this.jsonString = mb.generateJsonStream(operid);
                String aDefault = mb.generateJsonStream(operid, "default");
                this.jsonMap.put("default", aDefault);
                String system = mb.generateJsonStream(operid, "system");
                this.jsonMap.put("system", system);

                if ((aDefault + system).length() <= 100) {
                    return false;
                }

            } catch (Exception ex3) {
                ex3.printStackTrace();
                System.err.println("Wrong when getting menus of operator: [ "
                        + ex3 + "]");
            }

            return isLogin;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cm.release();
        }

    }

    /**
     * @return ArrayList
     * @roseuid 3F80B71A01BC
     */
    public List getJspList() {
        return jsplist;
    }

    /**
     * @return boolean
     * @roseuid 3F80B71A00BC
     */
    public boolean isLogin() {
        return isLogin;
    }

    /**
     * ����Ȩ�� 1��ȡ���Ϸ���Դ��ʶ 2��ʹ��Resources��checkPermission����У��
     *
     * @param resource
     * @return boolean
     * @roseuid 3F80B8590151
     */
    public boolean checkPermission(String resource, int type, String url) {
        boolean permit = resources.checkPermission(resource, type, url);
        return permit;
    }

    /**
     * ǩ��
     */
    public boolean logout() {

        if (sbsLogout(operatorid, operatorid)) {
            isLogin = false;
            resources = null;
            operator = null;
            operatorname = null;
            operatorid = null;
            roles = null;
            mb = null;
            xmlString = null;
            jsonString = null;
            remoteHost = null;
            remoteAddr = null;
            loginTime = null;
            return true;
        } else return false;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public void createImgSign2() {

        try {
            String deptfillstr100 = PropertyManager.getProperty("cims");
            deptfillstr100 = new String(deptfillstr100.getBytes(), "GBK");
            String lastFile = System.currentTimeMillis() + "";

            ImgSign imgSign = new ImgSign();
            filePath = "/images/" + lastFile + ".jpg";
            safySign = imgSign.creatImgSign(deptfillstr100 + filePath);

        } catch (Exception e) {
        }

		/*
         * try { String deptfillstr100 = PropertyManager.getProperty("cims");
		 * deptfillstr100 = new String(deptfillstr100.getBytes(), "GBK"); String
		 * lastFile = System.currentTimeMillis() + "";
		 * 
		 * File file = new File(deptfillstr100 + "/images"); if (!file.exists()) {
		 * file.mkdir(); }
		 * 
		 * File f = new File(deptfillstr100 + "/images"); File[] files =
		 * f.listFiles(); for (int i = 0; i < files.length; i++) { if
		 * (files[i].isFile() && files[i].exists()) {
		 * 
		 * if (Integer.parseInt((System.currentTimeMillis() + "") .substring(0,
		 * 5)) - Integer.parseInt(Util.strtoint(files[i].getName() .substring(0,
		 * 5))) > 0) files[i].delete();
		 *  } }
		 * 
		 * ImgSign imgSign = new ImgSign(); filePath = "/images/" + lastFile +
		 * ".jpg"; safySign = imgSign.creatImgSign(deptfillstr100 + filePath);
		 *  } catch (Exception e) {
		 *  }
		 */

    }

    public boolean ImgSign(String sign) {
        boolean retbool = false;

        try {
            ImgSignDel();

            if (sign.equals(safySign))
                retbool = true;

            return retbool;
        } catch (Exception e) {
            return retbool;
        }

    }

    public void ImgSignDel() {
        try {
            String deptfillstr100 = PropertyManager.getProperty("cims");
            deptfillstr100 = new String(deptfillstr100.getBytes(), "GBK");
            File f = new File(deptfillstr100 + filePath);
            if (f.exists())
                f.delete();

        } catch (Exception e) {

        }
    }

    private void createImgSign() {
        fimgSign = "";

        try {

            int rad = (int) Math.round(Math.random() * 10);
            if (rad == 10)
                rad = 9;
            fimgSign += rad;

            rad = (int) Math.round(Math.random() * 10);
            if (rad == 10)
                rad = 9;
            fimgSign += rad;

            rad = (int) Math.round(Math.random() * 10);
            if (rad == 10)
                rad = 9;
            fimgSign += rad;

            rad = (int) Math.round(Math.random() * 10);
            if (rad == 10)
                rad = 9;
            fimgSign += rad;

        } catch (Exception e) {

        }
    }

    public String getImgSign() {

        return fimgSign;
    }

    public String getJsonString() {
        return jsonString;
    }

    public String getJsonString(String target) {
        return (String) this.jsonMap.get(target);
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public String getLoginTime() {
        return loginTime;
    }

//    public Scttlr getScttlr() {
//        return scttlr;
//    }
}