/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import edu.cine.entities.Film;
import edu.cine.services.FilmCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ClienttController implements Initializable {

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
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    private FilmCRUD fc = new FilmCRUD();
    List<List<String>>  liste = fc.afficheFilmMail();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int column = 0;
        int row = 1;
           for (int i = 0; i < liste.size(); i++) {
               System.out.println(liste);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("film.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                FilmController filmc = fxmlLoader.getController();
               filmc.setData(liste.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                    System.out.println(liste);
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
                System.out.println("ereur here");
            }
        }

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

    @FXML
    private void showArchive(MouseEvent event) {
          FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Arch.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            
//                            ReservaController addReservController = loader.getController();
//                            addReservController. insertRecord();
                                                     
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }

    @FXML
    private void TOP(MouseEvent event) {
             FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Toto.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            
//                            ReservaController addReservController = loader.getController();
//                            addReservController. insertRecord();
                                                     
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }
    }



    

