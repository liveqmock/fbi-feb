package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lichao.W At 2015/4/14 9:07
 * wanglichao@163.com
 */
public class T462 extends SOFFormBody {
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
        int beanLength = 393;
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
            fieldTypes = new int[]{1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};//17
            fieldLengths = new int[]{18, 12, 15, 18, 72, 18, 72, 60, 10, 60, 10, 6, 4, 4, 4, 4, 6};
        }

        private String FBTIDX;    //��ˮ��
        private String TERMNM;    //����
        private BigDecimal TXNAMT;    //���׽��
        private String CUSACT;    //�������˺�
        private String ACTNAM;    //����������
        private String BENACT;    //�տ����˺�
        private String BENNAM;    //�տ�������
        private String RETAUX;    //ժҪ
        private String PRCSTS;    //״̬
        private String PRCMSG;    //������Ϣ
        private String ORDDAT;    //��������
        private String ORDTIM;    //����ʱ��
        private String ORDTLR;    //���׹�Ա
        private String VCHSET;    //��Ʊ�׺�
        private String ANACDE;    //������
        private String PRCTIM;    //������Ա
        private String PRCTLR;    //����ʱ��

        // = = = = = = = = = = = = = == = = = = = = =


        public String getFBTIDX() {
            return FBTIDX;
        }

        public void setFBTIDX(String FBTIDX) {
            this.FBTIDX = FBTIDX;
        }

        public String getTERMNM() {
            return TERMNM;
        }

        public void setTERMNM(String TERMNM) {
            this.TERMNM = TERMNM;
        }

        public BigDecimal getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(BigDecimal TXNAMT) {
            this.TXNAMT = TXNAMT;
        }

        public String getCUSACT() {
            return CUSACT;
        }

        public void setCUSACT(String CUSACT) {
            this.CUSACT = CUSACT;
        }

        public String getACTNAM() {
            return ACTNAM;
        }

        public void setACTNAM(String ACTNAM) {
            this.ACTNAM = ACTNAM;
        }

        public String getBENACT() {
            return BENACT;
        }

        public void setBENACT(String BENACT) {
            this.BENACT = BENACT;
        }

        public String getBENNAM() {
            return BENNAM;
        }

        public void setBENNAM(String BENNAM) {
            this.BENNAM = BENNAM;
        }

        public String getRETAUX() {
            return RETAUX;
        }

        public void setRETAUX(String RETAUX) {
            this.RETAUX = RETAUX;
        }

        public String getPRCSTS() {
            return PRCSTS;
        }

        public void setPRCSTS(String PRCSTS) {
            this.PRCSTS = PRCSTS;
        }

        public String getPRCMSG() {
            return PRCMSG;
        }

        public void setPRCMSG(String PRCMSG) {
            this.PRCMSG = PRCMSG;
        }

        public String getORDDAT() {
            return ORDDAT;
        }

        public void setORDDAT(String ORDDAT) {
            this.ORDDAT = ORDDAT;
        }

        public String getORDTIM() {
            return ORDTIM;
        }

        public void setORDTIM(String ORDTIM) {
            this.ORDTIM = ORDTIM;
        }

        public String getORDTLR() {
            return ORDTLR;
        }

        public void setORDTLR(String ORDTLR) {
            this.ORDTLR = ORDTLR;
        }

        public String getVCHSET() {
            return VCHSET;
        }

        public void setVCHSET(String VCHSET) {
            this.VCHSET = VCHSET;
        }

        public String getANACDE() {
            return ANACDE;
        }

        public void setANACDE(String ANACDE) {
            this.ANACDE = ANACDE;
        }

        public String getPRCTIM() {
            return PRCTIM;
        }

        public void setPRCTIM(String PRCTIM) {
            this.PRCTIM = PRCTIM;
        }

        public String getPRCTLR() {
            return PRCTLR;
        }

        public void setPRCTLR(String PRCTLR) {
            this.PRCTLR = PRCTLR;
        }
    }
}
