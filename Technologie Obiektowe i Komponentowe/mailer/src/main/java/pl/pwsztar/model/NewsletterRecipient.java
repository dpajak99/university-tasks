package pl.pwsztar.model;

public class NewsletterRecipient {
  private int id;
  private String email;
  private boolean termsAccepted;
  private String termsAcceptedString;

  public NewsletterRecipient(int id, String email, boolean termsAccepted) {
    this.id = id;
    this.email = email;
    this.termsAccepted = termsAccepted;
    this.termsAcceptedString = termsAccepted ? "[+]" : "[-]";
  }

  public void setTermsAccepted(boolean termsAccepted) {
    this.termsAccepted = termsAccepted;
    this.termsAcceptedString = this.termsAccepted ? "[+]" : "[-]";
  }

  public String getTermsAcceptedString() {
    return termsAcceptedString;
  }

  public void setTermsAcceptedString(String termsAcceptedString) {
    this.termsAcceptedString = termsAcceptedString;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isTermsAccepted() {
    return termsAccepted;
  }

  @Override
  public String toString() {
    return "NewsletterRecipient{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", termsAccepted=" + termsAccepted +
      '}';
  }
}
