package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15    ��������ѯ
 * Time: ����10:10
 * To change this template use File | Settings | File Templates.
 */
public class M9815 extends MTia{

    private String BATSEQ = "111111";     //ƽ̨˳���
    private String ORGIDT = "010";     //������
    private String DEPNUM = "60";     //���ź�
    private String PLCODE = "";     //������
    private String PLCNAM = "";     //����������
    private String GLCODE = "";     //������
    private String PLSCDE = "";     //������Ŀ
    private String PLCTYP = "";     //��������
    private String FUNCDE = "";     //�������
    private String BEGNUM = "000001";     //��ʼ����

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

    public String getPLCODE() {
        return PLCODE;
    }

    public void setPLCODE(String PLCODE) {
        this.PLCODE = PLCODE;
    }

    public String getPLCNAM() {
        return PLCNAM;
    }

    public void setPLCNAM(String PLCNAM) {
        this.PLCNAM = PLCNAM;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getPLSCDE() {
        return PLSCDE;
    }

    public void setPLSCDE(String PLSCDE) {
        this.PLSCDE = PLSCDE;
    }

    public String getPLCTYP() {
        return PLCTYP;
    }

    public void setPLCTYP(String PLCTYP) {
        this.PLCTYP = PLCTYP;
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
