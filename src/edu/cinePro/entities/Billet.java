
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.entities;

import java.sql.Timestamp;

/**
 *
 * @author Asus
 */
public class Billet {

    private int IDBillet;
    private String categorieBillet;
    private int nb_place;
    private Timestamp created_on;
    private boolean archieved;
    private int idReservation;
    private int idClient;

    public Billet() {
    }

  

    public Billet(String categorieBillet, int nb_place, boolean archieved, int idReservation, int idClient) {
        this.categorieBillet = categorieBillet;
        this.nb_place = nb_place;

        this.archieved = archieved;
        this.idReservation = idReservation;
        this.idClient = idClient;
    }

    public Billet(int IDBillet, String categorieBillet, int nb_place, Timestamp created_on, boolean archieved, int idReservation, int idClient) {
        this.IDBillet = IDBillet;
        this.categorieBillet = categorieBillet;
        this.nb_place = nb_place;
        this.created_on = created_on;
        this.archieved = archieved;
        this.idReservation = idReservation;
        this.idClient = idClient;
    }

    public int getIDBillet() {
        return IDBillet;
    }

    public String getCategorieBillet() {
        return categorieBillet;
    }

    public int getNb_place() {
        return nb_place;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public boolean isArchieved() {
        return archieved;
    }

    public int getIdReservation() {
        return idReservation;
    }


    public int getIdClient() {
        return idClient;
    }

    public void setIDBillet(int IDBillet) {
        this.IDBillet = IDBillet;
    }

    public void setCategorieBillet(String categorieBillet) {
        this.categorieBillet = categorieBillet;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }

    public void setArchieved(boolean archieved) {
        this.archieved = archieved;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

   

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Billet{" + "IDBillet=" + IDBillet + ", categorieBillet=" + categorieBillet + ", nb_place=" + nb_place + ", created_on=" + created_on + ", archieved=" + archieved + ", idReservation=" + idReservation + ", idClient=" + idClient + '}';
    }

   

}
