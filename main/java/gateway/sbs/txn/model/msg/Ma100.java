package gateway.sbs.txn.model.msg;

/**
 * ǩ��
 */
public class Ma100 extends MTia {
    private String ACTTY1;        //ȡ��/�����ʻ������� #
    private String IPTAC1;        //ȡ��/�����ʻ���   #
    private String DUEFLG;        //ǿ�Ƶ��ڱ�־ #
    private String DRAMD1;        //ȡ�ʽ   #
    private String CUSPW1 = "";        //ȡ������
    private String DPTPRD;      //��������   #
    private String VALDAT;      //��Ϣ��    #
    private String TXNAMT;      //���׽��   #
    private String VCHTYP;      //ת��/����ƾ֤����  #
    private String VCHNUM;      //ת��/����ƾ֤����   #
    private String PASTYP = "";      //֤�����
    private String PASSNO = "";      //֤������
    private String ACTTY2 = "";        //ת��/�����ʺ�����
    private String IPTAC2 = "";        //ת��/�����ʺź�
    private String REMARK = "";        //ժҪ
    private String ANACDE;        //������      #
    private String VCHTY1 = "";        //ȡ��/����ƾ֤����
    private String VCHNU1 = "";        //ȡ��/����ƾ֤����
    private String MAGFL1 = "";        //ˢ���������ʺű�־




    public Ma100(String ACTTY1, String IPTAC1, String DUEFLG, String DRAMD1, String DPTPRD, String VALDAT, String TXNAMT,
                 String VCHTYP, String VCHNUM, String ANACDE) {
        this.ACTTY1 = ACTTY1;
        this.IPTAC1 = IPTAC1;
        this.DUEFLG = DUEFLG;
        this.DRAMD1 = DRAMD1;
        this.DPTPRD = DPTPRD;
        this.VALDAT = VALDAT;
        this.TXNAMT = TXNAMT;
        this.VCHTYP = VCHTYP;
        this.VCHNUM = VCHNUM;
        this.ANACDE = ANACDE;

    }

    public String getACTTY1() {
        return ACTTY1;
    }

    public void setACTTY1(String ACTTY1) {
        this.ACTTY1 = ACTTY1;
    }

    public String getIPTAC1() {
        return IPTAC1;
    }

    public void setIPTAC1(String IPTAC1) {
        this.IPTAC1 = IPTAC1;
    }

    public String getDUEFLG() {
        return DUEFLG;
    }

    public void setDUEFLG(String DUEFLG) {
        this.DUEFLG = DUEFLG;
    }

    public String getDRAMD1() {
        return DRAMD1;
    }

    public void setDRAMD1(String DRAMD1) {
        this.DRAMD1 = DRAMD1;
    }

    public String getCUSPW1() {
        return CUSPW1;
    }

    public void setCUSPW1(String CUSPW1) {
        this.CUSPW1 = CUSPW1;
    }

    public String getDPTPRD() {
        return DPTPRD;
    }

    public void setDPTPRD(String DPTPRD) {
        this.DPTPRD = DPTPRD;
    }

    public String getVALDAT() {
        return VALDAT;
    }

    public void setVALDAT(String VALDAT) {
        this.VALDAT = VALDAT;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }

    public String getVCHNUM() {
        return VCHNUM;
    }

    public void setVCHNUM(String VCHNUM) {
        this.VCHNUM = VCHNUM;
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

    public String getACTTY2() {
        return ACTTY2;
    }

    public void setACTTY2(String ACTTY2) {
        this.ACTTY2 = ACTTY2;
    }

    public String getIPTAC2() {
        return IPTAC2;
    }

    public void setIPTAC2(String IPTAC2) {
        this.IPTAC2 = IPTAC2;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getANACDE() {
        return ANACDE;
    }

    public void setANACDE(String ANACDE) {
        this.ANACDE = ANACDE;
    }

    public String getVCHTY1() {
        return VCHTY1;
    }

    public void setVCHTY1(String VCHTY1) {
        this.VCHTY1 = VCHTY1;
    }

    public String getVCHNU1() {
        return VCHNU1;
    }

    public void setVCHNU1(String VCHNU1) {
        this.VCHNU1 = VCHNU1;
    }

    public String getMAGFL1() {
        return MAGFL1;
    }

    public void setMAGFL1(String MAGFL1) {
        this.MAGFL1 = MAGFL1;
    }
}
