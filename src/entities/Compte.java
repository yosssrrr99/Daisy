/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author 21653
 */
public class Compte {
    
    private String username ;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String Role;
    private String Image;
    
    
    public Compte(){
    }
    public Compte(String nom, String prenom, String username, String mail, String mdp, String Role, String img) {
        this.username = username;
        this.mail = mail;
        String hashedPassword = hashPassword(mdp);
        this.mdp = hashedPassword;
        this.Role=Role;
        this.nom=nom;
        this.prenom=prenom;
        this.Image=img;
    }

    public Compte(String username, String mdp) {
         this.username=username;
         this.mdp=mdp;
    }

    public Compte(String username, String mail, String Role, String Image) {
        this.username = username;
        this.mail = mail;
        this.Role = Role;
        this.Image = Image;
    }

    
    

        //To change body of generated methods, choose Tools | Templates.

    public String getUsername() {
        return username;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getRole() {
        return Role;
    }
    public String getImage() {
        return Image;
    }
    public String getMail() {
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setNom(String username) {
        this.nom=nom;
    }
    public void setPrenom(String username) {
        this.prenom = prenom;
    }
    public void setImage(String Image) {
        this.Image = Image;
    }
    

    public void setRole(String Role) {
        this.Role = Role;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    public String hashPassword(String password){
        final String SALT ="SECRETSALT";
        byte[] hashed = null;
        PBEKeySpec spec;
        spec = new PBEKeySpec(password.toCharArray()
                ,SALT.getBytes(),100,256);
        try{
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hashed = skf.generateSecret(spec).getEncoded();
        }catch(NoSuchAlgorithmException | InvalidKeySpecException e){
            System.out.println(e);
        }
        String securedPassword = Base64.getEncoder().encodeToString(hashed);
        return (securedPassword);
    }

    
    
     public int Authentification (String username, String mdp){
         
         if (this.username.equals(username)){
             String hashedPassword = hashPassword(mdp);
             if(this.mdp.equalsIgnoreCase(hashedPassword)){ 
                 
               return 1; 
                 
             }
             else{ 
                 
                 return 0;
             }
         }
         return -1;
     } 

    @Override
    public String toString() {
        return "Compte{" + "username=" + username + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mdp=" + mdp + ", Role=" + Role + ", Image=" + Image + '}';
    }
     

 

   
    

    
    
}
