package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-10-11            //��Ʊɾ��������
 * Time: ����9:11
 * To change this template use File | Settings | File Templates.
 */
public class M8409 extends MTia {

    private String BATSEQ = "111111";  //̨˳���
    private String ORGIDT = "010";  //����
    private String DEPNUM = "60";  //�ź�
    private String FUNCDE = "";  //�ܲ���  0��ɾ�� 1 ����ɾ��
    private String VCHSET = "";  //Ʊ�׺�
    private String SETSEQ = "";  //�����
    private String CRNYER = "";  //
    private String STMPNY = "";  //
    private String NSTMPG = "";  //
    private String PROFLG = "";  //

    public M8409(){

    }
    public M8409(String VCHSET){
        this.VCHSET = VCHSET;
    }
    public M8409(String VCHSET,String SETSEQ){
        this.VCHSET = VCHSET;
        this.SETSEQ = SETSEQ;
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

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getSETSEQ() {
        return SETSEQ;
    }

    public void setSETSEQ(String SETSEQ) {
        this.SETSEQ = SETSEQ;
    }

    public String getCRNYER() {
        return CRNYER;
    }

    public void setCRNYER(String CRNYER) {
        this.CRNYER = CRNYER;
    }

    public String getSTMPNY() {
        return STMPNY;
    }

    public void setSTMPNY(String STMPNY) {
        this.STMPNY = STMPNY;
    }

    public String getNSTMPG() {
        return NSTMPG;
    }

    public void setNSTMPG(String NSTMPG) {
        this.NSTMPG = NSTMPG;
    }

    public String getPROFLG() {
        return PROFLG;
    }

    public void setPROFLG(String PROFLG) {
        this.PROFLG = PROFLG;
    }
}
