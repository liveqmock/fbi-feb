package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/5    ��ѯ�˻���Ϣ��ϸ
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
public class M8857 extends MTia {

    private String ACTNUM = "";     //�ʻ��ʺ�
    private String BEGDAT = "";     //��������
    private String BEGNUM = "000001";     //��ʼ����

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getBEGDAT() {
        return BEGDAT;
    }

    public void setBEGDAT(String BEGDAT) {
        this.BEGDAT = BEGDAT;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
