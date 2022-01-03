package pl.dpajak.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.dao.csv.CsvAccountDao;
import spring.jdbc.impl.AccountServiceImp;
import spring.model.Account;
import spring.service.AccountService;

import java.util.List;

public class ConsoleApp {

  public static void main(String[] args) throws Exception {
    System.out.println("Start");
    ApplicationContext appCtx =
      new ClassPathXmlApplicationContext("applicationContext.xml");

    AccountServiceImp accountServiceImp = (AccountServiceImp)appCtx.getBean("AccountServiceImp");
    AccountService accountService = (AccountService) appCtx.getBean("accountService");

    List<Account> delinquentAccountsDb = accountServiceImp.findAll();
    for (Account a : delinquentAccountsDb) {
      System.out.println("Klient baza danych:" + a.getAccountNo());
    }

    List<Account> delinquentAccounts = accountService.findDeliquentAccounts();
    for (Account a : delinquentAccounts) {
      System.out.println("Niesolidny klient:" + a.getAccountNo());
    }
  }
}
