package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma821;
import gateway.sbs.txn.model.msg.Ma835;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ����������������Ӧ��ά��-��ʲ�ѯ
 */
@Component
public class Txna835Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna835Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma835 ma835= (Ma835) tia;
        logger.info("������ࣺ" + ma835.getDPTTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma835.getDPTTYP());
        paramList.add(ma835.getCURCDE());
        paramList.add(ma835.getACTTYP());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellerid, "a835", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("������ࣺ" + ma835.getDPTTYP()+ " �����룺");

        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
