package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-20             ���մ�Ʊ��ѯ������
 * Time: ����2:21
 * To change this template use File | Settings | File Templates.
 */
public class M8822 extends MTia{
    private String CUSIDT = ""; //  �ͻ���
    private String APCODE = ""; //  ������
    private String CURCDE = ""; //   �ұ�
    private String SECAMT = ""; // ��С���
    private String OVELIM = ""; // �����
    private String TLRNUM = ""; //  ��Ա��
    private String VCHSET = ""; // ��Ʊ�׺�
    private String FUNCDE = ""; //  �����
    private String REGADD = ""; // �����ֶ�
    private String BEGNUM = ""; // ��ʼ����
    private String ANACDE = ""; //ͳ�Ʒ�����

    public M8822() {
    }

    public M8822(String CUSIDT, String APCODE, String CURCDE,
                 String SECAMT, String OVELIM, String TLRNUM,
                 String VCHSET, String FUNCDE, String REGADD, String BEGNUM) {
        this.CUSIDT = CUSIDT;
        this.APCODE = APCODE;
        this.CURCDE = CURCDE;
        this.SECAMT = SECAMT;
        this.OVELIM = OVELIM;
        this.TLRNUM = TLRNUM;
        this.VCHSET = VCHSET;
        this.FUNCDE = FUNCDE;
        this.REGADD = REGADD;
        this.BEGNUM = BEGNUM;
    }

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getSECAMT() {
        return SECAMT;
    }

    public void setSECAMT(String SECAMT) {
        this.SECAMT = SECAMT;
    }

    public String getOVELIM() {
        return OVELIM;
    }

    public void setOVELIM(String OVELIM) {
        this.OVELIM = OVELIM;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getREGADD() {
        return REGADD;
    }

    public void setREGADD(String REGADD) {
        this.REGADD = REGADD;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }

    public String getANACDE() {
        return ANACDE;
    }

    public void setANACDE(String ANACDE) {
        this.ANACDE = ANACDE;
    }
}
