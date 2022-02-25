/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.entities;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Reservation {

    private int idRes;
    private String Categorie;
    private int idEv;
    private int idF;
    private int idSa;
    private int NbPlace;
    private Date dateDebut;
    private Date dateFin;
    

    public Reservation() {
    }

    public Reservation(String Categorie, int idEv, int idF, int idSa, int NbPlace) {
        this.Categorie = Categorie;
        this.idEv = idEv;
        this.idF = idF;
        this.idSa = idSa;
        this.NbPlace = NbPlace;
    }

    public int getIdRes() {
        return idRes;
    }
    

    public String getCategorie() {
        return Categorie;
    }

    public int getIdEv() {
        return idEv;
    }

    public int getIdF() {
        return idF;
    }

    public int getIdSa() {
        return idSa;
    }

    public int getNbPlace() {
        return NbPlace;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }
    
    
    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }
    

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public void setIdSa(int idSa) {
        this.idSa = idSa;
    }

    public void setNbPlace(int NbPlace) {
        this.NbPlace = NbPlace;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
   

    @Override
    public String toString() {
        return "Reservation{" + "Categorie=" + Categorie + ", idEv=" + idEv + ", idF=" + idF + ", idSa=" + idSa + ", NbPlace=" + NbPlace + '}';
    }

}
