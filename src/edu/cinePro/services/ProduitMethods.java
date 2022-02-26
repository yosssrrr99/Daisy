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
                JOptionPane.showMessageDialog(null, "Aucune suppression de produit effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);
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

    public void PromotionPrixVente() {
        List<Produit> myList = new ArrayList();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2022, 01, 10, 0, 0, 0);
        Date dateDebutJCC = calendar1.getTime();
        System.out.print(dateDebutJCC);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2022, 01, 18, 59, 59, 59);
        Date dateFinJCC = calendar2.getTime();
        System.out.print(dateFinJCC);

        LocalDateTime currentDate = LocalDateTime.now();
        Date convertedCurrentDate = Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(convertedCurrentDate);

        if (dateDebutJCC.before(convertedCurrentDate) && dateFinJCC.after(convertedCurrentDate)) {
            myList = affichageProduits();
            myList.stream()
                    .forEach((P) -> {
                        String requete = null;
                        try {
                            requete = "UPDATE produit SET "
                                    + "prixVenteUnit = ? "
                                    + "WHERE idProduit = ? ";

                            PreparedStatement prepare = laConnexion.prepareStatement(requete);
                            double prixAchatUnit_apresRemise = P.getPrixVenteUnit() - P.getPrixVenteUnit() * 50 / 100;
                            prepare.setDouble(1, prixAchatUnit_apresRemise);
                            prepare.setInt(2, P.getIDProduit());
                            prepare.executeUpdate();

                        } catch (SQLException e) {
                            System.out.println("erreur modification prixVenteUnit");
                        }
                    });

        }
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

    public void MaxProfitable() {
        List<Produit> myList = new ArrayList();
        myList = affichageProduits();
        //triez la liste des produits en ordre croissant du profit
        Collections.sort(myList);
        System.out.println("Le produit le plus profitable : " + myList.get(myList.size() - 1));
        System.out.println("Le produit le moins profitable : " + myList.get(0));

    }

    public void affichageProfitabilité() {
        List<Produit> myList = new ArrayList();

        try {
            String requete = "SELECT IDProduit , Designation , prixAchatUnit , prixVenteUnit FROM produit";

            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);
            System.out.println("\n"
                    + "  IDProduit"
                    + "     Designation"
                    + "                    prixAchatUnit"
                    + "                    prixVenteUnit  "
                    + "                    profit  "
                    + "\n");

            while (res.next()) {
                //informations general sur billet
                int IDProduit = res.getInt("IDProduit");
                String Designation = res.getString("Designation");
                float prixAchatUnit = res.getFloat("prixAchatUnit");
                float prixVenteUnit = res.getFloat("prixVenteUnit");
                Produit P = new Produit(Designation, prixAchatUnit, prixVenteUnit);
                double profit = P.calculateProductProfit(P);

                System.out.format(
                        "%5s%25s%25s%35s%35s\n", IDProduit, Designation,
                        prixAchatUnit, prixVenteUnit, profit);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void affichageQuantiteVendu_Produit() {
        List<Produit> myList = new ArrayList();

        try {
            String requete = "SELECT Produit.IDProduit AS \"ID Produit \",Produit.Designation AS \"Designation produit \" ,SUM(Panier.quantite) AS \"Quantite vendue\"\n"
                    + "FROM produit AS Produit ,panier AS Panier\n"
                    + "WHERE Produit.IDProduit LIKE Panier.IDProduit AND Panier.statusPanier LIKE 1\n"
                    + "GROUP BY (Produit.IDProduit)";

            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);
            System.out.println("\n"
                    + "  IDProduit"
                    + "     Designation"
                    + "                    Quantité vendue  "
                    + "\n");

            while (res.next()) {
                //informations general sur le produit
                int IDProduit = res.getInt(1);
                String Designation = res.getString(2);

                //informations sue la quantité vendue par produit
                int quantiteVendu = res.getInt(3);

                System.out.format(
                        "%5s%25s%25s\n", IDProduit, Designation,
                        quantiteVendu);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Produit recupProduit(int IDProduit) {
        Produit p = new Produit();

        try {
            String requete = "SELECT * FROM produit WHERE IDProduit = ?";
            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setInt(1, IDProduit);

            ResultSet res = prepare.executeQuery();

            while (res.next()) {
                p.setIDProduit(res.getInt("IDProduit"));
                p.setDesignation(res.getString("designation"));
                p.setDescription(res.getString("description"));
                p.setImage(res.getString("image"));
                p.setQuantiteEnStock(res.getInt("quantiteEnStock"));
                p.setPrixAchatUnit(res.getFloat("prixAchatUnit"));
                p.setPrixVenteUnit(res.getFloat("prixVenteUnit"));
                p.setStatusStock(res.getBoolean("statusStock"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public Produit recupProduitByDesignation_Description_PV(String searchValue) {
        Produit p = new Produit();

        try {
            String requete = "SELECT * FROM produit WHERE Designation LIKE '%" + searchValue + "%' OR Description LIKE '%" + searchValue + "%' OR prixVenteUnit LIKE '%" + searchValue + "%'";
            PreparedStatement prepare = laConnexion.prepareStatement(requete);

            ResultSet res = prepare.executeQuery();

            while (res.next()) {
                p.setIDProduit(res.getInt("IDProduit"));
                p.setDesignation(res.getString("designation"));
                p.setDescription(res.getString("description"));
                p.setImage(res.getString("image"));
                p.setQuantiteEnStock(res.getInt("quantiteEnStock"));
                p.setPrixAchatUnit(res.getFloat("prixAchatUnit"));
                p.setPrixVenteUnit(res.getFloat("prixVenteUnit"));
                p.setStatusStock(res.getBoolean("statusStock"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

}
