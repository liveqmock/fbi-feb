package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.T851;
import gateway.sbs.txn.model.msg.M8822;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private T851 t851 = new T851();
    private List<T851.Bean> dataList = new ArrayList<>();
    private boolean isExport ;
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
    private String totcnt = "";
    private String curcnt = "";

    public String onVchsetQry() {
        try {
            m8822 = new M8822(cusidt, apcode, curcde, secamt,
                    ovelim, tlrnum, vchset, funcde, fegadd, begnum);
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8822", m8822);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T851".equals(form.getFormHeader().getFormCode())) {
                        T851 t851 = (T851) form.getFormBody();
                        dataList.addAll(t851.getBeanList());
                        totcnt = t851.getFormBodyHeader().getTOTCNT();
                        curcnt = t851.getFormBodyHeader().getCURCNT();
                        isExport = true;
                    } else if ("W012".equals(form.getFormHeader().getFormCode())) {

                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
            if (dataList == null || dataList.isEmpty()) {
                MessageUtil.addWarn("没有查询到数据。");
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public void exportExcel(Object document){
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);

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

    public T851 getT851() {
        return t851;
    }

    public void setT851(T851 t851) {
        this.t851 = t851;
    }

    public List<T851.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T851.Bean> dataList) {
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

    public String getTotcnt() {
        return totcnt;
    }

    public void setTotcnt(String totcnt) {
        this.totcnt = totcnt;
    }

    public String getCurcnt() {
        return curcnt;
    }

    public void setCurcnt(String curcnt) {
        this.curcnt = curcnt;
    }

    public boolean isExport() {
        return isExport;
    }

    public void setExport(boolean export) {
        isExport = export;
    }
}
