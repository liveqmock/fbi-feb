package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9804;
import gateway.sbs.txn.model.msg.M9814;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-9-3
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9814Action extends AbstractTxnAction{
    private static Logger logger = LoggerFactory.getLogger(Txn9814Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {
        M9814 m9814 = (M9814) tia;
        logger.info("[9814-查询] 总账码：" + m9814.getGlcode() + " 核算码："
                + m9814.getApcode()+"核算码名称"+m9814.getApcnam()+"核算码类别"+m9814.getApctyp());
        List<String> paramList = new ArrayList<>();
       /* paramList.add("111111");
        paramList.add("010");
        paramList.add("60");*/

        paramList.add(m9814.getApcdcr());
        paramList.add(m9814.getApckid());
        paramList.add(m9814.getApcnam());
        paramList.add(m9814.getApcode());
        paramList.add(m9814.getApctyp());
        paramList.add(m9814.getApcdcr());
        paramList.add(m9814.getGlcode());
        paramList.add(m9814.getIntcac());
        paramList.add(m9814.getIntdac());
        paramList.add(m9814.getIntinc());
        paramList.add(m9814.getIntexp());
        paramList.add(m9814.getNcrint());
        paramList.add(m9814.getNdrint());
        paramList.add(m9814.getPlcode());
       paramList.add(m9814.getFUNCDE());
        paramList.add(m9814.getOpsapc());
        paramList.add(m9814.getMODFLG());
        //paramList.add("000001");

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "9814", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9814-查询] 总账码：" + m9814.getGlcode() + " 核算码："
                + m9814.getApcode()+"核算码名称"+m9814.getApcnam()+"核算码类别"+m9814.getApctyp());
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
