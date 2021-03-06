package feb.view;

import feb.service.DataExchangeService;
import feb.service.TemPrintService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T001;
import gateway.sbs.txn.model.form.ac.T003;
import gateway.sbs.txn.model.form.ac.T004;
import gateway.sbs.txn.model.form.ac.T224;
import gateway.sbs.txn.model.msg.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.advance.utils.MessagePropertyManager;
import pub.tools.BeanHelper;
import pub.tools.MessageUtil;
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
 * Date: 13-9-12
 * Time: 下午10:16
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ClientAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ClientAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;

    @ManagedProperty(value = "#{temPrintService}")
    private TemPrintService temPrintService;

    private String inpflg = "";
    private String cusidt = "";
    private String cusnam = "";
    private String pastyp = "";
    private String passno = "";
    private String cusidx = "";
    private String legbdy = "";
    private String relcus = "";
    private String action = "";
    private M8002 m8002 = new M8002();          //查询客户请求报文
    private M8004 m8004 = new M8004();          //修改客户
    private M8001 m8001 = new M8001();          //对公账户开户
    private T001 t001;                          //创建账户响应报文 与关闭账户查询公用
    private T224 t224;
    private T003 t003 = new T003();
    private T004 t004 = new T004();             //对公客户单笔查询
    private boolean closeable = false;         // 是否可关户
    private boolean updateable = false;        // 是否可修改
    private boolean isPrintable = false;               // 是否可打印凭证
    private List<T003.Bean> dataList = new ArrayList<>();
    private List<T003.Bean> tmpList = new ArrayList<>();
    private String tellerid;                    //柜员号
    private String srcpage;
    private UploadedFile custFile;  //客户文件
    private List<M8001> m8001errs;//批量导入客户信息失败集合
    private List<T001> t001s;//批量导入客户信息成功集合

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        cusidt = StringUtils.isEmpty(params.get("cusidt")) ? "" : params.get("cusidt");
        cusnam = StringUtils.isEmpty(params.get("cusnam")) ? "" : params.get("cusnam");
        srcpage = params.get("srcpage");
        action = StringUtils.isEmpty(params.get("action")) ? "" : params.get("action");
        tellerid = SkylineService.getOperId();
        if ("detail".equals(action)) onQryCus();
        if ("query".equals(action)) onAllQuery();
    }

    public String onCreate() {
        try {
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8001", m8001);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if (forms.size() == 2) {
                    if ("T001".equalsIgnoreCase(formcode)) {
                        t001 = (T001) form.getFormBody();
                        isPrintable = true;
                        MessageUtil.addInfo("客户信息建立成功，名称：" + m8001.getCUSNAM());
                    }
                } else if ("T001".equalsIgnoreCase(formcode)) {
                    t001 = (T001) form.getFormBody();
                    m8001.setFUNCDE("Y");
                    MessageUtil.addWarn("该客户已存在,如需继续创建请单击确认按钮、");
                } else {
                    MessageUtil.addErrorWithClientID("msgs", formcode);
                }
            }
        } catch (Exception e) {
            logger.error("8001客户信息建立失败", e);
            MessageUtil.addError("8001客户信息建立失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public void onTempPrintOpen() {
        try {
            temPrintService.printVchpenAct(
                    t001.getORGIDT(), t001.getDEPNUM(), t001.getCUSIDT(),
                    t001.getCUSNAM(), t001.getOPNDAT(),tellerid);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }
    public void onTempPrintClose() {
        try {
            temPrintService.printVchclsCus(
                    t001.getORGIDT(), t001.getDEPNUM(), t001.getCUSIDT(),
                    t001.getCUSNAM(), t001.getOPNDAT(),t001.getCLSDAT(),tellerid);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
    }


    public String onQryCus() {
        try {
            m8002 = new M8002(cusidt);
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8002", m8002);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T004".equalsIgnoreCase(formcode)) {
                    t004 = (T004) form.getFormBody();
                    BeanHelper.copyFields(t004, m8004);
                    updateable = true;
                    closeable = true;
                } else {
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        } catch (Exception e) {
            logger.error("查询客户失败", e);
            MessageUtil.addError("查询客户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //浏览查询
    public String onAllQuery() {
        try {
            m8002 = new M8002(cusidt, cusnam, pastyp, passno, cusidx, legbdy, relcus);
            if (!cusnam.equals("") && passno.equals("") && pastyp.equals("") &&
                    cusidx.equals("") && legbdy.equals("") && relcus.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("6");
            } else if (!pastyp.equals("") && !passno.equals("") && cusnam.equals("") &&
                    cusidx.equals("") && legbdy.equals("") && relcus.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("2");
            } else if (!cusidx.equals("") && cusnam.equals("") && passno.equals("") &&
                    pastyp.equals("") && legbdy.equals("") && relcus.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("3");
            } else if (!legbdy.equals("") && cusnam.equals("") && passno.equals("") &&
                    pastyp.equals("") && cusidx.equals("") && relcus.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("4");
            } else if (!relcus.equals("") && cusnam.equals("") && passno.equals("") &&
                    pastyp.equals("") && cusidx.equals("") && legbdy.equals("") && cusidt.equals("")) {
                m8002.setINPFLG("5");
            } else if (!cusidt.equals("") && relcus.equals("") && cusnam.equals("") && passno.equals("") &&
                    pastyp.equals("") && cusidx.equals("") && legbdy.equals("")) {
                m8002.setINPFLG("1");
            } else {
                MessageUtil.addWarn("没有这种组合，请检查输入项。");
                return null;
            }
            List<SOFForm> formList = dataExchangeService.callSbsTxn("8002", m8002);
            if (formList != null && !formList.isEmpty()) {
                dataList = new ArrayList<>();
                List list = new ArrayList();
                for (SOFForm form : formList) {
                    if ("T003".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T003 t003 = (T003) form.getFormBody();
                        dataList.addAll(t003.getBeanList());
                    } else if ("T004".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        T004 t004 = (T004) form.getFormBody();
                        list.add(t004);
                        dataList.addAll(list);
                    } else if ("W012".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                        //logger.info("查询完成");
                    } else {
                        logger.info(form.getFormHeader().getFormCode());
                        MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onClick() {
        return "clientBean";
    }

    public String onBack() {
        return srcpage + "?faces-redirect=true&action=query&cusidt=" + cusidt;
        //return "clientAllQry?faces-redirect=true&action=query&pastyp=" + t004.getPASTYP()+"&passno=" +t004.getPASSNO();
    }

    public String onClose() {
        try {
            M8003 m8003 = new M8003(m8002.getCUSIDT());
            SOFForm form = dataExchangeService.callSbsTxn("8003", m8003).get(0);
            String formcode = form.getFormHeader().getFormCode();
            if ("T001".equalsIgnoreCase(formcode)) {
                t001 = (T001) form.getFormBody();
                closeable = false;
                isPrintable = true;
                // TODO 打印
                MessageUtil.addInfo("客户账户关闭成功，客户号：" + t001.getCUSIDT());
            } else {
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("关闭客户失败", e);
            MessageUtil.addError("关闭客户失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;

    }

    public String onUpdate() {
        try {
            String tmp = m8004.getLOCCAP();
            tmp.replace(",","");  //金额不为空，上传的时候有符号
            m8004.setLOCCAP(tmp);
            List<SOFForm> forms = dataExchangeService.callSbsTxn("8004", m8004);
            for (SOFForm form : forms) {
                String formcode = form.getFormHeader().getFormCode();
                if ("T004".equalsIgnoreCase(formcode)) {
                    t004 = (T004) form.getFormBody();
                    updateable = false;
                    MessageUtil.addInfo("客户信息修改成功，客户号：" + t004.getCUSIDT());
                } else {
                    logger.info(formcode);
                    MessageUtil.addErrorWithClientID("msgs", formcode);
                }
            }
        } catch (Exception e) {
            logger.error("客户信息修改失败", e);
            MessageUtil.addError("客户信息修改失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    /**
     * 批量建立客户
     * author:nanmeiying
     */
    public void batImport() {
        t001s = new ArrayList<T001>();
        m8001errs = new ArrayList<M8001>();
        if (custFile == null) {
            MessageUtil.addInfo("文件不得为空!");
            return;
        }
        if (!custFile.getFileName().endsWith(".xls") && !custFile.getFileName().endsWith(".xlsx")) {
            MessageUtil.addInfo("请选择Excel文件!");
            return;
        }
        try {
            InputStream is = custFile.getInputstream();
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            int rowLen = sheet.getLastRowNum();
            HSSFCell cell;
            M8001 m8001Tmp;
            T001 t001Tmp;
            String tmp = "";
            for (int i = 1; i <= rowLen; i++) {
                m8001Tmp = new M8001();
                int cellNum = sheet.getRow(i).getLastCellNum();
                for (int j = 0; j < cellNum; j++) {
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
                            m8001Tmp.setCUSNAM(tmp);
                            break;
                        case 1:
                            m8001Tmp.setSHTNAM(tmp);
                            break;
                        case 2:
                            m8001Tmp.setCORADD(tmp);
                            break;
                        case 3:
                            m8001Tmp.setZIPCDE(tmp);
                            break;
                        case 4:
                            m8001Tmp.setBUSCDE(tmp);
                            break;
                        case 5:
                            m8001Tmp.setENTCDE(tmp);
                            break;
                        case 6:
                            m8001Tmp.setPASTYP(tmp);
                            break;
                        case 7:
                            m8001Tmp.setPASSNO(tmp);
                            break;
                        case 8:
                            m8001Tmp.setTELNUM(tmp);
                            break;
                        case 9:
                            m8001Tmp.setRSDCTR(tmp);
                            break;
                        case 10:
                            m8001Tmp.setOPRCTR(tmp);
                            break;
                        case 11:
                            m8001Tmp.setENTTYP(tmp);
                            break;
                        case 12:
                            m8001Tmp.setCUSTY1(tmp);
                            break;
                        case 13:
                            m8001Tmp.setBOCGRP(tmp);
                            break;
                        case 14:
                            m8001Tmp.setTSTRNK(tmp);
                            break;
                        case 15:
                            m8001Tmp.setCRDLIM(tmp);
                            break;
                        case 16:
                            m8001Tmp.setRSKGRP(tmp);
                            break;
                        case 17:
                            m8001Tmp.setRELCUS(tmp);
                            break;
                        case 18:
                            m8001Tmp.setCUSPWD(tmp);
                            break;
                        case 19:
                            m8001Tmp.setLEGBDY(tmp);
                            break;
                        case 20:
                            m8001Tmp.setACTBDY(tmp);
                            break;
                        case 21:
                            m8001Tmp.setLOCCAP(tmp);
                            break;
                        case 22:
                            m8001Tmp.setREGCAP(tmp);
                            break;
                        case 23:
                            m8001Tmp.setREGCCY(tmp);
                            break;
                        case 24:
                            m8001Tmp.setREGADD(tmp);
                            break;
                        case 25:
                            m8001Tmp.setREGDAT(tmp);
                            break;
                        case 26:
                            m8001Tmp.setEFFDUR(tmp);
                            break;
                        case 27:
                            m8001Tmp.setCTXNUM(tmp);
                            break;
                        case 28:
                            m8001Tmp.setLTXNUM(tmp);
                            break;
                        case 29:
                            m8001Tmp.setSUPDEP(tmp);
                            break;
                        default:
                            break;
                    }
                }
                List<SOFForm> forms = dataExchangeService.callSbsTxn("8001", m8001Tmp);
                for (SOFForm form : forms) {
                    String formcode = form.getFormHeader().getFormCode();
                    if (forms.size() == 2) {
                        if ("T001".equalsIgnoreCase(formcode)) {
                            t001Tmp = (T001) form.getFormBody();
                            t001s.add(t001Tmp);
                        }
                    } else if ("T001".equalsIgnoreCase(formcode)) {
                        m8001Tmp.setERROCDE("已存在！");
                        m8001errs.add(m8001Tmp);
                    } else {
                        String s = MessagePropertyManager.getProperty(formcode);
                        m8001Tmp.setERROCDE(formcode+s);
                        m8001errs.add(m8001Tmp);
                    }
                }
            }
        } catch (IOException e) {
            MessageUtil.addError("导入文件出现错误" + e.getMessage());
        }
    }

    //----------------------------------------------------------------

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }


    public M8002 getM8002() {
        return m8002;
    }

    public void setM8002(M8002 m8002) {
        this.m8002 = m8002;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }

    public M8001 getM8001() {
        return m8001;
    }

    public void setM8001(M8001 m8001) {
        this.m8001 = m8001;
    }

    public T001 getT001() {
        return t001;
    }

    public void setT001(T001 t001) {
        this.t001 = t001;
    }

    public String getCusidt() {
        return cusidt;
    }

    public void setCusidt(String cusidt) {
        this.cusidt = cusidt;
    }

    public M8004 getM8004() {
        return m8004;
    }

    public void setM8004(M8004 m8004) {
        this.m8004 = m8004;
    }

    public T004 getT004() {
        return t004;
    }

    public void setT004(T004 t004) {
        this.t004 = t004;
    }

    public List<T003.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T003.Bean> dataList) {
        this.dataList = dataList;
    }

    public String getCusnam() {
        return cusnam;
    }

    public void setCusnam(String cusnam) {
        this.cusnam = cusnam;
    }

    /*public boolean isPrintable() {
        return isPrintable;
    }

    public void setPrintable(boolean printable) {
        isPrintable = printable;
    }*/

    public T003 getT003() {
        return t003;
    }

    public void setT003(T003 t003) {
        this.t003 = t003;
    }

    public String getPastyp() {
        return pastyp;
    }

    public void setPastyp(String pastyp) {
        this.pastyp = pastyp;
    }

    public String getPassno() {
        return passno;
    }

    public void setPassno(String passno) {
        this.passno = passno;
    }

    public String getCusidx() {
        return cusidx;
    }

    public void setCusidx(String cusidx) {
        this.cusidx = cusidx;
    }

    public String getLegbdy() {
        return legbdy;
    }

    public void setLegbdy(String legbdy) {
        this.legbdy = legbdy;
    }

    public String getRelcus() {
        return relcus;
    }

    public void setRelcus(String relcus) {
        this.relcus = relcus;
    }

    public String getInpflg() {
        return inpflg;
    }

    public void setInpflg(String inpflg) {
        this.inpflg = inpflg;
    }

    public T224 getT224() {
        return t224;
    }

    public void setT224(T224 t224) {
        this.t224 = t224;
    }

    public boolean isPrintable() {
        return isPrintable;
    }

    public void setPrintable(boolean printable) {
        isPrintable = printable;
    }

    public String getSrcpage() {
        return srcpage;
    }

    public void setSrcpage(String srcpage) {
        this.srcpage = srcpage;
    }

    public UploadedFile getCustFile() {
        return custFile;
    }

    public void setCustFile(UploadedFile custFile) {
        this.custFile = custFile;
    }

    public List<T001> getT001s() {
        return t001s;
    }

    public void setT001s(List<T001> t001s) {
        this.t001s = t001s;
    }

    public List<M8001> getM8001errs() {
        return m8001errs;
    }

    public void setM8001errs(List<M8001> m8001errs) {
        this.m8001errs = m8001errs;
    }

    public TemPrintService getTemPrintService() {
        return temPrintService;
    }

    public void setTemPrintService(TemPrintService temPrintService) {
        this.temPrintService = temPrintService;
    }
}
