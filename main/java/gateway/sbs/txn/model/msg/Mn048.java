package gateway.sbs.txn.model.msg;

/**
 * ��������ȷ��
 */
public class Mn048 extends MTia {

    private String FBTIDX = "";              // ����

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
