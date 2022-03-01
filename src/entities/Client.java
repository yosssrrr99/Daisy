/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 *
 * @author 21653
 */
public class Client {
    private String  nom;
    private String prenom;
    private String dateNaiss;
    private String userName;
    
    public Client (){
    } 

    public Client(String nom, String prenom, String dateNaiss, String userName) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.userName = userName;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDatenaiss() {
        return dateNaiss;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDatenaiss(String datenaiss) {
        this.dateNaiss = datenaiss;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    
    

    public String getUsername() {
        return userName;
    }
    
    public void printAge(){
        String[] arrayString = this.dateNaiss.split("/");
        int  arrayInt [] = {0,0,0};
        for(int i=0 ; i<arrayString.length;i++){
            arrayInt[i] = Integer.parseInt(arrayString[i]);
        }
        LocalDate date = LocalDate.of(arrayInt[0], arrayInt[1], arrayInt[2]);
        LocalDate now = LocalDate.now();
        Period period = Period.between(date, now);
        System.out.println(+period.getYears()+"ans "+" et"+period.getChronology()+"mois");
    }

    @Override
    public String toString() {
        return "Client{" + "nom=" + nom + ", prenom=" + prenom + ", datenaiss=" + dateNaiss + ", username=" + userName + '}';
    }

    
    

    
    
}
