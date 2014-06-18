package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma130;
import gateway.sbs.txn.model.msg.Ma131;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知存款结清
 */
@Component
public class Txna131Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna131Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma131 ma131 = (Ma131) tia;
        logger.info("账户号：" + ma131.getIPTAC1());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma131.getACTTY1());
        paramList.add(ma131.getIPTAC1());
        paramList.add(ma131.getDRAMD1());
        paramList.add(ma131.getCUSPW1());
        paramList.add(ma131.getTXNDAT());
        paramList.add(ma131.getADVNUM());
        paramList.add(ma131.getTXNAMT());
        paramList.add(ma131.getACTTY2());
        paramList.add(ma131.getIPTAC2());
        paramList.add(ma131.getPASTYP());
        paramList.add(ma131.getPASSNO());
        paramList.add(ma131.getREMARK());
        paramList.add(ma131.getANACDE());
        paramList.add(ma131.getMAGFL1());
        paramList.add(ma131.getMAGFL2());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid,auttlr,autpwd, "a131", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账户号：" + ma131.getIPTAC1());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
