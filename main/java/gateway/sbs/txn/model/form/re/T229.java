package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15       存款种类表维护-多笔查询
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class T229 extends SOFFormBody {

    private String totcnt = "0";            // 总记录数
    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        byte[] totcntBytes = new byte[4];
        System.arraycopy(buffer, offset, totcntBytes, 0, 4);
        totcnt = new String(totcntBytes);

        int index = offset+4;
        int beanLength = 8;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length);
    }

    public String getTotcnt() {
        return totcnt;
    }

    public void setTotcnt(String totcnt) {
        this.totcnt = totcnt;
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1};
            fieldLengths = new int[]{2, 1, 4, 1};
        }


        private String DPTTYP;
        private String CHFMAK;
        private String APCODE;
        private String RECSTS;

        private String pkid;/////adddd

        public String getDPTTYP() {
            return DPTTYP;
        }

        public void setDPTTYP(String DPTTYP) {
            this.DPTTYP = DPTTYP;
        }

        public String getCHFMAK() {
            return CHFMAK;
        }

        public void setCHFMAK(String CHFMAK) {
            this.CHFMAK = CHFMAK;
        }

        public String getAPCODE() {
            return APCODE;
        }

        public void setAPCODE(String APCODE) {
            this.APCODE = APCODE;
        }

        public String getRECSTS() {
            return RECSTS;
        }

        public void setRECSTS(String RECSTS) {
            this.RECSTS = RECSTS;
        }

        public String getPkid() {
            return pkid;
        }

        public void setPkid(String pkid) {
            this.pkid = pkid;
        }
    }
}
