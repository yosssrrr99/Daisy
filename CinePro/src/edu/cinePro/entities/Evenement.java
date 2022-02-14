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
public class Evenement {
    private int idEvenement;
    private String nomEvenement;
    private int nbPlaceDisponible;
    private Date date;
    private int idFilm;

    public Evenement() {
    }

    public Evenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public Evenement(int idEvenement, String nomEvenement, int nbPlaceDisponible, Date date, int idFilm) {
        this.idEvenement = idEvenement;
        this.nomEvenement = nomEvenement;
        this.nbPlaceDisponible = nbPlaceDisponible;
        this.date = date;
        this.idFilm = idFilm;
    }

    public int getIdEvenement() {
        return idEvenement;
    }
    
    
}
