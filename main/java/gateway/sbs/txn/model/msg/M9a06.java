package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-14
 * Time: 下午1:16
 * To change this template use File | Settings | File Templates.
 */
public class M9a06 extends MTia {

    private String BATSEQ = "111111";       //平台顺序号
    private String ORGIDT = "010";       //机构号
    private String DEPNUM = "60";       //部门号
    private String CURCDE = "";       //币别码
    private String FRTCDE = "";       //收费码
    private String EFFDAT = "";       //生效日期
    private String FRTNAM = "";       //收费名称
    private String DOCCDE = "";       //凭证代码
    private String DOCNAM = "";       //凭证名称
    private String FRTSPH = "";       //费率浮动上限值
    private String FRTSPL = "";       //费率浮动下限值
    private String SPRFLG = "";       //浮动标识
    private String MALFEE = "";       //邮电费
    private String COMFEE = "";       //手续费
    private String FEERAT = "";       //收费率
    private String HIGLIM = "";       //计费高限
    private String LOWLIM = "";       //计费低限
    private String FEECYC = "";       //计费周期
    private String FEESDE = "";       //收费方式
    private String FEEAPC = "";       //收费转帐帐户核算码
    private String FUNCDE = "0";       //功能参数
    private String BEGNUM = "000001";       //起始序号

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

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getFRTCDE() {
        return FRTCDE;
    }

    public void setFRTCDE(String FRTCDE) {
        this.FRTCDE = FRTCDE;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public String getFRTNAM() {
        return FRTNAM;
    }

    public void setFRTNAM(String FRTNAM) {
        this.FRTNAM = FRTNAM;
    }

    public String getDOCCDE() {
        return DOCCDE;
    }

    public void setDOCCDE(String DOCCDE) {
        this.DOCCDE = DOCCDE;
    }

    public String getDOCNAM() {
        return DOCNAM;
    }

    public void setDOCNAM(String DOCNAM) {
        this.DOCNAM = DOCNAM;
    }

    public String getFRTSPH() {
        return FRTSPH;
    }

    public void setFRTSPH(String FRTSPH) {
        this.FRTSPH = FRTSPH;
    }

    public String getFRTSPL() {
        return FRTSPL;
    }

    public void setFRTSPL(String FRTSPL) {
        this.FRTSPL = FRTSPL;
    }

    public String getSPRFLG() {
        return SPRFLG;
    }

    public void setSPRFLG(String SPRFLG) {
        this.SPRFLG = SPRFLG;
    }

    public String getMALFEE() {
        return MALFEE;
    }

    public void setMALFEE(String MALFEE) {
        this.MALFEE = MALFEE;
    }

    public String getCOMFEE() {
        return COMFEE;
    }

    public void setCOMFEE(String COMFEE) {
        this.COMFEE = COMFEE;
    }

    public String getFEERAT() {
        return FEERAT;
    }

    public void setFEERAT(String FEERAT) {
        this.FEERAT = FEERAT;
    }

    public String getHIGLIM() {
        return HIGLIM;
    }

    public void setHIGLIM(String HIGLIM) {
        this.HIGLIM = HIGLIM;
    }

    public String getLOWLIM() {
        return LOWLIM;
    }

    public void setLOWLIM(String LOWLIM) {
        this.LOWLIM = LOWLIM;
    }

    public String getFEECYC() {
        return FEECYC;
    }

    public void setFEECYC(String FEECYC) {
        this.FEECYC = FEECYC;
    }

    public String getFEESDE() {
        return FEESDE;
    }

    public void setFEESDE(String FEESDE) {
        this.FEESDE = FEESDE;
    }

    public String getFEEAPC() {
        return FEEAPC;
    }

    public void setFEEAPC(String FEEAPC) {
        this.FEEAPC = FEEAPC;
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
