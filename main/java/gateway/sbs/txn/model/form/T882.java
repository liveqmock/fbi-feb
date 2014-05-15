package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10            //�����뵥�ʲ�ѯ
 * Time: ����9:28
 * To change this template use File | Settings | File Templates.
 */

public class T882 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{2, 4, 34, 1, 1, 1, 1, 1, 10, 4, 8};
    }

    private String ALCODE;               //�ʲ���ծ��Ŀ��
    private String GLCODE;               //����Ŀ��������
    private String ALCNAM;               //��Ŀ����
    private String ALCTYP;               //�ʲ���ծ����
    private String DCTYPE;               //����������ʶ
    private String OPRFLG;               //������
    private String SUMFLG;               //��Ŀ�ܼƱ�־
    private String GLCFLG;               //ͳһ��ƿ�Ŀ��־
    private String ASTRWT;               //�ʲ�����Ȩ��
    private String AMDTLR;               //�޸Ĺ�Ա
    private String UPDDAT;               //����޸�����

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
