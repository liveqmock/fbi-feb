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
 * ��ӡ��Ʊ �����ͻ�
 */
@Service
public class RosPrintService {

    private static final String BASE_FONT = PropertyManager.getProperty("print.pdf.font");

    /**
     * ��ȡ��Ʊtable
     *
     * @param title   ����
     * @param t016    ��Ӧ����
     * @param vchList �����ڲ�����
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public PdfPTable getCPTable(String title, T016 t016, List<Vch> vchList) throws IOException, DocumentException {
        PdfPTable table = initVchPdfPTable(title, t016.getWATNUM(), t016.getVCHSET(), t016.getTRNDAT(), t016.getTRNCDE(), t016.getTRNTIM());     // ����Pdf�ļ�
        PdfPCell cell;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 10, Font.NORMAL);// ���������С
        for (Vch vch : vchList) {
            String row = StringPad.pad4ChineseToByteLength(true, vch.getDEBACT(), 22, " ") +
                    StringPad.pad4ChineseToByteLength(false, "    " + vch.getDEBAMT(), 22, " ") +
                    StringPad.pad4ChineseToByteLength(false, vch.getCREACT(), 22, " ") + vch.getCREAMT();
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(15);//��Ԫ��߶�
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        String vchAbstract = StringPad.pad4ChineseToByteLength(true, "ժҪ��" + t016.getVCHPAR(), 62, " ");
        cell = new PdfPCell(new Paragraph(vchAbstract, headFont2));
        cell.setBorder(0);
        cell.setFixedHeight(15);//��Ԫ��߶�
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String lastrow = StringPad.pad4ChineseToByteLength(false, "        �кţ�" + t016.getORGIDT(), 32, " ") +
                StringPad.pad4ChineseToByteLength(false, "�ն˺ţ�" + "", 24, " ") +
                StringPad.pad4ChineseToByteLength(false, "���ˣ�", 20, " ") + "���죺";
        cell = new PdfPCell(new Paragraph(lastrow, headFont2));
        cell.setBorder(0);
        cell.setFixedHeight(15);//��Ԫ��߶�
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        return table;
    }

    /**
     * ��ȡ֤ʵ��table
     *
     * @param title ����
     * @param t104  ��Ӧ����
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public PdfPTable getZSSTable(String title, T104 t104) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable("");     // ����Pdf�ļ�
        PdfPCell cell;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// ���������С
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
        strs.add("��֤ʵ����Դ���˿���֤ʵ��������Ϊ��ѺȨ��ƾ֤");
        for (String row : strs) {
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(18);//��Ԫ��߶�
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        return table;
    }

    /**
     * ��ȡ��Ϣ��table
     *
     * @param title ����
     * @param t130  ��Ӧ����
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public PdfPTable getLXDTable(String title, T130 t130) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable(title);     // ����Pdf�ļ�
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// ���������С
        List<String> strs = new ArrayList<String>();
        strs.add("�����룺" + StringPad.pad4ChineseToByteLength(false, t130.getTXNCDE(), 15, " ") + "���׹�Ա��" + StringPad.pad4ChineseToByteLength(false, t130.getTELLER(), 15, " ") + "�������ڣ�" + t130.getTXNDAT());
        strs.add("�˺ţ�" + StringPad.pad4ChineseToByteLength(false, t130.getIPTAC(), 42, " ") + "��Ϣ���ڣ�" + t130.getVALDAT());
        strs.add("������" + t130.getACTNAM());
        strs.add("����           ����          ��Ϣ          ��˰��Ϣ        δ��˰��Ϣ");
        strs.add(StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getTXNAMT()), 16, " ") + "����" + StringPad.pad4ChineseToByteLength(false, StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat(t130.getOPNIRT()), 7, "0") + "%", 10, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getININT()), 15, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getP_INTAX()), 17, " ") + StringMathFormat.strFormat2(t130.getU_INTAX()));
        strs.add(StringPad.pad4ChineseToByteLength(true, "����", 20, " ") + StringPad.pad4ChineseToByteLength(false, StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat(t130.getSAVIRT()), 7, "0") + "%", 10, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getOUTINT()), 15, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getP_OUTTAX()), 17, " ") + StringMathFormat.strFormat2(t130.getU_OUTTAX()));
        strs.add(StringPad.pad4ChineseToByteLength(true, "��ֵ", 20, " ") + StringPad.pad4ChineseToByteLength(false, StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat(t130.getVALIRT()), 7, "0") + "% ", 10, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getVALINT()), 15, " ") + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getP_VALTAX()), 17, " ") + StringMathFormat.strFormat2(t130.getU_VALTAX()));
        strs.add("");
        strs.add("����   ˰�� " + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getTAXRATE()), 11, " ") + "��Ϣ˰ " + StringPad.pad4ChineseToByteLength(false, StringMathFormat.strFormat2(t130.getTAXAMT()), 12, " ") + "������ " + StringMathFormat.strFormat2(t130.getFEEAMT()));
        strs.add("���Ҵ���" + StringPad.pad4ChineseToByteLength(false, t130.getINTCUR(), 15, " ") + "ʵ����� " + StringMathFormat.strFormat2(t130.getTOTINT()));
        PdfPCell cell;
        for (String row : strs) {
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(22);//��Ԫ��߶�
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        return table;
    }

    /**
     * ��ӡ��Ʊ��֤ʵ��
     *
     * @param title016 t016����
     * @param t016     t016��Ӧ����
     * @param vchList  t016�����ڲ�����
     * @param title104 t104����
     * @param t104     t104��Ӧ����
     * @throws IOException
     * @throws DocumentException
     */
    public void printCP_ZSS(String title016, T016 t016, List<Vch> vchList, String title104, T104 t104) throws IOException, DocumentException {
        PdfPTable cpTable = getCPTable(title016, t016, vchList);
        PdfPTable zssTable = getZSSTable(title104, t104);
        printDoublePdfTable(cpTable, zssTable);
    }

