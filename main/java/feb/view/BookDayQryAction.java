package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T151;
import gateway.sbs.txn.model.msg.M8822;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-20
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class BookDayQryAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(BookDayQryAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private M8822 m8822;
    private T151 t151 = new T151();
    private List<T151.Bean> dataList = new ArrayList<>();
    private boolean isExport;
    private String cusidt = "";
    private String apcode = "";
    private String curcde = "";
    private String secamt = "";
    private String ovelim = "";
    private String tlrnum = "";
    private String vchset = "";
    private String funcde = "";
    private String fegadd = "";
    private String begnum = "";


    public String onVchsetQry() {
        String totcnt = "";
        String curcnt = "";
        int m = 0;//取整
        int n = 0;//取余
        try {
            m8822 = new M8822(cusidt, apcode, curcde, secamt,
                    ovelim, tlrnum, vchset, funcde, fegadd, begnum);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8822", m8822);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T151".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t151 = (T151) form.getFormBody();
                        dataList = t151.getBeanList();
                        totcnt = t151.getFormBodyHeader().getTOTCNT();
                        curcnt = t151.getFormBodyHeader().getCURCNT();
                        isExport = true;
                    } else {
                        logger.error(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (!totcnt.isEmpty() && totcnt != "") {
                //因为 totcnt是全局变量，所有在第一次查询之后，发起第二次交易时totcnt就不为空，所有要在第一次发起交易时清空
                m = Integer.parseInt(totcnt) / Integer.parseInt(curcnt);
                n = Integer.parseInt(totcnt) % Integer.parseInt(curcnt);
                if (m > 0 && n > 0) {
                    String tmp = "";
                    for (int j = 1; j < m; j++) {
                        tmp = j * Integer.parseInt(curcnt) + 1 + "";
                        m8822.setBEGNUM(tmp);
                        List<SOFForm> formList2 = dataExchangeService.callSbsTxn("8822", m8822);
                        if (formList2 != null && !formList2.isEmpty()) {
                            for (SOFForm form : formList2) {
                                if ("T151".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                                    t151 = (T151) form.getFormBody();
                                    dataList.addAll(t151.getBeanList());
                                    isExport = true;
                                } else {
                                    logger.error(form.getFormHeader().getFormCode());
                                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
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
        int rowLen = sheet.getLastRowNum();
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 1; i <= rowLen; i++) {
            //HSSFCell cell = header.getCell(i);
            HSSFCell cell = null;
            for (int j = 0; j < header.getPhysicalNumberOfCells(); j++) {
                if (j==2){
                    cell = sheet.getRow(i).getCell(j);
                    String a = cell.getStringCellValue().trim();
                    //cell.setCellValue("111");对值操作
                    //System.out.println(a);
                }


            }
            cell.setCellStyle(cellStyle);
        }
    }


    //==========================================================================
    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public M8822 getM8822() {
        return m8822;
    }

    public void setM8822(M8822 m8822) {
        this.m8822 = m8822;
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

    public String getCusidt() {
        return cusidt;
    }

    public void setCusidt(String cusidt) {
        this.cusidt = cusidt;
    }

    public String getVchset() {
        return vchset;
    }

    public void setVchset(String vchset) {
        this.vchset = vchset;
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

    public String getTlrnum() {
        return tlrnum;
    }

    public void setTlrnum(String tlrnum) {
        this.tlrnum = tlrnum;
    }

    public String getFuncde() {
        return funcde;
    }

    public void setFuncde(String funcde) {
        this.funcde = funcde;
    }

    public String getFegadd() {
        return fegadd;
    }

    public void setFegadd(String fegadd) {
        this.fegadd = fegadd;
    }

    public String getBegnum() {
        return begnum;
    }

    public void setBegnum(String begnum) {
        this.begnum = begnum;
    }


    public boolean isExport() {
        return isExport;
    }

    public void setExport(boolean export) {
        isExport = export;
    }

}
