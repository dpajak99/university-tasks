package spring.jdbc.impl;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import spring.dao.AccountDao;
import spring.jdbc.AccountRowMapper;
import spring.model.Account;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@Service
public class AccountServiceImp implements AccountDao {
  private static final String FIND_ALL_SQL = "select account_no, balance, last_paid_on from account";

  @Inject
  private JdbcTemplate jdbcTemplate;
  @Inject
  private AccountRowMapper accountRowMapper;

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Account> findAll() {
    return jdbcTemplate.query(FIND_ALL_SQL, accountRowMapper);
  }
}
