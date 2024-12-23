package com.ra.module5_project.service.sendMail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class ActiveAccount {
    public static void activeAccount(String email,long code){
        // Thiết lập các thuộc tính cho phiên
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tạo một phiên
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("huongcaoha1994@gmail.com", "ktmu mqrt zutl oaie");
            }
        });
        try {
            // Tạo một email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("huongcaoha1994@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Active Account");
            message.setText("http://localhost:8080/api.myService.com/v1/auth/active-account/"+email+"/verify/"+code);
            // Gửi email
            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
