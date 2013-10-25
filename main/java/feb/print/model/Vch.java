package feb.print.model;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-10-25
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public class Vch {
    private String DEBACT;          // 借方账号
    private String DEBAMT;          // 借方金额
    private String CREACT;          // 贷方账号
    private String CREAMT;          // 贷方金额

    public String getDEBACT() {
        return DEBACT;
    }

    public void setDEBACT(String DEBACT) {
        this.DEBACT = DEBACT;
    }

    public String getDEBAMT() {
        return DEBAMT;
    }

    public void setDEBAMT(String DEBAMT) {
        this.DEBAMT = DEBAMT;
    }

    public String getCREACT() {
        return CREACT;
    }

    public void setCREACT(String CREACT) {
        this.CREACT = CREACT;
    }

    public String getCREAMT() {
        return CREAMT;
    }

    public void setCREAMT(String CREAMT) {
        this.CREAMT = CREAMT;
    }
}
