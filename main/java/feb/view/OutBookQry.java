package feb.view;


import gateway.sbs.core.CtgManager;
import gateway.sbs.core.SBSRequest;
import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T632;
import gateway.sbs.txn.model.form.sc.T901;
import gateway.sbs.txn.model.msg.M841a;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pub.tools.MessageUtil;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/6/6         Õ‚Œß¥´∆±≤È—Ø
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class OutBookQry implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(OutBookQry.class);

    private String tellerId = SkylineService.getOperId();
    private M841a m841a = new M841a();
    private List<T632.Bean> dataList = new ArrayList<>();

    public String outVchQry(){
        try {
            List<String> paramList = new ArrayList<>();
            paramList.add(m841a.getPASSNO());
            paramList.add(m841a.getBEGNUM());
            CtgManager ctgManager = new CtgManager();
            SBSRequest sbsRequest = new SBSRequest(tellerId, tellerId, "841a", paramList);
            SBSResponse sbsResponse = new SBSResponse();
            ctgManager.processSingleResponsePkg(sbsRequest, sbsResponse);
            //List<SOFForm> forms = sbsResponse.getSofForms();
            SOFForm form = sbsResponse.getSofForms().get(0);
            String formCode = form.getFormHeader().getFormCode();
                if ("T632".equals(formCode)) {
                    T632 t632 = (T632)form.getFormBody();
                    dataList=t632.getBeanList();
                }else {
                    logger.error(formCode);
                    MessageUtil.addErrorWithClientID("msgs", formCode);
                }
        } catch (Exception e) {
            logger.error("≤È—Ø ß∞‹", e);
            MessageUtil.addError("≤È—Ø ß∞‹." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //= = = = = = = = = = = = = = = = = = = = = = =

    public M841a getM841a() {
        return m841a;
    }

    public void setM841a(M841a m841a) {
        this.m841a = m841a;
    }

    public List<T632.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T632.Bean> dataList) {
        this.dataList = dataList;
    }
}
