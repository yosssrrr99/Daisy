/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Billet;
import edu.cinePro.services.BilletMethods;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class BilletPaiementController implements Initializable {

    @FXML
    private BorderPane BorderPane;
    @FXML
    private ImageView filmImage;
    @FXML
    private Label titreFilm;
    @FXML
    private Label dateFilm;
    @FXML
    private Label heureFilm;
    @FXML
    private Label salleID;
    @FXML
    private Label prixBilletInitial;
    @FXML
    private Label prixBilletFinal;
    @FXML
    private Button payerBillerButton;
    @FXML
    private Button nosFilmsButtons;
    @FXML
    private Button nosPublicationsButton;
    @FXML
    private Button nosEvenementsButton;
    @FXML
    private Button réservationsButton;
    @FXML
    private Button shouvenirShopButton;
    @FXML
    private Button donationButton;
    @FXML
    private Button parametreButton;
    Billet B;
    BilletMethods BM = new BilletMethods();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String details = "Voici votre QR code";
        ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
        File F = new File("C:\\Users\\Asus\\Desktop\\CineProWithGUI\\src\\edu\\cinePro\\GUI\\images\\QRCode.jpg");
        try {
            FileOutputStream fos = new FileOutputStream(F);
            fos.write(out.toByteArray());
            fos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BilletPaiementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BilletPaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setBilletDetails(List<Object> detailsBillets, Billet B) {
        this.B = B;
        System.out.println(B);
        titreFilm.setText(detailsBillets.get(0).toString());
        InputStream stream = null;
        try {
            stream = new FileInputStream(detailsBillets.get(1).toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BilletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(stream);
        filmImage.setImage(image);

        dateFilm.setText("Date   :    " + detailsBillets.get(3).toString());
        String minute = detailsBillets.get(5).toString();
        if (parseInt(detailsBillets.get(5).toString()) <= 9) {
            minute = "0" + minute;
        }
        heureFilm.setText("Heure   :    " + detailsBillets.get(4).toString() + " : " + minute);
        salleID.setText("Salle   :    " + detailsBillets.get(6).toString());
        System.out.println(B);
        prixBilletInitial.setText(BM.prixBilletInitial(B) + " DT");
        prixBilletFinal.setText(BM.prixBilletTTC(B) + " DT");
    }

    @FXML
    private void deleteBillet(MouseEvent event) throws FileNotFoundException {
        System.out.println(B);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppréssion de billet");
        alert.setHeaderText(null);
        alert.setContentText("Vouliez vous supprimer le billet ? ");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {

            BM.supprimerBillet(B.getIDBillet());
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("billet.fxml"));
            try {
                Parent root = Loader.load();
                BilletController Billet = Loader.getController();
                Scene BilletScene = new Scene(root);
                Stage cineStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                cineStage.setScene(BilletScene);

            } catch (IOException ex) {
                Logger.getLogger(ShowProductsDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            // refreshTable();
        }

    }

    @FXML
    private void payerBillet(ActionEvent event) {
        Hyperlink hyperlink = new Hyperlink("hyperlink");
        payerBillerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.paypal.com/bizsignup/#/checkAccount"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });
    }

    @FXML
    private void showFilmsPage(ActionEvent event) {
    }

    @FXML
    private void showPublicationPage(ActionEvent event) {
    }

    @FXML
    private void showEvenementsPage(ActionEvent event) {
    }

    @FXML
    private void showReservationPage(ActionEvent event) {
    }

    @FXML
    private void showSouvenirShopPage(ActionEvent event) {
    }

    @FXML
    private void showDonationButton(ActionEvent event) {
    }

    @FXML
    private void showParametrePage(ActionEvent event) {
    }

   
}
