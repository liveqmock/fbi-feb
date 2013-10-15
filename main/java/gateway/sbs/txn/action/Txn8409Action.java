package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8409;
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
 * Date: 13-10-11
 * Time: ÉÏÎç9:18
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8409Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8409Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M8409 m8409 = (M8409)tia;
        logger.info("[8409-É¾³ý] ´«Æ±ºÅ£º" + m8409.getVCHSET());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8409.getBATSEQ());
        paramList.add(m8409.getORGIDT());
        paramList.add(m8409.getDEPNUM());
        paramList.add(m8409.getFUNCDE());
        paramList.add(m8409.getVCHSET());
        paramList.add(m8409.getSETSEQ());
        paramList.add(m8409.getCRNYER());
        paramList.add(m8409.getSTMPNY());
        paramList.add(m8409.getNSTMPG());
        paramList.add(m8409.getPROFLG());
        // Ö´ÐÐsbs½»Ò×
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8409", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8409-É¾³ý] ´«Æ±ºÅ£º" + m8409.getVCHSET()+ " ·µ»ØÂë£º");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
