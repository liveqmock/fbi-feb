package gateway.sbs.txn.model.msg;

/**
 * ��Ҫƾ֤����ά�����ʲ�ѯ
 */
public class Mh803 extends MTia {




    private String VCHTYP = "";  // ƾ֤����


    public Mh803(String vchtyp){
        this.VCHTYP = vchtyp;
    }

    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }
}
