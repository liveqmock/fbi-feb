package gateway.sbs.txn.model.msg;

import java.io.Serializable;

/**
 * ÇëÇó±¨ÎÄ POJO
 */
public abstract class MTia implements Serializable {
    protected String termid;
    protected String operid;

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }

    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid;
    }
}
