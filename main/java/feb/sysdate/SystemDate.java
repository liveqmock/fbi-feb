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
    private static OperatorManager getOperatorManager() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        OperatorManager om = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
        return om;
    }

    private String strdate = getOperatorManager().getSysBusinessDate();

    private Date stringTodate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            date = sdf.parse(strdate);
        } catch (Exception e) {
            throw new RuntimeException("日期转化异常："+e);
        }
        return date;
    }

    private String sysdate1 = strdate;
    private Date sysdate2 = stringTodate();

    public String getSysdate1() {
        return sysdate1;
    }

    public Date getSysdate2() {
        return sysdate2;
    }

}
