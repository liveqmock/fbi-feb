package gateway.sbs.txn.model.msg;

/**
 * Created by Lichao.W At 2015/4/14 8:53
 * wanglichao@163.com
 */
public class Maa06 extends MTia {
    private String TXNDAT = "";    //��������
    private String PAPSTS = "";    //״̬
    private String IPTAC1 = "";    //ת���ʻ�
    private String IPTAC2 = "";    //ת���ʻ�
    private String TXNAMT = "";    //���׽��
    private String ANACDE = "BI01";    //������
    private String CUSPW1 = "000001";    //��ʼ����

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
    }

    public String getPAPSTS() {
        return PAPSTS;
    }

    public void setPAPSTS(String PAPSTS) {
        this.PAPSTS = PAPSTS;
    }

    public String getIPTAC1() {
        return IPTAC1;
    }

    public void setIPTAC1(String IPTAC1) {
        this.IPTAC1 = IPTAC1;
    }

    public String getIPTAC2() {
        return IPTAC2;
    }

    public void setIPTAC2(String IPTAC2) {
        this.IPTAC2 = IPTAC2;
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

    public String getCUSPW1() {
        return CUSPW1;
    }

    public void setCUSPW1(String CUSPW1) {
        this.CUSPW1 = CUSPW1;
    }
}
