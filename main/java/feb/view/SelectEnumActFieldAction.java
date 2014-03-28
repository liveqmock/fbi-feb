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
public class SelectEnumActFieldAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(SelectEnumActFieldAction.class);

    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;
    private List<SelectItem> ctfStmfmtItems;      // ���˵���ҳ��ʽ
    private List<SelectItem> ctfLegfmtItems;      // �ֻ�����ҳ��ʽ
    private List<SelectItem> ctfActtypItems;      // �˻�����
    private List<SelectItem> ctfStmcycItems;      // ���˵���������
    private List<SelectItem> ctfLegcycItems;      // �ֻ��˳�������
    private List<SelectItem> ctfIntflgItems;      // ��Ϣ��־
    private List<SelectItem> ctfIntcycItems;      // ��Ϣ����
    private List<SelectItem> ctfLegzipItems;      // �˻���;
    private List<SelectItem> ctfCqeflgItems;      // ֧Ʊ/���۱�־
    /**
     * wang
     */
    private List<SelectItem> ctfInterActtypItems;         //�ڲ��˻�����
    private List<SelectItem> cusInterLegfmtItems;         //�ڲ��ֻ�����ҳ��ʽ

    @PostConstruct
    public void init() {
        ctfStmfmtItems = skylineService.getEnuSelectItemList("CTF-STMFMT", true, false);
        ctfLegfmtItems = skylineService.getEnuSelectItemList("CTF-LEGFMT", true, false);
        ctfActtypItems = skylineService.getEnuSelectItemList("CTF-ACTTYP", true, false);
        ctfStmcycItems = skylineService.getEnuSelectItemList("CTF-STMCYC", true, false);
        ctfLegcycItems = skylineService.getEnuSelectItemList("CTF-LEGCYC", true, false);
        ctfIntflgItems = skylineService.getEnuSelectItemList("CTF-INTFLG", true, false);
        ctfIntcycItems = skylineService.getEnuSelectItemList("CTF-INTCYC", true, false);
        ctfLegzipItems = skylineService.getEnuSelectItemList("CTF-LEGZIP", true, false);
        ctfCqeflgItems = skylineService.getEnuSelectItemList("CTF-CQEFLG", true, false);

        ctfInterActtypItems = skylineService.getEnuSelectItemList("CTF-INTERACTTYP", true, false);
        cusInterLegfmtItems = skylineService.getEnuSelectItemList("CTF-INTERLEGFMT", true, false);
    }



    public SkylineService getSkylineService() {
        return skylineService;
    }

    public void setSkylineService(SkylineService skylineService) {
        this.skylineService = skylineService;
    }

    public List<SelectItem> getCtfStmfmtItems() {
        return ctfStmfmtItems;
    }

    public void setCtfStmfmtItems(List<SelectItem> ctfStmfmtItems) {
        this.ctfStmfmtItems = ctfStmfmtItems;
    }

    public List<SelectItem> getCtfLegfmtItems() {
        return ctfLegfmtItems;
    }

    public void setCtfLegfmtItems(List<SelectItem> ctfLegfmtItems) {
        this.ctfLegfmtItems = ctfLegfmtItems;
    }

    public List<SelectItem> getCtfActtypItems() {
        return ctfActtypItems;
    }

    public void setCtfActtypItems(List<SelectItem> ctfActtypItems) {
        this.ctfActtypItems = ctfActtypItems;
    }

    public List<SelectItem> getCtfStmcycItems() {
        return ctfStmcycItems;
    }

    public void setCtfStmcycItems(List<SelectItem> ctfStmcycItems) {
        this.ctfStmcycItems = ctfStmcycItems;
    }

    public List<SelectItem> getCtfLegcycItems() {
        return ctfLegcycItems;
    }

    public void setCtfLegcycItems(List<SelectItem> ctfLegcycItems) {
        this.ctfLegcycItems = ctfLegcycItems;
    }

    public List<SelectItem> getCtfIntflgItems() {
        return ctfIntflgItems;
    }

    public void setCtfIntflgItems(List<SelectItem> ctfIntflgItems) {
        this.ctfIntflgItems = ctfIntflgItems;
    }

    public List<SelectItem> getCtfIntcycItems() {
        return ctfIntcycItems;
    }

    public void setCtfIntcycItems(List<SelectItem> ctfIntcycItems) {
        this.ctfIntcycItems = ctfIntcycItems;
    }

    public List<SelectItem> getCtfLegzipItems() {
        return ctfLegzipItems;
    }

    public void setCtfLegzipItems(List<SelectItem> ctfLegzipItems) {
        this.ctfLegzipItems = ctfLegzipItems;
    }

    public List<SelectItem> getCtfCqeflgItems() {
        return ctfCqeflgItems;
    }

    public void setCtfCqeflgItems(List<SelectItem> ctfCqeflgItems) {
        this.ctfCqeflgItems = ctfCqeflgItems;
    }

    public List<SelectItem> getCtfInterActtypItems() {
        return ctfInterActtypItems;
    }

    public void setCtfInterActtypItems(List<SelectItem> ctfInterActtypItems) {
        this.ctfInterActtypItems = ctfInterActtypItems;
    }

    public List<SelectItem> getCusInterLegfmtItems() {
        return cusInterLegfmtItems;
    }

    public void setCusInterLegfmtItems(List<SelectItem> cusInterLegfmtItems) {
        this.cusInterLegfmtItems = cusInterLegfmtItems;
    }
}
