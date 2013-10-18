package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.Mh805;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * H805重要凭证代号维护多笔查询
 */
@Component
public class Txnh805Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnh805Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mh805 mh805 = (Mh805) tia;
        logger.info("凭证：" + mh805.getVCHTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(mh805.getVCHTYP());




        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "h805", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("凭证：" + mh805.getVCHTYP());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
