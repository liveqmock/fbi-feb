package gateway.sbs.txn.model.msg;

/**
 * �Թ�֧������
 */
public class Mn035 extends MTia {

    private String FBTIDX = "";              // ����

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
