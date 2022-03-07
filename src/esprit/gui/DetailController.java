/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

import esprit.entities.Publication;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class DetailController implements Initializable {

    
    @FXML
    private AnchorPane paneFarRight;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label tit1;
    @FXML
    private Label datee;
    @FXML
    private Text desc1;
    @FXML
    private Label idpp;
    @FXML
    private ImageView imgpub1;
    @FXML
    private Button retour;
    @FXML
    private Text tit;
    @FXML
    private Text descrip;
    @FXML
    private Text date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void setDetail(Publication p) throws FileNotFoundException {
//
        tit1.setText(p.getTitre());
        desc1.setText(p.getTxtPub());
        datee.setText(p.getDateCreationPub().toString());
        idpp.setText(String.valueOf(p.getIdPub()));
        
        InputStream stream = new FileInputStream(p.getImgPub());
            Image imagee = new Image(stream);
            imgpub1.setImage(imagee);
        

    }
    
    
    @FXML
    private void Retour(MouseEvent event) {

        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            
            Stage primaryStage= new Stage();
            FXMLLoader FL = new FXMLLoader(getClass().getResource("AjouterPresse.fxml"));
            Parent root = FL.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
