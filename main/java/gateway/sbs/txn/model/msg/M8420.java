package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-25
 * Time: ����4:54
 * To change this template use File | Settings | File Templates.
 */
public class M8420 extends MTia {
 private String BATSEQ  = "111111";    //   ƽ̨��ˮ��
 private String ORGIDT  = "010";       //          ��Ա������
 private String DEPNUM  = "60";       //   ��Ա���ź�
 private String VCHSET  = "";          //           ��Ʊ�׺�

    public M8420(){

    }
    public M8420(String VCHSET) {
        this.VCHSET = VCHSET;
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

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }
}
