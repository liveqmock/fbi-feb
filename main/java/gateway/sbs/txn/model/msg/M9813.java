package gateway.sbs.txn.model.msg;

/**
 * 总账码单笔查询、增删改
 */
public class M9813 extends MTia {




    private String BATSEQ = "111111";  // 平台顺序号 // 数据长度
    private String ORGIDT = "010";     // 机构号 // 机构号
    private String DEPNUM = "60";      // 部门号 // 部门号
    private String GLCODE = "";          // 总账码
    private String GLCNAM = "";          // 总账码名称
    private String GLCCLS = "";          // 科目级别
    private String GLCGRP = "";          // 并入科目
    private String GLCTYP = "";          // 科目属性
    private String GLCCAT = "";          // 科目类别
    private String GLCCCY = "";          // 允许货币标志
    private String GLCBAL = "";          // 余额方向标志
    private String GLCOCC = "";          // 发生额方向标志
    private String GLCINT = "";          // 记息种类
    private String GLCRAT = "";          // 利率种类
    private String GLCOPN = "";          // 自动开户允许标志
    private String GLCRVS = "";          // 冲帐允许标志
    private String RSVFG1 = "";          // 备用标志1
    private String RSVFG2 = "";          // 备用标志2
    private String RSVFG3 = "";          // 备用标志3
    private String RSVFG4 = "";          // 备用标志4
    private String RSVFG5 = "";          // 备用标志5
    private String GLCBEL = "";          // 科目所属
    private String EFFDAT = "";          // 科目启用日期
    private String EXPDAT = "";          // 科目使用到期日
    private String FUNCDE = "";          //功功能码
    private String BEGNUM = "1";          //开始序号
    private String RSVRF3 = "";          //引用号

    public M9813() {
    }

    public M9813(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getBATSEQ() {
        return BATSEQ;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public String getGLCNAM() {
        return GLCNAM;
    }

    public String getGLCCLS() {
        return GLCCLS;
    }

    public String getGLCGRP() {
        return GLCGRP;
    }

    public String getGLCTYP() {
        return GLCTYP;
    }

    public String getGLCCAT() {
        return GLCCAT;
    }

    public String getGLCCCY() {
        return GLCCCY;
    }

    public String getGLCBAL() {
        return GLCBAL;
    }

    public String getGLCOCC() {
        return GLCOCC;
    }

    public String getGLCINT() {
        return GLCINT;
    }

    public String getGLCRAT() {
        return GLCRAT;
    }

    public String getGLCOPN() {
        return GLCOPN;
    }

    public String getGLCRVS() {
        return GLCRVS;
    }

    public String getRSVFG1() {
        return RSVFG1;
    }

    public String getRSVFG2() {
        return RSVFG2;
    }

    public String getRSVFG3() {
        return RSVFG3;
    }

    public String getRSVFG4() {
        return RSVFG4;
    }

    public String getRSVFG5() {
        return RSVFG5;
    }

    public String getGLCBEL() {
        return GLCBEL;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public String getEXPDAT() {
        return EXPDAT;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public String getRSVRF3() {
        return RSVRF3;
    }

    public void setBATSEQ(String BATSEQ) {
        this.BATSEQ = BATSEQ;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public void setDEPNUM(String DEPNUM) {
        this.DEPNUM = DEPNUM;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public void setGLCNAM(String GLCNAM) {
        this.GLCNAM = GLCNAM;
    }

    public void setGLCCLS(String GLCCLS) {
        this.GLCCLS = GLCCLS;
    }

    public void setGLCGRP(String GLCGRP) {
        this.GLCGRP = GLCGRP;
    }

    public void setGLCTYP(String GLCTYP) {
        this.GLCTYP = GLCTYP;
    }

    public void setGLCCAT(String GLCCAT) {
        this.GLCCAT = GLCCAT;
    }

    public void setGLCCCY(String GLCCCY) {
        this.GLCCCY = GLCCCY;
    }

    public void setGLCBAL(String GLCBAL) {
        this.GLCBAL = GLCBAL;
    }

    public void setGLCOCC(String GLCOCC) {
        this.GLCOCC = GLCOCC;
    }

    public void setGLCINT(String GLCINT) {
        this.GLCINT = GLCINT;
    }

    public void setGLCRAT(String GLCRAT) {
        this.GLCRAT = GLCRAT;
    }

    public void setGLCOPN(String GLCOPN) {
        this.GLCOPN = GLCOPN;
    }

    public void setGLCRVS(String GLCRVS) {
        this.GLCRVS = GLCRVS;
    }

    public void setRSVFG1(String RSVFG1) {
        this.RSVFG1 = RSVFG1;
    }

    public void setRSVFG2(String RSVFG2) {
        this.RSVFG2 = RSVFG2;
    }

    public void setRSVFG3(String RSVFG3) {
        this.RSVFG3 = RSVFG3;
    }

    public void setRSVFG4(String RSVFG4) {
        this.RSVFG4 = RSVFG4;
    }

    public void setRSVFG5(String RSVFG5) {
        this.RSVFG5 = RSVFG5;
    }

    public void setGLCBEL(String GLCBEL) {
        this.GLCBEL = GLCBEL;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public void setEXPDAT(String EXPDAT) {
        this.EXPDAT = EXPDAT;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }

    public void setRSVRF3(String RSVRF3) {
        this.RSVRF3 = RSVRF3;
    }
}
