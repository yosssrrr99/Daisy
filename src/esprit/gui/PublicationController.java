/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import esprit.entities.Publication;
import esprit.entities.Signale;
import esprit.services.SignaleCRUD;
import esprit.utils.MyConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class PublicationController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private ImageView imaage;
    @FXML
    private Text description;
    @FXML
    private Label datee;
    Connection cnx = MyConnection.getInstance().getCnx();
    Signale s;
    Publication P;
    @FXML
    private FontAwesomeIconView signal;
    @FXML
    private VBox vbox;
    @FXML
    private Label idpubb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO4
        //Signale S = new Signale(1, P.getIdPub(), 1);
        

        SignaleCRUD pc = new SignaleCRUD();
        System.out.println("houniiii");
        pc.supprimerPubParSignale();
        setData(P);
        
    }

     void setData(Publication P) {
        InputStream stream = null;
        try {

            titre.setText(String.valueOf(P.getTitre()));
            description.setText(P.getTxtPub());
            datee.setText(P.getDateCreationPub().toString());
            idpubb.setText(String.valueOf(P.getIdPub()));
            stream = new FileInputStream(P.getImgPub());
            Image image = new Image(stream);
            imaage.setImage(image);
            System.out.println(P);

            signal.setOnMouseClicked((MouseEvent event1) -> {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Vouliez vous signaler la publication ? ");
                Optional<ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {

                    Signale S = new Signale(1, P.getIdPub(), 1);


                    SignaleCRUD pc = new SignaleCRUD();
                    pc.ajouterSignale(S);
                                       

                }
            });

        } catch (FileNotFoundException ex) {
            System.out.println("errr1");
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                System.out.println("errr2");
            }
        }

    }


//    @FXML
//    private void getId(MouseEvent event) {
//    }
//
//    @FXML
//    private void Signal(MouseEvent event) {
//    }

//    @FXML
//    private void getId(MouseEvent event) {
//    }
//
//    @FXML
//    private void Signal(MouseEvent event) {
//    }

    @FXML
    private void getId(MouseEvent event) {
    }

    @FXML
    private void Signal(MouseEvent event) {
    }
}
