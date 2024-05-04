package dormitory.emailVerifycation;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public boolean sendMail(String to, int random) {

        final String username = "dormitory374@gmail.com";
        final String password = "ctjt jgpf ucfi epec";

        String host = "smtp.gmail.com";
        int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Gmail Verification");
            message.setText("your Verification code " + random);
            Transport.send(message);
            System.out.println("sms is sent to user " + to + " successfully!");
            return true;
        } catch (MessagingException e) {
            System.out.println("cant send :-(" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendInformantMail(String to,String subject,String text) {

        final String username = "dormitory374@gmail.com";
        final String password = "ctjt jgpf ucfi epec";

        String host = "smtp.gmail.com";
        int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("sms is sent to user " + to + " successfully!");
            return true;
        } catch (MessagingException e) {
            System.out.println("cant send :-( " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
