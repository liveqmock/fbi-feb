package inr.rowmapper;

import inr.bean.PrintBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintBeanRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        PrintBean printBean = new PrintBean(resultSet.getInt("idOne"), resultSet.getInt("idTwo"),
                resultSet.getString("acntcodeone"), resultSet.getString("acntnameone"), resultSet.getString("acntcodetwo"),
                resultSet.getString("acntnametwo"), resultSet.getBigDecimal("creditamt"), resultSet.getBigDecimal("debitamt"),
                resultSet.getString("remarkone"), resultSet.getString("remarktwo"), resultSet.getString("curcodeone"),
                resultSet.getString("curcodetwo"), resultSet.getDate("bizdate"), resultSet.getString("remark2"),
                resultSet.getString("csmothername"), resultSet.getBigDecimal("loanrate"), resultSet.getDate("termdate"));
        return printBean;
    }
}
