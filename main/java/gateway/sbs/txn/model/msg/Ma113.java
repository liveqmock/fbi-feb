package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-12
 * Time: ����11:22
 * To change this template use File | Settings | File Templates.
 */
public class Ma113 extends MTia{

   private String BOKNUM = "";    //�˻���
   private String PAPSTS = "";    //֪ͨ״̬
   private String VALDAT = "";    //֪ͨ����
   private String SGNDAT = "";    //Э��ȡ����
   private String CUSPW1 = "";    //��ʼ���

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
