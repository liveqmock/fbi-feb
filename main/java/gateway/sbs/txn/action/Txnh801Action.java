package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.Mh801;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class Txnh801Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnh801Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mh801 mh801 = (Mh801) tia;
        logger.info("凭证种类：" + mh801.getVCHTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(mh801.getVCHTYP());
        paramList.add(mh801.getOUTITM());
        paramList.add(mh801.getSCTMAK());
        paramList.add(mh801.getVCHNAM());
        paramList.add(mh801.getVCHAMT());






        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "h801", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("凭证种类：" + mh801.getVCHTYP());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
