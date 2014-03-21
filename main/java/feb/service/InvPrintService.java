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
 * Time: ����9:59
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
        PdfPTable table = new PdfPTable(new float[]{1200f});// ����һ��pdf���
        table.setSpacingBefore(300f);// ���ñ������հ׿��
        table.setTotalWidth(600);// ���ñ��Ŀ��
        table.setLockedWidth(true);// ���ñ��Ŀ�ȹ̶�
        table.getDefaultCell().setBorder(0);//���ñ��Ĭ��Ϊ�ޱ߿�
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont1 = new Font(bfChinese, 11, Font.NORMAL);// ���������С
        String row1 = StringPad.pad4ChineseToByteLength(true, title, 45, " ");
        String row11 = StringPad.pad4ChineseToByteLength(true, "", 50, "");
        String row2 = StringPad.pad4ChineseToByteLength(true, "������:", 15, " ") + orgidt +
                StringPad.pad4ChineseToByteLength(true, "�˺�:", 27, " ") + actnum;

        String row3 = StringPad.pad4ChineseToByteLength(true, "�ʻ���:", 15, " ") + actnam;

        String row4 = StringPad.pad4ChineseToByteLength(true, "��ʱ��Ч���: ", 16, " ") + avabal;

        String row5 = StringPad.pad4ChineseToByteLength(true, " �ػ�����: ", 16, " ") + clsdat +
                StringPad.pad4ChineseToByteLength(true, "ĩ�μ�Ϣ��: ", 23, " ") + lintdt;

        String row6 = StringPad.pad4ChineseToByteLength(true, "����1: ", 16, " ") + craccm1 +
                StringPad.pad4ChineseToByteLength(false, "                    ����1: ", 27, " ") + cratsf1 +
                StringPad.pad4ChineseToByteLength(false, "                    ��Ϣ1: ", 27, " ") + cacint1;

        String row7 = StringPad.pad4ChineseToByteLength(true, "����2: ", 16, " ") + craccm2 +
                StringPad.pad4ChineseToByteLength(false, "                    ����2: ", 27, " ") + cratsf2 +
                StringPad.pad4ChineseToByteLength(false, "                    ��Ϣ2: ", 27, " ") + cacint2;

        String row8 = StringPad.pad4ChineseToByteLength(true, "����3: ", 16, " ") + craccm3 +
                StringPad.pad4ChineseToByteLength(false, "                        ����3: ", 31, " ") + cratsf3 +
                StringPad.pad4ChineseToByteLength(false, "                    ��Ϣ3: ", 32, " ") + cacint3;

        String row9 = StringPad.pad4ChineseToByteLength(true, "    " + "   ", 50, " ");

        String row10 = StringPad.pad4ChineseToByteLength(true, "��Ϣ�ϼ�: ", 16, " ") + cacint +
                StringPad.pad4ChineseToByteLength(true, "��Ϣ�ϼ�: ", 28, " ") + acount;

        String[] labels = new String[]{row1, row11, row2, row3, row4, row5, row6, row7, row8, row9, row10};

        PdfPCell cell = null;
        for (String row : labels) {
            cell = new PdfPCell(new Paragraph(row, headFont1));
            cell.setBorder(0);
            cell.setFixedHeight(15);//��Ԫ��߶�
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        printPdfTable(table);
    }

    // ��ҳ��ӡ
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
