package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-4-30
 * Time: 上午8:52
 * To change this template use File | Settings | File Templates.
 */
public class M8951 extends MTia {

   private String FUNCDE = "";     //功能码     0-查询,1-打开,2-对账,3-关闭,4-次日账
   private String RECTYP = "";     //操作类别   0-全部，1-单笔
   private String ANACDE = "";     //渠道代码
   private String BEGNUM = "000001";     //起始序号

    public M8951() {
    }

    public M8951(String FUNCDE, String RECTYP) {
        this.FUNCDE = FUNCDE;
        this.RECTYP = RECTYP;
    }

    public M8951(String FUNCDE, String RECTYP, String ANACDE) {
        this.FUNCDE = FUNCDE;
        this.RECTYP = RECTYP;
        this.ANACDE = ANACDE;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getRECTYP() {
        return RECTYP;
    }

    public void setRECTYP(String RECTYP) {
        this.RECTYP = RECTYP;
    }

    public String getANACDE() {
        return ANACDE;
    }

    public void setANACDE(String ANACDE) {
        this.ANACDE = ANACDE;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
