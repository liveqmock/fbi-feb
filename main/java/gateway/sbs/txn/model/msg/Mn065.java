package gateway.sbs.txn.model.msg;

/**
 * 对公支付手续费查询
 */
public class Mn065 extends MTia {

    private String FBACCT = "";              // 查询年月 6 YYYYMM
    private String FUNCDE = "";              // 查询类别 0-未缴费,1-已缴费,2-全部
    private String BEGNUM = "";              // 起始笔数

    public Mn065(String FBACCT, String FUNCDE, String BEGNUM) {
        this.FBACCT = FBACCT;
        this.FUNCDE = FUNCDE;
        this.BEGNUM = BEGNUM;
    }

    public String getFBACCT() {
        return FBACCT;
    }

    public void setFBACCT(String FBACCT) {
        this.FBACCT = FBACCT;
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
