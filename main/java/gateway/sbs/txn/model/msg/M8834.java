package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-5         ��ѯ��Ʊ������
 * Time: ����8:41
 * To change this template use File | Settings | File Templates.
 */
public class M8834 extends MTia {

    private String BATSEQ = "111111";//ƽ̨˳���
    private String ORGIDT = "010";//    ������
    private String DEPNUM = "60";//    ���ź�
    private String CURCDE = "";//    �ұ�
    private String APCODE = "";//    ������
    private String CUSIDT = "";//    �ͻ���
    private String TXNAMT = "";//    ���׽��
    private String FUNCDE = "";//    ���ܲ���
    private String BEGNUM = "000001";//    ��ʼ����
    private String VCHSET = "";//    ��Ʊ�׺�
    private String TLRNUM = "";//    ��Ա��

    public M8834() {
    }
    //�ұ� ������ ��Ա�� ��Ʊ�׺�  �ͻ��� ���
    public M8834(String CURCDE, String APCODE, String TLRNUM, String VCHSET, String CUSIDT, String TXNAMT) {
        this.CURCDE = CURCDE;
        this.APCODE = APCODE;
        this.TLRNUM = TLRNUM;
        this.VCHSET = VCHSET;
        this.CUSIDT = CUSIDT;
        this.TXNAMT = TXNAMT;
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

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }
}
