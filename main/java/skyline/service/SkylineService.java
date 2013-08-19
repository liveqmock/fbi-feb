package skyline.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.form.config.SystemAttributeNames;
import pub.platform.security.OperatorManager;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * 开发平台基本信息服务类
 */
public class SkylineService {
    private static final Logger logger = LoggerFactory.getLogger(SkylineService.class);

    public static OperatorManager getOperatorManager() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        OperatorManager om = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
        if (om == null) {
            throw new RuntimeException("用户未登录！");
        }
        return om;
    }

    public static String getOperId() {
        return getOperatorManager().getOperatorId();
    }
}
