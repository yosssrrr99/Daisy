/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hp
 */
public class Facture {
  private  int idFacture ; 
   private String DateCreation ; 
   private float Total ; 
   private String idPanier ; 

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public String getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(String DateCreation) {
        this.DateCreation = DateCreation;
    }

    public float getTotal() {
        
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public String getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(String idPanier) {
        this.idPanier = idPanier;
    }

    public Facture(int idFacture, String DateCreation, float Total, String idPanier) {
        this.idFacture = idFacture;
        this.DateCreation = DateCreation;
        this.Total = Total;
        this.idPanier = idPanier;
    }

    public Facture(String DateCreation, float Total) {
        this.DateCreation = DateCreation;
        this.Total = Total;
    }

    public Facture(String DateCreation, float Total, String idPanier) {
        this.DateCreation = DateCreation;
        this.Total = Total;
        this.idPanier = idPanier;
    }

    public Facture() {
    }
    

    @Override
    public String toString() {
        return "Facture{" + "idFacture=" + idFacture + ", DateCreation=" + DateCreation + ", Total=" + Total + ", idPanier=" + idPanier + '}';
    }
   
}
