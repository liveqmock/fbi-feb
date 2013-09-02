package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * 柜员密码修改-响应报文Form体
 */
public class T924 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1};
        fieldLengths = new int[]{4, 8};
    }

    private String TLRNUM;          // 柜员号
    private String TLRPWD;          // 密码

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getTLRPWD() {
        return TLRPWD;
    }

    public void setTLRPWD(String TLRPWD) {
        this.TLRPWD = TLRPWD;
    }
}
