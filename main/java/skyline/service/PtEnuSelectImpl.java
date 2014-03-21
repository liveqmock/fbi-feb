package skyline.service;

import org.springframework.stereotype.Component;
import pub.platform.dao.PTENUDETAIL;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-9-6
 * Time: 下午4:54
 *
 * 更新：20140321 zhanrui 不再使用mybatis获取数据
 */
@Component
public class PtEnuSelectImpl implements Selectable {

    @Override
    public List<SelectItem> getSelectItems(String enuId, boolean isSelectNone, boolean isExpandID) {
        List<SelectItem> items = new ArrayList<SelectItem>();
        List<PTENUDETAIL> records = new PTENUDETAIL().findByWhere(" where enutype ='" + enuId + "' order by dispno");
        SelectItem item;
        if (isSelectNone) {
            item = new SelectItem("", "");
            items.add(item);
        }
        for (PTENUDETAIL record : records) {
            if (isExpandID) {
                item = new SelectItem(record.getEnuitemvalue(), record.getEnuitemvalue() + " " + record.getEnuitemlabel());
            } else {
                item = new SelectItem(record.getEnuitemvalue(), record.getEnuitemlabel());
            }
            items.add(item);
        }
        return items;
    }

    @Override
    public Map<String, String> getMap(String enuId) {
        Map<String, String> dataMap = new HashMap<>();
        List<PTENUDETAIL> records = new PTENUDETAIL().findByWhere(" where enutype ='" + enuId + "' order by dispno");
        for (PTENUDETAIL record : records) {
            dataMap.put(record.getEnuitemvalue(), record.getEnuitemvalue());
        }
        return dataMap;
    }
}
