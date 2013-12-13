package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-20
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class T851 extends SOFFormBody{
    private FormBodyHeader formBodyHeader = new FormBodyHeader();
    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        formBodyHeader.assembleFields(0, buffer);
        int index = offset + 12;
        int beanLength = 171;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            if (!bean.getCUSIDT().equals("")) {
                beanList.add(bean);
            } else {
                // System.out.print("=================");
            }
            index += beanLength;
        } while (index < buffer.length - 12);
    }

    public List<Bean> getBeanList() {
        return beanList;
    }
    public class FormBodyHeader extends AssembleModel {
        {
            fieldTypes = new int[]{1,1};
            fieldLengths = new int[]{6,6};
        }
        private String TOTCNT;
        private String CURCNT;

        public String getTOTCNT() {
            return TOTCNT;
        }

        public void setTOTCNT(String TOTCNT) {
            this.TOTCNT = TOTCNT;
        }

        public String getCURCNT() {
            return CURCNT;
        }

        public void setCURCNT(String CURCNT) {
            this.CURCNT = CURCNT;
        }
    }
    public class Bean extends AssembleModel {


        private String CUSIDT;  //客户号
        private String APCODE;  //核算码
        private String CURCDE;  //币别
        private String ERYDAT;  //交易日期
        private BigDecimal TXNAMT;  //金额
        private String TLRNUM;  //柜员
        private String VCHSET;  //传票套号
        private String SETSEQ;  //套内序号
        private String FURINF;  //摘要
        private String ERYTIM;  //交易时间
        private String ANACDE;  //统计码
        private String VALDAT;  //起息日期
        private String ACTNAM;  //账户名称

        {
            fieldTypes = new int[]{1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1};//10
            fieldLengths = new int[]{7, 4, 3, 8, 17, 4, 4, 2, 32, 6, 4 ,8, 72};
        }

        public String getCUSIDT() {
            return CUSIDT;
        }

        public void setCUSIDT(String CUSIDT) {
            this.CUSIDT = CUSIDT;
        }

        public String getAPCODE() {
            return APCODE;
        }

        public void setAPCODE(String APCODE) {
            this.APCODE = APCODE;
        }

        public String getCURCDE() {
            return CURCDE;
        }

        public void setCURCDE(String CURCDE) {
            this.CURCDE = CURCDE;
        }

        public String getERYDAT() {
            return ERYDAT;
        }

        public void setERYDAT(String ERYDAT) {
            this.ERYDAT = ERYDAT;
        }

        public BigDecimal getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(BigDecimal TXNAMT) {
            this.TXNAMT = TXNAMT;
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

        public String getFURINF() {
            return FURINF;
        }

        public void setFURINF(String FURINF) {
            this.FURINF = FURINF;
        }

        public String getERYTIM() {
            return ERYTIM;
        }

        public void setERYTIM(String ERYTIM) {
            this.ERYTIM = ERYTIM;
        }

        public String getANACDE() {
            return ANACDE;
        }

        public void setANACDE(String ANACDE) {
            this.ANACDE = ANACDE;
        }

        public String getVALDAT() {
            return VALDAT;
        }

        public void setVALDAT(String VALDAT) {
            this.VALDAT = VALDAT;
        }

        public String getACTNAM() {
            return ACTNAM;
        }

        public void setACTNAM(String ACTNAM) {
            this.ACTNAM = ACTNAM;
        }
    }

    public FormBodyHeader getFormBodyHeader() {
        return formBodyHeader;
    }

    public void setFormBodyHeader(FormBodyHeader formBodyHeader) {
        this.formBodyHeader = formBodyHeader;
    }
}
