package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15       损益码多笔响应
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class T815 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 42;
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
            fieldTypes = new int[]{1, 1, 1};
            fieldLengths = new int[]{4,34,4};
        }

        private String PLCODE;
        private String PLCNAM;
        private String GLCODE;

        public String getPLCODE() {
            return PLCODE;
        }

        public void setPLCODE(String PLCODE) {
            this.PLCODE = PLCODE;
        }

        public String getPLCNAM() {
            return PLCNAM;
        }

        public void setPLCNAM(String PLCNAM) {
            this.PLCNAM = PLCNAM;
        }

        public String getGLCODE() {
            return GLCODE;
        }

        public void setGLCODE(String GLCODE) {
            this.GLCODE = GLCODE;
        }
    }
}
