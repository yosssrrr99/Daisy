/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.services;

import entities.Client;
import entities.Compte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import utiles.MyConnection;

/**
 *
 * @author 21653
 */
public class ClientCrud {
    Connection cnx2;
    public ClientCrud(){
         cnx2 = MyConnection.getInstance().getCnx();
    }
       
    
       public void ajouterClient(Client c){
        try {
            String requete= "INSERT INTO client(nom, prenom, Datenaiss, userName)" + "VALUES (?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getDatenaiss());
            pst.setString(4, c.getUsername());
            pst.executeUpdate();
            System.out.println("Votre Client a été ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       }
       public List<Client> consulterClient() {
        List<Client> myListe1 = new ArrayList<>();

        try {
            String requete2 = "SELECT * FROM client ";
            Statement st = cnx2.createStatement();
            st.executeQuery(requete2);
            ResultSet res = st.executeQuery(requete2);

            while (res.next()) {
                Client c = new Client();

                
                c.setNom(res.getString("nom"));
                c.setPrenom(res.getString("prenom"));
                c.setDatenaiss(res.getString("DateNaiss"));
                c.setUsername(res.getString("userName"));
                myListe1.add(c);
            }
            System.out.println("Compte consulté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myListe1;
    }
       
    
       public boolean modifierClient( int idC,String username,String nom, String prenom, String dateNaiss) {
        boolean cModif = true;
        try {
            String requete3 = "UPDATE client SET nom= ? , prenom= ? , dateNaiss = ?, userName= ? WHERE idC= ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, dateNaiss);
            pst.setString(4, username);
            pst.setInt(5, idC);
            

            pst.executeUpdate();
            System.out.println("Client modifié"); 

        } catch (SQLException ex) {
            cModif = false;
            System.err.println(ex.getMessage());
        }
        return cModif;

    }
       public boolean supprimerClient(String username) {
        boolean cSup = true;
        try {
            String requete3 = "DELETE FROM client WHERE username = ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);

            pst.setString(1, username);
            pst.executeUpdate();
            System.out.println("Client supprimé");
        } catch (SQLException ex) {
            cSup = false;
            System.err.println(ex.getMessage());
        }
        return cSup;

    }
       
       
  }


    

    
