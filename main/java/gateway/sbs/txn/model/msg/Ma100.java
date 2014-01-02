package gateway.sbs.txn.model.msg;

/**
 * 签到
 */
public class Ma100 extends MTia {
    private String ACTTY1;        //取出/结清帐户号类型 #
    private String IPTAC1;        //取出/结清帐户号   #
    private String DUEFLG;        //强制到期标志 #
    private String DRAMD1;        //取款方式   #
    private String CUSPW1 = "";        //取款密码
    private String DPTPRD;      //存期月数   #
    private String VALDAT;      //起息日    #
    private String TXNAMT;      //交易金额   #
    private String VCHTYP;      //转入/存入凭证种类  #
    private String VCHNUM;      //转入/存入凭证号码   #
    private String PASTYP = "";      //证件类别
    private String PASSNO = "";      //证件号码
    private String ACTTY2 = "";        //转入/存入帐号类型
    private String IPTAC2 = "";        //转入/存入帐号号
    private String REMARK = "";        //摘要
    private String ANACDE;        //分析号      #
    private String VCHTY1 = "";        //取出/结清凭证代码
    private String VCHNU1 = "";        //取出/结清凭证号码
    private String MAGFL1 = "";        //刷磁条读入帐号标志




    public Ma100(String ACTTY1, String IPTAC1, String DUEFLG, String DRAMD1, String DPTPRD, String VALDAT, String TXNAMT,
                 String VCHTYP, String VCHNUM, String ANACDE) {
        this.ACTTY1 = ACTTY1;
        this.IPTAC1 = IPTAC1;
        this.DUEFLG = DUEFLG;
        this.DRAMD1 = DRAMD1;
        this.DPTPRD = DPTPRD;
        this.VALDAT = VALDAT;
        this.TXNAMT = TXNAMT;
        this.VCHTYP = VCHTYP;
        this.VCHNUM = VCHNUM;
        this.ANACDE = ANACDE;

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

    public String getDUEFLG() {
        return DUEFLG;
    }

    public void setDUEFLG(String DUEFLG) {
        this.DUEFLG = DUEFLG;
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

    public String getDPTPRD() {
        return DPTPRD;
    }

    public void setDPTPRD(String DPTPRD) {
        this.DPTPRD = DPTPRD;
    }

    public String getVALDAT() {
        return VALDAT;
    }

    public void setVALDAT(String VALDAT) {
        this.VALDAT = VALDAT;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
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

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getANACDE() {
        return ANACDE;
    }

    public void setANACDE(String ANACDE) {
        this.ANACDE = ANACDE;
    }

    public String getVCHTY1() {
        return VCHTY1;
    }

    public void setVCHTY1(String VCHTY1) {
        this.VCHTY1 = VCHTY1;
    }

    public String getVCHNU1() {
        return VCHNU1;
    }

    public void setVCHNU1(String VCHNU1) {
        this.VCHNU1 = VCHNU1;
    }

    public String getMAGFL1() {
        return MAGFL1;
    }

    public void setMAGFL1(String MAGFL1) {
        this.MAGFL1 = MAGFL1;
    }
}
