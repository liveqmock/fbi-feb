package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8823;
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
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8823Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8823Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        M8823 m8823 = (M8823)tia;
        logger.info("传票号：" + m8823.getVCHSET());
        List<String> paramList = new ArrayList<>();
        paramList.add(m8823.getCUSIDT());
        paramList.add(m8823.getAPCODE());
        paramList.add(m8823.getCURCDE());
        paramList.add(m8823.getERYDA1());
        paramList.add(m8823.getERYDA2());
        paramList.add(m8823.getSECAMT());
        paramList.add(m8823.getOVELIM());
        paramList.add(m8823.getTLRNUM());
        paramList.add(m8823.getVCHSET());
        paramList.add(m8823.getFUNCDE());
        paramList.add(m8823.getREGADD());
        paramList.add(m8823.getBEGNUM());
        paramList.add(m8823.getANACDE());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8823", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("传票号：" + m8823.getVCHSET() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
