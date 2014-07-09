package gateway.sbs.txn.model.msg;

/**
 * 定期转账开户
 */
public class Ma271 extends MTia {

    private String SYSIDT = "8";       // 8-单位定期存款
    private String CUSIDT = "";        // 客户号
    private String DRAMD2 = "0";       // 取款方式
    private String CUSPW2 = "";        // 客户密码
    private String ACTNM2 = "";        // 户名
    private String PASTYP = "";        // 证件种类
    private String PASSNO = "";        // 证件号
    private String CORADD = "";       // 地址
    private String TELNUM = "";        // 电话
    private String TXNAMT = "";        // 交易金额
    private String VALDAT = "";        // 起息日期
    private String ACTTYP = "00";        // 账户类型
    private String DPTTYP = "20";        // 存款种类

    private String DPTPRD = "";        // 存期
    private String DRICYC = "00";        // 取息周期
    private String ATOFLG = "1";        // 自动转存标志
    private String VCHTYP = "";        // 凭证种类
    private String VCHNUM = "";        // 凭证号
    private String ACTTY1 = "01";        // 账号类型
    private String IPTAC1 = "";        // 账号
    private String DRAMD1 = "0";        // 取款方式
    private String CUSPW1 = "";        // 客户密码
    private String PAPTYP = "";        // 支票种类
    private String PAPCDE = "";        // 支票号
    private String PAPMAC = "";        // 支票密码
    private String SGNDAT = "";        // 签发日
    private String REMARK = "";        // 摘要
    private String ACTTY2 = "01";        // 转息账户号类型
    private String IPTAC2 = "";        // 转息账户
    private String VCHUSERATE = "";    // 协议利率
    private String MAGFL1 = "0";        // 账号输入方式

    public String getSYSIDT() {
        return SYSIDT;
    }

    public void setSYSIDT(String SYSIDT) {
        this.SYSIDT = SYSIDT;
    }

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getDRAMD2() {
        return DRAMD2;
    }

    public void setDRAMD2(String DRAMD2) {
        this.DRAMD2 = DRAMD2;
    }

    public String getCUSPW2() {
        return CUSPW2;
    }

    public void setCUSPW2(String CUSPW2) {
        this.CUSPW2 = CUSPW2;
    }

    public String getACTNM2() {
        return ACTNM2;
    }

    public void setACTNM2(String ACTNM2) {
        this.ACTNM2 = ACTNM2;
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

    public String getTELNUM() {
        return TELNUM;
    }

    public void setTELNUM(String TELNUM) {
        this.TELNUM = TELNUM;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getVALDAT() {
        return VALDAT;
    }

    public void setVALDAT(String VALDAT) {
        this.VALDAT = VALDAT;
    }

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
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

    public String getDRICYC() {
        return DRICYC;
    }

    public void setDRICYC(String DRICYC) {
        this.DRICYC = DRICYC;
    }

    public String getATOFLG() {
        return ATOFLG;
    }

    public void setATOFLG(String ATOFLG) {
        this.ATOFLG = ATOFLG;
    }

    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }

    public String getVCHNUM() {
        return VCHNUM;
    }

    public void setVCHNUM(String VCHNUM) {
        this.VCHNUM = VCHNUM;
    }

    public String getACTTY1() {
        return ACTTY1;
    }

    public void setACTTY1(String ACTTY1) {
        this.ACTTY1 = ACTTY1;
    }

    public String getIPTAC1() {
        return IPTAC1;
    }

    public void setIPTAC1(String IPTAC1) {
        this.IPTAC1 = IPTAC1;
    }

    public String getDRAMD1() {
        return DRAMD1;
    }

    public void setDRAMD1(String DRAMD1) {
        this.DRAMD1 = DRAMD1;
    }

    public String getCUSPW1() {
        return CUSPW1;
    }

    public void setCUSPW1(String CUSPW1) {
        this.CUSPW1 = CUSPW1;
    }

    public String getPAPTYP() {
        return PAPTYP;
    }

    public void setPAPTYP(String PAPTYP) {
        this.PAPTYP = PAPTYP;
    }

    public String getPAPCDE() {
        return PAPCDE;
    }

    public void setPAPCDE(String PAPCDE) {
        this.PAPCDE = PAPCDE;
    }

    public String getPAPMAC() {
        return PAPMAC;
    }

    public void setPAPMAC(String PAPMAC) {
        this.PAPMAC = PAPMAC;
    }

    public String getSGNDAT() {
        return SGNDAT;
    }

    public void setSGNDAT(String SGNDAT) {
        this.SGNDAT = SGNDAT;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getACTTY2() {
        return ACTTY2;
    }

    public void setACTTY2(String ACTTY2) {
        this.ACTTY2 = ACTTY2;
    }

    public String getIPTAC2() {
        return IPTAC2;
    }

    public void setIPTAC2(String IPTAC2) {
        this.IPTAC2 = IPTAC2;
    }

    public String getVCHUSERATE() {
        return VCHUSERATE;
    }

    public void setVCHUSERATE(String VCHUSERATE) {
        this.VCHUSERATE = VCHUSERATE;
    }

    public String getMAGFL1() {
        return MAGFL1;
    }

    public void setMAGFL1(String MAGFL1) {
        this.MAGFL1 = MAGFL1;
    }
}
