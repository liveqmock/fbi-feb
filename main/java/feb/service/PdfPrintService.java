package feb.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import pub.platform.advance.utils.PropertyManager;
import pub.platform.security.OperatorManager;
import pub.platform.utils.StringUtil;
import pub.tools.StringPad;
import skyline.service.SkylineService;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 打印所有凭证
 */
@Service
public class PdfPrintService {

    private static final String BASE_FONT = PropertyManager.getProperty("print.pdf.font");
    // 开销户确认书
    public void printVch4OpenClsAct(String title, String orgid, String deptid, String actnum,
                                    String actnam, String opndat, String clsdat, String teller) throws IOException, DocumentException {
        PdfPTable table = initPdfPTable(title);     // 生成Pdf文件

        String[] labels = {"机构号：", "部门号：", "账号：", "账户名称：", "开户日期：", "销户日期：", "交易柜员："};
        String[] values = {orgid, deptid, actnum, actnam, opndat, clsdat, teller};
        int i = 0;
        PdfPCell cell = null;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// 设置字体大小

        for (String row : labels) {
            cell = new PdfPCell(new Paragraph(StringPad.pad4ChineseToByteLength(true, row, 30, " ") + values[i++], headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(22);//单元格高度
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        printPdfTable(table);
    }

    private PdfPTable initPdfPTable(String title) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1000f});// 建立一个pdf表格
        OperatorManager om = SkylineService.getOperatorManager();
        table.setSpacingBefore(160f);// 设置表格上面空白宽度
        table.setTotalWidth(835);// 设置表格的宽度
        table.setLockedWidth(false);// 设置表格的宽度固定
        table.getDefaultCell().setBorder(0);//设置表格默认为无边框
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont1 = new Font(bfChinese, 14, Font.BOLD);// 设置字体大小
        PdfPCell blankCell = new PdfPCell(new Paragraph());
        blankCell.setBorder(0);
        blankCell.setFixedHeight(25);
        table.addCell(blankCell);
        PdfPCell cell = new PdfPCell(new Paragraph(title, headFont1));
        cell.setBorder(0);
        cell.setFixedHeight(40);//单元格高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        blankCell.setFixedHeight(10);
        table.addCell(blankCell);

        return table;
    }
    // 打印表格
    private void printPdfTable(PdfPTable table) throws DocumentException, IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
        Document document = new Document(PageSize.A4, 16, 16, 36, 90);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, bos);
        writer.setPageEvent(new PdfPageEventHelper());
        document.open();
        document.add(table);
        document.close();
        response.reset();
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline");
        response.setContentLength(bos.size());
        response.setHeader("Cache-Control", "max-age=30");
        bos.write("javascript:window.print();".getBytes());
        bos.writeTo(out);
        out.flush();
        out.close();
        ctx.responseComplete();
    }

}
