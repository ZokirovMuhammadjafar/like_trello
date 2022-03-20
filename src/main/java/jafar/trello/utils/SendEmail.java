package jafar.trello.utils;

// Java program to send email

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class SendEmail {

    public void sendEmail(String... args) {
        String recipient = args[0];
        String sender = "Ibook2025com@gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        Session session = Session.getDefaultInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender,"lqgmrccwyrdbitph");
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("password change");
            message.setText("http://localhost:8080/auth/reset/"+args[1],"text/html");
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
