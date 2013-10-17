package gateway.sbs.txn.model.msg;

/**
 * 总账码单笔查询、增删改
 */
public class Mh820 extends MTia {



    private String VCHTYP;
    private String IOFLAG;
    private String BEGNUM;
    private String ENDNUM;
    private String VCHCNT;



    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }

    public String getIOFLAG() {
        return IOFLAG;
    }

    public void setIOFLAG(String IOFLAG) {
        this.IOFLAG = IOFLAG;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }

    public String getENDNUM() {
        return ENDNUM;
    }

    public void setENDNUM(String ENDNUM) {
        this.ENDNUM = ENDNUM;
    }

    public String getVCHCNT() {
        return VCHCNT;
    }

    public void setVCHCNT(String VCHCNT) {
        this.VCHCNT = VCHCNT;
    }
}
