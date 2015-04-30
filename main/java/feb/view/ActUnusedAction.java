package feb.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import feb.service.DataExchangeService;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T122;
import gateway.sbs.txn.model.msg.M8125;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.MessageUtil;
import pub.tools.JxlsManager;
import pub.tools.SystemDate;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lichao.W At 2015/4/20 14:26
 * wanglichao@163.com
 */
@ManagedBean
@ViewScoped
public class ActUnusedAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActUnusedAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private List<SelectItem> entInnerPapItems;      // 内转查询状态
    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;

    private M8125 m8125 = new M8125();
    private T122 t122 = new T122();
    private List<T122.Bean> dataList = new ArrayList<>();

    private T122.Bean[] selectedRecords;

    @PostConstruct
    public void init() {
        entInnerPapItems = skylineService.getEnuSelectItemList("CTF-ACTTYP-UN", false, false);
    }

    public String onActUnQry() {
        try {
            SOFForm form = dataExchangeService.callSbsTxn("8125", m8125).get(0);
            String formcode = form.getFormHeader().getFormCode();
            dataList = new ArrayList<>();
            if ("T122".equals(formcode)) {
                t122 = (T122) form.getFormBody();
                int i = Integer.parseInt(m8125.getBEGNUM());
                while (i<Integer.parseInt(t122.getTotcnt())){
                    m8125.setBEGNUM(i+"");
                    SOFForm form2 = dataExchangeService.callSbsTxn("8125", m8125).get(0);
                    String formcode2 = form2.getFormHeader().getFormCode();
                    if ("T122".equals(formcode2)){
                        t122 = (T122)form2.getFormBody();
                        dataList.addAll(t122.getBeanList());
                    }
                    i+=Integer.parseInt(t122.getCurcnt());
                }
            } else {
                logger.error(formcode);
                MessageUtil.addErrorWithClientID("msgs", formcode);
            }
        } catch (Exception e) {
            logger.error("查询失败", e);
            MessageUtil.addError("查询失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    //打印
    public String onAllConfirm() {
        selectedRecords = dataList.toArray(selectedRecords);
        onMultiConfirm();
        return null;
    }

    public String onMultiConfirm() {
        if (selectedRecords == null || selectedRecords.length == 0) {
            MessageUtil.addWarn("至少选择一笔数据记录！");
            return null;
        }
        try {
            String fileName = ActUnusedAction.class.getClassLoader().getResource("feb/pdfTemplates/actunClsTemp.pdf").getPath();
            //for (int item = 0; item < page; item++) {
            int item = 0;
            int page = selectedRecords.length;
            ByteArrayOutputStream baos[] = new ByteArrayOutputStream[page];
            for (T122.Bean bean : selectedRecords) {
                baos[item] = new ByteArrayOutputStream();
                PdfReader reader = new PdfReader(fileName);
                PdfStamper ps = new PdfStamper(reader, baos[item]);
                AcroFields fields = ps.getAcroFields();
                fields.setField("actnam", bean.getACTNAM());
                fields.setField("actnum", bean.getACTNUM());
                fields.setField("year", bean.getOPNDAT().substring(0,4));
                fields.setField("moth", bean.getOPNDAT().substring(5,7));
                fields.setField("day", bean.getOPNDAT().substring(8,10));
                ps.setFormFlattening(true);
                ps.close();
                item++;
            }
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpServletResponse resp = (HttpServletResponse) ctx.getExternalContext().getResponse();
            Document document = new Document();
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            PdfCopy pdfCopy = new PdfCopy(document, bao);
            document.open();
            PdfImportedPage impPage = null;
            /**取出之前保存的每页内容*/
            for (int i = 0; i < page; i++) {
                impPage = pdfCopy.getImportedPage(new PdfReader(baos[i]
                        .toByteArray()), 1);
                pdfCopy.addPage(impPage);
            }
            pdfCopy.addJavaScript("this.print({bUI: false,bSilent: true,bShrinkToFit: false});" + "\r\nthis.closeDoc();");
            document.close();
            resp.reset();
            ServletOutputStream out = resp.getOutputStream();
            resp.setContentType("application/pdf");
            resp.setHeader("Content-disposition", "inline");
            resp.setContentLength(bao.size());
            resp.setHeader("Cache-Control", "max-age=30");
            bao.writeTo(out);
            out.flush();
            out.close();
            ctx.responseComplete();
        } catch (Exception e) {
            logger.error("通知单打印失败", e);
            MessageUtil.addError("通知单打印失败");
        }
        return null;
    }


    //导出excel
    public String onExpExcel() {
        try {
            if (dataList == null || dataList.size() == 0) {
                MessageUtil.addWarn("未查询数据！");
                return null;
            }
            String expdat = new SystemDate().getSysdate1();
            Map beansMap = new HashMap();
            String excelFilename = "久悬户清单" + ".xls";
            JxlsManager jxls = new JxlsManager();
            beansMap.put("records", dataList);
            beansMap.put("expdat",expdat);
            jxls.exportDataToXls(excelFilename, "/actunCls.xls", beansMap);
        } catch (Exception e) {

        }
        return null;
    }

    // = = = = = = =  = get set  = = ==  = = = = =

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public M8125 getM8125() {
        return m8125;
    }

    public void setM8125(M8125 m8125) {
        this.m8125 = m8125;
    }

    public T122 getT122() {
        return t122;
    }

    public void setT122(T122 t122) {
        this.t122 = t122;
    }

    public List<T122.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T122.Bean> dataList) {
        this.dataList = dataList;
    }

    public List<SelectItem> getEntInnerPapItems() {
        return entInnerPapItems;
    }

    public void setEntInnerPapItems(List<SelectItem> entInnerPapItems) {
        this.entInnerPapItems = entInnerPapItems;
    }

    public SkylineService getSkylineService() {
        return skylineService;
    }

    public void setSkylineService(SkylineService skylineService) {
        this.skylineService = skylineService;
    }

    public T122.Bean[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(T122.Bean[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }
}
