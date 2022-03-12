/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import edu.cine.entities.Film;
import edu.cine.services.FilmCRUD;
import edu.cine.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ArchController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
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
      private FilmCRUD fc = new FilmCRUD();
    List<List<String>>  liste = fc.afficheArch();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
       List<Film> listeFilm = new ArrayList<Film>();
        FilmCRUD fc =new FilmCRUD();
    Connection  cnx = MyConnection.getInstance().getCnx();
        Date date = new Date();
        Timestamp currentDate = new Timestamp(date.getTime());
        System.out.println(currentDate.getYear());
        
        listeFilm.stream()
                // si depasser akther men aam 
                .filter(f -> f.getDateDispo().getYear() - currentDate.getYear() >1)
                .forEach((P) -> {
                    
                    try {
                        
                        String requete = "UPDATE film SET "
                                + "Archive= 1 ";
                     Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete); 
                        

                    } catch (SQLException e) {
                        System.out.println("erreur");
                    }
                });
    
      //supprimer
       
       

      
      
        
        listeFilm.stream()
                //si depasser akther men aam 
                .filter(f -> f.getDateDispo().getDay() - currentDate.getDay() >7 )
                .forEach((P) -> {
                    
                    try {
                        
                        String requete = "DELETE FROM film WHERE EtatAcc='en attente' ";
                        
                        Statement st = cnx.createStatement();
                        st.executeUpdate(requete);
                        
                        

                    } catch (SQLException e) {
                        System.out.println("erreur");
                    }
                });
    
        // TODO
              int column = 0;
        int row = 1;
           for (int i = 0; i < liste.size(); i++) {
 
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("filmm.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               FilmmController filmc = fxmlLoader.getController();
               filmc.setData(liste.get(i));
                if (column == 2) {
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

    
}
