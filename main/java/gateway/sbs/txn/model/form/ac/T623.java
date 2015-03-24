package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Lichao.W
 * On 2015/3/24 At 9:20
 */
public class T623 extends SOFFormBody{
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
        int beanLength = 66;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1, 3};//6
            fieldLengths = new int[]{3, 1, 1, 4, 40, 17};
        }

        private String SEQNUM;   //序号
        private String PLTYPE;   //类别
        private String PLCLAS;   //级别
        private String PLCODE;   //代号
        private String PLNAME;   //名称
        private BigDecimal PLAMNT;   //金额

        public String getSEQNUM() {
            return SEQNUM;
        }

        public void setSEQNUM(String SEQNUM) {
            this.SEQNUM = SEQNUM;
        }

        public String getPLTYPE() {
            return PLTYPE;
        }

        public void setPLTYPE(String PLTYPE) {
            this.PLTYPE = PLTYPE;
        }

        public String getPLCLAS() {
            return PLCLAS;
        }

        public void setPLCLAS(String PLCLAS) {
            this.PLCLAS = PLCLAS;
        }

        public String getPLCODE() {
            return PLCODE;
        }

        public void setPLCODE(String PLCODE) {
            this.PLCODE = PLCODE;
        }

        public String getPLNAME() {
            return PLNAME;
        }

        public void setPLNAME(String PLNAME) {
            this.PLNAME = PLNAME;
        }

        public BigDecimal getPLAMNT() {
            return PLAMNT;
        }

        public void setPLAMNT(BigDecimal PLAMNT) {
            this.PLAMNT = PLAMNT;
        }
    }
}
