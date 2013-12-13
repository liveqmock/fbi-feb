package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8822;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-20
 * Time: 下午2:38
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8822Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8822Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M8822 m8822 = (M8822)tia;
        logger.info("传票号：" + m8822.getVCHSET());
        List<String> paramList = new ArrayList<>();
        paramList.add(m8822.getCUSIDT());
        paramList.add(m8822.getAPCODE());
        paramList.add(m8822.getCURCDE());
        paramList.add(m8822.getSECAMT());
        paramList.add(m8822.getOVELIM());
        paramList.add(m8822.getTLRNUM());
        paramList.add(m8822.getVCHSET());
        paramList.add(m8822.getFUNCDE());
        paramList.add(m8822.getREGADD());
        paramList.add(m8822.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8822", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("传票号：" + m8822.getVCHSET() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
