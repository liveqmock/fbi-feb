package gateway.sbs.txn.model.msg;

/**
 * 关闭客户账户
 */
public class M8103 extends MTia {

    private String BATSEQ = "111111";  // 平台顺序号
    private String ORGIDT = "010";     // 机构号
    private String DEPNUM = "60";      // 部门号
    private String ACTNUM = "";        // 14位账号(客户号+核算码+币别)
    private String VCHAUT = "";        // 主管代号

    public M8103() {
    }

    public M8103(String ACTNUM) {
        this.ACTNUM = ACTNUM;
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

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getVCHAUT() {
        return VCHAUT;
    }

    public void setVCHAUT(String VCHAUT) {
        this.VCHAUT = VCHAUT;
    }
}
