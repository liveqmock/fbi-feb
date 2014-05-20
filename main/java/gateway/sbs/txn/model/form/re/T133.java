package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * 7082���ڽ���
 */

public class T133 extends SOFFormBody {

   {
    fieldTypes = new int[]{1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 3};
    fieldLengths = new int[]{20, 1, 18, 2, 15, 8, 2, 13, 20, 20, 15, 15} ;
   }

    private String ACTNM1;          // �˻���
    private String PASTYP;          // ֤������
    private String PASNO1;          // ֤������
    private String ACTTYP;          // �˻����
    private BigDecimal CURBL1;          // ���
    private String VALDAT;          // ��Ϣ����
    private String DPTPRD;          // ����
    private String TOTINT;          // ʵ�����
    private String ACTNM2;          // �˺�
    private String CURNAM;          // �ұ���
    private BigDecimal PREBAL;          // ����ǰ���
    private BigDecimal PSTBAL;          // ���׺����

    public String getACTNM1() {
        return ACTNM1;
    }

    public void setACTNM1(String ACTNM1) {
        this.ACTNM1 = ACTNM1;
    }

    public String getPASTYP() {
        return PASTYP;
    }

    public void setPASTYP(String PASTYP) {
        this.PASTYP = PASTYP;
    }

    public String getPASNO1() {
        return PASNO1;
    }

    public void setPASNO1(String PASNO1) {
        this.PASNO1 = PASNO1;
    }

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public BigDecimal getCURBL1() {
        return CURBL1;
    }

    public void setCURBL1(BigDecimal CURBL1) {
        this.CURBL1 = CURBL1;
    }

    public String getVALDAT() {
        return VALDAT;
    }

    public void setVALDAT(String VALDAT) {
        this.VALDAT = VALDAT;
    }

    public String getDPTPRD() {
        return DPTPRD;
    }

    public void setDPTPRD(String DPTPRD) {
        this.DPTPRD = DPTPRD;
    }

    public String getTOTINT() {
        return TOTINT;
    }

    public void setTOTINT(String TOTINT) {
        this.TOTINT = TOTINT;
    }

    public String getACTNM2() {
        return ACTNM2;
    }

    public void setACTNM2(String ACTNM2) {
        this.ACTNM2 = ACTNM2;
    }

    public String getCURNAM() {
        return CURNAM;
    }

    public void setCURNAM(String CURNAM) {
        this.CURNAM = CURNAM;
    }

    public BigDecimal getPREBAL() {
        return PREBAL;
    }

    public void setPREBAL(BigDecimal PREBAL) {
        this.PREBAL = PREBAL;
    }

    public BigDecimal getPSTBAL() {
        return PSTBAL;
    }

    public void setPSTBAL(BigDecimal PSTBAL) {
        this.PSTBAL = PSTBAL;
    }


    //    }
}
