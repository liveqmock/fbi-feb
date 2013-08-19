package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9804;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 9804-���ʵ��ʲ�ѯ����ɾ��
 */
@Component
public class Txn9804Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9804Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

        M9804 m9804 = (M9804) tia;
        logger.info("[9804-����CURD] �ұ�" + m9804.getCURCDE() +
                " ���ڣ�" + m9804.getEFFDAT() + " �����룺" + m9804.getIRTCDE());
        List<String> paramList = new ArrayList<>();
        paramList.add("111111");
        paramList.add("010");
        paramList.add("60");
        paramList.add(m9804.getCURCDE());
        paramList.add(m9804.getIRTCDE());
        paramList.add(m9804.getEFFDAT());   // ��������
        paramList.add(m9804.getIRTNAM());   // ��������
        paramList.add(m9804.getCURFLG());   // �Ƿ�ǰ����
        paramList.add(m9804.getMODFLG());   // ��ǰ�޸ı�־
        paramList.add(m9804.getIRTVAL());   // ��׼����ֵ
        paramList.add(m9804.getIRTSPH());   // ��������ֵ
        paramList.add(m9804.getIRTSPL());   // ��������ֵ
        paramList.add(m9804.getSPRFLG());   // ������־
        paramList.add(m9804.getCDFLAG());   // �����־
        paramList.add(m9804.getIRTTRM());   // ����
        paramList.add(m9804.getTRMUNT());   // ���޵�λ
        paramList.add(m9804.getFUNCDE());   // �������   0-���ʲ�ѯ,2-�޸�, 3-ɾ��, 4-����
        paramList.add("000001");

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellid, "9804", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9804-����CURD] �ұ�" + m9804.getCURCDE() +
                " ���ڣ�" + m9804.getEFFDAT() + "�����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
