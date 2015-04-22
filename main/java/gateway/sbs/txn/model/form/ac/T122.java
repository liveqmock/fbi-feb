package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lichao.W At 2015/4/20 12:46
 * wanglichao@163.com
 */
public class T122 extends SOFFormBody {
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
        int beanLength = 117;
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
            fieldTypes = new int[]{1, 1, 4, 1};//4
            fieldLengths = new int[]{18, 72, 17, 10};
        }

        private String ACTNUM;    //账号
        private String ACTNAM;    //户名
        private BigDecimal ACTBAL;    //余额
        private String OPNDAT;    //开户日期

        // = = = = = = = = = = = = = == = = = = = = =


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

        public BigDecimal getACTBAL() {
            return ACTBAL;
        }

        public void setACTBAL(BigDecimal ACTBAL) {
            this.ACTBAL = ACTBAL;
        }

        public String getOPNDAT() {
            return OPNDAT;
        }

        public void setOPNDAT(String OPNDAT) {
            this.OPNDAT = OPNDAT;
        }
    }
}
