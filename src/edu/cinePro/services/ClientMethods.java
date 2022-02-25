/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;

import edu.cinePro.entities.Billet;
import edu.cinePro.entities.Client;
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

/**
 *
 * @author Asus
 */
public class ClientMethods {

    Connection laConnexion;

    public ClientMethods() {
        laConnexion = MyConnection.getInstance().getCnx();

    }

    public Client recupEvent(int IDClient) {
        Client C = new Client();

        try {
            String requete = "SELECT * FROM client WHERE idClient = ?";
            PreparedStatement prepare = laConnexion.prepareStatement(requete);
            prepare.setInt(1, IDClient);

            ResultSet res = prepare.executeQuery();

            while (res.next()) {

                C.setIdClient(res.getInt("idClient"));
                C.setNom(res.getString("nom"));
                C.setNom(res.getString("prenom"));
                C.setDateDeNaissance(res.getDate("dateDeNaissance"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return C;
    }

}
