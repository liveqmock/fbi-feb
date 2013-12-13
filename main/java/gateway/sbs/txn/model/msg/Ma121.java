package gateway.sbs.txn.model.msg;

/**
 * 签到
 */
public class Ma121 extends MTia {
    private String ACTTY2;      //账户类别 *
    private String IPTAC2;      //账户号    *
    private String DRAMD2;      //取款方式  *
    private String CUSPW2 = "";      //客户密码
    private String ADVNUM = "";      //通知单号
    private String TXNDAT = "";      //交易日期
    private String PASTYP = "";      //证件种类
    private String PASSNO = "";      //证件号
    private String REMARK = "";      //地址
    private String MAGFL2 = "";      //账号输入方式







    public Ma121(String ACTTY2, String IPTAC2, String DRAMD2) {
        this.ACTTY2 = ACTTY2;
        this.IPTAC2 = IPTAC2;
        this.DRAMD2 = DRAMD2;

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

    public String getADVNUM() {
        return ADVNUM;
    }

    public void setADVNUM(String ADVNUM) {
        this.ADVNUM = ADVNUM;
    }

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
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

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getMAGFL2() {
        return MAGFL2;
    }

    public void setMAGFL2(String MAGFL2) {
        this.MAGFL2 = MAGFL2;
    }
}
