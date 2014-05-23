package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9a29;
import gateway.sbs.txn.model.msg.M9a30;
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
 * Date: 14-5-13  税率码
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9a29Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9a29Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9a29 m9a29 = (M9a29) tia;
        logger.info("[9a29-查询] 税表号：" + m9a29.getTAXTNO());

        List<String> paramList = new ArrayList<>();
        paramList.add(m9a29.getBATSEQ());
        paramList.add(m9a29.getORGIDT());
        paramList.add(m9a29.getDEPNUM());
        paramList.add(m9a29.getTAXTNO());
        paramList.add(m9a29.getTAXCDE());
        paramList.add(m9a29.getTAXRAT());
        paramList.add(m9a29.getTAXADJ());
        paramList.add(m9a29.getFUNCDE());
        paramList.add(m9a29.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9a29", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9a29-查询] 税表号：" + m9a29.getTAXTNO()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
