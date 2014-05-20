package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * h803单笔查询、增删改-响应 T007
 */

public class T007 extends SOFFormBody {
   {
    fieldTypes = new int[]{1, 1, 1, 3};
    fieldLengths = new int[]{6, 30, 1, 13};
   }
   private String OUTITM;
   private String VCHNAM;
   private String SCTMAK;
   private BigDecimal VCHAMT;

    public String getOUTITM() {
        return OUTITM;
    }

    public void setOUTITM(String OUTITM) {
        this.OUTITM = OUTITM;
    }

    public String getVCHNAM() {
        return VCHNAM;
    }

    public void setVCHNAM(String VCHNAM) {
        this.VCHNAM = VCHNAM;
    }

    public String getSCTMAK() {
        return SCTMAK;
    }

    public void setSCTMAK(String SCTMAK) {
        this.SCTMAK = SCTMAK;
    }

    public BigDecimal getVCHAMT() {
        return VCHAMT;
    }

    public void setVCHAMT(BigDecimal VCHAMT) {
        this.VCHAMT = VCHAMT;
    }


    //    }
}
