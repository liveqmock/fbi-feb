package gateway.sbs.txn.model.msg;

/**
 * ǩ��
 */
public class Ma121 extends MTia {
    private String ACTTY2;      //�˻���� *
    private String IPTAC2;      //�˻���    *
    private String DRAMD2;      //ȡ�ʽ  *
    private String CUSPW2 = "";      //�ͻ�����
    private String ADVNUM = "";      //֪ͨ����
    private String TXNDAT = "";      //��������
    private String PASTYP = "";      //֤������
    private String PASSNO = "";      //֤����
    private String REMARK = "";      //��ַ
    private String MAGFL2 = "";      //�˺����뷽ʽ







    public Ma121(String ACTTY2, String IPTAC2, String DRAMD2) {
        this.ACTTY2 = ACTTY2;
        this.IPTAC2 = IPTAC2;
        this.DRAMD2 = DRAMD2;

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

    public String getDRAMD2() {
        return DRAMD2;
    }

    public void setDRAMD2(String DRAMD2) {
        this.DRAMD2 = DRAMD2;
    }

    public String getCUSPW2() {
        return CUSPW2;
    }

    public void setCUSPW2(String CUSPW2) {
        this.CUSPW2 = CUSPW2;
    }

    public String getADVNUM() {
        return ADVNUM;
    }

    public void setADVNUM(String ADVNUM) {
        this.ADVNUM = ADVNUM;
    }

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
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

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getMAGFL2() {
        return MAGFL2;
    }

    public void setMAGFL2(String MAGFL2) {
        this.MAGFL2 = MAGFL2;
    }
}
