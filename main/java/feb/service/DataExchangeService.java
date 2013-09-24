package feb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.SbsTxnService;
import gateway.sbs.txn.model.msg.MTia;
import skyline.service.SkylineService;

import java.util.List;

import static skyline.service.SkylineService.*;

/**
 * 对外交易发起
 */
@Service
public class DataExchangeService {

    private static Logger logger = LoggerFactory.getLogger(DataExchangeService.class);
    @Autowired
    private SbsTxnService sbsTxnService;

    // SBS交易执行点
    public List<SOFForm> callSbsTxn(String txnCode, MTia tia) {
        String tellerid = getOperId();
        String termid = tellerid;
        return sbsTxnService.execute(termid, tellerid, txnCode, tia);
    }
}