    /**
     * ��ӡ��Ʊ����Ϣ��
     *
     * @param title016 t016����
     * @param t016     t016��Ӧ����
     * @param vchList  t016�����ڲ�����
     * @param title130 t130����
     * @param t130     t130��Ӧ����
     * @throws IOException
     * @throws DocumentException
     */
    public void printCP_LXD(String title016, T016 t016, List<Vch> vchList, String title130, T130 t130) throws IOException, DocumentException {
        PdfPTable cpTable = getCPTable(title016, t016, vchList);
        PdfPTable lxdTable = getLXDTable(title130, t130);
        printDoublePdfTable(cpTable, lxdTable);
    }

    /**
     * ��ӡ���֪ͨ��
     *
     * @param title ����
     * @param ndn   ��Ӧ����
     */
    public void printDrawNote(String title, T220 ndn) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable(title);     // ����Pdf�ļ�
        PdfPCell cell;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// ���������С
        List<String> strs = new ArrayList<String>();
        strs.add("���״��룺" + StringPad.pad4ChineseToByteLength(false, ndn.getTXNCDE(), 10, " ") + "��Ա���룺" + StringPad.pad4ChineseToByteLength(false, ndn.getTELLER(), 20, " ") + "�������ڣ�" + ndn.getTXNDAT());
        strs.add("");
        strs.add("�˻����" + StringPad.pad4ChineseToByteLength(false, ndn.getACTTY(), 10, " ") + "    �ʺţ�" + StringPad.pad4ChineseToByteLength(false, ndn.getIPTAC(), 20, " ") + "֪ͨ���ڣ�" + ndn.getADVDAT());
        strs.add("");
        strs.add("    ������" + StringPad.pad4ChineseToByteLength(false, ndn.getACTNAM(), 10, " ") + "    �ұ�" + StringPad.pad4ChineseToByteLength(false, ndn.getINTCUR(), 20, " ") + "֪ͨ��" + StringMathFormat.strFormat2(ndn.getTXNAMT().toString()));
        strs.add("");
        strs.add("֪ͨ���ţ�" + StringPad.pad4ChineseToByteLength(false, ndn.getADVNUM(), 10, " ") + "    ��ע��" + ndn.getREMARK());
        for (String row : strs) {
            cell = new PdfPCell(new Paragraph(row, headFont2));
            cell.setBorder(0);
            cell.setFixedHeight(18);//��Ԫ��߶�
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
        printSinglePdfTable(table);
    }

