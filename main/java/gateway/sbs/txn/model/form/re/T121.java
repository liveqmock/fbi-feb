package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 7980重要凭证代号维护多笔查询响应 T121
 */
public class T121 extends SOFFormBody {

    private FormBodyHeader formBodyHeader = new FormBodyHeader();
    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {

        formBodyHeader.assembleFields(0, buffer);
        int index = offset + 4;
        int beanLength = 54;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < (buffer.length - 4));
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class FormBodyHeader extends AssembleModel {
        {
            fieldTypes = new int[]{1};
            fieldLengths = new int[]{4};
        }

        private String TOTALNUM;           //总笔数

        public String getTOTALNUM() {
            return TOTALNUM;
        }

        public void setTOTALNUM(String TOTALNUM) {
            this.TOTALNUM = TOTALNUM;
        }
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 3};
            fieldLengths = new int[]{2, 6, 30, 1, 13};
        }

        private String VCHTYP;          // 凭证种类
        private String OUTITM;          // 表外核算码
        private String VCHNAM;          // 凭证名称
        private String SCTMAK;          // 是否有价证券
        private BigDecimal VCHAMT;          // 凭证金额


        public String getVCHTYP() {
            return VCHTYP;
        }

        public void setVCHTYP(String VCHTYP) {
            this.VCHTYP = VCHTYP;
        }

        public String getOUTITM() {
            return OUTITM;
        }

        public void setOUTITM(String OUTITM) {
            this.OUTITM = OUTITM;
        }

        public String getVCHNAM() {
            return VCHNAM;
        }

        public void setVCHNAM(String VCHNAM) {
            this.VCHNAM = VCHNAM;
        }

        public String getSCTMAK() {
            return SCTMAK;
        }

        public void setSCTMAK(String SCTMAK) {
            this.SCTMAK = SCTMAK;
        }

        public BigDecimal getVCHAMT() {
            return VCHAMT;
        }

        public void setVCHAMT(BigDecimal VCHAMT) {
            this.VCHAMT = VCHAMT;
        }


//    public int offset = 0;

    }
}
