package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-5
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
public class M8002 extends MTia {
    private String cusidt=""; //客户号
    private String cusnam="";  //客户名称

    public M8002(){}
    public M8002(String cusidt,String cusnam){
        this.cusidt=cusidt;
        this.cusnam=cusnam;
    }
    public String getCusidt() {
        return cusidt;
    }

    public void setCusidt(String cusidt) {
        this.cusidt = cusidt;
    }

    public String getCusnam() {
        return cusnam;
    }

    public void setCusnam(String cusnam) {
        this.cusnam = cusnam;
    }
}
