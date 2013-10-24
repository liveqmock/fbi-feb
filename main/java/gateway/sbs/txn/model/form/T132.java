package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 *
 */
public class T132 extends SOFFormBody {

        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{7, 20, 1, 18, 30, 15, 3};
        }

        private String CUSIDT;       // �ͻ���
        private String ACTNAM;       // �˻�����
        private String PASTYP;       // ֤�����
        private String PASSNO;       // ֤������
        private String CORADD;       // ��ַ
        private String TELNUM;       //   �绰
        private String CURCDE;       //   �ұ���

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
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

    public String getTELNUM() {
        return TELNUM;
    }

    public void setTELNUM(String TELNUM) {
        this.TELNUM = TELNUM;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }


    //    public int offset = 0;


}
