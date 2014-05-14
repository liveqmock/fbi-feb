package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9a01;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-5-13  币别码
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9a01Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9a01Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9a01 m9a01 = (M9a01) tia;
        logger.info("[9a01-查询] 币别码：" + m9a01.getCURCDE());

        List<String> paramList = new ArrayList<>();
        paramList.add(m9a01.getBATSEQ());
        paramList.add(m9a01.getORGIDT());
        paramList.add(m9a01.getDEPNUM());
        paramList.add(m9a01.getCURCDE());
        paramList.add(m9a01.getCURNMC());
        paramList.add(m9a01.getCURNME());
        paramList.add(m9a01.getDOMCDE());
        paramList.add(m9a01.getINTCUR());
        paramList.add(m9a01.getCURUNT());
        paramList.add(m9a01.getDECPOS());
        paramList.add(m9a01.getEXCFLG());
        paramList.add(m9a01.getEXCUNT());
        paramList.add(m9a01.getMINUNT());
        paramList.add(m9a01.getCUTFLG());
        paramList.add(m9a01.getSPEFLG());
        paramList.add(m9a01.getHOLFLG());
        paramList.add(m9a01.getCLRFLG());
        paramList.add(m9a01.getEFFDAT());
        paramList.add(m9a01.getEXPDAT());
        paramList.add(m9a01.getFUNCDE());
        paramList.add(m9a01.getBEGNUM());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9a01", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9a01-查询] 币别码：" + m9a01.getCURCDE()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
