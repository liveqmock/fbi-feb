package gateway.sbs.txn.model.msg;

/**
 * ���ʶ�ʲ�ѯ
 */
public class M9805 extends MTia {
    private String CURCDE;  // �ұ�
    private String IRTDAT; // ����

    public M9805(String CURCDE, String IRTDAT) {
        this.CURCDE = CURCDE;
        this.IRTDAT = IRTDAT;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public String getIRTDAT() {
        return IRTDAT;
    }
}
