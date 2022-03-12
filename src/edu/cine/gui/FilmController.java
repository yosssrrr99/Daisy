/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;


import edu.cine.entities.Avis;
import edu.cine.services.AvisCRUD;
import edu.cine.utils.MyConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FilmController implements Initializable {

    @FXML
    private Label du;
    @FXML
    private Text des;
    @FXML
    private Label titre;
    @FXML
    private ImageView image;
    @FXML
    private Label dated;
    @FXML
    private Label datef;
    @FXML
    private Label idres;
    @FXML
    private Label idff;
    @FXML
    private TextField av;
    @FXML
    private Button res;
    @FXML
    private Label moy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    
    }    
   

    
    void setData(List<String> ls) {
     
            InputStream stream = null;
        try {
           
            titre.setText(ls.get(0));
            du.setText(String.valueOf(ls.get(6)));
            des.setText(ls.get(7));
            dated.setText(ls.get(8));
            datef.setText(ls.get(9));
            idres.setText(ls.get(8));
            idff.setText(ls.get(11));
             moy.setText(ls.get(12));
            stream = new FileInputStream(ls.get(5));
            Image imagee = new Image(stream);
       
            
           
            image.setImage(imagee);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilmController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(FilmController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void ajouterProduitPanier(ActionEvent event) {
         FXMLLoader Loader = new FXMLLoader(getClass().getResource("bibi.fxml"));

        try {
            Parent root = Loader.load();
            // b= Loader.getController();
            String id = idres.getText();
        
//               b.setidd(id);
            
            Scene productDetailScene = new Scene(root);
            Stage cineStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            cineStage.setScene(productDetailScene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterAvis(ActionEvent event) {
       

        if (av.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Le champs est vides");
            alert.setContentText("Veuillez remplir le  champs");
            alert.showAndWait();

        } else {

        String avis= av.getText();
        int iddd=Integer.parseInt(avis);
              String film= idff.getText();
        int idddd=Integer.parseInt(film);
       Avis a = new Avis(1, idddd, iddd, "commentaire");
        AvisCRUD ac = new AvisCRUD();
       ac.ajouteAvis(a);
       ac.calculAvis();
          Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout");

            alert.setHeaderText(null);
            alert.setContentText("votre avis a été ajoutée avec succées !");

            alert.showAndWait();
        }

    }
}


       
    

    

