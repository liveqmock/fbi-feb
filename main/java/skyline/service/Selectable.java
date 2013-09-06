package skyline.service;

import javax.faces.model.SelectItem;
import java.util.List;

/**
 * 可选择下拉框
 */
public interface Selectable {
    List<SelectItem> getSelectItems(String enuId, boolean isSelectNone, boolean isExpandID);
}
