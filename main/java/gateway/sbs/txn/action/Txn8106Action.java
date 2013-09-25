package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8106;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 8106-关闭内部账户
 */
@Component
public class Txn8106Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8106Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8106 m8106 = (M8106) tia;
        logger.info("[8106-关闭内部账户] 账号：" + m8106.getACTNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8106.getBATSEQ());
        paramList.add(m8106.getORGIDT());
        paramList.add(m8106.getDEPNUM());
        paramList.add(m8106.getACTNUM());
        paramList.add(m8106.getVCHAUT());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, auttlr, autpwd, "8106", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8106-关闭内部账户] 账号：" + m8106.getACTNUM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
