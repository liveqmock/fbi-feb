package skyline.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.form.config.SystemAttributeNames;
import pub.platform.security.OperatorManager;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Spring中做具体初始化配置
 * 开发平台基本信息服务类
 */
public class SkylineService {
    private static final Logger logger = LoggerFactory.getLogger(SkylineService.class);

    private Selectable selectable; // 业务枚举类

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

    /**
     * 根据枚举表的内容组下拉菜单
     * @param enuId 枚举名
     * @param isSelectNone  true 添加空白选项， false 不添加
     * @param isExpandID   false:正常列表（不包含ID） true：列表中包含ID
     * @return
     */
    public List<SelectItem> getEnuSelectItemList(String enuId, boolean isSelectNone, boolean isExpandID) {
       return selectable.getSelectItems(enuId, isSelectNone, isExpandID);
    }

    public Selectable getSelectable() {
        return selectable;
    }

    public void setSelectable(Selectable selectable) {
        this.selectable = selectable;
    }
}
