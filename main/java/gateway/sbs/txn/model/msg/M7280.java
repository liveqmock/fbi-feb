package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/9           ���ἰ�ⶳ
 * Time: 9:54
 * To change this template use File | Settings | File Templates.
 */
public class M7280 extends MTia {

    private String BATSEQ = "111111";      //ƽ̨˳���
    private String ORGIDT = "010";      //������
    private String DEPNUM = "60";      //���ź�
    private String PROMOD = "";      //����ģʽ
    private String ACTNUM = "";      //�˺�
    private String TXNAMT = "";      //������
    private String FRZMOD = "";      //���᷽ʽ
    private String FRZFLG = "";      //��ȡ�涨
    private String FRZRSN = "";      //����ԭ��
    private String FRZDAT = "";      //��������
    private String FRZEDT = "";      //�ⶳ����
    private String PRDSEQ = "";      //�������

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

    public String getPROMOD() {
        return PROMOD;
    }

    public void setPROMOD(String PROMOD) {
        this.PROMOD = PROMOD;
    }

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getFRZMOD() {
        return FRZMOD;
    }

    public void setFRZMOD(String FRZMOD) {
        this.FRZMOD = FRZMOD;
    }

    public String getFRZFLG() {
        return FRZFLG;
    }

    public void setFRZFLG(String FRZFLG) {
        this.FRZFLG = FRZFLG;
    }

    public String getFRZRSN() {
        return FRZRSN;
    }

    public void setFRZRSN(String FRZRSN) {
        this.FRZRSN = FRZRSN;
    }

    public String getFRZDAT() {
        return FRZDAT;
    }

    public void setFRZDAT(String FRZDAT) {
        this.FRZDAT = FRZDAT;
    }

    public String getFRZEDT() {
        return FRZEDT;
    }

    public void setFRZEDT(String FRZEDT) {
        this.FRZEDT = FRZEDT;
    }

    public String getPRDSEQ() {
        return PRDSEQ;
    }

    public void setPRDSEQ(String PRDSEQ) {
        this.PRDSEQ = PRDSEQ;
    }
}
