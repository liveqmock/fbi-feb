package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.msg.M8401;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 13-11-20
 * Time: 下午5:53
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class BatchImportAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(BatchImportAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private M8401 m8401 = new M8401();
    private String filepath;
    private UploadedFile file;
    private String vchset;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        vchset = StringUtils.isEmpty(params.get("vchset")) ? "0000" : params.get("vchset");
        //System.out.println("==============================>" + Charset.defaultCharset());
        //System.out.println("==============================>" + System.getProperty("file.encoding"));//获取java环境默认编码
//        tlrnum = SkylineService.getOperId();//============>得到当前柜员号
//        sysdat = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    }
        /**
         * 对外提供读取excel 的方法
         * */
        public List<List<Object>> readExcel(File file) throws IOException{
            String fileName = file.getName();
            String extension = fileName.lastIndexOf(".")==-1?"":fileName.substring(fileName.lastIndexOf(".")+1);
            if("xls".equals(extension)){
                return read2003Excel(file);
            }else if("xlsx".equals(extension)){
                return read2007Excel(file);
            }else{
                throw new IOException("不支持的文件类型");
            }
        }
        /**
         * 读取 office 2003 excel
         * @throws IOException
         * @throws FileNotFoundException */
        private List<List<Object>> read2003Excel(File file) throws IOException{
            List<List<Object>> list = new LinkedList<List<Object>>();
            HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = hwb.getSheetAt(0);
            Object value = null;
            HSSFRow row = null;
            HSSFCell cell = null;

            for(int i = sheet.getFirstRowNum();i<= sheet.getPhysicalNumberOfRows();i++){
                row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                List<Object> linked = new LinkedList<Object>();
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    DecimalFormat df = new DecimalFormat("0");// 格式化 number String 字符
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                    DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            System.out.println(i+"行"+j+" 列 is String type");
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            System.out.println(i+"行"+j+" 列 is Number type ; DateFormt:"+cell.getCellStyle().getDataFormatString());
                            if("@".equals(cell.getCellStyle().getDataFormatString())){
                                value = df.format(cell.getNumericCellValue());
                            } else if("General".equals(cell.getCellStyle().getDataFormatString())){
                                value = nf.format(cell.getNumericCellValue());
                            }else{
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.println(i+"行"+j+" 列 is Boolean type");
                            value = cell.getBooleanCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            System.out.println(i+"行"+j+" 列 is Blank type");
                            value = "";
                            break;
                        default:
                            System.out.println(i+"行"+j+" 列 is default type");
                            value = cell.toString();
                    }
                    if (value == null || "".equals(value)) {
                        continue;
                    }
                    linked.add(value);

                }
                list.add(linked);
            }

            return list;
        }
        /**
         * 读取Office 2007 excel
         * */
        private List<List<Object>> read2007Excel(File file) throws IOException {

            List<List<Object>> list = new LinkedList<List<Object>>();
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
            // 读取第一章表格内容
            XSSFSheet sheet = xwb.getSheetAt(0);
            Object value = null;
            XSSFRow row = null;
            XSSFCell cell = null;
            for (int i = sheet.getFirstRowNum(); i <= sheet
                    .getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                List<Object> linked = new LinkedList<Object>();
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    DecimalFormat df = new DecimalFormat("0");// 格式化 number String 字符
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                    DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字

                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            System.out.println(i+"行"+j+" 列 is String type");
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            System.out.println(i+"行"+j+" 列 is Number type ; DateFormt:"+cell.getCellStyle().getDataFormatString());
                            if("@".equals(cell.getCellStyle().getDataFormatString())){
                                value = df.format(cell.getNumericCellValue());
                            } else if("General".equals(cell.getCellStyle().getDataFormatString())){
                                value = nf.format(cell.getNumericCellValue());
                            }else{
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.println(i+"行"+j+" 列 is Boolean type");
                            value = cell.getBooleanCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            System.out.println(i+"行"+j+" 列 is Blank type");
                            value = "";
                            break;
                        default:
                            System.out.println(i+"行"+j+" 列 is default type");
                            value = cell.toString();
                    }
                    if (value == null || "".equals(value)) {
                        continue;
                    }
                    linked.add(value);
                }
                list.add(linked);
            }
            return list;
        }
    public String onCreateNewRecord() {
        int tmp = 1;
        tmp++;
        String str = tmp + "";
        try {
            m8401.setSETSEQ(str);
            m8401.setVCHSET(vchset);
            SOFForm form = dataExchangeService.callSbsTxn("8401", m8401).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                //MessageUtil.addInfo("传票录入成功：");

            } else if ("M402".equalsIgnoreCase(formcode)) {
                pub.tools.MessageUtil.addWarnWithClientID("msgs", formcode);
            } else {
                pub.tools.MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8401传票录入失败", e);
            pub.tools.MessageUtil.addError("8401传票录入失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
//========================================================================

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public M8401 getM8401() {
        return m8401;
    }

    public void setM8401(M8401 m8401) {
        this.m8401 = m8401;
    }

    public String getVchset() {
        return vchset;
    }

    public void setVchset(String vchset) {
        this.vchset = vchset;
    }
}
