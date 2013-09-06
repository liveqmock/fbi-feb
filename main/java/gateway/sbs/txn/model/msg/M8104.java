package gateway.sbs.txn.model.msg;

/**
 * �ڲ��˻����� - 8104
 */
public class M8104 extends MTia {

    private String BATSEQ = "111111";  // ƽ̨˳���
    private String ORGIDT = "010";     // ������
    private String DEPNUM = "60";      // ���ź�
    private String ACTNUM = "";        // 14λ�˺�(�ͻ���+������+�ұ�)
    private String ACTNAM = "";        // �˻�����
    private String LEGFMT = "C";        // �ֻ�����ҳ��ʽ C.�ⲿ�ͻ���ҳ S.�����˻���ҳ O.������ҳ
    private String LEGSHT = "001";     // �ֻ��˳��˷���
    private String LEGDEP = "01060";   // �ֻ��˷ַ�����
    private String LEGADD = "";        // �ֻ��˵�ַ
    private String LEGZIP = "900000";  // �˻���; 100000. һ����㻧 200000. �ʽ�ר�û� 300000. Ʊ�ݻ� 400000. Ʊ�ݳ��˻� 500000. ���ʻ� 600000. ���⻧ 900000. ����
    private String LEGCYC = "M";       // �ֻ��˳������� D.�� T?Ѯ M?�� S.�� Y.��� P.��ҳ E.�з����� F.��������
    private String LEGCDT = "0031";    // �ֻ��˳�������
    private String ACTTYP = "9";       // �˻����� 5. ��������ʻ�  6. �����ʻ� 7. ��Ƭ���ʻ� 9. �����ڲ��ͻ�
    private String INTFLG = "0";        // ��Ϣ��־ 1. �˻��ֶμ�Ϣ 2. ���׼�Ϣ 3. �˻�Э����Ϣ 4. �˻����ֶμ�Ϣ 6. 903Э����Ϣ 0. ����Ϣ
    private String INTCYC = "S";       // ��Ϣ���� M?�� S?�� H?���� Y?�� F?������
    private String DINRAT = "";        // �跽���ʴ���  �˻�����Ϊ�����˻�ʱ,Ϊ������,�������ʴ���
    private String CINRAT = "";        // �������ʴ���  �˻�����Ϊ����˻�ʱ,Ϊ������,������ʴ���
    private String DRATSF = "";        // �跽�̶��򸡶�����  �����������Զ���������ֵ
    private String CRATSF = "";        // �����̶��򸡶�����  ����������Զ���������ֵ
    private String DEPNU3 = "60";      // ���ź�
    private String VCHAUT = "";        // ���ܴ���
    private String LOCCAP = "";        // ע������ʱ�  ��
    private String INTTRA = "";        // תϢ�˻� 18λ�˺ţ����Բ���
    private String TXNAMT = "";        // ���׽��     ��
    private String GLCODE = "";        // ������      ��

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

    public String getLEGDEP() {
        return LEGDEP;
    }

    public void setLEGDEP(String LEGDEP) {
        this.LEGDEP = LEGDEP;
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

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
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

    public String getDEPNU3() {
        return DEPNU3;
    }

    public void setDEPNU3(String DEPNU3) {
        this.DEPNU3 = DEPNU3;
    }

    public String getVCHAUT() {
        return VCHAUT;
    }

    public void setVCHAUT(String VCHAUT) {
        this.VCHAUT = VCHAUT;
    }

    public String getLOCCAP() {
        return LOCCAP;
    }

    public void setLOCCAP(String LOCCAP) {
        this.LOCCAP = LOCCAP;
    }

    public String getINTTRA() {
        return INTTRA;
    }

    public void setINTTRA(String INTTRA) {
        this.INTTRA = INTTRA;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }
}
