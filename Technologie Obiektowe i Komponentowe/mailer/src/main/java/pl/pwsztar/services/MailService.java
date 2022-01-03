package pl.pwsztar.services;

import pl.pwsztar.model.MailMessage;
import pl.pwsztar.utils.EnvUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {
    /**
     * Method to send mail message
     * @param mailMessage
     */
    public static void sendMessage(MailMessage mailMessage) {
        String username, password;
        Properties props = EnvUtils.getProperties();
        if( props == null) {
            return;
        }

        try {
            username = props.getProperty("mail.address");
            password = props.getProperty("mail.password");
        } catch (Exception e) {
            return;
        }

        Properties prop = new Properties();
        prop.put("mail.smtp.host", props.getProperty("mail.smtp.host"));
        prop.put("mail.smtp.port", props.getProperty("mail.smtp.port"));
        prop.put("mail.smtp.auth", props.getProperty("mail.smtp.auth"));
        prop.put("mail.smtp.socketFactory.port", props.getProperty("mail.smtp.socketFactory.port"));
        prop.put("mail.smtp.socketFactory.class", props.getProperty("mail.socketFactory.class"));
        prop.put("mail.smtp.starttls.enable", props.getProperty("mail.starttls.enable"));
        prop.put("mail.mime.charset", "UTF-8");

        String finalUsername = username;
        String finalPassword = password;
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(finalUsername, finalPassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailMessage.getRecipients())
            );
            message.setSubject(mailMessage.getSubject());
            message.setContent(mailMessage.getText(), "text/html; charset=UTF-8");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
