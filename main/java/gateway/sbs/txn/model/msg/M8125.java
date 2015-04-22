package gateway.sbs.txn.model.msg;

/**
 * Created by Lichao.W At 2015/4/20 10:12
 * wanglichao@163.com                  æ√–¸’Àªß≤È—Ø
 */
public class M8125 extends MTia {
    private String ACTTYP = "";
    private String BEGNUM ="000001";

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
