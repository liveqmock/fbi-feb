package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/12      存款种类与利率码对应表维护-多笔查询
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class Ma835 extends MTia {

    private String DPTTYP = "";     //存款种类
    private String CURCDE = "";     //货币代码
    private String ACTTYP = "";     //帐户类型

    public String getDPTTYP() {
        return DPTTYP;
    }

    public void setDPTTYP(String DPTTYP) {
        this.DPTTYP = DPTTYP;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }
}
