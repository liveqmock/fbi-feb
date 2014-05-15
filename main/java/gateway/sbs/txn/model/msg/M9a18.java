package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15
 * Time: 下午12:46
 * To change this template use File | Settings | File Templates.
 */
public class M9a18 extends MTia{

    private String BATSEQ = "111111";     //平台顺序号
    private String ORGIDT = "010";     //机构号
    private String DEPNUM = "60";     //部门号
    private String CTRCDE = "";     //国家码
    private String CTRNMC = "";     //国家名称(汉)
    private String CTRNME = "";     //国家名称(英)
    private String SWFCTR = "";     //SWIFT国家码
    private String RSVFG1 = "";     //备用标志1
    private String RSVFG2 = "";     //备用标志2
    private String RSVFG3 = "";     //备用标志3
    private String FUNCDE = "";     //功能参数
    private String BEGNUM = "000001";     //起始序号

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

    public String getCTRCDE() {
        return CTRCDE;
    }

    public void setCTRCDE(String CTRCDE) {
        this.CTRCDE = CTRCDE;
    }

    public String getCTRNMC() {
        return CTRNMC;
    }

    public void setCTRNMC(String CTRNMC) {
        this.CTRNMC = CTRNMC;
    }

    public String getCTRNME() {
        return CTRNME;
    }

    public void setCTRNME(String CTRNME) {
        this.CTRNME = CTRNME;
    }

    public String getSWFCTR() {
        return SWFCTR;
    }

    public void setSWFCTR(String SWFCTR) {
        this.SWFCTR = SWFCTR;
    }

    public String getRSVFG1() {
        return RSVFG1;
    }

    public void setRSVFG1(String RSVFG1) {
        this.RSVFG1 = RSVFG1;
    }

    public String getRSVFG2() {
        return RSVFG2;
    }

    public void setRSVFG2(String RSVFG2) {
        this.RSVFG2 = RSVFG2;
    }

    public String getRSVFG3() {
        return RSVFG3;
    }

    public void setRSVFG3(String RSVFG3) {
        this.RSVFG3 = RSVFG3;
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
