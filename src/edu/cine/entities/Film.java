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
    private int idF;
    private String nomF;
    private String Genre;
    private boolean Archive;
    private String EtatAcc;
    private int NumRea;
    private String Image;
    private String Description;
    private Timestamp dateDispo;
    private int duree;
    

    public Film() {
    }

    public Film(String nomF, String Genre, int NumRea, String Image, String Description, int duree) {
        this.nomF = nomF;
        this.Genre = Genre;
        this.NumRea = NumRea;
        this.Image = Image;
        this.Description = Description;
        this.duree = duree;
    }
    

    public Film(String nomF, String Genre, boolean Archive, String EtatAcc, int NumRea, String Image, String Description) {
        this.nomF = nomF;
        this.Genre = Genre;
        this.Archive = Archive;
        this.EtatAcc = EtatAcc;
        this.NumRea = NumRea;
        this.Image = Image;
        this.Description = Description;
        
    }

    public Film(int idF, String nomF, String Genre, boolean Archive, String EtatAcc, int NumRea, String Image, String Description, Timestamp dateDispo) {
        this.idF = idF;
        this.nomF = nomF;
        this.Genre = Genre;
        this.Archive = Archive;
        this.EtatAcc = EtatAcc;
        this.NumRea = NumRea;
        this.Image = Image;
        this.Description = Description;
        this.dateDispo = dateDispo;
    }

    public Film(String nomF, String Genre, boolean Archive, String EtatAcc, int NumRea, String Image, String Description, int duree) {
        this.nomF = nomF;
        this.Genre = Genre;
        this.Archive = Archive;
        this.EtatAcc = EtatAcc;
        this.NumRea = NumRea;
        this.Image = Image;
        this.Description = Description;
      
        this.duree = duree;
    }

    public int getDuree() {
        return duree;
    }
    

    public int getIdF() {
        return idF;
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

    public void setIdF(int idF) {
        this.idF = idF;
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

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Film{" + "idF=" + idF + ", nomF=" + nomF + ", Genre=" + Genre + ", Archive=" + Archive + ", EtatAcc=" + EtatAcc + ", NumRea=" + NumRea + ", Image=" + Image + ", Description=" + Description + ", dateDispo=" + dateDispo + ", duree=" + duree + '}';
    }
    
    
   

    
}
