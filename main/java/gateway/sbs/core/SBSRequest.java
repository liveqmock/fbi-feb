package gateway.sbs.core;

import java.util.List;

/**
 * SBS «Î«Û
 */
public class SBSRequest {
    private String txncode;
    private List<String> paramList;

    public SBSRequest(String txncode, List<String> paramList){
        this.txncode = txncode;
        this.paramList = paramList;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public String getTxncode() {
        return txncode;
    }
}
