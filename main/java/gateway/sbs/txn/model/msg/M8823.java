package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-20
 * Time: 下午2:30
 * To change this template use File | Settings | File Templates.
 */
public class M8823 extends MTia{
    private String CUSIDT = "";    //客户号    CTF-CUSIDT
    private String APCODE = "";    //核算码    CTF-APCODE
    private String CURCDE = "";    //币别    CTF-CURCDE
    private String ERYDA1 = "";    //起始日期    CTF-ERYDA1
    private String ERYDA2 = "";    //终止日期    CTF-ERYDA2
    private String SECAMT = "";    //最小金额    CTF-SECAMT
    private String OVELIM = "";    //最大金额    CTF-OVELIM
    private String TLRNUM = "";    //柜员号    CTF-TLRNUM
    private String VCHSET = "";    //传票套号    CTF-VCHSET
    private String FUNCDE = "";    //借贷别    CTF-FUNCDE
    private String REGADD = "";    //排序字段    CTF-REGADD
    private String BEGNUM = "";    //起始笔数    CTF-BEGNUM

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

    public String getERYDA1() {
        return ERYDA1;
    }

    public void setERYDA1(String ERYDA1) {
        this.ERYDA1 = ERYDA1;
    }

    public String getERYDA2() {
        return ERYDA2;
    }

    public void setERYDA2(String ERYDA2) {
        this.ERYDA2 = ERYDA2;
    }

    public String getSECAMT() {
        return SECAMT;
    }

    public void setSECAMT(String SECAMT) {
        this.SECAMT = SECAMT;
    }

    public String getOVELIM() {
        return OVELIM;
    }

    public void setOVELIM(String OVELIM) {
        this.OVELIM = OVELIM;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getREGADD() {
        return REGADD;
    }

    public void setREGADD(String REGADD) {
        this.REGADD = REGADD;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
