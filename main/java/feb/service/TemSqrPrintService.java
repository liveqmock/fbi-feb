package feb.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import feb.print.model.Vch;
import gateway.sbs.txn.model.form.re.T130;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-4-18    定期结清打印
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TemSqrPrintService {

    private String fileName;
    private String fileName2;

    public void printVch(String title, String trncde, String tlrnum, String trntim,
                         String vchset, String trndat, List<Vch> vchList,
                         String title2, T130 t130) throws IOException, DocumentException {
        fileName = TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/invTemp.pdf").getPath();
        fileName2 = TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/t130Temp.pdf").getPath();
        PdfReader reader2 = new PdfReader(fileName2);
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        PdfStamper ps2 = new PdfStamper(reader2, baos2);
        AcroFields fields2 = ps2.getAcroFields();
        fields2.setField("title", title2);
        fields2.setField("txncde", t130.getTXNCDE());
        fields2.setField("teller", t130.getTELLER());
        fields2.setField("txndat", t130.getTXNDAT());
        fields2.setField("iptac", t130.getIPTAC());
        fields2.setField("valdat", t130.getVALDAT());
        fields2.setField("actnam", t130.getACTNAM());
        fields2.setField("txnamt", t130.getTXNAMT());
        fields2.setField("opnint", t130.getOPNIRT());
        fields2.setField("inint", t130.getININT());
        fields2.setField("pintax", t130.getP_INTAX());
        fields2.setField("uintax", t130.getU_INTAX());
        fields2.setField("savirt", t130.getSAVIRT());
        fields2.setField("outint", t130.getOUTINT());
        fields2.setField("pouttax", t130.getP_OUTTAX());
        fields2.setField("uouttax", t130.getU_OUTTAX());
        fields2.setField("valirt", t130.getVALIRT());
        fields2.setField("valint", t130.getVALINT());
        fields2.setField("pvaltax", t130.getP_VALTAX());
        fields2.setField("uvaltax", t130.getU_VALTAX());
        fields2.setField("taxrate", t130.getTAXRATE());
        fields2.setField("taxamt", t130.getTAXAMT());
        fields2.setField("feeamt", t130.getFEEAMT());
        fields2.setField("intcur", t130.getINTCUR());
        fields2.setField("totint", t130.getTOTINT());
        ps2.setFormFlattening(true);
        ps2.close();
        int count = vchList.size(); //  总行数
        int pageCount = 20;       // 每页记录数
        int page = 0;             // 总共页数
        /** 主要控制总共的页数*/
        if (count >= pageCount && count % pageCount == 0) {
            page = count / pageCount;
        } else {
            page = count / pageCount + 1;
        }
        ByteArrayOutputStream baos[] = new ByteArrayOutputStream[page];
        for (int item = 0; item < page; item++) {
            baos[item] = new ByteArrayOutputStream();
            PdfReader reader = new PdfReader(fileName);
            PdfStamper ps = new PdfStamper(reader, baos[item]);
            AcroFields fields = ps.getAcroFields();
            fields.setField("title", title);
            fields.setField("trncde", trncde);
            fields.setField("tlrnum", tlrnum);
            fields.setField("trntim", trntim);
            fields.setField("vchset", vchset);
            fields.setField("trndat", trndat);
            int i = 0;
            int from = item * 20;
            int to = from + 20;
            if (to > count) {
                to = count;
            }
            for (Vch vch : vchList.subList(from, to)) {
                for (int j = 0; j < 4; j++) {
                    switch (j) {
                        case 0:
                            fields.setField("dc." + i + "." + j, vch.getDEBACT());
                            break;
                        case 1:
                            fields.setField("dc." + i + "." + j, vch.getDEBAMT());
                            break;
                        case 2:
                            fields.setField("dc." + i + "." + j, vch.getCREACT());
                            break;
                        case 3:
                            fields.setField("dc." + i + "." + j, vch.getCREAMT());
                            break;
                        default:
                            break;
                    }
                }
                i++;
            }
            ps.setFormFlattening(true);
            ps.close();
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
        pdfCopy.addPage(pdfCopy.getImportedPage(new PdfReader(baos2.toByteArray()), 1));
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
    }

    public void printInvOrd(String title, String cusidt, String outtef, String txndat, String orgnam,
                            String actnam, String boknum, String valdat, String expdat, String intcur,
                            String txnamt, String dpttyp, String dptprd, String intrat) throws IOException, DocumentException {
        fileName = TemPrintService.class.getClassLoader().getResource("feb/pdfTemplates/invOrdTemp.pdf").getPath();
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, baos);
        AcroFields fields = ps.getAcroFields();
        fields.setField("title", title);
        fields.setField("cusidt", cusidt);
        fields.setField("outtef", outtef);
        fields.setField("txndat", txndat);
        fields.setField("orgnam", orgnam);
        fields.setField("actnam", actnam);
        fields.setField("boknum", boknum);
        fields.setField("valdat", valdat);
        fields.setField("expdat", expdat);
        fields.setField("intcur", intcur);
        fields.setField("txnamt", txnamt);
        fields.setField("dpttyp", dpttyp);
        fields.setField("dptprd", dptprd);
        fields.setField("intrat", intrat);
        fields.setField("flag", "本证实书仅对存款人开户证实，不得作为质押权利凭证");
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
