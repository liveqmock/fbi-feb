package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma821;
import gateway.sbs.txn.model.msg.Ma824;
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
public class Txna824Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna824Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma824 ma824 = (Ma824) tia;
        logger.info("������ࣺ" + ma824.getDPTTYP());
        List<String> paramList = new ArrayList<>();
        paramList.add(ma824.getDPTTYP());
        paramList.add(ma824.getCHFMAK());
        paramList.add(ma824.getAPCODE());
        paramList.add(ma824.getRECSTS());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellerid, "a824", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("������ࣺ" + ma824.getDPTTYP()+ " �����룺");

        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
