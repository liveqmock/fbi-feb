package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M0003;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 0003-强制签退柜员
 */
@Component
public class Txn0003Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn0003Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

        M0003 m0003 = (M0003) tia;
        logger.info("[0003-签退柜员] 柜员号：" + m0003.getTLRNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m0003.getTLRNUM());
        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "0003", paramList);

        logger.info(response.getFormCodes().toString());
        return response.getSofForms();
    }
}
