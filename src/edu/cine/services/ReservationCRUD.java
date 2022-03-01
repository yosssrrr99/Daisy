/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.services;

import edu.cine.entities.Film;
import edu.cine.entities.Reservation;
import edu.cine.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ReservationCRUD {
    Connection cnx;
    public ReservationCRUD(){
       cnx = MyConnection.getInstance().getCnx();
    }
    public void ajouterRes(Reservation r){
        String requete = "INSERT INTO `reservation`(`Categorie`, `idEv`, `idF`, `NbPlace`, `idSa`, `DateDeb`, `DateFin`)"
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(requete);
            ps.setString(1,r.getCategorie());
            ps.setInt(2,r.getIdEv());
            ps.setInt(3, r.getIdF());
             ps.setInt(4,r.getNbPlace());
            ps.setInt(5,r.getIdSa());
            //java.sql.Date newDate1 = new Date(r.getDateDeb().getLocalDate());
            //java.sql.Date newDate2 = new Date(r.getDateFin().getDate());
            //Date newdatee =  Date.valueOf(r.getDateDeb());
             //Date date1 = Date.valueOf(r.getDateFin()); 
            ps.setString(6, r.getDateDeb());
            ps.setString(7, r.getDateFin());
            ps.executeUpdate();
            System.out.println("Réservation ajoutée dynamiquement");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
 
    public void modifierRes(int idRes,String Categorie,int IdEv, int IdF, int IdSa,int NbPlace, String DateDeb,  String DateFin) {
        
        
        try {
            String req = "UPDATE reservation SET "
                    + "Categorie = ?, "
                    + "IdEv = ?, "
                    + "IdF = ?, "
                    + "IdSa = ?, "
                    + "NbPlace = ?, "
                    + "DateDeb = ?, "
                    + "DateFin = ? "
                    + "WHERE idRes = ?";

            PreparedStatement prepare = cnx.prepareStatement(req);
            prepare.setString(1,Categorie);
            prepare.setInt(2, IdEv);
            prepare.setInt(3, IdF);
            prepare.setInt(4, IdSa);
            prepare.setInt(5, NbPlace);
            
            prepare.setString(6,DateDeb);
            prepare.setString(7,DateFin);
            prepare.setInt(8, idRes);

            prepare.executeUpdate();
            System.out.println("modification du reservation ");

        } catch (SQLException e) {
            
            System.out.println("\"Aucune reservation modifiée ");

        }
        
    }
     public List<Reservation> afficheRes() {
        List<Reservation> listeRes = new ArrayList();
        try {
            String requete = "SELECT * FROM Reservation";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Reservation r = new Reservation();

               r.setCategorie(res.getString("Categorie"));
               r.setIdEv(res.getInt("IdEv"));
               r.setIdF(res.getInt("IdF"));
               r.setIdSa(res.getInt("IdSa"));
               r.setNbPlace(res.getInt("NbPlace"));
              // Date date =res.getDate("DateDeb");
               //LocalDate datee=date.toLocalDate();
               //r.setDateDeb(datee);
               //Date dateeee =res.getDate("DateFin");
               //LocalDate dateee=dateeee.toLocalDate();
               //r.setDateFin(dateee);
               r.setDateDeb(res.getString("DateDeb"));
               r.setDateFin(res.getString("DateFin"));
               listeRes.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return listeRes;
    }
     
      public void annulerRes(int idRes) {
        
        try {
            String requete = "DELETE FROM Reservation WHERE idRes = ?";
            PreparedStatement prepare =  cnx.prepareStatement(requete);
            prepare.setInt(1, idRes);
            int nbEnregSup = prepare.executeUpdate();
            System.out.println("suppression");
            
        } catch (SQLException e) {
            System.out.println("Aucune suppression");
            
        }
        
    }
}
