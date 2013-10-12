package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.core.domain.SOFFormBody;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8402;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-10-10
 * Time: ÏÂÎç3:46
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8402Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8402Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8402 m8402 = (M8402) tia;
        logger.info("[8402 Ì×Æ½ºÅ] Ì×ºÅ£º" + m8402.getVCHSET());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8402.getBATSEQ());
        paramList.add(m8402.getORGIDT());
        paramList.add(m8402.getDEPNUM());
        paramList.add(m8402.getORGID3());
        paramList.add(m8402.getTLRNUM());
        paramList.add(m8402.getVCHSET());
        paramList.add(m8402.getFUNCDE());
        paramList.add(m8402.getTXNAMT());
        paramList.add(m8402.getBEGNUM());

        // Ö´ÐÐsbs½»Ò×
        SBSResponse response = coreTxnService.execute(termid, tellid, "8402", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8402 Ì×Æ½ºÅ] Ì×ºÅ£º" + m8402.getVCHSET() + " ·µ»ØÂë£º");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
