package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 9805利率多笔查询响应 T805
 */
public class T805 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 59;
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

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{3, 8, 3, 11, 4, 4, 1, 1, 24};
        }

        private String IRTCDE;          // 利率种类码
        private String EXTDAT;          // 启用日期
        private String CURCDE;          // 币别
        private String BASIRT;          // 基准利率
        private String FLTMAX;          // 浮动上限
        private String FLTMIN;          // 浮动下限
        private String FLTFLG;          // 浮动标志
        private String CDTYPE;          // 借贷别
        private String IRTNAM;          // 利率名称

        //    public int offset = 0;
        public String getIRTCDE() {
            return IRTCDE;
        }

        public void setIRTCDE(String IRTCDE) {
            this.IRTCDE = IRTCDE;
        }

        public String getEXTDAT() {
            return EXTDAT;
        }

        public void setEXTDAT(String EXTDAT) {
            this.EXTDAT = EXTDAT;
        }

        public String getCURCDE() {
            return CURCDE;
        }

        public void setCURCDE(String CURCDE) {
            this.CURCDE = CURCDE;
        }

        public String getBASIRT() {
            return BASIRT;
        }

        public void setBASIRT(String BASIRT) {
            this.BASIRT = BASIRT;
        }

        public String getFLTMAX() {
            return FLTMAX;
        }

        public void setFLTMAX(String FLTMAX) {
            this.FLTMAX = FLTMAX;
        }

        public String getFLTMIN() {
            return FLTMIN;
        }

        public void setFLTMIN(String FLTMIN) {
            this.FLTMIN = FLTMIN;
        }

        public String getFLTFLG() {
            return FLTFLG;
        }

        public void setFLTFLG(String FLTFLG) {
            this.FLTFLG = FLTFLG;
        }

        public String getCDTYPE() {
            return CDTYPE;
        }

        public void setCDTYPE(String CDTYPE) {
            this.CDTYPE = CDTYPE;
        }

        public String getIRTNAM() {
            return IRTNAM;
        }

        public void setIRTNAM(String IRTNAM) {
            this.IRTNAM = IRTNAM;
        }
    }
}
