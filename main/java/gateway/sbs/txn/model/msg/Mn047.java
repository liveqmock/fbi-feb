package gateway.sbs.txn.model.msg;

/**
 * �����������ʲ�ѯ
 */
public class Mn047 extends MTia {

    private String FBTIDX = "";              // ������
    private String ORDDAT = "";              // ��������

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
