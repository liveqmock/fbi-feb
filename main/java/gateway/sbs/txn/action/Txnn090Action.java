package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M0003;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn090;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * IET-n090-内部总分账户余额清算
 */
@Component
public class Txnn090Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn090Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn090 mn090 = (Mn090) tia;

        List<String> paramList = new ArrayList<>();
        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "n090", paramList);

        logger.info(response.getFormCodes().toString());
        return response.getSofForms();
    }
}
