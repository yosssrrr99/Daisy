/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.services;

import entities.Compte;
import entities.Etudiant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utiles.MyConnection;

/**
 *
 * @author 21653
 */
public class EtudiantCrud {
  Connection cnx2;
    public EtudiantCrud() {
       cnx2 = MyConnection.getInstance().getCnx();
    }
    public void ajouterEtudiant(Etudiant e){
        try {
            String requete1= "INSERT INTO Etudiant(NumInscri, idC)" + "VALUES (?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete1);
            pst.setInt(1, e.getNumInscri());
            pst.setInt(2, e.getIdC());
            
            pst.executeUpdate();
            System.out.println("Votre Etudiant a été ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public List<Etudiant> consulterEtudiant() {
        List<Etudiant> myListe = new ArrayList<>();

        try {
            String requete2 = "SELECT * FROM Etudiant ";
            Statement st = cnx2.createStatement();
            st.executeQuery(requete2);
            ResultSet res = st.executeQuery(requete2);

            while (res.next()) {
                Etudiant e = new Etudiant();

                e.setNumInscri(res.getInt("NumInscri"));
                e.setIdC(res.getInt("idC"));
                
                
                myListe.add(e);
            }
            System.out.println("Etudiant consulté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myListe;

    }
    public boolean supprimerEtudiant(int numinscri) {
        boolean eSup = true;
        try {
            String requete3 = "DELETE FROM Etudiant WHERE numinscri = ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);

            pst.setInt(1, numinscri);
            pst.executeUpdate();
            System.out.println("Votre étudiant a été supprimé");
        } catch (SQLException ex) {
            eSup = false;
            System.err.println(ex.getMessage());
        }
        return eSup;

    }
    public boolean modifierEtudiant(int NumInscri, int idC) {
        boolean eModif = true;
        try {
            String requete4 = "UPDATE Etudiant SET idC= ?  WHERE NumInscri= ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete4);
            
            
            pst.setInt(1, idC);
            pst.setInt(2, NumInscri);
            

            pst.executeUpdate();
            System.out.println("Etudiant modifié");

        } catch (SQLException ex) {
            eModif = false;
            System.err.println(ex.getMessage());
        }
        return eModif;

    }
    
  
}
