/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.entities;

/**
 *
 * @author sarra
 */
public class Compte {
    private String userName ;
    private String mail;
    private String mdp;
    private String Role;
    private String Image;
    
    
    public Compte(){
    }

    public Compte(String mail) {
        this.mail = mail;
    }
    

    public Compte(String mail, String Image) {
        this.mail = mail;
        this.Image = Image;
    }

    

        //To change body of generated methods, choose Tools | Templates.

    public String getUsername() {
        return userName;
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

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return  " ,mail=" + mail + ", Image=" + Image ;
    }
    
    

    
    
     
     

    
    
}
