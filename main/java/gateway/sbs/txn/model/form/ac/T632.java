package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/6     外围传票应答报文
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
public class T632 extends SOFFormBody {

    private String totcnt = "0"; // 总记录数
    private String curcnt = "0"; // 本包内记录数
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
        int beanLength = 108;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length);
    }

    public String getTotcnt() {
        return totcnt;
    }

    public void setTotcnt(String totcnt) {
        this.totcnt = totcnt;
    }

    public String getCurcnt() {
        return curcnt;
    }

    public void setCurcnt(String curcnt) {
        this.curcnt = curcnt;
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 3, 1, 1, 1, 1};//10
            fieldLengths = new int[]{18, 4, 4, 2, 14, 17, 8, 8, 1, 32};
        }

        private String FBTIDX;   // 流水号
        private String TLRNUM;   // 柜员号
        private String VCHSET;   // 传票套号
        private String SETSEQ;   // 套内序号
        private String ACTNUM;   //ZHANG HAO
        //        private String CUSIDT;   // 客户号
//        private String APCODE;   // 核算码
//        private String CURCDE;   // 币别
        private BigDecimal TXNAMT;   // 交易金额
        private String ERYDAT;   // 记账日期
        private String VALDAT;   // 起息日期
        private String RVSLBL;   // 冲账标志
        private String FURINF;   // 备注

        public String getFBTIDX() {
            return FBTIDX;
        }

        public void setFBTIDX(String FBTIDX) {
            this.FBTIDX = FBTIDX;
        }

        public String getTLRNUM() {
            return TLRNUM;
        }

        public void setTLRNUM(String TLRNUM) {
            this.TLRNUM = TLRNUM;
        }

        public String getVCHSET() {
            return VCHSET;
        }

        public void setVCHSET(String VCHSET) {
            this.VCHSET = VCHSET;
        }

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

        public BigDecimal getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(BigDecimal TXNAMT) {
            this.TXNAMT = TXNAMT;
        }

        public String getERYDAT() {
            return ERYDAT;
        }

        public void setERYDAT(String ERYDAT) {
            this.ERYDAT = ERYDAT;
        }

        public String getVALDAT() {
            return VALDAT;
        }

        public void setVALDAT(String VALDAT) {
            this.VALDAT = VALDAT;
        }

        public String getRVSLBL() {
            return RVSLBL;
        }

        public void setRVSLBL(String RVSLBL) {
            this.RVSLBL = RVSLBL;
        }

        public String getFURINF() {
            return FURINF;
        }

        public void setFURINF(String FURINF) {
            this.FURINF = FURINF;
        }
    }


}
