/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.services;

import esprit.entities.Compte;
import esprit.entities.Presse;
import esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sarra
 */
public class PresseCRUD {

    Connection cnx;

    public PresseCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterPresse(Presse p) {

        try {
            String requete = "INSERT INTO presse (userName, badgeAttribue) "
                    + "VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
                        
            pst.setString(1, p.getUserName());

            pst.setBoolean(2, p.getBadgeAttribue());

            pst.executeUpdate();

            System.out.println("presse ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean supprimerPresse(int id) {
        boolean pDel = true;
        try {
            String requete3 = "DELETE FROM Presse WHERE id = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete3);

            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("presse supprimée");
        } catch (SQLException ex) {
            pDel = false;
            System.err.println(ex.getMessage());
        }
        return pDel;
    }

    public boolean modifierPresse(int id, String userName, boolean badgeAttribue) {
        boolean pMod = true;
        
        try {
            String req = "UPDATE presse SET badgeAttribue=? WHERE id= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
           
            pst.setBoolean(1, badgeAttribue);

            pst.executeUpdate();
            System.out.println("presse modifiée");

        } catch (SQLException ex) {
            pMod = false;
            System.err.println(ex.getMessage());
        }
        return pMod;

    }

    public List<Presse> afficherListePresse() {
        List<Presse> Liste = new ArrayList<>();
        

        try {
            //String req = "SELECT * FROM presse where badgeAttribue= 0 ";
            String req = "SELECT C.*,P.* FROM presse P, compte C WHERE P.userName= C.userName ";

            Statement st = cnx.createStatement();
            st.executeQuery(req);
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Presse p = new Presse();
                
                p.setId(res.getInt("id"));
                p.setUsername(res.getString("userName"));
                p.setMail(res.getString("mail"));
                p.setImage(res.getString("Image"));
                p.setBadgeAttribué(res.getBoolean("badgeAttribue"));

                Liste.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return Liste;

    }
    public Presse RecupPresse(int IDpresse) {
       
                
//                p.setId(res.getInt("id"));
//                p.setUsername(res.getString("userName"));
//                p.setMail(res.getString("mail"));
//                p.setImage(res.getString("Image"));
//                p.setBadgeAttribué(res.getBoolean("badgeAttribue"));

       Presse p = new Presse();

        try {
            String requete = "SELECT * FROM Presse WHERE id = ?";
            PreparedStatement prepare = cnx.prepareStatement(requete);
            prepare.setInt(1, IDpresse);

            ResultSet res = prepare.executeQuery();

            while (res.next()) {
               p.setId(res.getInt("id"));
                p.setUsername(res.getString("userName"));
                p.setMail(res.getString("mail"));
                p.setImage(res.getString("Image"));
                p.setBadgeAttribué(res.getBoolean("badgeAttribue"));
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
}

   

