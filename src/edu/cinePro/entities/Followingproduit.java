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
public class Followingproduit {

    private int IDProduit;
    private int idClient;

    public Followingproduit() {
    }

    public Followingproduit(int IDProduit, int idClient) {
        this.IDProduit = IDProduit;
        this.idClient = idClient;
    }

    public int getIDProduit() {
        return IDProduit;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIDProduit(int IDProduit) {
        this.IDProduit = IDProduit;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Followingproduit{" + "IDProduit=" + IDProduit + ", idClient=" + idClient + '}';
    }
    

}
