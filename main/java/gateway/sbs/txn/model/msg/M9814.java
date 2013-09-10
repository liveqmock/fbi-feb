package gateway.sbs.txn.model.msg;

import gateway.sbs.core.domain.SOFFormBody;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-3
 * Time: ����2:25
 * To change this template use File | Settings | File Templates.
 */
//���ʲ�ѯ
public class M9814 extends MTia {

    private String glcode=""; //������
    private String apcode="";  //������
    private String apcnam="";       //����������
    private String apctyp="";       //����������
    private String plcode="";       //��Ӧ������
    private String intexp;       //��Ϣ֧���ʻ�������
    private String intinc;     //��Ϣ�����ʻ�������
    private String opsapc;       //��ת������
    private String apcdcr;       //����������
    private String intdac;       //Ӧ����Ϣ�ʻ�������
    private String intcac;       //Ӧ����Ϣ�ʻ�������
    private String ndrint;       //δʵ����Ϣ֧���˻�������
    private String ncrint;       //δʵ����Ϣ�����˻�������
    private String apckid="";       //APCKID
    private String MODFLG = "0"; // ��ǰ�޸ı�־  0-���޸ģ�1-�޸�
    private String FUNCDE;       // ������� 0-���ʲ�ѯ,2-�޸�, 3-ɾ��, 4-����
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
