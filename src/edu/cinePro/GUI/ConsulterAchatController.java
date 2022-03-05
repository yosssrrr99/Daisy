/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Panier;
import edu.cinePro.entities.Produit;
import edu.cinePro.services.FactureCRUD;
import edu.cinePro.services.Metiers;
import edu.cinePro.services.PanierCRUD;
import entities.Facture;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static java.time.LocalDate.now;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ConsulterAchatController implements Initializable {

    @FXML
    private TextField Liste;
    @FXML
    private Button ok;
    @FXML
    private Button notOk;
    //private Label label;
    @FXML
    private Label label;
    @FXML
    public ScrollPane scroll;
    @FXML
    public GridPane grid;
    @FXML
    private Label idProduit;
    @FXML
    private Label idClient;
    @FXML
    private Label nomPanier;
    @FXML
    private Label status;
    @FXML
    private Label qt2;
    @FXML
    private Label idBillet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ok.setStyle("-fx-background-color: #598a52");
        notOk.setStyle("-fx-background-color: #e44635");
        label.setVisible(false);
        idClient.setVisible(false);
        idProduit.setVisible(false);
        nomPanier.setVisible(false);
        status.setVisible(false);
        qt2.setVisible(false);
        Liste.setVisible(false);

        idBillet.setVisible(false);
        PanierCRUD pc = new PanierCRUD();
        String idproduit = idProduit.getText();
        String idcl = "1";
        String idB = "1";
        String nompanier = nomPanier.getText();
        String stat = status.getText();
        String quant = qt2.getText();

        Panier p1 = new Panier(idproduit, idcl, idB, nompanier, stat, quant);
        List<List<String>> listPanier = pc.afficherPanier();

        System.out.println(listPanier);
        int column = 0;
        int row = 1;
        for (int i = 0; i < listPanier.size(); i++) {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterAchat.fxml"));
                fxmlLoader.setLocation(getClass().getResource("LigneCommande.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                LigneCommandeController lc = fxmlLoader.getController();
                //ConsulterAchatController ca = fxmlLoader.getController();
                lc.setData(listPanier.get(i));
                System.out.println("cccccc+" + listPanier.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException ex) {
                System.out.println("ereeur");
            }

        }
    }

    public void setListe(String Liste) {
        this.Liste.setText(Liste);
    }

    @FXML
    private void validerPan(ActionEvent event) {
         
        try {

            FactureCRUD fc = new FactureCRUD();
            Metiers m = new Metiers();
            //CharSequence l = Liste.getCharacters();
            //String l2 = (String) l.subSequence(17, 18);

            Facture f1 = new Facture(now().toString(), m.esaiRemise(), "1");

            fc.ajouterFacture2(f1);
            //FactureCRUD fcc = new FactureCRUD();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Facture.fxml"));
            try {
                Parent root = loader.load();
                FactureController f = loader.getController();

                f.setIdF(fc.afficherFacture(f1).toString());

                Liste.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ConsulterAchatController.class.getName()).log(Level.SEVERE, null, ex);
            }
       } catch (SQLException ex) {
           Logger.getLogger(ConsulterAchatController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @FXML
    private void viderPan(ActionEvent event) {

        // CharSequence l =  Liste.getCharacters();
        // String l2 = (String) l.subSequence(17, 19);
        //Panier p = new Panier(l);
        //List<Panier> maListe = new ArrayList<>();
        //maListe.add(p);
        //PanierCRUD pc = new PanierCRUD();
        //pc.ViderPanier();
        //System.out.println(l2);
        notOk.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showConfirmation();
            }
        });

    }

    private void showConfirmation() {
        PanierCRUD pc = new PanierCRUD();
        Panier p1 = new Panier("1", "1", "1", "panier salma", "0", "5");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("supprimer panier");
        alert.setHeaderText("Êtes-vous sûr de vouloir déplacer cette panier vers la corbeille ?");
        alert.setContentText("C:/panier");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("No selection!");
        } else if (option.get() == ButtonType.OK) {
            pc.ViderPanier(p1);
            scroll.setVisible(false);
            notOk.setVisible(false);
            ok.setVisible(false);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("panier vide");
            alert1.setHeaderText("votre panier est vide");
            //alert1.setContentText("C:/panier");
            alert1.showAndWait();
            //label.setVisible(true);
            Liste.setVisible(false);
            ok.setVisible(false);
            this.label.setText("panier supprimée !");

            //System.out.println("*"+Liste.getText().substring(0, 0));
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Cancelled!");
        } else {
            this.label.setText("-");
        }
    }

}
