package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 对公支付手续费查询 T539
 */
public class T539 extends SOFFormBody {

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
            fieldTypes = new int[]{1, 1, 1, 5, 3};
            fieldLengths = new int[]{18, 60, 10, 6, 16};
        }

        private String CUSACT;          // 付款账号
        private String CUSNAM;          // 付款人名称
        private String BNKNAM;          // 付款银行
        private int TXNCNT;          // 付款笔数
        private BigDecimal FEEAMT;          // 手续费

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

        public int getTXNCNT() {
            return TXNCNT;
        }

        public void setTXNCNT(int TXNCNT) {
            this.TXNCNT = TXNCNT;
        }

        public BigDecimal getFEEAMT() {
            return FEEAMT;
        }

        public void setFEEAMT(BigDecimal FEEAMT) {
            this.FEEAMT = FEEAMT;
        }
    }
}
