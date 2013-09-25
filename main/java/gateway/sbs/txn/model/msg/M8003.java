package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-24          关闭客户请求报文
 * Time: 下午8:55
 * To change this template use File | Settings | File Templates.
 */
public class M8003 extends MTia {

   private String BATSEQ =  "111111" ;      //平台顺序号
   private String ORGIDT =  "010" ;      //机构号
   private String DEPNUM =  "60" ;      //部门号
   private String CUSNAM =  "" ;      //客户号

    public M8003(){}
    public M8003(String CUSNAM){
        this.CUSNAM = CUSNAM;
    }
    public String getBATSEQ() {
        return BATSEQ;
    }

    public void setBATSEQ(String BATSEQ) {
        this.BATSEQ = BATSEQ;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public void setDEPNUM(String DEPNUM) {
        this.DEPNUM = DEPNUM;
    }

    public String getCUSNAM() {
        return CUSNAM;
    }

    public void setCUSNAM(String CUSNAM) {
        this.CUSNAM = CUSNAM;
    }
}
