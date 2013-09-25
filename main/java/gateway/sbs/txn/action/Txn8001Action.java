package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8001;
import gateway.sbs.txn.model.msg.M8002;
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
 * Date: 13-9-12                   // 客户信息创建
 * Time: 下午9:51
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8001Action extends AbstractTxnAction{

    private static Logger logger = LoggerFactory.getLogger(Txn8001Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
<<<<<<< HEAD
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {
=======
    protected List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {
>>>>>>> c066d83120f0508b72cc3b4ffc5bf648dbd8d067

        M8001 m8001 = (M8001) tia;
        logger.info("[8001-客户创建] 客户名：" + m8001.getCUSNAM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8001.getBATSEQ());
        paramList.add(m8001.getORGIDT());
        paramList.add(m8001.getDEPNUM());
        paramList.add(m8001.getCUSNAM());
        paramList.add(m8001.getENGNAM());
        paramList.add(m8001.getSHTNAM());
        paramList.add(m8001.getCUSIDX());
        paramList.add(m8001.getRSDCTR());
        paramList.add(m8001.getOPRCTR());
        paramList.add(m8001.getCORADD());
        paramList.add(m8001.getZIPCDE());
        paramList.add(m8001.getTELNUM());
        paramList.add(m8001.getTELEXN());
        paramList.add(m8001.getPASTYP());
        paramList.add(m8001.getPASSNO());
        paramList.add(m8001.getTSTRNK());
        paramList.add(m8001.getCRDLIM());
        paramList.add(m8001.getRSKGRP());
        paramList.add(m8001.getRELCUS());
        paramList.add(m8001.getCUSPWD());
        paramList.add(m8001.getCUSKID());
        paramList.add(m8001.getDEPNU3());
        paramList.add(m8001.getLEGBDY());
        paramList.add(m8001.getACTBDY());
        paramList.add(m8001.getLOCCAP());
        paramList.add(m8001.getREGCAP());
        paramList.add(m8001.getREGCCY());
        paramList.add(m8001.getREGADD());
        paramList.add(m8001.getREGDAT());
        paramList.add(m8001.getEFFDUR());
        paramList.add(m8001.getCTXNUM());
        paramList.add(m8001.getLTXNUM());
        paramList.add(m8001.getBOCGRP());
        paramList.add(m8001.getSUPDEP());
        paramList.add(m8001.getBUSCDE());
        paramList.add(m8001.getENTTYP());
        paramList.add(m8001.getCUSTY1());
        paramList.add(m8001.getCUSTY2());
        paramList.add(m8001.getINTNET());
        paramList.add(m8001.getENTCDE());
        paramList.add(m8001.getIBKCDE());
        paramList.add(m8001.getSBKNUM());
        paramList.add(m8001.getFUNCDE());
        paramList.add(m8001.getSYSIDT());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "8001", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8001-客户创建] 客户名：" + m8001.getCUSNAM()+ " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
