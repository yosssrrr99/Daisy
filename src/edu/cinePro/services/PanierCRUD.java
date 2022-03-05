/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;
//import entities.Facture;

import edu.cinePro.entities.Panier;
import edu.cinePro.entities.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.cinePro.utils.MyConnection;

/**
 *
 * @author hp
 */
public class PanierCRUD {

    Connection cnx2;

    public PanierCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterPanier(Panier p) {
        try {
            String requete = "INSERT INTO panier(idProduit,idClient,idBillet,nomPanier,statusPanier,Quantite) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, p.getIdProduit());
            pst.setString(2, p.getIdClient());
            pst.setString(3, p.getIdBillet());
            pst.setString(4, p.getNomPanier());
            pst.setString(5, p.isStatusPanier());
            pst.setString(6, p.getQuantite());
            pst.executeUpdate();
            System.out.println("panier ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void DeletePanier(Panier p) {
        try {
            String requete4 = "DELETE FROM `panier` WHERE `idProduit` = ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete4);
            pst.setString(1, p.getIdProduit());
            int row = pst.executeUpdate();
            System.out.println("panier supprimée" + row);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ViderPanier(Panier p) {
        try {
            String requete4 = "DELETE from panier WHERE `IdClient` = ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete4);
            pst.setString(1, p.getIdClient());
            int res = pst.executeUpdate();
            System.out.println("panier supprimée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<List<String>> afficherPanier() {

        List<List<String>> liste = new ArrayList<>();
        try {

            String requete3 = "SELECT p.Designation , p.prixVenteUnit,p.Image ,p.IDProduit,pa.Quantite from produit p , panier pa WHERE pa.idProduit=p.IDProduit AND idClient = 1";
            Statement pst = cnx2.createStatement();
            //pst.setString(1,p.getIdClient() );
            ResultSet rs = pst.executeQuery(requete3);
            while (rs.next()) {
                List<String> listString = new ArrayList<>();
                listString.add(rs.getString("Designation"));
                listString.add(rs.getString("prixVenteUnit"));
                listString.add(rs.getString("Image"));
                listString.add(rs.getString("IDProduit"));
                listString.add(rs.getString("Quantite"));
                liste.add(listString);
            }
            return liste;

//            while (rs.next()) {
//               Produit pr = new Produit();
////               pr.setDesignation(rs.getString(1));
////               pr.setDescription(rs.getString(2));
////               //pr.setPrixVenteUnit(rs.getFloat(4));
//                Panier p = new Panier();
//
//                p.setIdPanier(rs.getInt(1));
//                p.setIdProduit(rs.getString(2));
//                p.setIdClient(rs.getString(3));
//                p.setIdBillet(rs.getString(4));
//                p.setNomPanier(rs.getString(5));
//
//                p.setStatusPanier(rs.getString(6));
//                p.setQuantite(rs.getString(7));
//
//                //liste2.add(pr);
//                maListe.add(p);
//                //maListe.addAll(liste2);
//            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return liste;
    }

}
