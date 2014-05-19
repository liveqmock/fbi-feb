package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-19      自动转账要素表
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public class M9a25 extends MTia {

    private String BATSEQ = "111111";      //平台顺序号
    private String ORGIDT = "010";      //机构号
    private String DEPNUM = "60";      //部门号
    private String ATRCDE = "";      //自动转帐码
    private String TRFSEQ = "";      //要素顺序号
    private String TRFKID = "";      //要素类别
    private String TRFNUM = "";      //要素号
    private String TRFOPR = "";      //要素操作码
    private String AMTTYP = "";      //要素金额类别
    private String AMTSDE = "";      //要素借贷方向
    private String FUNCDE = "";      //操作类别
    private String BEGNUM = "";      //起始笔数

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

    public String getATRCDE() {
        return ATRCDE;
    }

    public void setATRCDE(String ATRCDE) {
        this.ATRCDE = ATRCDE;
    }

    public String getTRFSEQ() {
        return TRFSEQ;
    }

    public void setTRFSEQ(String TRFSEQ) {
        this.TRFSEQ = TRFSEQ;
    }

    public String getTRFKID() {
        return TRFKID;
    }

    public void setTRFKID(String TRFKID) {
        this.TRFKID = TRFKID;
    }

    public String getTRFNUM() {
        return TRFNUM;
    }

    public void setTRFNUM(String TRFNUM) {
        this.TRFNUM = TRFNUM;
    }

    public String getTRFOPR() {
        return TRFOPR;
    }

    public void setTRFOPR(String TRFOPR) {
        this.TRFOPR = TRFOPR;
    }

    public String getAMTTYP() {
        return AMTTYP;
    }

    public void setAMTTYP(String AMTTYP) {
        this.AMTTYP = AMTTYP;
    }

    public String getAMTSDE() {
        return AMTSDE;
    }

    public void setAMTSDE(String AMTSDE) {
        this.AMTSDE = AMTSDE;
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
