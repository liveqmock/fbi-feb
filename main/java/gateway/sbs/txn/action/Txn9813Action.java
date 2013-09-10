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
 * 9804-总账码单笔查询、增删改
 */
@Component
public class Txn9813Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9813Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

        M9813 m9813 = (M9813) tia;
        logger.info("总账码名称：" + m9813.getGLCODE() + " 总账码：" + m9813.getGLCNAM());
        List<String> paramList = new ArrayList<>();
        paramList.add(m9813.getGLCNAM());
        paramList.add(m9813.getGLCODE());


        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "9804", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("总账码名称：" + m9813.getGLCODE() +  m9813.getGLCNAM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
