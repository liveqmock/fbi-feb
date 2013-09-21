package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 T404-查询（修改、删除、增加时，返回信息）
 */
public class T404 extends SOFFormBody {
    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};   //10
        fieldLengths = new int[]{4, 4, 4, 6, 2, 5, 8, 3, 4, 80};
    }

    private String  VCHSET; //传票号
    private String  TRNCDE; //操作类别
    private String  TLRNUM; //柜员号
    private String  TRNTIM; //交易时间
    private String  TM_CODE;//终端类别
    private String  TXNCODE; //交易流水号
    private String  TRNDAT; //交易日期
    private String  ORGIDT;  //机构号
    private String  TMCODE;  //交易码
    private String  VCHPAR;  //核算码信息

    private String APCODE;         //核算码                         Y
    private String APCNAM;         //核算码名称                     Y
    private String APCTYP;         //核算码类型 0-普通类 4-999临时存欠 5-外汇买卖人民币 6-933外币  7-自动对转类 8-临时类 9-催收类
    private String GLCODE;         //所属总账码

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

    public String getTM_CODE() {
        return TM_CODE;
    }

    public void setTM_CODE(String TM_CODE) {
        this.TM_CODE = TM_CODE;
    }

    public String getTXNCODE() {
        return TXNCODE;
    }

    public void setTXNCODE(String TXNCODE) {
        this.TXNCODE = TXNCODE;
    }

    public String getTRNDAT() {
        return TRNDAT;
    }

    public void setTRNDAT(String TRNDAT) {
        this.TRNDAT = TRNDAT;
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

    public String getVCHPAR() {
        return VCHPAR;
    }

    public void setVCHPAR(String VCHPAR) {
        this.VCHPAR = VCHPAR;
    }

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getAPCNAM() {
        return APCNAM;
    }

    public void setAPCNAM(String APCNAM) {
        this.APCNAM = APCNAM;
    }

    public String getAPCTYP() {
        return APCTYP;
    }

    public void setAPCTYP(String APCTYP) {
        this.APCTYP = APCTYP;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }
}
