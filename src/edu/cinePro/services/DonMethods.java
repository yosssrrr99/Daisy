/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;

import edu.cinePro.entities.Billet;
import edu.cinePro.entities.Client;
import edu.cinePro.entities.Don;
import edu.cinePro.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class DonMethods {

    Connection laConnexion;

    public DonMethods() {
        laConnexion = MyConnection.getInstance().getCnx();
    }

    public void ajouterDon(Don D) {

        String requete = "INSERT INTO don (montant,idClient)"
                + "VALUES (?,?)";
        try {

            PreparedStatement pst = laConnexion.prepareStatement(requete);
            pst.setFloat(1, D.getMontant());
            pst.setInt(2, D.getIdClient());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ajout Don effectué dans la BD avec succées", "Aucun probleme rencontré", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Aucun ajout de don effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);

        }

    }

    public List<Don> affichageDons() {
        List<Don> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM don";
            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Don D = new Don();

                D.setIDDon(res.getInt("IDDon"));
                D.setMontant(res.getFloat("montant"));
                D.setIdClient(res.getInt("idClient"));

                myList.add(D);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public boolean supprimerDon(int IDDon) {
        boolean pSuppression = true;
        String requete = null;
        try {
            requete = "DELETE FROM don WHERE IDDon = ?";
            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setInt(1, IDDon);
            int nbEnregSup = prepare.executeUpdate();
            if (nbEnregSup == 0) {
                JOptionPane.showMessageDialog(null, "Aucune suppression de don effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "suppression du don effectuée dans la BD avec succées", "Aucun Probleme rencontré", JOptionPane.INFORMATION_MESSAGE);

            }

        } catch (SQLException e) {
            pSuppression = false;
            JOptionPane.showMessageDialog(null, "Aucune suppression effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);

        }
        return pSuppression;
    }

    public boolean modifierDon(int IDDon, float montant, int idClient) {
        boolean DModification = true;
        String requete = null;
        try {
            requete = "UPDATE don SET "
                    + "montant = ?, "
                    + "idClient = ? "
                    + "WHERE IDDon = ?";

            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setFloat(1, montant);
            prepare.setInt(2, idClient);
            prepare.setInt(3, IDDon);

            prepare.executeUpdate();
            DModification = true;
            JOptionPane.showMessageDialog(null, "modification du don effectuée dans la BD avec succées.", "aucun probleme rencontré", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            DModification = false;
            JOptionPane.showMessageDialog(null, "Aucune modification de don effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);

        }
        return DModification;
    }

    public HashMap<Integer, Float> recupSommeDon_par_client() {

        HashMap<Integer, Float> DonMap = new HashMap<>();
        try {
            String requete = "SELECT C.idClient AS \"idDonateur\" ,sum(D.montant) AS \"Somme don\" FROM Don D, Client C WHERE C.idClient LIKE D.idClient GROUP BY C.idClient";
            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                int idClient = res.getInt(1);
                Float sommeDon = res.getFloat(2);
                DonMap.put(idClient, sommeDon);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        DonMap.entrySet().forEach((element) -> {
            System.out.println("ID du donateur: " + element.getKey() + "  =>  Somme des dons: " + element.getValue());
        });

        return DonMap;

    }

    public float montantDonHT(Don D) {
        float montantDonTTC = D.getMontant();
        float TVA = (float) 5.5;

        return montantDonTTC * (1 - TVA / 100);
    }

    public void afficherDonAvant_apres_TVA(Don D) {
        JOptionPane.showMessageDialog(null, "Montant don TTC : " + D.getMontant() + " DT" + "\nMontant don HT : " + montantDonHT(D) + " DT", "Informations montant don", JOptionPane.INFORMATION_MESSAGE);

    }

}
