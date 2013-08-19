package gateway.sbs.txn.model.msg;

/**
 * 利率多笔查询
 */
public class M9805 extends MTia {
    private String CURCDE;  // 币别
    private String IRTDAT; // 日期

    public M9805(String curcde, String irtdat8) {
        this.CURCDE = curcde;
        this.IRTDAT = irtdat8;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public String getIRTDAT() {
        return IRTDAT;
    }
}
