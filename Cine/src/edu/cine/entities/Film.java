/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author DELL
 */
public class Film {
    private String nomF;
    private String Genre;
    private boolean Archive;
    private String EtatAcc;
    private int NumRea;
    private String Image;
    private String Description;
    private Timestamp dateDispo;

    public Film() {
    }

    public Film(String nomF, String Genre, boolean Archive, String EtatAcc, int NumRea, String Image, String Description) {
        this.nomF = nomF;
        this.Genre = Genre;
        this.Archive = Archive;
        this.EtatAcc = EtatAcc;
        this.NumRea = NumRea;
        this.Image = Image;
        this.Description = Description;
        this.dateDispo=dateDispo;
    }

    public String getNomF() {
        return nomF;
    }

    public String getGenre() {
        return Genre;
    }

    public boolean isArchive() {
        return Archive;
    }

    public String getEtatAcc() {
        return EtatAcc;
    }

    public int getNumRea() {
        return NumRea;
    }

    public String getImage() {
        return Image;
    }
    public String getDescription() {
        return Description;
    }

    public Timestamp getDateDispo() {
        return dateDispo;
    }
     


    public void setNomF(String nomF) {
        this.nomF = nomF;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public void setArchive(boolean Archive) {
        this.Archive = Archive;
    }

    public void setEtatAcc(String EtatAcc) {
        this.EtatAcc = EtatAcc;
    }

    public void setNumRea(int NumRea) {
        this.NumRea = NumRea;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
     public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setDateDispo(Timestamp dateDispo) {
        this.dateDispo = dateDispo;
    }

    @Override
    public String toString() {
        return "Film{" + "nomF=" + nomF + ", Genre=" + Genre + ", Archive=" + Archive + ", EtatAcc=" + EtatAcc + ", NumRea=" + NumRea + ", Image=" + Image + ", Description=" + Description + ", dateDispo=" + dateDispo + '}';
    }
     

    
    
}
