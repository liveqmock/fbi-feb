package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8857;
import gateway.sbs.txn.model.msg.M8951;
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
 * Date: 14-6-5          查询账户结息明细
 * Time: 上午8:55
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8857Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8857Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M8857 m8857 = (M8857) tia;
        logger.info("账户号：" + m8857.getACTNUM());
        List<String> paramList = new ArrayList<>();
        paramList.add(m8857.getACTNUM());
        paramList.add(m8857.getBEGDAT());
        paramList.add(m8857.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8857", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账户号：" + m8857.getACTNUM());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
