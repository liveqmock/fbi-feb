package skyline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import skyline.repository.dao.PtenudetailMapper;
import skyline.repository.model.Ptenudetail;
import skyline.repository.model.PtenudetailExample;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-9-6
 * Time: ÏÂÎç4:54
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PtEnuSelectImpl implements Selectable {

    @Autowired
    private PtenudetailMapper ptenudetailMapper;

    @Override
    public List<SelectItem> getSelectItems(String enuId, boolean isSelectNone, boolean isExpandID) {
        PtenudetailExample example = new PtenudetailExample();
        example.createCriteria().andEnutypeEqualTo(enuId);
        example.setOrderByClause(" DISPNO ");
        List<Ptenudetail> records = ptenudetailMapper.selectByExample(example);
        List<SelectItem> items = new ArrayList<SelectItem>();
        SelectItem item;
        if (isSelectNone) {
            item = new SelectItem("", "");
            items.add(item);
        }
        for (Ptenudetail record : records) {
            if (isExpandID) {
                item = new SelectItem(record.getEnuitemvalue(), record.getEnuitemvalue() + " " + record.getEnuitemlabel());
            } else {
                item = new SelectItem(record.getEnuitemvalue(), record.getEnuitemlabel());
            }
            items.add(item);
        }
        return items;
    }
}
