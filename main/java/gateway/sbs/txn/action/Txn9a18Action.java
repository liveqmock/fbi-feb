package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9a06;
import gateway.sbs.txn.model.msg.M9a18;
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
 * Date: 14-5-13  币别码
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9a18Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9a18Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9a18 m9a18 = (M9a18)tia;
        logger.info("[9a18-查询] 国家码：" + m9a18.getCTRCDE());

        List<String> paramList = new ArrayList<>();
        paramList.add(m9a18.getBATSEQ());
        paramList.add(m9a18.getORGIDT());
        paramList.add(m9a18.getDEPNUM());
        paramList.add(m9a18.getCTRCDE());
        paramList.add(m9a18.getCTRNMC());
        paramList.add(m9a18.getCTRNME());
        paramList.add(m9a18.getSWFCTR());
        paramList.add(m9a18.getRSVFG1());
        paramList.add(m9a18.getRSVFG2());
        paramList.add(m9a18.getRSVFG3());
        paramList.add(m9a18.getFUNCDE());
        paramList.add(m9a18.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9a18", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9a18-查询] 国家码：" + m9a18.getCTRCDE()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
