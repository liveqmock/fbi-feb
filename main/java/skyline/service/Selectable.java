package skyline.service;

import javax.faces.model.SelectItem;
import java.util.List;
import java.util.Map;

/**
 * ��ѡ��������
 */
public interface Selectable {
    List<SelectItem> getSelectItems(String enuId, boolean isSelectNone, boolean isExpandID);

    Map<String, String> getMap(String enuId);
}
