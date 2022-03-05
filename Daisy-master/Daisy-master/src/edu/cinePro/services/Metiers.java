

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.services;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import edu.cinePro.utils.MyConnection;

/**
 *
 * @author hp
 */
public class Metiers{
    Connection cnx2;

    public Metiers() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
       public float esaiRemise() throws SQLException {
        float prix;
        int Quantite;
       
        float total;
        String role ; 

        String requete6 = "SELECT p.prixVenteUnit ,pa.Quantite ,  c.role FROM produit p ,panier pa,  client c  where pa.idProduit = p.idProduit  and pa.idClient = c.idClient ";
        //String requete7 = "SELECT PrixAdulte FROM billet";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(requete6);
        //ResultSet rs1 = st.executeQuery(requete7);

        while (rs.next()) {
            prix = rs.getFloat("prixVenteUnit");
            Quantite=rs.getInt("Quantite");
            
            role = rs.getString("role");
            
            //prixB = rs1.getFloat("PrixAdulte");
           // Compte c = new Compte();
            if (role.equals("etudiant")){
                return total =(float) ((prix * Quantite)* 0.2);
            }
            else 
                return total =(prix * Quantite) ; 
            
        }

        return 0;

    }
       //FactureCRUD fc = new FactureCRUD();
      
     
    
}
