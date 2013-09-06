package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8102;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 8102-�ͻ��˻��޸�
 */
@Component
public class Txn8102Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8102Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

        M8102 m8102 = (M8102) tia;
        logger.info("[8102-�ͻ��˻��޸�] �˺ţ�" + m8102.getACTNUM() + " �˻����ƣ�" + m8102.getACTNAM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8102.getBATSEQ());
        paramList.add(m8102.getORGIDT());
        paramList.add(m8102.getDEPNUM());
        paramList.add(m8102.getACTNUM());
        paramList.add(m8102.getACTNAM());
        paramList.add(m8102.getSTMFMT());
        paramList.add(m8102.getSTMSHT());
        paramList.add(m8102.getSTMDEP());
        paramList.add(m8102.getSTMADD());   // ���˵���ַ
        paramList.add(m8102.getSTMZIP());
        paramList.add(m8102.getSTMCYC());
        paramList.add(m8102.getSTMCDT());
        paramList.add(m8102.getACTTYP());
        paramList.add(m8102.getINTFLG());
        paramList.add(m8102.getINTCYC());   // ��Ϣ����

        paramList.add(m8102.getINTTRA());
        paramList.add(m8102.getCQEFLG());
        paramList.add(m8102.getBALLIM());
        paramList.add(m8102.getOVELIM());
        paramList.add(m8102.getOVEEXP());     // ��ȵ�����

        paramList.add(m8102.getDINRAT());
        paramList.add(m8102.getCINRAT());
        paramList.add(m8102.getDRATSF());
        paramList.add(m8102.getCRATSF());
        paramList.add(m8102.getVCHAUT());
        paramList.add(m8102.getLEGCYC());
        paramList.add(m8102.getLEGCDT());   // �ֻ��˳�������

        paramList.add(m8102.getLEGFMT());
        paramList.add(m8102.getLEGADD());
        paramList.add(m8102.getLEGZIP());
        paramList.add(m8102.getLEGSHT());
        paramList.add(m8102.getLEGDEP());
        paramList.add(m8102.getDEPNU3());

        paramList.add(m8102.getFXRATE());
        paramList.add(m8102.getLOCCAP());
        paramList.add(m8102.getTXNAMT());      // ���׽��
        paramList.add(m8102.getGLCODE());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellid, "8102", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8102-�ͻ��˻��޸�] �˺ţ�" + m8102.getACTNUM() + " �˻����ƣ�" +
                m8102.getACTNAM() + " �����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
