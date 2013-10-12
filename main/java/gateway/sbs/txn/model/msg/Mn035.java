package gateway.sbs.txn.model.msg;

/**
 * 对公支付撤销
 */
public class Mn035 extends MTia {

    private String FBTIDX = "";              // 索引

    public Mn035(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }

    public String getFBTIDX() {
        return FBTIDX;
    }

    public void setFBTIDX(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }
}
