package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T623;
import gateway.sbs.txn.model.msg.M8621;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private boolean isExport;
    private String curcde = "001";
    M8621 m8621 = new M8621();
    List<T623.Bean> dataList = new ArrayList<>();

    public void onAllQry() {
        m8621.setCURCDE(curcde);
        List<SOFForm> formList = dataExchangeService.callSbsTxn("8621", m8621);
        if (formList != null && !formList.isEmpty()) {
            dataList = new ArrayList<>();
            for (SOFForm form : formList) {
                if ("T623".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                    T623 t623 = (T623) form.getFormBody();
                    dataList.addAll(t623.getBeanList());
                    isExport = true;
                } else if (form.getFormHeader().getFormCode().contains("W012")) {
                    MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                } else {
                    logger.info(form.getFormHeader().getFormCode());
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        }
    }

    public void onExpExcel() {
        String[] excelHeader4 = {"代号", "名称", "金额", "代号", "名称", "金额"};
        String[] excelHeader3 = {"支出项目", "收入项目"};
        List<T623.Bean> dataList6 = new ArrayList<>();
        List<T623.Bean> dataList5 = new ArrayList<>();
        BigDecimal totexp = null;//支出合计
        BigDecimal totrev = null;//收入合计
        BigDecimal bene = null;//结益
        BigDecimal loss = null;//结损
        for (T623.Bean datas : dataList) {
            if ("2".equals(datas.getPLTYPE())) {
                if ("3".equals(datas.getPLCLAS())) {
                    totexp = datas.getPLAMNT();//支出合计
                } else if ("4".equals(datas.getPLCLAS())) {
                    bene = datas.getPLAMNT();//结益
                } else {
                    dataList6.add(datas);
                }
            } else if ("1".equals(datas.getPLTYPE())) {
                if ("3".equals(datas.getPLCLAS())) {
                    totrev = datas.getPLAMNT();//收入合计
                } else if ("4".equals(datas.getPLCLAS())) {
                    loss = datas.getPLAMNT();//结损
                } else {
                    dataList5.add(datas);
                }
            }
        }
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("acctab");
            HSSFRow row = null;

            HSSFFont font = wb.createFont();
            font.setFontName("Arial");                          //字体
            font.setFontHeightInPoints((short) 10);             //字体大小
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);      //粗体

            HSSFCellStyle styleHeader = wb.createCellStyle();    //header设置
            styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
            styleHeader.setFont(font);
            styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);   //左边框
            styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);   //右边框
            styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);    //上
            styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下

            HSSFCellStyle styleLeft = wb.createCellStyle();
            styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);  //居左
            styleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);   //左边框
            styleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);   //右边框
            styleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);    //上边框
            styleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下

            HSSFCellStyle styleCenter = wb.createCellStyle();
            styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //居中
            styleCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);   //左边框
            styleCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);   //右边框
            styleCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);    //上边框
            styleCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下

            HSSFCellStyle styleRight = wb.createCellStyle();
            styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);    //居右
            styleRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);   //左边框
            styleRight.setBorderRight(HSSFCellStyle.BORDER_THIN);   //右边框
            styleRight.setBorderTop(HSSFCellStyle.BORDER_THIN);    //上边框
            styleRight.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下

            //第一行
            row = sheet.createRow((int) 0);
            for (int i = 0; i < 1; i++) {
                row.setHeight((short) 400);     //行高
                HSSFFont font0 = wb.createFont();
                font0.setFontName("Arial");                          //字体
                font0.setFontHeightInPoints((short) 12);             //字体大小
                font0.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);      //粗体
                HSSFCellStyle styleHeader0 = wb.createCellStyle();    //header设置
                styleHeader0.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
                styleHeader0.setFont(font0);
                //styleHeader0.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 5));
                HSSFCell cell = row.createCell(i);
                cell.setCellValue("损益表");
                cell.setCellStyle(styleHeader0);
            }

            //第二行
            SimpleDateFormat smt = new SimpleDateFormat("yyyy年MM月dd日");
            row = sheet.createRow((int) 1);
            for (int i = 0; i < 6; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(styleHeader);
                if (i == 0) {
                    cell.setCellValue("货币：");
                } else if (i == 1) {
                    if ("001".equals(curcde)) {
                        cell.setCellValue("001-人民币");
                    } else if ("014".equals(curcde)) {
                        cell.setCellValue("014-各外币折美元");
                    } else {
                        cell.setCellValue("999-各货币折人民币");
                    }
                } else if (i == 2) {
                    cell.setCellValue(smt.format(new Date()));
                } else if (i == 5) {
                    cell.setCellValue("单位：元");
                }
            }

            //第三行
            row = sheet.createRow((int) 2);
            for (int i = 0; i < excelHeader3.length; i++) {
                if (i == 0) {
                    Region region2row = new Region(2, (short) 0, 2, (short) 2);
                    //参数1：行号 参数2：起始列号 参数3：行号 参数4：终止列号
                    sheet.addMergedRegion(region2row);
                    HSSFCell cell = row.createCell(i);
                    cell.setCellValue(excelHeader3[i]);
                    cell.setCellStyle(styleHeader);
                } else if (i == 1) {
                    Region region2row = new Region(2, (short) 3, 2, (short) 5);
                    //参数1：行号 参数2：起始列号 参数3：行号 参数4：终止列号
                    sheet.addMergedRegion(region2row);
                    HSSFCell cell = row.createCell(3);
                    cell.setCellValue(excelHeader3[i]);
                    cell.setCellStyle(styleHeader);
                }
            }

            row = sheet.createRow((int) 3);
            for (int i = 0; i < excelHeader4.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(excelHeader4[i]);
                cell.setCellStyle(styleHeader);
                sheet.autoSizeColumn(i);
            }

            for (int i = 0; i < dataList6.size(); i++) {
                row = sheet.createRow(i + 4);
                T623.Bean bean = dataList6.get(i);
                if ("2".equals(bean.getPLCLAS())) {
                    row.createCell(0).setCellValue(bean.getPLCODE());
                    sheet.getRow(i + 4).getCell(0).setCellStyle(styleCenter);
                    sheet.setColumnWidth(0, 3000);  //设置列宽
                } else {
                    row.createCell(0).setCellValue(bean.getPLCODE());
                    sheet.getRow(i + 4).getCell(0).setCellStyle(styleRight);
                    sheet.setColumnWidth(0, 3000);
                }
                row.createCell(1).setCellValue(bean.getPLNAME());
                sheet.getRow(i + 4).getCell(1).setCellStyle(styleLeft);
                sheet.setColumnWidth(1, 8000);

                row.createCell(2).setCellValue(bean.getPLAMNT().toString());
                sheet.getRow(i + 4).getCell(2).setCellStyle(styleRight);
                sheet.setColumnWidth(2, 6000);
            }

            for (int i = 0; i < dataList5.size(); i++) {
                if (dataList5.size() > dataList6.size()) {
                    if (i < dataList6.size()) {
                        row = sheet.getRow(i + 4);
                    } else {
                        row = sheet.createRow(i + 4);
                    }
                    T623.Bean bean = dataList5.get(i);
                    if ("2".equals(bean.getPLCLAS())) {
                        row.createCell(3).setCellValue(bean.getPLCODE());
                        sheet.getRow(i + 4).getCell(3).setCellStyle(styleCenter);
                        sheet.setColumnWidth(3, 3000);
                    } else {
                        row.createCell(3).setCellValue(bean.getPLCODE());
                        sheet.getRow(i + 4).getCell(3).setCellStyle(styleRight);
                        sheet.setColumnWidth(3, 3000);
                    }
                    row.createCell(4).setCellValue(bean.getPLNAME());
                    sheet.getRow(i + 4).getCell(4).setCellStyle(styleLeft);
                    sheet.setColumnWidth(4, 8000);

                    row.createCell(5).setCellValue(bean.getPLAMNT().toString());
                    sheet.getRow(i + 4).getCell(5).setCellStyle(styleRight);
                    sheet.setColumnWidth(5, 6000);
                } else {
                    row = sheet.getRow(i + 4);
                    T623.Bean bean = dataList5.get(i);
                    if ("2".equals(bean.getPLCLAS())) {
                        row.createCell(3).setCellValue(bean.getPLCODE());
                        sheet.getRow(i + 4).getCell(3).setCellStyle(styleLeft);
                        sheet.setColumnWidth(3, 3000);
                    } else {
                        row.createCell(3).setCellValue(bean.getPLCODE());
                        sheet.getRow(i + 4).getCell(3).setCellStyle(styleRight);
                        sheet.setColumnWidth(3, 3000);
                    }
                    row.createCell(4).setCellValue(bean.getPLNAME());
                    sheet.getRow(i + 4).getCell(4).setCellStyle(styleLeft);
                    sheet.setColumnWidth(4, 8000);

                    row.createCell(5).setCellValue(bean.getPLAMNT().toString());
                    sheet.getRow(i + 4).getCell(5).setCellStyle(styleRight);
                    sheet.setColumnWidth(5, 6000);
                }
            }

            if (dataList5.size() > dataList6.size()) {

                row = sheet.createRow(dataList5.size() + 4);
                row.createCell(1).setCellValue("支出合计:");
                row.getCell(1).setCellStyle(styleLeft);
                row.createCell(2).setCellValue(totexp.toString());
                row.getCell(2).setCellStyle(styleRight);
                row.createCell(4).setCellValue("收入合计:");
                row.getCell(4).setCellStyle(styleLeft);
                row.createCell(5).setCellValue(totrev.toString());
                row.getCell(5).setCellStyle(styleRight);

                row = sheet.createRow(dataList5.size() + 5);
                row.createCell(1).setCellValue("结益:");
                row.getCell(1).setCellStyle(styleLeft);
                row.createCell(2).setCellValue(bene.toString());
                row.getCell(2).setCellStyle(styleRight);
                row.createCell(4).setCellValue("结损:");
                row.getCell(4).setCellStyle(styleLeft);
                row.createCell(5).setCellValue(loss.toString());
                row.getCell(5).setCellStyle(styleRight);

            }
            OutputStream ouputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode("SBS损益表.xls", "UTF-8"));
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
            ctx.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*public String onExpExcel() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillPattern(HSSFCellStyle.ALIGN_CENTER);

        for (int i = 2; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);
            cell.setCellStyle(cellStyle);
        }
        Iterator<T623.Bean> it = dataList.iterator();
        while (it.hasNext()) {

        }
        return null;
    }*/

    /*public String onExpExcel() {
        try {
            if (dataList == null || dataList.size() == 0) {
                MessageUtil.addWarn("未查询数据！");
                return null;
            }
            Map beansMap = new HashMap();
            Date expdat = new Date();
            String name = "";
            BigDecimal ctot6 = null; //支出合计
            BigDecimal dtot5 = null;  //收入合计:
            BigDecimal jtot6 = null;  //收入合计:
            BigDecimal stot5 = null;  //结损:
            List<T623.Bean> dataList6 = new ArrayList<>();
            List<T623.Bean> dataList5 = new ArrayList<>();
            for (T623.Bean datas : dataList) {
                if ("2".equals(datas.getPLTYPE())) {
                    if ("3".equals(datas.getPLCLAS())) {
                        ctot6 = datas.getPLAMNT();
                    } else if ("4".equals(datas.getPLCLAS())) {
                        jtot6 = datas.getPLAMNT();
                    } else {
                        dataList6.add(datas);
                    }
                } else if ("1".equals(datas.getPLTYPE())) {
                    if ("3".equals(datas.getPLCLAS())) {
                        dtot5 = datas.getPLAMNT();
                    } else if ("4".equals(datas.getPLCLAS())) {
                        stot5 = datas.getPLAMNT();
                    } else {
                        dataList5.add(datas);
                    }
                }
            }
            if ("001".equals(curcde)){
                name = "人民币";
            }else if ("014".equals(curcde)){
                name = "各外币折美元";
            }else {
                name = "各货币折人民币";
            }
            String excelFilename = "SBS损益表" + ".xls";
            JxlsManager jxls = new JxlsManager();
            beansMap.put("record6s", dataList6);
            beansMap.put("record5s", dataList5);
            beansMap.put("curcde", curcde);
            beansMap.put("expdat", expdat);
            beansMap.put("name", name);
            beansMap.put("ctot6", ctot6);
            beansMap.put("dtot5", dtot5);
            beansMap.put("jtot6", jtot6);
            beansMap.put("stot5", stot5);
            jxls.exportDataToXls(excelFilename, "/acctab.xls", beansMap);
        } catch (Exception e) {

        }
        return null;
    }*/

    //= = = = = = = = = = = get set = = = = = = = =

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public boolean isExport() {
        return isExport;
    }

    public void setExport(boolean isExport) {
        this.isExport = isExport;
    }

    public String getCurcde() {
        return curcde;
    }

    public void setCurcde(String curcde) {
        this.curcde = curcde;
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
