package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10
 * Time: ����10:10
 * To change this template use File | Settings | File Templates.
 */
//��ʲ�ѯ
public class M9815 extends MTia{
    private String glcode=""; //������
    private String apcode="";  //������
    private String apcnam="";       //����������
    private String apctyp="";       //����������
    private String plcode="";       //��Ӧ������
    private String FUNCDE = "6";       // �������  5-��ʲ�ѯ���������룩6-��ʲ�ѯ��������

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
