package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/7/24       总分账号对照查询
 * Time: 9:32
 * To change this template use File | Settings | File Templates.
 */
public class T250 extends SOFFormBody {
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
        int beanLength = 182;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1};//5
            fieldLengths = new int[]{22, 40, 40, 40, 40};
        }

        private String CODNUM;    //分公司账号
        private String CODVAL;    //总公司账号
        private String CODVL1;    //日终保留余额
        private String CODVL2;    //上划下拨方式
        private String CODNAM;    //备注

    // = = = = = = = = = = = = = == = = = = = = =


        public String getCODNUM() {
            return CODNUM;
        }

        public void setCODNUM(String CODNUM) {
            this.CODNUM = CODNUM;
        }

        public String getCODVAL() {
            return CODVAL;
        }

        public void setCODVAL(String CODVAL) {
            this.CODVAL = CODVAL;
        }

        public String getCODVL1() {
            return CODVL1;
        }

        public void setCODVL1(String CODVL1) {
            this.CODVL1 = CODVL1;
        }

        public String getCODVL2() {
            return CODVL2;
        }

        public void setCODVL2(String CODVL2) {
            this.CODVL2 = CODVL2;
        }

        public String getCODNAM() {
            return CODNAM;
        }

        public void setCODNAM(String CODNAM) {
            this.CODNAM = CODNAM;
        }
    }
}
