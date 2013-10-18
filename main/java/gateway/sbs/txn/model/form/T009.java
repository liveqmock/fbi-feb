package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class T009 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();
    @Override
    public void assembleFields(int offset, byte[] buffer) {

        int index = offset;
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
    }

    public List<Bean> getBeanList() {
        return beanList;
    }


    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1};
            fieldLengths = new int[]{4, 2, 13, 13, 13};
        }

        private String TXNTLR;          // ƾ֤����
        private String VCHTYP;          // ���������
        private String VCHCNT;          // ƾ֤����
        private String BEGNUM;          // ƾ֤���
        private String ENDNUM;          // ƾ֤���




        public String getVCHTYP() {
            return VCHTYP;
        }

        public void setVCHTYP(String VCHTYP) {
            this.VCHTYP = VCHTYP;
        }

        public String getENDNUM() {
            return ENDNUM;
        }

        public void setENDNUM(String ENDNUM) {
            this.ENDNUM = ENDNUM;
        }

        public String getBEGNUM() {
            return BEGNUM;
        }

        public void setBEGNUM(String BEGNUM) {
            this.BEGNUM = BEGNUM;
        }

        public String getVCHCNT() {
            return VCHCNT;
        }

        public void setVCHCNT(String VCHCNT) {
            this.VCHCNT = VCHCNT;
        }

        public String getTXNTLR() {
            return TXNTLR;
        }

        public void setTXNTLR(String TXNTLR) {
            this.TXNTLR = TXNTLR;
        }
    }

        //    public int offset = 0;


}
