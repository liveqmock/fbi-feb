package feb.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

//import com.lowagie.tools.Executable;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-17
 * Time: 上午9:45
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RePrintService {

    private  String fileName ;

    public void printVchpenAct(String reptyp,String orgid,String teller) throws IOException, DocumentException {
        fileName =  RePrintService.class.getClassLoader().getResource("feb/resultsPdf/"+reptyp+orgid+teller+".pdf").getPath();
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, baos);
        ps.setFormFlattening(true);
        ps.close();
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletResponse resp = (HttpServletResponse) ctx.getExternalContext().getResponse();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        writer.setPageEvent(new PdfPageEventHelper());
        document.open();
        writer.addJavaScript("this.print({bUI: false,bSilent: true,bShrinkToFit: false});" + "\r\nthis.closeDoc();");
        //document.close();     //关闭回报nopage错误
        resp.reset();
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/pdf");
        resp.setHeader("Content-disposition", "inline");
        resp.setContentLength(baos.size());
        resp.setHeader("Cache-Control", "max-age=30");
        baos.writeTo(out);
        out.flush();
        out.close();
        ctx.responseComplete();

    }
}
