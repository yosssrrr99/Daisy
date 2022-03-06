/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;

import edu.cinePro.entities.Reservation;
import edu.cinePro.utils.MyConnection;
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
public class ReservationCRUD {

    Connection laConnexion;

    public ReservationCRUD() {
        laConnexion = MyConnection.getInstance().getCnx();

    }

    public Reservation recupReservation(int IDReservation) {
        Reservation R = new Reservation();

        try {
            String requete = "SELECT * FROM reservation WHERE idRes = ?";
            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setInt(1, IDReservation);

            ResultSet res = prepare.executeQuery();

            while (res.next()) {

                R.setIdRes(res.getInt("idRes"));
                R.setCategorie(res.getString("Categorie"));
                R.setIdEv(res.getInt("idEv"));
                R.setIdF(res.getInt("idF"));
                R.setDateDebut(res.getTimestamp("DateDeb"));
                R.setDateFin(res.getTimestamp("DateFin"));
                R.setNbPlace(res.getInt("NbPlace"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return R;
    }

    public void decrementPlaceDisponible(Reservation R) {
        String requete = null;
        try {
            requete = "UPDATE reservation SET "
                    + "NbPlace = ? "
                    + "WHERE idRes = ?";

            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setInt(1, R.getNbPlace());
            prepare.setInt(2, R.getIdRes());
            prepare.executeUpdate();

        } catch (SQLException e) {
            System.out.println("erreur modification statusStock");
        }
    }

}
