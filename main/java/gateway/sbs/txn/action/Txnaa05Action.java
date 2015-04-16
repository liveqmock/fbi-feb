package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Maa05;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lichao.W At 2015/4/15 9:10
 * wanglichao@163.com
 */
@Component
public class Txnaa05Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txnaa05Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Maa05 maa05 = (Maa05) tia;
        logger.info("[aa05-内转记账撤销] 流水号：" + maa05.getFBTIDX());

        List<String> paramList = new ArrayList<>();
        paramList.add(maa05.getFBTIDX());
        SBSResponse response = coreTxnService.execute(termid, tellid, "aa05", paramList);
        StringBuffer rtnFormCodes = new StringBuffer("[aa05-内转记账撤销] 流水号：" + maa05.getFBTIDX() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(response.getFormCodes().toString());
        return response.getSofForms();
    }
}
