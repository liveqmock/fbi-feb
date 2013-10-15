package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn047;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n047-批量代发单笔查询
 */
@Component
public class Txnn047Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn047Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn047 mn047 = (Mn047) tia;
        logger.info("[n047-批量代发单笔查询] 日期：" + mn047.getORDDAT() + " 索引号：" + mn047.getFBTIDX());

        List<String> paramList = new ArrayList<>();
        paramList.add(mn047.getFBTIDX());
        paramList.add(mn047.getORDDAT());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "n047", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n047-批量代发单笔查询] 日期：" + mn047.getORDDAT() +
                " 索引号：" + mn047.getFBTIDX() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
