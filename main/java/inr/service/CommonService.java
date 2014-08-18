package inr.service;


import inr.bean.PrintBean;
import inr.rowmapper.PrintBeanRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;

@Service
@Transactional
public class CommonService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public int importToLocalDB() throws Exception {
        String sql = "INSERT INTO w06_sta_glentry (id,BIZ_DATE,ASSET_DEBT_ID,CONTRACT_NO,LNCI_NO,REPAYMENT_TYPE,ENTRY_INDEX,REMARK,LEDGER_CODE,LEDGER_NAME,ACCOUNTINT_CODE,ACCOUNTINT_NAME,ACNT_CODE,ACNT_NAME,CUR_CODE,DEBIT_AMT,CREDIT_AMT,DATA_SOURCES,INPUT_DATETIME,CSM_OTHER_NAME,LOAN_RATE,ISSUE_DATE,TERM_DATE,REMARK2,REMARK3) " +
                "SELECT id,BIZ_DATE,ASSET_DEBT_ID,CONTRACT_NO,LNCI_NO,REPAYMENT_TYPE,ENTRY_INDEX,REMARK,LEDGER_CODE,LEDGER_NAME,ACCOUNTINT_CODE,ACCOUNTINT_NAME,ACNT_CODE,ACNT_NAME,CUR_CODE,DEBIT_AMT,CREDIT_AMT,DATA_SOURCES," +
                " INPUT_DATETIME,CSM_OTHER_NAME,LOAN_RATE,ISSUE_DATE,TERM_DATE,REMARK2,REMARK3 " +
                "FROM bi.w06_sta_glentry@bidata where id not in(select id from w06_sta_glentry)";
        return jdbcTemplate.update(sql);
    }

    public List<PrintBean> getPrintBeans() throws DataAccessException {
        String sql = "select w1.id as idOne," +
                "w2.id as idTwo," +
                "w1.acnt_code as acntCodeOne," +
                " w1.acnt_name as acntNameOne," +
                " w2.acnt_code as acntCodeTwo," +
                " w2.acnt_name as acntNameTwo," +
                " w2.credit_amt as creditAmt," +
                " w1.debit_amt as debitAmt," +
                " w2.remark as remarkTwo," +
                " w1.remark as remarkOne," +
                " w1.cur_code as curCodeOne," +
                " w2.cur_code as curCodeTwo," +
                " w1.biz_date as bizDate," +
                " w1.remark2 as remark2," +
                " w1.csm_other_name as csmOtherName," +
                " w1.loan_rate as loanRate," +
                " w1.term_date as termDate" +
                " from w06_sta_glentry w1, w06_sta_glentry w2" +
                " where w1.debit_amt = w2.credit_amt" +
                " and w1.input_datetime = w2.input_datetime" +
                " and (w1.remark=w2.remark or (w1.remark is null and w2.remark is null)) and w1.prflag=0 and w2.prflag = 0";
        return jdbcTemplate.query(sql, new PrintBeanRowMapper());
    }

    /*@Transactional(propagation = Propagation.REQUIRED)
    public void addVocherInfo(PrintBean selectedPrintBean) throws DataAccessException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "insert into printdata (IDONE,IDTWO,ACNTCODEONE,ACNTNAMEONE,ACNTCODETWO,ACNTNAMETWO,CREDITAMT,DEBITAMT,REMARKONE,REMARKTWO,CURCODEONE,CURCODETWO,BIZDATE,REMARK2,CSMOTHERNAME,LOANRATE,TERMDATE) values (" + selectedPrintBean.getIdOne() + "," + selectedPrintBean.getIdTwo() + ",'" + selectedPrintBean.getAcntCodeOne() + "','" + selectedPrintBean.getAcntNameOne() + "','" + selectedPrintBean.getAcntCodeTwo() + "','" + selectedPrintBean.getAcntNameTwo() + "'," + selectedPrintBean.getCreditAmt() + "," + selectedPrintBean.getDebitAmt() + ",'" + selectedPrintBean.getRemarkOne() + "','" + selectedPrintBean.getRemarkTwo() + "','" + selectedPrintBean.getCurCodeOne() + "','" + selectedPrintBean.getCurCodeTwo() + "',to_date('" + format.format(selectedPrintBean.getBizDate()) + "','yyyy-mm-dd'),'" + selectedPrintBean.getRemark2() + "','" + selectedPrintBean.getCsmOtherName() + "'," + selectedPrintBean.getLoanRate() + ",to_date('" + format.format(selectedPrintBean.getTermDate()) + "','yyyy-mm-dd'))";
        String upSql = "update w06_sta_glentry t  set t.PRFLAG = 1 where t.id = " + selectedPrintBean.getIdOne() + " or t.id = " + selectedPrintBean.getIdTwo();
        jdbcTemplate.update(sql);
        jdbcTemplate.update(upSql);
    }*/
    @Transactional(propagation = Propagation.REQUIRED) //事务
    public void addVocherInfo(PrintBean selectedPrintBean) throws DataAccessException {
        String sql = "insert into printdata (IDONE,IDTWO,ACNTCODEONE,ACNTNAMEONE,ACNTCODETWO,ACNTNAMETWO,CREDITAMT,DEBITAMT,REMARKONE,REMARKTWO,CURCODEONE,CURCODETWO,BIZDATE,REMARK2,CSMOTHERNAME,LOANRATE,TERMDATE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String upSql = "update w06_sta_glentry t  set t.PRFLAG = 1 where t.id = " + selectedPrintBean.getIdOne() + " or t.id = " + selectedPrintBean.getIdTwo();
        jdbcTemplate.update(sql, new Object[]{selectedPrintBean.getIdOne(), selectedPrintBean.getIdTwo(), selectedPrintBean.getAcntCodeOne(), selectedPrintBean.getAcntNameOne(), selectedPrintBean.getAcntCodeTwo(), selectedPrintBean.getAcntNameTwo(), selectedPrintBean.getCreditAmt(), selectedPrintBean.getDebitAmt(), selectedPrintBean.getRemarkOne(), selectedPrintBean.getRemarkTwo(), selectedPrintBean.getCurCodeOne(), selectedPrintBean.getCurCodeTwo(), selectedPrintBean.getBizDate(), selectedPrintBean.getRemark2(), selectedPrintBean.getCsmOtherName(), selectedPrintBean.getLoanRate(), selectedPrintBean.getTermDate()},
                new int[]{Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.DECIMAL, Types.DATE});
        jdbcTemplate.update(upSql);
    }
    public List<PrintBean> obtainVocherInfos() throws DataAccessException {//0：未打印 1：已打印
        String sql = "select IDONE,IDTWO,ACNTCODEONE,ACNTNAMEONE,ACNTCODETWO,ACNTNAMETWO,CREDITAMT,DEBITAMT,REMARKONE,REMARKTWO,CURCODEONE,CURCODETWO,BIZDATE,REMARK2,CSMOTHERNAME,LOANRATE,TERMDATE from printdata where TRFLAG = 0";
        return jdbcTemplate.query(sql, new PrintBeanRowMapper());
    }

    public void updatePrintdata(PrintBean selectedPrintBean) throws DataAccessException {
        String upSql = "update printdata t  set t.TRFLAG = 1 where t.IDONE = " + selectedPrintBean.getIdOne();
        jdbcTemplate.update(upSql);
    }
}
