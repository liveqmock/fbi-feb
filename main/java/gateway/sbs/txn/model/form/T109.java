package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * 8109 �رտͻ��˻�ǰ���˻���ѯ ��Ӧ����
 */
public class T109 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 7, 4, 3, 72, 3, 3, 10, 10, 1, 1, 18, 1, 4, 1, 3, 42, 6, 5, 1, 4, 1, 3, 42, 6, 5, 2, 8, 1, 21, 21, 8, 1, 10, 4};
    }

    private String ORGIDT;          // ������
    private String CUSIDT;          // �ͻ���
    private String APCODE;          // ������
    private String CURCDE;          // �ұ���
    private String ACTNAM;          // �˻�����
    private String DINRAT;          // �跽���ʴ���  �˻�����Ϊ�����˻�ʱ,Ϊ������,�������ʴ���
    private String CINRAT;          // �������ʴ���  �˻�����Ϊ����˻�ʱ,Ϊ������,������ʴ���
    private String DRATSF;          // �跽�̶��򸡶�����  �����������Զ���������ֵ
    private String CRATSF;          // �����̶��򸡶�����  ����������Զ���������ֵ
    private String INTFLG;          // ��Ϣ��־ 1. �˻��ֶμ�Ϣ 2. ���׼�Ϣ 3. �˻�Э����Ϣ 4. �˻����ֶμ�Ϣ 6. 903Э����Ϣ 0. ����Ϣ
    private String INTCYC;          // ��Ϣ���� M?�� S?�� H?���� Y?�� F?������
    private String INTTRA;          // תϢ�˻�
    private String STMCYC;          // ���˵��������� D.�� T?Ѯ M?�� S.�� Y.��� P.��ҳ E.�з����� F.��������
    private String STMCDT;          // ��������
    private String STMFMT;          // ��ҳ��ʽ C.�ⲿ�ͻ���ҳ S.�����˻���ҳ O.������ҳ
    private String STMSHT;          // ���˷���
    private String STMADD;          // ���˵���ַ
    private String STMZIP;          // �ʱ�
    private String STMDEP;          // ���˵��ַ�����
    private String LEGCYC;          // �ֻ��˳������� D.�� T?Ѯ M?�� S.�� Y.��� P.��ҳ E.�з����� F.��������
    private String LEGCDT;          // �ֻ��˳�������
    private String LEGFMT;          // �ֻ�����ҳ��ʽ C.�ⲿ�ͻ���ҳ S.�����˻���ҳ O.������ҳ
    private String LEGSHT;          // �ֻ��˳��˷���
    private String LEGADD;          // �ֻ��˵�ַ
    private String LEGZIP;          // �˻���; 100000. һ����㻧 200000. �ʽ�ר�û� 300000. Ʊ�ݻ� 400000. Ʊ�ݳ��˻� 500000. ���ʻ� 600000. ���⻧ 900000. ����
    private String LEGDEP;          // �ֻ��˷ַ�����
    private String DEPNUM;          // ���ź�
    private String OPNDAT;          // ��������
    private String CQEFLG;          // ֧Ʊ/���۱�־ ѡ������	1?֧Ʊ��2?���ۣ�3?����
    private String BALLIM;          // Э���˻�����޶� ���֣�û��С����
    private String OVELIM;          // ͸֧�޶� ���֣�û��С����
    private String OVEEXP;          // ��ȵ����� YYYYMMDD
    private String ACTTYP;          // �˻�����
    private String CRIRAT;          // Э������
    private String GLCODE;          // ������

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getDINRAT() {
        return DINRAT;
    }

    public void setDINRAT(String DINRAT) {
        this.DINRAT = DINRAT;
    }

    public String getCINRAT() {
        return CINRAT;
    }

    public void setCINRAT(String CINRAT) {
        this.CINRAT = CINRAT;
    }

    public String getDRATSF() {
        return DRATSF;
    }

    public void setDRATSF(String DRATSF) {
        this.DRATSF = DRATSF;
    }

    public String getCRATSF() {
        return CRATSF;
    }

    public void setCRATSF(String CRATSF) {
        this.CRATSF = CRATSF;
    }

    public String getINTFLG() {
        return INTFLG;
    }

    public void setINTFLG(String INTFLG) {
        this.INTFLG = INTFLG;
    }

    public String getINTCYC() {
        return INTCYC;
    }

    public void setINTCYC(String INTCYC) {
        this.INTCYC = INTCYC;
    }

    public String getINTTRA() {
        return INTTRA;
    }

    public void setINTTRA(String INTTRA) {
        this.INTTRA = INTTRA;
    }

    public String getSTMCYC() {
        return STMCYC;
    }

    public void setSTMCYC(String STMCYC) {
        this.STMCYC = STMCYC;
    }

    public String getSTMCDT() {
        return STMCDT;
    }

    public void setSTMCDT(String STMCDT) {
        this.STMCDT = STMCDT;
    }

    public String getSTMFMT() {
        return STMFMT;
    }

    public void setSTMFMT(String STMFMT) {
        this.STMFMT = STMFMT;
    }

    public String getSTMSHT() {
        return STMSHT;
    }

    public void setSTMSHT(String STMSHT) {
        this.STMSHT = STMSHT;
    }

    public String getSTMADD() {
        return STMADD;
    }

    public void setSTMADD(String STMADD) {
        this.STMADD = STMADD;
    }

    public String getSTMZIP() {
        return STMZIP;
    }

    public void setSTMZIP(String STMZIP) {
        this.STMZIP = STMZIP;
    }

    public String getSTMDEP() {
        return STMDEP;
    }

    public void setSTMDEP(String STMDEP) {
        this.STMDEP = STMDEP;
    }

    public String getLEGCYC() {
        return LEGCYC;
    }

    public void setLEGCYC(String LEGCYC) {
        this.LEGCYC = LEGCYC;
    }

    public String getLEGCDT() {
        return LEGCDT;
    }

    public void setLEGCDT(String LEGCDT) {
        this.LEGCDT = LEGCDT;
    }

    public String getLEGFMT() {
        return LEGFMT;
    }

    public void setLEGFMT(String LEGFMT) {
        this.LEGFMT = LEGFMT;
    }

    public String getLEGSHT() {
        return LEGSHT;
    }

    public void setLEGSHT(String LEGSHT) {
        this.LEGSHT = LEGSHT;
    }

    public String getLEGADD() {
        return LEGADD;
    }

    public void setLEGADD(String LEGADD) {
        this.LEGADD = LEGADD;
    }

    public String getLEGZIP() {
        return LEGZIP;
    }

    public void setLEGZIP(String LEGZIP) {
        this.LEGZIP = LEGZIP;
    }

    public String getLEGDEP() {
        return LEGDEP;
    }

    public void setLEGDEP(String LEGDEP) {
        this.LEGDEP = LEGDEP;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public void setDEPNUM(String DEPNUM) {
        this.DEPNUM = DEPNUM;
    }

    public String getOPNDAT() {
        return OPNDAT;
    }

    public void setOPNDAT(String OPNDAT) {
        this.OPNDAT = OPNDAT;
    }

    public String getCQEFLG() {
        return CQEFLG;
    }

    public void setCQEFLG(String CQEFLG) {
        this.CQEFLG = CQEFLG;
    }

    public String getBALLIM() {
        return BALLIM;
    }

    public void setBALLIM(String BALLIM) {
        this.BALLIM = BALLIM;
    }

    public String getOVELIM() {
        return OVELIM;
    }

    public void setOVELIM(String OVELIM) {
        this.OVELIM = OVELIM;
    }

    public String getOVEEXP() {
        return OVEEXP;
    }

    public void setOVEEXP(String OVEEXP) {
        this.OVEEXP = OVEEXP;
    }

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public String getCRIRAT() {
        return CRIRAT;
    }

    public void setCRIRAT(String CRIRAT) {
        this.CRIRAT = CRIRAT;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }
}
