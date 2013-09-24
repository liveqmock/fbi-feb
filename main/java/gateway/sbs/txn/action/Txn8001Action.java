package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.M8001;
import gateway.sbs.txn.model.msg.M8002;
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
 * Date: 13-9-5
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Txn8001Action extends AbstractTxnAction {
    private static Logger logger = LoggerFactory.getLogger(Txn8001Action.class);
    @Autowired
    private CoreTxnService coreTxnService;
    @Override
    protected List<SOFForm> process(String termid, String tellid, MTia tia) throws Exception {

        M8001 m8001 = (M8001) tia;
        logger.info("账号：" + m8001.getCusidt()+"姓名："+m8001.getCusnam());

        List<String> paramList = new ArrayList<>();
        paramList.add(m8001.getActbdy());
        paramList.add(m8001.getBuscde());
        paramList.add(m8001.getCusnam());
        paramList.add(m8001.getCusidt());
        paramList.add(m8001.getCoradd());
        paramList.add(m8001.getCtxnum());
        paramList.add(m8001.getCuskid());
        paramList.add(m8001.getCusty1());
        paramList.add(m8001.getCusty2());
        paramList.add(m8001.getCusnam());
        paramList.add(m8001.getEngnam());
        paramList.add(m8001.getEntcde());
        paramList.add(m8001.getEnttyp());
        paramList.add(m8001.getIbkcde());
        paramList.add(m8001.getIntnet());
        paramList.add(m8001.getLegbdy());
        paramList.add(m8001.getLtxnum());
        paramList.add(m8001.getOprctr());
        paramList.add(m8001.getPassno());
        paramList.add(m8001.getPastyp());
        paramList.add(m8001.getRelcus());
        paramList.add(m8001.getRsdctr());
        paramList.add(m8001.getReglap());
        paramList.add(m8001.getRegadd());
        paramList.add(m8001.getRskgrp());
        paramList.add(m8001.getRegccy());
        paramList.add(m8001.getSbknum());
        paramList.add(m8001.getShtnam());
        paramList.add(m8001.getSupdep());
        paramList.add(m8001.getTelexn());
        paramList.add(m8001.getTelnum());
        paramList.add(m8001.getTstrnk());
        paramList.add(m8001.getZipcde());
        // 执行sbs交易
        SBSResponse response = coreTxnService.execute(termid, tellid, "8001", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("账号：" + m8001.getCusidt() + " 账户名称：" +
                m8001.getCusnam() + " 返回码：");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
