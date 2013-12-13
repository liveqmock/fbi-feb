package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma121;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * H803重要凭证代号维护单笔查询
 */
@Component
public class Txna121Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna121Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma121 ma121 = (Ma121) tia;
        logger.info("账户号：" + ma121.getIPTAC2());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma121.getACTTY2());
        paramList.add(ma121.getIPTAC2());
        paramList.add(ma121.getDRAMD2());
        paramList.add(ma121.getCUSPW2());
        paramList.add(ma121.getADVNUM());
        paramList.add(ma121.getTXNDAT());
        paramList.add(ma121.getPASTYP());
        paramList.add(ma121.getPASSNO());
        paramList.add(ma121.getREMARK());




        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid,auttlr,autpwd, "a121", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账户号：" + ma121.getIPTAC2());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
