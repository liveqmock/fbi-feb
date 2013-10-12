package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M85a2;
import gateway.sbs.txn.model.msg.M9815;
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
 * Date: 13-9-10
 * Time: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn85a2Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn85a2Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M85a2 m85a2 = (M85a2) tia;
        logger.info("[85a2-查询] 传票号：" + m85a2.getVCHSET());

        List<String> paramList = new ArrayList<>();
        paramList.add(m85a2.getBATSEQ());
        paramList.add(m85a2.getORGIDT());
        paramList.add(m85a2.getDEPNUM());
        paramList.add(m85a2.getVCHSET());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "85a2", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[85a2-查询]  传票号：" + m85a2.getVCHSET()+ " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
