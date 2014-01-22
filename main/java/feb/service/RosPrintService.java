package feb.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import feb.print.model.Vch;
import gateway.sbs.txn.model.form.T016;
import gateway.sbs.txn.model.form.T104;
import gateway.sbs.txn.model.form.T130;
import gateway.sbs.txn.model.form.T220;
import org.springframework.stereotype.Service;
import pub.platform.advance.utils.PropertyManager;
import pub.platform.security.OperatorManager;
import pub.platform.utils.StringMathFormat;
import pub.tools.StringPad;
import skyline.service.SkylineService;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 打印传票 开销客户
 */
@Service
public class RosPrintService {

    private static final String BASE_FONT = PropertyManager.getProperty("print.pdf.font");

    /**
     * 获取传票table
     *
     * @param title   标题
     * @param t016    响应报文
     * @param vchList 报文内部数据
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public PdfPTable getCPTable(String title, T016 t016, List<Vch> vchList) throws IOException, DocumentException {
        PdfPTable table = initVchPdfPTable(title, t016.getWATNUM(), t016.getVCHSET(), t016.getTRNDAT(), t016.getTRNCDE(), t016.getTRNTIM());     // 生成Pdf文件
        PdfPCell cell;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
        for (Vch vch : vchList) {
            String row = StringPad.pad4ChineseToByteLength(true, vch.getDEBACT(), 22, " ") +
                    StringPad.pad4ChineseToByteLength(false, "    " + vch.getDEBAMT(), 22, " ") +
                    StringPad.pad4ChineseToByteLength(false, vch.getCREACT(), 22, " ") + vch.getCREAMT();
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(15);//单元格高度
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        String vchAbstract = StringPad.pad4ChineseToByteLength(true, "摘要：" + t016.getVCHPAR(), 62, " ");
        cell = new PdfPCell(new Paragraph(vchAbstract, headFont2));
        cell.setBorder(0);
        cell.setFixedHeight(15);//单元格高度
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String lastrow = StringPad.pad4ChineseToByteLength(false, "        行号：" + t016.getORGIDT(), 32, " ") +
                StringPad.pad4ChineseToByteLength(false, "终端号：" + "", 24, " ") +
                StringPad.pad4ChineseToByteLength(false, "复核：", 20, " ") + "经办：";
        cell = new PdfPCell(new Paragraph(lastrow, headFont2));
        cell.setBorder(0);
        cell.setFixedHeight(15);//单元格高度
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        return table;
    }

    /**
     * 获取证实书table
     *
     * @param title 标题
     * @param t104  响应报文
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public PdfPTable getZSSTable(String title, T104 t104) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable("");     // 生成Pdf文件
        PdfPCell cell;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// 设置字体大小
        List<String> strs = new ArrayList<String>();
        strs.add("        " + StringPad.pad4ChineseToByteLength(false, t104.getCUSIDT(), 22, " ") + StringPad.pad4ChineseToByteLength(false, t104.getOURREF(), 36, " ") + t104.getTXNDAT());
        strs.add("");
        strs.add("");
        strs.add("                " + StringPad.pad4ChineseToByteLength(false, t104.getORGNAM(), 32, " ") + t104.getACTNAM());
        strs.add("");
        strs.add("                " + t104.getBOKNUM());
        strs.add("");
        strs.add("                " + StringPad.pad4ChineseToByteLength(false, t104.getVALDAT(), 32, " ") + t104.getEXPDAT());
        strs.add("            " + t104.getINTCUR() + StringMathFormat.strFormat2(t104.getTXNAMT()));
        strs.add("");
        strs.add("      " + StringPad.pad4ChineseToByteLength(false, t104.getDPTTYP(), 17, " ") + StringPad.pad4ChineseToByteLength(false, t104.getDPTPRD(), 16, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat(t104.getINTRAT()), 4, "0") + "%");
        strs.add("本证实书仅对存款人开户证实，不得作为质押权利凭证");
        for (String row : strs) {
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(18);//单元格高度
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        return table;
    }

    /**
     * 获取利息单table
     *
     * @param title 标题
     * @param t130  响应报文
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public PdfPTable getLXDTable(String title, T130 t130) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable(title);     // 生成Pdf文件
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// 设置字体大小
        List<String> strs = new ArrayList<String>();
        strs.add("交易码：" + StringPad.pad4ChineseToByteLength(false, t130.getTXNCDE(), 15, " ") + "交易柜员：" + StringPad.pad4ChineseToByteLength(false, t130.getTELLER(), 15, " ") + "交易日期：" + t130.getTXNDAT());
        strs.add("账号：" + StringPad.pad4ChineseToByteLength(false, t130.getIPTAC(), 42, " ") + "起息日期：" + t130.getVALDAT());
        strs.add("户名：" + t130.getACTNAM());
        strs.add("本金           利率          利息          征税利息        未征税利息");
        strs.add(StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getTXNAMT()), 16, " ") + "期内" + StringPad.pad4ChineseToByteLength(false, StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat(t130.getOPNIRT()), 7, "0") + "%", 10, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getININT()), 15, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getP_INTAX()), 17, " ") + StringMathFormat.strFormat2(t130.getU_INTAX()));
        strs.add(StringPad.pad4ChineseToByteLength(true, "活期", 20, " ") + StringPad.pad4ChineseToByteLength(false, StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat(t130.getSAVIRT()), 7, "0") + "%", 10, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getOUTINT()), 15, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getP_OUTTAX()), 17, " ") + StringMathFormat.strFormat2(t130.getU_OUTTAX()));
        strs.add(StringPad.pad4ChineseToByteLength(true, "保值", 20, " ") + StringPad.pad4ChineseToByteLength(false, StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat(t130.getVALIRT()), 7, "0") + "% ", 10, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getVALINT()), 15, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getP_VALTAX()), 17, " ") + StringMathFormat.strFormat2(t130.getU_VALTAX()));
        strs.add("");
        strs.add("其他   税率 " + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getTAXRATE()), 11, " ") + "利息税 " + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getTAXAMT()), 12, " ") + "手续费 " + StringMathFormat.strFormat2(t130.getFEEAMT()));
        strs.add("货币代码" + StringPad.pad4ChineseToByteLength(false, t130.getINTCUR(), 15, " ") + "实付金额 " + StringMathFormat.strFormat2(t130.getTOTINT()));
        PdfPCell cell;
        for (String row : strs) {
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(22);//单元格高度
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        return table;
    }

    /**
     * 打印传票和证实书
     *
     * @param title016 t016标题
     * @param t016     t016响应报文
     * @param vchList  t016报文内部数据
     * @param title104 t104标题
     * @param t104     t104响应报文
     * @throws IOException
     * @throws DocumentException
     */
    public void printCP_ZSS(String title016, T016 t016, List<Vch> vchList, String title104, T104 t104) throws IOException, DocumentException {
        PdfPTable cpTable = getCPTable(title016, t016, vchList);
        PdfPTable zssTable = getZSSTable(title104, t104);
        printDoublePdfTable(cpTable, zssTable);
    }

