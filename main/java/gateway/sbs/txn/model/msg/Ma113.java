package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-12
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
public class Ma113 extends MTia{

   private String BOKNUM = "";    //账户号
   private String PAPSTS = "";    //通知状态
   private String VALDAT = "";    //通知日期
   private String SGNDAT = "";    //协定取款日
   private String CUSPW1 = "";    //起始序号

    public String getBOKNUM() {
        return BOKNUM;
    }

    public void setBOKNUM(String BOKNUM) {
        this.BOKNUM = BOKNUM;
    }

    public String getPAPSTS() {
        return PAPSTS;
    }

    public void setPAPSTS(String PAPSTS) {
        this.PAPSTS = PAPSTS;
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

    public String getCUSPW1() {
        return CUSPW1;
    }

    public void setCUSPW1(String CUSPW1) {
        this.CUSPW1 = CUSPW1;
    }
}
