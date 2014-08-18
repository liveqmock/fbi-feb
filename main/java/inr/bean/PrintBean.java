package inr.bean;

import java.math.BigDecimal;
import java.util.Date;

public class PrintBean {
    private int idOne;             //idOne
    private int idTwo;             //idTwo
    private String acntCodeOne;    //账号一
    private String acntNameOne;    //账户名一
    private String acntCodeTwo;    //账号二
    private String acntNameTwo;    //账户名二
    private BigDecimal creditAmt;  //交易金额
    private BigDecimal debitAmt;   //交易金额
    private String remarkOne;      //摘要一
    private String remarkTwo;      //摘要二
    private String curCodeOne;     //币种一
    private String curCodeTwo;     //币种二
    private Date bizDate;          //业务的统计日期
    private String remark2;        //打印分类  01：业务打印  02：任意打印
    private String csmOtherName;   //交易对手名称
    private BigDecimal loanRate;   //合同利率
    private Date termDate;         //到期日期

    public PrintBean(int idOne, int idTwo, String acntCodeOne, String acntNameOne, String acntCodeTwo, String acntNameTwo, BigDecimal creditAmt, BigDecimal debitAmt, String remarkOne, String remarkTwo, String curCodeOne, String curCodeTwo, Date bizDate, String remark2, String csmOtherName, BigDecimal loanRate, Date termDate) {
        this.idOne = idOne;
        this.idTwo = idTwo;
        this.acntCodeOne = acntCodeOne;
        this.acntNameOne = acntNameOne;
        this.acntCodeTwo = acntCodeTwo;
        this.acntNameTwo = acntNameTwo;
        this.creditAmt = creditAmt;
        this.debitAmt = debitAmt;
        this.remarkOne = remarkOne;
        this.remarkTwo = remarkTwo;
        this.curCodeOne = curCodeOne;
        this.curCodeTwo = curCodeTwo;
        this.bizDate = bizDate;
        this.remark2 = remark2;
        this.csmOtherName = csmOtherName;
        this.loanRate = loanRate;
        this.termDate = termDate;
    }

    public int getIdOne() {
        return idOne;
    }

    public void setIdOne(int idOne) {
        this.idOne = idOne;
    }

    public int getIdTwo() {
        return idTwo;
    }

    public void setIdTwo(int idTwo) {
        this.idTwo = idTwo;
    }

    public String getAcntCodeOne() {
        return acntCodeOne;
    }

    public void setAcntCodeOne(String acntCodeOne) {
        this.acntCodeOne = acntCodeOne;
    }

    public String getAcntNameOne() {
        return acntNameOne;
    }

    public void setAcntNameOne(String acntNameOne) {
        this.acntNameOne = acntNameOne;
    }

    public String getAcntCodeTwo() {
        return acntCodeTwo;
    }

    public void setAcntCodeTwo(String acntCodeTwo) {
        this.acntCodeTwo = acntCodeTwo;
    }

    public String getAcntNameTwo() {
        return acntNameTwo;
    }

    public void setAcntNameTwo(String acntNameTwo) {
        this.acntNameTwo = acntNameTwo;
    }

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    public BigDecimal getDebitAmt() {
        return debitAmt;
    }

    public void setDebitAmt(BigDecimal debitAmt) {
        this.debitAmt = debitAmt;
    }

    public String getRemarkOne() {
        return remarkOne;
    }

    public void setRemarkOne(String remarkOne) {
        this.remarkOne = remarkOne;
    }

    public String getRemarkTwo() {
        return remarkTwo;
    }

    public void setRemarkTwo(String remarkTwo) {
        this.remarkTwo = remarkTwo;
    }

    public String getCurCodeOne() {
        return curCodeOne;
    }

    public void setCurCodeOne(String curCodeOne) {
        this.curCodeOne = curCodeOne;
    }

    public String getCurCodeTwo() {
        return curCodeTwo;
    }

    public void setCurCodeTwo(String curCodeTwo) {
        this.curCodeTwo = curCodeTwo;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
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

    public Date getTermDate() {
        return termDate;
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }
}
