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
public class Realisateur extends Client {
    private String NomOrg;

    public Realisateur() {
    }

 
    public Realisateur(String NomOrg, String nom, String prenom, Date DateNaiss, String userName, String mail, String mdp, String role, String Image) {
        super(nom, prenom, DateNaiss, userName, mail, mdp, role, Image);
        this.NomOrg = NomOrg;
    }

    public String getNomOrg() {
        return NomOrg;
    }

    public void setNomOrg(String NomOrg) {
        this.NomOrg = NomOrg;
    }

    

  

   
  
}
