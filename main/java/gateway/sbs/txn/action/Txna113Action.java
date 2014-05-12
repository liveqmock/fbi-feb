package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.core.domain.SOFFormBody;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma113;
import gateway.sbs.txn.model.msg.Ma276;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-12
 * Time: 上午11:30
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txna113Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna113Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
        Ma113 ma113 = (Ma113) tia;
        logger.info("[a113-通知存款查询] 帐户号：" + ma113.getBOKNUM());

        List<String> paramList = new ArrayList<>();
        paramList.add(ma113.getBOKNUM());
        paramList.add(ma113.getPAPSTS());
        paramList.add(ma113.getVALDAT());
        paramList.add(ma113.getSGNDAT());
        paramList.add(ma113.getCUSPW1());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "a113", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[a113-通知存款查询] 帐户号：" +
                ma113.getBOKNUM()+" 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
