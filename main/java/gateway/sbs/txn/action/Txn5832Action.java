package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M5832;
import gateway.sbs.txn.model.msg.M9a01;
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
public class Txn5832Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn5832Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M5832 m5832 = (M5832) tia;
        logger.info("[5832-汇率查询] 第二货币码：" + m5832.getSECCCY());

        List<String> paramList = new ArrayList<>();
        paramList.add(m5832.getBATSEQ());
        paramList.add(m5832.getORGIDT());
        paramList.add(m5832.getDEPNUM());
        paramList.add(m5832.getCURCDE());
        paramList.add(m5832.getXRTCDE());
        paramList.add(m5832.getSECCCY());
        paramList.add(m5832.getEFFDAT());
        paramList.add(m5832.getEFFTIM());
        paramList.add(m5832.getEXPDAT());
        paramList.add(m5832.getFUNCDE());

        SBSResponse response = coreTxnService.execute(termid, tellerid, "5832", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[5832-汇率查询] 第二货币码：" + m5832.getSECCCY()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
