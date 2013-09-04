package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * 8109 关闭客户账户前的账户查询 响应报文
 */
public class T109 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 7, 4, 3, 72, 3, 3, 10, 10, 1, 1, 18, 1, 4, 1, 3, 42, 6, 5, 1, 4, 1, 3, 42, 6, 5, 2, 8, 1, 21, 21, 8, 1, 10, 4};
    }

    private String ORGIDT;          // 机构号
    private String CUSIDT;          // 客户号
    private String APCODE;          // 核算码
    private String CURCDE;          // 币别码
    private String ACTNAM;          // 账户名称
    private String DINRAT;          // 借方利率代码  账户类型为贷款账户时,为必输项,贷款利率代码
    private String CINRAT;          // 贷方利率代码  账户类型为存款账户时,为必输项,存款利率代码
    private String DRATSF;          // 借方固定或浮动利率  贷款利率码自动带出利率值
    private String CRATSF;          // 贷方固定或浮动利率  存款利率码自动带出利率值
    private String INTFLG;          // 计息标志 1. 账户分段计息 2. 交易计息 3. 账户协定计息 4. 账户不分段计息 6. 903协定计息 0. 不计息
    private String INTCYC;          // 计息周期 M?月 S?季 H?半年 Y?年 F?不定期
    private String INTTRA;          // 转息账户
    private String STMCYC;          // 对账单出账周期 D.天 T?旬 M?月 S.季 Y.年底 P.满页 E.有发生出 F.其它条件
    private String STMCDT;          // 出账日期
    private String STMFMT;          // 账页形式 C.外部客户账页 S.共享账户账页 O.不出账页
    private String STMSHT;          // 出账份数
    private String STMADD;          // 对账单地址
    private String STMZIP;          // 邮编
    private String STMDEP;          // 对账单分发部门
    private String LEGCYC;          // 分户账出账周期 D.天 T?旬 M?月 S.季 Y.年底 P.满页 E.有发生出 F.其它条件
    private String LEGCDT;          // 分户账出账日期
    private String LEGFMT;          // 分户账账页形式 C.外部客户账页 S.共享账户账页 O.不出账页
    private String LEGSHT;          // 分户账出账份数
    private String LEGADD;          // 分户账地址
    private String LEGZIP;          // 账户用途 100000. 一般结算户 200000. 资金专用户 300000. 票据户 400000. 票据池账户 500000. 验资户 600000. 表外户 900000. 其他
    private String LEGDEP;          // 分户账分发部门
    private String DEPNUM;          // 部门号
    private String OPNDAT;          // 建立日期
    private String CQEFLG;          // 支票/存折标志 选择输入	1?支票；2?存折；3?其它
    private String BALLIM;          // 协定账户余额限度 金额到分，没有小数点
    private String OVELIM;          // 透支限额 金额到分，没有小数点
    private String OVEEXP;          // 额度到期日 YYYYMMDD
    private String ACTTYP;          // 账户类型
    private String CRIRAT;          // 协定利率
    private String GLCODE;          // 总账码

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
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

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
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

    public String getSTMDEP() {
        return STMDEP;
    }

    public void setSTMDEP(String STMDEP) {
        this.STMDEP = STMDEP;
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

    public String getLEGSHT() {
        return LEGSHT;
    }

    public void setLEGSHT(String LEGSHT) {
        this.LEGSHT = LEGSHT;
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

    public String getLEGDEP() {
        return LEGDEP;
    }

    public void setLEGDEP(String LEGDEP) {
        this.LEGDEP = LEGDEP;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public void setDEPNUM(String DEPNUM) {
        this.DEPNUM = DEPNUM;
    }

    public String getOPNDAT() {
        return OPNDAT;
    }

    public void setOPNDAT(String OPNDAT) {
        this.OPNDAT = OPNDAT;
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

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public String getCRIRAT() {
        return CRIRAT;
    }

    public void setCRIRAT(String CRIRAT) {
        this.CRIRAT = CRIRAT;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }
}
