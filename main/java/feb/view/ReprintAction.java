package feb.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import feb.service.RePrintService;
import gateway.sbs.txn.model.form.ac.T001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.form.control.ListGenerator;
import pub.tools.MessageUtil;
import skyline.service.SkylineService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lichao.W
 * Date: 14-6-3
 * Time: ����4:45
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ReprintAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ReprintAction.class);


    @ManagedProperty(value = "#{rePrintService}")
    private RePrintService rePrintService;

    @ManagedProperty(value = "#{skylineService}")
    private SkylineService skylineService;

    private String reptyp = "";
    private String tellerid = "";
    private String fileName = "";
    private String path = null;

    private List<SelectItem> reptypItems;      //��ӡ����

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        fileName = params.get("filename");
        tellerid = SkylineService.getOperId();
        reptypItems = skylineService.getEnuSelectItemList("CTF-REPTYP", true, false);
        //onReadtxt();
    }

    public String onPrint() {
        try {
            rePrintService.printVchpenAct(reptyp,tellerid, fileName);
        } catch (Exception e) {
            logger.error("��ӡʧ��", e);
            MessageUtil.addError("��ӡʧ��." + (e.getMessage() == null ? "" : e.getMessage()));
        }
        return null;
    }

    public String onReadtxt(){
        File file= null;
        try {
            String encoding="UTF-8";
            path = ReprintAction.class.getClassLoader().getResource("feb/resultsTxt/Result.txt").getPath();
            file = new File(path);
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);
            BufferedReader br=new BufferedReader(read);
            String [] ss = null;
            String line = br.readLine();
            while (line!=null){
                /*if (line.startsWith("af")){
                    ss = line.split(" ");
                    for (String s:ss){
                        System.out.println(s);
                    }
                }*/
                ss = line.split(",");
                for (String s:ss){

                    System.out.println(s);

                }
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    //= = = = = = = = = = = = = = == = =  = = = =


    public String getReptyp() {
        return reptyp;
    }

    public void setReptyp(String reptyp) {
        this.reptyp = reptyp;
    }

    public String getTellerid() {
        return tellerid;
    }

    public void setTellerid(String tellerid) {
        this.tellerid = tellerid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public RePrintService getRePrintService() {
        return rePrintService;
    }

    public void setRePrintService(RePrintService rePrintService) {
        this.rePrintService = rePrintService;
    }

    public SkylineService getSkylineService() {
        return skylineService;
    }

    public void setSkylineService(SkylineService skylineService) {
        this.skylineService = skylineService;
    }

    public List<SelectItem> getReptypItems() {
        return reptypItems;
    }

    public void setReptypItems(List<SelectItem> reptypItems) {
        this.reptypItems = reptypItems;
    }
}
