/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.tests;

import edu.cinePro.entities.Produit;
import edu.cinePro.services.ProduitMethods;
import edu.cinePro.entities.Billet;
import edu.cinePro.services.BilletMethods;
import edu.cinePro.utils.MyConnection;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author Asus
 */
public class MainClass {

    public static void main(String[] args) {
        
        

        BilletMethods BM = new BilletMethods();
        Billet B = new Billet("testFilm", 6, false, 1, 1);
        BM.ajouterBillet(B);
        BM.affichageBillets();
        
        BM.modifierBillet(1,"second classe", 17, 1, 1);
        
        
        

        //test Produit
//        ProduitMethods PM = new ProduitMethods();
//    
//        Produit P;
//        P = new Produit("testDescription", "testDesignation", "testImage", 17, 55, 66, true);
//
//        //////////////////////////////test ajout Produit//////////////////////////////////
//        PM.ajouterProduit(P);
//
//        //////////////////////////////test affichage produit/////////////////////////////
//        System.out.println(PM.affichageProduits());
//
//        /////////////////////////////test suppression produit///////////////////////////
//        PM.supprimerProduit(6);
//
//        /////////////////////////////test modification produit///////////////////////////
//        PM.modifierProduit(13, "testDesignation3", "testDescription3", "testImage3", 0, 55, 66, true);
//
      
    

}
