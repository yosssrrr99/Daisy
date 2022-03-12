/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.entities;

import static sun.util.calendar.CalendarUtils.mod;

/**
 *
 * @author DELL
 */
public class Avis {
    private int idC;
    private int idF;
    private float nbEtoile=404;
    private String commentaire;
    private float MoyenneAvis; 

    public Avis() {
    }

    public Avis(int idC, int idF, float nbEtoile,String commentaire) {
        this.idC = idC;
        this.idF = idF;
          if((nbEtoile<=5)&&(nbEtoile>=0)&&((nbEtoile%1==0)||(nbEtoile%1==0.5))){
         this.nbEtoile = nbEtoile;
    }
        this.commentaire=commentaire;
    }

    public Avis(float nbEtoile) {
        this.nbEtoile=nbEtoile;
        
    }

    public int getIdC() {
        return idC;
    }

    public int getIdF() {
        return idF;
    }

    public float getNbEtoile() {
        return nbEtoile;
    }

    public String getCommentaire() {
        return commentaire;
    }
     public float getMoyenneAvis() {
        return MoyenneAvis;
    }


    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public void setNbEtoile(float nbEtoile) {
         if((nbEtoile<=5)&&(nbEtoile>=0)&&((nbEtoile%1==0)||(nbEtoile%1==0.5))){
         this.nbEtoile = nbEtoile;
    }
         
       
    }

    public void setMoyenneAvis(float MoyenneAvis) {
        this.MoyenneAvis = MoyenneAvis;
    }
  
   
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Avis{" + "idC=" + idC + ", idF=" + idF + ", nbEtoile=" + nbEtoile + ", commentaire=" + commentaire + ", MoyenneAvis=" + MoyenneAvis + '}';
    }
    

    
    
    
}
