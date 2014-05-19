package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-19
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public class M9a24 extends MTia {

    private String BATSEQ = "111111";      //平台顺序号
    private String ORGIDT = "010";      //机构号
    private String DEPNUM = "60";      //部门号
    private String ATRCDE = "";      //自动转帐码
    private String ATRNAM = "";      //名称
    private String CYCCDE = "";      //转帐周期
    private String CYCDAT = "";      //转帐日期
    private String TRICDE = "";      //启动条件码
    private String TRIBAL = "";      //启动金额
    private String FEXCDE = "";      //外汇买卖码
    private String BASRAT = "";      //基数比例1
    private String BASRT2 = "";      //基数比例2
    private String BASRT3 = "";      //基数比例3
    private String BASRT4 = "";      //基数比例4
    private String BASRT5 = "";      //基数比例5
    private String CURRAG = "";      //适用货币范围
    private String CUSIDT = "";      //特殊引用客户号
    private String APCODE = "";      //特殊引用核算码
    private String CURCDE = "";      //特殊引用币别
    private String PRDCDE = "";      //产品码
    private String EVTCDE = "";      //事件码
    private String CTPCDE = "";      //客户类型码
    private String CATCDE = "";      //细分类码
    private String SPECD1 = "";      //特别代码1
    private String SPECD2 = "";      //特别代码2
    private String SPECD3 = "";      //特别代码3
    private String PROFLG = "";      //处理区分
    private String ATRDCR = "";      //描述
    private String FUNCDE = "";      //操作类别
    private String BEGNUM = "";      //起始笔数

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

    public String getATRCDE() {
        return ATRCDE;
    }

    public void setATRCDE(String ATRCDE) {
        this.ATRCDE = ATRCDE;
    }

    public String getATRNAM() {
        return ATRNAM;
    }

    public void setATRNAM(String ATRNAM) {
        this.ATRNAM = ATRNAM;
    }

    public String getCYCCDE() {
        return CYCCDE;
    }

    public void setCYCCDE(String CYCCDE) {
        this.CYCCDE = CYCCDE;
    }

    public String getCYCDAT() {
        return CYCDAT;
    }

    public void setCYCDAT(String CYCDAT) {
        this.CYCDAT = CYCDAT;
    }

    public String getTRICDE() {
        return TRICDE;
    }

    public void setTRICDE(String TRICDE) {
        this.TRICDE = TRICDE;
    }

    public String getTRIBAL() {
        return TRIBAL;
    }

    public void setTRIBAL(String TRIBAL) {
        this.TRIBAL = TRIBAL;
    }

    public String getFEXCDE() {
        return FEXCDE;
    }

    public void setFEXCDE(String FEXCDE) {
        this.FEXCDE = FEXCDE;
    }

    public String getBASRAT() {
        return BASRAT;
    }

    public void setBASRAT(String BASRAT) {
        this.BASRAT = BASRAT;
    }

    public String getBASRT2() {
        return BASRT2;
    }

    public void setBASRT2(String BASRT2) {
        this.BASRT2 = BASRT2;
    }

    public String getBASRT3() {
        return BASRT3;
    }

    public void setBASRT3(String BASRT3) {
        this.BASRT3 = BASRT3;
    }

    public String getBASRT4() {
        return BASRT4;
    }

    public void setBASRT4(String BASRT4) {
        this.BASRT4 = BASRT4;
    }

    public String getBASRT5() {
        return BASRT5;
    }

    public void setBASRT5(String BASRT5) {
        this.BASRT5 = BASRT5;
    }

    public String getCURRAG() {
        return CURRAG;
    }

    public void setCURRAG(String CURRAG) {
        this.CURRAG = CURRAG;
    }

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getPRDCDE() {
        return PRDCDE;
    }

    public void setPRDCDE(String PRDCDE) {
        this.PRDCDE = PRDCDE;
    }

    public String getEVTCDE() {
        return EVTCDE;
    }

    public void setEVTCDE(String EVTCDE) {
        this.EVTCDE = EVTCDE;
    }

    public String getCTPCDE() {
        return CTPCDE;
    }

    public void setCTPCDE(String CTPCDE) {
        this.CTPCDE = CTPCDE;
    }

    public String getCATCDE() {
        return CATCDE;
    }

    public void setCATCDE(String CATCDE) {
        this.CATCDE = CATCDE;
    }

    public String getSPECD1() {
        return SPECD1;
    }

    public void setSPECD1(String SPECD1) {
        this.SPECD1 = SPECD1;
    }

    public String getSPECD2() {
        return SPECD2;
    }

    public void setSPECD2(String SPECD2) {
        this.SPECD2 = SPECD2;
    }

    public String getSPECD3() {
        return SPECD3;
    }

    public void setSPECD3(String SPECD3) {
        this.SPECD3 = SPECD3;
    }

    public String getPROFLG() {
        return PROFLG;
    }

    public void setPROFLG(String PROFLG) {
        this.PROFLG = PROFLG;
    }

    public String getATRDCR() {
        return ATRDCR;
    }

    public void setATRDCR(String ATRDCR) {
        this.ATRDCR = ATRDCR;
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
