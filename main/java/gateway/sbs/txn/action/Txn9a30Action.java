package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9a06;
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
public class Txn9a30Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9a30Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9a30 m9a30 = (M9a30) tia;
        logger.info("[9a30-查询] 税率码：" + m9a30.getTAXCDE());

        List<String> paramList = new ArrayList<>();
        paramList.add(m9a30.getBATSEQ());
        paramList.add(m9a30.getORGIDT());
        paramList.add(m9a30.getDEPNUM());
        paramList.add(m9a30.getTAXCDE());
        paramList.add(m9a30.getTAXDCR());
        paramList.add(m9a30.getTAXFG1());
        paramList.add(m9a30.getTAXFG2());
        paramList.add(m9a30.getTAXFG3());
        paramList.add(m9a30.getFUNCDE());
        paramList.add(m9a30.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9a30", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9a30-查询] 税率码：" + m9a30.getTAXCDE()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
