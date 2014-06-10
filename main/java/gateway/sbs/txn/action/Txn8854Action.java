package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8854;
import gateway.sbs.txn.model.msg.M8857;
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
 * Date: 14-6-5          查询账户分户账明细
 * Time: 上午8:55
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8854Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8854Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M8854 m8854 = (M8854) tia;
        logger.info("账户号：" + m8854.getACTNUM());
        List<String> paramList = new ArrayList<>();
        paramList.add(m8854.getBATSEQ());
        paramList.add(m8854.getORGIDT());
        paramList.add(m8854.getDEPNUM());
        paramList.add(m8854.getORGID3());
        paramList.add(m8854.getACTNUM());
        paramList.add(m8854.getBEGDAT());
        paramList.add(m8854.getENDDAT());
        paramList.add(m8854.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8854", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账户号：" + m8854.getACTNUM());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
