package gateway.sbs.txn.model.msg;

/**
 * 批量代发确认
 */
public class Mn048 extends MTia {

    private String FBTIDX = "";              // 索引

    public Mn048(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }

    public String getFBTIDX() {
        return FBTIDX;
    }

    public void setFBTIDX(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }
}
