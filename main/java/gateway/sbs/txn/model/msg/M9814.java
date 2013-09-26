package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10
 * Time: ����9:03
 * To change this template use File | Settings | File Templates.
 */
public class M9814 extends MTia {

    private String BATSEQ = "111111";   //ƽ̨˳��� �̶�ֵ  "111111"  Y
    private String ORGIDT = "010";      //������  �̶�ֵ      "010"      Y
    private String DEPNUM = "60";       //���ź�  �̶�ֵ      "60"       Y
    private String APCODE = "";         //������                         Y
    private String APCNAM = "";         //����������                     Y
    private String APCTYP = "";        //���������� 0-��ͨ�� 4-999��ʱ��Ƿ 5-������������ 6-933���  7-�Զ���ת�� 8-��ʱ�� 9-������
    private String GLCODE = "";         //����������                      Y
    private String PLCODE = "";  //��Ӧ������(�������������ж�Ӧ��������)  �������ACTPLC
    private String INTEXP="";           //��Ϣ֧���˻�������
    private String INTINC="";           //��Ϣ�����˻�������
    private String TRNIDT="";            //δʵ����Ϣ֧��������
    private String FEEAPC="";            //δʵ����Ϣ���������
    private String PDCTYP="0";            //����������
    private String EBKCDE="";            //��Ӧ���к�����
    private String OPSAPC = "";         //������׼�������         Y
    private String APCDCR = "";         //����������               Y
    private String INTDAC = "";         //Ӧ����Ϣ�˻�������       Y
    private String INTCAC = "";         //Ӧ����Ϣ�˻�������       Y
    private String FUNCDE="0";           //������ 0-���ʲ�ѯ��1-��ʲ�ѯ 2-�޸ġ�3-ɾ����4-����   Y
    private String DEGNUM = "";         //��ʼ���               Y
    private String CLRFLG = "1";         //���ú�                 Y
    private String MODFLG= "0";        // ��ǰ�޸ı�־  0-���޸ģ�1-�޸�
    private String TLRNCDE="";         //��Ա��

    //-----------------------------------------------------------
    public M9814() {
    }

    public M9814(String GLCODE, String APCODE) {
        this.GLCODE = GLCODE;
        this.APCODE = APCODE;
    }

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

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getAPCNAM() {
        return APCNAM;
    }

    public void setAPCNAM(String APCNAM) {
        this.APCNAM = APCNAM;
    }

    public String getAPCTYP() {
        return APCTYP;
    }

    public void setAPCTYP(String APCTYP) {
        this.APCTYP = APCTYP;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getPLCODE() {
        return PLCODE;
    }

    public void setPLCODE(String PLCODE) {
        this.PLCODE = PLCODE;
    }

    public String getINTEXP() {
        return INTEXP;
    }

    public void setINTEXP(String INTEXP) {
        this.INTEXP = INTEXP;
    }

    public String getINTINC() {
        return INTINC;
    }

    public void setINTINC(String INTINC) {
        this.INTINC = INTINC;
    }

    public String getTRNIDT() {
        return TRNIDT;
    }

    public void setTRNIDT(String TRNIDT) {
        this.TRNIDT = TRNIDT;
    }

    public String getFEEAPC() {
        return FEEAPC;
    }

    public void setFEEAPC(String FEEAPC) {
        this.FEEAPC = FEEAPC;
    }

    public String getPDCTYP() {
        return PDCTYP;
    }

    public void setPDCTYP(String PDCTYP) {
        this.PDCTYP = PDCTYP;
    }

    public String getEBKCDE() {
        return EBKCDE;
    }

    public void setEBKCDE(String EBKCDE) {
        this.EBKCDE = EBKCDE;
    }

    public String getOPSAPC() {
        return OPSAPC;
    }

    public void setOPSAPC(String OPSAPC) {
        this.OPSAPC = OPSAPC;
    }

    public String getAPCDCR() {
        return APCDCR;
    }

    public void setAPCDCR(String APCDCR) {
        this.APCDCR = APCDCR;
    }

    public String getINTDAC() {
        return INTDAC;
    }

    public void setINTDAC(String INTDAC) {
        this.INTDAC = INTDAC;
    }

    public String getINTCAC() {
        return INTCAC;
    }

    public void setINTCAC(String INTCAC) {
        this.INTCAC = INTCAC;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getDEGNUM() {
        return DEGNUM;
    }

    public void setDEGNUM(String DEGNUM) {
        this.DEGNUM = DEGNUM;
    }

    public String getCLRFLG() {
        return CLRFLG;
    }

    public void setCLRFLG(String CLRFLG) {
        this.CLRFLG = CLRFLG;
    }

    public String getTLRNCDE() {
        return TLRNCDE;
    }

    public void setTLRNCDE(String TLRNCDE) {
        this.TLRNCDE = TLRNCDE;
    }

    public String getMODFLG() {
        return MODFLG;
    }

    public void setMODFLG(String MODFLG) {
        this.MODFLG = MODFLG;
    }
}
