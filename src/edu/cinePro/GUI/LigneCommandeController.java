/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Panier;
import edu.cinePro.entities.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import edu.cinePro.services.PanierCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class LigneCommandeController implements Initializable {

    @FXML
    private Label prduit1;
    @FXML
    private Label prix;
    @FXML
    private ImageView img;
    @FXML
    private Button suppProd ;
    @FXML
    private Label label;
    @FXML
    private AnchorPane ss;
    @FXML
    private VBox vbox;
    @FXML
    private Label idprd;
    @FXML
    private Label qt;
    @FXML
    private ImageView pubelle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        label.setVisible(false);
            // TODO
            idprd.setVisible(false);
            suppProd.setStyle( "-fx-background-color: #DCDCDC");
        
        
    }   
     void setData(List<String> LS) {
          InputStream stream = null;
        try {
           
            Produit pr = new Produit();
            PanierCRUD pc = new PanierCRUD();
            //List<Produit> listprd = pc.affichageProduits();
                       
            //InputStream stream = null;
            prduit1.setText(LS.get(0));
            prix.setText(LS.get(1)+"DT");
            idprd.setText(LS.get(3));
            qt.setText(LS.get(4));
            stream = new FileInputStream(LS.get(2));
            Image image = new Image(stream);
            img.setImage(image);
            //description.setText(pr.getDescription());
            //designation.setText(pr.getDesignation());
            // System.out.println(P);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LigneCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(LigneCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        

    }

    @FXML
    private void supprimerProduit(ActionEvent event) {
         suppProd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                List<String> LS = null ;
                showConfirmation(LS);
            }
        });
    }
     private void showConfirmation(List<String> LS) {
       PanierCRUD pc = new PanierCRUD();
       
       String prd = idprd.getText();
       Panier p1 = new Panier(prd, "8", "3", "panier salma", "0", "5");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("supprimer panier");
      alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce produit  ?");
      //alert.setContentText("C:/panier");

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         this.label.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
          pc.DeletePanier(p1);
          //label.setVisible(true);
          //setData(LS);
          //Liste.setVisible(false);
          //ok.setVisible(false);
          vbox.setVisible(false);
          img.setVisible(false);
          suppProd.setVisible(false);
          pubelle.setVisible(false);
         this.label.setText("panier supprimée !");
         
        
          //System.out.println("*"+Liste.getText().substring(0, 0));
         
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Cancelled!");
      } else {
         this.label.setText("-");
      }
    }
}
