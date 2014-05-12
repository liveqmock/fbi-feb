package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-12
 * Time: 上午8:51
 * To change this template use File | Settings | File Templates.
 */
public class Ma276 extends MTia {

    private String BOKNUM = "";     // 账户号
    private String CUSIDT = "";     // 客户号
    private String ANACDE = "";     // 核算码
    private String CURCDE = "";     // 币别
    private String ACTNM1 = "";     // 户名
    private String DPTTYP = "";     // 存款种类
    private String DPTPRD = "";     // 存期
    private String TXNAMT = "";     // 存款金额最小
    private String ADVAMT = "";     // 存款金额最大
    private String VALDAT = "";     // 起息日期起日
    private String SGNDAT = "";     // 起息日期止日
    private String ADVDAT = "";     // 到期日期起日
    private String AUTSEQ = "";     // 到期日期止日
    private String PAPSTS = "1";     // 状态
    private String CUSPW1 = "";     // 起始序号

    //= = = = = = = = = = = = = =


    public String getBOKNUM() {
        return BOKNUM;
    }

    public void setBOKNUM(String BOKNUM) {
        this.BOKNUM = BOKNUM;
    }

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getANACDE() {
        return ANACDE;
    }

    public void setANACDE(String ANACDE) {
        this.ANACDE = ANACDE;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getACTNM1() {
        return ACTNM1;
    }

    public void setACTNM1(String ACTNM1) {
        this.ACTNM1 = ACTNM1;
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

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getADVAMT() {
        return ADVAMT;
    }

    public void setADVAMT(String ADVAMT) {
        this.ADVAMT = ADVAMT;
    }

    public String getVALDAT() {
        return VALDAT;
    }

    public void setVALDAT(String VALDAT) {
        this.VALDAT = VALDAT;
    }

    public String getSGNDAT() {
        return SGNDAT;
    }

    public void setSGNDAT(String SGNDAT) {
        this.SGNDAT = SGNDAT;
    }

    public String getADVDAT() {
        return ADVDAT;
    }

    public void setADVDAT(String ADVDAT) {
        this.ADVDAT = ADVDAT;
    }

    public String getAUTSEQ() {
        return AUTSEQ;
    }

    public void setAUTSEQ(String AUTSEQ) {
        this.AUTSEQ = AUTSEQ;
    }

    public String getPAPSTS() {
        return PAPSTS;
    }

    public void setPAPSTS(String PAPSTS) {
        this.PAPSTS = PAPSTS;
    }

    public String getCUSPW1() {
        return CUSPW1;
    }

    public void setCUSPW1(String CUSPW1) {
        this.CUSPW1 = CUSPW1;
    }
}
