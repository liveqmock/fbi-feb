package gateway.sbs.txn.model.msg;

/**
 * 批量代发撤销
 */
public class Mn045 extends MTia {

    private String FBTIDX = "";              // 索引

    public Mn045(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }

    public String getFBTIDX() {
        return FBTIDX;
    }

    public void setFBTIDX(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }
}
