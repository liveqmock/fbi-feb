package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M7280;
import gateway.sbs.txn.model.msg.M9a01;
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
 * Date: 14-5-13  冻结解冻
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn7280Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn7280Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M7280 m7280 = (M7280) tia;
        logger.info("[m7280-冻结解冻] 账号：" + m7280.getACTNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m7280.getBATSEQ());
        paramList.add(m7280.getORGIDT());
        paramList.add(m7280.getDEPNUM());
        paramList.add(m7280.getPROMOD());
        paramList.add(m7280.getACTNUM());
        paramList.add(m7280.getTXNAMT());
        paramList.add(m7280.getFRZMOD());
        paramList.add(m7280.getFRZFLG());
        paramList.add(m7280.getFRZRSN());
        paramList.add(m7280.getFRZDAT());
        paramList.add(m7280.getFRZEDT());
        paramList.add(m7280.getPRDSEQ());


        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "7280", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[m7280-冻结解冻] 账号：" + m7280.getACTNUM()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
