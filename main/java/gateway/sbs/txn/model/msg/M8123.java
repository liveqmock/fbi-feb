package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/10          多条件查询客户账户余额
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public class M8123 extends MTia {

    private String CUSIDT = "";      //客户号
    private String APCODE = "";      //核算码
    private String CURCDE = "";      //币别
    private String ACTTYP = "";      //帐户类型
    private String CUSKID = "";      //客户类别
    private String BEGNUM = "1";      //起始笔数

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
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

    public String getCUSKID() {
        return CUSKID;
    }

    public void setCUSKID(String CUSKID) {
        this.CUSKID = CUSKID;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
