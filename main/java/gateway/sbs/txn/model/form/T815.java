package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-3
 * Time: ����2:25
 * To change this template use File | Settings | File Templates.
 */
//��ʲ�ѯ
public class T815 extends SOFFormBody {

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
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};//14
            fieldLengths = new int[]{3, 1, 2, 8, 24, 10, 4, 4, 1, 1, 3, 1, 4, 8};
        }

        private String glcode = ""; //������
        private String apcode = "";  //������
        private String apcnam = "";       //����������
        private String apctyp = "";       //����������
        private String plcode = "";       //��Ӧ������
        private String intexp = "";       //��Ϣ֧���ʻ�������
        private String intinc = "";     //��Ϣ�����ʻ�������
        private String opsapc = "";       //��ת������
        private String apcdcr = "";       //����������
        private String intdac;       //Ӧ����Ϣ�ʻ�������
        private String intcac;       //Ӧ����Ϣ�ʻ�������
        private String ndrint;       //δʵ����Ϣ֧���˻�������
        private String ncrint;       //δʵ����Ϣ�����˻�������
        private String apckid = "";       //APCKID
   /* private String amdtlr;//
    private Date upddat;//
    private String recsts;//*/

        public String getApcode() {
            return this.apcode;
        }

        public void setApcode(String apcode) {
            this.apcode = apcode;
        }

        public String getApcnam() {
            return this.apcnam;
        }

        public void setApcnam(String apcnam) {
            this.apcnam = apcnam;
        }

        public String getApctyp() {
            return this.apctyp;
        }

        public void setApctyp(String apctyp) {
            this.apctyp = apctyp;
        }

        public String getGlcode() {
            return this.glcode;
        }

        public void setGlcode(String glcode) {
            this.glcode = glcode;
        }

        public String getPlcode() {
            return this.plcode;
        }

        public void setPlcode(String plcode) {
            this.plcode = plcode;
        }

        public String getIntexp() {
            return this.intexp;
        }

        public void setIntexp(String intexp) {
            this.intexp = intexp;
        }

        public String getIntinc() {
            return this.intinc;
        }

        public void setIntinc(String intinc) {
            this.intinc = intinc;
        }

        public String getOpsapc() {
            return this.opsapc;
        }

        public void setOpsapc(String opsapc) {
            this.opsapc = opsapc;
        }

        public String getApcdcr() {
            return this.apcdcr;
        }

        public void setApcdcr(String apcdcr) {
            this.apcdcr = apcdcr;
        }

        public String getIntdac() {
            return this.intdac;
        }

        public void setIntdac(String intdac) {
            this.intdac = intdac;
        }

        public String getIntcac() {
            return this.intcac;
        }

        public void setIntcac(String intcac) {
            this.intcac = intcac;
        }

        public String getNdrint() {
            return this.ndrint;
        }

        public void setNdrint(String ndrint) {
            this.ndrint = ndrint;
        }

        public String getNcrint() {
            return this.ncrint;
        }

        public void setNcrint(String ncrint) {
            this.ncrint = ncrint;
        }

        public String getApckid() {
            return this.apckid;
        }

        public void setApckid(String apckid) {
            this.apckid = apckid;
        }

    }
}
