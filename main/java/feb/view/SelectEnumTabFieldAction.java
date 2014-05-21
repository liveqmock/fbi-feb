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
 * 码表相关的枚举类
 */
@ManagedBean
@ViewScoped
public class SelectEnumTabFieldAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(SelectEnumTabFieldAction.class);

    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;

    private List<SelectItem> ctfTrfkidItems;      // 要素类别
    private List<SelectItem> ctfTrfoprItems;      // 要素操作码
    private List<SelectItem> ctfAmttypItems;      // 要素金额类别
    private List<SelectItem> ctfAmtsdeItems;      // 要素借贷方向
    private List<SelectItem> ctfAlctypItems;      // 资产负债类型
    private List<SelectItem> ctfDctypeItems;      // 借贷及轧差标识


    @PostConstruct
    public void init() {
        ctfTrfkidItems = skylineService.getEnuSelectItemList("CTF-TRFKID", true, false);
        ctfTrfoprItems = skylineService.getEnuSelectItemList("CTF-TRFOPR", true, false);
        ctfAmttypItems = skylineService.getEnuSelectItemList("CTF-AMTTYP", true, false);
        ctfAmtsdeItems = skylineService.getEnuSelectItemList("CTF-AMTSDE", true, false);
        ctfAlctypItems = skylineService.getEnuSelectItemList("CTF-ALCTYP", true, false);
        ctfDctypeItems = skylineService.getEnuSelectItemList("CTF-DCTYPE", true, false);

    }

    public SkylineService getSkylineService() {
        return skylineService;
    }

    public void setSkylineService(SkylineService skylineService) {
        this.skylineService = skylineService;
    }

    public List<SelectItem> getCtfTrfkidItems() {
        return ctfTrfkidItems;
    }

    public void setCtfTrfkidItems(List<SelectItem> ctfTrfkidItems) {
        this.ctfTrfkidItems = ctfTrfkidItems;
    }

    public List<SelectItem> getCtfTrfoprItems() {
        return ctfTrfoprItems;
    }

    public void setCtfTrfoprItems(List<SelectItem> ctfTrfoprItems) {
        this.ctfTrfoprItems = ctfTrfoprItems;
    }

    public List<SelectItem> getCtfAmttypItems() {
        return ctfAmttypItems;
    }

    public void setCtfAmttypItems(List<SelectItem> ctfAmttypItems) {
        this.ctfAmttypItems = ctfAmttypItems;
    }

    public List<SelectItem> getCtfAmtsdeItems() {
        return ctfAmtsdeItems;
    }

    public void setCtfAmtsdeItems(List<SelectItem> ctfAmtsdeItems) {
        this.ctfAmtsdeItems = ctfAmtsdeItems;
    }

    public List<SelectItem> getCtfAlctypItems() {
        return ctfAlctypItems;
    }

    public void setCtfAlctypItems(List<SelectItem> ctfAlctypItems) {
        this.ctfAlctypItems = ctfAlctypItems;
    }

    public List<SelectItem> getCtfDctypeItems() {
        return ctfDctypeItems;
    }

    public void setCtfDctypeItems(List<SelectItem> ctfDctypeItems) {
        this.ctfDctypeItems = ctfDctypeItems;
    }
}
