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
public class Presse extends Compte {

    private int id;
    private String userName;
    private boolean badgeAttribue = false;

    public Presse(int id, String userName, String mail, String Image, boolean badgeAttribue) {
        super(mail, Image);
        this.userName = userName;
        this.id = id;

        this.badgeAttribue = badgeAttribue;
    }

    public Presse() {
    }

    public Presse(String userName, boolean badgeAttribue) {
        this.userName = userName;
        this.badgeAttribue = badgeAttribue;
    }

    public Presse(String userName) {
        this.userName = userName;
    }

    public Presse(int id,String userName, String mail, boolean badgeAttribue) {
         super(mail);
        this.id=id;
        this.badgeAttribue=badgeAttribue;
        this.userName = userName;
    }

    public Presse(int id, String userName, boolean badgeAttribue) {
        this.id=id;
        this.badgeAttribue=badgeAttribue;
        this.userName = userName;
    }

  

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    public boolean getBadgeAttribue() {
        return badgeAttribue;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBadgeAttribué(boolean badgeAttribue) {
        this.badgeAttribue = badgeAttribue;
    }

    @Override
    public String toString() {
        return "Presse{ id=" + id + ", userName=" + userName + super.toString() + ", badgeAttribue=" + badgeAttribue + '}';
    }

}
