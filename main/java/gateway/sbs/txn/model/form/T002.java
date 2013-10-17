package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-5
 * Time: ÏÂÎç3:32
 * To change this template use File | Settings | File Templates.
 */
public class T002 extends SOFFormBody {
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
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{20, 50,30,40,50};
    }
    private String cusidt;
    private String cusnam;
    private  String pastyp;
    private  String passno;
    private  String coradd;
    private  String telnum;

    public String getPastyp() {
        return pastyp;
    }

    public void setPastyp(String pastyp) {
        this.pastyp = pastyp;
    }

    public String getPassno() {
        return passno;
    }

    public void setPassno(String passno) {
        this.passno = passno;
    }

    public String getCoradd() {
        return coradd;
    }

    public void setCoradd(String coradd) {
        this.coradd = coradd;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }
    public String getCusidt() {
        return cusidt;
    }
    public void setCusidt(String cusidt) {
        this.cusidt = cusidt;
    }

    public String getCusnam() {
        return cusnam;
    }

    public void setCusnam(String cusnam) {
        this.cusnam = cusnam;
    }
    }
}
