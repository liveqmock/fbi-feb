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
 * User: Lichao.W             �޸���һ��iTextAsian��jar��
 * Date: 14-3-17              �ͻ�ģ���ӡ
 * Time: ����9:45
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TemPrintService {

    private  String fileName ;
    public void printVchpenAct( String orgidt, String deptid, String actnum,
                               String actnam, String opndat, String teller) throws IOException, DocumentException {
        fileName =  TemPrintService.class.getClassLoader().getResource("feb/PdfTemplates/cusOpnTemp.pdf").getPath();
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
    //�رտͻ���ӡ
    public void printVchclsCus(String orgidt, String depnum, String cusidt,
                               String actnam, String opndat,String clsdat, String teller) throws IOException, DocumentException {
        fileName = TemPrintService.class.getClassLoader().getResource("feb/PdfTemplates/cusCloseTemp.pdf").getPath();
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

    //����
    public void printOpnAct( String orgidt, String deptid, String actnum,
                                String actnam, String opndat, String teller) throws IOException, DocumentException {
        fileName =  TemPrintService.class.getClassLoader().getResource("feb/PdfTemplates/actOpnTemp.pdf").getPath();
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

    //�ػ�
    public void printClsAct( String orgidt, String deptid, String actnum,
                                String actnam, String opndat,String clsdat , String teller) throws IOException, DocumentException {
        fileName =  TemPrintService.class.getClassLoader().getResource("feb/PdfTemplates/actClsTemp.pdf").getPath();
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
    private void printTempPdf(ByteArrayOutputStream baos) throws IOException, DocumentException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletResponse resp = (HttpServletResponse) ctx.getExternalContext().getResponse();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        writer.setPageEvent(new PdfPageEventHelper());
        document.open();
        writer.addJavaScript("this.print({bUI: false,bSilent: true,bShrinkToFit: false});" + "\r\nthis.closeDoc();");
        //document.close();     //�رջر�nopage����
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
