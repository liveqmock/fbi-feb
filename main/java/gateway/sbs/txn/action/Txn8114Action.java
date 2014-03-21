package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.core.domain.SOFFormBody;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8114;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-18
 * Time: 下午12:03
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8114Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8114Action.class);

    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8114 m8114 = (M8114) tia;
        logger.info("[8114-清单打印账户查询] 账号：" + m8114.getACTNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8114.getBATSEQ());
        paramList.add(m8114.getORGIDT());
        paramList.add(m8114.getDEPNUM());
        paramList.add(m8114.getORGID3());
        paramList.add(m8114.getACTNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, auttlr, autpwd, "8114", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8114-清单账户查询打印] 账号：" + m8114.getACTNUM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
