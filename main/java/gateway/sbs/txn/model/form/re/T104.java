package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * 存款证实书 [直接打印]
 */
public class T104 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{7, 16, 8, 34, 72, 18, 8,
                8, 3, 15, 2, 2, 9};
    }

    private String CUSIDT;          // 客户号
    private String OURREF;          // 业务引用号
    private String TXNDAT;          // 交易日期
    private String ORGNAM;          // 机构名称
    private String ACTNAM;          // 账户名称
    private String BOKNUM;          // 证实书编号
    private String VALDAT;          // 起息日期

    private String EXPDAT;          // 到期日期
    private String INTCUR;          // 币别码
    private String TXNAMT;          // 金额
    private String DPTTYP;          // 存款种类
    private String DPTPRD;          // 存期月数
    private String INTRAT;          // 年利率

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getOURREF() {
        return OURREF;
    }

    public void setOURREF(String OURREF) {
        this.OURREF = OURREF;
    }

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
    }

    public String getORGNAM() {
        return ORGNAM;
    }

    public void setORGNAM(String ORGNAM) {
        this.ORGNAM = ORGNAM;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getBOKNUM() {
        return BOKNUM;
    }

    public void setBOKNUM(String BOKNUM) {
        this.BOKNUM = BOKNUM;
    }

    public String getVALDAT() {
        return VALDAT;
    }

    public void setVALDAT(String VALDAT) {
        this.VALDAT = VALDAT;
    }

    public String getEXPDAT() {
        return EXPDAT;
    }

    public void setEXPDAT(String EXPDAT) {
        this.EXPDAT = EXPDAT;
    }

    public String getINTCUR() {
        return INTCUR;
    }

    public void setINTCUR(String INTCUR) {
        this.INTCUR = INTCUR;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getDPTTYP() {
        return DPTTYP;
    }

    public void setDPTTYP(String DPTTYP) {
        this.DPTTYP = DPTTYP;
    }

    public String getDPTPRD() {
        return DPTPRD;
    }

    public void setDPTPRD(String DPTPRD) {
        this.DPTPRD = DPTPRD;
    }

    public String getINTRAT() {
        return INTRAT;
    }

    public void setINTRAT(String INTRAT) {
        this.INTRAT = INTRAT;
    }
}
