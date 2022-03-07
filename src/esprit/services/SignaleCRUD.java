/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.services;

import esprit.entities.Publication;
import esprit.entities.Signale;
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
public class SignaleCRUD {

    Connection cnx;

    public SignaleCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterSignale(Signale S) {

        try {
            String requete = "INSERT INTO `signale`( idClient ,idPub, nbreSignal) "
                    + "VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setInt(1, S.getIdClient());
            pst.setInt(2, S.getIdPub());
            pst.setInt(3, S.getNbreSignal());

            pst.executeUpdate();

            System.out.println("Votre signal est ajouté dynamiquement avec succés!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Signale> affichageSignale() {
        List<Signale> myListe = new ArrayList<>();

        try {
            String requete = "SELECT * FROM signale ";
            Statement st = cnx.createStatement();
            st.executeQuery(requete);
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Signale p = new Signale();
                p.setIdSignal(res.getInt("idSignal"));
                p.setIdClient(res.getInt("idClient"));
                p.setIdPub(res.getInt("idPub"));
                p.setNbreSignal(res.getInt("nbreSignal"));

                myListe.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myListe;

    }

    public void supprimerPubParSignale() {

        try {
            String requete = "SELECT SUM(nbreSignal) FROM signale GROUP BY idPub";

            PreparedStatement pst = cnx.prepareStatement(requete);

            ResultSet x = pst.executeQuery();
            while (x.next()) {

                int somme = ((Number) x.getObject(1)).intValue();

                List<Signale> myliste = new ArrayList<Signale>();
                myliste = affichageSignale();

                myliste.stream()
                        .filter((f) -> somme >= 2)
                        .forEach((p) -> {

                            try {
                                String req = " DELETE FROM publication WHERE publication.idPub IN (SELECT idPub FROM signale)";

                                Statement st = new MyConnection().getCnx().createStatement();
                                st.executeUpdate(req);
                                System.out.println("publication supprimée par nbre de signales");

                            } catch (SQLException ex) {

                                System.err.println(ex.getMessage());
                            }
                        });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
