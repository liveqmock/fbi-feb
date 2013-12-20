package feb.sysdate;

import pub.platform.form.config.SystemAttributeNames;
import pub.platform.security.OperatorManager;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-12-20
 * Time: 下午12:42
 * To change this template use File | Settings | File Templates.
 */
public class SystemDate {
    //获取系统日期==========Skyservice============
    private OperatorManager getOperatorManager() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        OperatorManager om = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
        return om;
    }
    private String sysdate = getOperatorManager().getSysBusinessDate();
    //private String sysdate2 = new SimpleDateFormat("yyyy/MM/dd").format(sysdate);

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

}
