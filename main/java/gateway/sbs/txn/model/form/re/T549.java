package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 对公支付多笔查询 T549
 */
public class T549 extends SOFFormBody {

    private String totcnt = "0";            // 总记录数
    private String curcnt = "0";            // 本包内记录数

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
        int beanLength = 490;
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
            fieldTypes = new int[]{1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{18, 12, 15, 18, 72, 35, 72, 60, 10, 60, 12, 72, 10, 6, 4, 4, 6, 4};
        }

        private String FBTIDX;     //流水号
        private String TERMNM;     //渠道
        private BigDecimal TXNAMT;     //交易金额
        private String CUSACT;     //付款人账号
        private String ACTNAM;     //付款人名称
        private String BENACT;     //收款人账号
        private String BENNAM;     //收款人名称
        //private String LANDRS;     //落地原因
        private String RETAUX;     //附言
        private String PRCSTS;     //状态
        private String PRCMSG;     //交易信息
        private String BENBID;     //收款行行号
        private String BENBNK;     //收款行名称
        private String ORDDAT;     //交易日期
        private String ORDTIM;     //交易时间
        private String ORDTLR;     //交易柜员
        private String VCHSET;     //传票套号
        private String PRCTLR;     //审核时间
        private String PRCTIM;     //审核柜员

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

        public String getBENBID() {
            return BENBID;
        }

        public void setBENBID(String BENBID) {
            this.BENBID = BENBID;
        }

        public String getBENBNK() {
            return BENBNK;
        }

        public void setBENBNK(String BENBNK) {
            this.BENBNK = BENBNK;
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

        public String getPRCTLR() {
            return PRCTLR;
        }

        public void setPRCTLR(String PRCTLR) {
            this.PRCTLR = PRCTLR;
        }

        public String getPRCTIM() {
            return PRCTIM;
        }

        public void setPRCTIM(String PRCTIM) {
            this.PRCTIM = PRCTIM;
        }
    }
}
