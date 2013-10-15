package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn045;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n045-������������
 */
@Component
public class Txnn045Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn045Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn045 mn045 = (Mn045) tia;
        logger.info("[n045-������������] ������" + mn045.getFBTIDX());

        List<String> paramList = new ArrayList<>();
        paramList.add(mn045.getFBTIDX());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellid, "n045", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n045-������������] ��������" + mn045.getFBTIDX() + " �����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
