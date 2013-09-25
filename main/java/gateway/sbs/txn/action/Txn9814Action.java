package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
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
 * Date: 13-9-10
 * Time: ÏÂÎç10:46
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn9814Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txn9814Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
<<<<<<< HEAD
    public List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

=======
    protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception {
>>>>>>> c066d83120f0508b72cc3b4ffc5bf648dbd8d067
        M9814 m9814 = (M9814) tia;
        logger.info("×ÜÕËÂë£º" + m9814.getGLCODE() +
                              " ºËËãÂë£º" + m9814.getAPCODE());
        List<String> paramList = new ArrayList<>();
      /*  paramList.add(m9814.getBATSEQ());
        paramList.add(m9814.get());
        paramList.add("60");*/
        paramList.add(m9814.getBATSEQ());
        paramList.add(m9814.getORGIDT());
        paramList.add(m9814.getDEPNUM());
        paramList.add(m9814.getAPCODE());
        paramList.add(m9814.getAPCNAM());
        paramList.add(m9814.getAPCTYP());
        paramList.add(m9814.getGLCODE());
        paramList.add(m9814.getPLCODE());
        paramList.add(m9814.getINTEXP());
        paramList.add(m9814.getINTINC());
        paramList.add(m9814.getTRNIDT());
        paramList.add(m9814.getFEEAPC());
        paramList.add(m9814.getPDCTYP());
        paramList.add(m9814.getEBKCDE());
        paramList.add(m9814.getOPSAPC());
        paramList.add(m9814.getAPCDCR());
        paramList.add(m9814.getINTDAC());
        paramList.add(m9814.getINTCAC());
        paramList.add(m9814.getFUNCDE());
        paramList.add(m9814.getDEGNUM());
        paramList.add(m9814.getCLRFLG());
        paramList.add(m9814.getTLRNCDE());

        // Ö´ÐÐsbs½»Ò×
        SBSResponse response = coreTxnService.execute(termid, tellerid, "9814", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("×ÜÕËÂë£º" + m9814.getGLCODE() +
                                    " ºËËãÂë£º" + m9814.getAPCODE()+ "·µ»ØÂë£º");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
