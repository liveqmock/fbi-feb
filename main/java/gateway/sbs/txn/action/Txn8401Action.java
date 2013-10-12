package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8401;
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
 * Date: 13-9-10
 * Time: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8401Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8401Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8401 m8401 = (M8401) tia;
        logger.info("[8401-查询] 传票号：" + m8401.getVCHSET());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8401.getBATSEQ());
        paramList.add(m8401.getORGIDT());
        paramList.add(m8401.getDEPNUM());
        paramList.add(m8401.getORGID3());
        paramList.add(m8401.getTLRNUM());
        paramList.add(m8401.getVCHSET());
        paramList.add(m8401.getSETSEQ());
        paramList.add(m8401.getACTNUM());
        paramList.add(m8401.getTXNAMT());
        paramList.add(m8401.getRVSLBL());
        paramList.add(m8401.getOPNDA2());
        paramList.add(m8401.getFURINF());
        paramList.add(m8401.getPRDCDE());
        paramList.add(m8401.getDEPNU3());
        paramList.add(m8401.getANACDE());
        paramList.add(m8401.getFXRATE());
        paramList.add(m8401.getSECCCY());
        paramList.add(m8401.getSECAMT());
        paramList.add(m8401.getVCHAUT());
        paramList.add(m8401.getOUFCRE());
        paramList.add(m8401.getERYTYP());
        paramList.add(m8401.getERYDAT());
        paramList.add(m8401.getFUNCDE());
        paramList.add(m8401.getCORAPC());
        paramList.add(m8401.getREGNUM());


        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "8401", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8401-查询] 传票号：" + m8401.getVCHSET()+ " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
