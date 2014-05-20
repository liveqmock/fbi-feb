package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15       损益码多笔响应
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class T816 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 40;
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
            fieldLengths = new int[]{2,4,34};
        }

        private String ALCODE;
        private String GLCODE;
        private String ALCNAM;

        public String getALCODE() {
            return ALCODE;
        }

        public void setALCODE(String ALCODE) {
            this.ALCODE = ALCODE;
        }

        public String getGLCODE() {
            return GLCODE;
        }

        public void setGLCODE(String GLCODE) {
            this.GLCODE = GLCODE;
        }

        public String getALCNAM() {
            return ALCNAM;
        }

        public void setALCNAM(String ALCNAM) {
            this.ALCNAM = ALCNAM;
        }
    }
}
