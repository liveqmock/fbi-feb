package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * 8108 查询账户信息[各种账户] 响应报文
 */
public class T108 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                               1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                               1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                               1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 14, 72, 21, 21, 21, 21, 21, 21, 21, 21,
                                 9, 9, 21, 21, 9, 9, 21, 21, 9, 9, 3,
                                 3, 10, 10, 21, 21, 21, 21, 1, 1, 18, 8,
                                 8, 1, 4, 1, 3, 5, 42, 6, 1, 4, 1,
                                 3, 5, 42, 6, 1, 4, 4, 2, 8, 24, 24,
                                 1, 21, 21, 8, 8, 8, 1, 1, 1, 4, 8, 1};
    }

    private String ORGIDT;          // 机构号
    private String ACTNUM;          // 账号
    private String ACTNAM;          // 账户名称
    private String BOKBAL;          // 帐户余额
    private String AVABAL;          // 有效余额
    private String DIFBAL;          // 借方不计息余额
    private String CIFBAL;          // 贷方不计息余额
    private String MAVBAL;          // 月平均余额
    private String YAVBAL;          // 年平均余额
    private String DDRAMT;          // 本日借方发生额
    private String DCRAMT;          // 本日贷方发生额
    private String DDRCNT;          // 本日借方发生数
    private String DCRCNT;          // 本日贷方发生数
    private String MDRAMT;          // 月累计借方发生额
    private String MCRAMT;          // 月累计贷方发生额
    private String MDRCNT;          // 月累计借方发生数
    private String MCRCNT;          // 月累计贷方发生数
    private String YDRAMT;          // 年累计借方发生额
    private String YCRAMT;          // 年累计贷方发生额
    private String YDRCNT;          // 年累计借方发生数
    private String YCRCNT;          // 年累计贷方发生数
    private String DINRAT;          // 借方利率代码
    private String CINRAT;          // 贷方利率代码
    private String DRATSF;          // 借方固定或浮动利率
    private String CRATSF;          // 贷方固定或浮动利率
    private String DACINT;          // 应付利息
    private String CACINT;          // 应收利息
    private String DAMINT;          // 已摊销应付利息
    private String CAMINT;          // 已摊销应收利息
    private String INTFLG;          // 计息标志 1. 账户分段计息 2. 交易计息 3. 账户协定计息 4. 账户不分段计息 6. 903协定计息 0. 不计息
    private String INTCYC;          // 计息周期 M 月 S 季 H 半年 Y 年 F 不定期
    private String INTTRA;          // 转息账户
    private String LINTDT;          // 末次计息日期
    private String LINDTH;          // 末次转积数历史日期
    private String STMCYC;          // 对账单出账周期 D.天 T?旬 M?月 S.季 Y.年底 P.满页 E.有发生出 F.其它条件
    private String STMCDT;          // 出账日期
    private String STMFMT;          // 账页形式 C.外部客户账页 S.共享账户账页 O.不出账页
    private String STMSHT;          // 出账份数
    private String STMDEP;          // 对账单分发部门
    private String STMADD;          // 对账单地址
    private String STMZIP;          // 邮编
    private String LEGCYC;          // 分户账出账周期 D.天 T?旬 M?月 S.季 Y.年底 P.满页 E.有发生出 F.其它条件
    private String LEGCDT;          // 分户账出账日期
    private String LEGFMT;          // 分户账账页形式 C.外部客户账页 S.共享账户账页 O.不出账页
    private String LEGSHT;          // 分户账出账份数
    private String LEGDEP;          // 分户账分发部门
    private String LEGADD;          // 分户账地址
    private String LEGZIP;          // 账户用途 100000. 一般结算户 200000. 资金专用户 300000. 票据户 400000. 票据池账户 500000. 验资户 600000. 表外户 900000. 其他
    private String ACTTYP;          // 帐户类型
    private String ACTGLC;          // 总账码
    private String ACTPLC;          // 损益码
    private String DEPNUM;          // 部门号
    private String LENTDT;          // 末次记账日期
    private String DRACCM;          // 本计息段借方积数
    private String CRACCM;          // 本计息段贷方积数
    private String CQEFLG;          // 支票/存折标志 选择输入	1?支票；2?存折；3?其它
    private String BALLIM;          // 协定账户余额限度 金额到分，没有小数点
    private String OVELIM;          // 透支限额 金额到分，没有小数点
    private String OVEEXP;          // 额度到期日 YYYYMMDD
    private String OPNDAT;          // 开户日期
    private String CLSDAT;          // 销户日期
    private String REGSTS;          // 挂失状态
    private String FRZSTS;          // 冻结状态
    private String ACTSTS;          // 帐户状态
    private String AMDTLR;          // 修改柜员
    private String UPDDAT;          // 最后修改日期
    private String RECSTS;          // 记录状态

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
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

    public String getBOKBAL() {
        return BOKBAL;
    }

    public void setBOKBAL(String BOKBAL) {
        this.BOKBAL = BOKBAL;
    }

    public String getAVABAL() {
        return AVABAL;
    }

    public void setAVABAL(String AVABAL) {
        this.AVABAL = AVABAL;
    }

    public String getDIFBAL() {
        return DIFBAL;
    }

    public void setDIFBAL(String DIFBAL) {
        this.DIFBAL = DIFBAL;
    }

    public String getCIFBAL() {
        return CIFBAL;
    }

    public void setCIFBAL(String CIFBAL) {
        this.CIFBAL = CIFBAL;
    }

    public String getMAVBAL() {
        return MAVBAL;
    }

    public void setMAVBAL(String MAVBAL) {
        this.MAVBAL = MAVBAL;
    }

    public String getYAVBAL() {
        return YAVBAL;
    }

    public void setYAVBAL(String YAVBAL) {
        this.YAVBAL = YAVBAL;
    }

    public String getDDRAMT() {
        return DDRAMT;
    }

    public void setDDRAMT(String DDRAMT) {
        this.DDRAMT = DDRAMT;
    }

    public String getDCRAMT() {
        return DCRAMT;
    }

    public void setDCRAMT(String DCRAMT) {
        this.DCRAMT = DCRAMT;
    }

    public String getDDRCNT() {
        return DDRCNT;
    }

    public void setDDRCNT(String DDRCNT) {
        this.DDRCNT = DDRCNT;
    }

    public String getDCRCNT() {
        return DCRCNT;
    }

    public void setDCRCNT(String DCRCNT) {
        this.DCRCNT = DCRCNT;
    }

    public String getMDRAMT() {
        return MDRAMT;
    }

    public void setMDRAMT(String MDRAMT) {
        this.MDRAMT = MDRAMT;
    }

    public String getMCRAMT() {
        return MCRAMT;
    }

    public void setMCRAMT(String MCRAMT) {
        this.MCRAMT = MCRAMT;
    }

    public String getMDRCNT() {
        return MDRCNT;
    }

    public void setMDRCNT(String MDRCNT) {
        this.MDRCNT = MDRCNT;
    }

    public String getMCRCNT() {
        return MCRCNT;
    }

    public void setMCRCNT(String MCRCNT) {
        this.MCRCNT = MCRCNT;
    }

    public String getYDRAMT() {
        return YDRAMT;
    }

    public void setYDRAMT(String YDRAMT) {
        this.YDRAMT = YDRAMT;
    }

    public String getYCRAMT() {
        return YCRAMT;
    }

    public void setYCRAMT(String YCRAMT) {
        this.YCRAMT = YCRAMT;
    }

    public String getYDRCNT() {
        return YDRCNT;
    }

    public void setYDRCNT(String YDRCNT) {
        this.YDRCNT = YDRCNT;
    }

    public String getYCRCNT() {
        return YCRCNT;
    }

    public void setYCRCNT(String YCRCNT) {
        this.YCRCNT = YCRCNT;
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

    public String getDACINT() {
        return DACINT;
    }

    public void setDACINT(String DACINT) {
        this.DACINT = DACINT;
    }

    public String getCACINT() {
        return CACINT;
    }

    public void setCACINT(String CACINT) {
        this.CACINT = CACINT;
    }

    public String getDAMINT() {
        return DAMINT;
    }

    public void setDAMINT(String DAMINT) {
        this.DAMINT = DAMINT;
    }

    public String getCAMINT() {
        return CAMINT;
    }

    public void setCAMINT(String CAMINT) {
        this.CAMINT = CAMINT;
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

    public String getLINTDT() {
        return LINTDT;
    }

    public void setLINTDT(String LINTDT) {
        this.LINTDT = LINTDT;
    }

    public String getLINDTH() {
        return LINDTH;
    }

    public void setLINDTH(String LINDTH) {
        this.LINDTH = LINDTH;
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

    public String getACTTYP() {
        return ACTTYP;
    }

    public void setACTTYP(String ACTTYP) {
        this.ACTTYP = ACTTYP;
    }

    public String getACTGLC() {
        return ACTGLC;
    }

    public void setACTGLC(String ACTGLC) {
        this.ACTGLC = ACTGLC;
    }

    public String getACTPLC() {
        return ACTPLC;
    }

    public void setACTPLC(String ACTPLC) {
        this.ACTPLC = ACTPLC;
    }

    public String getDEPNUM() {
        return DEPNUM;
    }

    public void setDEPNUM(String DEPNUM) {
        this.DEPNUM = DEPNUM;
    }

    public String getLENTDT() {
        return LENTDT;
    }

    public void setLENTDT(String LENTDT) {
        this.LENTDT = LENTDT;
    }

    public String getDRACCM() {
        return DRACCM;
    }

    public void setDRACCM(String DRACCM) {
        this.DRACCM = DRACCM;
    }

    public String getCRACCM() {
        return CRACCM;
    }

    public void setCRACCM(String CRACCM) {
        this.CRACCM = CRACCM;
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

    public String getOPNDAT() {
        return OPNDAT;
    }

    public void setOPNDAT(String OPNDAT) {
        this.OPNDAT = OPNDAT;
    }

    public String getCLSDAT() {
        return CLSDAT;
    }

    public void setCLSDAT(String CLSDAT) {
        this.CLSDAT = CLSDAT;
    }

    public String getREGSTS() {
        return REGSTS;
    }

    public void setREGSTS(String REGSTS) {
        this.REGSTS = REGSTS;
    }

    public String getFRZSTS() {
        return FRZSTS;
    }

    public void setFRZSTS(String FRZSTS) {
        this.FRZSTS = FRZSTS;
    }

    public String getACTSTS() {
        return ACTSTS;
    }

    public void setACTSTS(String ACTSTS) {
        this.ACTSTS = ACTSTS;
    }

    public String getAMDTLR() {
        return AMDTLR;
    }

    public void setAMDTLR(String AMDTLR) {
        this.AMDTLR = AMDTLR;
    }

    public String getUPDDAT() {
        return UPDDAT;
    }

    public void setUPDDAT(String UPDDAT) {
        this.UPDDAT = UPDDAT;
    }

    public String getRECSTS() {
        return RECSTS;
    }

    public void setRECSTS(String RECSTS) {
        this.RECSTS = RECSTS;
    }
}
