package feb.view;

import feb.service.DataExchangeService;
import feb.service.TemPrintService;
import feb.service.TemVchPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T101;
import gateway.sbs.txn.model.msg.M8101;
import org.apache.commons.lang.StringUtils;
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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-2-10
 * Time: ����4:09
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ClientActBatAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientActBatAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    @ManagedProperty(value = "#{temPrintService}")
    private TemPrintService temPrintService;

    private String actnum;
    private String tellerid;
    private M8101 m8101 = new M8101();   // �˻�
    private T101 t101;                   // ����������Ϣ
    private UploadedFile file;  //�ͻ��ļ�
    private List<M8101> m8101errs;//���������˻�ʧ�ܼ���
    private List<T101> t101s;//���������˻���Ϣ�ɹ�����
    //private List<T101> selectedActs = new ArrayList<T101>();//����������ӡʱѡ���ӡ����

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        actnum = StringUtils.isEmpty(params.get("actnum")) ? "" : params.get("actnum");
        tellerid = SkylineService.getOperId();
    }

    public String onBtnImpClick() {
        if (file == null) {
            MessageUtil.addError("��ѡ���ļ�.");
            return null;
        }
        if (!file.getFileName().endsWith(".xls") && !file.getFileName().endsWith(".xlsx")) {
            pub.tools.MessageUtil.addInfo("��ѡ��Excel�ļ�!");
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
                throw new IOException("��֧�ֵ��ļ�����");
            }
        } catch (Exception ex) {
            MessageUtil.addError("����ʧ��.");
        }
        return null;
    }

    /**
     * ��ȡ office 2003 excel
     *
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public void read2003Excel(UploadedFile file) throws IOException {
        t101s = new ArrayList<T101>();
        m8101errs = new ArrayList<M8101>();
        try {
            InputStream is = file.getInputstream();
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            int rowLen = sheet.getLastRowNum();
            HSSFCell cell;
            String tmp = "";
            for (int i = 1; i <= rowLen; i++) {
                m8101 = new M8101();
                //int cellNum = sheet.getRow(i).getLastCellNum();
                for (int j = 0; j < 16; j++) {
                    cell = sheet.getRow(i).getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == 1) {
                            tmp = cell.getStringCellValue().trim();
                            if ("".equals(tmp)) {
                                continue;
                            }
                        } else if (cell.getCellType() == 0) {
                            tmp = NumberFormat.getNumberInstance().format(cell.getNumericCellValue()).replaceAll(",", "");
                            if ("0".equals(tmp)) {
                                continue;
                            }
                        } else if (cell.getCellType() == 3) {
                            continue;
                        }
                    } else continue;
                    switch (j) {
                        case 0:
                            m8101.setACTNUM(tmp);
                            break;
                        case 1:
                            m8101.setACTNAM(tmp);
                            break;
                        case 2:
                            m8101.setACTTYP(tmp);
                            break;
                        case 3:
                            m8101.setINTCYC(tmp);
                            break;
                        case 4:
                            m8101.setINTTRA(tmp);
                            break;
                        case 5:
                            m8101.setCQEFLG(tmp);
                            break;
                        case 6:
                            m8101.setSTMFMT(tmp);
                            break;
                        case 7:
                            m8101.setSTMDEP(tmp);
                            break;
                        case 8:
                            m8101.setSTMSHT(tmp);
                            break;
                        case 9:
                            m8101.setSTMCYC(tmp);
                            break;
                        case 10:
                            m8101.setSTMCDT(tmp);
                            break;
                        case 11:
                            m8101.setCINRAT(tmp);
                            break;
                        case 12:
                            m8101.setLEGZIP(tmp);
                            break;
                        case 13:
                            m8101.setSTMADD(tmp);
                            break;
                        case 14:
                            m8101.setSTMZIP(tmp);
                            break;
                        case 15:
                            m8101.setINTFLG(tmp);
                            break;
                        case 16:
                            m8101.setBALLIM(tmp);
                            break;
                        case 17:
                            m8101.setOVELIM(tmp);
                            break;
                        case 18:
                            m8101.setOVEEXP(tmp);
                            break;
                        case 19:
                            m8101.setDINRAT(tmp);
                            break;
                        case 20:
                            m8101.setDRATSF(tmp);
                            break;
                        case 21:
                            m8101.setCRATSF(tmp);
                            break;
                        case 22:
                            m8101.setLEGCYC(tmp);
                            break;
                        case 23:
                            m8101.setLEGCDT(tmp);
                            break;
                        case 24:
                            m8101.setLEGFMT(tmp);
                            break;
                        case 25:
                            m8101.setLEGADD(tmp);
                            break;
                        case 26:
                            m8101.setLEGSHT(tmp);
                            break;
                        case 27:
                            m8101.setLEGDEP(tmp);
                            break;
                        default:
                            break;
                    }
                }
                onCreateInternalAct();
            }
        } catch (IOException e) {
            pub.tools.MessageUtil.addError("�����ļ����ִ���" + e.getMessage());
        }
        pub.tools.MessageUtil.addInfo("���������");
    }

    /**
     * ��ȡOffice 2007 excel
     * <p/>
     * ԭpoi�����Զ�ȡ2007
     * ������Ҫ�����dom4j-1.6.1.jar  poi-ooxml-schemas-3.7-20101029.jar
     * xmlbeans-2.3.0.jar����jar��
     */
    public void read2007Excel(UploadedFile file) throws IOException {
        t101s = new ArrayList<T101>();
        m8101errs = new ArrayList<M8101>();
        try {
            InputStream input = file.getInputstream();
            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowLen = sheet.getLastRowNum();
            XSSFCell cell;
            String tmp = "";
            for (int i = 1; i <= rowLen; i++) {
                m8101 = new M8101();
                int cellNum = sheet.getRow(i).getLastCellNum();
                for (int j = 0; j < cellNum; j++) {
                    cell = sheet.getRow(i).getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == 1) {
                            tmp = cell.getStringCellValue();
                            if ("".equals(tmp)) {
                                continue;
                            }
                        } else if (cell.getCellType() == 0) {
                            tmp = NumberFormat.getNumberInstance().format(cell.getNumericCellValue()).replaceAll(",", "");
                            if ("0".equals(tmp)) {
                                continue;
                            }
                        } else if (cell.getCellType() == 3) {
                            continue;
                        }
                    } else continue;
                    switch (j) {
                        case 0:
                            m8101.setACTNUM(tmp);
                            break;
                        case 1:
                            m8101.setACTNAM(tmp);
                            break;
                        case 2:
                            m8101.setACTTYP(tmp);
                            break;
                        case 3:
                            m8101.setINTCYC(tmp);
                            break;
                        case 4:
                            m8101.setINTTRA(tmp);
                            break;
                        case 5:
                            m8101.setCQEFLG(tmp);
                            break;
                        case 6:
                            m8101.setSTMFMT(tmp);
                            break;
                        case 7:
                            m8101.setSTMDEP(tmp);
                            break;
                        case 8:
                            m8101.setSTMSHT(tmp);
                            break;
                        case 9:
                            m8101.setSTMCYC(tmp);
                            break;
                        case 10:
                            m8101.setSTMCDT(tmp);
                            break;
                        case 11:
                            m8101.setCINRAT(tmp);
                            break;
                        case 12:
                            m8101.setLEGZIP(tmp);
                            break;
                        case 13:
                            m8101.setSTMADD(tmp);
                            break;
                        case 14:
                            m8101.setSTMZIP(tmp);
                            break;
                        case 15:
                            m8101.setINTFLG(tmp);
                            break;
                        case 16:
                            m8101.setBALLIM(tmp);
                            break;
                        case 17:
                            m8101.setOVELIM(tmp);
                            break;
                        case 18:
                            m8101.setOVEEXP(tmp);
                            break;
                        case 19:
                            m8101.setDINRAT(tmp);
                            break;
                        case 20:
                            m8101.setDRATSF(tmp);
                            break;
                        case 21:
                            m8101.setCRATSF(tmp);
                            break;
                        case 22:
                            m8101.setLEGCYC(tmp);
                            break;
                        case 23:
                            m8101.setLEGCDT(tmp);
                            break;
                        case 24:
                            m8101.setLEGFMT(tmp);
                            break;
                        case 25:
                            m8101.setLEGADD(tmp);
                            break;
                        case 26:
                            m8101.setLEGSHT(tmp);
                            break;
                        case 27:
                            m8101.setLEGDEP(tmp);
                            break;
                        default:
                            break;
                    }
                }
                onCreateInternalAct();
            }
        } catch (IOException e) {
            pub.tools.MessageUtil.addError("�����ļ����ִ���" + e.getMessage());
        }
        pub.tools.MessageUtil.addInfo("���������");
    }

    public String onCreateInternalAct() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8101", m8101).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T101".equalsIgnoreCase(formcode)) {
                t101 = (T101) form.getFormBody();
                t101s.add(t101);
            } else {
                m8101errs.add(m8101);
                m8101.setERROCDE(formcode + MessagePropertyManager.getProperty(formcode));
            }
        } catch (Exception e) {
            logger.error("8101�˻�����ʧ��", e);
            MessageUtil.addError("8101�˻�����ʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    // ��ӡ����ȷ����
    public void onPrintOpenAct() {
        for (T101 bean : t101s) {
            try {
                temPrintService.printOpnAct(
                        bean.getORGIDT(), bean.getDEPNUM(), bean.getACTNUM(),
                        bean.getCUSNAM(), bean.getOPNDAT(), tellerid);
            } catch (Exception e) {
                logger.error("��ӡʧ��", e);
                pub.tools.MessageUtil.addError("��ӡʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
            }
        }
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


    public T101 getT101() {
        return t101;
    }

    public void setT101(T101 t101) {
        this.t101 = t101;
    }

    public M8101 getM8101() {
        return m8101;
    }

    public void setM8101(M8101 m8101) {
        this.m8101 = m8101;
    }

    public List<M8101> getM8101errs() {
        return m8101errs;
    }

    public void setM8101errs(List<M8101> m8101errs) {
        this.m8101errs = m8101errs;
    }

    public List<T101> getT101s() {
        return t101s;
    }

    public void setT101s(List<T101> t101s) {
        this.t101s = t101s;
    }

    public String getActnum() {
        return actnum;
    }

    public void setActnum(String actnum) {
        this.actnum = actnum;
    }

    public TemPrintService getTemPrintService() {
        return temPrintService;
    }

    public void setTemPrintService(TemPrintService temPrintService) {
        this.temPrintService = temPrintService;
    }
}
