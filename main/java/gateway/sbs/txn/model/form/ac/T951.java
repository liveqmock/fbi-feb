package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-4-30        ��Χ�ӿڹ���
 * Time: ����9:01
 * To change this template use File | Settings | File Templates.
 */
public class T951 extends SOFFormBody{
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
        int beanLength = 52;
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
            fieldLengths = new int[]{4, 32, 8, 8};
        }
        private String TERMID; //��������
        private String TERMNM; //��������
        private String CURDAT; //���������
        private String TXNSTS; //״̬

        public String getTERMID() {
            return TERMID;
        }

        public void setTERMID(String TERMID) {
            this.TERMID = TERMID;
        }

        public String getTERMNM() {
            return TERMNM;
        }

        public void setTERMNM(String TERMNM) {
            this.TERMNM = TERMNM;
        }

        public String getCURDAT() {
            return CURDAT;
        }

        public void setCURDAT(String CURDAT) {
            this.CURDAT = CURDAT;
        }

        public String getTXNSTS() {
            return TXNSTS;
        }

        public void setTXNSTS(String TXNSTS) {
            this.TXNSTS = TXNSTS;
        }
    }
}
