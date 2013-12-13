package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * 通知存款支取和结清
 */

public class T220 extends SOFFormBody {

   {
    fieldTypes = new int[]{1,1,1,1,1,1,1,1,3,1,1};
    fieldLengths = new int[]{4,4,8,2,22,8,72, 3, 15, 16, 18} ;
   }

    private String TXNCDE	;         //交易代码
    private String TELLER	;         //柜员代码
    private String TXNDAT	;         //交易日期
    private String ACTTY 	;         //账户类别
    private String IPTAC 	;         //帐号
    private String ADVDAT	;         //通知日期
    private String ACTNAM	;         //户名
    private String INTCUR ;         //币别
    private BigDecimal TXNAMT ;     //通知金额
    private String ADVNUM ;         //通知单号
    private String REMARK ;         //备注


    public String getTXNCDE() {
        return TXNCDE;
    }

    public void setTXNCDE(String TXNCDE) {
        this.TXNCDE = TXNCDE;
    }

    public String getTELLER() {
        return TELLER;
    }

    public void setTELLER(String TELLER) {
        this.TELLER = TELLER;
    }

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
    }

    public String getACTTY() {
        return ACTTY;
    }

    public void setACTTY(String ACTTY) {
        this.ACTTY = ACTTY;
    }

    public String getIPTAC() {
        return IPTAC;
    }

    public void setIPTAC(String IPTAC) {
        this.IPTAC = IPTAC;
    }

    public String getADVDAT() {
        return ADVDAT;
    }

    public void setADVDAT(String ADVDAT) {
        this.ADVDAT = ADVDAT;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getINTCUR() {
        return INTCUR;
    }

    public void setINTCUR(String INTCUR) {
        this.INTCUR = INTCUR;
    }

    public BigDecimal getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(BigDecimal TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getADVNUM() {
        return ADVNUM;
    }

    public void setADVNUM(String ADVNUM) {
        this.ADVNUM = ADVNUM;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
}
