/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.tests;

import edu.cinePro.entities.Produit;
import edu.cinePro.services.ProduitMethods;
import edu.cinePro.entities.Billet;
import edu.cinePro.entities.Don;
import edu.cinePro.services.BilletMethods;
import edu.cinePro.services.DonMethods;
import edu.cinePro.utils.MyConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Asus
 */
public class MainClass {

    public static void main(String[] args) {

        //test entitié billet
        
        BilletMethods BM = new BilletMethods();

       Billet B2 = new Billet("Second Class", 3, false, 1, 1);

        //BM.ajouterBillet(B2);
        //BM.afficherPrixBillet(B2);
        //BM.affichageBillets();
       // BM.modifierBillet(4, "Third Class", 17, 1, 1);
//       Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                BM.archiverBillet();
//            }
//        }, 0, 24 * 60 * 60 * 1000);//24*60*60*1000 delay de 1 jours entre l'execution du CRON d'archivage
//        System.out.println("\n##########################################################################################################################################################################################################################################");
//
//     BM.recupVente_Par_Film();
//        System.out.println("\n##########################################################################################################################################################################################################################################");
//        // recherche de billet multicritere
//       List<Billet> rechercheBillet = BM.rechercherBillet("balti");
//        if (rechercheBillet.isEmpty()) {
//            System.out.println("Aucune billet ne correspond à votre recherche");
//        } else {
//
//            rechercheBillet.forEach((B) -> {
//                System.out.println(B);
//            });
//
//        }



//        //test Produit
       ProduitMethods PM = new ProduitMethods();
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
//       PM.supprimerProduit(40);
//
//        /////////////////////////////test modification produit///////////////////////////
//      PM.modifierProduit(38, "testDesignation5", "testDescription5", "testImage5", 9,55, 55, true);
//
//        ///////////////////////////test profit produit//////////////////////////////////
//        PM.MaxProfitable(); 
//        System.out.println("####################################################################################");
//        PM.affichageProfitabilité();
//        System.out.println("####################################################################################");
//       PM.affichageQuantiteVendu_Produit();
        //CRON job update stockStatus inStock(1) => OutOfStock(2)

//
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                PM.updateStockStatus();
//            }
//        }, 0, 1000);//1000 => une seconde delay entre l'execution du CRON mise à jour status stock 0 = epsuisé / 1 = en stock

       // CRON job remise 50% sur les produits lors de la periode du festival du JCC
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.set(2022, 01, 15, 0, 0, 0);
//        Date dateDebutJCC = calendar1.getTime();
//        System.out.println(dateDebutJCC);
//
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                PM.PromotionPrixVente();
//            }
//        }, dateDebutJCC, 360 * 24 * 60 * 60 * 1000);//360*24*60*60*1000 ajout delais de une année





// Don test
//
//       DonMethods DM = new DonMethods();
//      Don D = new Don(10000, 2);
//      DM.ajouterDon(D);
//      System.out.println(DM.affichageDons());
//      DM.supprimerDon(3);
//        DM.modifierDon(1, 7500, 1);
//        DM.recupSommeDon_par_client();
//        DM.afficherDonAvant_apres_TVA(D);
    }

}
