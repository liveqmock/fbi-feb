package gateway.sbs.txn.model.msg;

/**
 * 开户 - 8101
 */
public class M8101 extends MTia {

    private String BATSEQ = "111111";  // 平台顺序号
    private String ORGIDT = "010";     // 机构号
    private String DEPNUM = "60";      // 部门号
    private String ACTNUM = "";        // 14位账号(客户号+核算码+币别)
    private String ACTNAM = "";        // 账户名称
    private String STMFMT = "C";       // 账页形式 C.外部客户账页 S.共享账户账页 O.不出账页
    private String STMSHT = "001";     // 出账份数
    private String STMDEP = "01060";   // 对账单分发部门
    private String STMADD = "";        // 对账单地址
    private String STMZIP = "";        // 邮编
    private String STMCYC = "M";       // 对账单出账周期 D.天 T?旬 M?月 S.季 Y.年底 P.满页 E.有发生出 F.其它条件
    private String STMCDT = "0031";    // 出账日期
    private String ACTTYP = "3";       // 账户类型 1.定存账户 2.贷款账户 3.其它客户账户 4.其它系统综合账户
    private String DEPNU3 = "60";      // 部门号
    private String INTFLG = "4";        // 计息标志 1. 账户分段计息 2. 交易计息 3. 账户协定计息 4. 账户不分段计息 6. 903协定计息 0. 不计息
    private String INTCYC = "S";       // 计息周期 M?月 S?季 H?半年 Y?年 F?不定期
    private String INTTRA = "";        // 转息账户 18位账号，可以不输
    private String CQEFLG = "3";        // 支票/存折标志 选择输入	1?支票；2?存折；3?其它
    private String BALLIM = "000";     // 协定账户余额限度 金额到分，没有小数点
    private String OVELIM = "000";     // 透支限额 金额到分，没有小数点
    private String OVEEXP = "";        // 额度到期日 YYYYMMDD
    private String DINRAT = "";        // 借方利率代码  账户类型为贷款账户时,为必输项,贷款利率代码
    private String CINRAT = "";        // 贷方利率代码  账户类型为存款账户时,为必输项,存款利率代码
    private String DRATSF = "";        // 借方固定或浮动利率  贷款利率码自动带出利率值
    private String CRATSF = "";        // 贷方固定或浮动利率  存款利率码自动带出利率值
    private String VCHAUT = "";        // 主管代号
    private String LEGCYC = "M";       // 分户账出账周期 D.天 T?旬 M?月 S.季 Y.年底 P.满页 E.有发生出 F.其它条件
    private String LEGCDT = "0031";    // 分户账出账日期
    private String LEGFMT = "C";        // 分户账账页形式 C.外部客户账页 S.共享账户账页 O.不出账页
    private String LEGADD = "";        // 分户账地址
    private String LEGZIP = "100000";  // 账户用途 100000. 一般结算户 200000. 资金专用户 300000. 票据户 400000. 票据池账户 500000. 验资户 600000. 表外户 900000. 其他
    private String LEGSHT = "001";     // 分户账出账份数
    private String LEGDEP = "01060";   // 分户账分发部门
    private String GLCODE = "";        // 总账码      空
    private String FXRATE = "";        // 汇率        空
    private String LOCCAP = "";        // 注册外币资本  空
    private String TXNAMT = "";        // 交易金额     空

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

    public String getSTMFMT() {
        return STMFMT;
    }

    public void setSTMFMT(String STMFMT) {
        this.STMFMT = STMFMT;
    }

    public String getSTMSHT() {
        return STMSHT;
    }

    public void setSTMSHT(String STMSHT) {
        this.STMSHT = STMSHT;
    }

    public String getSTMDEP() {
        return STMDEP;
    }

    public void setSTMDEP(String STMDEP) {
        this.STMDEP = STMDEP;
    }

    public String getSTMADD() {
        return STMADD;
    }

    public void setSTMADD(String STMADD) {
        this.STMADD = STMADD;
    }

    public String getSTMZIP() {
        return STMZIP;
    }

    public void setSTMZIP(String STMZIP) {
        this.STMZIP = STMZIP;
    }

    public String getSTMCYC() {
        return STMCYC;
    }

    public void setSTMCYC(String STMCYC) {
        this.STMCYC = STMCYC;
    }

    public String getSTMCDT() {
        return STMCDT;
    }

    public void setSTMCDT(String STMCDT) {
        this.STMCDT = STMCDT;
    }

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public String getDEPNU3() {
        return DEPNU3;
    }

    public void setDEPNU3(String DEPNU3) {
        this.DEPNU3 = DEPNU3;
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

    public String getINTTRA() {
        return INTTRA;
    }

    public void setINTTRA(String INTTRA) {
        this.INTTRA = INTTRA;
    }

    public String getCQEFLG() {
        return CQEFLG;
    }

    public void setCQEFLG(String CQEFLG) {
        this.CQEFLG = CQEFLG;
    }

    public String getBALLIM() {
        return BALLIM;
    }

    public void setBALLIM(String BALLIM) {
        this.BALLIM = BALLIM;
    }

    public String getOVELIM() {
        return OVELIM;
    }

    public void setOVELIM(String OVELIM) {
        this.OVELIM = OVELIM;
    }

    public String getOVEEXP() {
        return OVEEXP;
    }

    public void setOVEEXP(String OVEEXP) {
        this.OVEEXP = OVEEXP;
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

    public String getVCHAUT() {
        return VCHAUT;
    }

    public void setVCHAUT(String VCHAUT) {
        this.VCHAUT = VCHAUT;
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

    public String getLEGFMT() {
        return LEGFMT;
    }

    public void setLEGFMT(String LEGFMT) {
        this.LEGFMT = LEGFMT;
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

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getFXRATE() {
        return FXRATE;
    }

    public void setFXRATE(String FXRATE) {
        this.FXRATE = FXRATE;
    }

    public String getLOCCAP() {
        return LOCCAP;
    }

    public void setLOCCAP(String LOCCAP) {
        this.LOCCAP = LOCCAP;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }
}
