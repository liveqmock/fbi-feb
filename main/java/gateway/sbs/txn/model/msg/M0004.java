package gateway.sbs.txn.model.msg;

/**
 * ��Ա����ǩ��
 */
public class M0004 extends MTia {

    private String TLRPWD;              // ����

    public M0004(String TLRPWD) {
        this.TLRPWD = TLRPWD;
    }

    public String getTLRPWD() {
        return TLRPWD;
    }

    public void setTLRPWD(String TLRPWD) {
        this.TLRPWD = TLRPWD;
    }
}
