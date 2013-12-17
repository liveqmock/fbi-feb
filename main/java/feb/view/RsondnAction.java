package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T220;
import gateway.sbs.txn.model.msg.Ma111;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

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
 *
 */
@ManagedBean
@ViewScoped
public class RsondnAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RsondnAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private T220 ndn = new T220();

    private String auttlr = "SYS1";                     // ��Ȩ���ܹ�Ա��
    private String autpwd = "00000000";                     // ��Ȩ��������


    private String actty2;
    private String iptac2;
    private String advnum;
    private String dramd2;
    private String advamt;
    private String advdat;



    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        glcode = StringUtils.isEmpty(params.get("glcode")) ? "1040" : params.get("glcode");
        actty2 = "07";
        iptac2 = "010600012000002235";
        dramd2 = "0";
        advamt = "1";

    }
    public String onAllQuery() {
        try {
//            if (allone.contentEquals("0")){
            Ma111 ma111 = new Ma111(actty2,iptac2,advnum,dramd2,advamt,advdat);
            List<SOFForm> formList = dataExchangeService.callSbsTxn(auttlr,autpwd,"a111", ma111);
                 if (formList != null && !formList.isEmpty()) {
                    for (SOFForm form : formList) {
                        if ("T220".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                            T220 t220 = (T220) form.getFormBody();
                            ndn = t220;
                        }
                        else {
                            logger.info(form.getFormHeader().getFormCode());

                            MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
//                            MessageUtil.addInfoWithClientID("msgs", "��ѯ�ɹ�");
                        }
                    }
                    }
                if (ndn == null) {
                    MessageUtil.addWarn("û�в�ѯ�����ݡ�");
                }
        } catch (Exception e) {
            logger.error("��ѯʧ��", e);
            MessageUtil.addError("��ѯʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }

        return null;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }




    public String getActty2() {
        return actty2;
    }

    public void setActty2(String actty2) {
        this.actty2 = actty2;
    }

    public String getIptac2() {
        return iptac2;
    }

    public void setIptac2(String iptac2) {
        this.iptac2 = iptac2;
    }

    public T220 getNdn() {
        return ndn;
    }

    public void setNdn(T220 ndn) {
        this.ndn = ndn;
    }

    public String getDramd2() {
        return dramd2;
    }

    public void setDramd2(String dramd2) {
        this.dramd2 = dramd2;
    }

    public String getAdvamt() {
        return advamt;
    }

    public void setAdvamt(String advamt) {
        this.advamt = advamt;
    }

    public String getAuttlr() {
        return auttlr;
    }

    public void setAuttlr(String auttlr) {
        this.auttlr = auttlr;
    }

    public String getAutpwd() {
        return autpwd;
    }

    public void setAutpwd(String autpwd) {
        this.autpwd = autpwd;
    }

    public String getAdvnum() {
        return advnum;
    }

    public void setAdvnum(String advnum) {
        this.advnum = advnum;
    }

    public String getAdvdat() {
        return advdat;
    }

    public void setAdvdat(String advdat) {
        this.advdat = advdat;
    }
}