package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-12
 * Time: 上午9:06
 * To change this template use File | Settings | File Templates.
 */
public class T555 extends SOFFormBody {
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
        int beanLength = 171;
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
            fieldTypes = new int[]{1, 1, 1, 1, 3, 1, 1, 1, 1};//9
            fieldLengths = new int[]{18, 18, 16, 10, 16, 10, 1, 10, 72};
        }

        private String BOKNUM;    //账户号
        private String ACTNUM;    //账号
        private String ADVNAM;    //通知单号
        private String ADVDAT;    //通知日期
        private BigDecimal ADVAMT;    //通知金额
        private String DCDDAT;    //协定提款日
        private String ADVFLG;    //通知状态
        private String CNCDAT;    //撤销通知日期
        private String ACTNAM;    //户名

        // = = = = = = = = = = = = = == = = = = = = =

        public String getBOKNUM() {
            return BOKNUM;
        }

        public void setBOKNUM(String BOKNUM) {
            this.BOKNUM = BOKNUM;
        }

        public String getACTNUM() {
            return ACTNUM;
        }

        public void setACTNUM(String ACTNUM) {
            this.ACTNUM = ACTNUM;
        }

        public String getADVNAM() {
            return ADVNAM;
        }

        public void setADVNAM(String ADVNAM) {
            this.ADVNAM = ADVNAM;
        }

        public String getADVDAT() {
            return ADVDAT;
        }

        public void setADVDAT(String ADVDAT) {
            this.ADVDAT = ADVDAT;
        }

        public BigDecimal getADVAMT() {
            return ADVAMT;
        }

        public void setADVAMT(BigDecimal ADVAMT) {
            this.ADVAMT = ADVAMT;
        }

        public String getDCDDAT() {
            return DCDDAT;
        }

        public void setDCDDAT(String DCDDAT) {
            this.DCDDAT = DCDDAT;
        }

        public String getADVFLG() {
            return ADVFLG;
        }

        public void setADVFLG(String ADVFLG) {
            this.ADVFLG = ADVFLG;
        }

        public String getCNCDAT() {
            return CNCDAT;
        }

        public void setCNCDAT(String CNCDAT) {
            this.CNCDAT = CNCDAT;
        }

        public String getACTNAM() {
            return ACTNAM;
        }

        public void setACTNAM(String ACTNAM) {
            this.ACTNAM = ACTNAM;
        }
    }
}
