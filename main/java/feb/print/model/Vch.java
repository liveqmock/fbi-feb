package feb.print.model;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-10-25
 * Time: ����3:22
 * To change this template use File | Settings | File Templates.
 */
public class Vch {
    private String DEBACT;          // �跽�˺�
    private String DEBAMT;          // �跽���
    private String CREACT;          // �����˺�
    private String CREAMT;          // �������

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
