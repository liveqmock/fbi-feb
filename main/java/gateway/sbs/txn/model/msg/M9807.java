package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-14     费率表
 * Time: 上午9:54
 * To change this template use File | Settings | File Templates.
 */
public class M9807 extends MTia {

    private String BATSEQ = "111111";      //平台顺序号
    private String ORGIDT = "010";      //机构号
    private String DEPNUM = "60";      //部门号
    private String CURCDE = "";      //币别码
    private String FRTCDE = "";      //收费码
    private String EFFDAT = "";      //生效日期
    private String FUNCDE = "6";      //功能参数
    private String BEGNUM = "000001";      //起始序号

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

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getFRTCDE() {
        return FRTCDE;
    }

    public void setFRTCDE(String FRTCDE) {
        this.FRTCDE = FRTCDE;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
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
