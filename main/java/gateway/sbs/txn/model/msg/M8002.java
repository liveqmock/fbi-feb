package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-12          //��ѯ�ͻ�������
 * Time: ����9:06
 * To change this template use File | Settings | File Templates.
 */
public class M8002 extends MTia{

    private String BATSEQ = "111111";      //	ƽ̨˳��� "111111"
    private String ORGIDT = "010";         //	������     "010"
    private String DEPNUM = "60";          //	���ź�     "60"
    private String CUSIDT = "";           //	�ͻ���
    private String PASTYP="";             //	֤�����
    private String PASSNO="";             //	֤����
    private String CUSIDX="";            //	��������
    private String LEGBDY="";           //	���˴�������
    private String RELCUS="";           //	��ؿͻ�
    private String INPFLG="1";          //	��ʽ
    private String BEGNUM="000001";   //	��ʼ����
    private String CUSNAM="";         //	�ͻ�����
    private String SYSIDT="8";        //	ϵͳ��ʶ

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

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getPASTYP() {
        return PASTYP;
    }

    public void setPASTYP(String PASTYP) {
        this.PASTYP = PASTYP;
    }

    public String getPASSNO() {
        return PASSNO;
    }

    public void setPASSNO(String PASSNO) {
        this.PASSNO = PASSNO;
    }

    public String getCUSIDX() {
        return CUSIDX;
    }

    public void setCUSIDX(String CUSIDX) {
        this.CUSIDX = CUSIDX;
    }

    public String getLEGBDY() {
        return LEGBDY;
    }

    public void setLEGBDY(String LEGBDY) {
        this.LEGBDY = LEGBDY;
    }

    public String getRELCUS() {
        return RELCUS;
    }

    public void setRELCUS(String RELCUS) {
        this.RELCUS = RELCUS;
    }

    public String getINPFLG() {
        return INPFLG;
    }

    public void setINPFLG(String INPFLG) {
        this.INPFLG = INPFLG;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }

    public String getCUSNAM() {
        return CUSNAM;
    }

    public void setCUSNAM(String CUSNAM) {
        this.CUSNAM = CUSNAM;
    }

    public String getSYSIDT() {
        return SYSIDT;
    }

    public void setSYSIDT(String SYSIDT) {
        this.SYSIDT = SYSIDT;
    }
}
