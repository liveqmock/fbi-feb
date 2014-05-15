package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10            //损益码单笔查询
 * Time: 下午9:28
 * To change this template use File | Settings | File Templates.
 */

public class T882 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{2, 4, 34, 1, 1, 1, 1, 1, 10, 4, 8};
    }

    private String ALCODE;               //资产负债项目号
    private String GLCODE;               //该项目下总帐码
    private String ALCNAM;               //项目描述
    private String ALCTYP;               //资产负债类型
    private String DCTYPE;               //借贷及轧差标识
    private String OPRFLG;               //操作码
    private String SUMFLG;               //项目总计标志
    private String GLCFLG;               //统一会计科目标志
    private String ASTRWT;               //资产风险权数
    private String AMDTLR;               //修改柜员
    private String UPDDAT;               //最后修改日期

    public String getALCODE() {
        return ALCODE;
    }

    public void setALCODE(String ALCODE) {
        this.ALCODE = ALCODE;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getALCNAM() {
        return ALCNAM;
    }

    public void setALCNAM(String ALCNAM) {
        this.ALCNAM = ALCNAM;
    }

    public String getALCTYP() {
        return ALCTYP;
    }

    public void setALCTYP(String ALCTYP) {
        this.ALCTYP = ALCTYP;
    }

    public String getDCTYPE() {
        return DCTYPE;
    }

    public void setDCTYPE(String DCTYPE) {
        this.DCTYPE = DCTYPE;
    }

    public String getOPRFLG() {
        return OPRFLG;
    }

    public void setOPRFLG(String OPRFLG) {
        this.OPRFLG = OPRFLG;
    }

    public String getSUMFLG() {
        return SUMFLG;
    }

    public void setSUMFLG(String SUMFLG) {
        this.SUMFLG = SUMFLG;
    }

    public String getGLCFLG() {
        return GLCFLG;
    }

    public void setGLCFLG(String GLCFLG) {
        this.GLCFLG = GLCFLG;
    }

    public String getASTRWT() {
        return ASTRWT;
    }

    public void setASTRWT(String ASTRWT) {
        this.ASTRWT = ASTRWT;
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
