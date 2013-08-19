package gateway.sbs.core;

import java.util.List;

/**
 * SBS «Î«Û
 */
public class SBSRequest {
    private String termid;
    private String tellerid;
    private String txncode;
    private List<String> paramList;

    public SBSRequest(String termid, String tellerid, String txncode, List<String> paramList){
        this.termid = termid;
        this.tellerid = tellerid;
        this.txncode = txncode;
        this.paramList = paramList;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public String getTxncode() {
        return txncode;
    }

    public String getTermid() {
        return termid;
    }

    public String getTellerid() {
        return tellerid;
    }
}
