package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma832;
import gateway.sbs.txn.model.msg.Ma834;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 存款种类与利率码对应表维护-xiugai
 */
@Component
public class Txna834Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna834Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma834 ma834= (Ma834) tia;
        logger.info("存款种类：" + ma834.getDPTTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma834.getDPTTYP());
        paramList.add(ma834.getCURCDE());
        paramList.add(ma834.getACTTYP());
        paramList.add(ma834.getPRDTYP());
        paramList.add(ma834.getDPTPRD());
        paramList.add(ma834.getINTCDE());
        paramList.add(ma834.getRECSTS());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "a834", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("存款种类：" + ma834.getDPTTYP()+ " 返回码：");

        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
