package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M0003;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma841;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/7/24       总分账号对照查询
 * Time: 9:32
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txna841Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna841Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma841 ma841 = (Ma841) tia;
        logger.info("[ma841-总分账号对照查询] 代码类别：" + ma841.getDPTTYP());

        List<String> paramList = new ArrayList<>();
        paramList.add(ma841.getDPTTYP());
        paramList.add(ma841.getBEGNUM());
        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "a841", paramList);

        logger.info(response.getFormCodes().toString());
        return response.getSofForms();
    }
}
