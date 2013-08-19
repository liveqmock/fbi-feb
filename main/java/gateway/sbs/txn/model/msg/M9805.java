package gateway.sbs.txn.model.msg;

/**
 * 利率多笔查询
 */
public class M9805 extends MTia {
    private String CURCDE;  // 币别
    private String IRTDAT; // 日期

    public M9805(String CURCDE, String IRTDAT) {
        this.CURCDE = CURCDE;
        this.IRTDAT = IRTDAT;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public String getIRTDAT() {
        return IRTDAT;
    }
}
