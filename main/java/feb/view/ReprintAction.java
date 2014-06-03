package feb.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import feb.service.RePrintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-6-3
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ReprintAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ReprintAction.class);

    private String tellerid = "";
    private String fileName = "";
    @ManagedProperty(value = "#{rePrintService}")
    private RePrintService rePrintService;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        fileName = params.get("filename");
        tellerid = SkylineService.getOperId();
    }

    public String onPrint() {
        try {
            rePrintService.printVchpenAct(tellerid, fileName);
        } catch (Exception e) {
            logger.error("打印失败", e);
            MessageUtil.addError("打印失败." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }
   /* public String onQryVoV(){
        PdfReader reader = null;
        try {
            reader = new PdfReader(resultsPath+fileName+tellerid+".pdf");
            //reader = new PdfReader("d:/001.pdf");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper stamp = new PdfStamper(reader, bos);
            stamp.setFormFlattening(true);//必须要调用这个，否则文档不会生成的
            stamp.close();
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpServletResponse resp = (HttpServletResponse) ctx.getExternalContext().getResponse();
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, bos);
            writer.setPageEvent(new PdfPageEventHelper());
            document.open();
            writer.addJavaScript("this.print({bUI: false,bSilent: true,bShrinkToFit: false});" + "\r\nthis.closeDoc();");
            //document.close();     //关闭回报nopage错误
            resp.reset();
            ServletOutputStream out = resp.getOutputStream();
            resp.setContentType("application/pdf");
            resp.setHeader("Content-disposition", "inline");
            resp.setContentLength(bos.size());
            resp.setHeader("Cache-Control", "max-age=30");
            bos.writeTo(out);
            out.flush();
            out.close();
            ctx.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }*/


    //= = = = = = = = = = = = = = == = =  = = = =

    public String getTellerid() {
        return tellerid;
    }

    public void setTellerid(String tellerid) {
        this.tellerid = tellerid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public RePrintService getRePrintService() {
        return rePrintService;
    }

    public void setRePrintService(RePrintService rePrintService) {
        this.rePrintService = rePrintService;
    }

}
