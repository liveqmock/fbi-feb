package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/6      ��Χ��Ʊ��ѯ
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class M841a  extends MTia {

    private String PASSNO = "";    //��Χϵͳ��ˮ��
    private String BEGNUM = "1";    //��ʼ����

    public String getPASSNO() {
        return PASSNO;
    }

    public void setPASSNO(String PASSNO) {
        this.PASSNO = PASSNO;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
