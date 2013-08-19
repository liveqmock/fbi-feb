package gateway.sbs.txn.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.msg.MTia;

import java.util.List;

public abstract class AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(AbstractTxnAction.class);

    @Transactional
    public List<SOFForm> run(MTia tia) {
        try {
            return process(tia);
        } catch (Exception e) {
            logger.error("交易异常", e);
            throw new RuntimeException(e.getMessage() == null ? "交易异常." : e.getMessage());
        }
    }

    abstract protected List<SOFForm> process(MTia tia) throws Exception;
}
