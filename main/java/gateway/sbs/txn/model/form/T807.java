package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-13     �������ѯ����
 * Time: ����3:40
 * To change this template use File | Settings | File Templates.
 */
public class T807 extends SOFFormBody {
    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 73;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{3, 8, 24, 12, 12, 10, 1, 3};
        }

        private String FRTKD1;          // �շ���
        private String EFFDAT;          // ��Ч����
        private String FRTNAM;          // ����
        private String MALFEE;          // �ʷ�
        private String COMFEE;          // ������
        private String FEERAT;          // ����
        private String FEECYC;          // ����
        private String INTCUR;          // �ұ���

        public String getFRTKD1() {
            return FRTKD1;
        }

        public void setFRTKD1(String FRTKD1) {
            this.FRTKD1 = FRTKD1;
        }

        public String getEFFDAT() {
            return EFFDAT;
        }

        public void setEFFDAT(String EFFDAT) {
            this.EFFDAT = EFFDAT;
        }

        public String getFRTNAM() {
            return FRTNAM;
        }

        public void setFRTNAM(String FRTNAM) {
            this.FRTNAM = FRTNAM;
        }

        public String getMALFEE() {
            return MALFEE;
        }

        public void setMALFEE(String MALFEE) {
            this.MALFEE = MALFEE;
        }

        public String getCOMFEE() {
            return COMFEE;
        }

        public void setCOMFEE(String COMFEE) {
            this.COMFEE = COMFEE;
        }

        public String getFEERAT() {
            return FEERAT;
        }

        public void setFEERAT(String FEERAT) {
            this.FEERAT = FEERAT;
        }

        public String getFEECYC() {
            return FEECYC;
        }

        public void setFEECYC(String FEECYC) {
            this.FEECYC = FEECYC;
        }

        public String getINTCUR() {
            return INTCUR;
        }

        public void setINTCUR(String INTCUR) {
            this.INTCUR = INTCUR;
        }
    }
}
