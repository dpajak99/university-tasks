package pl.pwsztar.services.models;

import pl.pwsztar.model.NewsletterRecipient;
import pl.pwsztar.services.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewsletterDb {
  public static List<NewsletterRecipient> getAll() {
    List<NewsletterRecipient> newsletterRecipients = new ArrayList<>();
    List<Map<String, Object>> databaseResult;

    try {
      databaseResult = DatabaseConnection.doQuery("SELECT * FROM newsletter.email");
    } catch (Exception e) {
      return newsletterRecipients;
    }
    for(Map<String,Object> singleRecipientMap : databaseResult ) {
      newsletterRecipients.add(new NewsletterRecipient(
        Integer.parseInt((String) singleRecipientMap.get("id")),
        (String) singleRecipientMap.get("address"),
        (singleRecipientMap.get("terms_accepted")).equals("t")
      ));
    }
    return newsletterRecipients;
  }

  public static void update(NewsletterRecipient newsletterRecipient) {
    try {
      DatabaseConnection.doVoidQuery("UPDATE newsletter.email SET terms_accepted = " + newsletterRecipient.isTermsAccepted() + " WHERE id = " + newsletterRecipient.getId());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public static void remove(NewsletterRecipient newsletterRecipient) {
    try {
      DatabaseConnection.doVoidQuery("DELETE FROM newsletter.email WHERE id = " + newsletterRecipient.getId());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
  public static void insert(String email) {
    try {
      DatabaseConnection.doVoidQuery("INSERT INTO newsletter.email (address, terms_accepted) VALUES('" + email + "', true)");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
