package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mh803;
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
public class Txnh803Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnh803Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mh803 mh803 = (Mh803) tia;
        logger.info("凭证：" + mh803.getVCHTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(mh803.getVCHTYP());





        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "h803", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("凭证：" + mh803.getVCHTYP());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
