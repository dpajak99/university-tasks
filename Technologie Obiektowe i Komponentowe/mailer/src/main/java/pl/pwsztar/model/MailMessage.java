package pl.pwsztar.model;

import java.util.List;

public class MailMessage {
    private String subject;
    private String text;

    private List<String> recipients;

    public MailMessage() {
    }

    public MailMessage(String subject, String text, List<String> recipients) {
        this.subject = subject;
        this.text = text;
        this.recipients = recipients;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getRecipients() {
        return String.join(",", recipients);
    }
}
