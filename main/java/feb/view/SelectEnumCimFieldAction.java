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
 * ƾ֤��ص�ö����
 */
@ManagedBean
@ViewScoped
public class SelectEnumCimFieldAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(SelectEnumCimFieldAction.class);

    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;
    private List<SelectItem> ctfStmaloItems;      // �Ƿ���
    private List<SelectItem> ctfStmsctItems;      // �Ƿ��м�֤ȯ
    private List<SelectItem> ctfStmipoItems;      // ����/����


    @PostConstruct
    public void init() {
        ctfStmaloItems = skylineService.getEnuSelectItemList("CTF-ALLONE", true, false);
        ctfStmsctItems = skylineService.getEnuSelectItemList("CTF-SCTMAK", true, false);
        ctfStmipoItems = skylineService.getEnuSelectItemList("CTF-IOFLAG", true, false);

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
}
