package gateway.sbs.txn.model.form.sc;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/9
 * Time: 9:22
 * To change this template use File | Settings | File Templates.
 */
public class T206 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1};
        fieldLengths = new int[]{7, 4, 4, 30};
    }

    private String WATNUM ;     //流水号
    private String VCHSET ;     //传票套号
    private String TXNCDE ;     //交易码
    private String TXNDCR ;     //交易名称

    public String getWATNUM() {
        return WATNUM;
    }

    public void setWATNUM(String WATNUM) {
        this.WATNUM = WATNUM;
    }

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getTXNCDE() {
        return TXNCDE;
    }

    public void setTXNCDE(String TXNCDE) {
        this.TXNCDE = TXNCDE;
    }

    public String getTXNDCR() {
        return TXNDCR;
    }

    public void setTXNDCR(String TXNDCR) {
        this.TXNDCR = TXNDCR;
    }
}
