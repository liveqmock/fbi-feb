package gateway.sbs.txn.model.msg;

/**
 * �رտͻ��˻�ǰ���˻���ѯ
 */
public class M8109 extends MTia {

    private String BATSEQ = "111111";  // ƽ̨˳���
    private String ORGIDT = "010";     // ������
    private String DEPNUM = "60";      // ���ź�
    private String ORGID3 = "010";     // �˻�������
    private String ACTNUM = "";        // 14λ�˺�(�ͻ���+������+�ұ�)
    private String VCHAUT = "";        // ���ܴ���

    public String getBATSEQ() {
        return BATSEQ;
    }

    public void setBATSEQ(String BATSEQ) {
        this.BATSEQ = BATSEQ;
    }

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

    public String getORGID3() {
        return ORGID3;
    }

    public void setORGID3(String ORGID3) {
        this.ORGID3 = ORGID3;
    }

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getVCHAUT() {
        return VCHAUT;
    }

    public void setVCHAUT(String VCHAUT) {
        this.VCHAUT = VCHAUT;
    }
}
