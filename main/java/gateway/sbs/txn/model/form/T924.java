package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * ��Ա�����޸�-��Ӧ����Form��
 */
public class T924 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1};
        fieldLengths = new int[]{4, 8};
    }

    private String TLRNUM;          // ��Ա��
    private String TLRPWD;          // ����

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
