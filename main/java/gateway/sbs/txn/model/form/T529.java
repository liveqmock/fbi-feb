package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-19
 * Time: обнГ3:29
 * To change this template use File | Settings | File Templates.
 */
public class T529 extends SOFFormBody {

        {
            fieldTypes = new int[]{1};
            fieldLengths = new int[]{14};
        }
    private String DATE;

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }
}
