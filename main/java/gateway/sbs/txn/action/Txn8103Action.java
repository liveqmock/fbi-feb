package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8103;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 8103-关闭客户账户
 */
@Component
public class Txn8103Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8103Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8103 m8103 = (M8103) tia;
        logger.info("[8103-关闭客户账户] 账号：" + m8103.getACTNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8103.getBATSEQ());
        paramList.add(m8103.getORGIDT());
        paramList.add(m8103.getDEPNUM());
        paramList.add(m8103.getACTNUM());
        paramList.add(m8103.getVCHAUT());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, auttlr, autpwd, "8103", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8103-关闭客户账户] 账号：" + m8103.getACTNUM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
