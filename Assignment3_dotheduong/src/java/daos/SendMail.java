/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public class SendMail {
    public int sendMail(String toEmail){
        // String to = "asd12563241@gmail.com";
      // Sender's email ID needs to be mentioned
      String from = "assignmentse141129@gmail.com";
      Random generator = new Random();
      int codeVerify = generator.nextInt((9999 - 1000) + 1) + 1000;
        try {
            
            Properties props = new Properties();
            props.put("mail.smtp.user", "username");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "25");
            props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.EnableSSL.enable", "true");

            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            
//            props.put("mail.transport.protocol", "smtp");
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.port", "25");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable","true");
            javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication("assignmentse141129@gmail.com", "duongpro026");
                }
            };
            Session sess = Session.getDefaultInstance(props, authenticator);
            sess.setDebug(true);
            Transport transport = sess.getTransport("smtp");
            Message msg = new MimeMessage(sess);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Hello JavaMail");
            msg.setText(""+codeVerify);
            transport.connect();
            transport.send(msg);
            return codeVerify;
        } catch (Exception e) {
            System.out.println("err" + e);
        }
        return codeVerify;
    }
}
