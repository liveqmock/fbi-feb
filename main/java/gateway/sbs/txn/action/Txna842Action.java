package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Ma841;
import gateway.sbs.txn.model.msg.Ma842;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 2014/7/24       总分账号对照维护
 * Time: 9:32
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txna842Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txna842Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Ma842 ma842 = (Ma842) tia;
        logger.info("[ma842-总分账号对照] 代码类别：" + ma842.getACTTY1());

        List<String> paramList = new ArrayList<>();
        paramList.add(ma842.getPASTYP());
        paramList.add(ma842.getACTTY1());
        paramList.add(ma842.getIPTAC1());
        paramList.add(ma842.getACTNM1());
        paramList.add(ma842.getACTNM2());
        paramList.add(ma842.getREASON());
        paramList.add(ma842.getREMARK());
        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "a842", paramList);

        logger.info(response.getFormCodes().toString());
        return response.getSofForms();
    }
}
