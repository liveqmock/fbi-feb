package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9804;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 9804-利率单笔查询、增删改
 */
@Component
public class Txn9804Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9804Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

        M9804 m9804 = (M9804) tia;
        logger.info("[9804-利率CURD] 币别：" + m9804.getCURCDE() +
                " 日期：" + m9804.getEFFDAT() + " 利率码：" + m9804.getIRTCDE());
        List<String> paramList = new ArrayList<>();
        paramList.add("111111");
        paramList.add("010");
        paramList.add("60");
        paramList.add(m9804.getCURCDE());
        paramList.add(m9804.getIRTCDE());
        paramList.add(m9804.getEFFDAT());   // 启用日期
        paramList.add(m9804.getIRTNAM());   // 利率名称
        paramList.add(m9804.getCURFLG());   // 是否当前利率
        paramList.add(m9804.getMODFLG());   // 当前修改标志
        paramList.add(m9804.getIRTVAL());   // 基准利率值
        paramList.add(m9804.getIRTSPH());   // 浮动上限值
        paramList.add(m9804.getIRTSPL());   // 浮动下限值
        paramList.add(m9804.getSPRFLG());   // 浮动标志
        paramList.add(m9804.getCDFLAG());   // 借贷标志
        paramList.add(m9804.getIRTTRM());   // 期限
        paramList.add(m9804.getTRMUNT());   // 期限单位
        paramList.add(m9804.getFUNCDE());   // 操作类别   0-单笔查询,2-修改, 3-删除, 4-增加
        paramList.add("000001");

        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "9804", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[9804-利率CURD] 币别：" + m9804.getCURCDE() +
                " 日期：" + m9804.getEFFDAT() + "返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
