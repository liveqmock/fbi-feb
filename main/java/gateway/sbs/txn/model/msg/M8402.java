package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-10-10           //套平
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 */
public class M8402 extends MTia{

    private String BATSEQ = "111111" ;//平台顺序号
    private String ORGIDT = "010" ;//机构号
    private String DEPNUM = "60" ;//部门号
    private String ORGID3 = "010" ;//机构号
    private String TLRNUM = "" ;//柜员号
    private String VCHSET = "" ;//传票套号
    private String FUNCDE = "" ;//功能参数
    private String TXNAMT = "" ;//交易金额
    private String BEGNUM = "01" ;//起始序号

    public M8402 (){}
    public  M8402(String VCHSET,String TXNAMT){
        this.VCHSET = VCHSET;
        this.TXNAMT = TXNAMT;
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

    public String getORGID3() {
        return ORGID3;
    }

    public void setORGID3(String ORGID3) {
        this.ORGID3 = ORGID3;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
