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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    private Label heureReservation;
    @FXML
    private Label salleReservation;
    @FXML
    private VBox movieInformation;
    @FXML
    private VBox rightVbox;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                Total.setText("Total : " + totale);
                System.out.println(totale);
                float discount = BM.Remise(C, R, totale);
                System.out.println(discount);
                remise.setText("Remise : " + String.valueOf(discount));
                float prixFinalHorsTaxe = BM.prixFinalHT(totale, discount);
                prixFinalHT.setText("Prix Final HT : " + String.valueOf(prixFinalHorsTaxe));
                float prixFinaleTTC = BM.prixFinalTTC(prixFinalHorsTaxe);
                prixFinalTTC.setText("Prix Final TTC : " + String.valueOf(prixFinaleTTC));
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
    public void reserverBillet() {
        String categorie = categorieBillet.getSelectionModel().getSelectedItem().toString();
        int nbPlace = nbPlaceBillet.getValue();
        Billet B = new Billet(categorie, nbPlace, false, 1, 1);
        BM.ajouterBillet(B);
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

}
