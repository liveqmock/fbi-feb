package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M9861;
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
public class Txn9861Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9861Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {

        M9861 m9861 = (M9861) tia;
        logger.info("总账码名称：" + m9861.getGLCODE() + " 总账码：" + m9861.getGLCNAM());
        List<String> paramList = new ArrayList<>();
        paramList.add(m9861.getGLCODE());
        paramList.add(m9861.getGLCNAM());
        paramList.add(m9861.getGLCCLS());
        paramList.add(m9861.getGLCGRP());
        paramList.add(m9861.getGLCTYP());
        paramList.add(m9861.getGLCCAT());   // 启用日期
        paramList.add(m9861.getGLCCCY());   // 利率名称
        paramList.add(m9861.getGLCBAL());   // 是否当前利率
        paramList.add(m9861.getGLCOCC());   // 当前修改标志
        paramList.add(m9861.getGLCINT());   // 基准利率值
        paramList.add(m9861.getGLCRAT());   // 浮动上限值
        paramList.add(m9861.getGLCOPN());   // 浮动下限值
        paramList.add(m9861.getGLCRVS());   // 浮动标志
        paramList.add(m9861.getRSVFG1());   // 借贷标志
        paramList.add(m9861.getRSVFG2());   // 期限
        paramList.add(m9861.getRSVFG3());   // 期限单位
        paramList.add(m9861.getRSVFG4());   // 操作类别   0-单笔查询,2-修改, 3-删除, 4-增加
        paramList.add(m9861.getRSVFG5());
        paramList.add(m9861.getGLCBEL());
        paramList.add(m9861.getEFFDAT());
        paramList.add(m9861.getEXPDAT());
        paramList.add(m9861.getAMDTLR());
        paramList.add(m9861.getUPDDAT());
        paramList.add(m9861.getGLCORD());






        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9861", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("总账码名称：" + m9861.getGLCODE() +  m9861.getGLCNAM() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
