package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9807;
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
 * Date: 14-5-13  费率码
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9807Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9807Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9807 m9807 = (M9807) tia;
        logger.info("[9807-查询] 收费码：" + m9807.getFRTCDE());

        List<String> paramList = new ArrayList<>();
        paramList.add(m9807.getBATSEQ());
        paramList.add(m9807.getORGIDT());
        paramList.add(m9807.getDEPNUM());
        paramList.add(m9807.getCURCDE());
        paramList.add(m9807.getFRTCDE());
        paramList.add(m9807.getEFFDAT());
        paramList.add(m9807.getFUNCDE());
        paramList.add(m9807.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9807", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9807-查询] 收费码：" + m9807.getFRTCDE()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
