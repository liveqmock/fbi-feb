package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 0001柜员签到响应 T901
 * T901-ORGIDT	机构号	X(3)
 * T901-DEPNUM	部门号	X(2)
 * T901-GRPNUM	柜组代码	X(2)
 * T901-TLRNAM	柜员名称	X(8)
 * T901-TLRTYP	柜员类别	X(1)
 * T901-TLRLVL	柜员级别	X(1)
 * T901-AUTMA1	授权主管1	X(4)
 * T901-AUTPW1	授权主管1密码	X(8)
 * T901-AUTMA2	授权主管2	X(4)
 * T901-AUTPW2	授权主管2密码	X(8)
 * T901-SYSDAT	业务日期	X(8)
 * T901-TOTAL-TT1	交易数量	9(4)
 */
public class T901 extends SOFFormBody {

    private String ORGIDT;
    private String DEPNUM;
    private String GRPNUM;
    private String TLRNAM;
    private String TLRTYP;
    private String TLRLVL;
    private String AUTMA1;
    private String AUTPW1;
    private String AUTMA2;
    private String AUTPW2;
    private String SYSDAT;
    private String TXNCNT;
    private List<String> beanList = new ArrayList<>();

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 2, 2, 8, 1, 1, 4, 8, 4, 8, 8, 4};
    }

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        super.assembleFields(offset, buffer);
        byte[] bytes = new byte[buffer.length - 53];
        System.arraycopy(buffer, 53, bytes, 0, bytes.length);
        int cnt = Integer.parseInt(TXNCNT);
        if (bytes.length / cnt != 4) {
            throw new RuntimeException("长度错误,交易码数:" + cnt + " 报文长：" + bytes.length);
        }
        String txns = new String(bytes);
        for (int i = 0; i < cnt; i++) {
            beanList.add(txns.substring(i, i + 4));
        }
    }
}
