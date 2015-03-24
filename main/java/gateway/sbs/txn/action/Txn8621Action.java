package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8420;
import gateway.sbs.txn.model.msg.M8621;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lichao.W At 2015/3/24 9:17
 * wanglichao@163.com
 */
@Component
public class Txn8621Action extends AbstractTxnAction{
    private static Logger logger = LoggerFactory.getLogger(Txn8621Action.class);

    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8621 m8621 = (M8621) tia;
        logger.info("[8621-查询] 币别：" + m8621.getCURCDE());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8621.getCURCDE());
        paramList.add(m8621.getBEGNUM());
        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8621", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8621-查询] 币别：" + m8621.getCURCDE()+ " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
