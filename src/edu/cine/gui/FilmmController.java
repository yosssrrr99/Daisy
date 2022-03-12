/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FilmmController implements Initializable {

    @FXML
    private Label idff;
    @FXML
    private Text des;
    @FXML
    private Label titre;
    @FXML
    private ImageView image;
    @FXML
    private Label idres;
    @FXML
    private Label du;
    @FXML
    private Label dated;
    @FXML
    private Label datef;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    void setData(List<String> ls) {
               InputStream stream = null;
        try {
           
            titre.setText(ls.get(0));
            du.setText(String.valueOf(ls.get(6)));
            des.setText(ls.get(7));
            dated.setText(ls.get(8));
            datef.setText(ls.get(9));
            idres.setText(ls.get(8));
            idff.setText(ls.get(11));
            
            stream = new FileInputStream(ls.get(5));
            Image imagee = new Image(stream);
            
           
            image.setImage(imagee);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilmController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(FilmController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
