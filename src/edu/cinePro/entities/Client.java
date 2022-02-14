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
public class Client {
    
    private int idClient;

    private String nom;
    private String prenom;

    public Client() {
    }

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
     public int getIdClient() {
        return idClient;
    }
    
    
}
