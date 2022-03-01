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
public class Compte {
    private String userName;
    private String mail;
    private String mdp;
    private String role;
    private String Image;

    public Compte() {
    }

    public Compte(String userName, String mail, String mdp, String role, String Image) {
        this.userName = userName;
        this.mail = mail;
        this.mdp = mdp;
        this.role = role;
        this.Image = Image;
    }

    public String getUserName() {
        return userName;
    }

    public String getMail() {
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    public String getRole() {
        return role;
    }

    public String getImage() {
        return Image;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    
}
