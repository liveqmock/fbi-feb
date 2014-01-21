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
 * ��ӡ��Ʊ �����ͻ�
 */
@Service
public class VchPrintService {

    private static final String BASE_FONT = PropertyManager.getProperty("print.pdf.font");

    // ����ȷ����
    public void printVchpenAct(String title, String orgid, String deptid, String actnum,
                                    String actnam, String opndat, String clsdat, String teller) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable(title);     // ����Pdf�ļ�

        String[] labels = {"�����ţ�", "���źţ�", "�ͻ��ţ�", "�ͻ����ƣ�", "�������ڣ�",  "���׹�Ա��"};
        String[] values = {orgid, deptid, actnum, actnam, opndat, teller};
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
    // �رտͻ�ȷ����
    public void printVchClsAct(String title, String orgid, String deptid, String actnum,
                               String actnam, String opndat, String clsdat, String teller) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable(title);     // ����Pdf�ļ�

        String[] labels = {"�����ţ�", "���źţ�", "�ͻ��ţ�", "�ͻ����ƣ�", "�������ڣ�","�ر����ڣ�",  "���׹�Ա��"};
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
    // ��ӡ��Ʊ
    public void printVch(String title, String vchset, String txndat,
                         String txntim,  String bankno, String termid,
                         String abstra, List<Vchset> vchList) throws IOException, DocumentException {
        PdfPTable table = initVchPdfPTable(title, vchset,  txndat, txntim);     // ����Pdf�ļ�
        PdfPCell cell = null;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 10, Font.NORMAL);// ���������С
        for (Vchset vch : vchList) {
            String row = StringPad.pad4ChineseToByteLength(true, vch.getSETSEQ(), 16, " ") +         //���
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getACTNUM(), 20, " ")+    //�˺�
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getRVSLBL(), 7, " ")+      //��־
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getTXNAMT(), 24, " ")+     //���
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getFURINF(), 12, " ")+     //ժҪ
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getVALDAT(), 16, " ")+     //����
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getANACDE(), 10, " ") +    //ͳ����
                    StringPad.pad4ChineseToByteLength(true, "    " + vch.getVCHATT(),12," ");
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(15);//��Ԫ��߶�
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        Paragraph cell2 = new Paragraph(StringPad.pad4ChineseToByteLength(true, "- - - - - - - - - " +
                "- - - - - - - - - - - " +
                "- - - - - - - - - - - - - -"  , 85, " "), headFont2);
        table.addCell(cell2);
        printPdfTable(table);
    }

    /**
     * ��Ʊƾ֤
     *
     * @param title  ����
     * @param vchset �׺�
     * //@param vchno  ��Ʊ��
     * @param txndat ����
     * //@param txncde ������
     * @param txntim ʱ��
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private PdfPTable initVchPdfPTable(String title, String vchset,  String txndat,
                                       String txntim) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1200f});// ����һ��pdf���
        table.setSpacingBefore(300f);// ���ñ������հ׿��
        table.setTotalWidth(600);// ���ñ��Ŀ��
        table.setLockedWidth(true);// ���ñ��Ŀ�ȹ̶�
        table.getDefaultCell().setBorder(0);//���ñ��Ĭ��Ϊ�ޱ߿�
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont1 = new Font(bfChinese, 11, Font.NORMAL);// ���������С
//        String[] labels = new String[]{"aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"};
        OperatorManager om = SkylineService.getOperatorManager();

        String row1 = StringPad.pad4ChineseToByteLength(true, title, 66, " ");

        String row2 = StringPad.pad4ChineseToByteLength(true, " = = = = = = = = = = = = " +
                "= = = = = = = = = = = = = = = = = = = = = = ", 88, " ");

        String row3 = StringPad.pad4ChineseToByteLength(true, "���ڣ�", 20, " ") + txndat +
                StringPad.pad4ChineseToByteLength(true, "��  �ţ�", 63, " ") + vchset;

        String row4 = StringPad.pad4ChineseToByteLength(true, "ʱ�䣺", 20, " ") + txntim +
                StringPad.pad4ChineseToByteLength(true, "��Ա�ţ�", 65, " ") + om.getOperatorId();

        String row5 = StringPad.pad4ChineseToByteLength(true, " - - - - - - - - - - - - - - - - - - - - - " +
                "- - - - - - - - - - - - - - - " +
                "- - - - - - - - - - - - - -"  , 108, " ");

        String row6 = StringPad.pad4ChineseToByteLength(true, "���", 16, " ") +
                StringPad.pad4ChineseToByteLength(true, "�˺�", 14, " ") +
                StringPad.pad4ChineseToByteLength(true, "��־", 10, " ") +
                StringPad.pad4ChineseToByteLength(true, "���", 20, " ") +
                StringPad.pad4ChineseToByteLength(true, "ժҪ", 12, " ") +
                StringPad.pad4ChineseToByteLength(true, "����", 12, " ") +
                StringPad.pad4ChineseToByteLength(true, "ͳ����", 12, " ") +
                StringPad.pad4ChineseToByteLength(true, "����", 11, " ");

        String[] labels = new String[]{row1, row2, row3, row4, row5, row6};

        PdfPCell cell = null;
        for (String row : labels) {
            cell = new PdfPCell(new Paragraph(row, headFont1));
            cell.setBorder(0);
            cell.setFixedHeight(15);//��Ԫ��߶�
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        return table;
    }
    // ͨ�ÿհ�ƾ֤
    private PdfPTable initCmnPdfPTable(String title) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1000f});// ����һ��pdf���
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
