package gateway.sbs.txn.model.form.re;

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
        int beanLength = 45;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1};
            fieldLengths = new int[]{4, 2, 13, 13, 13};
        }

        private String TXNTLR;          // 凭证种类
        private String VCHTYP;          // 表外核算码
        private String VCHCNT;          // 凭证名称
        private String BEGNUM;          // 凭证金额
        private String ENDNUM;          // 凭证金额




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
