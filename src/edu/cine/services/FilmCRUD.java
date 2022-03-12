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
    Connection cnx;
    public FilmCRUD(){
        cnx = MyConnection.getInstance().getCnx();
    }
      
    public void ajouterFilm(){
       
        try {
             String req="INSERT INTO `film`(`nomF`, `Genre`, `Archive`, `EtatAcc`, `NumRea`, `Image`,`Description`) VALUES ('regardemoi','action','0','accepté','','img','hghvsdvz','') ";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Film ajouté!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void ajouterFilm2(Film f){
        String requete = "INSERT INTO `film`(`nomF`, `Genre`, `Archive`, `EtatAcc`, `NumRea`, `Image`,`Description`, `duree`)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(requete);
            ps.setString(1,f.getNomF());
            ps.setString(2,f.getGenre());
            ps.setBoolean(3, f.isArchive());
            ps.setString(4,f.getEtatAcc());
            ps.setInt(5,f.getNumRea());
            ps.setString(6,f.getImage());
            ps.setString(7,f.getDescription());
            ps.setInt(8, f.getDuree());
            //ps.setTimestamp(8, f.getDateDispo());
            ps.executeUpdate();
            System.out.println("Film ajoutée dynamiquement");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
     public void ajouterFilm3(Film f){
        String requete = "INSERT INTO `film`(`nomF`, `Genre`,  `NumRea`, `Image`,`Description`, `duree`)"
                + "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(requete);
            ps.setString(1,f.getNomF());
            ps.setString(2,f.getGenre());
            
            ps.setInt(3,f.getNumRea());
            ps.setString(4,f.getImage());
            ps.setString(5,f.getDescription());
            ps.setInt(6, f.getDuree());
            //ps.setTimestamp(8, f.getDateDispo());
            ps.executeUpdate();
            System.out.println("Film ajoutée dynamiquement");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
 
    public void modifierFilm(int idF,String nomF, String Genre,String img,  int NumRea,  String Description, int duree) {
        
        
        try {
            String req = "UPDATE film SET "
                    + "nomF = ?, "
                    + "Genre = ?, "
                    
                    + "NumRea = ?, "
                   + "Image = ?, "
                    + "Description = ?, "
                    + "duree = ? "
                    + "WHERE idF = ?";

            PreparedStatement prepare = cnx.prepareStatement(req);
            prepare.setString(1, nomF);
            prepare.setString(2, Genre);
            
            prepare.setInt(3, NumRea);
            prepare.setString(4, img);
            prepare.setString(5, Description);
            prepare.setInt(6, duree);
           prepare.setInt(7, idF);

            prepare.executeUpdate();
            System.out.println("modification du film ");

        } catch (SQLException e) {
            
            System.out.println("\"Aucune modification du film ");

        }
        
    }
     public List<List<String>> afficheFilm() {
        List<List<String>> liste = new ArrayList<>();
        try {
            String requete = "select film.*,reservation.DateDeb,reservation.DateFin,reservation.idRes\n" +
             "from film,reservation\n" +
              "where film.idF=reservation.idF";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
           
            
            while (rs.next()) {
                 List<String> listString = new ArrayList<>();
                
                listString.add(rs.getString("nomF"));
                listString.add(rs.getString("Genre"));
                listString.add(rs.getString("Archive"));
                listString.add(rs.getString("EtatAcc"));
                listString.add(rs.getString("NumRea"));
                listString.add(rs.getString("Image"));
                 listString.add(rs.getString("Duree"));
                listString.add(rs.getString("Description"));
                listString.add(rs.getString("idRes"));
                listString.add(rs.getString("DateDeb"));
                listString.add(rs.getString("DateFin"));
                listString.add(rs.getString("idF"));
                //System.out.println("bonjour");
                
                liste.add(listString);
               
            }
        } catch (SQLException ex) {
            System.out.println("houssem");
            System.out.println(ex.getMessage());
        }
        return liste;
    }
          public List<List<String>> afficheFilmMail() {
        List<List<String>> liste = new ArrayList<>();
        try {
            String requete = "select film.*,reservation.DateDeb,reservation.DateFin,reservation.idRes, avis.MoyenneAvis from film,reservation,avis where film.idF=reservation.idF and avis.idF = film.idF Group by idF";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
           
            
            while (rs.next()) {
                 List<String> listString = new ArrayList<>();
                
                listString.add(rs.getString("nomF"));
                listString.add(rs.getString("Genre"));
                listString.add(rs.getString("Archive"));
                listString.add(rs.getString("EtatAcc"));
                listString.add(rs.getString("NumRea"));
                listString.add(rs.getString("Image"));
                 listString.add(rs.getString("Duree"));
                listString.add(rs.getString("Description"));
                listString.add(rs.getString("idRes"));
                listString.add(rs.getString("DateDeb"));
                listString.add(rs.getString("DateFin"));
                listString.add(rs.getString("idF"));
                listString.add(rs.getString("MoyenneAvis"));
                //System.out.println("bonjour");
                
                liste.add(listString);
               
            }
            
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return liste;
    }
           public List<List<String>> afficheMail() {
        List<List<String>> liste = new ArrayList<>();
        try {
            String requete = "SELECT film.*,compte.mail from film,compte,client,realisateur where film.NumRea = realisateur.NumRea and compte.userName=client.userName and client.idC=realisateur.idC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
           
            
            while (rs.next()) {
                 List<String> listString = new ArrayList<>();
                
                listString.add(rs.getString("nomF"));
                listString.add(rs.getString("Genre"));
                listString.add(rs.getString("Archive"));
                listString.add(rs.getString("EtatAcc"));
                listString.add(rs.getString("NumRea"));
                listString.add(rs.getString("Image"));
                 listString.add(rs.getString("Duree"));
                listString.add(rs.getString("Description"));
                listString.add(rs.getString("mail"));
               
                //System.out.println("bonjour");
                
                liste.add(listString);
               
            }
            
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return liste;
    }
            public List<List<String>> afficheArch() {
        List<List<String>> liste = new ArrayList<>();
        try {
            String requete = "select film.*, reservation.DateDeb,reservation.DateFin,reservation.idRes from film  ,reservation  where film.idF=reservation.idF   and Archive=1";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
           
            
            while (rs.next()) {
                 List<String> listString = new ArrayList<>();
                
                listString.add(rs.getString("nomF"));
                listString.add(rs.getString("Genre"));
                listString.add(rs.getString("Archive"));
                listString.add(rs.getString("EtatAcc"));
                listString.add(rs.getString("NumRea"));
                listString.add(rs.getString("Image"));
                 listString.add(rs.getString("Duree"));
                listString.add(rs.getString("Description"));
                listString.add(rs.getString("idRes"));
                listString.add(rs.getString("DateDeb"));
                listString.add(rs.getString("DateFin"));
                listString.add(rs.getString("idF"));
                //System.out.println("bonjour");
                
                liste.add(listString);
               
            }
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return liste;
    }
          

     public Film recupFilm(int IDFilm) {
        Film f = new Film();

        try {
            String requete = "SELECT * FROM film WHERE idF = ?";
            PreparedStatement prepare = cnx.prepareStatement(requete);
            prepare.setInt(1, IDFilm);

            ResultSet res = prepare.executeQuery();

            while (res.next()) {
                f.setIdF(res.getInt("idF"));
               f.setNomF(res.getString("NomF"));
               f.setGenre(res.getString("Genre"));
               f.setArchive(res.getBoolean("Archive"));
               f.setEtatAcc(res.getString("EtatAcc"));
               f.setNumRea(res.getInt("NumRea"));
               f.setImage(res.getString("Image"));
               f.setDescription(res.getString("Description"));
               f.setDateDispo(res.getTimestamp("DateDispo"));
               f.setDuree(res.getInt("duree"));
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return f;
    }
              
//               f.setNomF(res.getString("NomF"));
//               f.setGenre(res.getString("Genre"));
//               f.setArchive(res.getBoolean("Archive"));
//               f.setEtatAcc(res.getString("EtatAcc"));
//               f.setNumRea(res.getInt("NumRea"));
//               f.setImage(res.getString("Image"));
//               f.setDescription(res.getString("Description"));
//               f.setDateDispo(res.getTimestamp("DateDispo"));
//               f.setDuree(res.getInt("duree"));
//               listeFilm.add(f);
        
     
     public List<Film> afficheFilm2() {
        List<Film> listeFilm = new ArrayList();
        try {
            String requete = "SELECT * FROM Film  ";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Film f = new Film();
              f.setIdF(res.getInt("idF"));
               f.setNomF(res.getString("NomF"));
               f.setGenre(res.getString("Genre"));
               f.setArchive(res.getBoolean("Archive"));
               f.setEtatAcc(res.getString("EtatAcc"));
               f.setNumRea(res.getInt("NumRea"));
               f.setImage(res.getString("Image"));
               f.setDescription(res.getString("Description"));
               f.setDateDispo(res.getTimestamp("DateDispo"));
                f.setDuree(res.getInt("duree"));
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
            PreparedStatement prepare =cnx.prepareStatement(requete);
            prepare.setInt(1, idF);
            int nbEnregSup = prepare.executeUpdate();
            System.out.println("suppression");
            
        } catch (SQLException e) {
            System.out.println("Aucune suppression");
            
        }
        
    }
      public void archiveFilm() {
        List<Film> listeFilm = new ArrayList<Film>();
        listeFilm = afficheFilm2();

        Date date = new Date();
        Timestamp currentDate = new Timestamp(date.getTime());
        System.out.println(currentDate.getYear());
        
        listeFilm.stream()
                // si depasser akther men aam 
                .filter(f -> f.getDateDispo().getYear() - currentDate.getYear() >1)
                .forEach((P) -> {
                    
                    try {
                        
                        String requete = "UPDATE film SET "
                                + "Archive= 1 ";

                        Statement st = cnx.createStatement();
                        st.executeUpdate(requete);
                        
                        

                    } catch (SQLException e) {
                        System.out.println("erreur");
                    }
                });
    }
      //supprimer
         public void SupprimerFilmEtat() {
        List<Film> listeFilm = new ArrayList<Film>();
        listeFilm = afficheFilm2();

        Date date = new Date();
        Timestamp currentDate = new Timestamp(date.getTime());
        System.out.println(currentDate.getDay());
        
        listeFilm.stream()
                //si depasser akther men aam 
                .filter(f -> f.getDateDispo().getDay() - currentDate.getDay() >7 )
                .forEach((P) -> {
                    
                    try {
                        
                        String requete = "DELETE FROM film WHERE EtatAcc='en attente' ";
                        
                        Statement st = cnx.createStatement();
                        st.executeUpdate(requete);
                        
                        

                    } catch (SQLException e) {
                        System.out.println("erreur");
                    }
                });
    }
      
    
}
