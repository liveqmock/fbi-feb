package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * �����������ʲ�ѯ
 */
public class T547 extends SOFFormBody {

    {
        fieldTypes = new int[]{
                1, 1, 1, 1, 1, 4, 1,
                1, 1, 1, 1, 1, 1, 1,
                1, 5, 5, 4, 1, 1, 1,
                1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{
                18, 4, 10, 6, 3, 15, 2,
                22, 60, 3, 8, 60, 8, 40,
                16, 6, 6, 15, 3, 32, 12,
                20, 12, 10, 4, 6, 10};
    }

    private String FBTIDX;       // ������
    private String ORDTLR;       // ��Ա��
    private String ORDDAT;       // ����
    private String ORDTIM;       // ʱ��
    private String TXNCUR;       // ���Һ�
    private BigDecimal TXNAMT;       // ���׽��
    private String CUSTYP;       // �˻����
    private String CUSACT;       // ������ʺ�
    private String ACTNAM;       // ���������
    private String ORDTYP;       // ���������
    private String ORDTNM;       // �����������
    private String RETAUX;       // ��ע
    private String PRCSTS;       // ״̬
    private String PRCMSG;       // ������Ϣ
    private String REQNUM;       // �������к�
    private int TOTCNT;       // �ܱ���
    private int FALCNT;       // ʧ�ܱ���
    private BigDecimal FALAMT;       // ʧ�ܽ��
    private String BNKCDE;       // ���д���
    private String BNKACT;       // �����˺�
    private String USAGES;       // ��;
    private String BPWCDE;       // �������۱���
    private String VCHNUM;       // ����ƾ֤��
    private String PRCDAT;       // ��������
    private String PRCTLR;       // �����Ա
    private String PRCTIM;       // ����ʱ��
    private String TRNDAT;       // ת������

    public String getFBTIDX() {
        return FBTIDX;
    }

    public void setFBTIDX(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }

    public String getORDTLR() {
        return ORDTLR;
    }

    public void setORDTLR(String ORDTLR) {
        this.ORDTLR = ORDTLR;
    }

    public String getORDDAT() {
        return ORDDAT;
    }

    public void setORDDAT(String ORDDAT) {
        this.ORDDAT = ORDDAT;
    }

    public String getORDTIM() {
        return ORDTIM;
    }

    public void setORDTIM(String ORDTIM) {
        this.ORDTIM = ORDTIM;
    }

    public String getTXNCUR() {
        return TXNCUR;
    }

    public void setTXNCUR(String TXNCUR) {
        this.TXNCUR = TXNCUR;
    }

    public String getCUSTYP() {
        return CUSTYP;
    }

    public void setCUSTYP(String CUSTYP) {
        this.CUSTYP = CUSTYP;
    }

    public String getCUSACT() {
        return CUSACT;
    }

    public void setCUSACT(String CUSACT) {
        this.CUSACT = CUSACT;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public BigDecimal getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(BigDecimal TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getRETAUX() {
        return RETAUX;
    }

    public void setRETAUX(String RETAUX) {
        this.RETAUX = RETAUX;
    }

    public String getPRCSTS() {
        return PRCSTS;
    }

    public void setPRCSTS(String PRCSTS) {
        this.PRCSTS = PRCSTS;
    }

    public String getPRCMSG() {
        return PRCMSG;
    }

    public void setPRCMSG(String PRCMSG) {
        this.PRCMSG = PRCMSG;
    }

    public String getPRCDAT() {
        return PRCDAT;
    }

    public void setPRCDAT(String PRCDAT) {
        this.PRCDAT = PRCDAT;
    }

    public String getPRCTLR() {
        return PRCTLR;
    }

    public void setPRCTLR(String PRCTLR) {
        this.PRCTLR = PRCTLR;
    }

    public String getPRCTIM() {
        return PRCTIM;
    }

    public void setPRCTIM(String PRCTIM) {
        this.PRCTIM = PRCTIM;
    }

    public String getORDTYP() {
        return ORDTYP;
    }

    public void setORDTYP(String ORDTYP) {
        this.ORDTYP = ORDTYP;
    }

    public String getORDTNM() {
        return ORDTNM;
    }

    public void setORDTNM(String ORDTNM) {
        this.ORDTNM = ORDTNM;
    }

    public String getREQNUM() {
        return REQNUM;
    }

    public void setREQNUM(String REQNUM) {
        this.REQNUM = REQNUM;
    }

    public int getTOTCNT() {
        return TOTCNT;
    }

    public void setTOTCNT(int TOTCNT) {
        this.TOTCNT = TOTCNT;
    }

    public int getFALCNT() {
        return FALCNT;
    }

    public void setFALCNT(int FALCNT) {
        this.FALCNT = FALCNT;
    }

    public BigDecimal getFALAMT() {
        return FALAMT;
    }

    public void setFALAMT(BigDecimal FALAMT) {
        this.FALAMT = FALAMT;
    }

    public String getBNKCDE() {
        return BNKCDE;
    }

    public void setBNKCDE(String BNKCDE) {
        this.BNKCDE = BNKCDE;
    }

    public String getBNKACT() {
        return BNKACT;
    }

    public void setBNKACT(String BNKACT) {
        this.BNKACT = BNKACT;
    }

    public String getUSAGES() {
        return USAGES;
    }

    public void setUSAGES(String USAGES) {
        this.USAGES = USAGES;
    }

    public String getBPWCDE() {
        return BPWCDE;
    }

    public void setBPWCDE(String BPWCDE) {
        this.BPWCDE = BPWCDE;
    }

    public String getVCHNUM() {
        return VCHNUM;
    }

    public void setVCHNUM(String VCHNUM) {
        this.VCHNUM = VCHNUM;
    }

    public String getTRNDAT() {
        return TRNDAT;
    }

    public void setTRNDAT(String TRNDAT) {
        this.TRNDAT = TRNDAT;
    }
}
