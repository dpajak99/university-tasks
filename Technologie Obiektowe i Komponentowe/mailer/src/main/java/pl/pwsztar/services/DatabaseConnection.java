package pl.pwsztar.services;

import pl.pwsztar.utils.EnvUtils;
import pl.pwsztar.utils.StringUtils;

import java.sql.*;
import java.util.*;

public class DatabaseConnection {
  private Connection connection;

  public DatabaseConnection() {
    connection = makeConnection();
  }

  public Connection getConnection() {
    return (connection);
  }

  /**
   * Method to close database connection
   */
  public void close() {
    try {
      connection.close();
    } catch (SQLException sqle) {
      System.err.println("Blad przy zamykaniu polaczenia: " + sqle);

    }
  }

  /**
   * Method to create database connection
   * @return Connection
   */
  public Connection makeConnection() {
    Properties props = EnvUtils.getProperties();
    if (props == null) {
      return null;
    }
    String url = "jdbc:postgresql://"
      + props.getProperty("db.host")
      + ":"
      + props.getProperty("db.port")
      + "/"
      + props.getProperty("db.database");

    try {
      Class.forName("org.postgresql.Driver");
      Connection connection = DriverManager.getConnection(url, props.getProperty("db.user"), props.getProperty("db.password"));
      System.out.println("conn" + connection);
      return connection;

    } catch (ClassNotFoundException cnfe) {
      System.err.println("Error 1: Blad ladowania sterownika: " + cnfe);
      return null;
    } catch (SQLException sqle) {
      System.err.println("Error 2: Blad przy nawiązywaniu polaczenia: " + sqle);
      return null;
    }
  }

  /**
   * Method to return all tables in database
   * @return Table names in List
   * @throws SQLException SQLException
   */
  public static List<String> getTables() throws SQLException {
    String[] tableHeader = {"TABLE"};
    List<String> result = new ArrayList<>();

    DatabaseConnection dbConnection = new DatabaseConnection();
    DatabaseMetaData metaData = dbConnection.getConnection().getMetaData();
    ResultSet tables = metaData.getTables(null, null, "%", tableHeader);

    while (tables.next()) {
      result.add(tables.getString("TABLE_NAME"));
    }
    return result;
  }

  /**
   * Method to do void database query
   * @param query SQL query
   * @throws SQLException SQLException
   */
  public static void doVoidQuery(String query) throws SQLException {
    DatabaseConnection dbConnection = new DatabaseConnection();
    Statement st = dbConnection.getConnection().createStatement();
    st.execute(query);
    dbConnection.close();
  }

  /**
   * Method to do SQL Query
   * @param query SQL Query
   * @return List of result rows
   * @throws SQLException SQLException
   */
  public static List<Map<String, Object>> doQuery(String query) throws SQLException {
    DatabaseConnection dbConnection = new DatabaseConnection();
    Statement st = dbConnection.getConnection().createStatement();
    ResultSet rs = st.executeQuery(query);
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnsNumber = rsmd.getColumnCount();
    List<Map<String, Object>> result = new ArrayList<>();

    while (rs.next()) {
        Map<String, Object> row = new HashMap<>();
        for (int i = 1; i <= columnsNumber; i++) {
            String columnName = rsmd.getColumnName(i);
            String columnValue = rs.getString(i);
            row.put( columnName, columnValue);
        }
        result.add( row );
    }
    prettyResultPrint(result);
    dbConnection.close();
    return result;
  }

  /**
   * Pretty console database result print
   * @param result database result
   */
  static void prettyResultPrint(List<Map<String, Object>> result) {
      final int CharCount = 30;

      int rowSize = 0;
      boolean isFirstIteration = true;
      StringBuilder prettyResult = new StringBuilder();
      for( Map<String, Object> row : result ) {
        if( isFirstIteration) {
          for (String column : row.keySet()) {
            prettyResult.append(StringUtils.addSpacesToLength(column, CharCount));
            prettyResult.append("  |  ");
            rowSize += CharCount + 5;
          }

          prettyResult.append("\n");
          prettyResult.append("- ".repeat(Math.max(0, rowSize / 2)));
          isFirstIteration = false;
        }
        prettyResult.append("\n");
        for( String column : row.keySet()  ) {
          prettyResult.append(StringUtils.addSpacesToLength( (String) row.get(column), CharCount));
          prettyResult.append("  |  ");
        }
      }
      System.out.println(prettyResult.toString());
  }
}