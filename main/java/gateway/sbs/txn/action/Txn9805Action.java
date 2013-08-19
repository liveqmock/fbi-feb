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
    public List<SOFForm> process(MTia tia) throws Exception {

        M9805 m9805 = (M9805) tia;
        logger.info("[9805-利率查询] 币别：" + m9805.getCURCDE() + " 日期：" + m9805.getIRTDAT());

        List<String> paramList = new ArrayList<>();
        paramList.add("111111");
        paramList.add("010");
        paramList.add("60");
        paramList.add(m9805.getCURCDE());
        paramList.add("");
        paramList.add(m9805.getIRTDAT());
        paramList.add("6");
        paramList.add("000001");

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(m9805.getTermid(), m9805.getOperid(), "9805", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9805-利率查询] 币别：" + m9805.getCURCDE() + " 日期：" +
                m9805.getIRTDAT() + "返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
