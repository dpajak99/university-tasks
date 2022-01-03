package spring.dao;

import spring.model.Account;
import java.util.List;

public interface AccountDao	{
  List<Account> findAll() throws	Exception;
}
