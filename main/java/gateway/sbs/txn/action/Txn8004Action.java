package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8002;
import gateway.sbs.txn.model.msg.M8004;
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
 * Date: 13-9-12                   // 客户信息查询
 * Time: 下午9:51
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8004Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8004Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8004 m8004 = (M8004) tia;
        logger.info("[8004-客户查询] 客户：" + m8004.getCUSNAM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8004.getBATSEQ());
        paramList.add(m8004.getORGIDT());
        paramList.add(m8004.getDEPNUM());
        paramList.add(m8004.getCUSIDT());
        paramList.add(m8004.getCUSNAM());
        paramList.add(m8004.getENGNAM());
        paramList.add(m8004.getSHTNAM());
        paramList.add(m8004.getCUSIDX());
        paramList.add(m8004.getRSDCTR());
        paramList.add(m8004.getOPRCTR());
        paramList.add(m8004.getCORADD());
        paramList.add(m8004.getZIPCDE());
        paramList.add(m8004.getTELNUM());
        paramList.add(m8004.getTELEXN());
        paramList.add(m8004.getPASTYP());
        paramList.add(m8004.getPASSNO());
        paramList.add(m8004.getTSTRNK());
        paramList.add(m8004.getCRDLIM());
        paramList.add(m8004.getRSKGRP());
        paramList.add(m8004.getRELCUS());
        paramList.add(m8004.getCUSPWD());
        paramList.add(m8004.getCUSKID());
        paramList.add(m8004.getLEGBDY());
        paramList.add(m8004.getACTBDY());
        paramList.add(m8004.getLOCCAP());
        paramList.add(m8004.getREGCAP());
        paramList.add(m8004.getREGCCY());
        paramList.add(m8004.getREGADD());
        paramList.add(m8004.getREGDAT());
        paramList.add(m8004.getEFFDUR());
        paramList.add(m8004.getCTXNUM());
        paramList.add(m8004.getLTXNUM());
        paramList.add(m8004.getBOCGRP());
        paramList.add(m8004.getSUPDEP());
        paramList.add(m8004.getBUSCDE());
        paramList.add(m8004.getENTTYP());
        paramList.add(m8004.getCUSTY1());
        paramList.add(m8004.getCUSTY2());
        paramList.add(m8004.getINTNET());
        paramList.add(m8004.getENTCDE());
        paramList.add(m8004.getIBKCDE());
        paramList.add(m8004.getSBKNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8004", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8004-客户查询] 客户：" + m8004.getCUSNAM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
