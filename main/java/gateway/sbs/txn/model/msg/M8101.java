package gateway.sbs.txn.model.msg;

/**
 * ���� - 8101
 */
public class M8101 extends MTia {

    private String BATSEQ = "111111";  // ƽ̨˳���
    private String ORGIDT = "010";     // ������
    private String DEPNUM = "60";      // ���ź�
    private String ACTNUM = "";        // 14λ�˺�(�ͻ���+������+�ұ�)
    private String ACTNAM = "";        // �˻�����
    private String STMFMT = "C";       // ��ҳ��ʽ C.�ⲿ�ͻ���ҳ S.�����˻���ҳ O.������ҳ
    private String STMSHT = "001";     // ���˷���
    private String STMDEP = "01060";   // ���˵��ַ�����
    private String STMADD = "";        // ���˵���ַ
    private String STMZIP = "";        // �ʱ�
    private String STMCYC = "M";       // ���˵��������� D.�� T?Ѯ M?�� S.�� Y.��� P.��ҳ E.�з����� F.��������
    private String STMCDT = "0031";    // ��������
    private String ACTTYP = "3";       // �˻����� 1.�����˻� 2.�����˻� 3.�����ͻ��˻� 4.����ϵͳ�ۺ��˻�
    private String DEPNU3 = "60";      // ���ź�
    private String INTFLG = "4";        // ��Ϣ��־ 1. �˻��ֶμ�Ϣ 2. ���׼�Ϣ 3. �˻�Э����Ϣ 4. �˻����ֶμ�Ϣ 6. 903Э����Ϣ 0. ����Ϣ
    private String INTCYC = "S";       // ��Ϣ���� M?�� S?�� H?���� Y?�� F?������
    private String INTTRA = "";        // תϢ�˻� 18λ�˺ţ����Բ���
    private String CQEFLG = "3";        // ֧Ʊ/���۱�־ ѡ������	1?֧Ʊ��2?���ۣ�3?����
    private String BALLIM = "000";     // Э���˻�����޶� ���֣�û��С����
    private String OVELIM = "000";     // ͸֧�޶� ���֣�û��С����
    private String OVEEXP = "";        // ��ȵ����� YYYYMMDD
    private String DINRAT = "";        // �跽���ʴ���  �˻�����Ϊ�����˻�ʱ,Ϊ������,�������ʴ���
    private String CINRAT = "";        // �������ʴ���  �˻�����Ϊ����˻�ʱ,Ϊ������,������ʴ���
    private String DRATSF = "";        // �跽�̶��򸡶�����  �����������Զ���������ֵ
    private String CRATSF = "";        // �����̶��򸡶�����  ����������Զ���������ֵ
    private String VCHAUT = "";        // ���ܴ���
    private String LEGCYC = "M";       // �ֻ��˳������� D.�� T?Ѯ M?�� S.�� Y.��� P.��ҳ E.�з����� F.��������
    private String LEGCDT = "0031";    // �ֻ��˳�������
    private String LEGFMT = "C";        // �ֻ�����ҳ��ʽ C.�ⲿ�ͻ���ҳ S.�����˻���ҳ O.������ҳ
    private String LEGADD = "";        // �ֻ��˵�ַ
    private String LEGZIP = "100000";  // �˻���; 100000. һ����㻧 200000. �ʽ�ר�û� 300000. Ʊ�ݻ� 400000. Ʊ�ݳ��˻� 500000. ���ʻ� 600000. ���⻧ 900000. ����
    private String LEGSHT = "001";     // �ֻ��˳��˷���
    private String LEGDEP = "01060";   // �ֻ��˷ַ�����
    private String GLCODE = "";        // ������      ��
    private String FXRATE = "";        // ����        ��
    private String LOCCAP = "";        // ע������ʱ�  ��
    private String TXNAMT = "";        // ���׽��     ��

    public String getBATSEQ() {
        return BATSEQ;
    }

    public void setBATSEQ(String BATSEQ) {
        this.BATSEQ = BATSEQ;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public void setDEPNUM(String DEPNUM) {
        this.DEPNUM = DEPNUM;
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

    public String getSTMDEP() {
        return STMDEP;
    }

    public void setSTMDEP(String STMDEP) {
        this.STMDEP = STMDEP;
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

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public String getDEPNU3() {
        return DEPNU3;
    }

    public void setDEPNU3(String DEPNU3) {
        this.DEPNU3 = DEPNU3;
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

    public String getVCHAUT() {
        return VCHAUT;
    }

    public void setVCHAUT(String VCHAUT) {
        this.VCHAUT = VCHAUT;
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

    public String getLEGSHT() {
        return LEGSHT;
    }

    public void setLEGSHT(String LEGSHT) {
        this.LEGSHT = LEGSHT;
    }

    public String getLEGDEP() {
        return LEGDEP;
    }

    public void setLEGDEP(String LEGDEP) {
        this.LEGDEP = LEGDEP;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getFXRATE() {
        return FXRATE;
    }

    public void setFXRATE(String FXRATE) {
        this.FXRATE = FXRATE;
    }

    public String getLOCCAP() {
        return LOCCAP;
    }

    public void setLOCCAP(String LOCCAP) {
        this.LOCCAP = LOCCAP;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }
}
