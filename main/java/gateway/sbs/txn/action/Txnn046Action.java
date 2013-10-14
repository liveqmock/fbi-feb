package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn046;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n046-����������ʲ�ѯ
 */
@Component
public class Txnn046Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn046Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn046 mn046 = (Mn046) tia;
        logger.info("[n046-����������ʲ�ѯ] ���ڣ�" + mn046.getORDDAT() +
                " ��ʼ������" + mn046.getBEGNUM() + " ״̬��" + mn046.getPRCSTS());

        List<String> paramList = new ArrayList<>();
        paramList.add(mn046.getORDDAT());
        paramList.add(mn046.getPRCSTS());
        paramList.add(mn046.getBEGNUM());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellid, "n046", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n046-����������ʲ�ѯ] ���ڣ�" + mn046.getORDDAT() +
                " ��ʼ������" + mn046.getBEGNUM() + " ״̬��" + mn046.getPRCSTS() + " �����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
