package gateway.sbs.txn.model.msg;

/**
 * ������������
 */
public class Mn045 extends MTia {

    private String FBTIDX = "";              // ����

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
