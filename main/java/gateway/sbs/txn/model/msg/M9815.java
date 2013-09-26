package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10
 * Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
//多笔查询
public class M9815 extends MTia{
    private String glcode=""; //总账码
    private String apcode="";  //核算码
    private String apcnam="";       //核算码名称
    private String apctyp="";       //核算码类型
    private String plcode="";       //对应损益吗
    private String FUNCDE = "6";       // 操作类别  5-多笔查询（按利率码）6-多笔查询（按日期

    //-----------------------------------------------------------------------
    public M9815(String glcode,String apcode){
        this.glcode=glcode;
        this.apcode=apcode;

    }
    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getApcode() {
        return apcode;
    }

    public void setApcode(String apcode) {
        this.apcode = apcode;
    }

    public String getApcnam() {
        return apcnam;
    }

    public void setApcnam(String apcnam) {
        this.apcnam = apcnam;
    }

    public String getApctyp() {
        return apctyp;
    }

    public void setApctyp(String apctyp) {
        this.apctyp = apctyp;
    }

    public String getPlcode() {
        return plcode;
    }

    public void setPlcode(String plcode) {
        this.plcode = plcode;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }
}
