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
 * Spring���������ʼ������
 * ����ƽ̨������Ϣ������
 */
public class SkylineService {
    private static final Logger logger = LoggerFactory.getLogger(SkylineService.class);

    private Selectable selectable; // ҵ��ö����

    public static OperatorManager getOperatorManager() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        OperatorManager om = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
        if (om == null) {
            throw new RuntimeException("�û�δ��¼��");
        }
        return om;
    }

    public static String getOperId() {
        return getOperatorManager().getOperatorId();
    }

    /**
     * ����ö�ٱ�������������˵�
     * @param enuId ö����
     * @param isSelectNone  true ��ӿհ�ѡ� false �����
     * @param isExpandID   false:�����б�������ID�� true���б��а���ID
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
