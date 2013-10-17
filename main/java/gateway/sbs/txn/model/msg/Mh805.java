package gateway.sbs.txn.model.msg;

/**
 * 重要凭证代号维护多笔查询
 */
public class Mh805 extends MTia {




    private String VCHTYP = "";  // 凭证总类


    public Mh805(){

    }

    public String getVCHTYP() {
        return VCHTYP;
    }

    public void setVCHTYP(String VCHTYP) {
        this.VCHTYP = VCHTYP;
    }
}
