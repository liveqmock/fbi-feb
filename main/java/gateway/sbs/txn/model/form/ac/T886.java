package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15       利率码多笔响应
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class T886 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 38;
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
            fieldTypes = new int[]{1, 1};
            fieldLengths = new int[]{4,34};
        }

        private String TAXCDE ;
        private String TAXDCR ;

        public String getTAXCDE() {
            return TAXCDE;
        }

        public void setTAXCDE(String TAXCDE) {
            this.TAXCDE = TAXCDE;
        }

        public String getTAXDCR() {
            return TAXDCR;
        }

        public void setTAXDCR(String TAXDCR) {
            this.TAXDCR = TAXDCR;
        }
    }
}
