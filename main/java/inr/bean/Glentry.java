package inr.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础数据
 */
public class Glentry {
    private int id;                          //ID
    private Date bizDate;                    //业务的统计日期
    private int assetDebtId;                 //生息资产、负债业务id
    private String contractNo;               //合同号
    private String lnciNo;                   //借据号
    private String rePaymentType;            //放还款类型01：放款02：还款
    private int entryIndex;                  //会计分录序号 1 第一个一借一贷的分录；2 第二个一借一贷的分录，依次类推
    private String remark;                   //摘要
    private String ledgerCode;               //总账码
    private String ledgerName;               //总账码名称
    private String accountIntcode;           //核算码
    private String accountIntName;           //核算码名称
    private String acntCode;                 //账号
    private String acntName;                 //账户名称
    private String curCode;                  //币种
    private BigDecimal debitAmt;             //交易金额
    private BigDecimal creditAmt;            //交易金额
    private String dataSources;              //01：抽取数据02：补录数据:03：导入数据
    private Date inputDateTime;              //录入时间
    private String csmOtherName;             //交易对手名称
    private BigDecimal loanRate;             //合同利率
    private Date issueDate;                  //发放日期
    private Date termDate;                   //到期日期
    private String remark2;                  //打印分类  01：业务打印  02：任意打印
    private String remark3;                  //摘要
    private int prFlag;                      //打印标记 0：未打印 1：已打印

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public int getAssetDebtId() {
        return assetDebtId;
    }

    public void setAssetDebtId(int assetDebtId) {
        this.assetDebtId = assetDebtId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getLnciNo() {
        return lnciNo;
    }

    public void setLnciNo(String lnciNo) {
        this.lnciNo = lnciNo;
    }

    public String getRePaymentType() {
        return rePaymentType;
    }

    public void setRePaymentType(String rePaymentType) {
        this.rePaymentType = rePaymentType;
    }

    public int getEntryIndex() {
        return entryIndex;
    }

    public void setEntryIndex(int entryIndex) {
        this.entryIndex = entryIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLedgerCode() {
        return ledgerCode;
    }

    public void setLedgerCode(String ledgerCode) {
        this.ledgerCode = ledgerCode;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public String getAccountIntcode() {
        return accountIntcode;
    }

    public void setAccountIntcode(String accountIntcode) {
        this.accountIntcode = accountIntcode;
    }

    public String getAccountIntName() {
        return accountIntName;
    }

    public void setAccountIntName(String accountIntName) {
        this.accountIntName = accountIntName;
    }

    public String getAcntCode() {
        return acntCode;
    }

    public void setAcntCode(String acntCode) {
        this.acntCode = acntCode;
    }

    public String getAcntName() {
        return acntName;
    }

    public void setAcntName(String acntName) {
        this.acntName = acntName;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public BigDecimal getDebitAmt() {
        return debitAmt;
    }

    public void setDebitAmt(BigDecimal debitAmt) {
        this.debitAmt = debitAmt;
    }

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getDataSources() {
        return dataSources;
    }

    public void setDataSources(String dataSources) {
        this.dataSources = dataSources;
    }

    public Date getInputDateTime() {
        return inputDateTime;
    }

    public void setInputDateTime(Date inputDateTime) {
        this.inputDateTime = inputDateTime;
    }

    public String getCsmOtherName() {
        return csmOtherName;
    }

    public void setCsmOtherName(String csmOtherName) {
        this.csmOtherName = csmOtherName;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getTermDate() {
        return termDate;
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public int getPrFlag() {
        return prFlag;
    }

    public void setPrFlag(int prFlag) {
        this.prFlag = prFlag;
    }
}
