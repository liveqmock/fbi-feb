package gateway.sbs.txn.model.msg;

/**
 * 签到
 */
public class Ma130 extends MTia {
    private String ACTTY1;         //帐户号类型
    private String IPTAC1;         //帐户号
    private String DRAMD1;         //取款方式
    private String CUSPW1= "";         //客户密码
    private String TXNDAT="";         //交易日期
    private String ADVNUM="";         //通知单号
    private String TXNAMT;         //交易金额
    private String ACTTY2;         //转入账户类型
    private String IPTAC2;         //转入账号
    private String PASTYP= "";         //证件种类
    private String PASSNO= "";         //证件号
    private String REMARK= "";         //摘要
    private String ANACDE= "";         //分类统计码
    private String MAGFL1= "";         //刷磁条读入帐号标志
    private String MAGFL2= "";         //备用字段






    public Ma130(String ACTTY1, String IPTAC1, String DRAMD1, String TXNDAT, String ADVNUM, String TXNAMT, String ACTTY2, String IPTAC2) {
        this.ACTTY1 = ACTTY1;
        this.IPTAC1 = IPTAC1;
        this.DRAMD1 = DRAMD1;
        this.TXNDAT = TXNDAT;
        this.ADVNUM = ADVNUM;
        this.TXNAMT = TXNAMT;
        this.ACTTY2 = ACTTY2;
        this.IPTAC2 = IPTAC2;
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

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
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
    public String getMAGFL1() {
        return MAGFL1;
    }

    public void setMAGFL1(String MAGFL1) {
        this.MAGFL1 = MAGFL1;
    }

    public String getMAGFL2() {
        return MAGFL2;
    }

    public void setMAGFL2(String MAGFL2) {
        this.MAGFL2 = MAGFL2;
    }

    public String getADVNUM() {
        return ADVNUM;
    }

    public void setADVNUM(String ADVNUM) {
        this.ADVNUM = ADVNUM;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getANACDE() {
        return ANACDE;
    }

    public void setANACDE(String ANACDE) {
        this.ANACDE = ANACDE;
    }
}
