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
 * �˻���ص�ö����
 */
@ManagedBean
@ViewScoped
public class SelectEnumTrbFieldAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(SelectEnumTrbFieldAction.class);

    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;
    private List<SelectItem> ctfStmsigItems;      // ϵͳ��ʶ
    private List<SelectItem> ctfAutrenItems;      // �Զ�ת���־
    private List<SelectItem> ctfActtypItems;      // �˻�����*
    private List<SelectItem> ctfTratatItems;      // ת���˺�����
    private List<SelectItem> ctfDraawwItems;      // ȡ�ʽ1
    private List<SelectItem> ctfDepdepItems;      // �������
    private List<SelectItem> ctfVoukinItems;      // ƾ֤����
    private List<SelectItem> ctfDratwoItems;      // ȡ�ʽ2

    @PostConstruct
    public void init() {
        ctfStmsigItems = skylineService.getEnuSelectItemList("CTF-SYSIDT", true, false);
        ctfAutrenItems = skylineService.getEnuSelectItemList("CTF-ATOFLG", true, false);
        ctfActtypItems = skylineService.getEnuSelectItemList("CTF-ACTTYP", true, false);
        ctfTratatItems = skylineService.getEnuSelectItemList("CTF-ACTTY2", true, false);
        ctfDraawwItems = skylineService.getEnuSelectItemList("CTF-DRAMD1", true, false);
        ctfDepdepItems = skylineService.getEnuSelectItemList("CTF-DPTTYP", true, false);
        ctfVoukinItems = skylineService.getEnuSelectItemList("CTF-VCHTYP", true, false);
        ctfDratwoItems = skylineService.getEnuSelectItemList("CTF-DRAMD2", true, false);

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

    public List<SelectItem> getCtfAutrenItems() {
        return ctfAutrenItems;
    }

    public void setCtfAutrenItems(List<SelectItem> ctfAutrenItems) {
        this.ctfAutrenItems = ctfAutrenItems;
    }

    public List<SelectItem> getCtfActtypItems() {
        return ctfActtypItems;
    }

    public void setCtfActtypItems(List<SelectItem> ctfActtypItems) {
        this.ctfActtypItems = ctfActtypItems;
    }

    public List<SelectItem> getCtfTratatItems() {
        return ctfTratatItems;
    }

    public void setCtfTratatItems(List<SelectItem> ctfTratatItems) {
        this.ctfTratatItems = ctfTratatItems;
    }

    public List<SelectItem> getCtfDraawwItems() {
        return ctfDraawwItems;
    }

    public void setCtfDraawwItems(List<SelectItem> ctfDraawwItems) {
        this.ctfDraawwItems = ctfDraawwItems;
    }

    public List<SelectItem> getCtfDepdepItems() {
        return ctfDepdepItems;
    }

    public void setCtfDepdepItems(List<SelectItem> ctfDepdepItems) {
        this.ctfDepdepItems = ctfDepdepItems;
    }

    public List<SelectItem> getCtfVoukinItems() {
        return ctfVoukinItems;
    }

    public void setCtfVoukinItems(List<SelectItem> ctfVoukinItems) {
        this.ctfVoukinItems = ctfVoukinItems;
    }

    public List<SelectItem> getCtfDratwoItems() {
        return ctfDratwoItems;
    }

    public void setCtfDratwoItems(List<SelectItem> ctfDratwoItems) {
        this.ctfDratwoItems = ctfDratwoItems;
    }
}
