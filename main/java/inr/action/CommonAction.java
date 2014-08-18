package inr.action;

import inr.bean.PrintBean;
import inr.service.CommonService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import pub.platform.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class CommonAction implements Serializable {

    private static Logger logger = Logger.getLogger(CommonAction.class);

    @ManagedProperty(value = "#{commonService}")
    private CommonService commonService;

    private List<PrintBean> printBeans;
    private PrintBean selectedPrintBean;

    private List<PrintBean> vochers;
    private PrintBean selectedVocher;


    @PostConstruct
    public void init() {
        //importToDB();//获取最新数据
    }

    /**
     * 统计数据
     */
    public void stasticData() {
        try {
            printBeans = commonService.getPrintBeans();
        } catch (DataAccessException e) {
            MessageUtil.addError("数据库连接失败!");
            logger.error(new Date().toString() + " 数据库连接失败!");
        }
    }

    /**
     * 导入到本地数据库
     */
    public void importToDB() {
        try {
            int num = commonService.importToLocalDB();
            MessageUtil.addInfo("导入" + num + "条数据!");
        } catch (Exception e) {
            MessageUtil.addError("数据库连接失败!");
            logger.error(new Date().toString() + " 数据库连接失败!");
        }
    }


    /**
     * 打印凭证
     */
    public void printVoucher(PrintBean bean) {
        try {
            //printPdf();
            commonService.addVocherInfo(bean);
            printBeans.remove(bean);
            MessageUtil.addInfo("执行成功!");
        } catch (DataAccessException e) {
            MessageUtil.addError("sql执行失败!");
            logger.error(new Date().toString() + " sql执行失败!");
        }
    }

    /**
     * 打印pdf文件
     */
    public void printPdf() {

    }

    /**
     * 记账页面信息检索
     */
    public void obtainVocherInfos() {
        try {
            vochers = commonService.obtainVocherInfos();
            for (PrintBean bean : vochers){
            }
        } catch (DataAccessException e) {
            MessageUtil.addError("sql执行失败!");
            logger.error(new Date().toString() + " sql执行失败!");
        }
    }

    /**
     * 添加前台页面显示内容
     *
     * @param msgStr
     */
    public void addMessage(FacesMessage.Severity severity, String msgStr) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msgStr, msgStr));
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public List<PrintBean> getPrintBeans() {
        return printBeans;
    }

    public void setPrintBeans(List<PrintBean> printBeans) {
        this.printBeans = printBeans;
    }

    public PrintBean getSelectedPrintBean() {
        return selectedPrintBean;
    }

    public void setSelectedPrintBean(PrintBean selectedPrintBean) {
        this.selectedPrintBean = selectedPrintBean;
    }

    public List<PrintBean> getVochers() {
        return vochers;
    }

    public void setVochers(List<PrintBean> vochers) {
        this.vochers = vochers;
    }

    public PrintBean getSelectedVocher() {
        return selectedVocher;
    }

    public void setSelectedVocher(PrintBean selectedVocher) {
        this.selectedVocher = selectedVocher;
    }
}

