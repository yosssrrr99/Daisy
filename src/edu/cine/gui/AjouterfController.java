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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterfController implements Initializable {

    @FXML
    private TextField fxNum;
    @FXML
    private TextField fxNom;
    @FXML
    private RadioButton btnAct;
    @FXML
    private RadioButton btnDra;
    @FXML
    private RadioButton fxJeu;
    @FXML
    private RadioButton fxCom;
    @FXML
    private TextField fxDes;
    @FXML
    private TextField fxImg;
    @FXML
    private Button btnAj;
    @FXML
    private TextField fxDuree;
    @FXML
    private Label fxG;
    @FXML
    private ToggleGroup Genre;
    @FXML
    private Button fxAcc;
    @FXML
    private Button fifi;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getGenre(ActionEvent event) {
        if(btnAct.isSelected()){
            fxG.setText(btnAct.getText());
        }
        else if(btnDra.isSelected()){
            fxG.setText(btnDra.getText());
        }
        else if(fxJeu.isSelected()){
           fxG.setText(fxJeu.getText());
        }
         else if(fxCom.isSelected()){
           fxG.setText(fxCom.getText());
        }
    }  

    @FXML
    private void saveFilm(ActionEvent event) {
        int Num =Integer.parseInt(fxNum.getText());
        String nom=fxNom.getText();
        int Duree=Integer.parseInt(fxDuree.getText());
        String genre =fxG.getText();
        String Des=fxDes.getText();
        String img =fxImg.getText();
        
        Film f = new Film(nom, genre, Num, img, Des, Duree);
        FilmCRUD fc = new FilmCRUD();
        fc.ajouterFilm3(f);
        
    
}

    @FXML
    private void getFil(ActionEvent event) {
         
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AccueilClient.fxml"));
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
    private void getAcc(ActionEvent event) {
    
      FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Acc.fxml"));
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
