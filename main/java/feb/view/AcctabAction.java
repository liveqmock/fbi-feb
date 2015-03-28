package feb.view;

import feb.service.DataExchangeService;
import feb.service.JxlsManager;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.ac.T623;
import gateway.sbs.txn.model.msg.M8621;
import javafx.scene.input.DataFormat;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.tools.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Lichao.W At 2015/3/24 9:17
 * wanglichao@163.com
 */
@ManagedBean
@ViewScoped
public class AcctabAction implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ActAccDtlQryAction.class);

    @ManagedProperty(value = "#{dataExchangeService}")
    private DataExchangeService dataExchangeService;
    private String curcde = "001";
    M8621 m8621 = new M8621();
    List<T623.Bean> dataList = new ArrayList<>();

    public void onAllQry(){
        m8621.setCURCDE(curcde);
        List<SOFForm> formList = dataExchangeService.callSbsTxn("8621", m8621);
        if (formList != null && !formList.isEmpty()) {
            dataList = new ArrayList<>();
            for (SOFForm form : formList) {
                if ("T623".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                    T623 t623 = (T623) form.getFormBody();
                    dataList.addAll(t623.getBeanList());
                } else if (form.getFormHeader().getFormCode().contains("W012")) {
                    MessageUtil.addInfoWithClientID("msgs", form.getFormHeader().getFormCode());
                } else {
                    logger.info(form.getFormHeader().getFormCode());
                    MessageUtil.addErrorWithClientID("msgs", form.getFormHeader().getFormCode());
                }
            }
        }
    }
    public String onExpExcel() {
        try {
            if (dataList == null || dataList.size() == 0) {
                MessageUtil.addWarn("未查询数据！");
                return null;
            }
            BigDecimal ctot6 = null; //支出合计
            BigDecimal dtot5 = null;  //收入合计:
            BigDecimal jtot6 = null;  //收入合计:
            BigDecimal stot5 = null;  //结损:::
            List<T623.Bean> dataList6 = new ArrayList<>();
            List<T623.Bean> dataList5 = new ArrayList<>();
            for (T623.Bean datas:dataList){
                if ("2".equals(datas.getPLTYPE())){
                    if ("3".equals(datas.getPLCLAS())){
                        ctot6 = datas.getPLAMNT();
                    }else if ("4".equals(datas.getPLCLAS())){
                        jtot6 = datas.getPLAMNT();
                    }else {
                        dataList6.add(datas);
                    }
                }else if ("1".equals(datas.getPLTYPE())){
                    dataList5.add(datas);
                }
            }
            Date expdat = new Date();
            String excelFilename = "损益表" + ".xls";
            JxlsManager jxls = new JxlsManager();
            Map beansMap = new HashMap();
            beansMap.put("record6s", dataList6);
            beansMap.put("record5s", dataList5);
            beansMap.put("curcde", curcde);
            beansMap.put("expdat", expdat);
            beansMap.put("ctot6", ctot6);
            beansMap.put("jtot6", jtot6);
            jxls.exportDataToXls(excelFilename, "/tmp.xls", beansMap);
        } catch (Exception e) {

        }
        return null;
    }

    //= = = = = = = = = = = get set = = = = = = = =

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }

    public String getCurcde() {
        return curcde;
    }

    public void setCurcde(String curcde) {
        this.curcde = curcde;
    }

    public M8621 getM8621() {
        return m8621;
    }

    public void setM8621(M8621 m8621) {
        this.m8621 = m8621;
    }

    public List<T623.Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<T623.Bean> dataList) {
        this.dataList = dataList;
    }
}
