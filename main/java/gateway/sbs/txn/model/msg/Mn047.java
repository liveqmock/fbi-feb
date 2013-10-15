package gateway.sbs.txn.model.msg;

/**
 * 批量代发单笔查询
 */
public class Mn047 extends MTia {

    private String FBTIDX = "";              // 索引号
    private String ORDDAT = "";              // 交易日期

    public Mn047(String FBTIDX, String ORDDAT) {
        this.FBTIDX = FBTIDX;
        this.ORDDAT = ORDDAT;
    }

    public String getORDDAT() {
        return ORDDAT;
    }

    public void setORDDAT(String ORDDAT) {
        this.ORDDAT = ORDDAT;
    }

    public String getFBTIDX() {
        return FBTIDX;
    }

    public void setFBTIDX(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }
}
