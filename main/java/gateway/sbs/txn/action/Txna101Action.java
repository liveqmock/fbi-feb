package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma101;
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
public class Txna101Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna101Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma101 ma101 = (Ma101) tia;
        logger.info("账户号：" + ma101.getIPTAC1());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma101.getACTTY1());
        paramList.add(ma101.getIPTAC1());
        paramList.add(ma101.getDUEFLG());
        paramList.add(ma101.getDRAMD1());
        paramList.add(ma101.getCUSPW1());
        paramList.add(ma101.getDPTPRD());
        paramList.add(ma101.getVALDAT());
        paramList.add(ma101.getTXNAMT());
        paramList.add(ma101.getVCHTYP());
        paramList.add(ma101.getVCHNUM());
        paramList.add(ma101.getPASTYP());
        paramList.add(ma101.getPASSNO());
        paramList.add(ma101.getACTTY2());
        paramList.add(ma101.getIPTAC2());
        paramList.add(ma101.getREMARK());
        paramList.add(ma101.getANACDE());
        paramList.add(ma101.getVCHTY1());
        paramList.add(ma101.getVCHNU1());
        paramList.add(ma101.getMAGFL1());











        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, auttlr,autpwd,"a101", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账户号：" + ma101.getIPTAC1());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
