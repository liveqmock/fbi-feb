package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-14
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class T908 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};//13
        fieldLengths = new int[]{1, 2, 2, 30, 40, 40, 40, 120, 122, 122, 72, 4, 10};
    }

    private String TDBSRT;     //大类
    private String TDMSRT;     //中类
    private String TDSSRT;     //小类
    private String TDBNAM;     //大类名称
    private String TDMNAM;     //中类名称
    private String TDSNAM;     //小类名称
    private String TDDNAM;     //行业名称
    private String TDDCN1;     //说明一
    private String TDDCN2;     //说明二
    private String TDDCN3;     //说明三
    private String TDDCN4;     //说明四
    private String AMDTLR;     //建立柜员
    private String UPDDAT;     //修改日期

    public String getTDBSRT() {
        return TDBSRT;
    }

    public void setTDBSRT(String TDBSRT) {
        this.TDBSRT = TDBSRT;
    }

    public String getTDMSRT() {
        return TDMSRT;
    }

    public void setTDMSRT(String TDMSRT) {
        this.TDMSRT = TDMSRT;
    }

    public String getTDSSRT() {
        return TDSSRT;
    }

    public void setTDSSRT(String TDSSRT) {
        this.TDSSRT = TDSSRT;
    }

    public String getTDBNAM() {
        return TDBNAM;
    }

    public void setTDBNAM(String TDBNAM) {
        this.TDBNAM = TDBNAM;
    }

    public String getTDMNAM() {
        return TDMNAM;
    }

    public void setTDMNAM(String TDMNAM) {
        this.TDMNAM = TDMNAM;
    }

    public String getTDSNAM() {
        return TDSNAM;
    }

    public void setTDSNAM(String TDSNAM) {
        this.TDSNAM = TDSNAM;
    }

    public String getTDDNAM() {
        return TDDNAM;
    }

    public void setTDDNAM(String TDDNAM) {
        this.TDDNAM = TDDNAM;
    }

    public String getTDDCN1() {
        return TDDCN1;
    }

    public void setTDDCN1(String TDDCN1) {
        this.TDDCN1 = TDDCN1;
    }

    public String getTDDCN2() {
        return TDDCN2;
    }

    public void setTDDCN2(String TDDCN2) {
        this.TDDCN2 = TDDCN2;
    }

    public String getTDDCN3() {
        return TDDCN3;
    }

    public void setTDDCN3(String TDDCN3) {
        this.TDDCN3 = TDDCN3;
    }

    public String getTDDCN4() {
        return TDDCN4;
    }

    public void setTDDCN4(String TDDCN4) {
        this.TDDCN4 = TDDCN4;
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
