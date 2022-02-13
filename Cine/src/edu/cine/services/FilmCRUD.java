/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.services;

import edu.cine.entities.Film;
import edu.cine.utils.MyConnection;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author DELL
 */
public class FilmCRUD {
      
    public void ajouterFilm(){
       
        try {
             String req="INSERT INTO `film`(`nomF`, `Genre`, `Archive`, `EtatAcc`, `NumRea`, `Image`,`Description`,`dateDispo`) VALUES ('regardemoi','action','0','accepté','','img','hghvsdvz','') ";
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Film ajouté!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void ajouterFilm2(Film f){
        String requete = "INSERT INTO `film`(`nomF`, `Genre`, `Archive`, `EtatAcc`, `NumRea`, `Image`,`Description`)"
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= new MyConnection().getCnx().prepareStatement(requete);
            ps.setString(1,f.getNomF());
            ps.setString(2,f.getGenre());
            ps.setBoolean(3, f.isArchive());
            ps.setString(4,f.getEtatAcc());
            ps.setInt(5,f.getNumRea());
            ps.setString(6,f.getImage());
            ps.setString(7,f.getDescription());
            ps.executeUpdate();
            System.out.println("Film ajoutée dynamiquement");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
 
    public void modifierFilm(int idF,String nomF, String Genre, boolean Archive, String EtatAcc, int NumRea, String Image, String Description) {
        
        
        try {
            String req = "UPDATE film SET "
                    + "nomF = ?, "
                    + "Genre = ?, "
                    + "Archive = ?, "
                    + "EtatAcc = ?, "
                    + "NumRea = ?, "
                    + "Image = ?, "
                    + "Description = ? "
                    + "WHERE idF = ?";

            PreparedStatement prepare = new MyConnection().getCnx().prepareStatement(req);
            prepare.setString(1, nomF);
            prepare.setString(2, Genre);
            prepare.setBoolean(3, Archive);
            prepare.setString(4, EtatAcc);
            prepare.setInt(5, NumRea);
            prepare.setString(6, Image);
            prepare.setString(7, Description);
           prepare.setInt(8, idF);

            prepare.executeUpdate();
            System.out.println("modification du film ");

        } catch (SQLException e) {
            
            System.out.println("\"Aucune modification du film ");

        }
        
    }
     public List<Film> afficheFilm() {
        List<Film> listeFilm = new ArrayList();
        try {
            String requete = "SELECT * FROM Film";
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Film f = new Film();

               f.setNomF(res.getString("NomF"));
               f.setGenre(res.getString("Genre"));
               f.setArchive(res.getBoolean("Archive"));
               f.setEtatAcc(res.getString("EtatAcc"));
               f.setNumRea(res.getInt("NumRea"));
               f.setImage(res.getString("Image"));
               f.setDescription(res.getString("Description"));
               f.setDateDispo(res.getTimestamp("DateDispo"));
               listeFilm.add(f);
            }
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return listeFilm;
    }
     
      public void supprimerFilm(int idF) {
        
        try {
            String requete = "DELETE FROM film WHERE idF = ?";
            PreparedStatement prepare =  new MyConnection().getCnx().prepareStatement(requete);
            prepare.setInt(1, idF);
            int nbEnregSup = prepare.executeUpdate();
            System.out.println("suppression");
            
        } catch (SQLException e) {
            System.out.println("Aucune suppression");
            
        }
        
    }
      public void archiveFilm() {
        List<Film> listeFilm = new ArrayList<Film>();
        listeFilm = afficheFilm();

        Date date = new Date();
        Timestamp currentDate = new Timestamp(date.getTime());
        System.out.println(currentDate.getYear());
        
        listeFilm.stream()
                // si depasser akther men aam 
                .filter(f -> f.getDateDispo().getYear() - currentDate.getYear() > 1)
                .forEach((P) -> {
                    
                    try {
                        String requete = "UPDATE film SET "
                                + "Archive= 1 ";

                        Statement st = new MyConnection().getCnx().createStatement();
                        st.executeUpdate(requete);

                    } catch (SQLException e) {
                        System.out.println("erreur");
                    }
                });
    }
    
}
