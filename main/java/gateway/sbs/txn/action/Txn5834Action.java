package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M5832;
import gateway.sbs.txn.model.msg.M5834;
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
 * Date: 14-5-14
 * Time: 下午2:23
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn5834Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn5834Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M5834 m5834 = (M5834) tia;
        logger.info("[5834-汇率查询] 第二货币码：" + m5834.getSECCCY());

        List<String> paramList = new ArrayList<>();
        paramList.add(m5834.getBATSEQ());
        paramList.add(m5834.getORGIDT());
        paramList.add(m5834.getDEPNUM());
        paramList.add(m5834.getCURCDE());
        paramList.add(m5834.getSECCCY());
        paramList.add(m5834.getEFFDAT());
        paramList.add(m5834.getEFFTIM());
        paramList.add(m5834.getRATVA1());
        paramList.add(m5834.getRATFL1());
        paramList.add(m5834.getRATVA2());
        paramList.add(m5834.getRATFL2());
        paramList.add(m5834.getRATVA3());
        paramList.add(m5834.getRATFL3());
        paramList.add(m5834.getRATVA4());
        paramList.add(m5834.getRATFL4());
        paramList.add(m5834.getRATVA5());
        paramList.add(m5834.getRATFL5());
        paramList.add(m5834.getRATVA6());
        paramList.add(m5834.getRATFL6());
        paramList.add(m5834.getRATVA7());
        paramList.add(m5834.getRATFL7());
        paramList.add(m5834.getRATVA8());
        paramList.add(m5834.getRATFL8());
        paramList.add(m5834.getRATVA9());
        paramList.add(m5834.getRATFL9());
        paramList.add(m5834.getFUNCDE());

        SBSResponse response = coreTxnService.execute(termid, tellerid, "5834", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[5834-汇率查询] 第二货币码：" + m5834.getSECCCY()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
