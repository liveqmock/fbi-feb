package feb.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import feb.print.model.Vchset;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-4-18
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TemVchPrintService {

    private String fileName;

    public void printVch(String vchset, String sysdat, String txntim, String tlrnum, java.util.List<Vchset> vchList) throws IOException, DocumentException {
        fileName = TemPrintService.class.getClassLoader().getResource("feb/PdfTemplates/vchTemp.pdf").getPath();
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
            fields.setField("date", sysdat);
            fields.setField("vch", vchset);
            fields.setField("time", txntim);
            fields.setField("tell", tlrnum);
            int i = 0;
            int from = item*20;
            int to = from +20;
            if (to>count){
                to = count;
            }
            for (Vchset vch : vchList.subList(from,to)) {
                for (int j = 0; j < 8; j++) {
                    switch (j) {
                        case 0:
                            fields.setField("Text1." + i + "." + j, vch.getSETSEQ());
                            break;
                        case 1:
                            fields.setField("Text1." + i + "." + j, vch.getACTNUM());
                            break;
                        case 2:
                            fields.setField("Text1." + i + "." + j, vch.getRVSLBL());
                            break;
                        case 3:
                            fields.setField("Text1." + i + "." + j, vch.getTXNAMT());
                            break;
                        case 4:
                            fields.setField("Text1." + i + "." + j, vch.getFURINF());
                            break;
                        case 5:
                            fields.setField("Text1." + i + "." + j, vch.getVALDAT());
                            break;
                        case 6:
                            fields.setField("Text1." + i + "." + j, vch.getANACDE());
                            break;
                        case 7:
                            fields.setField("Text1." + i + "." + j, vch.getVCHATT());
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
}
