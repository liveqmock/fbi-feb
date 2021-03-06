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
 * 凭证相关的枚举类
 */
@ManagedBean
@ViewScoped
public class SelectEnumCimFieldAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(SelectEnumCimFieldAction.class);

    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;
    private List<SelectItem> ctfStmaloItems;      // 是否单种
    private List<SelectItem> ctfStmsctItems;      // 是否有价证券
    private List<SelectItem> ctfStmipoItems;      // 领入/交出
    private List<SelectItem> ctfStmhstItems;      // 传票查询借贷别
    private List<SelectItem> rsoStmdraItems;      // 定期结清取款方式
    private List<SelectItem> rsoStmdueItems;      // 定期结清强制到期标志



    @PostConstruct
    public void init() {
        ctfStmaloItems = skylineService.getEnuSelectItemList("CTF-ALLONE", true, false);
        ctfStmsctItems = skylineService.getEnuSelectItemList("CTF-SCTMAK", true, false);
        ctfStmipoItems = skylineService.getEnuSelectItemList("CTF-IOFLAG", true, false);
        ctfStmhstItems = skylineService.getEnuSelectItemList("CTF-HSTCDE", true, false);
        rsoStmdraItems = skylineService.getEnuSelectItemList("CTF-DRAMD1", true, false);
        rsoStmdueItems = skylineService.getEnuSelectItemList("CTF-DUEFLG", true, false);

    }



    public SkylineService getSkylineService() {
        return skylineService;
    }

    public void setSkylineService(SkylineService skylineService) {
        this.skylineService = skylineService;
    }

    public List<SelectItem> getCtfStmaloItems() {
        return ctfStmaloItems;
    }

    public void setCtfStmaloItems(List<SelectItem> ctfStmaloItems) {
        this.ctfStmaloItems = ctfStmaloItems;
    }

    public List<SelectItem> getCtfStmsctItems() {
        return ctfStmsctItems;
    }

    public void setCtfStmsctItems(List<SelectItem> ctfStmsctItems) {
        this.ctfStmsctItems = ctfStmsctItems;
    }

    public List<SelectItem> getCtfStmipoItems() {
        return ctfStmipoItems;
    }

    public void setCtfStmipoItems(List<SelectItem> ctfStmipoItems) {
        this.ctfStmipoItems = ctfStmipoItems;
    }

    public List<SelectItem> getCtfStmhstItems() {
        return ctfStmhstItems;
    }

    public void setCtfStmhstItems(List<SelectItem> ctfStmhstItems) {
        this.ctfStmhstItems = ctfStmhstItems;
    }

    public List<SelectItem> getRsoStmdraItems() {
        return rsoStmdraItems;
    }

    public void setRsoStmdraItems(List<SelectItem> rsoStmdraItems) {
        this.rsoStmdraItems = rsoStmdraItems;
    }

    public List<SelectItem> getRsoStmdueItems() {
        return rsoStmdueItems;
    }

    public void setRsoStmdueItems(List<SelectItem> rsoStmdueItems) {
        this.rsoStmdueItems = rsoStmdueItems;
    }
}
