package gateway.sbs.txn.model.msg;

import gateway.sbs.core.domain.SOFFormBody;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-3
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
//单笔查询
public class M9814 extends MTia {

    private String glcode=""; //总账码
    private String apcode="";  //核算码
    private String apcnam="";       //核算码名称
    private String apctyp="";       //核算码类型
    private String plcode="";       //对应损益吗
    private String intexp;       //利息支出帐户核算码
    private String intinc;     //利息收入帐户核算码
    private String opsapc;       //对转核算码
    private String apcdcr;       //核算码描述
    private String intdac;       //应付利息帐户核算码
    private String intcac;       //应收利息帐户核算码
    private String ndrint;       //未实现利息支出账户核算码
    private String ncrint;       //未实现利息收入账户核算码
    private String apckid="";       //APCKID
    private String MODFLG = "0"; // 当前修改标志  0-不修改，1-修改
    private String FUNCDE;       // 操作类别 0-单笔查询,2-修改, 3-删除, 4-增加
    /*    private String amdtlr="";       //
    private String  upddat;         //
    private String recsts="";       //
    */

    public M9814(){
    }
    public M9814(String apcode,String glcode,String apctyp,String apcnam){
        this.glcode=glcode;
        this.apcode=apcode;
        this.apctyp=apctyp;
        this.apcnam=apcnam;
    }

    public String getMODFLG() {
        return MODFLG;
    }

    public void setMODFLG(String MODFLG) {
        this.MODFLG = MODFLG;
    }

    public String getFUNCDE() {
        return FUNCDE;
    }

    public void setFUNCDE(String FUNCDE) {
        this.FUNCDE = FUNCDE;
    }

    public String getApcode()
    {
        return this.apcode;
    }

    public void setApcode(String apcode)
    {
        this.apcode = apcode;
    }

    public String getApcnam()
    {
        return this.apcnam;
    }

    public void setApcnam(String apcnam)
    {
        this.apcnam = apcnam;
    }

    public String getApctyp()
    {
        return this.apctyp;
    }

    public void setApctyp(String apctyp)
    {
        this.apctyp = apctyp;
    }

    public String getGlcode()
    {
        return this.glcode;
    }

    public void setGlcode(String glcode)
    {
        this.glcode = glcode;
    }

    public String getPlcode()
    {
        return this.plcode;
    }

    public void setPlcode(String plcode)
    {
        this.plcode = plcode;
    }

    public String getIntexp()
    {
        return this.intexp;
    }

    public void setIntexp(String intexp)
    {
        this.intexp = intexp;
    }

    public String getIntinc()
    {
        return this.intinc;
    }

    public void setIntinc(String intinc)
    {
        this.intinc = intinc;
    }

    public String getOpsapc()
    {
        return this.opsapc;
    }

    public void setOpsapc(String opsapc)
    {
        this.opsapc = opsapc;
    }

    public String getApcdcr()
    {
        return this.apcdcr;
    }

    public void setApcdcr(String apcdcr)
    {
        this.apcdcr = apcdcr;
    }

    public String getIntdac()
    {
        return this.intdac;
    }

    public void setIntdac(String intdac)
    {
        this.intdac = intdac;
    }

    public String getIntcac()
    {
        return this.intcac;
    }

    public void setIntcac(String intcac)
    {
        this.intcac = intcac;
    }

    public String getNdrint()
    {
        return this.ndrint;
    }

    public void setNdrint(String ndrint)
    {
        this.ndrint = ndrint;
    }

    public String getNcrint()
    {
        return this.ncrint;
    }

    public void setNcrint(String ncrint)
    {
        this.ncrint = ncrint;
    }

    public String getApckid()
    {
        return this.apckid;
    }

    public void setApckid(String apckid)
    {
        this.apckid = apckid;
    }

}
