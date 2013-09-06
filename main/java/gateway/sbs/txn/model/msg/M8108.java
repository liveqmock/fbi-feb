package gateway.sbs.txn.model.msg;

/**
 * 查询账户信息
 */
public class M8108 extends MTia {

    private String BATSEQ = "111111";  // 平台顺序号
    private String ORGIDT = "010";     // 机构号
    private String DEPNUM = "60";      // 部门号
    private String ORGID3 = "010";     // 账户机构号
    private String ACTNUM = "";        // 14位账号(客户号+核算码+币别)

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

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }
}
