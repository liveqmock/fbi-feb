package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/7/24       �ܷ��˺Ŷ���ά��
 * Time: 9:32
 * To change this template use File | Settings | File Templates.
 */
public class Ma842 extends MTia{

    private String PASTYP = "";  //������� A-����,U-�޸�,D-ɾ��
    private String ACTTY1 = "";  //�������  24-�ܷ��˺Ŷ��գ�26-�ܷ��˺��µױ������
    private String IPTAC1 = "";  //�ֹ�˾�˺�
    private String ACTNM1 = "";  //�ܹ�˾�˺�
    private String ACTNM2 = "";  //�ϻ��²���ʽ
    private String REASON = "";  //�µױ������
    private String REMARK = "";  //��ע

    public String getPASTYP() {
        return PASTYP;
    }

    public void setPASTYP(String PASTYP) {
        this.PASTYP = PASTYP;
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

    public String getACTNM1() {
        return ACTNM1;
    }

    public void setACTNM1(String ACTNM1) {
        this.ACTNM1 = ACTNM1;
    }

    public String getACTNM2() {
        return ACTNM2;
    }

    public void setACTNM2(String ACTNM2) {
        this.ACTNM2 = ACTNM2;
    }

    public String getREASON() {
        return REASON;
    }

    public void setREASON(String REASON) {
        this.REASON = REASON;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
}
