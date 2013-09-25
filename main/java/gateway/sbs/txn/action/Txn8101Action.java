package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8101;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 8101-客户开户
 */
@Component
public class Txn8101Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8101Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8101 m8101 = (M8101) tia;
        logger.info("[8101-客户开户] 账号：" + m8101.getACTNUM() + " 账户名称：" + m8101.getACTNAM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8101.getBATSEQ());
        paramList.add(m8101.getORGIDT());
        paramList.add(m8101.getDEPNUM());
        paramList.add(m8101.getACTNUM());
        paramList.add(m8101.getACTNAM());
        paramList.add(m8101.getSTMFMT());
        paramList.add(m8101.getSTMSHT());
        paramList.add(m8101.getSTMDEP());
        paramList.add(m8101.getSTMADD());   // 对账单地址
        paramList.add(m8101.getSTMZIP());
        paramList.add(m8101.getSTMCYC());
        paramList.add(m8101.getSTMCDT());
        paramList.add(m8101.getACTTYP());
        paramList.add(m8101.getDEPNU3());
        paramList.add(m8101.getINTFLG());
        paramList.add(m8101.getINTCYC());   // 计息周期

        paramList.add(m8101.getINTTRA());
        paramList.add(m8101.getCQEFLG());
        paramList.add(m8101.getBALLIM());
        paramList.add(m8101.getOVELIM());
        paramList.add(m8101.getOVEEXP());     // 额度到期日

        paramList.add(m8101.getDINRAT());
        paramList.add(m8101.getCINRAT());
        paramList.add(m8101.getDRATSF());
        paramList.add(m8101.getCRATSF());
        paramList.add(m8101.getVCHAUT());
        paramList.add(m8101.getLEGCYC());
        paramList.add(m8101.getLEGCDT());   // 分户账出账日期

        paramList.add(m8101.getLEGFMT());
        paramList.add(m8101.getLEGADD());
        paramList.add(m8101.getLEGZIP());
        paramList.add(m8101.getLEGSHT());
        paramList.add(m8101.getLEGDEP());
        paramList.add(m8101.getGLCODE());
        paramList.add(m8101.getFXRATE());
        paramList.add(m8101.getLOCCAP());
        paramList.add(m8101.getTXNAMT());      // 交易金额

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "8101", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8101-客户开户] 账号：" + m8101.getACTNUM() + " 账户名称：" +
                m8101.getACTNAM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
