package skyline.service;

import javax.faces.model.SelectItem;
import java.util.List;

/**
 * ��ѡ��������
 */
public interface Selectable {
    List<SelectItem> getSelectItems(String enuId, boolean isSelectNone, boolean isExpandID);
}