    /**
     * ��ӡ����֪ͨ��
     *
     * @param title ����
     * @param ndp   ��Ӧ����
     */
    public void printRevoNote(String title, T220 ndp) throws IOException, DocumentException {
        PdfPTable table = initCmnPdfPTable(title);     // ����Pdf�ļ�
        PdfPCell cell;
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont2 = new Font(bfChinese, 12, Font.NORMAL);// ���������С
        List<String> strs = new ArrayList<String>();
        strs.add("���״��룺" + StringPad.pad4ChineseToByteLength(false, ndp.getTXNCDE(), 10, " ") + "��Ա���룺" + StringPad.pad4ChineseToByteLength(false, ndp.getTELLER(), 20, " ") + "�������ڣ�" + ndp.getTXNDAT());
        strs.add("");
        strs.add("�˻����" + StringPad.pad4ChineseToByteLength(false, ndp.getACTTY(), 10, " ") + "    �ʺţ�" + StringPad.pad4ChineseToByteLength(false, ndp.getIPTAC(), 20, " ") + "֪ͨ���ڣ�" + ndp.getADVDAT());
        strs.add("");
        strs.add("    ������" + StringPad.pad4ChineseToByteLength(false, ndp.getACTNAM(), 10, " ") + "    �ұ�" + StringPad.pad4ChineseToByteLength(false, ndp.getINTCUR(), 20, " ") + "֪ͨ��" + StringMathFormat.strFormat2(ndp.getTXNAMT().toString()));
        strs.add("");
        strs.add("֪ͨ���ţ�" + StringPad.pad4ChineseToByteLength(false, ndp.getADVNUM(), 10, " ") + "    ��ע��" + ndp.getREMARK());
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
     * ��Ʊƾ֤
     *
     * @param title  ����
     * @param vchno  �׺�
     * @param vchno  ��Ʊ��
     * @param txndat ����
     * @param txncde ������
     * @param txntim ʱ��
     * @return
     * @throws java.io.IOException
     * @throws com.itextpdf.text.DocumentException
     *
     */
    private PdfPTable initVchPdfPTable(String title, String watnum, String vchno, String txndat,
                                       String txncde, String txntim) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{1000f});// ����һ��pdf���
        table.setSpacingBefore(160f);// ���ñ������հ׿��
        table.setTotalWidth(835);// ���ñ��Ŀ��
        table.setLockedWidth(false);// ���ñ��Ŀ�ȹ̶�
        table.getDefaultCell().setBorder(0);//���ñ��Ĭ��Ϊ�ޱ߿�
        BaseFont bfChinese = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headFont1 = new Font(bfChinese, 10, Font.NORMAL);// ���������С
        OperatorManager om = SkylineService.getOperatorManager();
        String row1 = StringPad.pad4ChineseToByteLength(true, "���״��룺", 15, " ") + txncde + "  " +
                StringPad.pad4ChineseToByteLength(true, "��ˮ�ţ�", 60, " ") + watnum;
        String row2 = StringPad.pad4ChineseToByteLength(true, "����ʱ�䣺", 15, " ") + txntim +
                StringPad.pad4ChineseToByteLength(true, "��Ա�ţ�", 60, " ") + om.getOperatorId();
        String row3 = StringPad.pad4ChineseToByteLength(true, title, 60, " ") +
                StringPad.pad4ChineseToByteLength(true, "��Ʊ�ţ�", 21, " ") + vchno;
        String row4 = StringPad.pad4ChineseToByteLength(true, "�����գ�" + txndat, 89, " ");
        String row5 = StringPad.pad4ChineseToByteLength(true, "��DR", 15, " ") +
                StringPad.pad4ChineseToByteLength(true, "��CR", 37, " ");
        String[] labels = new String[]{row1, row2, row3, row4, row5};
        PdfPCell cell;
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

    /**
     * ͨ�ÿհ�ƾ֤
     *
     * @param title ����
     * @return
     * @throws IOException
     * @throws DocumentException
     */
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
        blankCell.setFixedHeight(18);
        table.addCell(blankCell);
        PdfPCell cell = new PdfPCell(new Paragraph(title, headFont1));
        cell.setBorder(0);
        cell.setFixedHeight(20);//��Ԫ��߶�
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ��������ˮƽ������ʾ
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        blankCell.setFixedHeight(7);
        table.addCell(blankCell);
        return table;
    }

    /**
     * ��ҳ��ӡ
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
     * ��ҳ��ӡ
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
