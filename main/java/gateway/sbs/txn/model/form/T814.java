package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-3
 * Time: ����2:25
 * To change this template use File | Settings | File Templates.
 */
//��ʲ�ѯ
public class T814 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{25, 25, 30, 2, 2, 10, 4, 4, 4, 4, 3, 4, 4, 8};
    }
    private String glcode=""; //������
    private String apcode="";  //������
    private String apcnam="";       //����������
    private String apctyp="";       //����������
    private String plcode="";       //��Ӧ������
    private String intexp="";       //��Ϣ֧���ʻ�������
    private String intinc="";     //��Ϣ�����ʻ�������
    private String opsapc="";       //��ת������
    private String apcdcr="";       //����������
    private String intdac;       //Ӧ����Ϣ�ʻ�������
    private String intcac;       //Ӧ����Ϣ�ʻ�������
    private String ndrint;       //δʵ����Ϣ֧���˻�������
    private String ncrint;       //δʵ����Ϣ�����˻�������
    private String apckid="";       //APCKID

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getApcode() {
        return apcode;
    }

    public void setApcode(String apcode) {
        this.apcode = apcode;
    }

    public String getApcnam() {
        return apcnam;
    }

    public void setApcnam(String apcnam) {
        this.apcnam = apcnam;
    }

    public String getApctyp() {
        return apctyp;
    }

    public void setApctyp(String apctyp) {
        this.apctyp = apctyp;
    }

    public String getPlcode() {
        return plcode;
    }

    public void setPlcode(String plcode) {
        this.plcode = plcode;
    }

    public String getIntexp() {
        return intexp;
    }

    public void setIntexp(String intexp) {
        this.intexp = intexp;
    }

    public String getIntinc() {
        return intinc;
    }

    public void setIntinc(String intinc) {
        this.intinc = intinc;
    }

    public String getOpsapc() {
        return opsapc;
    }

    public void setOpsapc(String opsapc) {
        this.opsapc = opsapc;
    }

    public String getApcdcr() {
        return apcdcr;
    }

    public void setApcdcr(String apcdcr) {
        this.apcdcr = apcdcr;
    }

    public String getIntdac() {
        return intdac;
    }

    public void setIntdac(String intdac) {
        this.intdac = intdac;
    }

    public String getIntcac() {
        return intcac;
    }

    public void setIntcac(String intcac) {
        this.intcac = intcac;
    }

    public String getNdrint() {
        return ndrint;
    }

    public void setNdrint(String ndrint) {
        this.ndrint = ndrint;
    }

    public String getNcrint() {
        return ncrint;
    }

    public void setNcrint(String ncrint) {
        this.ncrint = ncrint;
    }

    public String getApckid() {
        return apckid;
    }

    public void setApckid(String apckid) {
        this.apckid = apckid;
    }
}
