package gateway.sbs.txn.model.msg;

/**
 * �Թ�֧����ʲ�ѯ
 */
public class Mn036 extends MTia {

    private String ORDDAT = "";              // ��������
    private String PRCSTS = "";              // ״̬
    private String BEGNUM = "";              // ��ʼ����

    public Mn036(String ORDDAT, String PRCSTS, String BEGNUM) {
        this.ORDDAT = ORDDAT;
        this.PRCSTS = PRCSTS;
        this.BEGNUM = BEGNUM;
    }

    public String getORDDAT() {
        return ORDDAT;
    }

    public void setORDDAT(String ORDDAT) {
        this.ORDDAT = ORDDAT;
    }

    public String getPRCSTS() {
        return PRCSTS;
    }

    public void setPRCSTS(String PRCSTS) {
        this.PRCSTS = PRCSTS;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
