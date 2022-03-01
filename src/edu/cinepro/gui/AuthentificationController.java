/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import edu.cinepro.services.CompteCrud;
import entities.Compte;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class AuthentificationController implements Initializable {

    @FXML
    private Text tfmail;
    @FXML
    private Text tfmdp;
    @FXML
    private Text tfrole;
    @FXML
    private Text tfimg;
    @FXML
    private Button tfauthentification;
    @FXML
    private RadioButton tfclient;
    @FXML
    private RadioButton tfetudiant;
    @FXML
    private Label tfbienvenue;
    @FXML
    private Text tfnom;
    @FXML
    private Text tfprenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savecompte(ActionEvent event) {
        CompteCrud cc = new CompteCrud();
        String username  = cc.random(tfrole.getText());
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String mail = tfmail.getText();
        String mdp = tfmdp.getText();
        String role = tfrole.getText();
        String image = tfimg.getText();
        Compte c;
        c = new Compte(username, nom, prenom,  mail, mdp, role, image);
        cc.ajouterCompte(c);
    }

    @FXML
    private void saveselectionner(ActionEvent event) {
        if(tfclient.isSelected()){
            tfrole.setText(tfclient.getText());   
        }
//        else {
//            tfrole.setText(tfetudiant.getText());
//                    }
    }
    
    
}

