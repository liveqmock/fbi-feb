package gateway.sbs.txn.model.msg;

/**
 * ���ʶ�ʲ�ѯ
 */
public class M9805 extends MTia {
    private String CURCDE;  // �ұ�
    private String IRTDAT; // ����

    public M9805(String curcde, String irtdat8) {
        this.CURCDE = curcde;
        this.IRTDAT = irtdat8;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public String getIRTDAT() {
        return IRTDAT;
    }
}
