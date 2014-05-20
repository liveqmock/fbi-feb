package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ʽ��Ʊ[����ת�˿���]
 */
public class T106 extends SOFFormBody {

    private String VCHSET;            // ��Ʊ�׺� X(4)
    private String TRNCDE;            // ������  X(4)
    private String TLRNUM;            // ��Ա��  X(4)
    private String TRNTIM;            // ����ʱ�� X(6)
    private String WATNUM;            // ��ˮ�� X(7)
    private String TRNDAT;            // ��������  X(8)
    private String VCHPAR;            // ժҪ  X(60)
    private String PAPTYP;            // ֧Ʊ����  X(1)
    private String PAPCDE;            // ֧Ʊ��  X(10)
    private String PAPMAC;            // ֧Ʊ���� X(10)
    private String ORGIDT;            // ������ X(3)
    private String TMCODE;            // ǰ̨������  X(4)
    private String VCHPA1;            // ��ע1 X(40)
    private String VCHPA2;            // ������ע  X(60)

    private List<Bean> beanList = new ArrayList<Bean>();

    {
        fieldLengths = new int[]{4, 4, 4, 6, 7, 8, 60,
                1, 10, 10, 3, 4, 40, 60};
    }

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int index = offset;
        try {
            for (int i = 0; i < this.fieldLengths.length; i++) {
                byte[] bytes = new byte[fieldLengths[i]];
                System.arraycopy(buffer, index, bytes, 0, bytes.length);
                fields[i].setAccessible(true);
                if (fields[i].getType().getSimpleName().equals("String")) {
                    fields[i].set(this, (new String(bytes)).trim());
                } else {
                    // ֻװ���ַ����������������ݿ��ֶ��޸�
                }
                index += bytes.length;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("FormBody�����쳣[T106]��IllegalAccessException ");
        }

        int beanLength = 94;
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

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getTRNCDE() {
        return TRNCDE;
    }

    public void setTRNCDE(String TRNCDE) {
        this.TRNCDE = TRNCDE;
    }

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getTRNTIM() {
        return TRNTIM;
    }

    public void setTRNTIM(String TRNTIM) {
        this.TRNTIM = TRNTIM;
    }

    public String getWATNUM() {
        return WATNUM;
    }

    public void setWATNUM(String WATNUM) {
        this.WATNUM = WATNUM;
    }

    public String getTRNDAT() {
        return TRNDAT;
    }

    public void setTRNDAT(String TRNDAT) {
        this.TRNDAT = TRNDAT;
    }

    public String getVCHPAR() {
        return VCHPAR;
    }

    public void setVCHPAR(String VCHPAR) {
        this.VCHPAR = VCHPAR;
    }

    public String getPAPTYP() {
        return PAPTYP;
    }

    public void setPAPTYP(String PAPTYP) {
        this.PAPTYP = PAPTYP;
    }

    public String getPAPCDE() {
        return PAPCDE;
    }

    public void setPAPCDE(String PAPCDE) {
        this.PAPCDE = PAPCDE;
    }

    public String getPAPMAC() {
        return PAPMAC;
    }

    public void setPAPMAC(String PAPMAC) {
        this.PAPMAC = PAPMAC;
    }

    public String getORGIDT() {
        return ORGIDT;
    }

    public void setORGIDT(String ORGIDT) {
        this.ORGIDT = ORGIDT;
    }

    public String getTMCODE() {
        return TMCODE;
    }

    public void setTMCODE(String TMCODE) {
        this.TMCODE = TMCODE;
    }

    public String getVCHPA1() {
        return VCHPA1;
    }

    public void setVCHPA1(String VCHPA1) {
        this.VCHPA1 = VCHPA1;
    }

    public String getVCHPA2() {
        return VCHPA2;
    }

    public void setVCHPA2(String VCHPA2) {
        this.VCHPA2 = VCHPA2;
    }

    public void setBeanList(List<Bean> beanList) {
        this.beanList = beanList;
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1};
            fieldLengths = new int[]{18, 23, 18, 23, 12};
        }

        private String DEBACT;          // �跽�˺�
        private String DEBAMT;          // �跽���
        private String CREACT;          // �����˺�
        private String CREAMT;          // �������
        private String EXRATE;          // ����

        public String getDEBACT() {
            return DEBACT;
        }

        public void setDEBACT(String DEBACT) {
            this.DEBACT = DEBACT;
        }

        public String getDEBAMT() {
            return DEBAMT;
        }

        public void setDEBAMT(String DEBAMT) {
            this.DEBAMT = DEBAMT;
        }

        public String getCREACT() {
            return CREACT;
        }

        public void setCREACT(String CREACT) {
            this.CREACT = CREACT;
        }

        public String getCREAMT() {
            return CREAMT;
        }

        public void setCREAMT(String CREAMT) {
            this.CREAMT = CREAMT;
        }

        public String getEXRATE() {
            return EXRATE;
        }

        public void setEXRATE(String EXRATE) {
            this.EXRATE = EXRATE;
        }
    }
}
