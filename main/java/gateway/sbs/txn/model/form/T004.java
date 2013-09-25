package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-12        //内部或对公客户查询响应报文  修改返回报文
 * Time: 下午9:16
 * To change this template use File | Settings | File Templates.
 */
public class T004 extends SOFFormBody{
    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1 ,1,1,1,1,1,1,1,1,1,1};//46
        fieldLengths = new int[]{7, 72, 120, 16, 4, 3, 3,122,6,18,10,1,18,
                8,21,8,8,8,6,1,3,2,24,24,21,21,3,122,8,999,15,15,8,8,5,1,4,4,
                30,25,5,2,4,8,1,1};
    }
    private String CUSIDT;   //	客户号
    private String CUSNAM;   //	客户名称
    private String ENGNAM;   //	客户英文名
    private String SHTNAM;   //	客户简称
    private String CUSIDX;   //	简名索引
    private String RSDCTR;   //	注册国家码
    private String OPRCTR;   //	所在国家码
    private String CORADD;   //	地址
    private String ZIPCDE;   //	邮编
    private String TELNUM;   //	电话
    private String TELEXN;   //	电传
    private String PASTYP;   //	证件类别
    private String PASSNO;   //	证件号
    private String TSTRNK;   //	信用等级
    private String CRDLIM;   //	信用额度
    private String RSKGRP;   //	风险组别
    private String RELCUS;   //	相关客户
    private String OPNDAT;   //	开户日
    private String CUSPWD;   //	密码
    private String CUSKID;   //	客户类型码
    private String ORGIDT;   //	机构号
    private String DEPNUM;   //	部门号
    private String LEGBDY;   //	法人代表姓名
    private String ACTBDY;   //	财务负责人姓名
    private String LOCCAP;   //	注册外币资本
    private String REGCAP;   //	注册外币资本
    private String REGCCY;   //	第二货币
    private String REGADD;   //	地址
    private String REGDAT;   //	注册日期
    private String EFFDUR;   //	有效期(月)
    private String CTXNUM;   //	国税号码
    private String LTXNUM;   //	地税号码
    private String BOCGRP;   //	客户分类码
    private String SUPDEP;   //	主管部门
    private String BUSCDE;   //	行业代码
    private String ENTTYP;   //	企业性质
    private String CUSTY1;   //	损益归类码
    private String CUSTY2;   //	客户类别(控制开户)
    private String INTNET;   //	INTERNET号
    private String ENTCDE;   //	企业标准号
    private String IBKCDE;   //	联行代号
    private String SBKNUM;   //	行号
    private String AMDTLR;   //	功能参数
    private String UPDDAT;   //	修改日期
    private String RECSTS;   //	记录状态
    private String SYSIDT;   //	系统标识

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getCUSNAM() {
        return CUSNAM;
    }

    public void setCUSNAM(String CUSNAM) {
        this.CUSNAM = CUSNAM;
    }

    public String getENGNAM() {
        return ENGNAM;
    }

    public void setENGNAM(String ENGNAM) {
        this.ENGNAM = ENGNAM;
    }

    public String getSHTNAM() {
        return SHTNAM;
    }

    public void setSHTNAM(String SHTNAM) {
        this.SHTNAM = SHTNAM;
    }

    public String getCUSIDX() {
        return CUSIDX;
    }

    public void setCUSIDX(String CUSIDX) {
        this.CUSIDX = CUSIDX;
    }

    public String getRSDCTR() {
        return RSDCTR;
    }

    public void setRSDCTR(String RSDCTR) {
        this.RSDCTR = RSDCTR;
    }

    public String getOPRCTR() {
        return OPRCTR;
    }

    public void setOPRCTR(String OPRCTR) {
        this.OPRCTR = OPRCTR;
    }

    public String getCORADD() {
        return CORADD;
    }

    public void setCORADD(String CORADD) {
        this.CORADD = CORADD;
    }

    public String getZIPCDE() {
        return ZIPCDE;
    }

    public void setZIPCDE(String ZIPCDE) {
        this.ZIPCDE = ZIPCDE;
    }

    public String getTELNUM() {
        return TELNUM;
    }

    public void setTELNUM(String TELNUM) {
        this.TELNUM = TELNUM;
    }

    public String getTELEXN() {
        return TELEXN;
    }

    public void setTELEXN(String TELEXN) {
        this.TELEXN = TELEXN;
    }

    public String getPASTYP() {
        return PASTYP;
    }

    public void setPASTYP(String PASTYP) {
        this.PASTYP = PASTYP;
    }

    public String getPASSNO() {
        return PASSNO;
    }

    public void setPASSNO(String PASSNO) {
        this.PASSNO = PASSNO;
    }

    public String getTSTRNK() {
        return TSTRNK;
    }

    public void setTSTRNK(String TSTRNK) {
        this.TSTRNK = TSTRNK;
    }

    public String getCRDLIM() {
        return CRDLIM;
    }

    public void setCRDLIM(String CRDLIM) {
        this.CRDLIM = CRDLIM;
    }

    public String getRSKGRP() {
        return RSKGRP;
    }

    public void setRSKGRP(String RSKGRP) {
        this.RSKGRP = RSKGRP;
    }

    public String getRELCUS() {
        return RELCUS;
    }

    public void setRELCUS(String RELCUS) {
        this.RELCUS = RELCUS;
    }

    public String getOPNDAT() {
        return OPNDAT;
    }

    public void setOPNDAT(String OPNDAT) {
        this.OPNDAT = OPNDAT;
    }

    public String getCUSPWD() {
        return CUSPWD;
    }

    public void setCUSPWD(String CUSPWD) {
        this.CUSPWD = CUSPWD;
    }

    public String getCUSKID() {
        return CUSKID;
    }

    public void setCUSKID(String CUSKID) {
        this.CUSKID = CUSKID;
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

    public String getLEGBDY() {
        return LEGBDY;
    }

    public void setLEGBDY(String LEGBDY) {
        this.LEGBDY = LEGBDY;
    }

    public String getACTBDY() {
        return ACTBDY;
    }

    public void setACTBDY(String ACTBDY) {
        this.ACTBDY = ACTBDY;
    }

    public String getLOCCAP() {
        return LOCCAP;
    }

    public void setLOCCAP(String LOCCAP) {
        this.LOCCAP = LOCCAP;
    }

    public String getREGCAP() {
        return REGCAP;
    }

    public void setREGCAP(String REGCAP) {
        this.REGCAP = REGCAP;
    }

    public String getREGCCY() {
        return REGCCY;
    }

    public void setREGCCY(String REGCCY) {
        this.REGCCY = REGCCY;
    }

    public String getREGADD() {
        return REGADD;
    }

    public void setREGADD(String REGADD) {
        this.REGADD = REGADD;
    }

    public String getREGDAT() {
        return REGDAT;
    }

    public void setREGDAT(String REGDAT) {
        this.REGDAT = REGDAT;
    }

    public String getEFFDUR() {
        return EFFDUR;
    }

    public void setEFFDUR(String EFFDUR) {
        this.EFFDUR = EFFDUR;
    }

    public String getCTXNUM() {
        return CTXNUM;
    }

    public void setCTXNUM(String CTXNUM) {
        this.CTXNUM = CTXNUM;
    }

    public String getLTXNUM() {
        return LTXNUM;
    }

    public void setLTXNUM(String LTXNUM) {
        this.LTXNUM = LTXNUM;
    }

    public String getBOCGRP() {
        return BOCGRP;
    }

    public void setBOCGRP(String BOCGRP) {
        this.BOCGRP = BOCGRP;
    }

    public String getSUPDEP() {
        return SUPDEP;
    }

    public void setSUPDEP(String SUPDEP) {
        this.SUPDEP = SUPDEP;
    }

    public String getBUSCDE() {
        return BUSCDE;
    }

    public void setBUSCDE(String BUSCDE) {
        this.BUSCDE = BUSCDE;
    }

    public String getENTTYP() {
        return ENTTYP;
    }

    public void setENTTYP(String ENTTYP) {
        this.ENTTYP = ENTTYP;
    }

    public String getCUSTY1() {
        return CUSTY1;
    }

    public void setCUSTY1(String CUSTY1) {
        this.CUSTY1 = CUSTY1;
    }

    public String getCUSTY2() {
        return CUSTY2;
    }

    public void setCUSTY2(String CUSTY2) {
        this.CUSTY2 = CUSTY2;
    }

    public String getINTNET() {
        return INTNET;
    }

    public void setINTNET(String INTNET) {
        this.INTNET = INTNET;
    }

    public String getENTCDE() {
        return ENTCDE;
    }

    public void setENTCDE(String ENTCDE) {
        this.ENTCDE = ENTCDE;
    }

    public String getIBKCDE() {
        return IBKCDE;
    }

    public void setIBKCDE(String IBKCDE) {
        this.IBKCDE = IBKCDE;
    }

    public String getSBKNUM() {
        return SBKNUM;
    }

    public void setSBKNUM(String SBKNUM) {
        this.SBKNUM = SBKNUM;
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

    public String getSYSIDT() {
        return SYSIDT;
    }

    public void setSYSIDT(String SYSIDT) {
        this.SYSIDT = SYSIDT;
    }
}
