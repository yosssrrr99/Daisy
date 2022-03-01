/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.tests;

import edu.cine.entities.Avis;
import edu.cine.entities.Film;
import edu.cine.entities.Realisateur;
import edu.cine.entities.Reservation;
import edu.cine.services.AvisCRUD;
import edu.cine.services.FilmCRUD;
import edu.cine.services.RealisateurCRUD;
import edu.cine.services.ReservationCRUD;
import edu.cine.utils.MyConnection;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class MainClass {
    public static void main(String[] args) {
        //ajouter film suppression ajout insertion affichage archive
        //MyConnection mc = new MyConnection();
        FilmCRUD fc = new FilmCRUD();
       //fc.ajouterFilm();
       // Film f2 = new Film("dachra", "action", true, "accepté", 3, "img", "bbb", 5);
       //Film f1= new Film("regardemoi", "action", true , "accepté", 2, "img","yahki aalik");
        //fc.ajouterFilm2(f2);
      //fc.modifierFilm(2, "ons", "action", true, "accepté", 2, "img","actionnnnnnn");
     // List<Film> listFilm = fc.afficheFilm2();
       //for(Film f2: listFilm){
         // System.out.println(f2);
      //}
     //fc.archiveFilm();
       //fc.supprimerFilm(8);
       //fc.SupprimerFilmEtat();
       
      ReservationCRUD rc = new ReservationCRUD();
      //Date d1 = new Date(10, 10, 2020);
     // Date d2 = new Date(2020, 10, 23);
      LocalDate date = LocalDate.of(2020, 02, 23);
      Date date2 = Date.valueOf(date);
      //System.out.print(date2);
     // LocalDate datee = LocalDate.of(2020, 02, 25);
      //Date date1 = Date.valueOf(datee);
      //System.out.print(date1);
      
        //System.out.println(d1);
        //Reservation r = new Reservation();
       // java.sql.Date newDate1 = new java.sql.Date(date1.getTime());
        //System.out.println(newDate1);
     //Reservation r = new Reservation("evenement", 1, 8, 4, 50, date1, date2);
     Reservation r = new Reservation("film", 1, 8, 4, 50, "22-12-2020", "22-10-2021");
     rc.ajouterRes(r);
     //rc.modifierRes(12, "film", 1, 8, 4, 60, LocalDate.of(2020, 02, 23), LocalDate.of(2020, 02, 25));
      //rc.annulerRes(13);
    // List<Reservation> listRes = rc.afficheRes();
      //for(Reservation f2: listRes){
        // System.out.println(f2);
      //}
     AvisCRUD ac = new AvisCRUD();
       //Avis a = new Avis(1,8,3f,"yyyy");
       //ac.ajouteAvis(a);
       //ac.modifierAvis(6, 1, 8, 2, "Bien");
       //ac.supprimerAvis(6);
      // List<Avis> listAv =ac.afficheAvis();
       //for(Avis av:listAv){
         //  System.out.println(av);
       //}
       //int a= ac.AfficherMaxMoy();
        //System.out.println(a);
       // List<Avis> lili =ac.AfficherMaxMoy2();
        //f//or(Avis av:lili){
           //System.out.println(av);
       //}
       ac.AfficherMaxMoy2();
       //ac.calculAvis();
     //ac.AfdicheTop();
      RealisateurCRUD rcc=new RealisateurCRUD();
       //Realisateur r =  new Realisateur(1, "Cactus");
       //rcc.ajouterRea(r);
       //rcc.modifierRea(4, 2, "Lepi d'or");
       //rcc.désactiverRea(5);
       //List <Realisateur> listRea = rcc.afficheRea();
       //for(Realisateur r:listRea){
         //  System.out.println(r);
       //}
      // fc.SupprimerFilmEtat();
       
       
       
  
      
    }
}
