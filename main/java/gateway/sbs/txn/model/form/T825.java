package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15       自动转账码要素多笔响应
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class T825 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 30;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{3, 3, 1, 20, 1, 1, 1};
        }

        private String ATRCDE;    //自动转帐码
        private String TRFSEQ;    //要素顺序号
        private String TRFKID;    //要素类别
        private String TRFNUM;    //要素号
        private String TRFOPR;    //操作码
        private String AMTTYP;    //金额类别
        private String AMTSDE;    //借贷方向

        public String getATRCDE() {
            return ATRCDE;
        }

        public void setATRCDE(String ATRCDE) {
            this.ATRCDE = ATRCDE;
        }

        public String getTRFSEQ() {
            return TRFSEQ;
        }

        public void setTRFSEQ(String TRFSEQ) {
            this.TRFSEQ = TRFSEQ;
        }

        public String getTRFKID() {
            return TRFKID;
        }

        public void setTRFKID(String TRFKID) {
            this.TRFKID = TRFKID;
        }

        public String getTRFNUM() {
            return TRFNUM;
        }

        public void setTRFNUM(String TRFNUM) {
            this.TRFNUM = TRFNUM;
        }

        public String getTRFOPR() {
            return TRFOPR;
        }

        public void setTRFOPR(String TRFOPR) {
            this.TRFOPR = TRFOPR;
        }

        public String getAMTTYP() {
            return AMTTYP;
        }

        public void setAMTTYP(String AMTTYP) {
            this.AMTTYP = AMTTYP;
        }

        public String getAMTSDE() {
            return AMTSDE;
        }

        public void setAMTSDE(String AMTSDE) {
            this.AMTSDE = AMTSDE;
        }
    }
}
