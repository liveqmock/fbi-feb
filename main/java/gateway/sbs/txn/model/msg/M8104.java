package gateway.sbs.txn.model.msg;

/**
 * 内部账户开户 - 8104
 */
public class M8104 extends MTia {

    private String BATSEQ = "111111";  // 平台顺序号
    private String ORGIDT = "010";     // 机构号
    private String DEPNUM = "60";      // 部门号
    private String ACTNUM = "";        // 14位账号(客户号+核算码+币别)
    private String ACTNAM = "";        // 账户名称
    private String LEGFMT = "C";        // 分户账账页形式 C.外部客户账页 S.共享账户账页 O.不出账页
    private String LEGSHT = "001";     // 分户账出账份数
    private String LEGDEP = "01060";   // 分户账分发部门
    private String LEGADD = "";        // 分户账地址
    private String LEGZIP = "900000";  // 账户用途 100000. 一般结算户 200000. 资金专用户 300000. 票据户 400000. 票据池账户 500000. 验资户 600000. 表外户 900000. 其他
    private String LEGCYC = "M";       // 分户账出账周期 D.天 T?旬 M?月 S.季 Y.年底 P.满页 E.有发生出 F.其它条件
    private String LEGCDT = "0031";    // 分户账出账日期
    private String ACTTYP = "9";       // 账户类型 5. 外汇买卖帐户  6. 损益帐户 7. 卡片帐帐户 9. 其它内部客户
    private String INTFLG = "0";        // 计息标志 1. 账户分段计息 2. 交易计息 3. 账户协定计息 4. 账户不分段计息 6. 903协定计息 0. 不计息
    private String INTCYC = "S";       // 计息周期 M?月 S?季 H?半年 Y?年 F?不定期
    private String DINRAT = "";        // 借方利率代码  账户类型为贷款账户时,为必输项,贷款利率代码
    private String CINRAT = "";        // 贷方利率代码  账户类型为存款账户时,为必输项,存款利率代码
    private String DRATSF = "";        // 借方固定或浮动利率  贷款利率码自动带出利率值
    private String CRATSF = "";        // 贷方固定或浮动利率  存款利率码自动带出利率值
    private String DEPNU3 = "60";      // 部门号
    private String VCHAUT = "";        // 主管代号
    private String LOCCAP = "";        // 注册外币资本  空
    private String INTTRA = "";        // 转息账户 18位账号，可以不输
    private String TXNAMT = "";        // 交易金额     空
    private String GLCODE = "";        // 总账码      空

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

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getLEGFMT() {
        return LEGFMT;
    }

    public void setLEGFMT(String LEGFMT) {
        this.LEGFMT = LEGFMT;
    }

    public String getLEGSHT() {
        return LEGSHT;
    }

    public void setLEGSHT(String LEGSHT) {
        this.LEGSHT = LEGSHT;
    }

    public String getLEGDEP() {
        return LEGDEP;
    }

    public void setLEGDEP(String LEGDEP) {
        this.LEGDEP = LEGDEP;
    }

    public String getLEGADD() {
        return LEGADD;
    }

    public void setLEGADD(String LEGADD) {
        this.LEGADD = LEGADD;
    }

    public String getLEGZIP() {
        return LEGZIP;
    }

    public void setLEGZIP(String LEGZIP) {
        this.LEGZIP = LEGZIP;
    }

    public String getLEGCYC() {
        return LEGCYC;
    }

    public void setLEGCYC(String LEGCYC) {
        this.LEGCYC = LEGCYC;
    }

    public String getLEGCDT() {
        return LEGCDT;
    }

    public void setLEGCDT(String LEGCDT) {
        this.LEGCDT = LEGCDT;
    }

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public String getINTFLG() {
        return INTFLG;
    }

    public void setINTFLG(String INTFLG) {
        this.INTFLG = INTFLG;
    }

    public String getINTCYC() {
        return INTCYC;
    }

    public void setINTCYC(String INTCYC) {
        this.INTCYC = INTCYC;
    }

    public String getDINRAT() {
        return DINRAT;
    }

    public void setDINRAT(String DINRAT) {
        this.DINRAT = DINRAT;
    }

    public String getCINRAT() {
        return CINRAT;
    }

    public void setCINRAT(String CINRAT) {
        this.CINRAT = CINRAT;
    }

    public String getDRATSF() {
        return DRATSF;
    }

    public void setDRATSF(String DRATSF) {
        this.DRATSF = DRATSF;
    }

    public String getCRATSF() {
        return CRATSF;
    }

    public void setCRATSF(String CRATSF) {
        this.CRATSF = CRATSF;
    }

    public String getDEPNU3() {
        return DEPNU3;
    }

    public void setDEPNU3(String DEPNU3) {
        this.DEPNU3 = DEPNU3;
    }

    public String getVCHAUT() {
        return VCHAUT;
    }

    public void setVCHAUT(String VCHAUT) {
        this.VCHAUT = VCHAUT;
    }

    public String getLOCCAP() {
        return LOCCAP;
    }

    public void setLOCCAP(String LOCCAP) {
        this.LOCCAP = LOCCAP;
    }

    public String getINTTRA() {
        return INTTRA;
    }

    public void setINTTRA(String INTTRA) {
        this.INTTRA = INTTRA;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }
}
