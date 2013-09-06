package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8101;
import gateway.sbs.txn.model.msg.M8104;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 8104-�ڲ��˻�����
 */
@Component
public class Txn8104Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn8104Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

        M8104 m8104 = (M8104) tia;
        logger.info("[8104-�ͻ�����] �˺ţ�" + m8104.getACTNUM() + " �˻����ƣ�" + m8104.getACTNAM());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8104.getBATSEQ());
        paramList.add(m8104.getORGIDT());
        paramList.add(m8104.getDEPNUM());
        paramList.add(m8104.getACTNUM());
        paramList.add(m8104.getACTNAM());
        paramList.add(m8104.getLEGFMT());
        paramList.add(m8104.getLEGSHT());
        paramList.add(m8104.getLEGDEP());
        paramList.add(m8104.getLEGADD());
        paramList.add(m8104.getLEGZIP());
        paramList.add(m8104.getLEGCYC());
        paramList.add(m8104.getLEGCDT());   // �ֻ��˳�������
        paramList.add(m8104.getACTTYP());
        paramList.add(m8104.getINTFLG());
        paramList.add(m8104.getINTCYC());   // ��Ϣ����
        paramList.add(m8104.getDINRAT());
        paramList.add(m8104.getCINRAT());
        paramList.add(m8104.getDRATSF());
        paramList.add(m8104.getCRATSF());
        paramList.add(m8104.getDEPNU3());
        paramList.add(m8104.getVCHAUT());
        paramList.add(m8104.getLOCCAP());
        paramList.add(m8104.getINTTRA());
        paramList.add(m8104.getTXNAMT());      // ���׽��
        paramList.add(m8104.getGLCODE());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellid, "8104", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[8104-�ͻ�����] �˺ţ�" + m8104.getACTNUM() + " �˻����ƣ�" +
                m8104.getACTNAM() + " �����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
