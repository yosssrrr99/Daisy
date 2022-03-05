/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;

import edu.cinePro.entities.Don;
import edu.cinePro.entities.Followingproduit;
import edu.cinePro.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class FollowingProduitMethods {

    Connection laConnexion;

    public FollowingProduitMethods() {
        laConnexion = MyConnection.getInstance().getCnx();

    }

    public void ajouterFollowingproduit(Followingproduit FP) {

        String requete = "INSERT INTO followingproduit (IDProduit,idClient)"
                + "VALUES (?,?)";
        try {

            PreparedStatement pst = laConnexion.prepareStatement(requete);
            pst.setFloat(1, FP.getIDProduit());
            pst.setInt(2, FP.getIdClient());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("erreur");
        }

    }

    public void supprimerFollowingproduit(int idProduit,int idClient) {

        String requete = null;
        try {
            requete = "DELETE FROM followingproduit WHERE IDProduit = ? AND idClient = ?";
            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setInt(1, idProduit);
            prepare.setInt(2, idClient);
            prepare.executeUpdate();

        } catch (SQLException e) {
            System.out.println("erreur");

        }

    }
       public List<Followingproduit> affichageFollowingProduct() {
        List<Followingproduit> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM followingproduit";
            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Followingproduit FP = new Followingproduit();

                FP.setIDProduit(res.getInt("IDProduit"));
                FP.setIdClient(res.getInt("idClient"));
              

                myList.add(FP);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    
}
