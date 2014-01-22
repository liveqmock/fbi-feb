package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma280;
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
public class Txna280Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna280Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma280 ma280 = (Ma280) tia;
        logger.info("账户号：" + ma280.getIPTAC1());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma280.getACTTY1());
        paramList.add(ma280.getIPTAC1());
        paramList.add(ma280.getDRAMD1());
        paramList.add(ma280.getCUSPW1());
        paramList.add(ma280.getDRAIDT());
        paramList.add(ma280.getPASTYP());
        paramList.add(ma280.getPASSNO());
        paramList.add(ma280.getTXNDAT());
        paramList.add(ma280.getDUEFLG());
        paramList.add(ma280.getACTTY2());
        paramList.add(ma280.getIPTAC2());
        paramList.add(ma280.getNBKFL2());
        paramList.add(ma280.getREMARK());
        paramList.add(ma280.getVCHTYP());
        paramList.add(ma280.getVCHNUM());
        paramList.add(ma280.getMAGFL1());
        paramList.add(ma280.getMAGFL2());


        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "a280", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账户号：" + ma280.getIPTAC1());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
