package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn036;
import gateway.sbs.txn.model.msg.Mn037;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n037-对公支付单笔查询
 */
@Component
public class Txnn037Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn037Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn037 mn037 = (Mn037) tia;
        logger.info("[n037-对公支付单笔查询] 日期：" + mn037.getORDDAT() + " 索引号：" + mn037.getFBTIDX());

        List<String> paramList = new ArrayList<>();
        paramList.add(mn037.getFBTIDX());
        paramList.add(mn037.getORDDAT());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "n037", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n037-对公支付单笔查询] 日期：" + mn037.getORDDAT() +
                " 索引号：" + mn037.getFBTIDX() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
