package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M5832;
import gateway.sbs.txn.model.msg.M9816;
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
 * Date: 14-5-14       资产负债码查询
 * Time: 下午2:23
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9816Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9816Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M9816 m9816 = (M9816) tia;
        logger.info("[9816-查询] 资产负债项目号：" + m9816.getALCODE());

        List<String> paramList = new ArrayList<>();
        paramList.add(m9816.getBATSEQ());
        paramList.add(m9816.getORGIDT());
        paramList.add(m9816.getDEPNUM());
        paramList.add(m9816.getALCODE());
        paramList.add(m9816.getGLCODE());
        paramList.add(m9816.getALCNAM());
        paramList.add(m9816.getALCTYP());
        paramList.add(m9816.getDCTYPE());
        paramList.add(m9816.getOPRFLG());
        paramList.add(m9816.getGLCFLG());
        paramList.add(m9816.getSUMFLG());
        paramList.add(m9816.getASTRWT());
        paramList.add(m9816.getFUNCDE());
        paramList.add(m9816.getBEGNUM());

        SBSResponse response = coreTxnService.execute(termid, tellerid, "9816", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9816-查询] 资产负债项目号：" + m9816.getALCODE()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
