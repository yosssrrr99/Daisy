/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

/**
 *
 * @author DELL
 */


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Main {
   
        public static void sendMail(String recipient) throws Exception{
       System.out.println("preparing");
       Properties properties = new Properties();
     properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable","true");
properties.put("mail.smtp.host","smtp.gmail.com");
properties.put("mail.smtp.port","587");
properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
 String username = "mrabetmohamedmahdi@gmail.com";
 String password = "gg.123789456";
     
     
     Session session = Session.getDefaultInstance(properties,new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication(){
           return new PasswordAuthentication(username,password);  
         }
         
});
     Message message= prepareMessage(session,username,recipient);
     Transport.send(message);
       System.out.println("mesaage teb3ath");
     
   }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
       try {
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(myAccountEmail));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress( recipient));
           
           message.setSubject("my first email");
           message.setText("loooooooook");
           return message;
       }catch(Exception ex){
           System.out.println(ex.getMessage());
           
    }
     return null;
   }
    }
//        final String username = "yosr.sahnoun1@esprit.tn";
//        final String password = "213JFT4074";
//
//        Properties prop = new Properties();
//	prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "465");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.socketFactory.port", "465");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        
//        Session session = Session.getInstance(prop,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("yosr.sahnoun1@esprit.tn"));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse("mrabetmohamedmahdi@gmail.com")
//            );
//            message.setSubject("Testing Gmail SSL");
//            message.setText("Dear Mail Crawler,"
//                    + "\n\n Please do not spam my email!");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }

    
//   public static void sendMail(String recipient) throws Exception{
//       System.out.println("preparing");
//       Properties properties = new Properties();
//     properties.put("mail.smtp.auth","true");
//     properties.put("mail.smtp.starttls.enable", "true");
//     properties.put("mail.smtp.host", "true");
//     properties.put("mail.smtp.port", "587");
//     String myAccountEmail="yosr.sahnoun1@esprit.tn";
//     String password ="213JFT4074";
//     
//     
//     Session session = Session.getDefaultInstance(properties,new Authenticator() {
//         @Override
//         protected PasswordAuthentication getPasswordAuthentication(){
//           return new PasswordAuthentication(myAccountEmail,password);  
//         }
//         
//});
//     Message message= prepareMessage(session,myAccountEmail,recipient);
//     Transport.send(message);
//       System.out.println("mesaage teb3ath");
//     
//   }
//
//    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
//       try {
//           Message message = new MimeMessage(session);
//           message.setFrom(new InternetAddress(myAccountEmail));
//           message.setRecipient(Message.RecipientType.TO, new InternetAddress( recipient));
//           
//           message.setSubject("my first email");
//           message.setText("loooooooook");
//           return message;
//       }catch(Exception ex){
//           System.out.println(ex.getMessage());
//           
//    }
//     return null;
//   }
   


