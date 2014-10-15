package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10            //���ʲ�ѯ
 * Time: ����9:28
 * To change this template use File | Settings | File Templates.
 */

public class T862 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}; //15
        fieldLengths = new int[]{4, 1, 34, 4, 4, 4, 4, 4, 50, 4, 4, 4, 8, 1, 4, 4};
    }

    private String APCODE;         //������                         Y
    private String APCTYP;         //���������� 0-��ͨ�� 4-999��ʱ��Ƿ 5-������������ 6-933���  7-�Զ���ת�� 8-��ʱ�� 9-������
    private String APCNAM;         //����������                     Y
    private String GLCODE;         //����������                      Y
    private String PLCODE;         //��Ӧ������(�������������ж�Ӧ��������)  �������ACTPLC
    private String INTEXP;         //��Ϣ֧���˻�������
    private String INTINC;         //��Ϣ�����˻�������
    private String OPSAPC;         //������׼�������        Y
    private String APCDCR;         //����������              Y
    private String INTDAC;         //Ӧ����Ϣ�˻�������      Y
    private String INTCAC;         //Ӧ����Ϣ�˻�������      Y
    private String AMDTLR;         //������Ա
    private String UPDDAT;         //�޸�����
    private String CLRFLG;         //ͣ�ñ��                 Y
    private String DOMCDE;         //���ñ�־1
    private String CCYFIL;         //���ñ�־2
    private String EBKCDE;            //��Ӧ���к�����

    public String getCLRFLG() {
        return CLRFLG;
    }

    public void setCLRFLG(String CLRFLG) {
        this.CLRFLG = CLRFLG;
    }

    public String getEBKCDE() {
        return EBKCDE;
    }

    public void setEBKCDE(String EBKCDE) {
        this.EBKCDE = EBKCDE;
    }

    public String getAPCODE() {
        return APCODE;
    }

    public void setAPCODE(String APCODE) {
        this.APCODE = APCODE;
    }

    public String getAPCNAM() {
        return APCNAM;
    }

    public void setAPCNAM(String APCNAM) {
        this.APCNAM = APCNAM;
    }

    public String getAPCTYP() {
        return APCTYP;
    }

    public void setAPCTYP(String APCTYP) {
        this.APCTYP = APCTYP;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getPLCODE() {
        return PLCODE;
    }

    public void setPLCODE(String PLCODE) {
        this.PLCODE = PLCODE;
    }

    public String getINTEXP() {
        return INTEXP;
    }

    public void setINTEXP(String INTEXP) {
        this.INTEXP = INTEXP;
    }

    public String getINTINC() {
        return INTINC;
    }

    public void setINTINC(String INTINC) {
        this.INTINC = INTINC;
    }

    public String getOPSAPC() {
        return OPSAPC;
    }

    public void setOPSAPC(String OPSAPC) {
        this.OPSAPC = OPSAPC;
    }

    public String getAPCDCR() {
        return APCDCR;
    }

    public void setAPCDCR(String APCDCR) {
        this.APCDCR = APCDCR;
    }

    public String getINTDAC() {
        return INTDAC;
    }

    public void setINTDAC(String INTDAC) {
        this.INTDAC = INTDAC;
    }

    public String getINTCAC() {
        return INTCAC;
    }

    public void setINTCAC(String INTCAC) {
        this.INTCAC = INTCAC;
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

    public String getDOMCDE() {
        return DOMCDE;
    }

    public void setDOMCDE(String DOMCDE) {
        this.DOMCDE = DOMCDE;
    }

    public String getCCYFIL() {
        return CCYFIL;
    }

    public void setCCYFIL(String CCYFIL) {
        this.CCYFIL = CCYFIL;
    }

}
