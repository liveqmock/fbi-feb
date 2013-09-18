package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 9813�������ʲ�ѯ��Ӧ T813
 */
public class T813 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 59;
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

        private String GLCODE	;          // ������
        private String GLCNAM	;          // ����������

        public String getGLCODE() {
            return GLCODE;
        }

        public void setGLCODE(String GLCODE) {
            this.GLCODE = GLCODE;
        }

        public String getGLCNAM() {
            return GLCNAM;
        }

        public void setGLCNAM(String GLCNAM) {
            this.GLCNAM = GLCNAM;
        }
        //    public int offset = 0;

    }
}
