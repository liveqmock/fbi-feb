package gateway.sbs.txn.model.msg;

/**
 * ��Ҫƾ֤����ά�����
 */
public class Mh801 extends MTia {




    private String VCHTYP = ""	;          // ƾ֤����
    private String OUTITM	= "";          // ���������
    private String VCHNAM	= "";          // ƾ֤����
    private String SCTMAK	= "";          // �Ƿ��м�֤ȯ
    private String VCHAMT	= "";          // ƾ֤���


    public Mh801(String vchtyp){
        this.VCHTYP = vchtyp;
    }

    public Mh801() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }

    public String getOUTITM() {
        return OUTITM;
    }

    public void setOUTITM(String OUTITM) {
        this.OUTITM = OUTITM;
    }

    public String getVCHNAM() {
        return VCHNAM;
    }

    public void setVCHNAM(String VCHNAM) {
        this.VCHNAM = VCHNAM;
    }

    public String getSCTMAK() {
        return SCTMAK;
    }

    public void setSCTMAK(String SCTMAK) {
        this.SCTMAK = SCTMAK;
    }

    public String getVCHAMT() {
        return VCHAMT;
    }

    public void setVCHAMT(String VCHAMT) {
        this.VCHAMT = VCHAMT;
    }
}
