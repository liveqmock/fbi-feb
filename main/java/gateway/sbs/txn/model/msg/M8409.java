package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-10-11            //传票删除请求报文
 * Time: 上午9:11
 * To change this template use File | Settings | File Templates.
 */
public class M8409 extends MTia {

    private String BATSEQ = "111111";  //台顺序号
    private String ORGIDT = "010";  //构号
    private String DEPNUM = "60";  //门号
    private String FUNCDE = "";  //能参数  0套删除 1 单笔删除
    private String VCHSET = "";  //票套号
    private String SETSEQ = "";  //内序号
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
