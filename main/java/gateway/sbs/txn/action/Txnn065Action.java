package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn065;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n065-�Թ�֧�������Ѳ�ѯ
 */
@Component
public class Txnn065Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn065Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn065 mn065 = (Mn065) tia;
        logger.info("[n065-�Թ�֧�������Ѳ�ѯ] ��ѯ���£�" + mn065.getFBACCT() +
                " ���(0-δ�ɷ�,1-�ѽɷ�,2-ȫ��)��" + mn065.getFUNCDE() +
                " ��ʼ������" + mn065.getBEGNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(mn065.getFBACCT());
        paramList.add(mn065.getFUNCDE());
        paramList.add(mn065.getBEGNUM());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellid, "n065", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n065-�Թ�֧�������Ѳ�ѯ] ��ѯ���£�" + mn065.getFBACCT() +
                " ���(0-δ�ɷ�,1-�ѽɷ�,2-ȫ��)��" + mn065.getFUNCDE() +
                " ��ʼ������" + mn065.getBEGNUM() +
                " �����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
