package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-12          //查询客户请求报文
 * Time: 下午9:06
 * To change this template use File | Settings | File Templates.
 */
public class M8002 extends MTia{

    private String BATSEQ = "111111";      //	平台顺序号 "111111"
    private String ORGIDT = "010";         //	机构号     "010"
    private String DEPNUM = "60";          //	部门号     "60"
    private String CUSIDT = "";           //	客户号
    private String PASTYP="";             //	证件类别
    private String PASSNO="";             //	证件号
    private String CUSIDX="";            //	简名索引
    private String LEGBDY="";           //	法人代表姓名
    private String RELCUS="";           //	相关客户
    private String INPFLG="1";          //	形式
    private String BEGNUM="000001";   //	起始笔数
    private String CUSNAM="";         //	客户名称
    private String SYSIDT="8";        //	系统标识

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
