package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma100;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 定期部分支取
 */
@Component
public class Txna100Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna100Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma100 ma100 = (Ma100) tia;
        logger.info("账户号：" + ma100.getIPTAC1());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma100.getACTTY1());
        paramList.add(ma100.getIPTAC1());
        paramList.add(ma100.getDUEFLG());
        paramList.add(ma100.getDRAMD1());
        paramList.add(ma100.getCUSPW1());
        paramList.add(ma100.getDPTPRD());
        paramList.add(ma100.getVALDAT());
        paramList.add(ma100.getTXNAMT());
        paramList.add(ma100.getVCHTYP());
        paramList.add(ma100.getVCHNUM());
        paramList.add(ma100.getPASTYP());
        paramList.add(ma100.getPASSNO());
        paramList.add(ma100.getACTTY2());
        paramList.add(ma100.getIPTAC2());
        paramList.add(ma100.getREMARK());
        paramList.add(ma100.getANACDE());
        paramList.add(ma100.getVCHTY1());
        paramList.add(ma100.getVCHNU1());
        paramList.add(ma100.getMAGFL1());











        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, auttlr,autpwd,"a100", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账户号：" + ma100.getIPTAC1());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
