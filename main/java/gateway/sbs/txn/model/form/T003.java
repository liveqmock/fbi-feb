package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-12               //浏览查询
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */
public class T003 extends SOFFormBody{
    {
        fieldTypes=new int[]{1,1,1,1,1};
        fieldLengths=new int[]{7,18,16,18,1}  ;
    }
    private String CUSIDT;  //	客户号
    private String PASSNO;  //	证件号
    private String SHTNAM;  //	客户简称
    private String TELNUM;  //	电话
    private String SYSIDT;  //	系统标识

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getPASSNO() {
        return PASSNO;
    }

    public void setPASSNO(String PASSNO) {
        this.PASSNO = PASSNO;
    }

    public String getSHTNAM() {
        return SHTNAM;
    }

    public void setSHTNAM(String SHTNAM) {
        this.SHTNAM = SHTNAM;
    }

    public String getTELNUM() {
        return TELNUM;
    }

    public void setTELNUM(String TELNUM) {
        this.TELNUM = TELNUM;
    }

    public String getSYSIDT() {
        return SYSIDT;
    }

    public void setSYSIDT(String SYSIDT) {
        this.SYSIDT = SYSIDT;
    }
}
