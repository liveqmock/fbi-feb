package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-14
 * Time: ����3:12
 * To change this template use File | Settings | File Templates.
 */
public class M8848 extends MTia {

    private String BATSEQ = "111111";        //ƽ̨˳���
    private String ORGIDT = "010";        //������
    private String DEPNUM = "60";        //���ź�
    private String PASTYP = "";        //����
    private String INPFLG = "";        //����
    private String SBKNUM = "";        //С��
    private String WRKUNT = "";        //��������
    private String STMADD = "";        //��������
    private String INTNET = "";        //С������
    private String ENGNAM = "";        //��ҵ��������
    private String REGADD = "";        //˵��һ
    private String CORADD = "";        //˵����
    private String CUSNAM = "";        //˵����
    private String FUNCDE = "";        //�������
    private String BEGNUM = "000001";        //��ʼ����

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

    public String getPASTYP() {
        return PASTYP;
    }

    public void setPASTYP(String PASTYP) {
        this.PASTYP = PASTYP;
    }

    public String getINPFLG() {
        return INPFLG;
    }

    public void setINPFLG(String INPFLG) {
        this.INPFLG = INPFLG;
    }

    public String getSBKNUM() {
        return SBKNUM;
    }

    public void setSBKNUM(String SBKNUM) {
        this.SBKNUM = SBKNUM;
    }

    public String getWRKUNT() {
        return WRKUNT;
    }

    public void setWRKUNT(String WRKUNT) {
        this.WRKUNT = WRKUNT;
    }

    public String getSTMADD() {
        return STMADD;
    }

    public void setSTMADD(String STMADD) {
        this.STMADD = STMADD;
    }

    public String getINTNET() {
        return INTNET;
    }

    public void setINTNET(String INTNET) {
        this.INTNET = INTNET;
    }

    public String getENGNAM() {
        return ENGNAM;
    }

    public void setENGNAM(String ENGNAM) {
        this.ENGNAM = ENGNAM;
    }

    public String getREGADD() {
        return REGADD;
    }

    public void setREGADD(String REGADD) {
        this.REGADD = REGADD;
    }

    public String getCORADD() {
        return CORADD;
    }

    public void setCORADD(String CORADD) {
        this.CORADD = CORADD;
    }

    public String getCUSNAM() {
        return CUSNAM;
    }

    public void setCUSNAM(String CUSNAM) {
        this.CUSNAM = CUSNAM;
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
