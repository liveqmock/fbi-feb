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
 * ��ӡ����ƾ֤
 */
@Service
public class PdfPrintService {

    private static final String BASE_FONT = PropertyManager.getProperty("print.pdf.font");
    // ������ȷ����
    public void printVch4OpenClsAct(String title, String orgid, String deptid, String actnum,
                                    String actnam, String opndat, String clsdat, String teller) throws IOException, DocumentException {
        PdfPTable table = initPdfPTable(title);     // ����Pdf�ļ�

        String[] labels = {"�����ţ�", "���źţ�", "�˺ţ�", "�˻����ƣ�", "�������ڣ�", "�������ڣ�", "���׹�Ա��"};
        String[] values = {orgid, deptid, actnum, actnam, opndat, clsdat, teller};
        int i = 0;
        PdfPCell cell = null;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// ���������С

        for (String row : labels) {
            cell = new PdfPCell(new Paragraph(StringPad.pad4ChineseToByteLength(true, row, 30, " ") + values[i++], headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(22);//��Ԫ��߶�
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        printPdfTable(table);
    }

    private PdfPTable initPdfPTable(String title) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1000f});// ����һ��pdf���
        OperatorManager om = SkylineService.getOperatorManager();
        table.setSpacingBefore(160f);// ���ñ������հ׿��
        table.setTotalWidth(835);// ���ñ��Ŀ��
        table.setLockedWidth(false);// ���ñ��Ŀ�ȹ̶�
        table.getDefaultCell().setBorder(0);//���ñ��Ĭ��Ϊ�ޱ߿�
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont1 = new Font(bfChinese, 14, Font.BOLD);// ���������С
        PdfPCell blankCell = new PdfPCell(new Paragraph());
        blankCell.setBorder(0);
        blankCell.setFixedHeight(25);
        table.addCell(blankCell);
        PdfPCell cell = new PdfPCell(new Paragraph(title, headFont1));
        cell.setBorder(0);
        cell.setFixedHeight(40);//��Ԫ��߶�
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ��������ˮƽ������ʾ
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        blankCell.setFixedHeight(10);
        table.addCell(blankCell);

        return table;
    }
    // ��ӡ���
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
