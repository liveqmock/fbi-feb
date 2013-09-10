package gateway.sbs.txn.model.msg;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-5
 * Time: 上午10:14
 * 建立公司客户信息
 */
public class M8001 extends MTia{
    private String cusidt=""; //客户号
    private String cusnam="";  //客户名称
    private String shtnam="";  //客户简称
    private String engnam="";  //英文名
    private String rsdctr="1";  //国家注册码list  1中国
    private String oprctr="1";  //所在国家码 list 1中国
    private String coradd="";  //住址
    private String zipcde="";  //邮编 6位
    private String telnum="";  //电话号码
    private String telexn="";  //电传 10位
    private String tstrnk="8";  //信用级别 1-8
    private String pastyp="1";  //证件类型 list 1 身分证
    private String passno="";  //证件号码 18位
    private Long crdlim;    //授信额度（元）
    private String rskgrp="";  //风险组别
    private String relcus="";  //相关客户
    private Date opndat;    //开户日期
    private String cuskid="";  //客户类型 lis
    private String legbdy="";  //法人姓名
    private String actbdy="";  //财务负责人姓名
    private String reglap="";  //注册外币资本
    private Long loccap;    //注册本币资本
    private int sheff=3;      //效期（月数）
    private String regccy="";  //注册外币
    private String regadd="";  //注册地址
    private Date regdat;    //注册日期
    private String ctxnum="";  //国家税号
    private String ltxnum="";  //地方税号
    private String supdep="";  //主管部门管
    private String buscde="";  //行业代码
    private String enttyp="";  //企业性质 list
    private String custy1="";  //客户类型1
    private String custy2="";  //客户类型2
    private String intnet="";  //INTERET号
    private String entcde="";  //企业准字号
    private String ibkcde="";  //联行行号
    private String sbknum="";  //辖内行号

    public String getCusidt() {
        return cusidt;
    }

    public void setCusidt(String cusidt) {
        this.cusidt = cusidt;
    }

    public String getCusnam() {
        return cusnam;
    }

    public void setCusnam(String cusnam) {
        this.cusnam = cusnam;
    }

    public String getShtnam() {
        return shtnam;
    }

    public void setShtnam(String shtnam) {
        this.shtnam = shtnam;
    }

    public String getEngnam() {
        return engnam;
    }

    public void setEngnam(String engnam) {
        this.engnam = engnam;
    }

    public String getRsdctr() {
        return rsdctr;
    }

    public void setRsdctr(String rsdctr) {
        this.rsdctr = rsdctr;
    }

    public String getOprctr() {
        return oprctr;
    }

    public void setOprctr(String oprctr) {
        this.oprctr = oprctr;
    }

    public String getCoradd() {
        return coradd;
    }

    public void setCoradd(String coradd) {
        this.coradd = coradd;
    }

    public String getZipcde() {
        return zipcde;
    }

    public void setZipcde(String zipcde) {
        this.zipcde = zipcde;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public String getTelexn() {
        return telexn;
    }

    public void setTelexn(String telexn) {
        this.telexn = telexn;
    }

    public String getTstrnk() {
        return tstrnk;
    }

    public void setTstrnk(String tstrnk) {
        this.tstrnk = tstrnk;
    }

    public String getPastyp() {
        return pastyp;
    }

    public void setPastyp(String pastyp) {
        this.pastyp = pastyp;
    }

    public String getPassno() {
        return passno;
    }

    public void setPassno(String passno) {
        this.passno = passno;
    }

    public Long getCrdlim() {
        return crdlim;
    }

    public void setCrdlim(Long crdlim) {
        this.crdlim = crdlim;
    }

    public String getRskgrp() {
        return rskgrp;
    }

    public void setRskgrp(String rskgrp) {
        this.rskgrp = rskgrp;
    }

    public String getRelcus() {
        return relcus;
    }

    public void setRelcus(String relcus) {
        this.relcus = relcus;
    }

    public Date getOpndat() {
        return opndat;
    }

    public void setOpndat(Date opndat) {
        this.opndat = opndat;
    }

    public String getCuskid() {
        return cuskid;
    }

    public void setCuskid(String cuskid) {
        this.cuskid = cuskid;
    }

    public String getLegbdy() {
        return legbdy;
    }

    public void setLegbdy(String legbdy) {
        this.legbdy = legbdy;
    }

    public String getActbdy() {
        return actbdy;
    }

    public void setActbdy(String actbdy) {
        this.actbdy = actbdy;
    }

    public String getReglap() {
        return reglap;
    }

    public void setReglap(String reglap) {
        this.reglap = reglap;
    }

    public Long getLoccap() {
        return loccap;
    }

    public void setLoccap(Long loccap) {
        this.loccap = loccap;
    }

    public int getSheff() {
        return sheff;
    }

    public void setSheff(int sheff) {
        this.sheff = sheff;
    }

    public String getRegccy() {
        return regccy;
    }

    public void setRegccy(String regccy) {
        this.regccy = regccy;
    }

    public String getRegadd() {
        return regadd;
    }

    public void setRegadd(String regadd) {
        this.regadd = regadd;
    }

    public Date getRegdat() {
        return regdat;
    }

    public void setRegdat(Date regdat) {
        this.regdat = regdat;
    }

    public String getCtxnum() {
        return ctxnum;
    }

    public void setCtxnum(String ctxnum) {
        this.ctxnum = ctxnum;
    }

    public String getLtxnum() {
        return ltxnum;
    }

    public void setLtxnum(String ltxnum) {
        this.ltxnum = ltxnum;
    }

    public String getSupdep() {
        return supdep;
    }

    public void setSupdep(String supdep) {
        this.supdep = supdep;
    }

    public String getBuscde() {
        return buscde;
    }

    public void setBuscde(String buscde) {
        this.buscde = buscde;
    }

    public String getEnttyp() {
        return enttyp;
    }

    public void setEnttyp(String enttyp) {
        this.enttyp = enttyp;
    }

    public String getCusty1() {
        return custy1;
    }

    public void setCusty1(String custy1) {
        this.custy1 = custy1;
    }

    public String getCusty2() {
        return custy2;
    }

    public void setCusty2(String custy2) {
        this.custy2 = custy2;
    }

    public String getIntnet() {
        return intnet;
    }

    public void setIntnet(String intnet) {
        this.intnet = intnet;
    }

    public String getEntcde() {
        return entcde;
    }

    public void setEntcde(String entcde) {
        this.entcde = entcde;
    }

    public String getIbkcde() {
        return ibkcde;
    }

    public void setIbkcde(String ibkcde) {
        this.ibkcde = ibkcde;
    }

    public String getSbknum() {
        return sbknum;
    }

    public void setSbknum(String sbknum) {
        this.sbknum = sbknum;
    }
}
