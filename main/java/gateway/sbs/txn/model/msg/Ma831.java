package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/12      存款种类与利率码对应表维护-添加
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class Ma831 extends MTia {

    private String DPTTYP = "";     //存款种类
    private String CURCDE = "";     //货币代码
    private String ACTTYP = "";     //帐户类型
    private String PRDTYP = "";     //存期种类
    private String DPTPRD = "";     //对应存期
    private String INTCDE = "";     //对应利率码
    private String RECSTS = "";     //记录状态

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

    public String getPRDTYP() {
        return PRDTYP;
    }

    public void setPRDTYP(String PRDTYP) {
        this.PRDTYP = PRDTYP;
    }

    public String getDPTPRD() {
        return DPTPRD;
    }

    public void setDPTPRD(String DPTPRD) {
        this.DPTPRD = DPTPRD;
    }

    public String getINTCDE() {
        return INTCDE;
    }

    public void setINTCDE(String INTCDE) {
        this.INTCDE = INTCDE;
    }

    public String getRECSTS() {
        return RECSTS;
    }

    public void setRECSTS(String RECSTS) {
        this.RECSTS = RECSTS;
    }
}
