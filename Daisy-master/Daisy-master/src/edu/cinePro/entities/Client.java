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
public class Client {

    private int idClient;
    private String nom;
    private String prenom;
    private String email;
    private Date dateDeNaissance;
    private String userName;
    private String role;

    public Client() {
    }
    

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Client(int idClient, String nom, String prenom, Date dateDeNaissance) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
    }

    public Client(int idClient, String nom, String prenom, String email, Date dateDeNaissance) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
    }

    public Client(int idClient, String nom, String prenom, String email, Date dateDeNaissance, String userName, String role) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.userName = userName;
        this.role = role;
    }

//test
    
    

    public int getIdClient() {
        return idClient;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }
    

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Client{" + "idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", dateDeNaissance=" + dateDeNaissance + ", userName=" + userName + ", role=" + role + '}';
    }
    
    
    
    


  
    
    
    
    

}
