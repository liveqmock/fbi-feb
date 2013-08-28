package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBean;

/**
 * 9804���ʵ��ʲ�ѯ����ɾ��-��Ӧ T804
 */
public class T804 extends SOFFormBean {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 1, 2, 8, 24, 10, 4, 4, 1, 1, 3, 1, 4, 8};
    }

    private String CURCDE;          // ������
    private String IRTKD1;          // ����������1
    private String IRTKD2;          // ����������2
    private String EFFDAT;          // ��������
    private String IRTNAM;          // ��������
    private String IRTVAL;          // ��׼����ֵ
    private String IRTSPH;          // ��������ֵ
    private String IRTSPL;          // ��������ֵ
    private String SPRFLG;          // ������־
    private String CDFLAG;          // �����־
    private String IRTTRM;          // ����
    private String TRMUNT;          // ���޵�λ
    private String CRETLR;          //  ������Ա
    private String CREDAT;          // ��������

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getIRTKD1() {
        return IRTKD1;
    }

    public void setIRTKD1(String IRTKD1) {
        this.IRTKD1 = IRTKD1;
    }

    public String getIRTKD2() {
        return IRTKD2;
    }

    public void setIRTKD2(String IRTKD2) {
        this.IRTKD2 = IRTKD2;
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

    public String getCRETLR() {
        return CRETLR;
    }

    public void setCRETLR(String CRETLR) {
        this.CRETLR = CRETLR;
    }

    public String getCREDAT() {
        return CREDAT;
    }

    public void setCREDAT(String CREDAT) {
        this.CREDAT = CREDAT;
    }
}
