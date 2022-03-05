/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Client;
import edu.cinePro.entities.Don;
import edu.cinePro.services.DonMethods;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DonController implements Initializable {

    private static Message prepareMessage(Session session, String myAccountEmail, String receveur) throws AddressException, MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receveur));
        message.setSubject("Réception de don");
        String htmlCode = "<h1>   Chere Madame Yasmine SLITI </h1> <br/> <h2> <b> On vient de recevoir votre don . Merci pour votre générosité. </b> </h2>";
        message.setContent(htmlCode, "text/html");
        return message;

    }

    @FXML
    private BorderPane BorderPane;
    @FXML
    private Label outOfStockLabel;
    @FXML
    private VBox chosenFruitCard1;
    @FXML
    private HBox montantDonInput;
    @FXML
    private Label donMontantAffichage;
    @FXML
    private VBox chosenFruitCard2;
    @FXML
    private Label outOfStockLabel2;
    @FXML
    private TextField donMontant;
    @FXML
    private Button boutonPaiementDon;
    @FXML
    private ImageView SidaImg;
    @FXML
    private Text dondescription;
    @FXML
    private Text paiementDescription;

    DonMethods DM = new DonMethods();
    float montantHorsFisc = 0;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boutonPaiementDon.setDisable(true);
        //controle saisie sur le montant
        Pattern pattern = Pattern.compile("\\d*|\\d+\\,\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        donMontant.setTextFormatter(formatter);

        //listener sur changement montant don
        donMontant.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {

                float montantAvecFisc = parseInt(newValue);
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                this.montantHorsFisc = DM.montantDON_HFisc(montantAvecFisc);
                donMontantAffichage.setText(String.valueOf(df.format(montantHorsFisc)) + " TD");
                boutonPaiementDon.setDisable(false);
            } else if (newValue.equals("")) {
                donMontantAffichage.setText("0" + " TD");
                boutonPaiementDon.setDisable(true);
            }
        });

    }

    @FXML
    private void validerDon(ActionEvent event) throws Exception {
        Don D = new Don(montantHorsFisc, 1);
        DM.ajouterDon(D);
        try {
            sendMail("yasmine.sliti@esprit.tn");
        } catch (Exception ex) {
            Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void sendMail(String receveur) throws Exception {

        System.out.println("Preparing to send email");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "yasmin.slitti@gmail.com";
        String password = "azizaBalti1964*";

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, receveur);
        Transport.send(message);
        System.out.println("message send successfully");

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
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("billet.fxml"));

        try {
            Parent root = Loader.load();
            BilletController billets = Loader.getController();
            donMontant.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(BilletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showSouvenirShopPage(ActionEvent event) {

        FXMLLoader Loader = new FXMLLoader(getClass().getResource("productClient.fxml"));

        try {
            Parent root = Loader.load();
            ProductClientController products = Loader.getController();
            donMontant.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProductClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showParametrePage(ActionEvent event) {
    }

    @FXML
    private void showDonationButton(ActionEvent event) {

        FXMLLoader Loader = new FXMLLoader(getClass().getResource("don.fxml"));

        try {
            Parent root = Loader.load();
            DonController dons = Loader.getController();
            donMontant.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
