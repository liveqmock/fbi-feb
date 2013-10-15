package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn035;
import gateway.sbs.txn.model.msg.Mn038;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n038-对公支付确认
 */
@Component
public class Txnn038Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn038Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn038 mn038 = (Mn038) tia;
        logger.info("[n038-对公支付确认] 索引：" + mn038.getFBTIDX());

        List<String> paramList = new ArrayList<>();
        paramList.add(mn038.getFBTIDX());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "n038", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n038-对公支付确认] 索引：：" + mn038.getFBTIDX() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
