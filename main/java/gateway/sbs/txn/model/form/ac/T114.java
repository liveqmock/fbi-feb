package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-18
 * Time: ����12:38
 * To change this template use File | Settings | File Templates.
 */

/**
 * �嵥��ӡ��Ӧ����
 */
public class T114 extends SOFFormBody {
    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 14, 72, 21, 8, 8, 24, 10, 21, 24, 10, 21, 24, 10, 21, 21, 21};
    }

    private String ORGIDT;    //   ������
    private String ACTNUM;    //           �˺�
    private String ACTNAM;    //   �˻�����
    private String AVABAL;    //           ��ʱ��Ч���
    private String CLSDAT;    //   �ػ�����
    private String LINTDT;    //           ĩ�μ�Ϣ����
    private String CRACCM1;    //   ����1
    private String CRATSF1;    //           ����1
    private String CACINT1;    //   ��Ϣ1
    private String CRACCM2;    //           ����2
    private String CRATSF2;    //   ����2
    private String CACINT2;    //           ��Ϣ2
    private String CRACCM3;    //   ����3
    private String CRATSF3;    //           ����3
    private String CACINT3;    //   ��Ϣ3
    private String CACINT;    //           ����Ϣ
    private String AMOUNT;    //   ��Ϣ�ϼ�

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getAVABAL() {
        return AVABAL;
    }

    public void setAVABAL(String AVABAL) {
        this.AVABAL = AVABAL;
    }

    public String getCLSDAT() {
        return CLSDAT;
    }

    public void setCLSDAT(String CLSDAT) {
        this.CLSDAT = CLSDAT;
    }

    public String getLINTDT() {
        return LINTDT;
    }

    public void setLINTDT(String LINTDT) {
        this.LINTDT = LINTDT;
    }

    public String getCRACCM1() {
        return CRACCM1;
    }

    public void setCRACCM1(String CRACCM1) {
        this.CRACCM1 = CRACCM1;
    }

    public String getCRATSF1() {
        return CRATSF1;
    }

    public void setCRATSF1(String CRATSF1) {
        this.CRATSF1 = CRATSF1;
    }

    public String getCACINT1() {
        return CACINT1;
    }

    public void setCACINT1(String CACINT1) {
        this.CACINT1 = CACINT1;
    }

    public String getCRACCM2() {
        return CRACCM2;
    }

    public void setCRACCM2(String CRACCM2) {
        this.CRACCM2 = CRACCM2;
    }

    public String getCRATSF2() {
        return CRATSF2;
    }

    public void setCRATSF2(String CRATSF2) {
        this.CRATSF2 = CRATSF2;
    }

    public String getCACINT2() {
        return CACINT2;
    }

    public void setCACINT2(String CACINT2) {
        this.CACINT2 = CACINT2;
    }

    public String getCRACCM3() {
        return CRACCM3;
    }

    public void setCRACCM3(String CRACCM3) {
        this.CRACCM3 = CRACCM3;
    }

    public String getCRATSF3() {
        return CRATSF3;
    }

    public void setCRATSF3(String CRATSF3) {
        this.CRATSF3 = CRATSF3;
    }

    public String getCACINT3() {
        return CACINT3;
    }

    public void setCACINT3(String CACINT3) {
        this.CACINT3 = CACINT3;
    }

    public String getCACINT() {
        return CACINT;
    }

    public void setCACINT(String CACINT) {
        this.CACINT = CACINT;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }
}
