package gateway.sbs.txn.model.msg;

/**
 * �����޸�
 */
public class M0005 extends MTia {
    private String TLRPWD;              // ԭ����
    private String NEWPWD;              // ������
    private String CFMPWD; // ȷ��������

    public M0005(String TLRPWD, String NEWPWD, String CFMPWD) {
        this.TLRPWD = TLRPWD;
        this.NEWPWD = NEWPWD;
        this.CFMPWD = CFMPWD;
    }
}
