package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.SOFFormBody;

/**
 T404-��ѯ���޸ġ�ɾ��������ʱ��������Ϣ��
 */
public class T404 extends SOFFormBody {
    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};   //10
        fieldLengths = new int[]{4, 4, 4, 6, 2, 5, 8, 3, 4, 80};
    }

    private String  VCHSET; //��Ʊ��
    private String  TRNCDE; //�������
    private String  TLRNUM; //��Ա��
    private String  TRNTIM; //����ʱ��
    private String  TM_CODE;//�ն����
    private String  TXNCODE; //������ˮ��
    private String  TRNDAT; //��������
    private String  ORGIDT;  //������
    private String  TMCODE;  //������
    private String  VCHPAR;  //��������Ϣ

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getTRNCDE() {
        return TRNCDE;
    }

    public void setTRNCDE(String TRNCDE) {
        this.TRNCDE = TRNCDE;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getTRNTIM() {
        return TRNTIM;
    }

    public void setTRNTIM(String TRNTIM) {
        this.TRNTIM = TRNTIM;
    }

    public String getTM_CODE() {
        return TM_CODE;
    }

    public void setTM_CODE(String TM_CODE) {
        this.TM_CODE = TM_CODE;
    }

    public String getTXNCODE() {
        return TXNCODE;
    }

    public void setTXNCODE(String TXNCODE) {
        this.TXNCODE = TXNCODE;
    }

    public String getTRNDAT() {
        return TRNDAT;
    }

    public void setTRNDAT(String TRNDAT) {
        this.TRNDAT = TRNDAT;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public String getTMCODE() {
        return TMCODE;
    }

    public void setTMCODE(String TMCODE) {
        this.TMCODE = TMCODE;
    }

    public String getVCHPAR() {
        return VCHPAR;
    }

    public void setVCHPAR(String VCHPAR) {
        this.VCHPAR = VCHPAR;
    }
}
