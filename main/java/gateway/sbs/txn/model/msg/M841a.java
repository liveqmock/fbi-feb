package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/6      外围传票查询
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class M841a  extends MTia {

    private String PASSNO = "";    //外围系统流水号
    private String BEGNUM = "1";    //起始笔数

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
