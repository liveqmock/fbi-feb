package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-13     自动转账表要素单笔
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */
public class T872 extends SOFFormBody {
    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};//9
        fieldLengths = new int[]{3, 3, 1, 20, 1, 1, 1, 4,8};
    }

    private String ATRCDE;     //自动转帐码
    private String TRFSEQ;     //要素顺序号
    private String TRFKID;     //要素类别
    private String TRFNUM;     //要素号
    private String TRFOPR;     //要素操作码
    private String AMTTYP;     //要素金额类别
    private String AMTSDE;     //要素借贷方向
    private String AMDTLR;     //修改柜员
    private String UPDDAT;     //修改日期

    public String getATRCDE() {
        return ATRCDE;
    }

    public void setATRCDE(String ATRCDE) {
        this.ATRCDE = ATRCDE;
    }

    public String getTRFSEQ() {
        return TRFSEQ;
    }

    public void setTRFSEQ(String TRFSEQ) {
        this.TRFSEQ = TRFSEQ;
    }

    public String getTRFKID() {
        return TRFKID;
    }

    public void setTRFKID(String TRFKID) {
        this.TRFKID = TRFKID;
    }

    public String getTRFNUM() {
        return TRFNUM;
    }

    public void setTRFNUM(String TRFNUM) {
        this.TRFNUM = TRFNUM;
    }

    public String getTRFOPR() {
        return TRFOPR;
    }

    public void setTRFOPR(String TRFOPR) {
        this.TRFOPR = TRFOPR;
    }

    public String getAMTTYP() {
        return AMTTYP;
    }

    public void setAMTTYP(String AMTTYP) {
        this.AMTTYP = AMTTYP;
    }

    public String getAMTSDE() {
        return AMTSDE;
    }

    public void setAMTSDE(String AMTSDE) {
        this.AMTSDE = AMTSDE;
    }

    public String getAMDTLR() {
        return AMDTLR;
    }

    public void setAMDTLR(String AMDTLR) {
        this.AMDTLR = AMDTLR;
    }

    public String getUPDDAT() {
        return UPDDAT;
    }

    public void setUPDDAT(String UPDDAT) {
        this.UPDDAT = UPDDAT;
    }
}
