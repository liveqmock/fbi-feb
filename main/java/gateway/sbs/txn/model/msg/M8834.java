package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-5         查询传票请求报文
 * Time: 上午8:41
 * To change this template use File | Settings | File Templates.
 */
public class M8834 extends MTia {

    private String BATSEQ = "111111";//平台顺序号
    private String ORGIDT = "010";//    机构号
    private String DEPNUM = "60";//    部门号
    private String CURCDE = "";//    币别
    private String APCODE = "";//    核算码
    private String CUSIDT = "";//    客户号
    private String TXNAMT = "";//    交易金额
    private String FUNCDE = "";//    功能参数
    private String BEGNUM = "000001";//    起始笔数
    private String VCHSET = "";//    传票套号
    private String TLRNUM = "";//    柜员号

    public M8834() {
    }
    //币别 核算码 柜员号 传票套号  客户号 金额
    public M8834(String CURCDE, String APCODE, String TLRNUM, String VCHSET, String CUSIDT, String TXNAMT) {
        this.CURCDE = CURCDE;
        this.APCODE = APCODE;
        this.TLRNUM = TLRNUM;
        this.VCHSET = VCHSET;
        this.CUSIDT = CUSIDT;
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

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
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

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }
}
