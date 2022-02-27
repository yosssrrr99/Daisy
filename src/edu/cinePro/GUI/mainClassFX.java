/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Asus
 */
public class mainClassFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        Parent root;
        try {
            //load est suceptible de declancher une exception
            //si on travail pas dans le meme package il nous faut la route vers le fichier

            
//            root = FXMLLoader.load(getClass().getResource("don.fxml"));
//            primaryStage.setTitle("don_Client_GUI");
//            
//            
            root = FXMLLoader.load(getClass().getResource("tableView.fxml"));
            primaryStage.setTitle("produit_admin_GUI");
//
//            root = FXMLLoader.load(getClass().getResource("productClient.fxml"));
//            primaryStage.setTitle("produit_client_GUI");


            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("tableView.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("erreurrrrrrrrrrrrrrr");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
