/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;

import edu.cinePro.entities.Billet;
import edu.cinePro.entities.Client;
import edu.cinePro.entities.Evenement;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class BilletMethods {

    Connection laConnexion;

    public BilletMethods() {
        laConnexion = MyConnection.getInstance().getCnx();
    }

    public void ajouterBillet(Billet B) {

        String requete = "INSERT INTO billet (categorieBillet,nb_place,archived,idEvenement,idClient)"
                + "VALUES (?,?,?,?,?)";
        try {

            PreparedStatement bst = laConnexion.prepareStatement(requete);
            bst.setString(1, B.getCategorieBillet());
            bst.setInt(2, B.getNb_place());
            bst.setBoolean(3, B.isArchieved());
            bst.setInt(4, B.getIdEvenement());
            bst.setInt(5, B.getIdClient());

            bst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ajout Billet effectué dans la BD avec succées", "Aucun probleme rencontré", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Aucune ajout de Billet effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void affichageBillets() {
        List<Billet> myList = new ArrayList();

        try {
            String requete = "SELECT Billet.categorieBillet , Billet.nb_place , Billet.created_on , Event.nomEvenement , Client.nom , Client.prenom "
                    + "FROM billet AS billet , evenement AS Event, client AS Client "
                    + "WHERE Event.idEvenement = Billet.idEvenement "
                    + "AND Client.idClient = Billet.idClient";

            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);
            System.out.println("\n"
                    + "  Categorie Billet"
                    + "                    Nombre de place"
                    + "                    Date de création  "
                    + "                    Nom de l'evenement"
                    + "                    Nom du client"
                    + "                    Prenom du client"
                    + "\n");

            while (res.next()) {
                //informations general sur billet
                String categorieBillet = res.getString("categorieBillet");
                int nb_place = res.getInt("nb_place");
                Timestamp created_on = res.getTimestamp("created_on");

                //informations sur l'evenement
                String nomEvenement = res.getString("nomEvenement");

                //informations sur le client
                String nomClient = res.getString("nom");
                String prenomClient = res.getString("prenom");

                System.out.format(
                        "%10s%35s%50s%35s%27s%32s\n", categorieBillet,
                        nb_place, created_on, nomEvenement, nomClient, prenomClient);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean modifierBillet(int IDBillet, String categorieBillet, int nb_place, int idEvenement, int idClient) {
        boolean BModification = true;
        String requete = null;
        try {
            requete = "UPDATE billet SET "
                    + "categorieBillet = ?, "
                    + "nb_place = ?, "
                    + "idEvenement = ?, "
                    + "idClient = ? "
                    + "WHERE IDBillet = ?";

            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setString(1, categorieBillet);
            prepare.setInt(2, nb_place);
            prepare.setInt(3, idEvenement);
            prepare.setInt(4, idClient);
            prepare.setInt(5, IDBillet);

            prepare.executeUpdate();
            BModification = true;
            JOptionPane.showMessageDialog(null, "modification de billet effectuée dans la BD avec succées.", "aucun probleme rencontré", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            BModification = false;
            JOptionPane.showMessageDialog(null, "Aucune modification de billet effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);

        }
        return BModification;
    }

    public List<Billet> recup_Liste_Billets() {
        List<Billet> listeBillets = new ArrayList();
        try {
            String requete = "SELECT * FROM billet";
            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Billet b = new Billet();

                b.setIDBillet(res.getInt("IDBillet"));
                b.setCategorieBillet(res.getString("categorieBillet"));
                b.setNb_place(res.getInt("nb_place"));
                b.setCreated_on(res.getTimestamp("created_on"));
                b.setArchieved(res.getBoolean("archived"));
                b.setIdEvenement(res.getInt("idEvenement"));
                b.setIdClient(res.getInt("idClient"));

                listeBillets.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeBillets;
    }

    public void archiverBillet() {
        List<Billet> listeBillets = new ArrayList<Billet>();
        listeBillets = recup_Liste_Billets();

        Date date = new Date();
        Timestamp currentDate = new Timestamp(date.getTime());
        System.out.println(currentDate.getDate());
        
        listeBillets.stream()
                .filter(b -> b.getCreated_on().getDate() - currentDate.getDate() < 7)
                .forEach((P) -> {
                    String requete = null;
                    try {
                        requete = "UPDATE billet SET "
                                + "archived = 1 ";

                        Statement st = laConnexion.createStatement();
                        st.executeUpdate(requete);

                    } catch (SQLException e) {
                        System.out.println("erreur modification prixVenteUnit");
                    }
                });
    }

}
