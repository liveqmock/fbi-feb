package gateway.sbs.txn.model.form.ac;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * //∂‡± ≤È—Ø
 */
public class T814 extends SOFFormBody {

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
            fieldLengths = new int[]{4, 34};
        }

        private String APCODE;
        private String APCNAM;

//=========================================
        public String getAPCODE() {
            return APCODE;
        }

        public void setAPCODE(String APCODE) {
            this.APCODE = APCODE;
        }

        public String getAPCNAM() {
            return APCNAM;
        }

        public void setAPCNAM(String APCNAM) {
            this.APCNAM = APCNAM;
        }


    }
}
