package gateway.sbs.txn.model.msg;

/**
 * ǩ��
 */
public class M0001 extends MTia {
    private String TLRNUM;              // ��Ա
    private String TLRPWD;              // ����
    private String DEVBLN = "        "; // ǰ���豸���б���� 8���ո�

    public M0001(String TLRNUM, String TLRPWD) {
        this.TLRNUM = TLRNUM;
        this.TLRPWD = TLRPWD;
    }
}
