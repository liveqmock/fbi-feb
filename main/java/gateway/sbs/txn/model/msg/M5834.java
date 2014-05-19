package gateway.sbs.txn.model.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-14
 * Time: 下午2:22      汇率
 * To change this template use File | Settings | File Templates.
 */
public class M5834 extends MTia {

    private String BATSEQ = "111111";      //序号
    private String ORGIDT = "010";      //柜员机构号
    private String DEPNUM = "60";      //柜员部门号
    private String CURCDE = "";      //币别码
    private String SECCCY = "";      //第二货币
    private String EFFDAT = "";      //启用日期
    private String EFFTIM = "000000";      //启用时间
    private String RATVA1 = "";      //买入价
    private String RATFL1 = "";      //汇率标志1
    private String RATVA2 = "";      //卖出价
    private String RATFL2 = "";      //汇率标志2
    private String RATVA3 = "";      //现钞买入价
    private String RATFL3 = "";      //汇率标志3
    private String RATVA4 = "";      //中间价
    private String RATFL4 = "";      //汇率标志4
    private String RATVA5 = "";      //平仓价
    private String RATFL5 = "";      //汇率标志5
    private String RATVA6 = "";      //决算价
    private String RATFL6 = "";      //汇率标志6
    private String RATVA7 = "";      //美元内部折算价
    private String RATFL7 = "";      //汇率标志7
    private String RATVA8 = "";      //人民银行基准价
    private String RATFL8 = "";      //汇率标志8
    private String RATVA9 = "";      //市场价
    private String RATFL9 = "";      //  汇率标志9
    private String FUNCDE = "0";      //功能别

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

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }



    public String getSECCCY() {
        return SECCCY;
    }

    public void setSECCCY(String SECCCY) {
        this.SECCCY = SECCCY;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public String getEFFTIM() {
        return EFFTIM;
    }

    public void setEFFTIM(String EFFTIM) {
        this.EFFTIM = EFFTIM;
    }

    public String getRATVA1() {
        return RATVA1;
    }

    public void setRATVA1(String RATVA1) {
        this.RATVA1 = RATVA1;
    }

    public String getRATFL1() {
        return RATFL1;
    }

    public void setRATFL1(String RATFL1) {
        this.RATFL1 = RATFL1;
    }

    public String getRATVA2() {
        return RATVA2;
    }

    public void setRATVA2(String RATVA2) {
        this.RATVA2 = RATVA2;
    }

    public String getRATFL2() {
        return RATFL2;
    }

    public void setRATFL2(String RATFL2) {
        this.RATFL2 = RATFL2;
    }

    public String getRATVA3() {
        return RATVA3;
    }

    public void setRATVA3(String RATVA3) {
        this.RATVA3 = RATVA3;
    }

    public String getRATFL3() {
        return RATFL3;
    }

    public void setRATFL3(String RATFL3) {
        this.RATFL3 = RATFL3;
    }

    public String getRATVA4() {
        return RATVA4;
    }

    public void setRATVA4(String RATVA4) {
        this.RATVA4 = RATVA4;
    }

    public String getRATFL4() {
        return RATFL4;
    }

    public void setRATFL4(String RATFL4) {
        this.RATFL4 = RATFL4;
    }

    public String getRATVA5() {
        return RATVA5;
    }

    public void setRATVA5(String RATVA5) {
        this.RATVA5 = RATVA5;
    }

    public String getRATFL5() {
        return RATFL5;
    }

    public void setRATFL5(String RATFL5) {
        this.RATFL5 = RATFL5;
    }

    public String getRATVA6() {
        return RATVA6;
    }

    public void setRATVA6(String RATVA6) {
        this.RATVA6 = RATVA6;
    }

    public String getRATFL6() {
        return RATFL6;
    }

    public void setRATFL6(String RATFL6) {
        this.RATFL6 = RATFL6;
    }

    public String getRATVA7() {
        return RATVA7;
    }

    public void setRATVA7(String RATVA7) {
        this.RATVA7 = RATVA7;
    }

    public String getRATFL7() {
        return RATFL7;
    }

    public void setRATFL7(String RATFL7) {
        this.RATFL7 = RATFL7;
    }

    public String getRATVA8() {
        return RATVA8;
    }

    public void setRATVA8(String RATVA8) {
        this.RATVA8 = RATVA8;
    }

    public String getRATFL8() {
        return RATFL8;
    }

    public void setRATFL8(String RATFL8) {
        this.RATFL8 = RATFL8;
    }

    public String getRATVA9() {
        return RATVA9;
    }

    public void setRATVA9(String RATVA9) {
        this.RATVA9 = RATVA9;
    }

    public String getRATFL9() {
        return RATFL9;
    }

    public void setRATFL9(String RATFL9) {
        this.RATFL9 = RATFL9;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }
}
