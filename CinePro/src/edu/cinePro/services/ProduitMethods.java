/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;

import edu.cinePro.entities.Produit;
import edu.cinePro.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class ProduitMethods {
    
    Connection laConnexion;
    
    public ProduitMethods() {
        laConnexion = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterProduit(Produit P) {
        
        String requete = "INSERT INTO produit (description,designation,image,quantiteEnStock,prixAchatUnit,prixVenteUnit,statusStock)"
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            
            PreparedStatement pst = laConnexion.prepareStatement(requete);
            pst.setString(1, P.getDescription());
            pst.setString(2, P.getDesignation());
            pst.setString(3, P.getImage());
            pst.setInt(4, P.getQuantiteEnStock());
            pst.setFloat(5, (float) P.getPrixAchatUnit());
            pst.setFloat(6, (float) P.getPrixVenteUnit());
            pst.setBoolean(7, P.isStatusStock());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ajout Produit effectué dans la BD avec succées", "Aucun probleme rencontré", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Aucune ajout de produit effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
    public List<Produit> affichageProduits() {
        List<Produit> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM produit";
            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);
            
            while (res.next()) {
                Produit p = new Produit();
                
                p.setIDProduit(res.getInt("IDProduit"));
                p.setDesignation(res.getString("designation"));
                p.setDescription(res.getString("description"));
                p.setImage(res.getString("image"));
                p.setQuantiteEnStock(res.getInt("quantiteEnStock"));
                p.setPrixAchatUnit(res.getFloat("prixAchatUnit"));
                p.setPrixVenteUnit(res.getFloat("prixVenteUnit"));
                p.setStatusStock(res.getBoolean("statusStock"));
                
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public boolean supprimerProduit(int IDProduit) {
        boolean pSuppression = true;
        String requete = null;
        try {
            requete = "DELETE FROM produit WHERE IDProduit = ?";
            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setInt(1, IDProduit);
            int nbEnregSup = prepare.executeUpdate();
            if (nbEnregSup == 0) {
                JOptionPane.showMessageDialog(null, "Aucune suppression effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "suppression du produit effectuée dans la BD avec succées", "Aucun Probleme rencontré", JOptionPane.INFORMATION_MESSAGE);
                
            }
            
        } catch (SQLException e) {
            pSuppression = false;
            JOptionPane.showMessageDialog(null, "Aucune suppression effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);
            
        }
        return pSuppression;
    }
    
    public boolean modifierProduit(int IDProduit, String designation, String description, String image, int quantiteEnStock, float prixAchatUnit, float prixVenteUnit, boolean statusStock) {
        boolean pModification = true;
        String requete = null;
        try {
            requete = "UPDATE produit SET "
                    + "designation = ?, "
                    + "description = ?, "
                    + "image = ?, "
                    + "quantiteEnStock = ?, "
                    + "prixAchatUnit = ?, "
                    + "prixVenteUnit = ?, "
                    + "statusStock = ? "
                    + "WHERE IDProduit = ?";
            
            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setString(1, designation);
            prepare.setString(2, description);
            prepare.setString(3, image);
            prepare.setInt(4, quantiteEnStock);
            prepare.setFloat(5, (float) prixAchatUnit);
            prepare.setFloat(6, (float) prixVenteUnit);
            prepare.setBoolean(7, statusStock);
            prepare.setInt(8, IDProduit);
            
            prepare.executeUpdate();
            pModification = true;
            JOptionPane.showMessageDialog(null, "modification du produit effectuée dans la BD avec succées.", "aucun probleme rencontré", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            pModification = false;
            JOptionPane.showMessageDialog(null, "Aucune modification du produit effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);
            
        }
        return pModification;
    }
    
   
    
    public void updateStockStatus() {
        
        List<Produit> myList = new ArrayList();
        myList = affichageProduits();
        myList.stream()
                .filter((P) -> (P.getQuantiteEnStock() == 0))
                .forEach((P) -> {
                    String requete = null;
                    try {
                        requete = "UPDATE produit SET "
                                + "statusStock = ? "
                                + "WHERE IDProduit = ?";
                        
                        PreparedStatement prepare = laConnexion.prepareStatement(requete);
                        prepare.setBoolean(1, false);
                        prepare.setInt(2, P.getIDProduit());
                        prepare.executeUpdate();
                        
                    } catch (SQLException e) {
                        System.out.println("erreur modification statusStock");
                    }
                    System.out.println(P.getDesignation() + " is out of stock");
                });
    }
    
    
    
}
