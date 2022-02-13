/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.entities;

/**
 *
 * @author DELL
 */
public class Reservation {
    private String Categorie;
    private int idEv;
    private int idF;
    private int idSa;
    private int NbPlace;

    public Reservation() {
    }

    public Reservation(String Categorie, int idEv, int idF, int idSa, int NbPlace) {
        this.Categorie = Categorie;
        this.idEv = idEv;
        this.idF = idF;
        this.idSa = idSa;
        this.NbPlace = NbPlace;
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
    
    
}
