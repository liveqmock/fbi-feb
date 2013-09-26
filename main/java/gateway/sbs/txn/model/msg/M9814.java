package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10
 * Time: 下午9:03
 * To change this template use File | Settings | File Templates.
 */
public class M9814 extends MTia {

    private String BATSEQ = "111111";   //平台顺序号 固定值  "111111"  Y
    private String ORGIDT = "010";      //机构号  固定值      "010"      Y
    private String DEPNUM = "60";       //部门号  固定值      "60"       Y
    private String APCODE = "";         //核算码                         Y
    private String APCNAM = "";         //核算码名称                     Y
    private String APCTYP = "";        //核算码类型 0-普通类 4-999临时存欠 5-外汇买卖人民币 6-933外币  7-自动对转类 8-临时类 9-催收类
    private String GLCODE = "";         //所属总账码                      Y
    private String PLCODE = "";  //对应损益码(损益类核算码才有对应的损益码)  损益码表ACTPLC
    private String INTEXP="";           //利息支出账户核算码
    private String INTINC="";           //利息收入账户核算码
    private String TRNIDT="";            //未实现利息支出核算码
    private String FEEAPC="";            //未实现利息收入核算码
    private String PDCTYP="0";            //核算码种类
    private String EBKCDE="";            //对应总行核算码
    private String OPSAPC = "";         //所属标准核算代码         Y
    private String APCDCR = "";         //核算码描述               Y
    private String INTDAC = "";         //应付利息账户核算码       Y
    private String INTCAC = "";         //应收利息账户核算码       Y
    private String FUNCDE="0";           //功能码 0-单笔查询、1-多笔查询 2-修改、3-删除、4-增加   Y
    private String DEGNUM = "";         //开始序号               Y
    private String CLRFLG = "1";         //引用号                 Y
    private String MODFLG= "0";        // 当前修改标志  0-不修改，1-修改
    private String TLRNCDE="";         //柜员号

    //-----------------------------------------------------------
    public M9814() {
    }

    public M9814(String GLCODE, String APCODE) {
        this.GLCODE = GLCODE;
        this.APCODE = APCODE;
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

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getAPCNAM() {
        return APCNAM;
    }

    public void setAPCNAM(String APCNAM) {
        this.APCNAM = APCNAM;
    }

    public String getAPCTYP() {
        return APCTYP;
    }

    public void setAPCTYP(String APCTYP) {
        this.APCTYP = APCTYP;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getPLCODE() {
        return PLCODE;
    }

    public void setPLCODE(String PLCODE) {
        this.PLCODE = PLCODE;
    }

    public String getINTEXP() {
        return INTEXP;
    }

    public void setINTEXP(String INTEXP) {
        this.INTEXP = INTEXP;
    }

    public String getINTINC() {
        return INTINC;
    }

    public void setINTINC(String INTINC) {
        this.INTINC = INTINC;
    }

    public String getTRNIDT() {
        return TRNIDT;
    }

    public void setTRNIDT(String TRNIDT) {
        this.TRNIDT = TRNIDT;
    }

    public String getFEEAPC() {
        return FEEAPC;
    }

    public void setFEEAPC(String FEEAPC) {
        this.FEEAPC = FEEAPC;
    }

    public String getPDCTYP() {
        return PDCTYP;
    }

    public void setPDCTYP(String PDCTYP) {
        this.PDCTYP = PDCTYP;
    }

    public String getEBKCDE() {
        return EBKCDE;
    }

    public void setEBKCDE(String EBKCDE) {
        this.EBKCDE = EBKCDE;
    }

    public String getOPSAPC() {
        return OPSAPC;
    }

    public void setOPSAPC(String OPSAPC) {
        this.OPSAPC = OPSAPC;
    }

    public String getAPCDCR() {
        return APCDCR;
    }

    public void setAPCDCR(String APCDCR) {
        this.APCDCR = APCDCR;
    }

    public String getINTDAC() {
        return INTDAC;
    }

    public void setINTDAC(String INTDAC) {
        this.INTDAC = INTDAC;
    }

    public String getINTCAC() {
        return INTCAC;
    }

    public void setINTCAC(String INTCAC) {
        this.INTCAC = INTCAC;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getDEGNUM() {
        return DEGNUM;
    }

    public void setDEGNUM(String DEGNUM) {
        this.DEGNUM = DEGNUM;
    }

    public String getCLRFLG() {
        return CLRFLG;
    }

    public void setCLRFLG(String CLRFLG) {
        this.CLRFLG = CLRFLG;
    }

    public String getTLRNCDE() {
        return TLRNCDE;
    }

    public void setTLRNCDE(String TLRNCDE) {
        this.TLRNCDE = TLRNCDE;
    }

    public String getMODFLG() {
        return MODFLG;
    }

    public void setMODFLG(String MODFLG) {
        this.MODFLG = MODFLG;
    }
}
