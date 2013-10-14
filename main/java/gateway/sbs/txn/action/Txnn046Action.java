package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn046;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n046-批量代发多笔查询
 */
@Component
public class Txnn046Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn046Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn046 mn046 = (Mn046) tia;
        logger.info("[n046-批量代发多笔查询] 日期：" + mn046.getORDDAT() +
                " 起始笔数：" + mn046.getBEGNUM() + " 状态：" + mn046.getPRCSTS());

        List<String> paramList = new ArrayList<>();
        paramList.add(mn046.getORDDAT());
        paramList.add(mn046.getPRCSTS());
        paramList.add(mn046.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "n046", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n046-批量代发多笔查询] 日期：" + mn046.getORDDAT() +
                " 起始笔数：" + mn046.getBEGNUM() + " 状态：" + mn046.getPRCSTS() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
