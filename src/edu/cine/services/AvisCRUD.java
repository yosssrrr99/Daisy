/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.services;

import edu.cine.entities.Avis;
import edu.cine.entities.Film;
import edu.cine.entities.Reservation;
import edu.cine.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class AvisCRUD {
    Connection cnx;
    public AvisCRUD(){
        cnx = MyConnection.getInstance().getCnx();
    }
    public void ajouteAvis(Avis a){
        
        String requete = "INSERT INTO `avis`(`idC`, `idF`, `nbEtoile`,`commentaire` ) "
                + "VALUES (?,?,?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(requete);
            ps.setInt(1,a.getIdC());
            ps.setInt(2,a.getIdF());
            ps.setFloat(3, a.getNbEtoile());
            ps.setString(4,a.getCommentaire());
            ps.executeUpdate();
            System.out.println("Avis ajoutée dynamiquement");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    public void modifierAvis(int idAvis,int idC,int idF,int nbEtoile,String commentaire) {
        
        
        try {
            String req = "UPDATE avis SET "
                    + "idC= ?, "
                    + "idF = ?, "
                    + "nbEtoile = ?, "
                    + "Commentaire = ? "
                    + "WHERE idAvis = ?";

            PreparedStatement prepare = cnx.prepareStatement(req);
            prepare.setInt(1, idC);
            prepare.setInt(2, idF);
            prepare.setInt(3, nbEtoile);
            prepare.setString(4, commentaire);
           prepare.setInt(5, idAvis);

            prepare.executeUpdate();
            System.out.println("modification d'avis ");

        } catch (SQLException e) {
            
            System.out.println("\"Aucune modification d'avis ");

        }
        
    }
     public List<Avis> afficheAvis() {
        List<Avis> listeAv = new ArrayList();
        try {
            String requete = "SELECT * FROM Avis";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Avis a = new Avis();

               a.setIdC(res.getInt("idC"));
               a.setIdF(res.getInt("idF"));
               a.setNbEtoile(res.getInt("nbEtoile"));
               a.setCommentaire(res.getString("commentaire"));
               a.setMoyenneAvis(res.getFloat("MoyenneAvis"));
               listeAv.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return listeAv;
    }
     
      public void supprimerAvis(int idAvis) {
        
        try {
            String requete = "DELETE FROM Avis WHERE idAvis = ?";
            PreparedStatement prepare =  cnx.prepareStatement(requete);
            prepare.setInt(1, idAvis);
            prepare.executeUpdate();
            System.out.println("suppression");
            
        } catch (SQLException e) {
            System.out.println("Aucune suppression");
            
        }
        
    }
 public void calculAvis(){
      
        try {
            String req="SELECT idF,AVG(NbEtoile) from Avis WHERE nbEtoile <> 404 GROUP BY idF";
            PreparedStatement st = cnx.prepareStatement(req);
            //st.setInt(1, idF);
            ResultSet moy  = st.executeQuery();
           
            while (moy.next()) {
                TreeMap<Integer,Double> myTreeMap=new TreeMap();
                myTreeMap.put(moy.getInt(1), moy.getDouble(2));
                
                  for( int f:myTreeMap.keySet()){
                 // System.out.println("idFilm:"+f+"  NbEtoile:"+myTreeMap.get(f));
                  //Integer b = f;
                  //System.out.println(f);
                  //Double a=myTreeMap.get(f);
                  //System.out.println(a);
                  String req1 = "UPDATE avis SET "
                    + "MoyenneAvis = ? "
                    + "WHERE idF = ? And nbEtoile <>404";
                   PreparedStatement prepare = cnx.prepareStatement(req1);
                   prepare.setDouble(1, myTreeMap.get(f));
                   prepare.setDouble(2, f);

                  prepare.executeUpdate();
                      System.out.println("moyenne ajoutée");
                  
                  }
               // System.out.println(myTreeMap);
               
               // System.out.println("avg is        " + moy.getFloat(1)+ "    " +moy.getFloat(2));
               
                
                
            }       } catch (SQLException ex) {
           System.out.println("erreur");
        }
 }
 public void AfdicheTop(){
      
        try {
            String req="SELECT idF,AVG(NbEtoile) from Avis WHERE nbEtoile <> 404 GROUP BY idF";
            PreparedStatement st = cnx.prepareStatement(req);
            //st.setInt(1, idF);
            ResultSet moy  = st.executeQuery();
           
            if (moy.next()) {
                TreeMap<Integer,Double> myTreeMap=new TreeMap();
                myTreeMap.put(moy.getInt(1), moy.getDouble(2));
                
                  for( int f:myTreeMap.keySet()){
                  System.out.println("idFilmTop:"+f+"  NbEtoile:"+myTreeMap.get(f));
                  
                  }
               // System.out.println(myTreeMap);
               
               // System.out.println("avg is        " + moy.getFloat(1)+ "    " +moy.getFloat(2));
               // Set<Integer> Top = myTreeMap.keySet();
                //System.out.println("top des films est d'id"+Top);
                
                
            }       } catch (SQLException ex) {
           System.out.println("erreur");
        }
 }
  public List<Avis> afficheMoy() {
        List<Avis> listeAv = new ArrayList();
        try {
            String requete = "SELECT MoyenneAvis FROM Avis";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Avis a = new Avis();
                a.setMoyenneAvis(res.getFloat("MoyenneAvis"));
               listeAv.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return listeAv;
    }
 
 public Integer AfficherMaxMoy() {
       
                //si depasser akther men aam 
                List<Avis> listAvis =  afficheAvis();
                               Optional av = listAvis.stream().max(Comparator.comparing(Avis::getMoyenneAvis)).map(a->a.getIdF());
                         if(av.isPresent()){
                            return (Integer) av.get();  
                         }
                          //System.out.println("MaxAvis: " + av.get());
        return null;
   }
  public void AfficherMaxMoy2() {
      List<Avis> listeAv = new ArrayList();
                AvisCRUD ac = new AvisCRUD();
                int a =ac.AfficherMaxMoy();
                 try {;

                       
       String requete = "SELECT f.nomF,f.Genre,f.Image,f.Description,f.duree,f.dateDispo, a.MoyenneAvis,a.Commentaire FROM film f ,avis a WHERE f.idF=a.idF and f.idF='"+a+"'";
                         
                         
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Avis a1 = new Avis();
                Film f=new Film();
               
                f.setNomF(res.getString("nomF"));
                f.setGenre(res.getString("Genre"));
                f.setImage(res.getString("Image"));
                f.setDescription(res.getString("Description"));
                f.setDuree(res.getInt("duree"));
                f.setDateDispo(res.getTimestamp("datedispo"));
                a1.setMoyenneAvis(res.getFloat("MoyenneAvis"));
                a1.setCommentaire(res.getString("commentaire"));
                System.out.println(f+"      " +a1);
            };
            

                    } catch (SQLException e) {
                        System.out.println("erreur");
                    }
                
                    
  
                   
                
    }
}
 

    
 
 
 
 
 
 
 
                                

                        

