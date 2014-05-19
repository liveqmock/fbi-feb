package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9a24;
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
 * Date: 14-5-19
 * Time: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9a24Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn9a24Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9a24 m9a24 = (M9a24)tia;
        logger.info("[9a24-查询] 自动转账码：" + m9a24.getATRCDE());
        List<String> paramList = new ArrayList<>();
        paramList.add(m9a24.getBATSEQ());
        paramList.add(m9a24.getORGIDT());
        paramList.add(m9a24.getDEPNUM());
        paramList.add(m9a24.getATRCDE());
        paramList.add(m9a24.getATRNAM());
        paramList.add(m9a24.getCYCCDE());
        paramList.add(m9a24.getCYCDAT());
        paramList.add(m9a24.getTRICDE());
        paramList.add(m9a24.getTRIBAL());
        paramList.add(m9a24.getFEXCDE());
        paramList.add(m9a24.getBASRAT());
        paramList.add(m9a24.getBASRT2());
        paramList.add(m9a24.getBASRT3());
        paramList.add(m9a24.getBASRT4());
        paramList.add(m9a24.getBASRT5());
        paramList.add(m9a24.getCURRAG());
        paramList.add(m9a24.getCUSIDT());
        paramList.add(m9a24.getAPCODE());
        paramList.add(m9a24.getCURCDE());
        paramList.add(m9a24.getPRDCDE());
        paramList.add(m9a24.getEVTCDE());
        paramList.add(m9a24.getCTPCDE());
        paramList.add(m9a24.getCATCDE());
        paramList.add(m9a24.getSPECD1());
        paramList.add(m9a24.getSPECD2());
        paramList.add(m9a24.getSPECD3());
        paramList.add(m9a24.getPROFLG());
        paramList.add(m9a24.getATRDCR());
        paramList.add(m9a24.getFUNCDE());
        paramList.add(m9a24.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9a24", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9a24-查询] 自动转账码：" + m9a24.getATRCDE()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
