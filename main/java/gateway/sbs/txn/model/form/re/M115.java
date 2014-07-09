package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/7/9   无相应帐户资料或帐号错误
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
public class M115 extends SOFFormBody {
    {
        fieldTypes = new int[]{1};
        fieldLengths = new int[]{17};
    }

    private String ACTNUM ;

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }
}
