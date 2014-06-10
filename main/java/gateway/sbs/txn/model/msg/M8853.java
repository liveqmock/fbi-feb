package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/9      查询账户对账单账明细
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
public class M8853 extends MTia {

    private String BATSEQ = "111111" ;    //
    private String ORGIDT = "010" ;    //柜员机构号
    private String DEPNUM = "60" ;    //柜员部门号
    private String ORGID3 = "010" ;    //帐户机构号
    private String ACTNUM = "" ;    //帐户帐号
    private String BEGDAT = "" ;    //起始交易日期
    private String ENDDAT = "" ;    //终止交易日期
    private String BEGNUM = "1" ;    //起始笔数

    public String getBATSEQ() {
        return BATSEQ;
    }

    public void setBATSEQ(String BATSEQ) {
        this.BATSEQ = BATSEQ;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public void setDEPNUM(String DEPNUM) {
        this.DEPNUM = DEPNUM;
    }

    public String getORGID3() {
        return ORGID3;
    }

    public void setORGID3(String ORGID3) {
        this.ORGID3 = ORGID3;
    }

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getBEGDAT() {
        return BEGDAT;
    }

    public void setBEGDAT(String BEGDAT) {
        this.BEGDAT = BEGDAT;
    }

    public String getENDDAT() {
        return ENDDAT;
    }

    public void setENDDAT(String ENDDAT) {
        this.ENDDAT = ENDDAT;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
