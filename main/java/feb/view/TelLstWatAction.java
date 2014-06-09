package feb.view;

import gateway.sbs.core.CtgManager;
import gateway.sbs.core.SBSRequest;
import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.sc.T206;
import gateway.sbs.txn.model.msg.M0260;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Date: 2014/6/9
 * Time: 9:29
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class TelLstWatAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(TelLstWatAction.class);

    private String tellerId = SkylineService.getOperId();
    private T206 t206 = new T206();
    private M0260 m0260 = new M0260();

    /*@PostConstruct
    public void init(){
        onTelWatQry();
    } */
    @PostConstruct
    public void onTelWatQry(){
        try {
            List<String> paramList =  new ArrayList<>();
            CtgManager ctgManager = new CtgManager();
            SBSRequest sbsRequest = new SBSRequest(tellerId, tellerId, "0260", paramList);
            SBSResponse sbsResponse = new SBSResponse();
            ctgManager.processSingleResponsePkg(sbsRequest, sbsResponse);
            //List<SOFForm> forms = sbsResponse.getSofForms();
            SOFForm form = sbsResponse.getSofForms().get(0);
            String formCode = form.getFormHeader().getFormCode();
            if ("T206".equals(formCode)) {
                t206 = (T206)form.getFormBody();
            }else {
                logger.error(formCode);
                MessageUtil.addErrorWithClientID("msgs", formCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //= = = = = = = = = = = = = = = = = = = = = =


    public T206 getT206() {
        return t206;
    }

    public void setT206(T206 t206) {
        this.t206 = t206;
    }

    public M0260 getM0260() {
        return m0260;
    }

    public void setM0260(M0260 m0260) {
        this.m0260 = m0260;
    }
}
