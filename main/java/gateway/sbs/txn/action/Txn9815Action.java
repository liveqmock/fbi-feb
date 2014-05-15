package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9805;
import gateway.sbs.txn.model.msg.M9815;
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
 * Date: 13-9-10
 * Time: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9815Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn9815Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9815 m9815 = (M9815) tia;
        logger.info("[9815-查询] 损益码：" + m9815.getPLCODE());

        List<String> paramList = new ArrayList<>();
        paramList.add(m9815.getBATSEQ());
        paramList.add(m9815.getORGIDT());
        paramList.add(m9815.getDEPNUM());
        paramList.add(m9815.getPLCODE());
        paramList.add(m9815.getPLCNAM());
        paramList.add(m9815.getGLCODE());
        paramList.add(m9815.getPLSCDE());
        paramList.add(m9815.getPLCTYP());
        paramList.add(m9815.getFUNCDE());
        paramList.add(m9815.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9815", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9815-查询] 损益码：" + m9815.getPLCODE()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();

    }
}
