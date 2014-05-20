package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-13    �ұ����ѯ���
 * Time: ����3:36
 * To change this template use File | Settings | File Templates.
 */
public class T806 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1};    //19
        fieldLengths = new int[]{3, 3, 8, 24, 2, 14, 6,
                6, 1, 17, 17, 10, 17, 17, 1, 1, 4, 4, 8};
    }

    private String CURCDE;        //������
    private String FRTCDE;        //�շ���
    private String EFFDAT;        //��������
    private String FRTNAM;        //�շ�����
    private String DOCCDE;        //ƾ֤����
    private String DOCNAM;        //ƾ֤����
    private String FRTSPH;        //���ʸ�������ֵ
    private String FRTSPL;        //���ʸ�������ֵ
    private String SPRFLG;        //������ʶ
    private String MALFEE;        //�ʵ��
    private String COMFEE;        //������
    private String FEERAT;        //�շ���
    private String HIGLIM;        //�ƷѸ���
    private String LOWLIM;        //�Ʒѵ���
    private String FEECYC;        //�Ʒ�����
    private String FEESDE;        //�շѷ�ʽ
    private String FEEAPC;        //�շ�ת���ʻ�������
    private String CRETLR;        //������Ա
    private String CREDAT;        //��������

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
