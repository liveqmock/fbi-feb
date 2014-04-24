package gateway.sbs.txn.action;

import gateway.sbs.core.SBSRequest;
import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma000;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-4-8
 * Time: 上午9:00
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txna000Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna000Action.class);
    @Autowired
    private CoreTxnService coreTxnService ;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma000 ma000 = new Ma000();
        logger.info("冲正流水号：" + ma000.getRVSKEY());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma000.getRVSKEY());
        paramList.add(ma000.getRVSTCD());
        paramList.add(ma000.getACTTY1());
        paramList.add(ma000.getIPTAC1());
        paramList.add(ma000.getACTTY2());
        paramList.add(ma000.getIPTAC2());
        paramList.add(ma000.getTXNAMT());
        paramList.add(ma000.getREMARK());
        paramList.add(ma000.getMAGFL1());
        paramList.add(ma000.getMAGFL2());

        SBSResponse response = coreTxnService.execute(termid, tellerid,"a000", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("冲正流水号：" + ma000.getRVSKEY());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
