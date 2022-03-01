/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.services;

import entities.Compte;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utiles.MyConnection;

/**
 *
 * @author 21653
 */
public class CompteCrud {
    Connection cnx2;
    public CompteCrud() {
       cnx2 = MyConnection.getInstance().getCnx();
    }
    public void ajouterCompte(Compte c){
        try {
            String requete2= "INSERT INTO `compte` (userName, Nom, Prenom, mail, mdp, role, Image) " + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, c.getUsername());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getPrenom());
            pst.setString(4, c.getMail());
            pst.setString(5, c.getMdp());
            pst.setString(6, c.getRole());
            pst.setString(7, c.getImage());
            pst.executeUpdate();
            System.out.println("Votre Compte a été ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public List<Compte> consulterCompte() {
        List<Compte> myListe = new ArrayList<>();
        try {
            String requete5 = "SELECT * FROM Compte ";
            Statement st = cnx2.createStatement();
            st.executeQuery(requete5);
            ResultSet res = st.executeQuery(requete5);
            while (res.next()) {
                Compte c = new Compte();

                c.setUsername(res.getString("userName"));
                c.setMail(res.getString("mail"));
                c.setMdp(res.getString("mdp"));
                c.setRole(res.getString("role"));
                c.setImage(res.getString("Image"));
                
                myListe.add(c);
            }
            System.out.println("Compte consulté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myListe;

    }
    public Compte authentifier(String username, String mdp) {
        Compte c = new Compte();
        System.out.println(username);
        try {
            String requete6 = "SELECT * FROM compte WHERE userName = ?";
            PreparedStatement pst = cnx2.prepareStatement(requete6);
            pst.setString(1, username);
            
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                
                c.setUsername(res.getString("userName"));
                c.setMail(res.getString("mail"));
                c.setMdp(res.getString("mdp"));
                c.setRole(res.getString("role"));
                c.setImage(res.getString("Image"));
                if(c.Authentification(username, mdp)==1)
        return c;
        else{
            System.out.println("incorrect");
        return null;}
                
               
            }
            System.out.println("Compte consulté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        if(compte.Authentification(username, mdp)==1)
//        return compte;
//        else{
//            System.out.println("incorrect");
 return null;
   }

    public boolean supprimerCompte(String username) {
        boolean cSup = true;
        try {
            String requete3 = "DELETE FROM Compte WHERE username = ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);

            pst.setString(1, username);
            pst.executeUpdate();
            System.out.println("Compte supprimé");
        } catch (SQLException ex) {
            cSup = false;
            System.err.println(ex.getMessage());
        }
        return cSup;

    }
    public boolean modifierCompte(String username, String mail,int mdp, String image) {
        boolean cModif = true;
        try {
            String requete4 = "UPDATE Compte SET mail= ? , mdp= ? , image = ? WHERE username= ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete4);
            
            pst.setString(1, mail);
            pst.setInt(2, mdp);
            pst.setString(3, image);
            pst.setString(4, username);

            pst.executeUpdate();
            System.out.println("Compte modifié");

        } catch (SQLException ex) {
            cModif = false;
            System.err.println(ex.getMessage());
        }
        return cModif;

    }
public String random(String Role){
        
        int Max = 4000;
    int Min = 0001;
    int x = (int) (Min + (Math.random() * (Max - Min)))/10;
    int y = (int) (Min + (Math.random() * (Max - Min)));
    
    if (Role.equals("Etudiant")){
    String username = Integer.toString(x)+"ETU"+Integer.toString(y);
        System.out.println("Votre ID est :"+username); 
        return username;
    }else {
        if (Role.equals("Client")){
    String username = Integer.toString(x)+"CLI"+Integer.toString(y);   
     System.out.println("Votre username est :"+username); 
      return username;
    
}

    }
       return Role;
         } 

    
}
    


    
    
    
    
    
    
       

