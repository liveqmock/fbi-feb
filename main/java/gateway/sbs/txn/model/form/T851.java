package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-20
 * Time: ����3:14
 * To change this template use File | Settings | File Templates.
 */
public class T851 extends SOFFormBody {

        private String totcnt = "0";            // �ܼ�¼��
        private String curcnt = "0";            // �����ڼ�¼��

        private List<Bean> beanList = new ArrayList<Bean>();

        @Override
        public void assembleFields(int offset, byte[] buffer) {
            byte[] totcntBytes = new byte[6];
            byte[] curcntBytes = new byte[6];
            System.arraycopy(buffer, offset, totcntBytes, 0, 6);
            totcnt = new String(totcntBytes);
            System.arraycopy(buffer, offset + 6, curcntBytes, 0, 6);
            curcnt = new String(curcntBytes);

            int index = offset + 12;
            int beanLength = 171;
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

        public String getTotcnt() {
            return totcnt;
        }

        public String getCurcnt() {
            return curcnt;
        }

        public void setTotcnt(String totcnt) {
            this.totcnt = totcnt;
        }

        public void setCurcnt(String curcnt) {
            this.curcnt = curcnt;
        }

        public class Bean extends AssembleModel {
            {
                fieldTypes = new int[]{1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1}; //13
                fieldLengths = new int[]{7, 4, 3, 8, 17, 4, 4, 2, 32, 6, 4, 8, 72};//171
            }

            private String CUSIDT;          //�ͻ���
            private String APCODE;          //������
            private String CURCDE;          //�ұ�
            private String ERYDAT;          //��������
            private BigDecimal TXNAMT;          //���
            private String TLRNUM;          //��Ա
            private String VCHSET;          //��Ʊ�׺�
            private String SETSEQ;          //�������
            private String FURINF;          //ժҪ
            private String ERYTIM;          //����ʱ��
            private String ANACDE;          //ͳ����
            private String VALDAT;          //��Ϣ����
            private String ACTNAM;          //�˻�����

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

            public String getERYDAT() {
                return ERYDAT;
            }

            public void setERYDAT(String ERYDAT) {
                this.ERYDAT = ERYDAT;
            }

            public BigDecimal getTXNAMT() {
                return TXNAMT;
            }

            public void setTXNAMT(BigDecimal TXNAMT) {
                this.TXNAMT = TXNAMT;
            }

            public String getTLRNUM() {
                return TLRNUM;
            }

            public void setTLRNUM(String TLRNUM) {
                this.TLRNUM = TLRNUM;
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

            public String getFURINF() {
                return FURINF;
            }

            public void setFURINF(String FURINF) {
                this.FURINF = FURINF;
            }

            public String getERYTIM() {
                return ERYTIM;
            }

            public void setERYTIM(String ERYTIM) {
                this.ERYTIM = ERYTIM;
            }

            public String getANACDE() {
                return ANACDE;
            }

            public void setANACDE(String ANACDE) {
                this.ANACDE = ANACDE;
            }

            public String getVALDAT() {
                return VALDAT;
            }

            public void setVALDAT(String VALDAT) {
                this.VALDAT = VALDAT;
            }

            public String getACTNAM() {
                return ACTNAM;
            }

            public void setACTNAM(String ACTNAM) {
                this.ACTNAM = ACTNAM;
            }
        }
    }


    /*private List<Bean> beanList = new ArrayList<Bean>();
    private FormBodyHeader formBodyHeader = new FormBodyHeader();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        formBodyHeader.assembleFields(0, buffer);
        int index = offset + 12;
        int beanLength = 171;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length-12);
    }

    public FormBodyHeader getFormBodyHeader() {
        return formBodyHeader;
    }

    public void setFormBodyHeader(FormBodyHeader formBodyHeader) {
        this.formBodyHeader = formBodyHeader;
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class FormBodyHeader extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1}; //2
            fieldLengths = new int[]{6, 6};//12
        }

        private String TOTCNT;          //�ܱ���
        private String CURCNT;          //��������

        public String getTOTCNT() {
            return TOTCNT;
        }

        public void setTOTCNT(String TOTCNT) {
            this.TOTCNT = TOTCNT;
        }

        public String getCURCNT() {
            return CURCNT;
        }

        public void setCURCNT(String CURCNT) {
            this.CURCNT = CURCNT;
        }
    }

    public class Bean extends AssembleModel {

        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}; //13
            fieldLengths = new int[]{7, 4, 3, 8, 17, 4, 4, 2, 32, 6, 4, 8, 72};//169
        }

        private String CUSIDT;          //�ͻ���
        private String APCODE;          //������
        private String CURCDE;          //�ұ�
        private String ERYDAT;          //��������
        private String TXNAMT;          //���
        private String TLRNUM;          //��Ա
        private String VCHSET;          //��Ʊ�׺�
        private String SETSEQ;          //�������
        private String FURINF;          //ժҪ
        private String ERYTIM;          //����ʱ��
        private String ANACDE;          //ͳ����
        private String VALDAT;          //��Ϣ����
        private String ACTNAM;          //�˻�����

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

        public String getERYDAT() {
            return ERYDAT;
        }

        public void setERYDAT(String ERYDAT) {
            this.ERYDAT = ERYDAT;
        }

        public String getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(String TXNAMT) {
            this.TXNAMT = TXNAMT;
        }

        public String getTLRNUM() {
            return TLRNUM;
        }

        public void setTLRNUM(String TLRNUM) {
            this.TLRNUM = TLRNUM;
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

        public String getFURINF() {
            return FURINF;
        }

        public void setFURINF(String FURINF) {
            this.FURINF = FURINF;
        }

        public String getERYTIM() {
            return ERYTIM;
        }

        public void setERYTIM(String ERYTIM) {
            this.ERYTIM = ERYTIM;
        }

        public String getANACDE() {
            return ANACDE;
        }

        public void setANACDE(String ANACDE) {
            this.ANACDE = ANACDE;
        }

        public String getVALDAT() {
            return VALDAT;
        }

        public void setVALDAT(String VALDAT) {
            this.VALDAT = VALDAT;
        }

        public String getACTNAM() {
            return ACTNAM;
        }

        public void setACTNAM(String ACTNAM) {
            this.ACTNAM = ACTNAM;
        }
    }*/
