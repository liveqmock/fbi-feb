package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15       国家码多笔响应
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class T818 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 71;
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
            fieldLengths = new int[]{3,34,34};
        }

        private String CTRCDE ;
        private String CTRNMC ;
        private String CTRNME ;

        public String getCTRCDE() {
            return CTRCDE;
        }

        public void setCTRCDE(String CTRCDE) {
            this.CTRCDE = CTRCDE;
        }

        public String getCTRNMC() {
            return CTRNMC;
        }

        public void setCTRNMC(String CTRNMC) {
            this.CTRNMC = CTRNMC;
        }

        public String getCTRNME() {
            return CTRNME;
        }

        public void setCTRNME(String CTRNME) {
            this.CTRNME = CTRNME;
        }
    }
}
