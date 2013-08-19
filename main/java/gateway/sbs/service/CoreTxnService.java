package gateway.sbs.service;

import gateway.sbs.core.CtgManager;
import gateway.sbs.core.SBSRequest;
import gateway.sbs.core.SBSResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SBS���Ľ���ִ��
 */
@Service
public class CoreTxnService {
    private static Logger logger = LoggerFactory.getLogger(CoreTxnService.class);

    public SBSResponse execute(String termid, String tellerid, String txnCode, List<String> paramList) {
        CtgManager ctgManager = new CtgManager();
        SBSRequest sbsRequest = new SBSRequest(txnCode, paramList);
        SBSResponse sbsResponse = new SBSResponse();
        ctgManager.processSingleResponsePkg(termid, tellerid, sbsRequest, sbsResponse);
        return sbsResponse;
    }
}
