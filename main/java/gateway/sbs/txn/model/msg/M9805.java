package gateway.sbs.txn.model.msg;

/**
 * ���ʶ�ʲ�ѯ
 */
public class M9805 extends MTia {

    private String BATSEQ = "111111";  // ƽ̨˳���
    private String ORGIDT = "010";     // ������
    private String DEPNUM = "60";      // ���ź�
    private String CURCDE;             // �ұ�
    private String IRTCDE = "";        // ������
    private String EFFDAT;             // ����
    private String FUNCDE = "6";       // �������  5-��ʲ�ѯ���������룩6-��ʲ�ѯ�������ڣ�
    private String BEGNUM = "000001"; // ��ʼ����

    public M9805(String CURCDE, String EFFDAT) {
        this.CURCDE = CURCDE;
        this.EFFDAT = EFFDAT;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public String getBATSEQ() {
        return BATSEQ;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public String getIRTCDE() {
        return IRTCDE;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }
}
