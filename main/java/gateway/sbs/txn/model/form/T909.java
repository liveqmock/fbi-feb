package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-14
 * Time: 下午3:17
 * To change this template use File | Settings | File Templates.
 */
public class T909 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 45;
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
            fieldLengths = new int[]{5,40};
        }

        private String TDDTDC;          // 收费码
        private String TDDNAM;          // 收费码

        public String getTDDTDC() {
            return TDDTDC;
        }

        public void setTDDTDC(String TDDTDC) {
            this.TDDTDC = TDDTDC;
        }

        public String getTDDNAM() {
            return TDDNAM;
        }

        public void setTDDNAM(String TDDNAM) {
            this.TDDNAM = TDDNAM;
        }
    }
}
