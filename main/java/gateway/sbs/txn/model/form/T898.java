package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-29                  //��Ʊ��ѯ��Ӧ
 * Time: ����9:48
 * To change this template use File | Settings | File Templates.
 */
public class T898 extends SOFFormBody {

    private List<Bean> beanList = new ArrayList<Bean>();
    private FormBodyHeader formBodyHeader = new FormBodyHeader();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        formBodyHeader.assembleFields(offset,buffer);
        int index = offset + 16;
        int beanLength = 196;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length);
    }

    public FormBodyHeader getFormBodyHeader() {
        return formBodyHeader;
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public class FormBodyHeader extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1}; //5
            fieldLengths = new int[]{3, 4, 4, 4, 1};//16
        }

        private String ORGIDT;          //������
        private String TLRNUM;          //��Ա��
        private String VCHSET;          //��Ʊ�׺�
        private String TOTNUM;          //�ܱ���
        private String SETFLG;             //

        public String getORGIDT() {
            return ORGIDT;
        }

        public void setORGIDT(String ORGIDT) {
            this.ORGIDT = ORGIDT;
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

        public String getTOTNUM() {
            return TOTNUM;
        }

        public void setTOTNUM(String TOTNUM) {
            this.TOTNUM = TOTNUM;
        }

        public String getSETFLG() {
            return SETFLG;
        }

        public void setSETFLG(String SETFLG) {
            this.SETFLG = SETFLG;
        }
    }

    public class Bean extends AssembleModel {

        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}; //26
            fieldLengths = new int[]{2, 4, 14, 17, 8, 2, 4, 32, 13, 3, 17, 4, 3,
                    4, 2, 7, 7, 7, 1, 8, 8, 1, 6, 1, 20, 1};//196
        }

        //        private String ORGIDT ;          //������
//        private String TLRNUM ;          //��Ա��
//        private String VCHSET ;          //��Ʊ�׺�
//        private String TOTNUM ;          //�ܱ���
//        private String SETFLG ;          //
        //��ʼѭ��
        private String SETSEQ;          //�������
        private String PRDCDE;          //��Ʒ��
        private String ACTNUM;          //�˺�
        private String TXNAMT;          //���
        private String VALDAT;          //��Ϣ����
        private String RVSLBL;          //������־
        private String ANACDE;          //ͳ����
        private String FURINF;          //��ע
        private String FXRATE;          //����
        private String SECCCY;          //�ڶ�������
        private String SECAMT;          //�ڶ����ҽ��
        private String CORAPC;          //�����Ӧ������
        private String VCHATT;          //������
        private String VCHAUT;          //���ܴ���
        private String DEPNUM;          //���ź�
        private String TXNBAK;          //�����к�
        private String ACTBAK;          //�˻��к�
        private String CLRBAK;          //�����к�
        private String ERYTYP;          //��Ʊ���뷽ʽ
        private String ERYDAT;          //��Ʊ��������
        private String ERYTIM;          //��Ʊ����ʱ��
        private String OUFCRE;          //���������
        private String REFNUM;          //��Ƭ���ú�
        private String VCHSTS;          //ƾ֤״̬
        private String REGNUM;          //ƾ֤���
        private String RECSTS;          //��¼״̬

        public String getSETSEQ() {
            return SETSEQ;
        }

        public void setSETSEQ(String SETSEQ) {
            this.SETSEQ = SETSEQ;
        }

        public String getPRDCDE() {
            return PRDCDE;
        }

        public void setPRDCDE(String PRDCDE) {
            this.PRDCDE = PRDCDE;
        }

        public String getACTNUM() {
            return ACTNUM;
        }

        public void setACTNUM(String ACTNUM) {
            this.ACTNUM = ACTNUM;
        }

        public String getTXNAMT() {
            return TXNAMT;
        }

        public void setTXNAMT(String TXNAMT) {
            this.TXNAMT = TXNAMT;
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

        public String getANACDE() {
            return ANACDE;
        }

        public void setANACDE(String ANACDE) {
            this.ANACDE = ANACDE;
        }

        public String getFURINF() {
            return FURINF;
        }

        public void setFURINF(String FURINF) {
            this.FURINF = FURINF;
        }

        public String getFXRATE() {
            return FXRATE;
        }

        public void setFXRATE(String FXRATE) {
            this.FXRATE = FXRATE;
        }

        public String getSECCCY() {
            return SECCCY;
        }

        public void setSECCCY(String SECCCY) {
            this.SECCCY = SECCCY;
        }

        public String getSECAMT() {
            return SECAMT;
        }

        public void setSECAMT(String SECAMT) {
            this.SECAMT = SECAMT;
        }

        public String getCORAPC() {
            return CORAPC;
        }

        public void setCORAPC(String CORAPC) {
            this.CORAPC = CORAPC;
        }

        public String getVCHATT() {
            return VCHATT;
        }

        public void setVCHATT(String VCHATT) {
            this.VCHATT = VCHATT;
        }

        public String getVCHAUT() {
            return VCHAUT;
        }

        public void setVCHAUT(String VCHAUT) {
            this.VCHAUT = VCHAUT;
        }

        public String getDEPNUM() {
            return DEPNUM;
        }

        public void setDEPNUM(String DEPNUM) {
            this.DEPNUM = DEPNUM;
        }

        public String getTXNBAK() {
            return TXNBAK;
        }

        public void setTXNBAK(String TXNBAK) {
            this.TXNBAK = TXNBAK;
        }

        public String getACTBAK() {
            return ACTBAK;
        }

        public void setACTBAK(String ACTBAK) {
            this.ACTBAK = ACTBAK;
        }

        public String getCLRBAK() {
            return CLRBAK;
        }

        public void setCLRBAK(String CLRBAK) {
            this.CLRBAK = CLRBAK;
        }

        public String getERYTYP() {
            return ERYTYP;
        }

        public void setERYTYP(String ERYTYP) {
            this.ERYTYP = ERYTYP;
        }

        public String getERYDAT() {
            return ERYDAT;
        }

        public void setERYDAT(String ERYDAT) {
            this.ERYDAT = ERYDAT;
        }

        public String getERYTIM() {
            return ERYTIM;
        }

        public void setERYTIM(String ERYTIM) {
            this.ERYTIM = ERYTIM;
        }

        public String getOUFCRE() {
            return OUFCRE;
        }

        public void setOUFCRE(String OUFCRE) {
            this.OUFCRE = OUFCRE;
        }

        public String getREFNUM() {
            return REFNUM;
        }

        public void setREFNUM(String REFNUM) {
            this.REFNUM = REFNUM;
        }

        public String getVCHSTS() {
            return VCHSTS;
        }

        public void setVCHSTS(String VCHSTS) {
            this.VCHSTS = VCHSTS;
        }

        public String getREGNUM() {
            return REGNUM;
        }

        public void setREGNUM(String REGNUM) {
            this.REGNUM = REGNUM;
        }

        public String getRECSTS() {
            return RECSTS;
        }

        public void setRECSTS(String RECSTS) {
            this.RECSTS = RECSTS;
        }
    }
}
