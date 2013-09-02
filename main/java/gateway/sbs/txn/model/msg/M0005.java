package gateway.sbs.txn.model.msg;

/**
 * 密码修改
 */
public class M0005 extends MTia {
    private String TLRPWD;              // 原密码
    private String NEWPWD;              // 新密码
    private String CFMPWD; // 确认新密码

    public M0005(String TLRPWD, String NEWPWD, String CFMPWD) {
        this.TLRPWD = TLRPWD;
        this.NEWPWD = NEWPWD;
        this.CFMPWD = CFMPWD;
    }
}
