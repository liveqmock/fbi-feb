package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.core.domain.SOFFormBody;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8420;
import gateway.sbs.txn.model.msg.M85a2;
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
 * Date: 14-3-25
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8420Action extends  AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8420Action.class);

    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8420 m8420 = (M8420) tia;
        logger.info("[8420-查询] 传票号：" + m8420.getVCHSET());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8420.getBATSEQ());
        paramList.add(m8420.getORGIDT());
        paramList.add(m8420.getDEPNUM());
        paramList.add(m8420.getVCHSET());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8420", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8420-查询]传票号：" + m8420.getVCHSET()+ " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
