package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * �Թ�֧�������Ѳ�ѯ T539
 */
public class T539 extends SOFFormBody {

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
        int beanLength = 110;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1};
            fieldLengths = new int[]{18, 60, 10, 6, 16};
        }

        private String CUSACT;          // �����˺�
        private String CUSNAM;          // ����������
        private String BNKNAM;          // ��������
        private String TXNCNT;          // �������
        private String FEEAMT;          // ������

        public String getCUSACT() {
            return CUSACT;
        }

        public void setCUSACT(String CUSACT) {
            this.CUSACT = CUSACT;
        }

        public String getCUSNAM() {
            return CUSNAM;
        }

        public void setCUSNAM(String CUSNAM) {
            this.CUSNAM = CUSNAM;
        }

        public String getBNKNAM() {
            return BNKNAM;
        }

        public void setBNKNAM(String BNKNAM) {
            this.BNKNAM = BNKNAM;
        }

        public String getTXNCNT() {
            return TXNCNT;
        }

        public void setTXNCNT(String TXNCNT) {
            this.TXNCNT = TXNCNT;
        }

        public String getFEEAMT() {
            return FEEAMT;
        }

        public void setFEEAMT(String FEEAMT) {
            this.FEEAMT = FEEAMT;
        }
    }
}
