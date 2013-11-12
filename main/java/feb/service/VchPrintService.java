package feb.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import feb.print.model.Vchset;
import org.springframework.stereotype.Service;
import pub.platform.advance.utils.PropertyManager;
import pub.platform.security.OperatorManager;
import pub.tools.StringPad;
import skyline.service.SkylineService;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 打印所有凭证
 */
@Service
public class VchPrintService {

    private static final String BASE_FONT = PropertyManager.getProperty("print.pdf.font");

    // 打印传票
    public void printVch(String title, String vchset, String txndat,
                         String txntim,  String bankno, String termid,
                         String abstra, List<Vchset> vchList) throws IOException, DocumentException {
        PdfPTable table = initVchPdfPTable(title, vchset,  txndat, txntim);     // 生成Pdf文件
        PdfPCell cell = null;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
        for (Vchset vch : vchList) {
            String row = StringPad.pad4ChineseToByteLength(true, vch.getSETSEQ(), 11, " ") +
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getACTNUM(), 19, " ")+
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getRVSLBL(), 6, " ")+
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getTXNAMT(), 17, " ")+
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getFURINF(), 6, " ")+
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getVALDAT(), 13, " ")+
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getANACDE(), 4, " ") + vch.getVCHATT();
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(15);//单元格高度
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        Paragraph cell2 = new Paragraph(StringPad.pad4ChineseToByteLength(true, "- - - - - - - - - - - - - - - " +
                "- - - - - - - - - - - " +
                "- - - - - - - - - - - - - -"  , 87, " "), headFont2);
        table.addCell(cell2);
        printPdfTable(table);
    }

    /**
     * 传票凭证
     *
     * @param title  标题
     * @param vchset 套号
     * //@param vchno  传票号
     * @param txndat 日期
     * //@param txncde 交易码
     * @param txntim 时间
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private PdfPTable initVchPdfPTable(String title, String vchset,  String txndat,
                                       String txntim) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1000f});// 建立一个pdf表格
        table.setSpacingBefore(160f);// 设置表格上面空白宽度
        table.setTotalWidth(835);// 设置表格的宽度
        table.setLockedWidth(false);// 设置表格的宽度固定
        table.getDefaultCell().setBorder(0);//设置表格默认为无边框
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont1 = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
//        String[] labels = new String[]{"aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"};
        OperatorManager om = SkylineService.getOperatorManager();

        String row1 = StringPad.pad4ChineseToByteLength(true, title, 52, " ");

        String row2 = StringPad.pad4ChineseToByteLength(true, "  = = = = = = = = = = = = = = " +
                "= = = = = = = = = = = = = = = =", 78, " ");

        String row3 = StringPad.pad4ChineseToByteLength(true, "日期：", 15, " ") + txndat +
                StringPad.pad4ChineseToByteLength(true, "套   号：", 58, " ") + vchset;

        String row4 = StringPad.pad4ChineseToByteLength(true, "时间：", 15, " ") + txntim +
                StringPad.pad4ChineseToByteLength(true, "柜员号：", 60, " ") + om.getOperatorId();

        String row5 = StringPad.pad4ChineseToByteLength(true, "- - - - - - - - - - - - - - - " +
                "- - - - - - - - - - - " +
                "- - - - - - - - - - - - - -"  , 87, " ");

        String row6 = StringPad.pad4ChineseToByteLength(true, "序号", 13, " ") +
                StringPad.pad4ChineseToByteLength(true, "账号", 10, " ") +
                StringPad.pad4ChineseToByteLength(true, "标志", 14, " ") +
                StringPad.pad4ChineseToByteLength(true, "金额", 14, " ") +
                StringPad.pad4ChineseToByteLength(true, "摘要", 11, " ") +
                StringPad.pad4ChineseToByteLength(true, "日期", 7, " ") +
                StringPad.pad4ChineseToByteLength(true, "统计码", 10, " ") +
                StringPad.pad4ChineseToByteLength(true, "附件", 7, " ");

        String[] labels = new String[]{row1, row2, row3, row4, row5, row6};

        PdfPCell cell = null;
        for (String row : labels) {
            cell = new PdfPCell(new Paragraph(row, headFont1));
            cell.setBorder(0);
            cell.setFixedHeight(15);//单元格高度
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        return table;
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
