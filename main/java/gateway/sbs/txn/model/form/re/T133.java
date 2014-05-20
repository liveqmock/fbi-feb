package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * 7082定期结清
 */

public class T133 extends SOFFormBody {

   {
    fieldTypes = new int[]{1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 3};
    fieldLengths = new int[]{20, 1, 18, 2, 15, 8, 2, 13, 20, 20, 15, 15} ;
   }

    private String ACTNM1;          // 账户号
    private String PASTYP;          // 证件种类
    private String PASNO1;          // 证件号码
    private String ACTTYP;          // 账户类别
    private BigDecimal CURBL1;          // 余额
    private String VALDAT;          // 起息日期
    private String DPTPRD;          // 存期
    private String TOTINT;          // 实付金额
    private String ACTNM2;          // 账号
    private String CURNAM;          // 币别名
    private BigDecimal PREBAL;          // 交易前余额
    private BigDecimal PSTBAL;          // 交易后余额

    public String getACTNM1() {
        return ACTNM1;
    }

    public void setACTNM1(String ACTNM1) {
        this.ACTNM1 = ACTNM1;
    }

    public String getPASTYP() {
        return PASTYP;
    }

    public void setPASTYP(String PASTYP) {
        this.PASTYP = PASTYP;
    }

    public String getPASNO1() {
        return PASNO1;
    }

    public void setPASNO1(String PASNO1) {
        this.PASNO1 = PASNO1;
    }

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public BigDecimal getCURBL1() {
        return CURBL1;
    }

    public void setCURBL1(BigDecimal CURBL1) {
        this.CURBL1 = CURBL1;
    }

    public String getVALDAT() {
        return VALDAT;
    }

    public void setVALDAT(String VALDAT) {
        this.VALDAT = VALDAT;
    }

    public String getDPTPRD() {
        return DPTPRD;
    }

    public void setDPTPRD(String DPTPRD) {
        this.DPTPRD = DPTPRD;
    }

    public String getTOTINT() {
        return TOTINT;
    }

    public void setTOTINT(String TOTINT) {
        this.TOTINT = TOTINT;
    }

    public String getACTNM2() {
        return ACTNM2;
    }

    public void setACTNM2(String ACTNM2) {
        this.ACTNM2 = ACTNM2;
    }

    public String getCURNAM() {
        return CURNAM;
    }

    public void setCURNAM(String CURNAM) {
        this.CURNAM = CURNAM;
    }

    public BigDecimal getPREBAL() {
        return PREBAL;
    }

    public void setPREBAL(BigDecimal PREBAL) {
        this.PREBAL = PREBAL;
    }

    public BigDecimal getPSTBAL() {
        return PSTBAL;
    }

    public void setPSTBAL(BigDecimal PSTBAL) {
        this.PSTBAL = PSTBAL;
    }


    //    }
}
