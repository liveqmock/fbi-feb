package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * �Թ�֧����ʲ�ѯ T536
 */
public class T536 extends SOFFormBody {

    private String totcnt = "0";            // �ܼ�¼��
    private String curcnt = "0";            // �����ڼ�¼��

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        byte[] totcntBytes = new byte[4];
        byte[] curcntBytes = new byte[4];
        System.arraycopy(buffer, offset, totcntBytes, 0, 4);
        totcnt = new String(totcntBytes);
        System.arraycopy(buffer, offset + 4, curcntBytes, 0, 4);
        curcnt = new String(curcntBytes);

        int index = offset + 8;
        int beanLength = 63;
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
            fieldTypes = new int[]{1, 1, 1, 1};
            fieldLengths = new int[]{18, 22, 15, 8};
        }

        private String FBTIDX;          // ������
        private String CUSACT;          // �����˺�
        private String TXNAMT;          // ���׽��
        private String PRCSTS;          // ����״̬

        public String getFBTIDX() {
            return FBTIDX;
        }

        public void setFBTIDX(String FBTIDX) {
            this.FBTIDX = FBTIDX;
        }

        public String getCUSACT() {
            return CUSACT;
        }

        public void setCUSACT(String CUSACT) {
            this.CUSACT = CUSACT;
        }

        public String getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(String TXNAMT) {
            this.TXNAMT = TXNAMT;
        }

        public String getPRCSTS() {
            return PRCSTS;
        }

        public void setPRCSTS(String PRCSTS) {
            this.PRCSTS = PRCSTS;
        }
    }
}
