package gateway.sbs.txn.model.msg;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-5
 * Time: ����10:14
 * ������˾�ͻ���Ϣ
 */
public class M8001 extends MTia{
    private String cusidt=""; //�ͻ���
    private String cusnam="";  //�ͻ�����
    private String shtnam="";  //�ͻ����
    private String engnam="";  //Ӣ����
    private String rsdctr="1";  //����ע����list  1�й�
    private String oprctr="1";  //���ڹ����� list 1�й�
    private String coradd="";  //סַ
    private String zipcde="";  //�ʱ� 6λ
    private String telnum="";  //�绰����
    private String telexn="";  //�紫 10λ
    private String tstrnk="8";  //���ü��� 1-8
    private String pastyp="1";  //֤������ list 1 ���֤
    private String passno="";  //֤������ 18λ
    private Long crdlim;    //���Ŷ�ȣ�Ԫ��
    private String rskgrp="";  //�������
    private String relcus="";  //��ؿͻ�
    private Date opndat;    //��������
    private String cuskid="";  //�ͻ����� lis
    private String legbdy="";  //��������
    private String actbdy="";  //������������
    private String reglap="";  //ע������ʱ�
    private Long loccap;    //ע�᱾���ʱ�
    private int sheff=3;      //Ч�ڣ�������
    private String regccy="";  //ע�����
    private String regadd="";  //ע���ַ
    private Date regdat;    //ע������
    private String ctxnum="";  //����˰��
    private String ltxnum="";  //�ط�˰��
    private String supdep="";  //���ܲ��Ź�
    private String buscde="";  //��ҵ����
    private String enttyp="";  //��ҵ���� list
    private String custy1="";  //�ͻ�����1
    private String custy2="";  //�ͻ�����2
    private String intnet="";  //INTERET��
    private String entcde="";  //��ҵ׼�ֺ�
    private String ibkcde="";  //�����к�
    private String sbknum="";  //Ͻ���к�

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
