package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma270;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * a270-定期存款开户查询
 */
@Component
public class Txna270Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna270Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma270 ma270 = (Ma270) tia;
        logger.info("[a270-定期存款开户查询] 客户号：" + ma270.getCUSIDT()
                + " 金额：" + ma270.getTXNAMT() + " 凭证号：" + ma270.getVCHNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(ma270.getSYSIDT());
        paramList.add(ma270.getCUSIDT());
        paramList.add(ma270.getDRAMD2());
        paramList.add(ma270.getCUSPW2());
        paramList.add(ma270.getACTNM2());
        paramList.add(ma270.getPASTYP());
        paramList.add(ma270.getPASSNO());
        paramList.add(ma270.getCORADD());

        paramList.add(ma270.getTELNUM());
        paramList.add(ma270.getTXNAMT());
        paramList.add(ma270.getVALDAT());
        paramList.add(ma270.getACTTYP());
        paramList.add(ma270.getDPTTYP());

        paramList.add(ma270.getDPTPRD());
        paramList.add(ma270.getDRICYC());
        paramList.add(ma270.getATOFLG());
        paramList.add(ma270.getVCHTYP());
        paramList.add(ma270.getVCHNUM());
        paramList.add(ma270.getACTTY1());
        paramList.add(ma270.getIPTAC1());
        paramList.add(ma270.getDRAMD1());

        paramList.add(ma270.getCUSPW1());
        paramList.add(ma270.getPAPTYP());
        paramList.add(ma270.getPAPCDE());
        paramList.add(ma270.getPAPMAC());
        paramList.add(ma270.getSGNDAT());
        paramList.add(ma270.getREMARK());
        paramList.add(ma270.getACTTY2());
        paramList.add(ma270.getIPTAC2());
        paramList.add(ma270.getVCHUSERATE());
        paramList.add(ma270.getMAGFL1());
        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "a270", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[a270-定期存款开户查询] 客户号：" + ma270.getCUSIDT()
                + " 金额：" + ma270.getTXNAMT() + " 凭证号：" + ma270.getVCHNUM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
