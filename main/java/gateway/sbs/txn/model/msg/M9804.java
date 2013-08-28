package gateway.sbs.txn.model.msg;

/**
 * ���ʵ��ʲ�ѯ����ɾ��
 */
public class M9804 extends MTia {

    private String CURCDE;       // �ұ�
    private String IRTCDE;       // ������
    private String EFFDAT;       // ��������
    private String IRTNAM = "";  // ��������
    private String CURFLG = "0"; // �Ƿ�ǰ����   0-��1-��
    private String MODFLG = "0"; // ��ǰ�޸ı�־  0-���޸ģ�1-�޸�
    private String IRTVAL = "";  // ��׼����ֵ
    private String IRTSPH = "";  // ��������ֵ
    private String IRTSPL = "";  // ��������ֵ
    private String SPRFLG = "";  // ������־
    private String CDFLAG = "";  // �����־  D-�裬C-����B-˫��ʹ��
    private String IRTTRM = "";  // ����
    private String TRMUNT = "";  // ���޵�λ
    private String FUNCDE;       // ������� 0-���ʲ�ѯ,2-�޸�, 3-ɾ��, 4-����

    public M9804() {
    }

    public M9804(String CURCDE, String IRTCDE, String EFFDAT, String FUNCDE) {
        this.CURCDE = CURCDE;
        this.IRTCDE = IRTCDE;
        this.EFFDAT = EFFDAT;
        this.FUNCDE = FUNCDE;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getIRTCDE() {
        return IRTCDE;
    }

    public void setIRTCDE(String IRTCDE) {
        this.IRTCDE = IRTCDE;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public String getIRTNAM() {
        return IRTNAM;
    }

    public void setIRTNAM(String IRTNAM) {
        this.IRTNAM = IRTNAM;
    }

    public String getCURFLG() {
        return CURFLG;
    }

    public void setCURFLG(String CURFLG) {
        this.CURFLG = CURFLG;
    }

    public String getMODFLG() {
        return MODFLG;
    }

    public void setMODFLG(String MODFLG) {
        this.MODFLG = MODFLG;
    }

    public String getIRTVAL() {
        return IRTVAL;
    }

    public void setIRTVAL(String IRTVAL) {
        this.IRTVAL = IRTVAL;
    }

    public String getIRTSPH() {
        return IRTSPH;
    }

    public void setIRTSPH(String IRTSPH) {
        this.IRTSPH = IRTSPH;
    }

    public String getIRTSPL() {
        return IRTSPL;
    }

    public void setIRTSPL(String IRTSPL) {
        this.IRTSPL = IRTSPL;
    }

    public String getSPRFLG() {
        return SPRFLG;
    }

    public void setSPRFLG(String SPRFLG) {
        this.SPRFLG = SPRFLG;
    }

    public String getCDFLAG() {
        return CDFLAG;
    }

    public void setCDFLAG(String CDFLAG) {
        this.CDFLAG = CDFLAG;
    }

    public String getIRTTRM() {
        return IRTTRM;
    }

    public void setIRTTRM(String IRTTRM) {
        this.IRTTRM = IRTTRM;
    }

    public String getTRMUNT() {
        return TRMUNT;
    }

    public void setTRMUNT(String TRMUNT) {
        this.TRMUNT = TRMUNT;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }
}
