package pl.pwsztar.services.models;

import pl.pwsztar.services.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;

public class TablesDb {
  public static List<String> getAll() {
    List<String> tables = new ArrayList<>();
    try {
      tables = DatabaseConnection.getTables();
    } catch (Exception e) {
      return tables;
    }
    return tables;
  }
}
