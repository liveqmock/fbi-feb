package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBean;

/**
 * 9804利率单笔查询、增删改-响应 T804
 */
public class T804 extends SOFFormBean {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 1, 2, 8, 24, 10, 4, 4, 1, 1, 3, 1, 4, 8};
    }

    private String CURCDE;          // 货币码
    private String IRTKD1;          // 利率种类码1
    private String IRTKD2;          // 利率种类码2
    private String EFFDAT;          // 启用日期
    private String IRTNAM;          // 利率名称
    private String IRTVAL;          // 基准利率值
    private String IRTSPH;          // 浮动上限值
    private String IRTSPL;          // 浮动下限值
    private String SPRFLG;          // 浮动标志
    private String CDFLAG;          // 借贷标志
    private String IRTTRM;          // 期限
    private String TRMUNT;          // 期限单位
    private String CRETLR;          //  建档柜员
    private String CREDAT;          // 建档日期

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getIRTKD1() {
        return IRTKD1;
    }

    public void setIRTKD1(String IRTKD1) {
        this.IRTKD1 = IRTKD1;
    }

    public String getIRTKD2() {
        return IRTKD2;
    }

    public void setIRTKD2(String IRTKD2) {
        this.IRTKD2 = IRTKD2;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public String getIRTNAM() {
        return IRTNAM;
    }

    public void setIRTNAM(String IRTNAM) {
        this.IRTNAM = IRTNAM;
    }

    public String getIRTVAL() {
        return IRTVAL;
    }

    public void setIRTVAL(String IRTVAL) {
        this.IRTVAL = IRTVAL;
    }

    public String getIRTSPH() {
        return IRTSPH;
    }

    public void setIRTSPH(String IRTSPH) {
        this.IRTSPH = IRTSPH;
    }

    public String getIRTSPL() {
        return IRTSPL;
    }

    public void setIRTSPL(String IRTSPL) {
        this.IRTSPL = IRTSPL;
    }

    public String getSPRFLG() {
        return SPRFLG;
    }

    public void setSPRFLG(String SPRFLG) {
        this.SPRFLG = SPRFLG;
    }

    public String getCDFLAG() {
        return CDFLAG;
    }

    public void setCDFLAG(String CDFLAG) {
        this.CDFLAG = CDFLAG;
    }

    public String getIRTTRM() {
        return IRTTRM;
    }

    public void setIRTTRM(String IRTTRM) {
        this.IRTTRM = IRTTRM;
    }

    public String getTRMUNT() {
        return TRMUNT;
    }

    public void setTRMUNT(String TRMUNT) {
        this.TRMUNT = TRMUNT;
    }

    public String getCRETLR() {
        return CRETLR;
    }

    public void setCRETLR(String CRETLR) {
        this.CRETLR = CRETLR;
    }

    public String getCREDAT() {
        return CREDAT;
    }

    public void setCREDAT(String CREDAT) {
        this.CREDAT = CREDAT;
    }
}
