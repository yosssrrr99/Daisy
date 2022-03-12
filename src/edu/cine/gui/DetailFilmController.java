/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import edu.cine.entities.Avis;
import edu.cine.entities.Film;
import edu.cine.utils.MyConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DetailFilmController implements Initializable {

    @FXML
    private AnchorPane paneFarRight;
    @FXML
    private ImageView profileImage;
    @FXML
    private ImageView image;
    @FXML
    private Label nono;
    @FXML
    private Label fifi;
    @FXML
    private Label gogo;
    @FXML
    private Label dodo;
    @FXML
    private Text dess;
    @FXML
    private Label eoeo;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        


    }    
        String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
   
     public void setFilmID(Film f) throws FileNotFoundException {
         Avis a = new Avis();
         System.out.println(f);
     try { 
            cnx = (Connection)MyConnection.getInstance().getCnx();
            query = "SELECT MoyenneAvis FROM  avis WHERE idF= ?";
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, f.getIdF());
            resultSet = preparedStatement.executeQuery();
         
            if(resultSet.next()){
           // System.out.println(resultSet.getFloat("MoyenneAvis"));     
          
           eoeo.setText(String.valueOf(resultSet.getFloat("MoyenneAvis")));
                         

        
            }
            
            nono.setText(String.valueOf(f.getNumRea()));
            fifi.setText(f.getNomF());
            gogo.setText(f.getGenre());
            dodo.setText(String.valueOf(f.getDuree()));
            dess.setText(f.getDescription());
            InputStream stream = new FileInputStream(f.getImage());
            Image imagee = new Image(stream);
            image.setImage(imagee);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }

    @FXML
    private void getFil(MouseEvent event) {
    }

    @FXML
    private void geta(MouseEvent event) {
    }
  
   

   
    
}
