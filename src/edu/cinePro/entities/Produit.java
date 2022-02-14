/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.entities;

/**
 *
 * @author Asus
 */
public class Produit implements Comparable<Produit> {

    private int IDProduit;
    private String Designation;
    private String Description;
    private String Image;
    private int quantiteEnStock;
    private double prixAchatUnit;
    private double prixVenteUnit;
    private boolean statusStock;

    public Produit() {
    }

    public Produit(String Description, String Designation, String Image, int quantiteEnStock, double prixAchatUnit, double prixVenteUnit, boolean statusStock) {
        this.Description = Description;
        this.Designation = Designation;
        this.Image = Image;
        this.quantiteEnStock = quantiteEnStock;
        this.prixAchatUnit = prixAchatUnit;
        this.prixVenteUnit = prixVenteUnit;
        this.statusStock = statusStock;
    }

    public Produit(int IDProduit, String Designation, String Description, String Image, int quantiteEnStock, double prixAchatUnit, double prixVenteUnit, boolean statusStock) {
        this.IDProduit = IDProduit;
        this.Designation = Designation;
        this.Description = Description;
        this.Image = Image;
        this.quantiteEnStock = quantiteEnStock;
        this.prixAchatUnit = prixAchatUnit;
        this.prixVenteUnit = prixVenteUnit;
        this.statusStock = statusStock;
    }

    public int getIDProduit() {
        return IDProduit;
    }

    public String getDesignation() {
        return Designation;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public double getPrixAchatUnit() {
        return prixAchatUnit;
    }

    public double getPrixVenteUnit() {
        return prixVenteUnit;
    }

    public boolean isStatusStock() {
        return statusStock;
    }

    public void setIDProduit(int IDProduit) {
        this.IDProduit = IDProduit;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        if (quantiteEnStock >= 0) {
            this.quantiteEnStock = quantiteEnStock;
        } else {
            this.quantiteEnStock = 0;
        }
    }

    public void setPrixAchatUnit(double prixAchatUnit) {
        this.prixAchatUnit = prixAchatUnit;
    }

    public void setPrixVenteUnit(double prixVenteUnit) {
        this.prixVenteUnit = prixVenteUnit;
    }

    public void setStatusStock(boolean statusStock) {
        this.statusStock = statusStock;
    }

    @Override
    public String toString() {
        return "Produit{" + "IDProduit=" + IDProduit + ", Designation=" + Designation + ", Description=" + Description + ", Image=" + Image + ", quantiteEnStock=" + quantiteEnStock + ", prixAchatUnit=" + prixAchatUnit + ", prixVenteUnit=" + prixVenteUnit + ", statusStock=" + statusStock + '}';
    }

    public double calculateProductProfit(Produit P) {

        double margeProfit = (P.prixVenteUnit - P.prixAchatUnit) / (P.prixVenteUnit);
        return margeProfit;

    }

    @Override
    public int compareTo(Produit o) {
        if (this.calculateProductProfit(this) - o.calculateProductProfit(o)>0)
            return 1;
        else if ((this.calculateProductProfit(this) - o.calculateProductProfit(o)==0))
            return 0;
        else return -1;
    }

}
