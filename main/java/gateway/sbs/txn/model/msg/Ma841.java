package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/7/24       总分账号对照查询
 * Time: 9:32
 * To change this template use File | Settings | File Templates.
 */
public class Ma841 extends MTia{

    private String DPTTYP = "";  //代码类别 24-总分账号对照，26-总分账号月底保留余额
    private String BEGNUM = "1";  //起始笔数

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
