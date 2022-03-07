/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.services;

import esprit.entities.Publication;
import esprit.utils.MyConnection;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author sarr
 */
public class PublicationCRUD implements Job{

    Connection cnx;

    public PublicationCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

//    public void ajouterPublication0() {
//        try {
//            String requete = "INSERT INTO publication(imgPub,txtPub,dateCreationPub,idPresse) VALUES (1,'imagesarah','textesarah',NOW(),145)";
//            Statement st = cnx.createStatement();
//            st.executeUpdate(requete);
//            System.out.println("publication ajoutée statiquement avec succés");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//
//    }

    public void ajouterPublication(Publication p) {
        boolean archive = false;

        try {
            String requete2 = "INSERT INTO publication (titre,imgPub,txtPub,idPresse) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, p.getTitre());
            pst.setString(2, p.getImgPub());
            pst.setString(3, p.getTxtPub());

            pst.setInt(4, p.getIdPresse());
            

            pst.executeUpdate();

            System.out.println("Votre publication est ajoutée dynamiquement avec succés!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean supprimerPublication(int idPub) {
        boolean pSup = true;
        try {
            String requete3 = "DELETE FROM Publication WHERE idPub = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete3);

            pst.setInt(1, idPub);
            pst.executeUpdate();
            System.out.println("publication supprimée");
        } catch (SQLException ex) {
            pSup = false;
            System.err.println(ex.getMessage());
        }
        return pSup;

    }

    public boolean modifierPublication(String imgPub, String txtPub) {
        boolean pModif = true;
        try {
            String requete4 = "UPDATE publication SET imgPub= ?, txtPub= ? WHERE idPub= ? ";
            PreparedStatement pst = cnx.prepareStatement(requete4);
            pst.setString(1, imgPub);
            pst.setString(2, txtPub);
           // pst.setInt(3, idPub);

            pst.executeUpdate();
            System.out.println("publication modifiée");

        } catch (SQLException ex) {
            pModif = false;
            System.err.println(ex.getMessage());
        }
        return pModif;

    }

    public List<Publication> consulterPublication() {
        List<Publication> myListe = new ArrayList<>();

        try {
            String requete5 = "SELECT * FROM publication WHERE archive= 0";
            Statement st = cnx.createStatement();
            st.executeQuery(requete5);
            ResultSet res = st.executeQuery(requete5);

            while (res.next()) {
                Publication p = new Publication();
                p.setIdPub(res.getInt("idPub"));
                p.setTitre(res.getString("titre"));
                p.setImgPub(res.getString("imgPub"));
                p.setTxtPub(res.getString("txtPub"));
                p.setDateCreationPub(res.getTimestamp("dateCreationPub"));

                p.setArchive(res.getBoolean("archive"));
                myListe.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myListe;

    }

    public List<Publication> consulterPublicationArchive() {
        List<Publication> myListe = new ArrayList<>();

        try {
            String requete5 = "SELECT * FROM publication WHERE archive= 1";
            Statement st = cnx.createStatement();
            st.executeQuery(requete5);
            ResultSet res = st.executeQuery(requete5);

            while (res.next()) {
                Publication p = new Publication();
                p.setIdPub(res.getInt("idPub"));
                p.setImgPub(res.getString("imgPub"));
                p.setTxtPub(res.getString("txtPub"));
                p.setDateCreationPub(res.getTimestamp("dateCreationPub"));
                p.setArchive(res.getBoolean("archive"));
                myListe.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myListe;

    }

//    public void archiverPublication() {
//        List<Publication> myliste = new ArrayList<Publication>();
//        myliste = consulterPublication();
//
//        Date date = new Date();
//        Timestamp currentDate = new Timestamp(date.getTime());
//        System.out.println(currentDate.getDate());
//
//        myliste.stream()
//                .filter(f -> (currentDate.getDate() - f.getDateCreationPub().getDate()) < 1)
//                .forEach((p) -> {
//
//                    try {
//                        String req = "UPDATE publication SET "
//                                + "Archive= 1 ";
//
//                        Statement st = new MyConnection().getCnx().createStatement();
//                        st.executeUpdate(req);
//
//                    } catch (SQLException ex) {
//                        
//                        System.err.println(ex.getMessage());
//                    }
//                });
//    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
                        String req = "UPDATE publication SET "
                                + "Archive= 1 ";

                        Statement st = new MyConnection().getCnx().createStatement();
                        st.executeUpdate(req);
                        System.out.print("publication archivée à l'heure  "+

                new Date()  + "\n");

                    } catch (SQLException ex) {
                        
                        System.err.println(ex.getMessage());
                    } 
    }
    
      public void modifierPublication1 (int idPub,String titre ,String imgPub, String txtPub) {
        try {
            String requete4 = "UPDATE publication SET titre= ?,imgPub= ?, txtPub= ? WHERE idPub= ? ";
            PreparedStatement pst = cnx.prepareStatement(requete4);
            pst.setString(1, titre);
            pst.setString(2, imgPub);
            pst.setString(3, txtPub);
            
                        

           pst.setInt(4, idPub);

            pst.executeUpdate();
            System.out.println("publication modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
      
      public Publication RecupPub(int IDpublication) {
      

        Publication p = new Publication();

        try {
            String requete = "SELECT * FROM publication WHERE idPub = ?";
            PreparedStatement prepare = cnx.prepareStatement(requete);
            prepare.setInt(1, IDpublication);

            ResultSet res = prepare.executeQuery();

            while (res.next()) {
              p.setIdPub(res.getInt("idPub"));
              p.setTitre(res.getString("titre"));
                p.setImgPub(res.getString("imgPub"));
                p.setTxtPub(res.getString("txtPub"));
                p.setDateCreationPub(res.getTimestamp("dateCreationPub"));
                //p.setArchive(res.getBoolean("archive"));
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
      
      
    }


