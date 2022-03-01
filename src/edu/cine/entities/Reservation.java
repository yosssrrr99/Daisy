/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.entities;

import java.time.LocalDate;
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
    private String DateDeb;
    private String DateFin;
    
    public Reservation() {
    }

    public Reservation(String Categorie, int idEv, int idF, int idSa, int NbPlace) {
        this.Categorie = Categorie;
        this.idEv = idEv;
        this.idF = idF;
        this.idSa = idSa;
        this.NbPlace = NbPlace;
    }

    public Reservation(String Categorie, int idEv, int idF, int idSa, int NbPlace, String DateDeb, String DateFin) {
        this.Categorie = Categorie;
        this.idEv = idEv;
        this.idF = idF;
        this.idSa = idSa;
        this.NbPlace = NbPlace;
        this.DateDeb = DateDeb;
        this.DateFin = DateFin;
    }

    public Reservation(int idRes, String Categorie, int idEv, int idF, int idSa, int NbPlace, String DateDeb, String DateFin) {
        this.idRes = idRes;
        this.Categorie = Categorie;
        this.idEv = idEv;
        this.idF = idF;
        this.idSa = idSa;
        this.NbPlace = NbPlace;
        this.DateDeb = DateDeb;
        this.DateFin = DateFin;
    }

    public int getIdRes() {
        return idRes;
    }
    

    public String getDateDeb() {
        return DateDeb;
    }

    public String getDateFin() {
        return DateFin;
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

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
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

    public void setDateDeb(String DateDeb) {
        this.DateDeb = DateDeb;
    }

    public void setDateFin(String DateFin) {
        this.DateFin = DateFin;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idRes=" + idRes + ", Categorie=" + Categorie + ", idEv=" + idEv + ", idF=" + idF + ", idSa=" + idSa + ", NbPlace=" + NbPlace + ", DateDeb=" + DateDeb + ", DateFin=" + DateFin + '}';
    }
 
  
   

    

   
    
    
}
