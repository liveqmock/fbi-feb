package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8123;
import gateway.sbs.txn.model.msg.M8854;
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
 * Date: 14-6-5          多条件查询客户账户余额
 * Time: 上午8:55
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8123Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8123Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M8123 m8123 = (M8123) tia;
        logger.info("客户号：" + m8123.getCUSIDT());
        List<String> paramList = new ArrayList<>();
        paramList.add(m8123.getCUSIDT());
        paramList.add(m8123.getAPCODE());
        paramList.add(m8123.getCURCDE());
        paramList.add(m8123.getACTTYP());
        paramList.add(m8123.getCUSKID());
        paramList.add(m8123.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8123", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("客户号：" + m8123.getCUSIDT());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
