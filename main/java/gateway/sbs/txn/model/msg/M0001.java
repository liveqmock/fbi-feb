package gateway.sbs.txn.model.msg;

/**
 * 签到
 */
public class M0001 extends MTia {
    private String TLRNUM;              // 柜员
    private String TLRPWD;              // 密码
    private String DEVBLN = "        "; // 前端设备队列表代码 8个空格

    public M0001(String TLRNUM, String TLRPWD) {
        this.TLRNUM = TLRNUM;
        this.TLRPWD = TLRPWD;
    }
}
