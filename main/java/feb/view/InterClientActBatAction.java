package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T101;
import gateway.sbs.txn.model.msg.M8104;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-2-10
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class InterClientActBatAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(InterClientActBatAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    private String tellerid;
    private M8104 m8104 = new M8104();   // 内部账户
    private T101 t101;                   // 开关户返回信息
    private UploadedFile file;  //客户文件
    private List<M8104> m8104errs;//批量创建内部账户失败集合
    private List<T101> t101s;//批量创建内部账户信息成功集合

    @PostConstruct
    public void init() {
        tellerid = SkylineService.getOperId();
    }

    public String onBtnImpClick() {
        if (file == null) {
            MessageUtil.addError("请选择文件.");
            return null;
        }
        if (!file.getFileName().endsWith(".xls") && !file.getFileName().endsWith(".xlsx")) {
            pub.tools.MessageUtil.addInfo("请选择Excel文件!");
            return null;
        }
        try {
            //String fileName = filepath.split("/")[filepath.split("/").length - 1];
            String fileName = file.getFileName();
            String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
            if ("xls".equals(extension)) {
                read2003Excel(file);
            } else if ("xlsx".equals(extension)) {
                read2007Excel(file);
            } else {
                throw new IOException("不支持的文件类型");
            }
        } catch (Exception ex) {
            MessageUtil.addError("导入失败.");
        }
        return null;
    }

    /**
     * 读取 office 2003 excel
     *
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public void read2003Excel(UploadedFile file) throws IOException {
        t101s = new ArrayList<T101>();
        m8104errs = new ArrayList<M8104>();
        try {
            InputStream is = file.getInputstream();
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            int rowLen = sheet.getLastRowNum();
            HSSFCell cell;
            String tmp = "";
            for (int i = 1; i <= rowLen; i++) {
                m8104 = new M8104();
                //int cellNum = sheet.getRow(i).getLastCellNum();
                for (int j = 0; j < 16; j++) {
                    cell = sheet.getRow(i).getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == 1) {
                            tmp = cell.getStringCellValue().trim();
                        } else if (cell.getCellType() == 0) {
                            tmp = NumberFormat.getNumberInstance().format(cell.getNumericCellValue()).replaceAll(",", "");
                        }
                    } else break;
                    switch (j) {
                        case 0:
                            m8104.setACTNUM(tmp);
                            break;
                        case 1:
                            m8104.setACTNAM(tmp);
                            break;
                        case 2:
                            m8104.setCINRAT(tmp);
                            break;
                        case 3:
                            m8104.setACTTYP(tmp);
                            break;
                        case 4:
                            m8104.setINTFLG(tmp);
                            break;
                        case 5:
                            m8104.setINTCYC(tmp);
                            break;
                        case 6:
                            m8104.setINTTRA(tmp);
                            break;
                        case 7:
                            m8104.setDINRAT(tmp);
                            break;
                        case 8:
                            m8104.setDRATSF(tmp);
                            break;
                        case 9:
                            m8104.setCRATSF(tmp);
                            break;
                        case 10:
                            m8104.setLEGCYC(tmp);
                            break;
                        case 11:
                            m8104.setLEGCDT(tmp);
                            break;
                        case 12:
                            m8104.setLEGFMT(tmp);
                            break;
                        case 13:
                            m8104.setLEGADD(tmp);
                            break;
                        case 14:
                            m8104.setLEGSHT(tmp);
                            break;
                        case 15:
                            m8104.setLEGDEP(tmp);
                            break;
                        default:
                            break;
                    }
                }
                onCreateInternalAct();
            }
        } catch (IOException e) {
            pub.tools.MessageUtil.addError("导入文件出现错误" + e.getMessage());
        }
        pub.tools.MessageUtil.addInfo("导入结束！");
    }

    /**
     * 读取Office 2007 excel
     */
    public void read2007Excel(UploadedFile file) throws IOException {
        t101s = new ArrayList<T101>();
        m8104errs = new ArrayList<M8104>();
        try {
            InputStream input = file.getInputstream();
            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowLen = sheet.getLastRowNum();
            XSSFCell cell;
            String tmp = "";
            for (int i = 1; i <= rowLen; i++) {
                m8104 = new M8104();
                //int cellNum = sheet.getRow(i).getLastCellNum();
                for (int j = 0; j < 16; j++) {
                    cell = sheet.getRow(i).getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == 1) {
                            tmp = cell.getStringCellValue();
                        } else if (cell.getCellType() == 0) {
                            tmp = NumberFormat.getNumberInstance().format(cell.getNumericCellValue()).replaceAll(",", "");
                        }
                    } else break;
                    switch (j) {
                        case 0:
                            m8104.setACTNUM(tmp);
                            break;
                        case 1:
                            m8104.setACTNAM(tmp);
                            break;
                        case 2:
                            m8104.setCINRAT(tmp);
                            break;
                        case 3:
                            m8104.setACTTYP(tmp);
                            break;
                        case 4:
                            m8104.setINTFLG(tmp);
                            break;
                        case 5:
                            m8104.setINTCYC(tmp);
                            break;
                        case 6:
                            m8104.setINTTRA(tmp);
                            break;
                        case 7:
                            m8104.setDINRAT(tmp);
                            break;
                        case 8:
                            m8104.setDRATSF(tmp);
                            break;
                        case 9:
                            m8104.setCRATSF(tmp);
                            break;
                        case 10:
                            m8104.setLEGCYC(tmp);
                            break;
                        case 11:
                            m8104.setLEGCDT(tmp);
                            break;
                        case 12:
                            m8104.setLEGFMT(tmp);
                            break;
                        case 13:
                            m8104.setLEGADD(tmp);
                            break;
                        case 14:
                            m8104.setLEGSHT(tmp);
                            break;
                        case 15:
                            m8104.setLEGDEP(tmp);
                            break;
                        default:
                            break;
                    }
                }
                onCreateInternalAct();
            }
        } catch (IOException e) {
            pub.tools.MessageUtil.addError("导入文件出现错误" + e.getMessage());
        }
        pub.tools.MessageUtil.addInfo("导入结束！");
    }

    public String onCreateInternalAct() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8104", m8104).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                t101 = (T101) form.getFormBody();
                t101s.add(t101);
            } else {
                m8104errs.add(m8104);
                pub.tools.MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("8104内部账户开户失败", e);
            pub.tools.MessageUtil.addError("8104内部账户开户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //======================================================================================
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public M8104 getM8104() {
        return m8104;
    }

    public void setM8104(M8104 m8104) {
        this.m8104 = m8104;
    }

    public T101 getT101() {
        return t101;
    }

    public void setT101(T101 t101) {
        this.t101 = t101;
    }

    public List<M8104> getM8104errs() {
        return m8104errs;
    }

    public void setM8104errs(List<M8104> m8104errs) {
        this.m8104errs = m8104errs;
    }

    public List<T101> getT101s() {
        return t101s;
    }

    public void setT101s(List<T101> t101s) {
        this.t101s = t101s;
    }
}
