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
public class Avis {
    private int idC;
    private int idF;
    private float nbEtoile;

    public Avis() {
    }

    public Avis(int idC, int idF, float nbEtoile) {
        this.idC = idC;
        this.idF = idF;
        this.nbEtoile = nbEtoile;
    }

    public int getIdC() {
        return idC;
    }

    public int getIdF() {
        return idF;
    }

    public float getNbEtoile() {
        return nbEtoile;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public void setNbEtoile(float nbEtoile) {
        this.nbEtoile = nbEtoile;
    }
    
}
