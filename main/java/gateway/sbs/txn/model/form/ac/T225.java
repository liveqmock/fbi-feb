package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/9
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
public class T225 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};  //11
        fieldLengths = new int[]{3, 2, 14, 72, 21, 8, 8, 1, 1, 4, 6};
    }

    private String ORGIDT ;   //������
    private String DEPNUM ;   //���ź�
    private String ACTNUM ;   //�˺�
    private String ACTNAM ;   //����
    private String TXNAMT ;   //������
    private String FRZDAT ;   //��������
    private String FRZEDT ;   //�ⶳ����
    private String FRZRSN ;   //����ԭ��
    private String PROMOD ;   //���᷽ʽ
    private String AMDTLR ;   //�޸Ĺ�Ա��
    private String PDNSEQ ;   //�������


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

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getFRZDAT() {
        return FRZDAT;
    }

    public void setFRZDAT(String FRZDAT) {
        this.FRZDAT = FRZDAT;
    }

    public String getFRZEDT() {
        return FRZEDT;
    }

    public void setFRZEDT(String FRZEDT) {
        this.FRZEDT = FRZEDT;
    }

    public String getFRZRSN() {
        return FRZRSN;
    }

    public void setFRZRSN(String FRZRSN) {
        this.FRZRSN = FRZRSN;
    }

    public String getPROMOD() {
        return PROMOD;
    }

    public void setPROMOD(String PROMOD) {
        this.PROMOD = PROMOD;
    }

    public String getAMDTLR() {
        return AMDTLR;
    }

    public void setAMDTLR(String AMDTLR) {
        this.AMDTLR = AMDTLR;
    }

    public String getPDNSEQ() {
        return PDNSEQ;
    }

    public void setPDNSEQ(String PDNSEQ) {
        this.PDNSEQ = PDNSEQ;
    }
}
