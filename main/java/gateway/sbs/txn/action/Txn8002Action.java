package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8002;
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
 * Date: 13-9-12                   // 客户信息查询
 * Time: 下午9:51
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8002Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8002Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8002 m8002 = (M8002) tia;
        logger.info("[8002-客户查询] 客户号：" + m8002.getCUSIDT());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8002.getBATSEQ());
        paramList.add(m8002.getORGIDT());
        paramList.add(m8002.getDEPNUM());
        paramList.add(m8002.getCUSIDT());
        paramList.add(m8002.getPASTYP());
        paramList.add(m8002.getPASSNO());
        paramList.add(m8002.getCUSIDX());
        paramList.add(m8002.getLEGBDY());
        paramList.add(m8002.getRELCUS());
        paramList.add(m8002.getINPFLG());
        paramList.add(m8002.getBEGNUM());
        paramList.add(m8002.getCUSNAM());
        paramList.add(m8002.getSYSIDT());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8002", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8002-客户查询] 客户号：" + m8002.getCUSIDT() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
