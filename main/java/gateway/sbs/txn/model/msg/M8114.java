package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-18
 * Time: ����12:00
 * To change this template use File | Settings | File Templates.
 */

/**
 * ���ڴ���嵥��ѯ
 */
public class M8114 extends MTia {
    private String BATSEQ = "111111";  //    ƽ̨˳���
    private String ORGIDT = "010";  //            ������
    private String DEPNUM = "60";  //    ���ź�
    private String ORGID3 = "010";  //            �˻�������
    private String ACTNUM = "";  //    �˺�

    public M8114(){

    }
    public M8114(String ORGID3, String ACTNUM) {
        this.ORGID3 = ORGID3;
        this.ACTNUM = ACTNUM;
    }

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
}
