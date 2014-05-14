package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-13       币别码查询
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
public class M9a01 extends MTia {

   private String BATSEQ = "111111";    //平台顺序号
   private String ORGIDT = "010";    //机构号
   private String DEPNUM = "60";    //部门号
   private String CURCDE = "";    //币别码
   private String CURNMC = "";    //中文货币名称
   private String CURNME = "";    //英文货币名称
   private String DOMCDE = "";    //国内货币符号
   private String INTCUR = "";    //国际标准货币符号
   private String CURUNT = "";    //折算汇率单位
   private String DECPOS = "";    //辅币位数
   private String EXCFLG = "";    //现钞兑换标志
   private String EXCUNT = "";    //现钞兑换最小单位
   private String MINUNT = "";    //最小零钞单位
   private String CUTFLG = "";    //取舍进位标志
   private String SPEFLG = "";    //特殊使用标志(保留备用)
   private String HOLFLG = "";    //假日表标志
   private String CLRFLG = "";    //记帐货币标记
   private String EFFDAT = "";    //启用日期
   private String EXPDAT = "";    //使用到期日
   private String FUNCDE = "";    //功能参数
   private String BEGNUM = "";    //起始序号

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

    public String getCURNMC() {
        return CURNMC;
    }

    public void setCURNMC(String CURNMC) {
        this.CURNMC = CURNMC;
    }

    public String getCURNME() {
        return CURNME;
    }

    public void setCURNME(String CURNME) {
        this.CURNME = CURNME;
    }

    public String getDOMCDE() {
        return DOMCDE;
    }

    public void setDOMCDE(String DOMCDE) {
        this.DOMCDE = DOMCDE;
    }

    public String getINTCUR() {
        return INTCUR;
    }

    public void setINTCUR(String INTCUR) {
        this.INTCUR = INTCUR;
    }

    public String getCURUNT() {
        return CURUNT;
    }

    public void setCURUNT(String CURUNT) {
        this.CURUNT = CURUNT;
    }

    public String getDECPOS() {
        return DECPOS;
    }

    public void setDECPOS(String DECPOS) {
        this.DECPOS = DECPOS;
    }

    public String getEXCFLG() {
        return EXCFLG;
    }

    public void setEXCFLG(String EXCFLG) {
        this.EXCFLG = EXCFLG;
    }

    public String getEXCUNT() {
        return EXCUNT;
    }

    public void setEXCUNT(String EXCUNT) {
        this.EXCUNT = EXCUNT;
    }

    public String getMINUNT() {
        return MINUNT;
    }

    public void setMINUNT(String MINUNT) {
        this.MINUNT = MINUNT;
    }

    public String getCUTFLG() {
        return CUTFLG;
    }

    public void setCUTFLG(String CUTFLG) {
        this.CUTFLG = CUTFLG;
    }

    public String getSPEFLG() {
        return SPEFLG;
    }

    public void setSPEFLG(String SPEFLG) {
        this.SPEFLG = SPEFLG;
    }

    public String getHOLFLG() {
        return HOLFLG;
    }

    public void setHOLFLG(String HOLFLG) {
        this.HOLFLG = HOLFLG;
    }

    public String getCLRFLG() {
        return CLRFLG;
    }

    public void setCLRFLG(String CLRFLG) {
        this.CLRFLG = CLRFLG;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public String getEXPDAT() {
        return EXPDAT;
    }

    public void setEXPDAT(String EXPDAT) {
        this.EXPDAT = EXPDAT;
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
