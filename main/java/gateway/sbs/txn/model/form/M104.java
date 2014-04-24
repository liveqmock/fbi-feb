package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-4-10
 * Time: обнГ4:44
 * To change this template use File | Settings | File Templates.
 */
public class M104 extends SOFFormBody {

    {
        fieldTypes = new int[]{1};
        fieldLengths = new int[]{99};
    }

    private String MESSAG ;

    public String getMESSAG() {
        return MESSAG;
    }

    public void setMESSAG(String MESSAG) {
        this.MESSAG = MESSAG;
    }
}
