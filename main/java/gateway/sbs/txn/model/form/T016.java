package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ʽ��Ʊ[����ת�˿���]
 */
public class T016 extends SOFFormBody {

    /*
    T016-VCHSET	��Ʊ�׺�	X(4)
T016-TRNCDE	������	X(4)
T016-TLRNUM	��Ա��	X(4)
T016-TRNTIM	����ʱ��	X(6)
T016-WATNUM	��ˮ��	X(7)
T016-TRNDAT	��������	X(8)
T016-VCHPAR	ժҪ	X(60)
T016-PAPTYP	֧Ʊ����	X(1)
T016-PAPCDE	֧Ʊ��	X(10)
T016-PAPMAC	֧Ʊ����	X(10)
T016-ORGIDT	������	X(3)
T016-TMCODE	ǰ̨������	X(4)
T016-VCHPA1	��ע1	X(40)
T016-VCHPA2	������ע	X(60)
     */
    private String VCHSET;            // ��Ʊ�׺�
    private String TRNCDE;            // ������
    private String TLRNUM;            // ��Ա��
    private String TRNTIM;            // ����ʱ��
    private String WATNUM;            // ��ˮ��
    private String TRNDAT;            // ��������
    private String VCHPAR;            // ժҪ
    private String PAPTYP;            // ֧Ʊ����
    private String PAPCDE;            // ֧Ʊ��
    private String PAPMAC;            // ֧Ʊ����
    private String ORGIDT;            // ������
    private String TMCODE;            // ǰ̨������
    private String VCHPA1;            // ��ע1
    private String VCHPA2;            // ������ע

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        byte[] totcntBytes = new byte[4];
        byte[] curcntBytes = new byte[4];
        System.arraycopy(buffer, offset, totcntBytes, 0, 4);
//        totcnt = new String(totcntBytes);
        System.arraycopy(buffer, offset + 4, curcntBytes, 0, 4);
//        curcnt = new String(curcntBytes);

        int index = offset + 8;
        int beanLength = 63;
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
            fieldTypes = new int[]{1, 1, 1, 1, 1};
            fieldLengths = new int[]{18, 23, 18, 23, 12};
        }

        private String DEBACT;          // �跽�˺�
        private String DEBAMT;          // �跽���
        private String CREACT;      // �����˺�
        private String CREAMT;          // �������
        private String EXRATE;          // ����


    }
}
