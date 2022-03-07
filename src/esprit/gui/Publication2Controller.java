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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class Publication2Controller implements Initializable {

    
    @FXML
    private Text description1;
    @FXML
    private ImageView imaage1;
    @FXML
    private Label datee1;
    @FXML
    private Label titre11;
    @FXML
    private Text titreee1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    void setData1(Publication P) {
        InputStream stream = null;
        try {
           

            titre11.setText(String.valueOf(P.getTitre()));
            description1.setText(P.getTxtPub());
            datee1.setText(P.getDateCreationPub().toString());
            stream = new FileInputStream(P.getImgPub());
            Image image = new Image(stream);
            imaage1.setImage(image);
            System.out.println(P);
            

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
    
}
