package feb.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-4-29
 * Time: ÉÏÎç10:13
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TemRosPrintService {

    private  String fileName ;

    public void printRosdn(String title,String txncde,String teller,String txndat,String iptac,
                           String advdat,String actnam,String intcur,String txnamt,
                           String advnum,String remark) throws IOException, DocumentException {
        fileName =  TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/rosTemp.pdf").getPath();
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, baos);
        AcroFields fields = ps.getAcroFields();
        fields.setField("title", title);
        fields.setField("txncde", txncde);
        fields.setField("teller", teller);
        fields.setField("txndat", txndat);
        fields.setField("iptac", iptac);
        fields.setField("advdat", advdat);
        fields.setField("actnam", actnam);
        fields.setField("intcur", intcur);
        fields.setField("txnamt", txnamt);
        fields.setField("advnum", advnum);
        fields.setField("remark", remark);
        ps.setFormFlattening(true);
        ps.close();
        printTempPdf(baos);

    }

    public void printRosdp( String title,String txncde,String teller,String txndat,String iptac,
                            String advdat,String actnam,String intcur,String txnamt,
                            String advnum,String remark) throws IOException, DocumentException {
        fileName =  TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/rosTemp.pdf").getPath();
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, baos);
        AcroFields fields = ps.getAcroFields();
        fields.setField("title", title);
        fields.setField("txncde", txncde);
        fields.setField("teller", teller);
        fields.setField("txndat", txndat);
        fields.setField("iptac", iptac);
        fields.setField("advdat", advdat);
        fields.setField("actnam", actnam);
        fields.setField("intcur", intcur);
        fields.setField("txnamt", txnamt);
        fields.setField("advnum", advnum);
        fields.setField("remark", remark);
        ps.setFormFlattening(true);
        ps.close();
        printTempPdf(baos);

    }
    private void printTempPdf(ByteArrayOutputStream baos) throws IOException, DocumentException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletResponse resp = (HttpServletResponse) ctx.getExternalContext().getResponse();
        Document document = new Document();
        PdfCopy pdfCopy = new PdfCopy(document, baos);
        document.open();
        PdfImportedPage impPage = null;
        impPage = pdfCopy.getImportedPage(new PdfReader(baos
                .toByteArray()), 1);
        pdfCopy.addPage(impPage);
        pdfCopy.addJavaScript("this.print({bUI: false,bSilent: true,bShrinkToFit: false});" + "\r\nthis.closeDoc();");
        document.close();
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
