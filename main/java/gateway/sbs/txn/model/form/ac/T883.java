package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.SOFFormBody;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10            //˰�ʱ��ʲ�ѯ
 * Time: ����9:28
 * To change this template use File | Settings | File Templates.
 */

public class T883 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{3, 4, 9, 9, 4, 8};
    }

    private String TAXTNO;               //˰���
    private String TAXCDE;               //˰����
    private String TAXRAT;               //˰��
    private String TAXADJ;               //˰�ʵ���ֵ
    private String AMDTLR;               //�޸Ĺ�Ա
    private String UPDDAT;               //�޸�����

    public String getTAXTNO() {
        return TAXTNO;
    }

    public void setTAXTNO(String TAXTNO) {
        this.TAXTNO = TAXTNO;
    }

    public String getTAXCDE() {
        return TAXCDE;
    }

    public void setTAXCDE(String TAXCDE) {
        this.TAXCDE = TAXCDE;
    }

    public String getTAXRAT() {
        return TAXRAT;
    }

    public void setTAXRAT(String TAXRAT) {
        this.TAXRAT = TAXRAT;
    }

    public String getTAXADJ() {
        return TAXADJ;
    }

    public void setTAXADJ(String TAXADJ) {
        this.TAXADJ = TAXADJ;
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
