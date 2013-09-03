package gateway.sbs.txn.model.msg;

/**
 * 利率多笔查询
 */
public class M9805 extends MTia {

    private String BATSEQ = "111111";  // 平台顺序号
    private String ORGIDT = "010";     // 机构号
    private String DEPNUM = "60";      // 部门号
    private String CURCDE;             // 币别
    private String IRTCDE = "";        // 利率码
    private String EFFDAT;             // 日期
    private String FUNCDE = "6";       // 操作类别  5-多笔查询（按利率码）6-多笔查询（按日期）
    private String BEGNUM = "000001"; // 起始笔数

    public M9805(String CURCDE, String EFFDAT) {
        this.CURCDE = CURCDE;
        this.EFFDAT = EFFDAT;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public String getBATSEQ() {
        return BATSEQ;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public String getIRTCDE() {
        return IRTCDE;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }
}
