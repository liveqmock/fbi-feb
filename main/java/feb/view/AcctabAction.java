package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T623;
import gateway.sbs.txn.model.msg.M8621;
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

/**
 * Created by Lichao.W At 2015/3/24 9:17
 * wanglichao@163.com
 */
@ManagedBean
@ViewScoped
public class AcctabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActAccDtlQryAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    M8621 m8621 = new M8621();
    List<T623.Bean> dataList = new ArrayList<>();

    public void onAllQry(){
        List<SOFForm> formList = dataExchangeService.callSbsTxn("8621", m8621);
        if (formList != null && !formList.isEmpty()) {
            dataList = new ArrayList<>();
            for (SOFForm form : formList) {
                if ("T623".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                    T623 t623 = (T623) form.getFormBody();
                    dataList.addAll(t623.getBeanList());
                } else if (form.getFormHeader().getFormCode().contains("W012")) {
                    MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                } else {
                    logger.info(form.getFormHeader().getFormCode());
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        }
    }

    public void xlsExport(Object document){
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

    //= = = = = = = = = = = get set = = = = = = = =

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public M8621 getM8621() {
        return m8621;
    }

    public void setM8621(M8621 m8621) {
        this.m8621 = m8621;
    }

    public List<T623.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T623.Bean> dataList) {
        this.dataList = dataList;
    }
}
