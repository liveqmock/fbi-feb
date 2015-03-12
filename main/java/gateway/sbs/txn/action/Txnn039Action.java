package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn036;
import gateway.sbs.txn.model.msg.Mn039;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n036-对公支付多笔确认
 */
@Component
public class Txnn039Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn039Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn039 mn039 = (Mn039) tia;
        logger.info("[n039-对公支付多笔确认新] 日期：" + mn039.getORDDAT() +
                " 起始笔数：" + mn039.getBEGNUM() + " 状态：" + mn039.getPRCSTS());

        List<String> paramList = new ArrayList<>();
        paramList.add(mn039.getORDDAT());
        paramList.add(mn039.getPRCSTS());
        paramList.add(mn039.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "n039", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n039-对公支付多笔确认新] 日期：" + mn039.getORDDAT() +
                " 起始笔数：" + mn039.getBEGNUM() + " 状态：" + mn039.getPRCSTS() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
