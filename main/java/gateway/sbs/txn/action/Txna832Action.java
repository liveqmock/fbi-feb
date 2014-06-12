package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma832;
import gateway.sbs.txn.model.msg.Ma835;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ����������������Ӧ��ά��-ɾ��
 */
@Component
public class Txna832Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna832Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma832 ma832= (Ma832) tia;
        logger.info("������ࣺ" + ma832.getDPTTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma832.getDPTTYP());
        paramList.add(ma832.getCURCDE());
        paramList.add(ma832.getACTTYP());
        paramList.add(ma832.getPRDTYP());
        paramList.add(ma832.getDPTPRD());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellerid, "a832", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("������ࣺ" + ma832.getDPTTYP()+ " �����룺");

        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
