package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10            //利率码单笔查询
 * Time: 下午9:28
 * To change this template use File | Settings | File Templates.
 */

public class T885 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{4, 34, 1, 1, 1, 4, 8};
    }

    private String TAXCDE;               //税率码
    private String TAXDCR;               //税率描述
    private String TAXFG1;               //税率标识1
    private String TAXFG2;               //税率标识2
    private String TAXFG3;               //税率标识3
    private String AMDTLR;               //建档柜员
    private String UPDDAT;               //建档日期

    public String getTAXCDE() {
        return TAXCDE;
    }

    public void setTAXCDE(String TAXCDE) {
        this.TAXCDE = TAXCDE;
    }

    public String getTAXDCR() {
        return TAXDCR;
    }

    public void setTAXDCR(String TAXDCR) {
        this.TAXDCR = TAXDCR;
    }

    public String getTAXFG1() {
        return TAXFG1;
    }

    public void setTAXFG1(String TAXFG1) {
        this.TAXFG1 = TAXFG1;
    }

    public String getTAXFG2() {
        return TAXFG2;
    }

    public void setTAXFG2(String TAXFG2) {
        this.TAXFG2 = TAXFG2;
    }

    public String getTAXFG3() {
        return TAXFG3;
    }

    public void setTAXFG3(String TAXFG3) {
        this.TAXFG3 = TAXFG3;
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
