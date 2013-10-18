package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mh830;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * H803重要凭证代号维护单笔查询
 */
@Component
public class Txnh830Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnh830Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mh830 mh830 = (Mh830) tia;
        logger.info("凭证：" + mh830.getVCHTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(mh830.getALLONE());
        paramList.add(mh830.getVCHTYP());
        paramList.add(mh830.getORGIDT());
        paramList.add(mh830.getDEPNUM());
        paramList.add(mh830.getTXNTLR());





        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "h830", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("凭证：" + mh830.getVCHTYP());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
