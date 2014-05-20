package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * 7082定期结清
 */

public class T130 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{4, 4, 8, 2, 22, 8, 72, 15, 9, 15, 9, 15, 9, 15, 3, 15, 4, 9, 15, 15, 15, 15, 15, 15, 15, 15};
    }

    private String TXNCDE;          // 交易码
    private String TELLER;          // 柜员码
    private String TXNDAT;          // 交易日期
    private String ACTTY;          // 账户类别
    private String IPTAC;          // 账号
    private String VALDAT;          // 起息日期
    private String ACTNAM;          // 户名
    private String TXNAMT;          // 本金金额
    private String OPNIRT;          // 期内利率
    private String ININT;          // 期内利息
    private String SAVIRT;          // 活期利率
    private String OUTINT;          // 活期利息
    private String VALIRT;          // 保值利率
    private String VALINT;          // 保值利息
    private String INTCUR;          // 币别码
    private String TOTINT;          // 实付金额
    private String ACTTYP;          // 账户类别
    private String TAXRATE;          // 税率
    private String TAXAMT;          //利息税
    private String FEEAMT;          // 手续费
    private String U_INTAX;          // 本金未征税利息
    private String U_OUTTAX;          // 活期未征税利息
    private String U_VALTAX;          // 保值未征税利息
    private String P_INTAX;          // 本金征税利息
    private String P_OUTTAX;          // 活期征税利息
    private String P_VALTAX;          // 保值征税利息

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

    public String getVALDAT() {
        return VALDAT;
    }

    public void setVALDAT(String VALDAT) {
        this.VALDAT = VALDAT;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getOPNIRT() {
        return OPNIRT;
    }

    public void setOPNIRT(String OPNIRT) {
        this.OPNIRT = OPNIRT;
    }

    public String getININT() {
        return ININT;
    }

    public void setININT(String ININT) {
        this.ININT = ININT;
    }

    public String getSAVIRT() {
        return SAVIRT;
    }

    public void setSAVIRT(String SAVIRT) {
        this.SAVIRT = SAVIRT;
    }

    public String getOUTINT() {
        return OUTINT;
    }

    public void setOUTINT(String OUTINT) {
        this.OUTINT = OUTINT;
    }

    public String getVALIRT() {
        return VALIRT;
    }

    public void setVALIRT(String VALIRT) {
        this.VALIRT = VALIRT;
    }

    public String getVALINT() {
        return VALINT;
    }

    public void setVALINT(String VALINT) {
        this.VALINT = VALINT;
    }

    public String getINTCUR() {
        return INTCUR;
    }

    public void setINTCUR(String INTCUR) {
        this.INTCUR = INTCUR;
    }

    public String getTOTINT() {
        return TOTINT;
    }

    public void setTOTINT(String TOTINT) {
        this.TOTINT = TOTINT;
    }

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public String getTAXRATE() {
        return TAXRATE;
    }

    public void setTAXRATE(String TAXRATE) {
        this.TAXRATE = TAXRATE;
    }

    public String getTAXAMT() {
        return TAXAMT;
    }

    public void setTAXAMT(String TAXAMT) {
        this.TAXAMT = TAXAMT;
    }

    public String getFEEAMT() {
        return FEEAMT;
    }

    public void setFEEAMT(String FEEAMT) {
        this.FEEAMT = FEEAMT;
    }

    public String getU_INTAX() {
        return U_INTAX;
    }

    public void setU_INTAX(String u_INTAX) {
        U_INTAX = u_INTAX;
    }

    public String getU_OUTTAX() {
        return U_OUTTAX;
    }

    public void setU_OUTTAX(String u_OUTTAX) {
        U_OUTTAX = u_OUTTAX;
    }

    public String getU_VALTAX() {
        return U_VALTAX;
    }

    public void setU_VALTAX(String u_VALTAX) {
        U_VALTAX = u_VALTAX;
    }

    public String getP_INTAX() {
        return P_INTAX;
    }

    public void setP_INTAX(String p_INTAX) {
        P_INTAX = p_INTAX;
    }

    public String getP_OUTTAX() {
        return P_OUTTAX;
    }

    public void setP_OUTTAX(String p_OUTTAX) {
        P_OUTTAX = p_OUTTAX;
    }

    public String getP_VALTAX() {
        return P_VALTAX;
    }

    public void setP_VALTAX(String p_VALTAX) {
        P_VALTAX = p_VALTAX;
    }
}
