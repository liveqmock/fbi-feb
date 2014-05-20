package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * �Թ�֧�����ʲ�ѯ
 */
public class T537 extends SOFFormBody {

    {
        fieldTypes = new int[]{
                1, 1, 1, 1, 1, 1, 4,
                1, 1, 1, 4, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{
                18, 4, 8, 10, 6, 3, 15,
                2, 22, 72, 15, 35, 200, 200,
                40, 150, 150, 12, 8, 40, 10,
                4, 6};
    }

    private String FBTIDX;       // ������
    private String ORDTLR;       // ��Ա��
    private String TERMNM;       // ����
    private String ORDDAT;       // ����
    private String ORDTIM;       // ʱ��
    private String TXNCUR;       // ���Һ�
    private BigDecimal TXNAMT;       // ���׽��
    private String CUSTYP;       // �˻����
    private String CUSACT;       // ������ʺ�
    private String ACTNAM;       // ���������
    private BigDecimal FEEAMT;       // ����
    private String BENACT;       // �տ����˺�
    private String BENNAM;       // �տ�������
    private String BENBNK;       // �տ�������
    private String PBKACT;       // ���������
    private String RETNAM;       // ���������
    private String RETAUX;       // ����
    private String CHQNUM;       // �տ����к�
    private String PRCSTS;       // ״̬
    private String PRCMSG;       // ������Ϣ
    private String PRCDAT;       // ��������
    private String PRCTLR;       // �����Ա
    private String PRCTIM;       // ����ʱ��

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

    public String getTERMNM() {
        return TERMNM;
    }

    public void setTERMNM(String TERMNM) {
        this.TERMNM = TERMNM;
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

    public BigDecimal getFEEAMT() {
        return FEEAMT;
    }

    public void setFEEAMT(BigDecimal FEEAMT) {
        this.FEEAMT = FEEAMT;
    }

    public String getBENACT() {
        return BENACT;
    }

    public void setBENACT(String BENACT) {
        this.BENACT = BENACT;
    }

    public String getBENNAM() {
        return BENNAM;
    }

    public void setBENNAM(String BENNAM) {
        this.BENNAM = BENNAM;
    }

    public String getBENBNK() {
        return BENBNK;
    }

    public void setBENBNK(String BENBNK) {
        this.BENBNK = BENBNK;
    }

    public String getPBKACT() {
        return PBKACT;
    }

    public void setPBKACT(String PBKACT) {
        this.PBKACT = PBKACT;
    }

    public String getRETNAM() {
        return RETNAM;
    }

    public void setRETNAM(String RETNAM) {
        this.RETNAM = RETNAM;
    }

    public String getRETAUX() {
        return RETAUX;
    }

    public void setRETAUX(String RETAUX) {
        this.RETAUX = RETAUX;
    }

    public String getCHQNUM() {
        return CHQNUM;
    }

    public void setCHQNUM(String CHQNUM) {
        this.CHQNUM = CHQNUM;
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
}
