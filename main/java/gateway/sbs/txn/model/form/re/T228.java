package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15       存款种类表维护-单笔查询
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class T228 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1};
        fieldLengths = new int[]{4, 1};
    }

    private String APCODE;
    private String RECSTS;

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getRECSTS() {
        return RECSTS;
    }

    public void setRECSTS(String RECSTS) {
        this.RECSTS = RECSTS;
    }
}
