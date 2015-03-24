package gateway.sbs.txn.model.msg;

/**
 * Created by Lichao.W At 2015/3/24 9:17
 * wanglichao@163.com
 */
public class M8621 extends MTia{
    private String CURCDE  = "001";           //   ±Ò±ð
    private String BEGNUM  = "1";             //  ÆðÊ¼ÐòºÅ

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