    /**
     * 打印传票和利息单
     *
     * @param title016 t016标题
     * @param t016     t016响应报文
     * @param vchList  t016报文内部数据
     * @param title130 t130标题
     * @param t130     t130响应报文
     * @throws IOException
     * @throws DocumentException
     */
    public void printCP_LXD(String title016, T016 t016, List<Vch> vchList, String title130, T130 t130) throws IOException, DocumentException {
        PdfPTable cpTable = getCPTable(title016, t016, vchList);
        PdfPTable lxdTable = getLXDTable(title130, t130);
        printDoublePdfTable(cpTable, lxdTable);
    }

    /**
     * 打印提款通知单
     *
     * @param title 标题
     * @param ndn   响应报文
     */
    public void printDrawNote(String title, T220 ndn) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable(title);     // 生成Pdf文件
        PdfPCell cell;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// 设置字体大小
        List<String> strs = new ArrayList<String>();
        strs.add("交易代码：" + StringPad.pad4ChineseToByteLength(false, ndn.getTXNCDE(), 10, " ") + "柜员代码：" + StringPad.pad4ChineseToByteLength(false, ndn.getTELLER(), 20, " ") + "交易日期：" + ndn.getTXNDAT());
        strs.add("");
        strs.add("账户类别：" + StringPad.pad4ChineseToByteLength(false, ndn.getACTTY(), 10, " ") + "    帐号：" + StringPad.pad4ChineseToByteLength(false, ndn.getIPTAC(), 20, " ") + "通知日期：" + ndn.getADVDAT());
        strs.add("");
        strs.add("    户名：" + StringPad.pad4ChineseToByteLength(false, ndn.getACTNAM(), 10, " ") + "    币别：" + StringPad.pad4ChineseToByteLength(false, ndn.getINTCUR(), 20, " ") + "通知金额：" + StringMathFormat.strFormat2(ndn.getTXNAMT().toString()));
        strs.add("");
        strs.add("通知单号：" + StringPad.pad4ChineseToByteLength(false, ndn.getADVNUM(), 10, " ") + "    备注：" + ndn.getREMARK());
        for (String row : strs) {
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(18);//单元格高度
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        printSinglePdfTable(table);
    }

