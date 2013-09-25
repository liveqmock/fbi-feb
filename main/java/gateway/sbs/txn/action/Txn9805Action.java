package gateway.sbs.txn.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gateway.sbs.core.SBSResponse;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.msg.M9805;
import gateway.sbs.txn.model.msg.MTia;

import java.util.ArrayList;
import java.util.List;

/**
 * 9805-利率多笔查询
 */
@Component
public class Txn9805Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9805Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9805 m9805 = (M9805) tia;
        logger.info("[9805-利率查询] 币别：" + m9805.getCURCDE() + " 日期：" + m9805.getEFFDAT());

        List<String> paramList = new ArrayList<>();
        paramList.add(m9805.getBATSEQ());
        paramList.add(m9805.getORGIDT());
        paramList.add(m9805.getDEPNUM());
        paramList.add(m9805.getCURCDE());
        paramList.add(m9805.getIRTCDE());
        paramList.add(m9805.getEFFDAT());
        paramList.add(m9805.getFUNCDE());
        paramList.add(m9805.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9805", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9805-利率查询] 币别：" + m9805.getCURCDE() + " 日期：" +
                m9805.getEFFDAT() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
