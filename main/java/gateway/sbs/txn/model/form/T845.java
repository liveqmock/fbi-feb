package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-25
 * Time: ����5:02
 * To change this template use File | Settings | File Templates.
 */
public class T845 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 122;
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

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};//12
            fieldLengths = new int[]{2, 14, 20, 2, 21, 32, 4, 1, 8, 12, 3, 3};//122
        }

        private String SETSEQ;   //       ��Ʊ�������
        private String ACTNUM;   //               �˺�
        private String OLDACN;   //       ���ʺ�
        private String RVSLBL;   //               ���˱�־
        private String  TXNAMT;   //       ���׽��
        private String COMMNT;   //               ��ע
        private String VCHSET;   //       ��Ʊ�׺�
        private String RECSTS;   //               ��¼״̬
        private String ERYTIM;   //       ����ʱ��
        private String FXRATE;   //               ����
        private String VCHATT;   //       ������
        private String SECCCY;   //               �ڶ�����

        public String getSETSEQ() {
            return SETSEQ;
        }

        public void setSETSEQ(String SETSEQ) {
            this.SETSEQ = SETSEQ;
        }

        public String getACTNUM() {
            return ACTNUM;
        }

        public void setACTNUM(String ACTNUM) {
            this.ACTNUM = ACTNUM;
        }

        public String getOLDACN() {
            return OLDACN;
        }

        public void setOLDACN(String OLDACN) {
            this.OLDACN = OLDACN;
        }

        public String getRVSLBL() {
            return RVSLBL;
        }

        public void setRVSLBL(String RVSLBL) {
            this.RVSLBL = RVSLBL;
        }

        public String getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(String TXNAMT) {
            this.TXNAMT = TXNAMT;
        }

        public String getCOMMNT() {
            return COMMNT;
        }

        public void setCOMMNT(String COMMNT) {
            this.COMMNT = COMMNT;
        }

        public String getVCHSET() {
            return VCHSET;
        }

        public void setVCHSET(String VCHSET) {
            this.VCHSET = VCHSET;
        }

        public String getRECSTS() {
            return RECSTS;
        }

        public void setRECSTS(String RECSTS) {
            this.RECSTS = RECSTS;
        }

        public String getERYTIM() {
            return ERYTIM;
        }

        public void setERYTIM(String ERYTIM) {
            this.ERYTIM = ERYTIM;
        }

        public String getFXRATE() {
            return FXRATE;
        }

        public void setFXRATE(String FXRATE) {
            this.FXRATE = FXRATE;
        }

        public String getVCHATT() {
            return VCHATT;
        }

        public void setVCHATT(String VCHATT) {
            this.VCHATT = VCHATT;
        }

        public String getSECCCY() {
            return SECCCY;
        }

        public void setSECCCY(String SECCCY) {
            this.SECCCY = SECCCY;
        }
    }
}
