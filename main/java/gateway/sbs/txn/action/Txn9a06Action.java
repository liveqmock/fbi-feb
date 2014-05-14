package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9a01;
import gateway.sbs.txn.model.msg.M9a06;
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
public class Txn9a06Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9a06Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9a06 m9a06 = (M9a06) tia;
        logger.info("[9a06-查询] 费率码：" + m9a06.getFRTCDE());

        List<String> paramList = new ArrayList<>();
        paramList.add(m9a06.getBATSEQ());
        paramList.add(m9a06.getORGIDT());
        paramList.add(m9a06.getDEPNUM());
        paramList.add(m9a06.getCURCDE());
        paramList.add(m9a06.getFRTCDE());
        paramList.add(m9a06.getEFFDAT());
        paramList.add(m9a06.getFRTNAM());
        paramList.add(m9a06.getDOCCDE());
        paramList.add(m9a06.getDOCNAM());
        paramList.add(m9a06.getFRTSPH());
        paramList.add(m9a06.getFRTSPL());
        paramList.add(m9a06.getSPRFLG());
        paramList.add(m9a06.getMALFEE());
        paramList.add(m9a06.getCOMFEE());
        paramList.add(m9a06.getFEERAT());
        paramList.add(m9a06.getHIGLIM());
        paramList.add(m9a06.getLOWLIM());
        paramList.add(m9a06.getFEECYC());
        paramList.add(m9a06.getFEESDE());
        paramList.add(m9a06.getFEEAPC());
        paramList.add(m9a06.getFUNCDE());
        paramList.add(m9a06.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9a06", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9a06-查询] 费率码：" + m9a06.getCURCDE()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
