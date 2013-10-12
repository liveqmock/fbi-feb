package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-29              //外围传票录入
 * Time: 下午12:57
 * To change this template use File | Settings | File Templates.
 */
public class M8401 extends MTia {

    private String BATSEQ = "111111";     // 平台顺序号
    private String ORGIDT = "010";     // 机构号
    private String DEPNUM = "60";     // 部门号
    private String ORGID3 = "010";     // 机构号
    private String TLRNUM = "";     // 柜员号
    private String VCHSET = "";     // 传票套号
    private String SETSEQ = "";     // 传票序号
    private String ACTNUM = "";     // 账号
    private String TXNAMT = "";     // 交易金额
    private String RVSLBL = "";     // 冲账标志   '* '-冲账，'12'-转账,'11'-现金,'52'-补账
    private String OPNDA2 = "";     // 交易日期
    private String FURINF = "";     // 摘要
    private String PRDCDE = "VCH1";     // 产品码
    private String DEPNU3 = "60";     // 部门号
    private String ANACDE = "";     // 统计码
    private String FXRATE = "";     // 汇率
    private String SECCCY = "";     // 第二货币
    private String SECAMT = "";     // 第二货币金额
    private String VCHAUT = "";     // 主管代号
    private String OUFCRE = "";     // 建销卡标记
    private String ERYTYP = "0";     // 传票输入方式
    private String ERYDAT = "";     // 记帐日期
    private String FUNCDE = "1";     // 功能参数
    private String CORAPC = "";     // 损益对应核算码
    private String REGNUM = "";     // 凭证编号

    //===================================================
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

    public String getORGID3() {
        return ORGID3;
    }

    public void setORGID3(String ORGID3) {
        this.ORGID3 = ORGID3;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getSETSEQ() {
        return SETSEQ;
    }

    public void setSETSEQ(String SETSEQ) {
        this.SETSEQ = SETSEQ;
    }

    public String getACTNUM() {
        return ACTNUM;
    }

    public void setACTNUM(String ACTNUM) {
        this.ACTNUM = ACTNUM;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getRVSLBL() {
        return RVSLBL;
    }

    public void setRVSLBL(String RVSLBL) {
        this.RVSLBL = RVSLBL;
    }

    public String getOPNDA2() {
        return OPNDA2;
    }

    public void setOPNDA2(String OPNDA2) {
        this.OPNDA2 = OPNDA2;
    }

    public String getFURINF() {
        return FURINF;
    }

    public void setFURINF(String FURINF) {
        this.FURINF = FURINF;
    }

    public String getPRDCDE() {
        return PRDCDE;
    }

    public void setPRDCDE(String PRDCDE) {
        this.PRDCDE = PRDCDE;
    }

    public String getDEPNU3() {
        return DEPNU3;
    }

    public void setDEPNU3(String DEPNU3) {
        this.DEPNU3 = DEPNU3;
    }

    public String getANACDE() {
        return ANACDE;
    }

    public void setANACDE(String ANACDE) {
        this.ANACDE = ANACDE;
    }

    public String getFXRATE() {
        return FXRATE;
    }

    public void setFXRATE(String FXRATE) {
        this.FXRATE = FXRATE;
    }

    public String getSECCCY() {
        return SECCCY;
    }

    public void setSECCCY(String SECCCY) {
        this.SECCCY = SECCCY;
    }

    public String getSECAMT() {
        return SECAMT;
    }

    public void setSECAMT(String SECAMT) {
        this.SECAMT = SECAMT;
    }

    public String getVCHAUT() {
        return VCHAUT;
    }

    public void setVCHAUT(String VCHAUT) {
        this.VCHAUT = VCHAUT;
    }

    public String getOUFCRE() {
        return OUFCRE;
    }

    public void setOUFCRE(String OUFCRE) {
        this.OUFCRE = OUFCRE;
    }

    public String getERYTYP() {
        return ERYTYP;
    }

    public void setERYTYP(String ERYTYP) {
        this.ERYTYP = ERYTYP;
    }

    public String getERYDAT() {
        return ERYDAT;
    }

    public void setERYDAT(String ERYDAT) {
        this.ERYDAT = ERYDAT;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getCORAPC() {
        return CORAPC;
    }

    public void setCORAPC(String CORAPC) {
        this.CORAPC = CORAPC;
    }

    public String getREGNUM() {
        return REGNUM;
    }

    public void setREGNUM(String REGNUM) {
        this.REGNUM = REGNUM;
    }
}
