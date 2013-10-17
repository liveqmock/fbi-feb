package gateway.sbs.txn.model.msg;

/**
 * 重要凭证代号维护单笔查询
 */
public class Mh803 extends MTia {




    private String VCHTYP = "";  // 凭证总类


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
