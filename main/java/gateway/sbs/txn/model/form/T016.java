package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 复式传票[定期转账开户]
 */
public class T016 extends SOFFormBody {

    /*
    T016-VCHSET	传票套号	X(4)
T016-TRNCDE	交易码	X(4)
T016-TLRNUM	柜员号	X(4)
T016-TRNTIM	交易时间	X(6)
T016-WATNUM	流水号	X(7)
T016-TRNDAT	交易日期	X(8)
T016-VCHPAR	摘要	X(60)
T016-PAPTYP	支票种类	X(1)
T016-PAPCDE	支票号	X(10)
T016-PAPMAC	支票密码	X(10)
T016-ORGIDT	机构号	X(3)
T016-TMCODE	前台交易码	X(4)
T016-VCHPA1	备注1	X(40)
T016-VCHPA2	冲正备注	X(60)
     */
    private String VCHSET;            // 传票套号
    private String TRNCDE;            // 交易码
    private String TLRNUM;            // 柜员号
    private String TRNTIM;            // 交易时间
    private String WATNUM;            // 流水号
    private String TRNDAT;            // 交易日期
    private String VCHPAR;            // 摘要
    private String PAPTYP;            // 支票种类
    private String PAPCDE;            // 支票号
    private String PAPMAC;            // 支票密码
    private String ORGIDT;            // 机构号
    private String TMCODE;            // 前台交易码
    private String VCHPA1;            // 备注1
    private String VCHPA2;            // 冲正备注

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        byte[] totcntBytes = new byte[4];
        byte[] curcntBytes = new byte[4];
        System.arraycopy(buffer, offset, totcntBytes, 0, 4);
//        totcnt = new String(totcntBytes);
        System.arraycopy(buffer, offset + 4, curcntBytes, 0, 4);
//        curcnt = new String(curcntBytes);

        int index = offset + 8;
        int beanLength = 63;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length);
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1};
            fieldLengths = new int[]{18, 23, 18, 23, 12};
        }

        private String DEBACT;          // 借方账号
        private String DEBAMT;          // 借方金额
        private String CREACT;      // 贷方账号
        private String CREAMT;          // 贷方金额
        private String EXRATE;          // 汇率


    }
}
