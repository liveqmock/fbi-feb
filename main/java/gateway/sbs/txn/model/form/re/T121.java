package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 7980��Ҫƾ֤����ά����ʲ�ѯ��Ӧ T121
 */
public class T121 extends SOFFormBody {

    private FormBodyHeader formBodyHeader = new FormBodyHeader();
    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {

        formBodyHeader.assembleFields(0, buffer);
        int index = offset + 4;
        int beanLength = 54;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < (buffer.length - 4));
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class FormBodyHeader extends AssembleModel {
        {
            fieldTypes = new int[]{1};
            fieldLengths = new int[]{4};
        }

        private String TOTALNUM;           //�ܱ���

        public String getTOTALNUM() {
            return TOTALNUM;
        }

        public void setTOTALNUM(String TOTALNUM) {
            this.TOTALNUM = TOTALNUM;
        }
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 3};
            fieldLengths = new int[]{2, 6, 30, 1, 13};
        }

        private String VCHTYP;          // ƾ֤����
        private String OUTITM;          // ���������
        private String VCHNAM;          // ƾ֤����
        private String SCTMAK;          // �Ƿ��м�֤ȯ
        private BigDecimal VCHAMT;          // ƾ֤���


        public String getVCHTYP() {
            return VCHTYP;
        }

        public void setVCHTYP(String VCHTYP) {
            this.VCHTYP = VCHTYP;
        }

        public String getOUTITM() {
            return OUTITM;
        }

        public void setOUTITM(String OUTITM) {
            this.OUTITM = OUTITM;
        }

        public String getVCHNAM() {
            return VCHNAM;
        }

        public void setVCHNAM(String VCHNAM) {
            this.VCHNAM = VCHNAM;
        }

        public String getSCTMAK() {
            return SCTMAK;
        }

        public void setSCTMAK(String SCTMAK) {
            this.SCTMAK = SCTMAK;
        }

        public BigDecimal getVCHAMT() {
            return VCHAMT;
        }

        public void setVCHAMT(BigDecimal VCHAMT) {
            this.VCHAMT = VCHAMT;
        }


//    public int offset = 0;

    }
}
