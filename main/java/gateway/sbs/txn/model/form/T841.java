package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-5      查询传票响应报文
 * Time: 上午8:52
 * To change this template use File | Settings | File Templates.
 */
public class T841 extends SOFFormBody {
    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 103;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            if (!bean.getCUSIDT().equals("")) {
                beanList.add(bean);
            } else {
               // System.out.print("=================");
            }
            index += beanLength;
        } while (index < buffer.length);
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};//10
            fieldLengths = new int[]{7, 4, 3, 4, 2, 21, 21, 8, 1, 32};
        }

        private String CUSIDT;  //客户号
        private String APCODE;  //核算码
        private String CURCDE;  //币别
        private String VCHSET;  //传票套号
        private String SETSEQ;  //套内序号
        private String DRBALA;  //借方发生额
        private String CRBALA;  //贷方发生额
        private String VALDAT;  //起息日期
        private String RVSLBL;  //冲账标志
        private String FURINF;  //备注

        public String getCUSIDT() {
            return CUSIDT;
        }

        public void setCUSIDT(String CUSIDT) {
            this.CUSIDT = CUSIDT;
        }

        public String getAPCODE() {
            return APCODE;
        }

        public void setAPCODE(String APCODE) {
            this.APCODE = APCODE;
        }

        public String getCURCDE() {
            return CURCDE;
        }

        public void setCURCDE(String CURCDE) {
            this.CURCDE = CURCDE;
        }

        public String getVCHSET() {
            return VCHSET;
        }

        public void setVCHSET(String VCHSET) {
            this.VCHSET = VCHSET;
        }

        public String getSETSEQ() {
            return SETSEQ;
        }

        public void setSETSEQ(String SETSEQ) {
            this.SETSEQ = SETSEQ;
        }

        public String getDRBALA() {
            return DRBALA;
        }

        public void setDRBALA(String DRBALA) {
            this.DRBALA = DRBALA;
        }

        public String getCRBALA() {
            return CRBALA;
        }

        public void setCRBALA(String CRBALA) {
            this.CRBALA = CRBALA;
        }

        public String getVALDAT() {
            return VALDAT;
        }

        public void setVALDAT(String VALDAT) {
            this.VALDAT = VALDAT;
        }

        public String getRVSLBL() {
            return RVSLBL;
        }

        public void setRVSLBL(String RVSLBL) {
            this.RVSLBL = RVSLBL;
        }

        public String getFURINF() {
            return FURINF;
        }

        public void setFURINF(String FURINF) {
            this.FURINF = FURINF;
        }
    }
}
