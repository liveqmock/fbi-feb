package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15           ˰�ʱ�ά��
 * Time: ����1:39
 * To change this template use File | Settings | File Templates.
 */
public class M9a29 extends MTia {

    private String BATSEQ = "111111";      //ƽ̨˳���
    private String ORGIDT = "010";      //������
    private String DEPNUM = "60";      //���ź�
    private String TAXTNO = "";      //˰���
    private String TAXCDE = "";      //˰����
    private String TAXRAT = "";      //˰��
    private String TAXADJ = "";      //˰�ʵ���ֵ
    private String FUNCDE = "";      //���ܲ���
    private String BEGNUM = "";      //��ʼ���

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

    public String getTAXTNO() {
        return TAXTNO;
    }

    public void setTAXTNO(String TAXTNO) {
        this.TAXTNO = TAXTNO;
    }

    public String getTAXCDE() {
        return TAXCDE;
    }

    public void setTAXCDE(String TAXCDE) {
        this.TAXCDE = TAXCDE;
    }

    public String getTAXRAT() {
        return TAXRAT;
    }

    public void setTAXRAT(String TAXRAT) {
        this.TAXRAT = TAXRAT;
    }

    public String getTAXADJ() {
        return TAXADJ;
    }

    public void setTAXADJ(String TAXADJ) {
        this.TAXADJ = TAXADJ;
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
