package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M5832;
import gateway.sbs.txn.model.msg.M8848;
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
 * Date: 14-5-14
 * Time: ÏÂÎç2:23
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8848Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8848Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8848 m8848 = (M8848) tia;

        List<String> paramList = new ArrayList<>();
        paramList.add(m8848.getBATSEQ());
        paramList.add(m8848.getORGIDT());
        paramList.add(m8848.getDEPNUM());
        paramList.add(m8848.getPASTYP());
        paramList.add(m8848.getINPFLG());
        paramList.add(m8848.getSBKNUM());
        paramList.add(m8848.getWRKUNT());
        paramList.add(m8848.getSTMADD());
        paramList.add(m8848.getINTNET());
        paramList.add(m8848.getENGNAM());
        paramList.add(m8848.getREGADD());
        paramList.add(m8848.getCORADD());
        paramList.add(m8848.getCUSNAM());
        paramList.add(m8848.getFUNCDE());
        paramList.add(m8848.getBEGNUM());

        SBSResponse response = coreTxnService.execute(termid, tellerid, "8848", paramList);

        StringBuffer rtnFormCodes = new StringBuffer( " ·µ»ØÂë£º");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
