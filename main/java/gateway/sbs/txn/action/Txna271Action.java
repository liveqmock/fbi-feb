package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma271;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * a271-定期存款开户
 */
@Component
public class Txna271Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna271Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma271 ma271 = (Ma271) tia;
        logger.info("[a271-定期存款开户] 客户号：" + ma271.getCUSIDT()
                + " 金额：" + ma271.getTXNAMT() + " 凭证号：" + ma271.getVCHNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(ma271.getSYSIDT());
        paramList.add(ma271.getCUSIDT());
        paramList.add(ma271.getDRAMD2());
        paramList.add(ma271.getCUSPW2());
        paramList.add(ma271.getACTNM2());
        paramList.add(ma271.getPASTYP());
        paramList.add(ma271.getPASSNO());
        paramList.add(ma271.getCORADD());

        paramList.add(ma271.getTELNUM());
        paramList.add(ma271.getTXNAMT());
        paramList.add(ma271.getVALDAT());
        paramList.add(ma271.getACTTYP());
        paramList.add(ma271.getDPTTYP());

        paramList.add(ma271.getDPTPRD());
        paramList.add(ma271.getDRICYC());
        paramList.add(ma271.getATOFLG());
        paramList.add(ma271.getVCHTYP());
        paramList.add(ma271.getVCHNUM());
        paramList.add(ma271.getACTTY1());
        paramList.add(ma271.getIPTAC1());
        paramList.add(ma271.getDRAMD1());

        paramList.add(ma271.getCUSPW1());
        paramList.add(ma271.getPAPTYP());
        paramList.add(ma271.getPAPCDE());
        paramList.add(ma271.getPAPMAC());
        paramList.add(ma271.getSGNDAT());
        paramList.add(ma271.getREMARK());
        paramList.add(ma271.getACTTY2());
        paramList.add(ma271.getIPTAC2());
        paramList.add(ma271.getVCHUSERATE());
        paramList.add(ma271.getMAGFL1());
        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "a271", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[a271-定期存款开户] 客户号：" + ma271.getCUSIDT()
                + " 金额：" + ma271.getTXNAMT() + " 凭证号：" + ma271.getVCHNUM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
