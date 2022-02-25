/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;

import edu.cinePro.entities.Billet;
import edu.cinePro.entities.Client;
import edu.cinePro.entities.Reservation;
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
public class BilletMethods {

    Connection laConnexion;

    public BilletMethods() {
        laConnexion = MyConnection.getInstance().getCnx();
    }

    public void ajouterBillet(Billet B) {

        ReservationCRUD RC = new ReservationCRUD();
        Reservation R = RC.recupReservation(B.getIdReservation());

        if (R.getNbPlace() == 0) {

            JOptionPane.showMessageDialog(null, "L'evenement est Complet", "Pas de place disponible", JOptionPane.ERROR_MESSAGE);

        } else if (R.getNbPlace() < B.getNb_place()) {

            JOptionPane.showMessageDialog(null, "Il reste seulement " + R.getNbPlace() + " disponible", "Place disponible insuffisant", JOptionPane.ERROR_MESSAGE);

        } else {

            R.setNbPlace(R.getNbPlace() - B.getNb_place());
            RC.decrementPlaceDisponible(R);

            String requete = "INSERT INTO billet (categorieBillet,nb_place,archived,idReservation,idClient)"
                    + "VALUES (?,?,?,?,?)";
            try {

                PreparedStatement bst = laConnexion.prepareStatement(requete);
                bst.setString(1, B.getCategorieBillet());
                bst.setInt(2, B.getNb_place());
                bst.setBoolean(3, B.isArchieved());
                bst.setInt(4, B.getIdReservation());
                bst.setInt(5, B.getIdClient());

                bst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Ajout Billet effectué dans la BD avec succées", "Aucun probleme rencontré", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Aucune ajout de Billet effectuée dans la BD.", "Probleme rencontré", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

    public void affichageBillets() {
        List<Billet> myList = new ArrayList();

        try {
            String requete = "SELECT Billet.IDBillet , Billet.categorieBillet , Billet.nb_place , Billet.created_on , Film.nomF , Client.nom , Client.prenom FROM billet AS billet , reservation AS Reserv, client AS Client , film AS Film WHERE Reserv.idRes = Billet.idReservation AND Film.idF = Reserv.idF AND Client.idClient = Billet.idClient";

            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);
            System.out.println("\n"
                    + "  ID Billet"
                    + "     Categorie Billet"
                    + "                    Nombre de place"
                    + "                    Date de création  "
                    + "                    Nom du film"
                    + "                    Nom du client"
                    + "                    Prenom du client"
                    + "\n");

            while (res.next()) {
                //informations general sur billet
                int IDBillet = res.getInt("IDBillet");
                String categorieBillet = res.getString("categorieBillet");
                int nb_place = res.getInt("nb_place");
                Timestamp created_on = res.getTimestamp("created_on");

                //informations sur l'evenement
                String nomFilm = res.getString("nomF");

                //informations sur le client
                String nomClient = res.getString("nom");
                String prenomClient = res.getString("prenom");

                System.out.format(
                        "%5s%25s%32s%45s%35s%31s%37s\n", IDBillet, categorieBillet,
                        nb_place, created_on, nomFilm, nomClient, prenomClient);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean modifierBillet(int IDBillet, String categorieBillet, int nb_place, int idReservation, int idClient) {
        boolean BModification = true;
        String requete = null;
        try {
            requete = "UPDATE billet SET "
                    + "categorieBillet = ?, "
                    + "nb_place = ?, "
                    + "idReservation = ?, "
                    + "idClient = ? "
                    + "WHERE IDBillet = ?";

            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setString(1, categorieBillet);
            prepare.setInt(2, nb_place);
            prepare.setInt(3, idReservation);
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
                b.setIdReservation(res.getInt("idReservation"));
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
        
        
        //archivage aprés 7 jours de la création du billet
        listeBillets.stream()
                .filter(b -> currentDate.getDate() - b.getCreated_on().getDate() > 7)
                .forEach((P) -> {
                    String requete = null;
                    try {
                        requete = "UPDATE billet SET "
                                + "archived = 1 ";

                        Statement st = laConnexion.createStatement();
                        st.executeUpdate(requete);

                    } catch (SQLException e) {
                        System.out.println("erreur d'archivage billet");
                    }
                });
    }

    public float prixBilletInitial(Billet B) {
        float prixBillet = 0;
        switch (B.getCategorieBillet()) {
            case "First Class":
                prixBillet = B.getNb_place() * 50;
                break;
            case "Second Class":
                prixBillet = B.getNb_place() * 25;
                break;
            case "Third Class":
                prixBillet = B.getNb_place() * 15;
                break;
        }
        return prixBillet;
    }

    public long nbAchat_FirstClass_parClient(int IDClient) {
        List<Billet> listeBillets = new ArrayList<Billet>();
        listeBillets = recup_Liste_Billets();
        long nb_achat_firstClass = listeBillets.stream()
                .filter(new Predicate<Billet>() {
                    @Override
                    public boolean test(Billet b) {
                        return (b.getIdClient() == IDClient) && (b.getCategorieBillet().equals("First Class"));
                    }
                })
                .count();

        return nb_achat_firstClass;
    }

    public float calculRemise(Billet B) {
        float remise = 0;

        //recupération Evenement
        ReservationCRUD RC = new ReservationCRUD();
        Reservation R = RC.recupReservation(B.getIdReservation());

        //récuperation client
        ClientMethods CM = new ClientMethods();
        Client C = CM.recupEvent(B.getIdClient());

        //Promotion special anniversaire
        if ((R.getDateDebut().getMonth() == C.getDateDeNaissance().getMonth()) && (R.getDateDebut().getDate() == C.getDateDeNaissance().getDate())) {
            remise += 25 * prixBilletInitial(B) / 100;
        }

        //promotion pour les clients qui ont acheté auparavant des billets firstClass
        if (nbAchat_FirstClass_parClient(B.getIdClient()) == 2) {
            remise += 20 * prixBilletInitial(B) / 100;

        } else if ((nbAchat_FirstClass_parClient(B.getIdClient()) > 2) && (nbAchat_FirstClass_parClient(B.getIdClient()) <= 6)) {
            remise += 40 * prixBilletInitial(B) / 100;

        } else if (nbAchat_FirstClass_parClient(B.getIdClient()) > 6) {
            remise += 50 * prixBilletInitial(B) / 100;

        }

        return remise;
    }

    public float prixBilletTTC(Billet b) {
        float prixBilletInitial = prixBilletInitial(b);
        float remise = calculRemise(b);

        return prixBilletInitial - remise;
    }

    public float prixBilletHT(Billet b) {
        float prixBilletTTC = prixBilletTTC(b);
        float TVA = (float) 5.5;

        return prixBilletTTC * (1 - TVA / 100);
    }

    public void afficherPrixBillet(Billet b) {
        JOptionPane.showMessageDialog(null, "Prix billet initial : " + prixBilletInitial(b) + " DT" + "\nRemise : " + calculRemise(b) + " DT" + "\nPrix Billet TTC : " + prixBilletTTC(b) + " DT" + "\nPrix Billet HT : " + prixBilletHT(b) + " DT", "Informations prix Billet", JOptionPane.INFORMATION_MESSAGE);

    }

    public HashMap<String, Integer> recupVente_Par_Film() {

        HashMap<String, Integer> VenteMap = new HashMap<String, Integer>();
        try {
            String requete = "SELECT f.nomF AS \"Titre du film\",COUNT(*) AS \"Nombre de billets vendus\"\n"
                    + "FROM billet AS b,reservation AS r ,film AS f\n"
                    + "WHERE b.idReservation LIKE r.idRes AND r.idF LIKE f.idF\n"
                    + "GROUP BY f.idF";
            Statement st = laConnexion.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                String titreFilm = res.getString(1);
                int NombreBilletVendu = res.getInt(2);
                VenteMap.put(titreFilm, NombreBilletVendu);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        for (Map.Entry<String, Integer> element : VenteMap.entrySet()) {
            System.out.println("Titre du film: " + element.getKey() + "  =>  Nombre de billets vendus: " + element.getValue());
        }

        return VenteMap;

    }

    public List<Billet> rechercherBillet(String recherche) {
        List<Billet> myList = new ArrayList();

        try {
            String requete = "SELECT * \n"
                    + "FROM billet AS B,reservation AS reserv,film AS F,client AS C\n"
                    + " WHERE B.idReservation LIKE reserv.idRes AND \n"
                    + "           B.idClient LIKE C.idClient AND\n"
                    + "           reserv.idF LIKE F.idF AND (\n"
                    + "           B.IDBillet LIKE '%" + recherche + "%' OR\n"
                    + "           B.categorieBillet LIKE '%" + recherche + "%' OR\n"
                    + "           B.nb_place LIKE '%" + recherche + "%' OR\n"
                    + "           B.created_on LIKE '%" + recherche + "%' OR\n"
                    + "           B.idReservation LIKE '%" + recherche + "%' OR\n"
                    + "           B.idClient  LIKE '%" + recherche + "%' OR\n"
                    + "           reserv.dateDebut LIKE '%" + recherche + "%' OR\n"
                    + "           F.nomF LIKE '%" + recherche + "%' OR\n"
                    + "           C.nom LIKE '%" + recherche + "%' OR\n"
                    + "           C.prenom LIKE '%" + recherche + "%')";
            PreparedStatement prepare = laConnexion.prepareStatement(requete);

            ResultSet res = prepare.executeQuery();

            while (res.next()) {

                Billet B = new Billet();

                int IDBillet = res.getInt(1);
                String categorieBillet = res.getString(2);
                int nb_place = res.getInt(3);
                Timestamp created_on = res.getTimestamp(4);
                int idEvenement = res.getInt(6);
                int idClient = res.getInt(7);

                B.setIDBillet(IDBillet);
                B.setCategorieBillet(categorieBillet);
                B.setNb_place(nb_place);
                B.setCreated_on(created_on);
                B.setIdReservation(idEvenement);
                B.setIdClient(idClient);

                myList.add(B);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
