package feb.view;

import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T898;
import gateway.sbs.txn.model.msg.M8401;
import gateway.sbs.txn.model.msg.M85a2;
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
import org.springframework.util.StringUtils;
import pub.platform.MessageUtil;
import pub.platform.advance.utils.MessagePropertyManager;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private T898 t898 = new T898();
    private List<T898.Bean> dataList = new ArrayList<>();
    private List<T898.Bean> allList = new ArrayList<>();
    private List<M8401> errorList = new ArrayList<>();
    private UploadedFile file;
    private String vchset;
    private String tlrnum;
    private String totnum;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        vchset = StringUtils.isEmpty(params.get("vchset")) ? "0000" : params.get("vchset");
        tlrnum = SkylineService.getOperId();//============>得到当前柜员号
        onBatchQry();
    }

    public String onBatchQry() {
        try {
            M85a2 m85a2 = new M85a2("0000");
            List<SOFForm> formList = dataExchangeService.callSbsTxn("85a2", m85a2);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                allList = new ArrayList<>();
                for (SOFForm form : formList) {
                    if ("T898".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        t898 = (T898) form.getFormBody();
                        allList.addAll(t898.getBeanList());
                        if (!allList.get(0).getSETSEQ().equals("")) {
                            for (T898.Bean bean : allList) {
                                if (!bean.getRECSTS().equals("I")) {
                                    bean.setTMPAMT(new BigDecimal(bean.getTXNAMT()));
                                    /*DecimalFormat df = new DecimalFormat("###,###,##0.00");
                                    bean.setTXNAMT(df.format(new BigDecimal(bean.getTXNAMT())));*/
                                    dataList.add(bean);
                                }
                            }
                        }
                        //tlrnum = t898.getFormBodyHeader().getTLRNUM();//柜员号
                        vchset = t898.getFormBodyHeader().getVCHSET();
                        totnum = t898.getFormBodyHeader().getTOTNUM();//总笔数
                    } else if ("M922".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        pub.tools.MessageUtil.addWarn("您无权限操作该交易 !");
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        pub.tools.MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            pub.tools.MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onBtnImpClick() {
        if(file == null){
            MessageUtil.addError("请选择文件.");
            return null;
        }
        try {
            //String filename = filepath.split("/")[filepath.split("/").length - 1];
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
        try {
            InputStream input = file.getInputstream();
            POIFSFileSystem fs = new POIFSFileSystem(input);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            int rowLen = sheet.getLastRowNum();
            HSSFCell cell;
            String tmp = "";
            for (int i = 1; i <= rowLen; i++) {
                m8401 = new M8401();
                int cellNum = sheet.getRow(i).getLastCellNum();
                for (int j = 0; j < cellNum; j++) {
                    cell = sheet.getRow(i).getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == 1) {
                            tmp = cell.getStringCellValue().trim();
                            if ("".equals(tmp)){
                                continue;
                            }
                        } else if (cell.getCellType() == 0) {
                            tmp = NumberFormat.getNumberInstance().format(cell.getNumericCellValue()).replaceAll(",", "");
                            if ("0".equals(tmp)){
                                continue;
                            }
                        }else if (cell.getCellType()==3){
                            continue;
                        }
                    } else continue;
                    switch (j) {
                        case 0:
                            m8401.setACTNUM(tmp);
                            break;
                        case 1:
                            m8401.setTXNAMT(tmp);
                            break;
                        case 2:
                            m8401.setFURINF(tmp);
                            break;
                        case 3:
                            m8401.setRVSLBL(tmp);
                            break;
                        case 4:
                            m8401.setOPNDA2(tmp);
                            break;
                        case 5:
                            m8401.setANACDE(tmp);
                            break;
                        default:
                            break;
                    }
                }
                onCreateNewRecord();
            }
        } catch (Exception ex) {
            MessageUtil.addError("录入内容异常.");
        }
        MessageUtil.addInfo("录入结束.");
    }

    /**
     * 读取Office 2007 excel
     */
    public String read2007Excel(UploadedFile file) throws IOException {
        try {
            InputStream input = file.getInputstream();
            //POIFSFileSystem fs = new POIFSFileSystem(input);
            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowLen = sheet.getLastRowNum();
            XSSFCell cell;
            String tmp = "";
            for (int i = 1; i <= rowLen; i++) {
                m8401 = new M8401();
                int cellNum = sheet.getRow(i).getLastCellNum();
                for (int j = 0; j < cellNum; j++) {
                    cell = sheet.getRow(i).getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == 1) {
                            tmp = cell.getStringCellValue().trim();
                            if ("".equals(tmp)){
                                continue;
                            }
                        } else if (cell.getCellType() == 0) {
                            tmp = NumberFormat.getNumberInstance().format(cell.getNumericCellValue()).replaceAll(",", "");
                            if ("0".equals(tmp)){
                                continue;
                            }
                        }else if (cell.getCellType()==3){
                            continue;
                        }
                    } else continue;
                    switch (j) {
                        case 0:
                            m8401.setACTNUM(tmp);
                            break;
                        case 1:
                            m8401.setTXNAMT(tmp);
                            break;
                        case 2:
                            m8401.setFURINF(tmp);
                            break;
                        case 3:
                            m8401.setRVSLBL(tmp);
                            break;
                        case 4:
                            m8401.setOPNDA2(tmp);
                            break;
                        case 5:
                            m8401.setANACDE(tmp);
                            break;
                        default:
                            break;
                    }
                }
                onCreateNewRecord();
            }
        } catch (Exception ex) {
            MessageUtil.addError("录入内容异常.");
        }
        MessageUtil.addInfo("录入结束.");
        return null;
    }

    public String onCreateNewRecord() {
        int tmp = Integer.parseInt(totnum) + 1;
        String str = tmp + "";
        //tmp++;
        try {
            m8401.setSETSEQ(str);
            m8401.setVCHSET(vchset);
            m8401.setTLRNUM(tlrnum);

            SOFForm form = dataExchangeService.callSbsTxn("8401", m8401).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("W001".equalsIgnoreCase(formcode)) {
                //MessageUtil.addInfo("传票录入成功：");
                onBatchQry();//查询一下totnum
            } else {
                errorList.add(m8401);
                m8401.setERRINF(formcode + MessagePropertyManager.getProperty(formcode));
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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

    public T898 getT898() {
        return t898;
    }

    public void setT898(T898 t898) {
        this.t898 = t898;
    }

    public List<T898.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T898.Bean> dataList) {
        this.dataList = dataList;
    }

    public List<T898.Bean> getAllList() {
        return allList;
    }

    public void setAllList(List<T898.Bean> allList) {
        this.allList = allList;
    }

    public List<M8401> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<M8401> errorList) {
        this.errorList = errorList;
    }

}
