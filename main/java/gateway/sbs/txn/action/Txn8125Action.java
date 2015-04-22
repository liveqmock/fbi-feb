package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8125;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lichao.W At 2015/4/20 10:14
 * wanglichao@163.com
 */
@Component
public class Txn8125Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8125Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M8125 m8125 = (M8125) tia;
        logger.info("科目类别：" + m8125.getACTTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(m8125.getACTTYP());
        paramList.add(m8125.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8125", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("科目类别：" + m8125.getACTTYP() + " 返回码： ");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
