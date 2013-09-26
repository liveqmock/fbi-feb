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
 * �ͻ���ص�ö����
 */
@ManagedBean
@ViewScoped
public class SelectEnumCusFieldAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(SelectEnumCusFieldAction.class);

    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;

    // private List<SelectItem> ctfStmfmtItems;      // ���˵���ҳ��ʽ
    private List<SelectItem> cusPastypItems;      //֤������
    private List<SelectItem> cusBocgrpItems;      //�ͻ�����
    private List<SelectItem> cusEnttypItems;      //��ҵ����
    private List<SelectItem> cusCusty1Items;      //���޽���Ȩ

    @PostConstruct
    public void init() {
        //ctfStmfmtItems = skylineService.getEnuSelectItemList("CTF-STMFMT", true, false);
        cusPastypItems = skylineService.getEnuSelectItemList("CTF-PASTYP", true, false);
        cusBocgrpItems = skylineService.getEnuSelectItemList("CTF-BOCGRP", true, false);
        cusEnttypItems = skylineService.getEnuSelectItemList("CTF-ENTTYP", true, false);
        cusCusty1Items = skylineService.getEnuSelectItemList("CTF-CUSTY1", true, false);

    }

    public SkylineService getSkylineService() {
        return skylineService;
    }

    public void setSkylineService(SkylineService skylineService) {
        this.skylineService = skylineService;
    }

    public List<SelectItem> getCusPastypItems() {
        return cusPastypItems;
    }

    public void setCusPastypItems(List<SelectItem> cusPastypItems) {
        this.cusPastypItems = cusPastypItems;
    }

    public List<SelectItem> getCusBocgrpItems() {
        return cusBocgrpItems;
    }

    public void setCusBocgrpItems(List<SelectItem> cusBocgrpItems) {
        this.cusBocgrpItems = cusBocgrpItems;
    }

    public List<SelectItem> getCusEnttypItems() {
        return cusEnttypItems;
    }

    public void setCusEnttypItems(List<SelectItem> cusEnttypItems) {
        this.cusEnttypItems = cusEnttypItems;
    }

    public List<SelectItem> getCusCusty1Items() {
        return cusCusty1Items;
    }

    public void setCusCusty1Items(List<SelectItem> cusCusty1Items) {
        this.cusCusty1Items = cusCusty1Items;
    }
}
