/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.entities;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Client extends Compte{
    private String nom;
    private String prenom;
    private Date DateNaiss;

    public Client() {
    }

    public Client(String nom, String prenom, Date DateNaiss, String userName, String mail, String mdp, String role, String Image) {
        super(userName, mail, mdp, role, Image);
        this.nom = nom;
        this.prenom = prenom;
        this.DateNaiss = DateNaiss;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaiss() {
        return DateNaiss;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaiss(Date DateNaiss) {
        this.DateNaiss = DateNaiss;
    }

  
 
    
    
    
}
