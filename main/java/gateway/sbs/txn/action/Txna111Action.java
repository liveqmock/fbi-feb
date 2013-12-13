package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma111;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class Txna111Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna111Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma111 ma111 = (Ma111) tia;
        logger.info("账户号：" + ma111.getIPTAC2());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma111.getACTTY2());
        paramList.add(ma111.getIPTAC2());
        paramList.add(ma111.getDRAMD2());
        paramList.add(ma111.getCUSPW2());
        paramList.add(ma111.getADVNUM());
        paramList.add(ma111.getTXNDAT());
        paramList.add(ma111.getADVAMT());
        paramList.add(ma111.getADVDAT());
        paramList.add(ma111.getPASTYP());
        paramList.add(ma111.getPASSNO());
        paramList.add(ma111.getREMARK());




        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid,auttlr,autpwd, "a111", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账户号：" + ma111.getIPTAC2());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
