package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15
 * Time: ����11:24
 * To change this template use File | Settings | File Templates.
 */
public class M9816 extends MTia {

    private String BATSEQ = "111111";     //ƽ̨˳���
    private String ORGIDT = "010";     //������
    private String DEPNUM = "60";     //���ź�
    private String ALCODE = "";     //�ʲ���ծ��Ŀ��
    private String GLCODE = "";     //����Ŀ��������
    private String ALCNAM = "";     //��Ŀ����
    private String ALCTYP = "";     //�ʲ���ծ����
    private String DCTYPE = "";     //����������ʶ
    private String OPRFLG = "";     //������
    private String GLCFLG = "";     //��Ŀ�ܼƱ�־
    private String SUMFLG = "";     //ͳһ��ƿ�Ŀ��־
    private String ASTRWT = "";     //�ʲ�����Ȩ��
    private String FUNCDE = "";     //�������
    private String BEGNUM = "000001";     //��ʼ����


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

    public String getALCODE() {
        return ALCODE;
    }

    public void setALCODE(String ALCODE) {
        this.ALCODE = ALCODE;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getALCNAM() {
        return ALCNAM;
    }

    public void setALCNAM(String ALCNAM) {
        this.ALCNAM = ALCNAM;
    }

    public String getALCTYP() {
        return ALCTYP;
    }

    public void setALCTYP(String ALCTYP) {
        this.ALCTYP = ALCTYP;
    }

    public String getDCTYPE() {
        return DCTYPE;
    }

    public void setDCTYPE(String DCTYPE) {
        this.DCTYPE = DCTYPE;
    }

    public String getOPRFLG() {
        return OPRFLG;
    }

    public void setOPRFLG(String OPRFLG) {
        this.OPRFLG = OPRFLG;
    }

    public String getGLCFLG() {
        return GLCFLG;
    }

    public void setGLCFLG(String GLCFLG) {
        this.GLCFLG = GLCFLG;
    }

    public String getSUMFLG() {
        return SUMFLG;
    }

    public void setSUMFLG(String SUMFLG) {
        this.SUMFLG = SUMFLG;
    }

    public String getASTRWT() {
        return ASTRWT;
    }

    public void setASTRWT(String ASTRWT) {
        this.ASTRWT = ASTRWT;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
