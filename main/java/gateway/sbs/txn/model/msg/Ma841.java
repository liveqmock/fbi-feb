package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/7/24       �ܷ��˺Ŷ��ղ�ѯ
 * Time: 9:32
 * To change this template use File | Settings | File Templates.
 */
public class Ma841 extends MTia{

    private String DPTTYP = "";  //������� 24-�ܷ��˺Ŷ��գ�26-�ܷ��˺��µױ������
    private String BEGNUM = "1";  //��ʼ����

    public String getDPTTYP() {
        return DPTTYP;
    }

    public void setDPTTYP(String DPTTYP) {
        this.DPTTYP = DPTTYP;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
