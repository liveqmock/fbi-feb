package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8002;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-5
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8002Action extends AbstractTxnAction  {
    private static Logger logger = LoggerFactory.getLogger(Txn8002Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8002 m8002 = (M8002) tia;
        logger.info("[8002-查询客户账户] 账号：" + m8002.getCusidt()+"姓名："+m8002.getCusnam());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8002.getCusidt());
        paramList.add(m8002.getCusnam());

        SBSResponse response = coreTxnService.execute(termid, tellid, "8002", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账号：" + m8002.getCusidt());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
