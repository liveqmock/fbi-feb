package gateway.sbs.txn.model.msg;

/**
 * ��Ҫƾ֤����ά��ɾ��
 */
public class Mh802 extends MTia {




    private String VCHTYP = ""	;          // ƾ֤����

    public Mh802(String vchtyp){
        this.VCHTYP = vchtyp;
    }
    public Mh802(){

    }

    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }
}
