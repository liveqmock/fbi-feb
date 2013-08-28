package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 0001��Աǩ����Ӧ T901
 * T901-ORGIDT	������	X(3)
 * T901-DEPNUM	���ź�	X(2)
 * T901-GRPNUM	�������	X(2)
 * T901-TLRNAM	��Ա����	X(8)
 * T901-TLRTYP	��Ա���	X(1)
 * T901-TLRLVL	��Ա����	X(1)
 * T901-AUTMA1	��Ȩ����1	X(4)
 * T901-AUTPW1	��Ȩ����1����	X(8)
 * T901-AUTMA2	��Ȩ����2	X(4)
 * T901-AUTPW2	��Ȩ����2����	X(8)
 * T901-SYSDAT	ҵ������	X(8)
 * T901-TOTAL-TT1	��������	9(4)
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
            throw new RuntimeException("���ȴ���,��������:" + cnt + " ���ĳ���" + bytes.length);
        }
        String txns = new String(bytes);
        for (int i = 0; i < cnt; i++) {
            beanList.add(txns.substring(i, i + 4));
        }
    }
}
