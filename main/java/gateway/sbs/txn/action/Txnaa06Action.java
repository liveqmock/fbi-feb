package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma842;
import gateway.sbs.txn.model.msg.Maa06;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lichao.W At 2015/4/14 8:56
 * wanglichao@163.com
 */
@Component
public class Txnaa06Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txnaa06Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Maa06 maa06 = (Maa06) tia;
        logger.info("[aa6-内转记账查询] 交易金额：" + maa06.getTXNAMT());

        List<String> paramList = new ArrayList<>();
        paramList.add(maa06.getTXNDAT());
        paramList.add(maa06.getPAPSTS());
        paramList.add(maa06.getIPTAC1());
        paramList.add(maa06.getIPTAC2());
        paramList.add(maa06.getTXNAMT());
        paramList.add(maa06.getANACDE());
        paramList.add(maa06.getCUSPW1());
        SBSResponse response = coreTxnService.execute(termid, tellid, "aa06", paramList);
        StringBuffer rtnFormCodes = new StringBuffer("[aa6-内转记账查询] 交易金额：" + maa06.getTXNAMT() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(response.getFormCodes().toString());
        return response.getSofForms();
    }
}
