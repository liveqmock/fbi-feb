package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-19      �Զ�ת��Ҫ�ر�
 * Time: ����10:25
 * To change this template use File | Settings | File Templates.
 */
public class M9a25 extends MTia {

    private String BATSEQ = "111111";      //ƽ̨˳���
    private String ORGIDT = "010";      //������
    private String DEPNUM = "60";      //���ź�
    private String ATRCDE = "";      //�Զ�ת����
    private String TRFSEQ = "";      //Ҫ��˳���
    private String TRFKID = "";      //Ҫ�����
    private String TRFNUM = "";      //Ҫ�غ�
    private String TRFOPR = "";      //Ҫ�ز�����
    private String AMTTYP = "";      //Ҫ�ؽ�����
    private String AMTSDE = "";      //Ҫ�ؽ������
    private String FUNCDE = "";      //�������
    private String BEGNUM = "";      //��ʼ����

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

    public String getATRCDE() {
        return ATRCDE;
    }

    public void setATRCDE(String ATRCDE) {
        this.ATRCDE = ATRCDE;
    }

    public String getTRFSEQ() {
        return TRFSEQ;
    }

    public void setTRFSEQ(String TRFSEQ) {
        this.TRFSEQ = TRFSEQ;
    }

    public String getTRFKID() {
        return TRFKID;
    }

    public void setTRFKID(String TRFKID) {
        this.TRFKID = TRFKID;
    }

    public String getTRFNUM() {
        return TRFNUM;
    }

    public void setTRFNUM(String TRFNUM) {
        this.TRFNUM = TRFNUM;
    }

    public String getTRFOPR() {
        return TRFOPR;
    }

    public void setTRFOPR(String TRFOPR) {
        this.TRFOPR = TRFOPR;
    }

    public String getAMTTYP() {
        return AMTTYP;
    }

    public void setAMTTYP(String AMTTYP) {
        this.AMTTYP = AMTTYP;
    }

    public String getAMTSDE() {
        return AMTSDE;
    }

    public void setAMTSDE(String AMTSDE) {
        this.AMTSDE = AMTSDE;
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
}
