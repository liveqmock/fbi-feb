package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-13    币别码查询多笔
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */
public class T806 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1};    //19
        fieldLengths = new int[]{3, 3, 8, 24, 2, 14, 6,
                6, 1, 17, 17, 10, 17, 17, 1, 1, 4, 4, 8};
    }

    private String CURCDE;        //货币码
    private String FRTCDE;        //收费码
    private String EFFDAT;        //启用日期
    private String FRTNAM;        //收费名称
    private String DOCCDE;        //凭证代码
    private String DOCNAM;        //凭证名称
    private String FRTSPH;        //费率浮动上限值
    private String FRTSPL;        //费率浮动下限值
    private String SPRFLG;        //浮动标识
    private String MALFEE;        //邮电费
    private String COMFEE;        //手续费
    private String FEERAT;        //收费率
    private String HIGLIM;        //计费高限
    private String LOWLIM;        //计费低限
    private String FEECYC;        //计费周期
    private String FEESDE;        //收费方式
    private String FEEAPC;        //收费转帐帐户核算码
    private String CRETLR;        //建档柜员
    private String CREDAT;        //建档日期

    public String getCURCDE() {
        return CURCDE;
    }

    public void setCURCDE(String CURCDE) {
        this.CURCDE = CURCDE;
    }

    public String getFRTCDE() {
        return FRTCDE;
    }

    public void setFRTCDE(String FRTCDE) {
        this.FRTCDE = FRTCDE;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public String getFRTNAM() {
        return FRTNAM;
    }

    public void setFRTNAM(String FRTNAM) {
        this.FRTNAM = FRTNAM;
    }

    public String getDOCCDE() {
        return DOCCDE;
    }

    public void setDOCCDE(String DOCCDE) {
        this.DOCCDE = DOCCDE;
    }

    public String getDOCNAM() {
        return DOCNAM;
    }

    public void setDOCNAM(String DOCNAM) {
        this.DOCNAM = DOCNAM;
    }

    public String getFRTSPH() {
        return FRTSPH;
    }

    public void setFRTSPH(String FRTSPH) {
        this.FRTSPH = FRTSPH;
    }

    public String getFRTSPL() {
        return FRTSPL;
    }

    public void setFRTSPL(String FRTSPL) {
        this.FRTSPL = FRTSPL;
    }

    public String getSPRFLG() {
        return SPRFLG;
    }

    public void setSPRFLG(String SPRFLG) {
        this.SPRFLG = SPRFLG;
    }

    public String getMALFEE() {
        return MALFEE;
    }

    public void setMALFEE(String MALFEE) {
        this.MALFEE = MALFEE;
    }

    public String getCOMFEE() {
        return COMFEE;
    }

    public void setCOMFEE(String COMFEE) {
        this.COMFEE = COMFEE;
    }

    public String getFEERAT() {
        return FEERAT;
    }

    public void setFEERAT(String FEERAT) {
        this.FEERAT = FEERAT;
    }

    public String getHIGLIM() {
        return HIGLIM;
    }

    public void setHIGLIM(String HIGLIM) {
        this.HIGLIM = HIGLIM;
    }

    public String getLOWLIM() {
        return LOWLIM;
    }

    public void setLOWLIM(String LOWLIM) {
        this.LOWLIM = LOWLIM;
    }

    public String getFEECYC() {
        return FEECYC;
    }

    public void setFEECYC(String FEECYC) {
        this.FEECYC = FEECYC;
    }

    public String getFEESDE() {
        return FEESDE;
    }

    public void setFEESDE(String FEESDE) {
        this.FEESDE = FEESDE;
    }

    public String getFEEAPC() {
        return FEEAPC;
    }

    public void setFEEAPC(String FEEAPC) {
        this.FEEAPC = FEEAPC;
    }

    public String getCRETLR() {
        return CRETLR;
    }

    public void setCRETLR(String CRETLR) {
        this.CRETLR = CRETLR;
    }

    public String getCREDAT() {
        return CREDAT;
    }

    public void setCREDAT(String CREDAT) {
        this.CREDAT = CREDAT;
    }
}
