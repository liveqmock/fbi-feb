package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-4-8
 * Time: ����8:56
 * To change this template use File | Settings | File Templates.
 */
public class Ma000 extends MTia {

    private String RVSKEY = "";     //������ˮ��
    private String RVSTCD = "";     //        ����������
    private String ACTTY1 = "";    //ȡ��/�����ʻ�������
    private String IPTAC1 = "";    //ȡ��/�����ʻ���
    private String ACTTY2 = "";    //����/�����ʻ�������
    private String IPTAC2 = "";    //����/�����ʻ���
    private String TXNAMT = "";    //        �������׽��
    private String REMARK = "";    //ժҪ
    private String MAGFL1 = "";    //        ����
    private String MAGFL2 = "";    //����


    public Ma000() {
    }

    public Ma000(String RVSKEY, String RVSTCD, String ACTTY1,
                 String IPTAC1, String ACTTY2, String IPTAC2,
                 String TXNAMT, String REMARK) {
        this.RVSKEY = RVSKEY;
        this.RVSTCD = RVSTCD;
        this.ACTTY1 = ACTTY1;
        this.IPTAC1 = IPTAC1;
        this.ACTTY2 = ACTTY2;
        this.IPTAC2 = IPTAC2;
        this.TXNAMT = TXNAMT;
        this.REMARK = REMARK;
    }

    public String getRVSKEY() {
        return RVSKEY;
    }

    public void setRVSKEY(String RVSKEY) {
        this.RVSKEY = RVSKEY;
    }

    public String getRVSTCD() {
        return RVSTCD;
    }

    public void setRVSTCD(String RVSTCD) {
        this.RVSTCD = RVSTCD;
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

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
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
}
