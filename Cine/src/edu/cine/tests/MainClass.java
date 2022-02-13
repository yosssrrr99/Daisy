/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.tests;

import edu.cine.entities.Film;
import edu.cine.services.FilmCRUD;
import edu.cine.utils.MyConnection;
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
        Film f1= new Film("regarse", "action", true , "accepté", 3, "img","yahki aalik");
        fc.ajouterFilm2(f1);
       //fc.modifierFilm(8, "ons", "action", true, "accepté", 4, "img","yahki aalik");
      // List<Film> listFilm = fc.afficheFilm();
       //for(Film f2: listFilm){
           // System.out.println(f2);
       //}
       //fc.archiveFilm();
       //fc.supprimerFilm(8);
       
       
  
      
    }
}
