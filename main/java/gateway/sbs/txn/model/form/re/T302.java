package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 7980重要凭证代号维护多笔查询响应 T121
 */
public class T302 extends SOFFormBody {


    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {


        int index = offset;
        int beanLength = 66;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            if (!bean.VCHTYP.equals("")){
                beanList.add(bean);
            }

            index += beanLength;
        } while (index < buffer.length);
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{2, 8, 8, 8, 8, 8, 8 , 8, 8};
        }

        private String VCHTYP;
        private String LWKBAL;
        private String CRNAPP;
        private String CRNUSE;
        private String CRNCHG;
        private String CRNABD;
        private String CRNLST;
        private String CRNOUT;
        private String CRNBAL;





        public String getVCHTYP() {
            return VCHTYP;
        }

        public void setVCHTYP(String VCHTYP) {
            this.VCHTYP = VCHTYP;
        }

        public String getLWKBAL() {
            return LWKBAL;
        }

        public void setLWKBAL(String LWKBAL) {
            this.LWKBAL = LWKBAL;
        }

        public String getCRNAPP() {
            return CRNAPP;
        }

        public void setCRNAPP(String CRNAPP) {
            this.CRNAPP = CRNAPP;
        }

        public String getCRNUSE() {
            return CRNUSE;
        }

        public void setCRNUSE(String CRNUSE) {
            this.CRNUSE = CRNUSE;
        }

        public String getCRNCHG() {
            return CRNCHG;
        }

        public void setCRNCHG(String CRNCHG) {
            this.CRNCHG = CRNCHG;
        }

        public String getCRNABD() {
            return CRNABD;
        }

        public void setCRNABD(String CRNABD) {
            this.CRNABD = CRNABD;
        }

        public String getCRNLST() {
            return CRNLST;
        }

        public void setCRNLST(String CRNLST) {
            this.CRNLST = CRNLST;
        }

        public String getCRNOUT() {
            return CRNOUT;
        }

        public void setCRNOUT(String CRNOUT) {
            this.CRNOUT = CRNOUT;
        }

        public String getCRNBAL() {
            return CRNBAL;
        }

        public void setCRNBAL(String CRNBAL) {
            this.CRNBAL = CRNBAL;
        }


        //    public int offset = 0;

    }
}
