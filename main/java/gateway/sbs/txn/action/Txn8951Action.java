package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8951;
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
 * Date: 14-4-30
 * Time: 上午8:55
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8951Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8951Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M8951 m8951 = (M8951) tia;
        logger.info("渠道代码：" + m8951.getANACDE());
        List<String> paramList = new ArrayList<>();
        paramList.add(m8951.getFUNCDE());
        paramList.add(m8951.getRECTYP());
        paramList.add(m8951.getANACDE());
        paramList.add(m8951.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8951", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("渠道代码：" + m8951.getANACDE());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
