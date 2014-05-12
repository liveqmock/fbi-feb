package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-12
 * Time: ����9:06
 * To change this template use File | Settings | File Templates.
 */
public class T554 extends SOFFormBody {
    private String totcnt = "0";            // �ܼ�¼��
    private String curcnt = "0";            // �����ڼ�¼��

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        byte[] totcntBytes = new byte[6];
        byte[] curcntBytes = new byte[6];
        System.arraycopy(buffer, offset, totcntBytes, 0, 6);
        totcnt = new String(totcntBytes);
        System.arraycopy(buffer, offset + 6, curcntBytes, 0, 6);
        curcnt = new String(curcntBytes);

        int index = offset + 12;
        int beanLength = 208;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length);
    }


    public List<Bean> getBeanList() {
        return beanList;
    }

    public String getTotcnt() {
        return totcnt;
    }

    public String getCurcnt() {
        return curcnt;
    }

    public void setTotcnt(String totcnt) {
        this.totcnt = totcnt;
    }

    public void setCurcnt(String curcnt) {
        this.curcnt = curcnt;
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 3, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1};//18
            fieldLengths = new int[]{18, 18, 72, 16, 10, 10,
                    10, 2, 1, 3, 10, 2, 2, 13, 1, 18, 1, 1};
        }

        private String BOKNUM;    //�˻���
        private String ACTNUM;    //�˺�
        private String ACTNAM;    //����
        private BigDecimal CURBAL;    //���
        private String OPNDAT;    //������
        private String VALDAT;    //��Ϣ��
        private String EXPDAT;    //������
        private String DPTPRD;    //����
        private String ATOFLG;    //�Զ�ת���־
        private String INTCDE;    //������
        private String INTRAT;     //����
        private String DPTTYP;     //�������
        private String VCHTYP;     //ƾ֤����
        private String VCHNUM;     //ƾ֤����
        private String PASTYP;     //֤������
        private String PASSNO;    //֤������
        private String DRAMDE;     //֧ȡ��ʽ
        private String ACTSTS;     //״̬

    // = = = = = = = = = = = = = == = = = = = = =

        public String getBOKNUM() {
            return BOKNUM;
        }

        public void setBOKNUM(String BOKNUM) {
            this.BOKNUM = BOKNUM;
        }

        public String getACTNUM() {
            return ACTNUM;
        }

        public void setACTNUM(String ACTNUM) {
            this.ACTNUM = ACTNUM;
        }

        public String getACTNAM() {
            return ACTNAM;
        }

        public void setACTNAM(String ACTNAM) {
            this.ACTNAM = ACTNAM;
        }

        public BigDecimal getCURBAL() {
            return CURBAL;
        }

        public void setCURBAL(BigDecimal CURBAL) {
            this.CURBAL = CURBAL;
        }

        public String getOPNDAT() {
            return OPNDAT;
        }

        public void setOPNDAT(String OPNDAT) {
            this.OPNDAT = OPNDAT;
        }

        public String getVALDAT() {
            return VALDAT;
        }

        public void setVALDAT(String VALDAT) {
            this.VALDAT = VALDAT;
        }

        public String getEXPDAT() {
            return EXPDAT;
        }

        public void setEXPDAT(String EXPDAT) {
            this.EXPDAT = EXPDAT;
        }

        public String getDPTPRD() {
            return DPTPRD;
        }

        public void setDPTPRD(String DPTPRD) {
            this.DPTPRD = DPTPRD;
        }

        public String getATOFLG() {
            return ATOFLG;
        }

        public void setATOFLG(String ATOFLG) {
            this.ATOFLG = ATOFLG;
        }

        public String getINTCDE() {
            return INTCDE;
        }

        public void setINTCDE(String INTCDE) {
            this.INTCDE = INTCDE;
        }

        public String getINTRAT() {
            return INTRAT;
        }

        public void setINTRAT(String INTRAT) {
            this.INTRAT = INTRAT;
        }

        public String getDPTTYP() {
            return DPTTYP;
        }

        public void setDPTTYP(String DPTTYP) {
            this.DPTTYP = DPTTYP;
        }

        public String getVCHTYP() {
            return VCHTYP;
        }

        public void setVCHTYP(String VCHTYP) {
            this.VCHTYP = VCHTYP;
        }

        public String getVCHNUM() {
            return VCHNUM;
        }

        public void setVCHNUM(String VCHNUM) {
            this.VCHNUM = VCHNUM;
        }

        public String getPASTYP() {
            return PASTYP;
        }

        public void setPASTYP(String PASTYP) {
            this.PASTYP = PASTYP;
        }

        public String getPASSNO() {
            return PASSNO;
        }

        public void setPASSNO(String PASSNO) {
            this.PASSNO = PASSNO;
        }

        public String getDRAMDE() {
            return DRAMDE;
        }

        public void setDRAMDE(String DRAMDE) {
            this.DRAMDE = DRAMDE;
        }

        public String getACTSTS() {
            return ACTSTS;
        }

        public void setACTSTS(String ACTSTS) {
            this.ACTSTS = ACTSTS;
        }
    }
}
