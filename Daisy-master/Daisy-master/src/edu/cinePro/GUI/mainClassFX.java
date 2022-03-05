/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
//
//            root = FXMLLoader.load(getClass().getResource("don.fxml"));
//            primaryStage.setTitle("don_Client_GUI");
            
//
//            root = FXMLLoader.load(getClass().getResource("tableView.fxml"));
//            primaryStage.setTitle("produit_admin_GUI");

            root = FXMLLoader.load(getClass().getResource("productClient.fxml"));
            primaryStage.setTitle("produit_client_GUI");
//
//            root = FXMLLoader.load(getClass().getResource("billet.fxml"));
//            primaryStage.setTitle("billet_GUI");

//            root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));
//            primaryStage.setTitle("admin_Panel_GUI");

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
