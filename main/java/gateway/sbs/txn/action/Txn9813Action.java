package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9813;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 9816-总账码单笔查询、增删改
 */
@Component
public class Txn9813Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9813Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

        M9813 m9813 = (M9813) tia;
        logger.info("总账码名称：" + m9813.getGLCODE() + " 总账码：" + m9813.getEFFDAT());
        List<String> paramList = new ArrayList<>();
        paramList.add(m9813.getBATSEQ());
        paramList.add(m9813.getORGIDT());
        paramList.add(m9813.getDEPNUM());
        paramList.add(m9813.getGLCODE());
        paramList.add(m9813.getGLCNAM());
        paramList.add(m9813.getGLCCLS());
        paramList.add(m9813.getGLCGRP());
        paramList.add(m9813.getGLCTYP());
        paramList.add(m9813.getGLCCAT());
        paramList.add(m9813.getGLCCCY());
        paramList.add(m9813.getGLCBAL());
        paramList.add(m9813.getGLCOCC());
        paramList.add(m9813.getGLCINT());
        paramList.add(m9813.getGLCRAT());
        paramList.add(m9813.getGLCOPN());
        paramList.add(m9813.getGLCRVS());
        paramList.add(m9813.getRSVFG1());
        paramList.add(m9813.getRSVFG2());
        paramList.add(m9813.getRSVFG3());
        paramList.add(m9813.getRSVFG4());
        paramList.add(m9813.getRSVFG5());
        paramList.add(m9813.getGLCBEL());
        paramList.add(m9813.getEFFDAT());
        paramList.add(m9813.getEXPDAT());
        paramList.add(m9813.getFUNCDE());
        paramList.add(m9813.getBEGNUM());
        paramList.add(m9813.getRSVRF3());



        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "9813", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("总账码名称：" + m9813.getGLCODE() +  m9813.getEFFDAT() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
