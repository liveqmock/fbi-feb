package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15    损益码表查询
 * Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
public class M9815 extends MTia{

    private String BATSEQ = "111111";     //平台顺序号
    private String ORGIDT = "010";     //机构号
    private String DEPNUM = "60";     //部门号
    private String PLCODE = "";     //损益码
    private String PLCNAM = "";     //损益项名称
    private String GLCODE = "";     //总帐码
    private String PLSCDE = "";     //损益子目
    private String PLCTYP = "";     //损益类型
    private String FUNCDE = "";     //操作类别
    private String BEGNUM = "000001";     //起始笔数

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
