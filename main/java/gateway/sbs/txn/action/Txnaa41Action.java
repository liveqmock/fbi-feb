package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9a01;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Maa41;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class Txnaa41Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnaa41Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        Maa41 maa41 = (Maa41) tia;
        logger.info("[aa41-内转交易] 转出账号：" + maa41.getIPTAC1()+"转入账号"+maa41.getIPTAC2());

        List<String> paramList = new ArrayList<>();

        paramList.add(maa41.getACTTY1());
        paramList.add(maa41.getIPTAC1());
        paramList.add(maa41.getDRAMD1());
        paramList.add(maa41.getACTNM1());
        paramList.add(maa41.getCUSPW1());
        paramList.add(maa41.getPASTYP());
        paramList.add(maa41.getPASSNO());
        paramList.add(maa41.getPAPTYP());
        paramList.add(maa41.getPAPCDE());
        paramList.add(maa41.getPAPMAC());
        paramList.add(maa41.getSGNDAT());
        paramList.add(maa41.getNBKFL1());
        paramList.add(maa41.getAUTSEQ());
        paramList.add(maa41.getAUTDAT());
        paramList.add(maa41.getTXNAMT());
        paramList.add(maa41.getACTTY2());
        paramList.add(maa41.getIPTAC2());
        paramList.add(maa41.getACTNM2());
        paramList.add(maa41.getNBKFL2());
        paramList.add(maa41.getTXNDAT());
        paramList.add(maa41.getREMARK());
        paramList.add(maa41.getANACDE());
        paramList.add(maa41.getMAGFL1());
        paramList.add(maa41.getMAGFL2());

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "aa41", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[aa41-内转交易] 转出账号：" + maa41.getIPTAC1()+"转入账号"+maa41.getIPTAC2()
                + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
