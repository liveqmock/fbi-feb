package gateway.sbs.txn.model.msg;

/**
 * 对公支付确认
 */
public class Mn038 extends MTia {

    private String FBTIDX = "";              // 索引

    public Mn038(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }

    public String getFBTIDX() {
        return FBTIDX;
    }

    public void setFBTIDX(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }
}
