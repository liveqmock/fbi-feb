package gateway.sbs.txn.model.msg;

/**
 * 利率单笔查询、增删改
 */
public class M9804 extends MTia {

    private String CURCDE;       // 币别
    private String IRTCDE;       // 利率码
    private String EFFDAT;       // 启用日期
    private String IRTNAM = "";  // 利率名称
    private String CURFLG = "0"; // 是否当前利率   0-否，1-是
    private String MODFLG = "0"; // 当前修改标志  0-不修改，1-修改
    private String IRTVAL = "";  // 基准利率值
    private String IRTSPH = "";  // 浮动上限值
    private String IRTSPL = "";  // 浮动下限值
    private String SPRFLG = "";  // 浮动标志
    private String CDFLAG = "";  // 借贷标志  D-借，C-贷，B-双方使用
    private String IRTTRM = "";  // 期限
    private String TRMUNT = "";  // 期限单位
    private String FUNCDE;       // 操作类别 0-单笔查询,2-修改, 3-删除, 4-增加

    public M9804() {
    }

    public M9804(String CURCDE, String IRTCDE, String EFFDAT, String FUNCDE) {
        this.CURCDE = CURCDE;
        this.IRTCDE = IRTCDE;
        this.EFFDAT = EFFDAT;
        this.FUNCDE = FUNCDE;
    }

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getIRTCDE() {
        return IRTCDE;
    }

    public void setIRTCDE(String IRTCDE) {
        this.IRTCDE = IRTCDE;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public String getIRTNAM() {
        return IRTNAM;
    }

    public void setIRTNAM(String IRTNAM) {
        this.IRTNAM = IRTNAM;
    }

    public String getCURFLG() {
        return CURFLG;
    }

    public void setCURFLG(String CURFLG) {
        this.CURFLG = CURFLG;
    }

    public String getMODFLG() {
        return MODFLG;
    }

    public void setMODFLG(String MODFLG) {
        this.MODFLG = MODFLG;
    }

    public String getIRTVAL() {
        return IRTVAL;
    }

    public void setIRTVAL(String IRTVAL) {
        this.IRTVAL = IRTVAL;
    }

    public String getIRTSPH() {
        return IRTSPH;
    }

    public void setIRTSPH(String IRTSPH) {
        this.IRTSPH = IRTSPH;
    }

    public String getIRTSPL() {
        return IRTSPL;
    }

    public void setIRTSPL(String IRTSPL) {
        this.IRTSPL = IRTSPL;
    }

    public String getSPRFLG() {
        return SPRFLG;
    }

    public void setSPRFLG(String SPRFLG) {
        this.SPRFLG = SPRFLG;
    }

    public String getCDFLAG() {
        return CDFLAG;
    }

    public void setCDFLAG(String CDFLAG) {
        this.CDFLAG = CDFLAG;
    }

    public String getIRTTRM() {
        return IRTTRM;
    }

    public void setIRTTRM(String IRTTRM) {
        this.IRTTRM = IRTTRM;
    }

    public String getTRMUNT() {
        return TRMUNT;
    }

    public void setTRMUNT(String TRMUNT) {
        this.TRMUNT = TRMUNT;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }
}
