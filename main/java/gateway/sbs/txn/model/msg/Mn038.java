package gateway.sbs.txn.model.msg;

/**
 * �Թ�֧��ȷ��
 */
public class Mn038 extends MTia {

    private String FBTIDX = "";              // ����

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
