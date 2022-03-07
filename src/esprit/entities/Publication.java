/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.entities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author sarra
 */
public class Publication {

    private int idPub;
    private String titre;
    private String imgPub;
    private String txtPub;
    private Timestamp dateCreationPub;
    private int idPresse;
    private boolean archive;

    public Publication(String titre ,String imgPub, String txtPub, int idPresse) {
        this.idPub = idPub;
        this.imgPub = imgPub;
        this.txtPub = txtPub;
         this.titre = titre;
         this.idPresse = idPresse;
    }

    public Publication(String titre, String imgPub, String txtPub) {
        this.titre = titre;
        this.imgPub = imgPub;
        this.txtPub = txtPub;
    }

    public Publication() {
    }

    public Publication(int idPub, String titre, String imgPub, String txtPub, Timestamp dateCreationPub, int idPresse, boolean archive) {
        this.idPub = idPub;
        this.titre = titre;
        this.imgPub = imgPub;
        this.txtPub = txtPub;
        this.dateCreationPub = dateCreationPub;
        this.idPresse = idPresse;
        this.archive = archive;
    }

    public Publication(String imgPub, String txtPub, int idPresse, boolean archive) {

        this.idPresse = idPresse;
        this.imgPub = imgPub;
        this.txtPub = txtPub;

        this.archive = archive;
    }

    public Publication(int idPub, String titre, String imgPub, String txtPub, Timestamp dateCreationPub, boolean archive) {
        this.idPub = idPub;
        this.titre = titre;
        this.imgPub = imgPub;
        this.txtPub = txtPub;
        this.dateCreationPub = dateCreationPub;
        this.archive = archive;
    }

    public Publication(int idPub, String imgPub, String txtPub, Timestamp dateCreationPub, int idPresse, boolean archive) {
        this.idPub = idPub;
        this.imgPub = imgPub;
        this.txtPub = txtPub;
        this.dateCreationPub = dateCreationPub;
        this.idPresse = idPresse;
        this.archive = archive;
    }

    public Publication(String titre, String imgPub, String txtPub, Timestamp dateCreationPub, int idPresse, boolean archive) {
        this.titre = titre;
        this.imgPub = imgPub;
        this.txtPub = txtPub;
        this.dateCreationPub = dateCreationPub;
        this.idPresse = idPresse;
        this.archive = archive;
    }

    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    

    public Publication(String imgPub, String txtPub) {
        this.imgPub = imgPub;
        this.txtPub = txtPub;
    }

    public Publication(String txtPub, int idPresse) {
        this.txtPub = txtPub;
        this.idPresse = idPresse;
    }

    public Publication( String imgPub, String txtPub, int idPresse) {

        this.idPresse = idPresse;
        this.imgPub = imgPub;
        this.txtPub = txtPub;

    }

    public int getIdPub() {
        return idPub;
    }

    public int getIdPresse() {
        return idPresse;
    }

    public String getImgPub() {
        return imgPub;
    }

    public String getTxtPub() {
        return txtPub;
    }

    public Timestamp getDateCreationPub() {
        return dateCreationPub;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setIdPub(int idPub) {
        this.idPub = idPub;
    }

    public void setIdPresse(int idPresse) {
        this.idPresse = idPresse;
    }

    public void setImgPub(String imgPub) {
        this.imgPub = imgPub;
    }

    public void setTxtPub(String txtPub) {
        this.txtPub = txtPub;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public void setDateCreationPub(Timestamp dateCreationPub) {
        this.dateCreationPub = dateCreationPub;
    }

    @Override
    public String toString() {
        return "Publication{" + "idPub=" + idPub + ", titre=" + titre + ", imgPub=" + imgPub + ", txtPub=" + txtPub + ", dateCreationPub=" + dateCreationPub + ", idPresse=" + idPresse + ", archive=" + archive + '}';
    }

    

}
