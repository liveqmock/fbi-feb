package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8002;
import gateway.sbs.txn.model.msg.M8003;
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
public class Txn8003Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8003Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8003 m8003 = (M8003) tia;
        logger.info("[8003-客户关闭] 客户号：" + m8003.getCUSIDT());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8003.getBATSEQ());
        paramList.add(m8003.getORGIDT());
        paramList.add(m8003.getDEPNUM());
        paramList.add(m8003.getCUSIDT());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8003", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8003-客户关闭] 客户号：" + m8003.getCUSIDT() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
