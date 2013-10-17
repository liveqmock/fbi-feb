package gateway.sbs.txn.model.msg;

/**
 * 重要凭证库存维护详细查询
 */
public class Mh830 extends MTia {



    private String VCHTYP;            //凭证种类
    private String ALLONE;            //是否单种
    private String ORGIDT = "010";     //机构号
    private String DEPNUM = "60";     //部门号
    private String TXNTLR;            //柜员号


    public Mh830(){

    }
    public Mh830(String VCHTYP,String ALLONE,String ORGIDT,String DEPNUM,String TXNTLR){
        this.VCHTYP = VCHTYP;
        this.ALLONE = ALLONE;
        this.ORGIDT = ORGIDT;
        this.DEPNUM = DEPNUM;
        this.TXNTLR = TXNTLR;
    }

    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }
    public String getALLONE() {
        return ALLONE;
    }

    public void setALLONE(String ALLONE) {
        this.ALLONE = ALLONE;
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

    public String getTXNTLR() {
        return TXNTLR;
    }

    public void setTXNTLR(String TXNTLR) {
        this.TXNTLR = TXNTLR;
    }


}
