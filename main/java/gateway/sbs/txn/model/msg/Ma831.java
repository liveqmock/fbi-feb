package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/12      ����������������Ӧ��ά��-���
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class Ma831 extends MTia {

    private String DPTTYP = "";     //�������
    private String CURCDE = "";     //���Ҵ���
    private String ACTTYP = "";     //�ʻ�����
    private String PRDTYP = "";     //��������
    private String DPTPRD = "";     //��Ӧ����
    private String INTCDE = "";     //��Ӧ������
    private String RECSTS = "";     //��¼״̬

    public String getDPTTYP() {
        return DPTTYP;
    }

    public void setDPTTYP(String DPTTYP) {
        this.DPTTYP = DPTTYP;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public String getPRDTYP() {
        return PRDTYP;
    }

    public void setPRDTYP(String PRDTYP) {
        this.PRDTYP = PRDTYP;
    }

    public String getDPTPRD() {
        return DPTPRD;
    }

    public void setDPTPRD(String DPTPRD) {
        this.DPTPRD = DPTPRD;
    }

    public String getINTCDE() {
        return INTCDE;
    }

    public void setINTCDE(String INTCDE) {
        this.INTCDE = INTCDE;
    }

    public String getRECSTS() {
        return RECSTS;
    }

    public void setRECSTS(String RECSTS) {
        this.RECSTS = RECSTS;
    }
}
