package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-14
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
public class T552 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 57;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1};//7
            fieldLengths = new int[]{3, 4, 8, 6, 20, 15, 1};
        }

        private String CURCDE;          // 币别码
        private String DOMCDE;          // 国际货币符号
        private String EFFDAT;          // 启用日期
        private String EFFTIM;          // 启用时间
        private String XRTNAM;          // 名称
        private String RATVAL;          // 汇率值
        private String RATFLG;          // 汇率标识

        public String getCURCDE() {
            return CURCDE;
        }

        public void setCURCDE(String CURCDE) {
            this.CURCDE = CURCDE;
        }

        public String getDOMCDE() {
            return DOMCDE;
        }

        public void setDOMCDE(String DOMCDE) {
            this.DOMCDE = DOMCDE;
        }

        public String getEFFDAT() {
            return EFFDAT;
        }

        public void setEFFDAT(String EFFDAT) {
            this.EFFDAT = EFFDAT;
        }

        public String getEFFTIM() {
            return EFFTIM;
        }

        public void setEFFTIM(String EFFTIM) {
            this.EFFTIM = EFFTIM;
        }

        public String getXRTNAM() {
            return XRTNAM;
        }

        public void setXRTNAM(String XRTNAM) {
            this.XRTNAM = XRTNAM;
        }

        public String getRATVAL() {
            return RATVAL;
        }

        public void setRATVAL(String RATVAL) {
            this.RATVAL = RATVAL;
        }

        public String getRATFLG() {
            return RATFLG;
        }

        public void setRATFLG(String RATFLG) {
            this.RATFLG = RATFLG;
        }
    }
}
