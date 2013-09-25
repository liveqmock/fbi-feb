package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9861;
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
public class Txn9861Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9861Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9861 m9861 = (M9861) tia;
        logger.info("���������ƣ�" + m9861.getGLCODE() + " �����룺" + m9861.getGLCNAM());
        List<String> paramList = new ArrayList<>();
        paramList.add(m9861.getGLCODE());
        paramList.add(m9861.getGLCNAM());
        paramList.add(m9861.getGLCCLS());
        paramList.add(m9861.getGLCGRP());
        paramList.add(m9861.getGLCTYP());
        paramList.add(m9861.getGLCCAT());   // ��������
        paramList.add(m9861.getGLCCCY());   // ��������
        paramList.add(m9861.getGLCBAL());   // �Ƿ�ǰ����
        paramList.add(m9861.getGLCOCC());   // ��ǰ�޸ı�־
        paramList.add(m9861.getGLCINT());   // ��׼����ֵ
        paramList.add(m9861.getGLCRAT());   // ��������ֵ
        paramList.add(m9861.getGLCOPN());   // ��������ֵ
        paramList.add(m9861.getGLCRVS());   // ������־
        paramList.add(m9861.getRSVFG1());   // �����־
        paramList.add(m9861.getRSVFG2());   // ����
        paramList.add(m9861.getRSVFG3());   // ���޵�λ
        paramList.add(m9861.getRSVFG4());   // �������   0-���ʲ�ѯ,2-�޸�, 3-ɾ��, 4-����
        paramList.add(m9861.getRSVFG5());
        paramList.add(m9861.getGLCBEL());
        paramList.add(m9861.getEFFDAT());
        paramList.add(m9861.getEXPDAT());
        paramList.add(m9861.getAMDTLR());
        paramList.add(m9861.getUPDDAT());
        paramList.add(m9861.getGLCORD());






        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9861", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("���������ƣ�" + m9861.getGLCODE() +  m9861.getGLCNAM() + " �����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
