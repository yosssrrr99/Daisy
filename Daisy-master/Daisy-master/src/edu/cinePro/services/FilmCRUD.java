/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;

import edu.cinePro.entities.Film;
import edu.cinePro.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class FilmCRUD {

    Connection laConnexion;

    public FilmCRUD() {
        laConnexion = MyConnection.getInstance().getCnx();
    }

    public Film recupFilmById(int idFilm) {

        Film F = new Film();

        try {
            String requete = "SELECT * FROM film WHERE idF = ?";
            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setInt(1, idFilm);

            ResultSet res = prepare.executeQuery();

            while (res.next()) {

                F.setIdF(res.getInt("idF"));
                F.setNomF(res.getString("nomF"));
                F.setGenre(res.getString("Genre"));
                F.setArchive(res.getBoolean("Archive"));
                F.setEtatAcc(res.getString("EtatAcc"));
                F.setNumRea(res.getInt("NumRea"));
                F.setImage(res.getString("Image"));
                F.setDescription(res.getString("Description"));
                F.setDateDispo(res.getTimestamp("dateDispo"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return F;
    }
}
