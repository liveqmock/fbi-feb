package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * 通知存款支取和结清
 */

public class T091 extends SOFFormBody {

   {
    fieldTypes = new int[]{1,1,1,1,1,3,1};
    fieldLengths = new int[]{20,1,18,30,8,15,20} ;
   }

    private String ACTNAM 	;         //账户名
    private String PASTYP 	;         //证件类别
    private String PASSNO 	;         //证件号码
    private String CORADD 	;         //地址
    private String OPNDAT 	;         //开户日
    private BigDecimal CURBAL 	;         //余额
    private String CURNAM 	;         //货币名称


    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getPASTYP() {
        return PASTYP;
    }

    public void setPASTYP(String PASTYP) {
        this.PASTYP = PASTYP;
    }

    public String getPASSNO() {
        return PASSNO;
    }

    public void setPASSNO(String PASSNO) {
        this.PASSNO = PASSNO;
    }

    public String getCORADD() {
        return CORADD;
    }

    public void setCORADD(String CORADD) {
        this.CORADD = CORADD;
    }

    public String getOPNDAT() {
        return OPNDAT;
    }

    public void setOPNDAT(String OPNDAT) {
        this.OPNDAT = OPNDAT;
    }

    public BigDecimal getCURBAL() {
        return CURBAL;
    }

    public void setCURBAL(BigDecimal CURBAL) {
        this.CURBAL = CURBAL;
    }

    public String getCURNAM() {
        return CURNAM;
    }

    public void setCURNAM(String CURNAM) {
        this.CURNAM = CURNAM;
    }
}
