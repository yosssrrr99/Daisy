/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class Panier {
    private String idPanier ; 
    private String idProduit ; 
    private String idClient; 
    private String idBillet; 
    private String nomPanier;
    private String statusPanier;
    private String Quantite;

    public Panier() {
    }

    public Panier(String idPanier, String idProduit, String idClient, String idBillet, String nomPanier, String statusPanier, String Quantite) {
        this.idPanier = idPanier;
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.idBillet = idBillet;
        this.nomPanier = nomPanier;
        this.statusPanier = statusPanier;
        this.Quantite = Quantite;
    }

    public Panier(String liste) {
     List<Panier> maListe = new ArrayList<>();  }

    public String getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(String idPanier) {
        this.idPanier = idPanier;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdBillet() {
        return idBillet;
    }

    public void setIdBillet(String idBillet) {
        this.idBillet = idBillet;
    }

    public String getNomPanier() {
        return nomPanier;
    }

    public void setNomPanier(String nomPanier) {
        this.nomPanier = nomPanier;
    }

    public String isStatusPanier() {
        return statusPanier;
    }

    public void setStatusPanier(String statusPanier) {
        this.statusPanier = statusPanier;
    }

    public String getQuantite() {
        return Quantite;
    }

    public void setQuantite(String Quantite) {
        this.Quantite = Quantite;
    }

    @Override
    public String toString() {
        return "Panier{" + "idPanier=" + idPanier + ", idProduit=" + idProduit + ", idClient=" + idClient + ", idBillet=" + idBillet + ", nomPanier=" + nomPanier + ", statusPanier=" + statusPanier + ", Quantite=" + Quantite + '}';
    }

    public Panier(String idProduit, String idClient, String idBillet, String nomPanier, String statusPanier, String Quantite) {
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.idBillet = idBillet;
        this.nomPanier = nomPanier;
        this.statusPanier = statusPanier;
        this.Quantite = Quantite;
    }

    
    
}
