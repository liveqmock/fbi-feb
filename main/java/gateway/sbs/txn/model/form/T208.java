package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * h803单笔查询、增删改-响应 T007
 */

public class T208 extends SOFFormBody {
   {
    fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    fieldLengths = new int[]{4, 4, 4, 6 ,7 ,8 ,60 , 3, 4, 8 ,4 ,18 ,23 ,2 ,30 ,13 ,13 ,13 };
   }
    private String VCHSET;
    private String TRNCDE;
    private String TLRNUM;
    private String TRNTIM;
    private String WATNUM;
    private String TRNDAT;
    private String VCHPAR;
    private String ORGIDT;
    private String TMCODE;
    private String TLNAME;
    private String VCHCHR;
    private String VCHITM;
    private String VCHAMT;
    private String VCHTYP;
    private String VCHNAM;
    private String VCHBNM;
    private String VCHENM;
    private String VCHNUM;

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getTRNCDE() {
        return TRNCDE;
    }

    public void setTRNCDE(String TRNCDE) {
        this.TRNCDE = TRNCDE;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getTRNTIM() {
        return TRNTIM;
    }

    public void setTRNTIM(String TRNTIM) {
        this.TRNTIM = TRNTIM;
    }

    public String getWATNUM() {
        return WATNUM;
    }

    public void setWATNUM(String WATNUM) {
        this.WATNUM = WATNUM;
    }

    public String getTRNDAT() {
        return TRNDAT;
    }

    public void setTRNDAT(String TRNDAT) {
        this.TRNDAT = TRNDAT;
    }

    public String getVCHPAR() {
        return VCHPAR;
    }

    public void setVCHPAR(String VCHPAR) {
        this.VCHPAR = VCHPAR;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public String getTMCODE() {
        return TMCODE;
    }

    public void setTMCODE(String TMCODE) {
        this.TMCODE = TMCODE;
    }

    public String getTLNAME() {
        return TLNAME;
    }

    public void setTLNAME(String TLNAME) {
        this.TLNAME = TLNAME;
    }

    public String getVCHCHR() {
        return VCHCHR;
    }

    public void setVCHCHR(String VCHCHR) {
        this.VCHCHR = VCHCHR;
    }

    public String getVCHITM() {
        return VCHITM;
    }

    public void setVCHITM(String VCHITM) {
        this.VCHITM = VCHITM;
    }

    public String getVCHAMT() {
        return VCHAMT;
    }

    public void setVCHAMT(String VCHAMT) {
        this.VCHAMT = VCHAMT;
    }

    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }

    public String getVCHNAM() {
        return VCHNAM;
    }

    public void setVCHNAM(String VCHNAM) {
        this.VCHNAM = VCHNAM;
    }

    public String getVCHBNM() {
        return VCHBNM;
    }

    public void setVCHBNM(String VCHBNM) {
        this.VCHBNM = VCHBNM;
    }

    public String getVCHENM() {
        return VCHENM;
    }

    public void setVCHENM(String VCHENM) {
        this.VCHENM = VCHENM;
    }

    public String getVCHNUM() {
        return VCHNUM;
    }

    public void setVCHNUM(String VCHNUM) {
        this.VCHNUM = VCHNUM;
    }

//    }
}
