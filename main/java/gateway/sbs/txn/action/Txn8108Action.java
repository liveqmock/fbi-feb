package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8108;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 8108-账户查询
 */
@Component
public class Txn8108Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8108Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

        M8108 m8108 = (M8108) tia;
        logger.info("[8108-账户查询] 账号：" + m8108.getACTNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8108.getBATSEQ());
        paramList.add(m8108.getORGIDT());
        paramList.add(m8108.getDEPNUM());
        paramList.add(m8108.getORGID3());
        paramList.add(m8108.getACTNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "8108", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8108-账户查询] 账号：" + m8108.getACTNUM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
