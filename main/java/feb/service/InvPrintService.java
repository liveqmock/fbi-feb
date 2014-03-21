package feb.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import pub.platform.advance.utils.PropertyManager;
import pub.tools.StringPad;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-3-19
 * Time: 上午9:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class InvPrintService {

    private static final String BASE_FONT = PropertyManager.getProperty("print.pdf.font");

    public void printCurrInv(String title, String orgidt, String actnum, String actnam,
                             String avabal, String clsdat, String lintdt, String craccm1,
                             String cratsf1, String cacint1, String craccm2, String cratsf2,
                             String cacint2, String craccm3, String cratsf3, String cacint3,
                             String cacint, String acount) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1200f});// 建立一个pdf表格
        table.setSpacingBefore(300f);// 设置表格上面空白宽度
        table.setTotalWidth(600);// 设置表格的宽度
        table.setLockedWidth(true);// 设置表格的宽度固定
        table.getDefaultCell().setBorder(0);//设置表格默认为无边框
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont1 = new Font(bfChinese, 11, Font.NORMAL);// 设置字体大小
        String row1 = StringPad.pad4ChineseToByteLength(true, title, 50, " ");
        String row11 = StringPad.pad4ChineseToByteLength(true, "", 50, "");
        String row2 = StringPad.pad4ChineseToByteLength(true, "机构号:", 15, " ") + orgidt +
                StringPad.pad4ChineseToByteLength(true, "账号:", 27, " ") + actnum;

        String row3 = StringPad.pad4ChineseToByteLength(true, "帐户名:", 15, " ") + actnam;

        String row4 = StringPad.pad4ChineseToByteLength(true, "即时有效余额: ", 16, " ") +
                StringPad.pad4ChineseToByteLength(true, avabal, 18, " ");

        String row5 = StringPad.pad4ChineseToByteLength(true, " 关户日期: ", 16, " ") + clsdat +
                StringPad.pad4ChineseToByteLength(true, "末次记息日: ", 23, " ") + lintdt;

        String row6 = StringPad.pad4ChineseToByteLength(true, "积数1: ", 16, " ") +
                StringPad.pad4ChineseToByteLength(true, craccm1, 18, " ") +
                StringPad.pad4ChineseToByteLength(true, "   利率1: ", 13, " ") +
                StringPad.pad4ChineseToByteLength(true, cratsf1, 18, " ")+
                StringPad.pad4ChineseToByteLength(true, "   利息1: ", 13, " ") +
                StringPad.pad4ChineseToByteLength(true, cacint1, 18, " ");

        String row7 = StringPad.pad4ChineseToByteLength(true, "积数2: ", 16, " ") +
                StringPad.pad4ChineseToByteLength(true, craccm2 , 18, " ")+
                StringPad.pad4ChineseToByteLength(true, "   利率2: ", 13, " ") +
                StringPad.pad4ChineseToByteLength(true, cratsf2, 18, " ") +
                StringPad.pad4ChineseToByteLength(true, "   利息2: ", 13, " ") +
                StringPad.pad4ChineseToByteLength(true, cacint2, 18, " ");

        String row8 = StringPad.pad4ChineseToByteLength(true, "积数3: ", 16, " ") +
                StringPad.pad4ChineseToByteLength(true, craccm3, 18, " ") +
                StringPad.pad4ChineseToByteLength(true, "   利率3: ", 13, " ") +
                StringPad.pad4ChineseToByteLength(true, cratsf3, 18, " ") +
                StringPad.pad4ChineseToByteLength(true, "   利息3: ", 13, " ") +
                StringPad.pad4ChineseToByteLength(true, cacint3, 18, " ");

        String row9 = StringPad.pad4ChineseToByteLength(true, "    " + "   ", 50, " ");

        String row10 = StringPad.pad4ChineseToByteLength(true, "利息合计: ", 16, " ") +
                StringPad.pad4ChineseToByteLength(true, cacint, 18, " ") +
                StringPad.pad4ChineseToByteLength(true, "    本息合计: ", 13, " ") +
                StringPad.pad4ChineseToByteLength(true, acount, 18, " ");

        String[] labels = new String[]{row1, row11, row2, row3, row4, row5, row6, row7, row8, row9, row10};

        PdfPCell cell = null;
        for (String row : labels) {
            cell = new PdfPCell(new Paragraph(row, headFont1));
            cell.setBorder(0);
            cell.setFixedHeight(15);//单元格高度
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
        }
        printPdfTable(table);
    }

    // 单页打印
    private void printPdfTable(PdfPTable table) throws DocumentException, IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
        Document document = new Document(PageSize.A4, 16, 16, 36, 90);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, bos);
        writer.setPageEvent(new PdfPageEventHelper());
        document.open();
        document.add(table);
        writer.addJavaScript("this.print({bUI: false,bSilent: true,bShrinkToFit: false});" + "\r\nthis.closeDoc();");
        document.close();
        response.reset();
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline");
        response.setContentLength(bos.size());
        response.setHeader("Cache-Control", "max-age=30");
        bos.writeTo(out);
        out.flush();
        out.close();
        ctx.responseComplete();
    }
}
