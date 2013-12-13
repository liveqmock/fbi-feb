package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma130;
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
public class Txna130Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna130Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma130 ma130 = (Ma130) tia;
        logger.info("账户号：" + ma130.getIPTAC1());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma130.getACTTY1());
        paramList.add(ma130.getIPTAC1());
        paramList.add(ma130.getDRAMD1());
        paramList.add(ma130.getCUSPW1());
        paramList.add(ma130.getTXNDAT());
        paramList.add(ma130.getADVNUM());
        paramList.add(ma130.getTXNAMT());
        paramList.add(ma130.getACTTY2());
        paramList.add(ma130.getIPTAC2());
        paramList.add(ma130.getPASTYP());
        paramList.add(ma130.getPASSNO());
        paramList.add(ma130.getREMARK());
        paramList.add(ma130.getANACDE());
        paramList.add(ma130.getMAGFL1());
        paramList.add(ma130.getMAGFL2());




        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid,auttlr,autpwd, "a130", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账户号：" + ma130.getIPTAC1());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
