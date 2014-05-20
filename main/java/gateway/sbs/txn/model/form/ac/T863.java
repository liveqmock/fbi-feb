package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10            //损益码单笔查询
 * Time: 下午9:28
 * To change this template use File | Settings | File Templates.
 */

public class T863 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{ 4, 34, 4, 2, 1, 4, 8};
    }

    private String PLCODE;               //损益码
    private String PLCNAM;               //损益项名称
    private String GLCODE;               //总帐码
    private String PLSCDE;               //损益子目
    private String PLCTYP;               //损益类型
    private String AMDTLR;               //修改柜员
    private String UPDDAT;               //修改日期

    public String getPLCODE() {
        return PLCODE;
    }

    public void setPLCODE(String PLCODE) {
        this.PLCODE = PLCODE;
    }

    public String getPLCNAM() {
        return PLCNAM;
    }

    public void setPLCNAM(String PLCNAM) {
        this.PLCNAM = PLCNAM;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getPLSCDE() {
        return PLSCDE;
    }

    public void setPLSCDE(String PLSCDE) {
        this.PLSCDE = PLSCDE;
    }

    public String getPLCTYP() {
        return PLCTYP;
    }

    public void setPLCTYP(String PLCTYP) {
        this.PLCTYP = PLCTYP;
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
