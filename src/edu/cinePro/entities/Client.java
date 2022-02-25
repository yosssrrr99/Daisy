/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.entities;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Client {

    private int idClient;

    private String nom;
    private String prenom;
    private Date dateDeNaissance;

    public Client() {
    }

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Client(int idClient, String nom, String prenom, Date dateDeNaissance) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
    }

    public int getIdClient() {
        return idClient;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    @Override
    public String toString() {
        return "Client{" + "idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", dateDeNaissance=" + dateDeNaissance + '}';
    }
    
    
    
    

}
