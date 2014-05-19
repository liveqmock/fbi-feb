package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9a24;
import gateway.sbs.txn.model.msg.M9a25;
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
 * Date: 14-5-19
 * Time: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9a25Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn9a25Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9a25 m9a25 = (M9a25)tia;
        logger.info("[9a25-查询] 自动要素码：" + m9a25.getTRFNUM());
        List<String> paramList = new ArrayList<>();
        paramList.add(m9a25.getBATSEQ());
        paramList.add(m9a25.getORGIDT());
        paramList.add(m9a25.getDEPNUM());
        paramList.add(m9a25.getATRCDE());
        paramList.add(m9a25.getTRFSEQ());
        paramList.add(m9a25.getTRFKID());
        paramList.add(m9a25.getTRFNUM());
        paramList.add(m9a25.getTRFOPR());
        paramList.add(m9a25.getAMTTYP());
        paramList.add(m9a25.getAMTSDE());
        paramList.add(m9a25.getFUNCDE());
        paramList.add(m9a25.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9a25", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9a25-查询] 自动要素码：" + m9a25.getTRFNUM()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
