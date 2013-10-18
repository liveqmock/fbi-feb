package gateway.sbs.txn.model.msg;

/**
 * 重要凭证代号维护删除
 */
public class Mh802 extends MTia {




    private String VCHTYP = ""	;          // 凭证种类

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
