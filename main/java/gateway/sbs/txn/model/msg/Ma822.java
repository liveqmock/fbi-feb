package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/10             存款种类表维护-多笔查询
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class Ma822 extends MTia {

   private String DPTTYP = "" ;     //存款种类
   private String CHFMAK = "" ;     //        本外币标识

    public String getDPTTYP() {
        return DPTTYP;
    }

    public void setDPTTYP(String DPTTYP) {
        this.DPTTYP = DPTTYP;
    }

    public String getCHFMAK() {
        return CHFMAK;
    }

    public void setCHFMAK(String CHFMAK) {
        this.CHFMAK = CHFMAK;
    }
}
