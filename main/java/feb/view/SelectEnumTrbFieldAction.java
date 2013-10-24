package feb.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.List;

/**
 * 定期存款开户相关的枚举类
 */
@ManagedBean
@ViewScoped
public class SelectEnumTrbFieldAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(SelectEnumTrbFieldAction.class);

    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;
    private List<SelectItem> ctfStmsigItems;      // 系统标识
    private List<SelectItem> ctfAtoflgItems;      // 自动转存标志
    private List<SelectItem> ctfActtypItems;      // 账户类型
    private List<SelectItem> ctfActty2Items;      // 转息账号类型
    private List<SelectItem> ctfDramdItems;      // 取款方式
    private List<SelectItem> ctfDpttypItems;      // 存款种类
    private List<SelectItem> ctfVchtypItems;      // 凭证种类

    @PostConstruct
    public void init() {
        ctfAtoflgItems = skylineService.getEnuSelectItemList("CTF-ATOFLG", true, false);
        ctfActtypItems = skylineService.getEnuSelectItemList("CTF-ACTTYP", true, false);
        ctfActty2Items = skylineService.getEnuSelectItemList("CTF-ACTTY2", true, false);
        ctfDramdItems = skylineService.getEnuSelectItemList("CTF-DRAMD", true, false);
        ctfDpttypItems = skylineService.getEnuSelectItemList("CTF-DPTTYP", true, false);
        ctfVchtypItems = skylineService.getEnuSelectItemList("CTF-VCHTYP", true, false);
    }

    public SkylineService getSkylineService() {
        return skylineService;
    }

    public void setSkylineService(SkylineService skylineService) {
        this.skylineService = skylineService;
    }

    public List<SelectItem> getCtfStmsigItems() {
        return ctfStmsigItems;
    }

    public void setCtfStmsigItems(List<SelectItem> ctfStmsigItems) {
        this.ctfStmsigItems = ctfStmsigItems;
    }

    public List<SelectItem> getCtfAtoflgItems() {
        return ctfAtoflgItems;
    }

    public void setCtfAtoflgItems(List<SelectItem> ctfAtoflgItems) {
        this.ctfAtoflgItems = ctfAtoflgItems;
    }

    public List<SelectItem> getCtfActtypItems() {
        return ctfActtypItems;
    }

    public void setCtfActtypItems(List<SelectItem> ctfActtypItems) {
        this.ctfActtypItems = ctfActtypItems;
    }

    public List<SelectItem> getCtfActty2Items() {
        return ctfActty2Items;
    }

    public void setCtfActty2Items(List<SelectItem> ctfActty2Items) {
        this.ctfActty2Items = ctfActty2Items;
    }

    public List<SelectItem> getCtfDramdItems() {
        return ctfDramdItems;
    }

    public void setCtfDramdItems(List<SelectItem> ctfDramdItems) {
        this.ctfDramdItems = ctfDramdItems;
    }

    public List<SelectItem> getCtfDpttypItems() {
        return ctfDpttypItems;
    }

    public void setCtfDpttypItems(List<SelectItem> ctfDpttypItems) {
        this.ctfDpttypItems = ctfDpttypItems;
    }

    public List<SelectItem> getCtfVchtypItems() {
        return ctfVchtypItems;
    }

    public void setCtfVchtypItems(List<SelectItem> ctfVchtypItems) {
        this.ctfVchtypItems = ctfVchtypItems;
    }
}
