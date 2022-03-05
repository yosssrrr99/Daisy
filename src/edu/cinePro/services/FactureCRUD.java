/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;


import entities.Facture;
import edu.cinePro.entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.cinePro.utils.MyConnection;

/**
 *
 * @author hp
 */
public class FactureCRUD {

    Connection cnx2;

    public FactureCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterFacture() {
        try {
            float t = 01;
            String requete = "INSERT INTO facture(idFacture,DateCreation,Total,idPanier) VALUES (40,NOW(),t,1)";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("facture ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouterFacture2(Facture F) {
        try {
            String requete2 = "INSERT INTO facture(idFacture,DateCreation,Total,idPanier) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, F.getIdFacture());
            pst.setString(2, F.getDateCreation());
            pst.setFloat(3, F.getTotal());
            pst.setString(4, F.getIdPanier());
            pst.executeUpdate();
            System.out.println("facture avec la méthode 2 ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Facture> afficherFacture(Facture f) {
        List<Facture> myListe = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM facture WHERE `IdPanier` = ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setString(1,f.getIdPanier());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //Facture f = new Facture();
                f.setIdFacture(rs.getInt(1));
                f.setDateCreation(rs.getString("DateCreation"));
                f.setTotal(rs.getFloat(3));
                f.setIdPanier(rs.getString(4));
                myListe.add(f);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myListe;
    }

 

    public void DeleteFacture() {
        try {
            String requete4 = "DELETE FROM `facture` WHERE `idFacture` = 5 ";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete4);
            System.out.println("facture supprimée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

}
