package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15           ˰�����ѯ
 * Time: ����1:39
 * To change this template use File | Settings | File Templates.
 */
public class M9a30 extends MTia {

    private String BATSEQ = "111111";      //ƽ̨˳���
    private String ORGIDT = "010";      //������
    private String DEPNUM = "60";      //���ź�
    private String TAXCDE = "";      //˰����
    private String TAXDCR = "";      //˰������
    private String TAXFG1 = "";      //˰�ʱ�ʶ1
    private String TAXFG2 = "";      //˰�ʱ�ʶ2
    private String TAXFG3 = "";      //˰�ʱ�ʶ3
    private String FUNCDE = "";      //���ܲ���
    private String BEGNUM = "000001";      //��ʼ���

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

    public String getTAXCDE() {
        return TAXCDE;
    }

    public void setTAXCDE(String TAXCDE) {
        this.TAXCDE = TAXCDE;
    }

    public String getTAXDCR() {
        return TAXDCR;
    }

    public void setTAXDCR(String TAXDCR) {
        this.TAXDCR = TAXDCR;
    }

    public String getTAXFG1() {
        return TAXFG1;
    }

    public void setTAXFG1(String TAXFG1) {
        this.TAXFG1 = TAXFG1;
    }

    public String getTAXFG2() {
        return TAXFG2;
    }

    public void setTAXFG2(String TAXFG2) {
        this.TAXFG2 = TAXFG2;
    }

    public String getTAXFG3() {
        return TAXFG3;
    }

    public void setTAXFG3(String TAXFG3) {
        this.TAXFG3 = TAXFG3;
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
