package gateway.sbs.txn.model.msg;

/**
 * �Թ�֧�������Ѳ�ѯ
 */
public class Mn065 extends MTia {

    private String FBACCT = "";              // ��ѯ���� 6 YYYYMM
    private String FUNCDE = "";              // ��ѯ��� 0-δ�ɷ�,1-�ѽɷ�,2-ȫ��
    private String BEGNUM = "";              // ��ʼ����

    public Mn065(String FBACCT, String FUNCDE, String BEGNUM) {
        this.FBACCT = FBACCT;
        this.FUNCDE = FUNCDE;
        this.BEGNUM = BEGNUM;
    }

    public String getFBACCT() {
        return FBACCT;
    }

    public void setFBACCT(String FBACCT) {
        this.FBACCT = FBACCT;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
