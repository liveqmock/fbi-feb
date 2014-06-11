package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma823;
import gateway.sbs.txn.model.msg.Ma825;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ��������ά��-��ʲ�ѯ
 */
@Component
public class Txna823Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna823Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma823 ma823 = (Ma823) tia;
        logger.info("������ࣺ" + ma823.getDPTTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma823.getDPTTYP());
        paramList.add(ma823.getCHFMAK());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellerid, "a823", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("������ࣺ" + ma823.getDPTTYP()+ " �����룺");

        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