    /**
     * 打印撤销通知单
     *
     * @param title 标题
     * @param ndp   响应报文
     */
    public void printRevoNote(String title, T220 ndp) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable(title);     // 生成Pdf文件
        PdfPCell cell;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// 设置字体大小
        List<String> strs = new ArrayList<String>();
        strs.add("交易代码：" + StringPad.pad4ChineseToByteLength(false, ndp.getTXNCDE(), 10, " ") + "柜员代码：" + StringPad.pad4ChineseToByteLength(false, ndp.getTELLER(), 20, " ") + "交易日期：" + ndp.getTXNDAT());
        strs.add("");
        strs.add("账户类别：" + StringPad.pad4ChineseToByteLength(false, ndp.getACTTY(), 10, " ") + "    帐号：" + StringPad.pad4ChineseToByteLength(false, ndp.getIPTAC(), 20, " ") + "通知日期：" + ndp.getADVDAT());
        strs.add("");
        strs.add("    户名：" + StringPad.pad4ChineseToByteLength(false, ndp.getACTNAM(), 10, " ") + "    币别：" + StringPad.pad4ChineseToByteLength(false, ndp.getINTCUR(), 20, " ") + "通知金额：" + StringMathFormat.strFormat2(ndp.getTXNAMT().toString()));
        strs.add("");
        strs.add("通知单号：" + StringPad.pad4ChineseToByteLength(false, ndp.getADVNUM(), 10, " ") + "    备注：" + ndp.getREMARK());
        for (String row : strs) {
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(18);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        printSinglePdfTable(table);
    }

    /**
     * 传票凭证
     *
     * @param title  标题
     * @param vchno  套号
     * @param vchno  传票号
     * @param txndat 日期
     * @param txncde 交易码
     * @param txntim 时间
     * @return
     * @throws java.io.IOException
     * @throws com.itextpdf.text.DocumentException
     *
     */
    private PdfPTable initVchPdfPTable(String title, String watnum, String vchno, String txndat,
                                       String txncde, String txntim) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1000f});// 建立一个pdf表格
        table.setSpacingBefore(160f);// 设置表格上面空白宽度
        table.setTotalWidth(835);// 设置表格的宽度
        table.setLockedWidth(false);// 设置表格的宽度固定
        table.getDefaultCell().setBorder(0);//设置表格默认为无边框
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont1 = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
        OperatorManager om = SkylineService.getOperatorManager();
        String row1 = StringPad.pad4ChineseToByteLength(true, "交易代码：", 15, " ") + txncde + "  " +
                StringPad.pad4ChineseToByteLength(true, "流水号：", 60, " ") + watnum;
        String row2 = StringPad.pad4ChineseToByteLength(true, "交易时间：", 15, " ") + txntim +
                StringPad.pad4ChineseToByteLength(true, "柜员号：", 60, " ") + om.getOperatorId();
        String row3 = StringPad.pad4ChineseToByteLength(true, title, 60, " ") +
                StringPad.pad4ChineseToByteLength(true, "传票号：", 21, " ") + vchno;
        String row4 = StringPad.pad4ChineseToByteLength(true, "记账日：" + txndat, 89, " ");
        String row5 = StringPad.pad4ChineseToByteLength(true, "借DR", 15, " ") +
                StringPad.pad4ChineseToByteLength(true, "贷CR", 37, " ");
        String[] labels = new String[]{row1, row2, row3, row4, row5};
        PdfPCell cell;
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

    /**
     * 通用空白凭证
     *
     * @param title 标题
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private PdfPTable initCmnPdfPTable(String title) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1000f});// 建立一个pdf表格
        table.setSpacingBefore(160f);// 设置表格上面空白宽度
        table.setTotalWidth(835);// 设置表格的宽度
        table.setLockedWidth(false);// 设置表格的宽度固定
        table.getDefaultCell().setBorder(0);//设置表格默认为无边框
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont1 = new Font(bfChinese, 14, Font.BOLD);// 设置字体大小
        PdfPCell blankCell = new PdfPCell(new Paragraph());
        blankCell.setBorder(0);
        blankCell.setFixedHeight(18);
        table.addCell(blankCell);
        PdfPCell cell = new PdfPCell(new Paragraph(title, headFont1));
        cell.setBorder(0);
        cell.setFixedHeight(20);//单元格高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        blankCell.setFixedHeight(7);
        table.addCell(blankCell);
        return table;
    }

    /**
     * 两页打印
     *
     * @param table1
     * @param table2
     * @throws DocumentException
     * @throws IOException
     */
    private void printDoublePdfTable(PdfPTable table1, PdfPTable table2) throws DocumentException, IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
        Document document = new Document(PageSize.A4, 16, 16, 36, 90);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, bos);
        writer.setPageEvent(new PdfPageEventHelper());
        document.open();
        document.add(table1);
        document.newPage();
        document.add(table2);
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

    /**
     * 单页打印
     *
     * @param table
     * @throws DocumentException
     * @throws IOException
     */
    private void printSinglePdfTable(PdfPTable table) throws DocumentException, IOException {
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
