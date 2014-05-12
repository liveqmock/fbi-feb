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
public class T554 extends SOFFormBody {
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
        int beanLength = 208;
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
            fieldTypes = new int[]{1, 1, 1, 3, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1};//18
            fieldLengths = new int[]{18, 18, 72, 16, 10, 10,
                    10, 2, 1, 3, 10, 2, 2, 13, 1, 18, 1, 1};
        }

        private String BOKNUM;    //账户号
        private String ACTNUM;    //账号
        private String ACTNAM;    //户名
        private BigDecimal CURBAL;    //余额
        private String OPNDAT;    //开户日
        private String VALDAT;    //起息日
        private String EXPDAT;    //到期日
        private String DPTPRD;    //存期
        private String ATOFLG;    //自动转存标志
        private String INTCDE;    //利率码
        private String INTRAT;     //利率
        private String DPTTYP;     //存款种类
        private String VCHTYP;     //凭证种类
        private String VCHNUM;     //凭证号码
        private String PASTYP;     //证件种类
        private String PASSNO;    //证件号码
        private String DRAMDE;     //支取方式
        private String ACTSTS;     //状态

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

        public String getACTNAM() {
            return ACTNAM;
        }

        public void setACTNAM(String ACTNAM) {
            this.ACTNAM = ACTNAM;
        }

        public BigDecimal getCURBAL() {
            return CURBAL;
        }

        public void setCURBAL(BigDecimal CURBAL) {
            this.CURBAL = CURBAL;
        }

        public String getOPNDAT() {
            return OPNDAT;
        }

        public void setOPNDAT(String OPNDAT) {
            this.OPNDAT = OPNDAT;
        }

        public String getVALDAT() {
            return VALDAT;
        }

        public void setVALDAT(String VALDAT) {
            this.VALDAT = VALDAT;
        }

        public String getEXPDAT() {
            return EXPDAT;
        }

        public void setEXPDAT(String EXPDAT) {
            this.EXPDAT = EXPDAT;
        }

        public String getDPTPRD() {
            return DPTPRD;
        }

        public void setDPTPRD(String DPTPRD) {
            this.DPTPRD = DPTPRD;
        }

        public String getATOFLG() {
            return ATOFLG;
        }

        public void setATOFLG(String ATOFLG) {
            this.ATOFLG = ATOFLG;
        }

        public String getINTCDE() {
            return INTCDE;
        }

        public void setINTCDE(String INTCDE) {
            this.INTCDE = INTCDE;
        }

        public String getINTRAT() {
            return INTRAT;
        }

        public void setINTRAT(String INTRAT) {
            this.INTRAT = INTRAT;
        }

        public String getDPTTYP() {
            return DPTTYP;
        }

        public void setDPTTYP(String DPTTYP) {
            this.DPTTYP = DPTTYP;
        }

        public String getVCHTYP() {
            return VCHTYP;
        }

        public void setVCHTYP(String VCHTYP) {
            this.VCHTYP = VCHTYP;
        }

        public String getVCHNUM() {
            return VCHNUM;
        }

        public void setVCHNUM(String VCHNUM) {
            this.VCHNUM = VCHNUM;
        }

        public String getPASTYP() {
            return PASTYP;
        }

        public void setPASTYP(String PASTYP) {
            this.PASTYP = PASTYP;
        }

        public String getPASSNO() {
            return PASSNO;
        }

        public void setPASSNO(String PASSNO) {
            this.PASSNO = PASSNO;
        }

        public String getDRAMDE() {
            return DRAMDE;
        }

        public void setDRAMDE(String DRAMDE) {
            this.DRAMDE = DRAMDE;
        }

        public String getACTSTS() {
            return ACTSTS;
        }

        public void setACTSTS(String ACTSTS) {
            this.ACTSTS = ACTSTS;
        }
    }
}
