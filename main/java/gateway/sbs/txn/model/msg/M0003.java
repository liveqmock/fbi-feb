package gateway.sbs.txn.model.msg;

/**
 * ǿ��ǩ��
 */
public class M0003 extends MTia {
    private String TLRNUM;              // ��Ա

    public M0003(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }
}
