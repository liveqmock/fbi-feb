package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-6           创建客户信息响应报文
 * Time: 上午9:58
 * To change this template use File | Settings | File Templates.
 */
public class T224 extends SOFFormBody {
    {
        fieldTypes = new int[]{ 1 };
        fieldLengths = new int[]{ 7 };
    }
    private  String CUSIDT;

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }
}
