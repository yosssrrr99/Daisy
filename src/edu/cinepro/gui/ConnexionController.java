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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class ConnexionController implements Initializable {

    @FXML
    private TextField bfusername;
    @FXML
    private TextField bfmdp;
    @FXML
    private Button bfcnx;
    @FXML
    private Text tfoublie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveconnexion(ActionEvent event){
        CompteCrud cc = new CompteCrud();
        String username = bfusername.getText();
        String mdp = bfmdp.getText();
        Compte c = cc.authentifier(username, mdp);
        if(c!=null){
            System.out.println("accepté");
        }else
            System.out.println("Compte n'existe pas");
        
         
    }
}

