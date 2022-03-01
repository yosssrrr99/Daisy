/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.testd;


import edu.cinepro.services.ClientCrud;
import edu.cinepro.services.CompteCrud;
import edu.cinepro.services.EtudiantCrud;
import entities.Client;
import entities.Compte;
import entities.Etudiant;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Base64;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import utiles.MyConnection;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author 21653
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MyConnection cnx2;
        cnx2 = new MyConnection();
        
     CompteCrud cd = new CompteCrud();
     cd.random("Client");
     
     
     
     
//        
Compte c1 = new Compte("233CLI1389", "amine","zitoun", "Amine@esprit","lalalal", "Client", "jjj");
// Compte c2 = new Compte("213JMT3002", "yosr@esprit","yoyo", "Client", "aaa" );
// Compte c3 = new Compte("213JMT3000", "sarra@esprit","soso", "Etudiant", "bbb" );
       cd.ajouterCompte(c1);
//    cd.ajouterCompte(c2);
//    cd.ajouterCompte(c3);
//    
//    System.out.println("authentification "+c1.Authentification("213JMT3001", "lalalal")+" \n");
//        List<Compte> myListe = cd.consulterCompte();
//        
////        MyConnection m1 = MyConnection.getInstance();
////        MyConnection m2 = MyConnection.getInstance();
////        System.out.println(m1.hashCode() + "  ----  "+m2.hashCode());
//       
//        
//        cd.supprimerCompte("213JMT3002");
//       
////        cd.modifierCompte("213JMT3002" , "yosr1@esprit",1222, "aaa");
////        System.out.println(c2);
//        
//        
//
//       ClientCrud ccd= new ClientCrud();
//       
////       Client c = new Client("yosr", "sahnoun", "1999/11/27", "213JMT3002");
////
////       ccd.ajouterClient(c); 
//       
////       System.out.println(ccd.consulterClient());
//       
////        ccd.modifierClient(44444,"yosr", "sahnoun", "1999/11/27", "213JMT3002");
////        System.out.println(ccd.modifierClient(44524, "213JMT3002", "yoser", "Sahnoun", "1999/11/27"));
//        
//        
//          EtudiantCrud ec = new EtudiantCrud();
////        Etudiant e1 = new Etudiant(14556, 44524);
////        ec.ajouterEtudiant(e1);
////        ec.supprimerEtudiant(14556);
//        System.out.println(ec.modifierEtudiant(14558, 44524));
//
////        System.out.println(c1.getMdp());
////        
//        
//        
//        String password ="azerty";
//        String salt="sjkdfhzefu";
//        byte[] hashed = null;
//        PBEKeySpec spec;
//        spec = new PBEKeySpec(password.toCharArray()
//                ,salt.getBytes(),100,256);
//        try{
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//             hashed = skf.generateSecret(spec).getEncoded();
//        }catch(NoSuchAlgorithmException | InvalidKeySpecException e){
//            
//        }
//        String securedPassword = Base64.getEncoder().encodeToString(hashed);
//        System.out.print(securedPassword+"\n");
//        String newPassword = "azerty";
//        byte[] newHashed =null;
//        spec = new PBEKeySpec(newPassword.toCharArray()
//                ,salt.getBytes(),100,256);
//        try{
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//             newHashed = skf.generateSecret(spec).getEncoded();
//        }catch(NoSuchAlgorithmException | InvalidKeySpecException e){
//            
//        }
//        String newSecuredPassword = Base64.getEncoder().encodeToString(newHashed);
//        System.out.print(newSecuredPassword.equalsIgnoreCase(securedPassword)+"\n");
//        
//        String dateNaisse = "1998/07/17";
//        String[] arrayString = dateNaisse.split("/");
//        int  arrayInt [] = {0,0,0};
//        for(int i=0 ; i<arrayString.length;i++){
//            arrayInt[i] = Integer.parseInt(arrayString[i]);
//      }
//        LocalDate date = LocalDate.of(arrayInt[0], arrayInt[1], arrayInt[2]);
//        LocalDate now = LocalDate.now();
//        Period period2 = Period.between(date, now);
//        System.out.println("L'age  est : "+period2.getYears()+"ans "+" et " +period2.getMonths()+"mois" +period2.getDays()+"jours");
//    
//
//    }
//        
//    }



//public class SendEmail {
//    public static void sendMail(String recepient){
//    Properties properties = new Properties();
//
//    properties.put("mail.stp.auth", "true");
//    properties.put("mail.stp.statrttls.enable", "true");
//    properties.put("mail.stp.host", "stmp.gmail.com");
//    properties.put("mail.stp.port", "587");
//    String myAccountEmail="salemzitoun.medamine@esprit.tn";
//    String password = "istic2021";
//    Session session = Session.getInstance(properties, new Authenticator() {
//    @Override
//        protected PasswordAuthentification getPasswordAuthentification(){
//            return new PasswordAuthentication(myAccountEmail, password );
//            
//        }
//        Message message = prepareMessage ();
//        
//    }
//            
//}
//    )
//    
//      
//      // Recipient's email ID needs to be mentioned.
//      String to = "medamine.salemzitoun@esprit.tn";
//
//      // Sender's email ID needs to be mentioned
//      String from = "web@gmail.com";
//
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//
//      // Get system properties
//      Properties properties = System.getProperties();
//
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", host);
//
//      // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);
//
//      try {
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//         // Set Subject: header field
//         message.setSubject("This is the Subject Line!");
//
//         // Now set the actual message
//         message.setText("This is actual message");
//
//         // Send message
//         Transport.send(message);
//         System.out.println("Sent message successfully....");
//      } catch (MessagingException mex) {
//         mex.printStackTrace();
//      }
   }
    
}

    
    
    
    
    
      
       
      
        
        
        
    
 
        
    
        
    


