package gateway.sbs.txn.model.msg;

/**
 * 重要凭证代号维护添加
 */
public class Mh801 extends MTia {




    private String VCHTYP = ""	;          // 凭证种类
    private String OUTITM	= "";          // 表外核算码
    private String VCHNAM	= "";          // 凭证名称
    private String SCTMAK	= "";          // 是否有价证券
    private String VCHAMT	= "";          // 凭证金额


    public Mh801(String vchtyp){
        this.VCHTYP = vchtyp;
    }

    public Mh801() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }

    public String getOUTITM() {
        return OUTITM;
    }

    public void setOUTITM(String OUTITM) {
        this.OUTITM = OUTITM;
    }

    public String getVCHNAM() {
        return VCHNAM;
    }

    public void setVCHNAM(String VCHNAM) {
        this.VCHNAM = VCHNAM;
    }

    public String getSCTMAK() {
        return SCTMAK;
    }

    public void setSCTMAK(String SCTMAK) {
        this.SCTMAK = SCTMAK;
    }

    public String getVCHAMT() {
        return VCHAMT;
    }

    public void setVCHAMT(String VCHAMT) {
        this.VCHAMT = VCHAMT;
    }
}
