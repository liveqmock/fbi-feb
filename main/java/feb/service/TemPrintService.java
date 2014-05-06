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

//import com.lowagie.tools.Executable;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W             修改了一个iTextAsian的jar包
 * Date: 14-3-17              客户模板打印
 * Time: 上午9:45
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TemPrintService {

    private  String fileName ;
    public void printVchpenAct( String orgidt, String deptid, String actnum,
                               String actnam, String opndat, String teller) throws IOException, DocumentException {
        fileName =  TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/cusOpnTemp.pdf").getPath();
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, baos);
        AcroFields fields = ps.getAcroFields();
        fields.setField("jid", orgidt);
        fields.setField("bid", deptid);
        fields.setField("id", actnum);
        fields.setField("name", actnam);
        fields.setField("date", opndat);
        fields.setField("pl", teller);
        ps.setFormFlattening(true);
        ps.close();
        printTempPdf(baos);

    }
    //关闭客户打印
    public void printVchclsCus(String orgidt, String depnum, String cusidt,
                               String actnam, String opndat,String clsdat, String teller) throws IOException, DocumentException {
        fileName = TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/cusCloseTemp.pdf").getPath();
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, baos);
        AcroFields fields = ps.getAcroFields();
        fields.setField("orgidt", orgidt);
        fields.setField("depnum", depnum);
        fields.setField("cusidt", cusidt);
        fields.setField("cusnam", actnam);
        fields.setField("opndat", opndat);
        fields.setField("clsdat", clsdat);
        fields.setField("amdtlr", teller);
        ps.setFormFlattening(true);
        ps.close();
        printTempPdf(baos);

    }

    //开户
    public void printOpnAct( String orgidt, String deptid, String actnum,
                                String actnam, String opndat, String teller) throws IOException, DocumentException {
        fileName =  TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/actOpnTemp.pdf").getPath();
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, baos);
        AcroFields fields = ps.getAcroFields();
        fields.setField("orgidt", orgidt);
        fields.setField("depnum", deptid);
        fields.setField("actnum", actnum);
        fields.setField("actnam", actnam);
        fields.setField("opndat", opndat);
        fields.setField("amdtlr", teller);
        ps.setFormFlattening(true);
        ps.close();
        printTempPdf(baos);

    }

    //关户
    public void printClsAct( String orgidt, String deptid, String actnum,
                                String actnam, String opndat,String clsdat , String teller) throws IOException, DocumentException {
        fileName =  TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/actClsTemp.pdf").getPath();
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, baos);
        AcroFields fields = ps.getAcroFields();
        fields.setField("orgidt", orgidt);
        fields.setField("depnum", deptid);
        fields.setField("actnum", actnum);
        fields.setField("actnam", actnam);
        fields.setField("opndat", opndat);
        fields.setField("clsdat", clsdat);
        fields.setField("amdtlr", teller);
        ps.setFormFlattening(true);
        ps.close();
        printTempPdf(baos);

    }

    /**
     * 活期清单打印
     */
    public void printCurrInv(String orgidt,String actnum,String actnam,
            String avabal,String clsdat,String lintdt,String craccm1,String cratsf1,String cacint1,
            String craccm2,String cratsf2,String cacint2,
            String craccm3,String cratsf3,String cacint3,String cacint,String amount) throws IOException, DocumentException {

        fileName =  TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/curInvTemp.pdf").getPath();
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, baos);
        AcroFields fields = ps.getAcroFields();
        fields.setField("orgidt", orgidt);
        fields.setField("actnum", actnum);
        fields.setField("actnam", actnam);
        fields.setField("avabal", avabal);
        fields.setField("clsdat", clsdat);
        fields.setField("lintdt", lintdt);
        fields.setField("craccm1", craccm1);
        fields.setField("cratsf1", cratsf1);
        fields.setField("cacint1", cacint1);
        fields.setField("craccm2", craccm2);
        fields.setField("cratsf2", cratsf2);
        fields.setField("cacint2", cacint2);
        fields.setField("craccm3", craccm3);
        fields.setField("cratsf3", cratsf3);
        fields.setField("cacint3", cacint3);
        fields.setField("cacint", cacint);
        fields.setField("amount", amount);
        ps.setFormFlattening(true);
        ps.close();
        printTempPdf(baos);

    }

    private void printTempPdf(ByteArrayOutputStream baos) throws IOException, DocumentException {
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
