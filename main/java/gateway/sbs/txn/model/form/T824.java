package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-15       �Զ�ת��������Ӧ
 * Time: ����2:25
 * To change this template use File | Settings | File Templates.
 */
public class T824 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 65;
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
            fieldLengths = new int[]{3, 34, 1, 4, 1, 21, 1};
        }

        private String ATRCDE;    //ת����
        private String ATRNAM;    //�Զ�ת������
        private String CYCCDE;    //����
        private String CYCDAT;    //����
        private String TRICDE;    //������
        private String TRIBAL;    //�������
        private String FEXCDE;    //���������

        public String getATRCDE() {
            return ATRCDE;
        }

        public void setATRCDE(String ATRCDE) {
            this.ATRCDE = ATRCDE;
        }

        public String getATRNAM() {
            return ATRNAM;
        }

        public void setATRNAM(String ATRNAM) {
            this.ATRNAM = ATRNAM;
        }

        public String getCYCCDE() {
            return CYCCDE;
        }

        public void setCYCCDE(String CYCCDE) {
            this.CYCCDE = CYCCDE;
        }

        public String getCYCDAT() {
            return CYCDAT;
        }

        public void setCYCDAT(String CYCDAT) {
            this.CYCDAT = CYCDAT;
        }

        public String getTRICDE() {
            return TRICDE;
        }

        public void setTRICDE(String TRICDE) {
            this.TRICDE = TRICDE;
        }

        public String getTRIBAL() {
            return TRIBAL;
        }

        public void setTRIBAL(String TRIBAL) {
            this.TRIBAL = TRIBAL;
        }

        public String getFEXCDE() {
            return FEXCDE;
        }

        public void setFEXCDE(String FEXCDE) {
            this.FEXCDE = FEXCDE;
        }
    }
}
