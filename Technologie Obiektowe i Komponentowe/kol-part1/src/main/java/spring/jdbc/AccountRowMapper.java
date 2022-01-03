package spring.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import spring.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccountNo(resultSet.getString(1));
        account.setBalance(resultSet.getBigDecimal(2));
        account.setLastPaidOn(resultSet.getDate(3));

        return account;
    }
}
