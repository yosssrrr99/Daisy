/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Billet;
import edu.cinePro.entities.Client;
import edu.cinePro.entities.Reservation;
import edu.cinePro.services.BilletMethods;
import edu.cinePro.services.ClientMethods;
import edu.cinePro.services.ReservationCRUD;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class BilletController implements Initializable {

    @FXML
    private Label titreFilm;
    @FXML
    private Text descriptionFilm;
    @FXML
    private Label dateReservation;
    @FXML
    private Label salleReservation;
    @FXML
    private VBox movieInformation;
    @FXML
    private Label titreFilm1;
    @FXML
    private Button reinitialiser;
    @FXML
    private Button AcheterBillet;
    @FXML
    private ComboBox categorieBillet;
    @FXML
    private Spinner<Integer> nbPlaceBillet;
    @FXML
    private Label Total;
    @FXML
    private Label remise;
    @FXML
    private Label prixFinalHT;
    @FXML
    private Label prixFinalTTC;

    BilletMethods BM = new BilletMethods();

    ClientMethods CM = new ClientMethods();
    ReservationCRUD RM = new ReservationCRUD();

    Client C = CM.recupClient(1);
    Reservation R = RM.recupReservation(1);
    @FXML
    private ImageView imageFilm;
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
    @FXML
    private Label genreFilm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(BM.recup_info_film(1));

        titreFilm.setText(BM.recup_info_film(1).get(0).toString());
        
        InputStream stream = null;
        try {
            stream = new FileInputStream(BM.recup_info_film(1).get(1).toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BilletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(stream);
        imageFilm.setImage(image);
        
        descriptionFilm.setText(BM.recup_info_film(1).get(2).toString());
        
        genreFilm.setText("Genre : "+ BM.recup_info_film(1).get(3).toString());
              
        salleReservation.setText("Salle : " + BM.recup_info_film(1).get(4).toString());
        
        dateReservation.setText("Date : "+ BM.recup_info_film(1).get(5).toString());

        //CRON pour l'archivage billet
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BM.archiverBillet();
            }
        }, 0, 24 * 60 * 60 * 1000);//24*60*60*1000 delay de 1 jours entre l'execution du CRON d'archivage

        ObservableList<String> list = FXCollections.observableArrayList("First Class", "Second Class", "Third Class");
        categorieBillet.setItems(list);
        categorieBillet.setValue("First Class");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0, 1);
        valueFactory.setValue(0);
        nbPlaceBillet.setValueFactory(valueFactory);

        AcheterBillet.setDisable(true);

        nbPlaceBillet.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                String categorie = categorieBillet.getSelectionModel().getSelectedItem().toString();
                float totale = BM.calculTotal(categorie, nbPlaceBillet.getValue());
                Total.setText("Total : " + totale + " TD");
                float discount = BM.Remise(C, R, totale);
                remise.setText("Remise : " + String.valueOf(discount) + " TD");
                float prixFinalHorsTaxe = BM.prixFinalHT(totale, discount);
                
                prixFinalHT.setText("Prix Final HT : " + String.valueOf(prixFinalHorsTaxe) + " TD");
                float prixFinaleTTC = BM.prixFinalTTC(prixFinalHorsTaxe);
                DecimalFormat f = new DecimalFormat("##.00");
                System.out.println(f.format(prixFinaleTTC));

                prixFinalTTC.setText("Prix Final TTC : " + String.valueOf(f.format(prixFinaleTTC)) + " TD");
                if (nbPlaceBillet.getValue() == 0) {
                    AcheterBillet.setDisable(true);
                } else {
                    AcheterBillet.setDisable(false);

                }
            }

        });

        categorieBillet.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String categorie = categorieBillet.getSelectionModel().getSelectedItem().toString();
                float totale = BM.calculTotal(categorie, nbPlaceBillet.getValue());
                Total.setText("Total : " + totale);
                float discount = BM.Remise(C, R, totale);
                remise.setText("Remise : " + String.valueOf(discount));
                float prixFinalHorsTaxe = BM.prixFinalHT(totale, discount);
                prixFinalHT.setText("Prix Final HT : " + String.valueOf(prixFinalHorsTaxe));
                float prixFinaleTTC = BM.prixFinalTTC(prixFinalHorsTaxe);
                prixFinalTTC.setText("Prix Final TTC : " + String.valueOf(prixFinaleTTC));

            }

        });

    }

    @FXML
    public void reserverBillet(ActionEvent event) {
        String categorie = categorieBillet.getSelectionModel().getSelectedItem().toString();
        int nbPlace = nbPlaceBillet.getValue();
        //id client 1 
        //id reservation 1
        Billet B = new Billet(categorie, nbPlace, false, 1, 1);
        BM.ajouterBillet(B);
        B = BM.recup_Liste_Billets().get(BM.recup_Liste_Billets().size() - 1);

        FXMLLoader Loader = new FXMLLoader(getClass().getResource("BilletPaiement.fxml"));
        try {
            Parent root = Loader.load();

            BilletPaiementController payerBillet = Loader.getController();
            List<Object> BilletDetails = BM.recup_info_film(B.getIdReservation());
            payerBillet.setBilletDetails(BilletDetails, B);

            Scene BilletDetailScene = new Scene(root);
            Stage cineStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            cineStage.setScene(BilletDetailScene);

        } catch (IOException ex) {
            Logger.getLogger(ShowProductsDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reinitialiserBillet(ActionEvent event) {
        categorieBillet.setValue("First Class");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0, 1);
        valueFactory.setValue(0);
        nbPlaceBillet.setValueFactory(valueFactory);
        Total.setText("Total : " + "0.0");
        remise.setText("Remise : " + "0.0");
        prixFinalHT.setText("Prix Final HT : " + "0.0");
        prixFinalTTC.setText("Prix Final TTC : " + "0.0");

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
            prixFinalTTC.getScene().setRoot(root);
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
            prixFinalTTC.getScene().setRoot(root);
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
            prixFinalTTC.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
