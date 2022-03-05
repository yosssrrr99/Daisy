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
public class Don {
    
    int IDDon;
    float montant;
    int idClient;

    public Don() {
    }

    public Don(float montant, int idClient) {
        this.montant = montant;
        this.idClient = idClient;
    }

    public Don(int IDDon, float montant, int idClient) {
        this.IDDon = IDDon;
        this.montant = montant;
        this.idClient = idClient;
    }

    public int getIDDon() {
        return IDDon;
    }

    public float getMontant() {
        return montant;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIDDon(int IDDon) {
        this.IDDon = IDDon;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Don{" + "IDDon=" + IDDon + ", montant=" + montant + ", idClient=" + idClient + '}';
    }
    
    
    
    
}
