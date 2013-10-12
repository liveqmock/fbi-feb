package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8104;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn036;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n036-�Թ�֧����ʲ�ѯ
 */
@Component
public class Txnn036Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn036Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn036 mn036 = (Mn036) tia;
        logger.info("[n036-�Թ�֧����ʲ�ѯ] ���ڣ�" + mn036.getORDDAT() +
                " ��ʼ������" + mn036.getBEGNUM() + " ״̬��" + mn036.getPRCSTS());

        List<String> paramList = new ArrayList<>();
        paramList.add(mn036.getORDDAT());
        paramList.add(mn036.getPRCSTS());
        paramList.add(mn036.getBEGNUM());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellid, "n036", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n036-�Թ�֧����ʲ�ѯ] ���ڣ�" + mn036.getORDDAT() +
                " ��ʼ������" + mn036.getBEGNUM() + " ״̬��" + mn036.getPRCSTS() + " �����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
