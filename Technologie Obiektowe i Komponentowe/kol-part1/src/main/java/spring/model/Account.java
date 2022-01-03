package spring.model;

import java.math.BigDecimal;
import java.util.Date;

public class Account	{
  private	String	accountNo;
  private BigDecimal balance;
  private Date lastPaidOn;

  public Account() {};

  public Account(String accountNo, BigDecimal balance, Date lastPaidOn) {
    this.accountNo = accountNo;
    this.balance = balance;
    this.lastPaidOn	= lastPaidOn;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public Date getLastPaidOn() {
    return lastPaidOn;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public void setLastPaidOn(Date lastPaidOn) {
    this.lastPaidOn = lastPaidOn;
  }

  @Override
  public String toString() {
    return "Account{" +
      "accountNo='" + accountNo + '\'' +
      ", balance=" + balance +
      ", lastPaidOn=" + lastPaidOn +
      '}';
  }
}
