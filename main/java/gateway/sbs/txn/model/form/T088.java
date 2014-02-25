package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * ���ڲ���֧ȡ
 */

public class T088 extends SOFFormBody {

   {
    fieldTypes = new int[]{1,3,1,1,1,1,1,1};
    fieldLengths = new int[]{2,15,15,20,1,18, 30, 20} ;
   }

    private String ACTTYP	;         //�˻����
    private BigDecimal CURBAL	;         //���
    private String INTEST	;         //��Ϣ
    private String ACTNAM	;         //����
    private String PASTYP	;         //֤������
    private String PASSNO	;         //֤������
    private String CORADD	;         //��ַ
    private String CURNAM ;         //�ұ�����

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public BigDecimal getCURBAL() {
        return CURBAL;
    }

    public void setCURBAL(BigDecimal CURBAL) {
        this.CURBAL = CURBAL;
    }

    public String getINTEST() {
        return INTEST;
    }

    public void setINTEST(String INTEST) {
        this.INTEST = INTEST;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getPASTYP() {
        return PASTYP;
    }

    public void setPASTYP(String PASTYP) {
        this.PASTYP = PASTYP;
    }

    public String getPASSNO() {
        return PASSNO;
    }

    public void setPASSNO(String PASSNO) {
        this.PASSNO = PASSNO;
    }

    public String getCORADD() {
        return CORADD;
    }

    public void setCORADD(String CORADD) {
        this.CORADD = CORADD;
    }

    public String getCURNAM() {
        return CURNAM;
    }

    public void setCURNAM(String CURNAM) {
        this.CURNAM = CURNAM;
    }
}
