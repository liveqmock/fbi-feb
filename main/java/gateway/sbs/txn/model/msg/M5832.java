package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-14
 * Time: ����2:22
 * To change this template use File | Settings | File Templates.
 */
public class M5832 extends MTia {

    private String BATSEQ = "111111";      //���
    private String ORGIDT = "010";      //��Ա������
    private String DEPNUM = "60";      //��Ա���ź�
    private String CURCDE = "";      //�ұ���
    private String XRTCDE = "";      //���ʱ�־
    private String SECCCY = "";      //�ڶ�����
    private String EFFDAT = "";      //��������
    private String EFFTIM = "";      //����ʱ��
    private String EXPDAT = "000000";      //��ֹ����
    private String FUNCDE = "6";      //���ܱ�

    public String getBATSEQ() {
        return BATSEQ;
    }

    public void setBATSEQ(String BATSEQ) {
        this.BATSEQ = BATSEQ;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public void setDEPNUM(String DEPNUM) {
        this.DEPNUM = DEPNUM;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getXRTCDE() {
        return XRTCDE;
    }

    public void setXRTCDE(String XRTCDE) {
        this.XRTCDE = XRTCDE;
    }

    public String getSECCCY() {
        return SECCCY;
    }

    public void setSECCCY(String SECCCY) {
        this.SECCCY = SECCCY;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public String getEFFTIM() {
        return EFFTIM;
    }

    public void setEFFTIM(String EFFTIM) {
        this.EFFTIM = EFFTIM;
    }

    public String getEXPDAT() {
        return EXPDAT;
    }

    public void setEXPDAT(String EXPDAT) {
        this.EXPDAT = EXPDAT;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }
}
