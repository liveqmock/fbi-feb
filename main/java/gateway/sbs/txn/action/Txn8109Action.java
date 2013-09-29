package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8109;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 8109-�رտͻ��˻�ǰ���˻���ѯ
 */
@Component
public class Txn8109Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8109Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        M8109 m8109 = (M8109) tia;
        logger.info("[8109-�رտͻ��˻�ǰ���˻���ѯ] �˺ţ�" + m8109.getACTNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8109.getBATSEQ());
        paramList.add(m8109.getORGIDT());
        paramList.add(m8109.getDEPNUM());
        paramList.add(m8109.getORGID3());
        paramList.add(m8109.getACTNUM());
        paramList.add(m8109.getVCHAUT());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellid, auttlr, autpwd, "8109", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8109-�رտͻ��˻�ǰ���˻���ѯ] �˺ţ�" + m8109.getACTNUM() + " �����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
