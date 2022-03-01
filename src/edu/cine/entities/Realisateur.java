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
public class Realisateur  {
    private int idC;
    private int NumRea;
    private String NomOrg;
    private String UserName;

    public Realisateur() {
    }

    public Realisateur(int NumRea, String NomOrg, String UserName) {
        this.NumRea = NumRea;
        this.NomOrg = NomOrg;
        this.UserName = UserName;
    }

  

    public Realisateur(String NomOrg, String UserName) {
        this.NomOrg = NomOrg;
        this.UserName = UserName;
    }

    public String getUserName() {
        return UserName;
    }

    public int getIdC() {
        return idC;
    }

    
 
   

    public String getNomOrg() {
        return NomOrg;
    }

    public int getNumRea() {
        return NumRea;
    }

    

   
  

    

    public void setNomOrg(String NomOrg) {
        this.NomOrg = NomOrg;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setNumRea(int NumRea) {
        this.NumRea = NumRea;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }
    

    @Override
    public String toString() {
        return "Realisateur{" + "NumRea=" + NumRea + ", NomOrg=" + NomOrg + ", UserName=" + UserName + '}';
    }
    


    

  

   
  
}
