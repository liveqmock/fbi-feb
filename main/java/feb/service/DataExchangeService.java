package feb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.SbsTxnService;
import gateway.sbs.txn.model.msg.MTia;

import java.util.List;

/**
 * ���⽻�׷���
 */
@Service
public class DataExchangeService {

    private static Logger logger = LoggerFactory.getLogger(DataExchangeService.class);
    @Autowired
    private SbsTxnService sbsTxnService;

    // SBS����ִ�е�
    public List<SOFForm> callSbsTxn(String txnCode, MTia tia) {
        return sbsTxnService.execute(txnCode, tia);
    }
}
