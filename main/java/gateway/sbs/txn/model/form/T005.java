package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-12             //���˿ͻ����ʲ�ѯ
 * Time: ����9:35
 * To change this template use File | Settings | File Templates.
 */
public class T005 extends SOFFormBody {
    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1};//39
        fieldLengths = new int[]{7, 72, 120, 16, 4, 3, 3,122,6,18,10,1,18,
                8,21,8,8,8,6,1,3,2,1,10,2,2,34,7,7,15,15,1,3,4,4,4,8,1,1};
    }

    private String CUSIDT;       //	�ͻ���
    private String CUSNAM;       //	�ͻ�����
    private String ENGNAM;       //	�ͻ�Ӣ����
    private String SHTNAM;       //	�ͻ����
    private String CUSIDX;       //	��������
    private String RSDCTR;       //	ע�������
    private String OPRCTR;       //	���ڹ�����
    private String CORADD;       //	��ַ
    private String ZIPCDE;       //	�ʱ�
    private String TELNUM;       //	�绰
    private String TELEXN;       //	�紫
    private String PASTYP;       //	֤�����
    private String PASSNO;       //	֤����
    private String TSTRNK;       //	���õȼ�
    private String CRDLIM;       //	���ö��
    private String RSKGRP;       //	�������
    private String RELCUS;       //	��ؿͻ�
    private String OPNDAT;       //	������
    private String CUSPWD;       //	����
    private String CUSKID;       //	�ͻ�������
    private String ORGIDT;       //	������
    private String DEPNUM;       //	���ź�
    private String CUSSEX;       //	�ͻ��Ա�M-�У� F-Ů
    private String BIRDAY;       //	��������
    private String PROFSN;       //	ְҵ��1-���ˣ� 2-ũ�� 3-���ˣ� 4-�ɲ��� 5-ְԱ�� 6-����ְҵ�ߣ� 7-�����Ͷ��ߣ� 8-��ʦ�� 9-ѧ��
    private String POSTON;       //	ְ��1-�ܾ���2-����3-������4-��Ա
    private String WRKUNT;       //	������λ
    private String MONSLA;       //	������
    private String OTHINC;       //	��������
    private String ISRNUM;       //	���˹����պ�
    private String TAXNUM;       //	˰��
    private String MARSTS;       //	����״����0-δ�飬 1-�ѻ�
    private String FAMPER;       //	��ͥ�˿�
    private String CUSTY1;       //	���������
    private String CUSTY2;       //	�ͻ����(���ƿ���)
    private String AMDTLR;       //	���ܲ���
    private String UPDDAT;       //	�޸�����
    private String RECSTS;       //	��¼״̬
    private String SYSIDT;       //	ϵͳ��ʶ

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

    public String getCUSSEX() {
        return CUSSEX;
    }

    public void setCUSSEX(String CUSSEX) {
        this.CUSSEX = CUSSEX;
    }

    public String getBIRDAY() {
        return BIRDAY;
    }

    public void setBIRDAY(String BIRDAY) {
        this.BIRDAY = BIRDAY;
    }

    public String getPROFSN() {
        return PROFSN;
    }

    public void setPROFSN(String PROFSN) {
        this.PROFSN = PROFSN;
    }

    public String getPOSTON() {
        return POSTON;
    }

    public void setPOSTON(String POSTON) {
        this.POSTON = POSTON;
    }

    public String getWRKUNT() {
        return WRKUNT;
    }

    public void setWRKUNT(String WRKUNT) {
        this.WRKUNT = WRKUNT;
    }

    public String getMONSLA() {
        return MONSLA;
    }

    public void setMONSLA(String MONSLA) {
        this.MONSLA = MONSLA;
    }

    public String getOTHINC() {
        return OTHINC;
    }

    public void setOTHINC(String OTHINC) {
        this.OTHINC = OTHINC;
    }

    public String getISRNUM() {
        return ISRNUM;
    }

    public void setISRNUM(String ISRNUM) {
        this.ISRNUM = ISRNUM;
    }

    public String getTAXNUM() {
        return TAXNUM;
    }

    public void setTAXNUM(String TAXNUM) {
        this.TAXNUM = TAXNUM;
    }

    public String getMARSTS() {
        return MARSTS;
    }

    public void setMARSTS(String MARSTS) {
        this.MARSTS = MARSTS;
    }

    public String getFAMPER() {
        return FAMPER;
    }

    public void setFAMPER(String FAMPER) {
        this.FAMPER = FAMPER;
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
