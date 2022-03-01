/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.services;

import edu.cine.entities.Film;
import edu.cine.entities.Realisateur;
import edu.cine.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class RealisateurCRUD {
    Connection cnx;
    public RealisateurCRUD(){
        cnx = MyConnection.getInstance().getCnx();
    }
     public void ajouterRea(Realisateur rr){
        String requete = "INSERT INTO `realisateur`(`idC`, `NomOrg`)"
                + "VALUES (?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(requete);
            ps.setInt(1,rr.getIdC());
            ps.setString(2,rr.getNomOrg());
            ps.executeUpdate();
            System.out.println("realisateur ajoutée dynamiquement");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
 
    public void modifierRea(int NumRea, int idC, String NomOrg) {
        
        
        try {
            String req = "UPDATE realisateur SET "
                    
                    + "NomOrg = ? "
                    + "WHERE NumRea = ?";

            PreparedStatement prepare = cnx.prepareStatement(req);
           
            prepare.setString(1, NomOrg);
           prepare.setInt(2, NumRea);

            prepare.executeUpdate();
            System.out.println("modification du realisateur ");

        } catch (SQLException e) {
            
            System.out.println("\"Aucune modification du realisateur ");

        }
        
    }
     public List<Realisateur> afficheRea() {
        List<Realisateur> listeRea = new ArrayList();
        try {
            String requete = "SELECT * FROM realisateur";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Realisateur r= new Realisateur();

               r.setNumRea(res.getInt("NumRea"));
               r.setNomOrg(res.getString("NomOrg"));
               listeRea.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return listeRea;
    }
     
      public void désactiverRea(int NumRea) {
        
        try {
            String requete = "DELETE FROM realisateur WHERE NumRea = ?";
            PreparedStatement prepare = cnx.prepareStatement(requete);
            prepare.setInt(1, NumRea);
            int nbEnregSup = prepare.executeUpdate();
            System.out.println("suppression");
            
        } catch (SQLException e) {
            System.out.println("Aucune suppression");
            
        }
        
    }
      
      
}
