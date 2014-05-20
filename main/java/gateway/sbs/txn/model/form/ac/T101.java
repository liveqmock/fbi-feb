package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * 8101 �ͻ����� ��Ӧ����
 */
public class T101 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 2, 14, 72, 8, 8, 4};
    }

    private String ORGIDT;          // ���ź�
    private String DEPNUM;          // ������
    private String ACTNUM;          // �˺�
    private String CUSNAM;          // �ͻ�����
    private String OPNDAT;          // ��������
    private String CLSDAT;          // ע������
    private String AMDTLR;          // ����Ա����

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

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getCUSNAM() {
        return CUSNAM;
    }

    public void setCUSNAM(String CUSNAM) {
        this.CUSNAM = CUSNAM;
    }

    public String getOPNDAT() {
        return OPNDAT;
    }

    public void setOPNDAT(String OPNDAT) {
        this.OPNDAT = OPNDAT;
    }

    public String getCLSDAT() {
        return CLSDAT;
    }

    public void setCLSDAT(String CLSDAT) {
        this.CLSDAT = CLSDAT;
    }

    public String getAMDTLR() {
        return AMDTLR;
    }

    public void setAMDTLR(String AMDTLR) {
        this.AMDTLR = AMDTLR;
    }
}
