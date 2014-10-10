package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T151;
import gateway.sbs.txn.model.msg.M8823;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static skyline.service.SkylineService.getOperId;

/**
 * 利率码
 */
@ManagedBean
@ViewScoped
public class ActhstAction implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ActhstAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private T151 t151 = new T151();

    private String cusidt = "";
    private String apcode = "";
    private String curcde = "";
    private String eryda1 = "";
    private String eryda2 = "";
    private String secamt = "";
    private String ovelim = "";
    private String tlenum = getOperId();
    private String vchset = "";
    private String funcde = "";
    private String regadd = "";
    private String begnum = "";
    private boolean isExport;
    private List<T151.Bean> dataList = new ArrayList<>();



    public String onAllQuery() {
        String totcnt = "";
        String curcnt = "";
        int m = 0;//取整
        int n = 0;//取余
        try {
            M8823 m8823 = new M8823(cusidt, apcode, curcde, eryda1, eryda2,
                    secamt, ovelim, tlenum, vchset, funcde, regadd, begnum);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8823", m8823);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T151".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t151 = (T151) form.getFormBody();
                        dataList = t151.getBeanList();
                        totcnt = t151.getFormBodyHeader().getTOTCNT();
                        curcnt = t151.getFormBodyHeader().getCURCNT();
                        isExport = true;
                    }else {
                        logger.error(form.getFormHeader().getFormCode());
                        pub.platform.MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (!totcnt.isEmpty()&&totcnt!=""){
                //因为 totcnt是全局变量，所有在第一次查询之后，发起第二次交易时totcnt就不为空，所有要在第一次发起交易时清空
                m = Integer.parseInt(totcnt) / Integer.parseInt(curcnt);
                n = Integer.parseInt(totcnt) % Integer.parseInt(curcnt);
                if (m>0&&n>0){
                    String tmp = "";
                    for (int j = 1; j <= m; j++) {
                        tmp = j * Integer.parseInt(curcnt) + 1 + "";
                        m8823.setBEGNUM(tmp);
                        List<SOFForm> formList2 = dataExchangeService.callSbsTxn("8823", m8823);
                        if (formList2 != null && !formList2.isEmpty()) {
                            for (SOFForm form : formList2) {
                                if ("T151".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                                    t151 = (T151) form.getFormBody();
                                    dataList.addAll(t151.getBeanList());
                                    isExport = true;
                                } else {
                                    logger.error(form.getFormHeader().getFormCode());
                                    pub.platform.MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public void exportExcel(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    //-------------------------------------------------------------------------------
    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getCusidt() {
        return cusidt;
    }

    public void setCusidt(String cusidt) {
        this.cusidt = cusidt;
    }

    public String getApcode() {
        return apcode;
    }

    public void setApcode(String apcode) {
        this.apcode = apcode;
    }

    public String getCurcde() {
        return curcde;
    }

    public void setCurcde(String curcde) {
        this.curcde = curcde;
    }

    public String getEryda1() {
        return eryda1;
    }

    public void setEryda1(String eryda1) {
        this.eryda1 = eryda1;
    }

    public String getEryda2() {
        return eryda2;
    }

    public void setEryda2(String eryda2) {
        this.eryda2 = eryda2;
    }

    public String getSecamt() {
        return secamt;
    }

    public void setSecamt(String secamt) {
        this.secamt = secamt;
    }

    public String getOvelim() {
        return ovelim;
    }

    public void setOvelim(String ovelim) {
        this.ovelim = ovelim;
    }

    public String getTlenum() {
        return tlenum;
    }

    public void setTlenum(String tlenum) {
        this.tlenum = tlenum;
    }

    public String getVchset() {
        return vchset;
    }

    public void setVchset(String vchset) {
        this.vchset = vchset;
    }

    public String getFuncde() {
        return funcde;
    }

    public void setFuncde(String funcde) {
        this.funcde = funcde;
    }

    public String getRegadd() {
        return regadd;
    }

    public void setRegadd(String regadd) {
        this.regadd = regadd;
    }

    public String getBegnum() {
        return begnum;
    }

    public void setBegnum(String begnum) {
        this.begnum = begnum;
    }

    public T151 getT151() {
        return t151;
    }

    public void setT151(T151 t151) {
        this.t151 = t151;
    }

    public List<T151.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T151.Bean> dataList) {
        this.dataList = dataList;
    }


    public boolean isExport() {
        return isExport;
    }

    public void setExport(boolean export) {
        isExport = export;
    }
}
