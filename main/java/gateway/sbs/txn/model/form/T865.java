package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10            //�������뵥�ʲ�ѯ
 * Time: ����9:28
 * To change this template use File | Settings | File Templates.
 */

public class T865 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 34, 34, 2, 1, 1, 1, 4, 8};
    }

    private String CTRCDE;               //������
    private String CTRNMC;               //��������(��)
    private String CTRNME;               //��������(Ӣ)
    private String SWFCTR;               //SWIFT������
    private String RSVFG1;               //���ñ�־1
    private String RSVFG2;               //���ñ�־2
    private String RSVFG3;               //���ñ�־3
    private String AMDTLR;               //������Ա
    private String UPDDAT;               //��������

    public String getCTRCDE() {
        return CTRCDE;
    }

    public void setCTRCDE(String CTRCDE) {
        this.CTRCDE = CTRCDE;
    }

    public String getCTRNMC() {
        return CTRNMC;
    }

    public void setCTRNMC(String CTRNMC) {
        this.CTRNMC = CTRNMC;
    }

    public String getCTRNME() {
        return CTRNME;
    }

    public void setCTRNME(String CTRNME) {
        this.CTRNME = CTRNME;
    }

    public String getSWFCTR() {
        return SWFCTR;
    }

    public void setSWFCTR(String SWFCTR) {
        this.SWFCTR = SWFCTR;
    }

    public String getRSVFG1() {
        return RSVFG1;
    }

    public void setRSVFG1(String RSVFG1) {
        this.RSVFG1 = RSVFG1;
    }

    public String getRSVFG2() {
        return RSVFG2;
    }

    public void setRSVFG2(String RSVFG2) {
        this.RSVFG2 = RSVFG2;
    }

    public String getRSVFG3() {
        return RSVFG3;
    }

    public void setRSVFG3(String RSVFG3) {
        this.RSVFG3 = RSVFG3;
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
}
