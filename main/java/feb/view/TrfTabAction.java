package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T824;
import gateway.sbs.txn.model.form.T825;
import gateway.sbs.txn.model.form.T871;
import gateway.sbs.txn.model.form.T872;
import gateway.sbs.txn.model.msg.M9a24;
import gateway.sbs.txn.model.msg.M9a25;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;

import javax.annotation.PostConstruct;
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
 * Date: 14-5-15
 * Time: ����9:52
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class TrfTabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(TrfTabAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String atrcde;
    private String trfseq;
    private String action;

    private T825 t825 = new T825();
    private T872 t872 = new T872();
    private List<T825.Bean> dataList = new ArrayList<>();
    private M9a25 m9a25 = new M9a25();

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        action = params.get("action");
        atrcde = params.get("atrcde");
        trfseq = params.get("trfseq");
        if (action != null) {
            if ("detail".equals(action)) {
                onDetailQry();
            }
        }
    }

    public String onAllQry() {
        try {
            m9a25.setFUNCDE("1");
            m9a25.setBEGNUM("000001");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("9a25", m9a25);
            if (!formList.isEmpty() && formList != null) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T825".equals(form.getFormHeader().getFormCode())) {
                        t825 = (T825) form.getFormBody();
                        dataList.addAll(t825.getBeanList());
                    } else if ("W012".equals(form.getFormHeader().getFormCode())) {

                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("��ѯʧ��", e);
            MessageUtil.addError("��ѯʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onDetailQry() {
        try {
            m9a25.setFUNCDE("0");
            m9a25.setATRCDE(atrcde);
            m9a25.setTRFSEQ(trfseq);
            SOFForm form = dataExchangeService.callSbsTxn("9a25", m9a25).get(0);
            if ("T872".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                t872 = (T872) form.getFormBody();
            } else {
                logger.error(form.getFormHeader().getFormCode());
                MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
            }
        } catch (Exception e) {
            logger.error("��ѯʧ��", e);
            MessageUtil.addError("��ѯʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClick() {
        return "trftabBean";
    }

    public String onBack() {
        return "trftabQry?faces-redirect=true";
    }


    //= = = = = = = = = = = = = = = = = = = = = = = = = = =


    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getAtrcde() {
        return atrcde;
    }

    public void setAtrcde(String atrcde) {
        this.atrcde = atrcde;
    }

    public String getTrfseq() {
        return trfseq;
    }

    public void setTrfseq(String trfseq) {
        this.trfseq = trfseq;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T825 getT825() {
        return t825;
    }

    public void setT825(T825 t825) {
        this.t825 = t825;
    }

    public T872 getT872() {
        return t872;
    }

    public void setT872(T872 t872) {
        this.t872 = t872;
    }

    public List<T825.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T825.Bean> dataList) {
        this.dataList = dataList;
    }

    public M9a25 getM9a25() {
        return m9a25;
    }

    public void setM9a25(M9a25 m9a25) {
        this.m9a25 = m9a25;
    }

}
