package feb.view;


import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T851;
import gateway.sbs.txn.model.msg.M8857;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/5
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ClientActIntQry implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActIntQry.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService ;

    private M8857 m8857 =  new M8857();
    private T851 t851 = new T851();
    private List<T851.Bean> dataList = new ArrayList<>();


    public String onIntQry(){

        try {
            SOFForm form = dataExchangeService.callSbsTxn("8857",m8857).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T851".equals(formcode)){
                t851 = (T851)form.getFormBody();
                dataList = t851.getBeanList();
            }else {
                MessageUtil.addErrorWithClientID("msgs",formcode);
            }
        } catch (Exception e) {
            logger.error("结息明细查询失败.", e);
            MessageUtil.addError("结息明细查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public M8857 getM8857() {
        return m8857;
    }

    public void setM8857(M8857 m8857) {
        this.m8857 = m8857;
    }

    public T851 getT851() {
        return t851;
    }

    public void setT851(T851 t851) {
        this.t851 = t851;
    }

    public List<T851.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T851.Bean> dataList) {
        this.dataList = dataList;
    }
}
