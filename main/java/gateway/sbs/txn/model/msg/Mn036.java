package gateway.sbs.txn.model.msg;

/**
 * 对公支付多笔查询
 */
public class Mn036 extends MTia {

    private String ORDDAT = "";              // 交易日期
    private String PRCSTS = "";              // 状态
    private String BEGNUM = "";              // 起始笔数

    public Mn036(String ORDDAT, String PRCSTS, String BEGNUM) {
        this.ORDDAT = ORDDAT;
        this.PRCSTS = PRCSTS;
        this.BEGNUM = BEGNUM;
    }

    public String getORDDAT() {
        return ORDDAT;
    }

    public void setORDDAT(String ORDDAT) {
        this.ORDDAT = ORDDAT;
    }

    public String getPRCSTS() {
        return PRCSTS;
    }

    public void setPRCSTS(String PRCSTS) {
        this.PRCSTS = PRCSTS;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
