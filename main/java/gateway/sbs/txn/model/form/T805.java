package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBean;

/**
 * 9805���ʶ�ʲ�ѯ��Ӧ T805
 */
public class T805 extends SOFFormBean {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 8, 3, 11, 4, 4, 1, 1, 24};
    }

    private String IRTCDE;          // ����������
    private String EXTDAT;          // ��������
    private String CURCDE;          // �ұ�
    private String BASIRT;          // ��׼����
    private String FLTMAX;          // ��������
    private String FLTMIN;          // ��������
    private String FLTFLG;          // ������־
    private String CDTYPE;          // �����
    private String IRTNAM;          // ��������

//    public int offset = 0;
    public String getIRTCDE() {
        return IRTCDE;
    }

    public void setIRTCDE(String IRTCDE) {
        this.IRTCDE = IRTCDE;
    }

    public String getEXTDAT() {
        return EXTDAT;
    }

    public void setEXTDAT(String EXTDAT) {
        this.EXTDAT = EXTDAT;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getBASIRT() {
        return BASIRT;
    }

    public void setBASIRT(String BASIRT) {
        this.BASIRT = BASIRT;
    }

    public String getFLTMAX() {
        return FLTMAX;
    }

    public void setFLTMAX(String FLTMAX) {
        this.FLTMAX = FLTMAX;
    }

    public String getFLTMIN() {
        return FLTMIN;
    }

    public void setFLTMIN(String FLTMIN) {
        this.FLTMIN = FLTMIN;
    }

    public String getFLTFLG() {
        return FLTFLG;
    }

    public void setFLTFLG(String FLTFLG) {
        this.FLTFLG = FLTFLG;
    }

    public String getCDTYPE() {
        return CDTYPE;
    }

    public void setCDTYPE(String CDTYPE) {
        this.CDTYPE = CDTYPE;
    }

    public String getIRTNAM() {
        return IRTNAM;
    }

    public void setIRTNAM(String IRTNAM) {
        this.IRTNAM = IRTNAM;
    }

    public String toString() {

        return IRTCDE + " " + IRTNAM + " " + EXTDAT +
                CURCDE + " " + BASIRT + " " + FLTMAX +
                FLTMIN + " " + FLTFLG + " " + CDTYPE;
    }
}
