package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8834;
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
 * Date: 13-11-5
 * Time: 上午9:00
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8834Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8834Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M8834 m8834 = (M8834)tia;
        logger.info("传票号：" + m8834.getVCHSET());
        List<String> paramList = new ArrayList<>();
        paramList.add(m8834.getBATSEQ());
        paramList.add(m8834.getORGIDT());
        paramList.add(m8834.getDEPNUM());
        paramList.add(m8834.getCURCDE());
        paramList.add(m8834.getAPCODE());
        paramList.add(m8834.getCUSIDT());
        paramList.add(m8834.getTXNAMT());
        paramList.add(m8834.getFUNCDE());
        paramList.add(m8834.getBEGNUM());
        paramList.add(m8834.getVCHSET());
        paramList.add(m8834.getTLRNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8834", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("传票号：" + m8834.getVCHSET() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
