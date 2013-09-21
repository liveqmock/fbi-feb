package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-10            //单笔查询
 * Time: 下午9:28
 * To change this template use File | Settings | File Templates.
 */

public class T862 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        //int beanLength = 59;
        //do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
           // index += beanLength;
       //} while (index < buffer.length);
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class Bean extends AssembleModel {

        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}; //15
            fieldLengths = new int[]{4, 1, 34, 4, 4, 4, 4, 4, 50, 4, 4, 4, 8, 4, 4};
        }

        private String APCODE;         //核算码                         Y
        private String APCNAM;         //核算码名称                     Y
        private String APCTYP;         //核算码类型 0-普通类 4-999临时存欠 5-外汇买卖人民币 6-933外币  7-自动对转类 8-临时类 9-催收类
        private String GLCODE;         //所属总账码                      Y
        private String PLCODE;         //对应损益码(损益类核算码才有对应的损益码)  损益码表ACTPLC
        private String INTEXP;         //利息支出账户核算码
        private String INTINC;         //利息收入账户核算码
        private String OPSAPC;         //所属标准核算代码        Y
        private String APCDCR;         //核算码描述              Y
        private String INTDAC;         //应付利息账户核算码      Y
        private String INTCAC;         //应收利息账户核算码      Y
        private String AMDTLR;         //建立柜员
        private String UPDDAT;         //修改日期x
        private String DOMCDE;         //备用标志1
        private String CCYFIL;          //备用标志2

        public String getAPCODE() {
            return APCODE;
        }

        public void setAPCODE(String APCODE) {
            this.APCODE = APCODE;
        }

        public String getAPCNAM() {
            return APCNAM;
        }

        public void setAPCNAM(String APCNAM) {
            this.APCNAM = APCNAM;
        }

        public String getAPCTYP() {
            return APCTYP;
        }

        public void setAPCTYP(String APCTYP) {
            this.APCTYP = APCTYP;
        }

        public String getGLCODE() {
            return GLCODE;
        }

        public void setGLCODE(String GLCODE) {
            this.GLCODE = GLCODE;
        }

        public String getPLCODE() {
            return PLCODE;
        }

        public void setPLCODE(String PLCODE) {
            this.PLCODE = PLCODE;
        }

        public String getINTEXP() {
            return INTEXP;
        }

        public void setINTEXP(String INTEXP) {
            this.INTEXP = INTEXP;
        }

        public String getINTINC() {
            return INTINC;
        }

        public void setINTINC(String INTINC) {
            this.INTINC = INTINC;
        }

        public String getOPSAPC() {
            return OPSAPC;
        }

        public void setOPSAPC(String OPSAPC) {
            this.OPSAPC = OPSAPC;
        }

        public String getAPCDCR() {
            return APCDCR;
        }

        public void setAPCDCR(String APCDCR) {
            this.APCDCR = APCDCR;
        }

        public String getINTDAC() {
            return INTDAC;
        }

        public void setINTDAC(String INTDAC) {
            this.INTDAC = INTDAC;
        }

        public String getINTCAC() {
            return INTCAC;
        }

        public void setINTCAC(String INTCAC) {
            this.INTCAC = INTCAC;
        }

        public String getAMDTLR() {
            return AMDTLR;
        }

        public void setAMDTLR(String AMDTLR) {
            this.AMDTLR = AMDTLR;
        }

        public String getUPDDAT() {
            return UPDDAT;
        }

        public void setUPDDAT(String UPDDAT) {
            this.UPDDAT = UPDDAT;
        }

        public String getDOMCDE() {
            return DOMCDE;
        }

        public void setDOMCDE(String DOMCDE) {
            this.DOMCDE = DOMCDE;
        }
        public String getCCYFIL() {
            return CCYFIL;
        }

        public void setCCYFIL(String CCYFIL) {
            this.CCYFIL = CCYFIL;
        }
    }
}
