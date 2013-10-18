package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mh802;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * H803��Ҫƾ֤����ά��ɾ��
 */
@Component
public class Txnh802Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnh802Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mh802 mh802 = (Mh802) tia;
        logger.info("ƾ֤��" + mh802.getVCHTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(mh802.getVCHTYP());






        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellerid, "h802", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("ƾ֤��" + mh802.getVCHTYP());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
